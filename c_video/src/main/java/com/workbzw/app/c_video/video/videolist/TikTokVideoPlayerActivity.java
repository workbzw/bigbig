package com.workbzw.app.c_video.video.videolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.workbzw.app.base.activity.BaseVMActivity;
import com.workbzw.app.base.router.RouterPath;
import com.workbzw.app.c_video.R;
import com.workbzw.app.c_video.bean.TiktokBean;
import com.workbzw.app.c_video.databinding.CVideoActivityTiktokVideoPlayerBinding;
import com.workbzw.app.c_video.utils.DataUtil;
import com.workbzw.app.c_video.utils.PreloadManager;
import com.workbzw.app.c_video.utils.ProxyVideoCacheManager;
import com.workbzw.app.c_video.utils.Utils;
import com.workbzw.app.c_video.controller.TikTokController;
import com.workbzw.app.c_video.view.TikTokRenderViewFactory;
import com.workbzw.app.c_video.view.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.L;


/**
 * 模仿抖音短视频，使用VerticalViewPager实现，实现了预加载功能
 * Created by Doikki on 2019/10/13.
 */
@Route(path = RouterPath.Video.TIKTOK)
public class TikTokVideoPlayerActivity extends BaseVMActivity<CVideoActivityTiktokVideoPlayerBinding, TiktokVideoPlayerViewModel> {
    /**
     * 当前播放位置
     */
    private int mCurPos;
    private List<TiktokBean> mVideoList = new ArrayList<>();
    private TiktokVideoPlayerAdapter mTiktokVideoPlayerAdapter;
    private VerticalViewPager mViewPager;
    private PreloadManager mPreloadManager;
    private TikTokController mController;

    private static final String KEY_INDEX = "index";

    public static void start(Context context, int index) {
        Intent i = new Intent(context, TikTokVideoPlayerActivity.class);
        i.putExtra(KEY_INDEX, index);
        context.startActivity(i);
    }

    @Override
    public Integer setLayout() {
        return R.layout.c_video_activity_tiktok_video_player;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initViewPager();
        initVideoView();
        mPreloadManager = PreloadManager.getInstance(this);
        addData(null);
        Intent extras = getIntent();
        int index = extras.getIntExtra(KEY_INDEX, 0);
        mViewPager.setCurrentItem(index);
        mViewPager.post(new Runnable() {
            @Override
            public void run() {
                startPlay(index);
            }
        });
    }

    VideoView mVideoView;

    private void initVideoView() {
        mVideoView = new VideoView(this);
        mVideoView.setLooping(true);
        //以下只能二选一，看你的需求
        mVideoView.setRenderViewFactory(TikTokRenderViewFactory.create());
//        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        mController = new TikTokController(this);
        mVideoView.setVideoController(mController);
    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.vvp);
        mViewPager.setOffscreenPageLimit(4);
        mTiktokVideoPlayerAdapter = new TiktokVideoPlayerAdapter(mVideoList);
        mViewPager.setAdapter(mTiktokVideoPlayerAdapter);
        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            private int mCurItem;
            /**
             * VerticalViewPager是否反向滑动
             */
            private boolean mIsReverseScroll;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == mCurItem) {
                    return;
                }
                mIsReverseScroll = position < mCurItem;
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == mCurPos) return;
                startPlay(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == VerticalViewPager.SCROLL_STATE_DRAGGING) {
                    mCurItem = mViewPager.getCurrentItem();
                }

                if (state == VerticalViewPager.SCROLL_STATE_IDLE) {
                    mPreloadManager.resumePreload(mCurPos, mIsReverseScroll);
                } else {
                    mPreloadManager.pausePreload(mCurPos, mIsReverseScroll);
                }
            }
        });
    }

    private void startPlay(int position) {
        int count = mViewPager.getChildCount();
        for (int i = 0; i < count; i++) {
            View itemView = mViewPager.getChildAt(i);
            TiktokVideoPlayerAdapter.ViewHolder viewHolder = (TiktokVideoPlayerAdapter.ViewHolder) itemView.getTag();
            if (viewHolder.mPosition == position) {
                mVideoView.release();
                Utils.removeViewFormParent(mVideoView);
                TiktokBean tiktokBean = mVideoList.get(position);
                String playUrl = mPreloadManager.getPlayUrl(tiktokBean.videoDownloadUrl);
                L.i("startPlay: " + "position: " + position + "  url: " + playUrl);
                mVideoView.setUrl(playUrl);
                //请点进去看isDissociate的解释
                mController.addControlComponent(viewHolder.mTikTokView, true);
                viewHolder.mPlayerContainer.addView(mVideoView, 0);
                mVideoView.start();
                mCurPos = position;
                break;
            }
        }
    }

    /**
     * 添加数据
     */
    public void addData(View view) {
        mVideoList.addAll(DataUtil.getTiktokDataFromAssets(this));
        mTiktokVideoPlayerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }
        mPreloadManager.removeAllPreloadTask();
        //清除缓存，实际使用可以不需要清除，这里为了方便测试
        ProxyVideoCacheManager.clearAllCache(this);
    }

    @Override
    public void onBackPressed() {
        if (mVideoView == null || !mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }
}

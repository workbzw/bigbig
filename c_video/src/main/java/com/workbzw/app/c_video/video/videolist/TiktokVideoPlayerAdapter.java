package com.workbzw.app.c_video.video.videolist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.workbzw.app.base.image.Imager;
import com.workbzw.app.c_video.R;
import com.workbzw.app.c_video.bean.TiktokBean;
import com.workbzw.app.c_video.utils.PreloadManager;
import com.workbzw.app.c_video.view.TikTokView;

import java.util.ArrayList;
import java.util.List;


public class TiktokVideoPlayerAdapter extends PagerAdapter {

    /**
     * View缓存池，从ViewPager中移除的item将会存到这里面，用来复用
     */
    private List<View> mViewPool = new ArrayList<>();

    /**
     * 数据源
     */
    private List<TiktokBean> mVideoBeans;

    public TiktokVideoPlayerAdapter(List<TiktokBean> videoBeans) {
        this.mVideoBeans = videoBeans;
    }

    @Override
    public int getCount() {
        return mVideoBeans == null ? 0 : mVideoBeans.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        View view = null;
        if (mViewPool.size() > 0) {//取第一个进行复用
            view = mViewPool.get(0);
            mViewPool.remove(0);
        }

        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.c_video_item_tiktok_video_player, container, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        TiktokBean item = mVideoBeans.get(position);
        //开始预加载
        PreloadManager.getInstance(context).addPreloadTask(item.videoDownloadUrl, position);
        Imager.loadUrl(viewHolder.mThumb, item.coverImgUrl);
        viewHolder.mTitle.setText(item.title);
        viewHolder.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
        viewHolder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了标题", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.mPosition = position;
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View itemView = (View) object;
        container.removeView(itemView);
        TiktokBean item = mVideoBeans.get(position);
        //取消预加载
        PreloadManager.getInstance(container.getContext()).removePreloadTask(item.videoDownloadUrl);
        //保存起来用来复用
        mViewPool.add(itemView);
    }

    /**
     * 借鉴ListView item复用方法
     */
    public static class ViewHolder {

        public int mPosition;
        public TextView mTitle;//标题
        public ImageView mThumb;//封面图
        public ImageView ivClose;//关闭
        public TikTokView mTikTokView;
        public FrameLayout mPlayerContainer;

        ViewHolder(View itemView) {
            mTikTokView = itemView.findViewById(R.id.tiktok_View);
            mTitle = mTikTokView.findViewById(R.id.tv_title);
            mThumb = mTikTokView.findViewById(R.id.iv_thumb);
            ivClose = mTikTokView.findViewById(R.id.iv_back);
            mPlayerContainer = itemView.findViewById(R.id.container);
            itemView.setTag(this);
        }
    }
}

package com.workbzw.app.c_video.video.coverlist;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.workbzw.app.base.fragment.BaseVMFragment;
import com.workbzw.app.base.router.RouterPath;
import com.workbzw.app.c_video.R;
import com.workbzw.app.c_video.bean.TiktokBean;
import com.workbzw.app.c_video.databinding.CVideoFragmentTiktokCoverListBinding;
import com.workbzw.app.c_video.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页视频列表
 */
@Route(path = RouterPath.Video.LIST)
public class TiktokCoverListFragment extends BaseVMFragment<CVideoFragmentTiktokCoverListBinding, TiktokCoverListViewModel> {
    private List<TiktokBean> data = new ArrayList<>();

    public TiktokCoverListFragment() {
    }

    @Override
    public int setLayout() {
        return R.layout.c_video_fragment_tiktok_cover_list;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        TikTokCoverListAdapter tikTokCoverListAdapter = new TikTokCoverListAdapter(data);

        binding.rcvCoverList.setLayoutManager(layoutManager);
        binding.rcvCoverList.addItemDecoration(new SimpleItemDecoration());
        binding.rcvCoverList.setAdapter(tikTokCoverListAdapter);
        initData(tikTokCoverListAdapter);
    }

    void initData(TikTokCoverListAdapter adapter) {
        //模拟请求数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<TiktokBean> tiktokBeans = DataUtil.getTiktokDataFromAssets(getActivity());
                data.addAll(tiktokBeans);
                binding.rcvCoverList.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    class SimpleItemDecoration extends RecyclerView.ItemDecoration {
        /**
         * @param outRect 边界
         * @param view    recyclerView ItemView
         * @param parent  recyclerView
         * @param state   recycler 内部数据管理
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //设定底部边距为1px
            outRect.set(4, 0, 4, 8);
        }
    }
}
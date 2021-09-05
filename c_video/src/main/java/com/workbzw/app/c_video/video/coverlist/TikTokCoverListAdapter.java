package com.workbzw.app.c_video.video.coverlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.workbzw.app.base.image.Imager;
import com.workbzw.app.base.util.OnSingleClickListener;
import com.workbzw.app.c_video.R;
import com.workbzw.app.c_video.bean.TiktokBean;
import com.workbzw.app.c_video.databinding.CVideoItemTiktokCoverListBinding;
import com.workbzw.app.c_video.video.videolist.TikTokVideoPlayerActivity;

import java.util.List;


public class TikTokCoverListAdapter extends RecyclerView.Adapter<TikTokCoverListAdapter.TikTokListViewHolder> {

    public List<TiktokBean> data;

    public TikTokCoverListAdapter(List<TiktokBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TikTokListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CVideoItemTiktokCoverListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.c_video_item_tiktok_cover_list, parent, false);
        return new TikTokListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TikTokListViewHolder holder, int position) {
        TiktokBean item = data.get(position);
        holder.binding.tvTitle.setText(item.title);
        Imager.loadUrl(holder.binding.ivThumb, item.coverImgUrl);
        holder.mPosition = position;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) holder.binding.ivThumb.getLayoutParams();
        /*随机80像素以内的高度差*/
        if (position % 3 == 0) {
            layoutParams.height = (int) (viewDefaultHeight + 80);
        } else if (position % 3 == 1) {
            layoutParams.height = (int) (viewDefaultHeight - 80);
        } else {
            layoutParams.height = viewDefaultHeight;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static int viewDefaultHeight = 0;
    public static boolean isFirst = true;

    public static class TikTokListViewHolder extends RecyclerView.ViewHolder {
        CVideoItemTiktokCoverListBinding binding;
        int mPosition;

        public TikTokListViewHolder(@NonNull CVideoItemTiktokCoverListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) binding.ivThumb.getLayoutParams();
            if (isFirst) {
                viewDefaultHeight = layoutParams.height;
                isFirst = false;
            }
            itemView.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onSingleClick(View v) {
                    TikTokVideoPlayerActivity.start(itemView.getContext(), mPosition);
                }
            });
        }
    }
}

package com.workbzw.app.c_video.video.coverlist;

import androidx.lifecycle.MutableLiveData;

import com.workbzw.app.base.viewmodel.BaseViewModel;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/8/31 12:53 AM
 * @desc
 */
public class TiktokCoverListViewModel extends BaseViewModel {
    MutableLiveData<Float> ratio;

    public MutableLiveData<Float> getRatio() {
        if (ratio == null) {
            ratio = new MutableLiveData<>();
            ratio.setValue((float) (9f / (16f + Math.random() * 2)));
        }
        return ratio;
    }
}

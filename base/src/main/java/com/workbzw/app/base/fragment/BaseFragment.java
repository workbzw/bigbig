package com.workbzw.app.base.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.workbzw.app.base.AppApplication;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/8/30 3:10 PM
 * @desc
 */
public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Context context() {
        return AppApplication.getInstance();
    }

    public FragmentActivity activity() {
        return getActivity();
    }
}

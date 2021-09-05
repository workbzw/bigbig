package com.workbzw.app.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.workbzw.app.base.BR;
import com.workbzw.app.base.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/8/30 3:10 PM
 * @desc
 */
public abstract class BaseVMFragment<DB extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment {
    public abstract int setLayout();

    public abstract void init(Bundle savedInstanceState);

    public DB binding;
    public VM vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, setLayout(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Class vmClazz;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            vmClazz = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            vmClazz = BaseViewModel.class;
        }
        vm = (VM) new ViewModelProvider(this).get(vmClazz);
        binding.setLifecycleOwner(this);
        binding.setVariable(BR.vm, vm);
        init(savedInstanceState);
    }
}

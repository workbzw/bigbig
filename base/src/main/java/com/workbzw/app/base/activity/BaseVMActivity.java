package com.workbzw.app.base.activity;

import android.os.Bundle;

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
 * @date 2021/8/30 9:19 AM
 * @desc
 */
public abstract class BaseVMActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity {
    public abstract Integer setLayout();

    public abstract void init(Bundle savedInstanceState);

    public DB binding;
    public VM vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarTransparent();
        binding = DataBindingUtil.setContentView(this, setLayout());
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

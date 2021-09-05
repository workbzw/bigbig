package com.workbzw.app.profile;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.workbzw.app.base.fragment.BaseVMFragment;
import com.workbzw.app.base.router.RouterPath;
import com.workbzw.app.profile.databinding.CAccountAccountFragmentBinding;

@Route(path = RouterPath.Account.ACCOUNT)
public class AccountFragment extends BaseVMFragment<CAccountAccountFragmentBinding, AccountViewModel> {

    @Override
    public int setLayout() {
        return R.layout.c_account_account_fragment;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }
}
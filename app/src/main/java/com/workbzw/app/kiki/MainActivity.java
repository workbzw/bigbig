package com.workbzw.app.kiki;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.workbzw.app.base.activity.BaseVMActivity;
import com.workbzw.app.base.router.Navigator;
import com.workbzw.app.base.router.RouterPath;
import com.workbzw.app.kiki.databinding.AppKikiActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(path = RouterPath.Main.MAIN)
public class MainActivity extends BaseVMActivity<AppKikiActivityMainBinding, MainViewModel> {

    @Override
    public Integer setLayout() {
        return R.layout.app_kiki_activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        /*视频列表*/
        Fragment tiktokFragment = Navigator.fragment(RouterPath.Video.LIST);
        /*个人中心*/
        Fragment accountFragment = Navigator.fragment(RouterPath.Account.ACCOUNT);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(tiktokFragment);
        fragmentList.add(accountFragment);
        FragmentAdapter vpAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        binding.vpContainer.setAdapter(vpAdapter);
        /*底部按钮点击切换*/
        Map<Integer, Integer> btnClickMap = new HashMap<>();
        btnClickMap.put(R.id.nav_item_1, 0);
        btnClickMap.put(R.id.nav_item_2, 1);
        BottomNavigationViewHelper.bindViewPager(binding.bnvBottom, binding.vpContainer, btnClickMap);
    }
}
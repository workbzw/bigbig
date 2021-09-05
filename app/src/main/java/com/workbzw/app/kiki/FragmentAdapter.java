package com.workbzw.app.kiki;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/4 6:29 PM
 * @desc
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList;

    public FragmentAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

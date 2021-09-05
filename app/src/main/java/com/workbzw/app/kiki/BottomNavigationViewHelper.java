package com.workbzw.app.kiki;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Map;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/4 4:24 PM
 * @desc
 */
public class BottomNavigationViewHelper {

    public static void bindViewPager(BottomNavigationView bnv, ViewPager vp, Map<Integer, Integer> menuIdViewPagerPositionMap) {

        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                int viewPagerPosition = menuIdViewPagerPositionMap.get(itemId);
                vp.setCurrentItem(viewPagerPosition, false);
                return true;
            }
        });

    }
}

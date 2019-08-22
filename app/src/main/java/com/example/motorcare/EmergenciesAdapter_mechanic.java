package com.example.motorcare;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class EmergenciesAdapter_mechanic extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private  final List<String> FragmentListTitles = new ArrayList<>();
    public EmergenciesAdapter_mechanic(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return FragmentListTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitles.get(position);
    }

    public void addFragment(Fragment fragment, String Title){
       fragmentList.add(fragment);
       FragmentListTitles.add(Title);

    }
}

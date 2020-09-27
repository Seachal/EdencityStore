package com.edencity.store.home.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


// Created by Ardy on 2020/3/3.
public class CountVpAdapter extends FragmentPagerAdapter {

    private final ArrayList<String> objects;
    private final ArrayList<Fragment> objects1;

    public CountVpAdapter(FragmentManager fm, ArrayList<String> objects, ArrayList<Fragment> objects1) {
        super(fm);
        this.objects = objects;
        this.objects1 = objects1;
    }

    @Override
    public Fragment getItem(int i) {
        return objects1.get(i);
    }

    @Override
    public int getCount() {
        return objects1.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return objects.get(position);
    }
}

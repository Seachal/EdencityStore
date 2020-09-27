package com.edencity.store.user.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


// Created by Ardy on 2020/2/12.
public class MyCertVpAdapter extends FragmentPagerAdapter {

    private  ArrayList<Fragment> mList;
    private final ArrayList<String> mTitle;

    public MyCertVpAdapter(FragmentManager fm, ArrayList<Fragment> mList, ArrayList<String> mTitle) {
        super(fm);
        this.mList = mList;
        this.mTitle = mTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return mList.get(i);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }

}

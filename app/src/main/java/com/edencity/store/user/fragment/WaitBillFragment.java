package com.edencity.store.user.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.user.adapter.MyCertVpAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitBillFragment extends BaseFragment2 {


    private SmartTabLayout tab;
    private ImageView back;
    private ViewPager vp;
    private ArrayList<String> mTitle;

    public WaitBillFragment() {
        // Required empty public constructor
    }

    public static WaitBillFragment newInstance() {
        return new WaitBillFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_wait_bill, container, false);
        initView(inflate);
        initLogic();
        return inflate;
    }

    private void initView(View inflate) {

        tab= inflate.findViewById(R.id.tab);

        back = inflate.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        vp = inflate.findViewById(R.id.vp);
    }
    private void initLogic() {



        mTitle = new ArrayList<>();
        mTitle.add("房租");
        mTitle.add("广告");
        ArrayList<Fragment> objects = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            MyWaitBillFragment myCertFragment = new MyWaitBillFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("tag",i);
            myCertFragment.setArguments(bundle);
            objects.add(myCertFragment);
        }

        MyCertVpAdapter vpAdapter = new MyCertVpAdapter(getChildFragmentManager(), objects,mTitle);
        vp.setAdapter(vpAdapter);
        tab.setViewPager(vp);
    }
}

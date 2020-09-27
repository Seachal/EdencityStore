package com.edencity.store.user.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.home.activity.ShopDetailActivity;
import com.edencity.store.home.adapter.HomeListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyActiveFragment extends BaseFragment2 {


    private SmartRefreshLayout smart;
    private ImageView back;
    private RecyclerView rlv_active;
    private HomeListAdapter homeListAdapter;

    public MyActiveFragment() {
        // Required empty public constructor
    }


    public static MyActiveFragment newInstance(){
        return new MyActiveFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my_active, container, false);
        initView(inflate);
        initLogic();
        return inflate;
    }

    private void initLogic() {
        ArrayList<BaseDebug> objects = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            objects.add(new BaseDebug("伊甸城VIP活动空间","伊甸城山西有限公司",R.drawable.banner,"伊甸城VIP厅"));
        }
        homeListAdapter = new HomeListAdapter(getActivity());
        rlv_active.setLayoutManager(new LinearLayoutManager(getContext()));
        rlv_active.setAdapter(this.homeListAdapter);
        /*homeListAdapter.addData(objects);*/
        homeListAdapter.onClick(new HomeListAdapter.onItemClick() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), ShopDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(View inflate) {

        back = inflate.findViewById(R.id.back);
        back.setOnClickListener(v -> pop());
        rlv_active = inflate.findViewById(R.id.rlv_active);
        smart = inflate.findViewById(R.id.smart);
    }

}

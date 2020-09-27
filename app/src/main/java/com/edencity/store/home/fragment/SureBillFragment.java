package com.edencity.store.home.fragment;


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

import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.home.activity.BillDetailActivity;
import com.edencity.store.home.adapter.BillSureAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SureBillFragment extends BaseFragment2 {


    private ImageView back;
    private RecyclerView rlv_bill;
    private SmartRefreshLayout smart;

    public SureBillFragment() {
        // Required empty public constructor
    }


    public static SureBillFragment newInstance(){
        return new SureBillFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_sure_bill, container, false);

        initView(inflate);
        initRlv();
        return inflate;
    }

    private void initRlv() {
        BillSureAdapter billSureAdapter = new BillSureAdapter();
        rlv_bill.setAdapter(billSureAdapter);

        billSureAdapter.onClick(new BillSureAdapter.onItemClick() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), BillDetailActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView(View inflate) {

        back =  inflate.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        rlv_bill = inflate.findViewById(R.id.rlv_bill);
        rlv_bill.setLayoutManager(new LinearLayoutManager(getContext()));
        smart = inflate.findViewById(R.id.smart);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }
}

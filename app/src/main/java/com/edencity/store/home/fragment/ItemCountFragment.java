package com.edencity.store.home.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.home.adapter.CountItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemCountFragment extends Fragment {


    private SmartRefreshLayout smart;
    private RecyclerView rlv_chat;
    private MyNormalTextView end_with;
    private int type;
    private String s;

    public ItemCountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_item_count, container, false);
        Bundle arguments = getArguments();
        if (arguments!=null){
             type = arguments.getInt("type");
        }
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        ArrayList<BaseDebug> objects = new ArrayList<>();
        if (type==0){
            s = "7日";
        }else if (type==1){
            s = "30日";
        }else if (type==2){
            s = "自然月";
        }else if (type==3){
            s = "自定义";
        }
        objects.add(new BaseDebug("营收","56700.00",1,s,false,"10%"));
        objects.add(new BaseDebug("支出","56700.00",2,s,true,"20%"));
        objects.add(new BaseDebug("订单","56700.00",3,s,false,"10%"));
        objects.add(new BaseDebug("客单价","56700.00",4,s,true,"30%"));

        rlv_chat.setLayoutManager(new LinearLayoutManager(getContext()));
        CountItemAdapter countItemAdapter = new CountItemAdapter();
        countItemAdapter.addData(objects);
        rlv_chat.setAdapter(countItemAdapter);
        rlv_chat.setHasFixedSize(true);
        rlv_chat.setNestedScrollingEnabled(false);
    }

    private void initView(View inflate) {
         smart = inflate.findViewById(R.id.smart);
         rlv_chat = inflate.findViewById(R.id.rlv_chat);
         end_with = inflate.findViewById(R.id.end_with);



    }

}

package com.edencity.store.user.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edencity.store.R;
import com.edencity.store.user.adapter.WaitBillAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyWaitBillFragment extends Fragment {


    private RecyclerView rlv;

    public MyWaitBillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my_wait_bill, container, false);
        initView(inflate);
        initList();
        return inflate;
    }

    private void initList() {
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        WaitBillAdapter waitBillAdapter = new WaitBillAdapter();
        rlv.setAdapter(waitBillAdapter);
    }

    private void initView(View inflate) {

        rlv = inflate.findViewById(R.id.rlv);

    }

}

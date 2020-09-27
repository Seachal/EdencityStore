package com.edencity.store.user.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/*import cn.zhongyu.edencity.user.adapter.GridImageAdapter;*/


/**
 * A simple {@link Fragment} subclass.
 */
public class FaPiaoFragment extends BaseFragment2 {

    @BindView(R.id.name)
    MyMediumTextView name;
    @BindView(R.id.address)
    MyNormalTextView address;


    public static FaPiaoFragment newInstance() {
        return new FaPiaoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fapiao, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewItemClicked(View view) {
        if (view.getId() == R.id.btn_back) {
            pop();
        }
    }

}

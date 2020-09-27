package com.edencity.store.user.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.user.activity.BankCardDetalActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBankCardFragment extends BaseFragment2 {


    private ImageView card;
    private MyMediumTextView card_code;

    public MyBankCardFragment() {
        // Required empty public constructor
    }
    public static MyBankCardFragment newInstance(){
        return new MyBankCardFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my_bank_card, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        StatusBarCompat.changeToLightStatusBar(getActivity());
    }

    private void initView(View inflate) {

        card = inflate.findViewById(R.id.card);
        card_code = inflate.findViewById(R.id.card_code);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BankCardDetalActivity.class);
                startActivity(intent);
            }
        });

    }

}

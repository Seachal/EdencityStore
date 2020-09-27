package com.edencity.store.user.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.entity.BaseDebug;


// Created by Ardy on 2020/2/25.
public class WaitBillAdapter extends RecyclerView.Adapter {

    private ArrayList<BaseDebug> mList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wait_bill
                    , viewGroup,false);
            return new ViewHolder1(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


           ViewHolder1 holder = (ViewHolder1) viewHolder;
           holder.mPayState.setText("待支付");
           /*holder.mPay.setText(mList.get(i).getSubtitle());*/
           holder.mHadMoney.setText("30000.00");
           holder.mTime.setText("应缴日期2020-02-24");
           holder.mPlace.setText("伊甸城4号楼2层506室");
           holder.mShouldPayTime.setText("第2期房租账单");


    }



    @Override
    public int getItemCount() {
        /*return mList.size()==0?0:mList.size();*/
        return 2;
    }

    public void addData(ArrayList<BaseDebug> objects) {
        mList.clear();
        mList.addAll(objects);
        notifyDataSetChanged();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private final MyMediumTextView mPayState;
        private ImageView mShopImg;
        private MyMediumTextView mHadMoney;
        private MyMediumTextView mShouldPayTime;
        private MyNormalTextView mTime;
        private MyNormalTextView mPlace;
        private MyNormalTextView mPay;

        public ViewHolder1(@NonNull View inflate) {
            super(inflate);

            mShopImg = (ImageView)inflate.findViewById(R.id.shop_img);
            mHadMoney = (MyMediumTextView) inflate.findViewById(R.id.had_money);
            mShouldPayTime = (MyMediumTextView) inflate.findViewById(R.id.should_pay_time);
            mPlace = (MyNormalTextView) inflate.findViewById(R.id.place);
            mTime = (MyNormalTextView) inflate.findViewById(R.id.time);
            mPay = (MyNormalTextView) inflate.findViewById(R.id.pay);
            mPayState = (MyMediumTextView) inflate.findViewById(R.id.pay_state);

        }
    }

}

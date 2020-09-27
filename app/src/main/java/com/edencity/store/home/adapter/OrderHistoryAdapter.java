package com.edencity.store.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.entity.BaseDebug;


// Created by Ardy on 2020/1/20.
public class OrderHistoryAdapter extends RecyclerView.Adapter {

    private ArrayList<BaseDebug> mList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_histrory, viewGroup,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        if (i%2==1){
            holder.mTag.setText("赠");
            holder.mOder_type.setText("一级会员：年卡");
            holder.look_price.setText("¥365.00");
        }else{
            holder.mTag.setText("买");
            holder.mOder_type.setText("二级会员：年卡");
            holder.look_price.setText("查看邀请成员");
        }

        holder.begin_time.setText("有效期至：2021-11-24");
        holder.end.setText("赠送时间：2018-11-24");
    }

    @Override
    public int getItemCount() {
        return mList.size()>0?mList.size():10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final MyMediumTextView mTag;
        private final MyMediumTextView mOder_type;
        private final MyMediumTextView look_price;
        private final MyNormalTextView begin_time;
        private final MyNormalTextView end;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTag = itemView.findViewById(R.id.tag_text);
            mOder_type =  itemView.findViewById(R.id.order_type);
            begin_time = itemView.findViewById(R.id.begin);
            end = itemView.findViewById(R.id.end);
            look_price = itemView.findViewById(R.id.look_price);

        }
    }

}

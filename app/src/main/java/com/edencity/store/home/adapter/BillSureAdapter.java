package com.edencity.store.home.adapter;

import android.graphics.Color;
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
import com.edencity.store.util.ResUtils;

// Created by Ardy on 2020/3/7.
public class BillSureAdapter extends RecyclerView.Adapter {

    private ArrayList<BaseDebug> mList = new ArrayList<>();
    private onItemClick ono;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bill_sure, viewGroup,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        if (i%2==1){
            holder.mOder_type.setText("收款-二维码收款");
            holder.price.setText("+365.00");
            holder.price.setTextColor(ResUtils.getColor(holder.price.getContext(),R.color.blue_nomal));
        }else{
            holder.mOder_type.setText("付款-二维码付款");
            holder.price.setText("-30.00");
            holder.price.setTextColor(Color.parseColor("#333333"));
        }

        holder.time.setText("2月8日 08:11");
        holder.order_code.setText("订单编号：2015239842353902");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ono!=null){
                    ono.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 12;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final MyMediumTextView mOder_type;
        private final MyMediumTextView price;
        private final MyNormalTextView time;
        private final MyNormalTextView order_code;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mOder_type =  itemView.findViewById(R.id.order_type);
            order_code = itemView.findViewById(R.id.end);
            price = itemView.findViewById(R.id.look_price);
            time = itemView.findViewById(R.id.time_tag);

        }
    }
    public interface onItemClick{
        void onClick(int position);
    }
    public void onClick(onItemClick itemClick){
        ono = itemClick;
    }

}

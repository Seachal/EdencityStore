package com.edencity.store.user.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edencity.store.R;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.entity.BillEntity;

import java.util.ArrayList;
import java.util.List;

// Created by Ardy on 2020/3/28.
public class BillAdapter extends RecyclerView.Adapter {
    public ArrayList<BillEntity.AccountBillsBean.ListBean> mList = new ArrayList<>();
    private onItemClick ono;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_order, viewGroup,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        BillEntity.AccountBillsBean.ListBean bean = mList.get(i);
        if (bean.getChangeType().equals("0")){
            holder.price.setText("+"+bean.getChangeBalance());
        }else{
            holder.price.setText("-"+bean.getChangeBalance());
        }
        switch (bean.getSourceType()){
                case "1":
                    holder.shop_name.setText("订单");
                break;
                case "2":
                    holder.shop_name.setText("结算");
                break;
                case "3":
                    holder.shop_name.setText("广告");
                break;
                case "4":
                    holder.shop_name.setText("房租费用支出");
                break;
                case "5":
                    holder.shop_name.setText("退款");
                break;
        }
        holder.time_tag.setText(bean.getCreatetime());
        holder.order_number.setText(bean.getSourceOrderId());
        holder.itemView.setOnClickListener(v -> {
           if (ono!=null){
               ono.onItem(i);
           }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size()>0?mList.size():0;
    }

    public void addData(List<BillEntity.AccountBillsBean.ListBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addNewData(List<BillEntity.AccountBillsBean.ListBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final MyNormalTextView time_tag;
        private final MyMediumTextView shop_name;
        private final MyMediumTextView price;
        private final MyNormalTextView order_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shop_name = itemView.findViewById(R.id.shop_name);
            time_tag = itemView.findViewById(R.id.time_tag);
            order_number = itemView.findViewById(R.id.order_number);
            price = itemView.findViewById(R.id.price);
        }
    }

    public interface onItemClick{
        void onItem(int i);
    }
    public void onItem(onItemClick onItemClick){
        ono = onItemClick;
    }
}

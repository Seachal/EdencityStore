package com.edencity.store.user.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edencity.store.R;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.entity.CountHistoryEntity;
import com.edencity.store.util.ButtonUtils;

import java.util.ArrayList;
import java.util.List;

// Created by Ardy on 2020/3/28.
public class BillCountAdapter extends RecyclerView.Adapter {

    public ArrayList<CountHistoryEntity.AccountBillsBean.ListBean> mList = new ArrayList<>();
    private onItmeClickLisener ono;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_order, viewGroup,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        CountHistoryEntity.AccountBillsBean.ListBean bean = mList.get(i);

        holder.price.setText("-"+bean.getBalanceAmount());
        holder.shop_name.setText("伊甸果结算");
        holder.time_tag.setText(bean.getCreatetime());
        holder.order_number.setText(bean.getBalanceNo());
        holder.mll.setOnClickListener(v -> {
            if (!ButtonUtils.isFastDoubleClick(R.id.ll)){
                if (ono!=null){
                    ono.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size()>0?mList.size():0;
    }

    public void addData(List<CountHistoryEntity.AccountBillsBean.ListBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addNewData(List<CountHistoryEntity.AccountBillsBean.ListBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final MyNormalTextView time_tag;
        private final MyMediumTextView shop_name;
        private final MyMediumTextView price;
        private final MyNormalTextView order_number;
        private final ConstraintLayout mll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shop_name = itemView.findViewById(R.id.shop_name);
            time_tag = itemView.findViewById(R.id.time_tag);
            order_number = itemView.findViewById(R.id.order_number);
            price = itemView.findViewById(R.id.price);
            mll = itemView.findViewById(R.id.ll);
        }
    }

    public interface onItmeClickLisener{
        void onItemClick(int i);
    }
    public void onItemClick(onItmeClickLisener lisener){
        ono = lisener ;
    }
}

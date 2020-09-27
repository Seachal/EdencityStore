package com.edencity.store.user.adapter;

import android.graphics.Color;
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

// Created by Ardy on 2020/3/12.
public class ItemTicketListAdapter extends RecyclerView.Adapter {
    public ArrayList<BaseDebug> mList = new ArrayList<>();
    private onSelect ono;
    private int type;

    public ItemTicketListAdapter(int type) {
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ticket, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        BaseDebug baseDebug = mList.get(i);

        if (type==1){
            if (baseDebug.isChecked()){
                holder.check.setImageResource(R.mipmap.check_blue);
            }else{
                holder.check.setImageResource(R.mipmap.check_gray);
            }
            holder.mTv.setText("2020-01-25");
            holder.sign.setText(baseDebug.getUrl());
            holder.price.setText(baseDebug.getSubtitle());
            holder.type.setText(baseDebug.getTitle());
            holder.check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ono!=null){
                        ono.select(i);
                    }
                }
            });holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ono!=null){
                        ono.onItemClick(i);
                    }
                }
            });
        }else{
             holder.check.setVisibility(View.GONE);
            holder.mTv.setText("2020-01-25");
            holder.sign.setText(baseDebug.getSubtitle());
            holder.price.setText(baseDebug.getUrl());
            holder.price.setTextColor(Color.parseColor("#666666"));
            holder.type.setText(baseDebug.getTitle());
            holder.check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ono!=null){
                        ono.select(i);
                    }
                }
            });holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ono!=null){
                        ono.onItemClick(i);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size()>0?mList.size():0;
    }

    public void addData(ArrayList<BaseDebug> objects) {
        mList.clear();
        mList.addAll(objects);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final MyNormalTextView mTv;
        private final MyNormalTextView sign;
        private final MyMediumTextView price;
        private final MyMediumTextView type;
        private final ImageView check;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTv = itemView.findViewById(R.id.time);
            sign = itemView.findViewById(R.id.sign);
            price = itemView.findViewById(R.id.all_price);
            type = itemView.findViewById(R.id.type);
            check = itemView.findViewById(R.id.check);
        }
    }

    public interface onSelect{
        void select(int i);
        void onItemClick(int i);
    }
    public void select(onSelect onSelect){
        ono = onSelect;
    }

}

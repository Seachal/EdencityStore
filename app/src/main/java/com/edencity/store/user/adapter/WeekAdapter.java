package com.edencity.store.user.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.entity.BaseDebug;

// Created by Ardy on 2020/3/11.
public class WeekAdapter extends RecyclerView.Adapter {

    public ArrayList<BaseDebug> mList = new ArrayList<>();
    private onSelect ono;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_week, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

       ViewHolder holder = (ViewHolder) viewHolder;
        BaseDebug baseDebug = mList.get(i);
        if (baseDebug.getTitle().equals("")){
            holder.mTv.setBackgroundResource(R.drawable.week_tag_null);
        }else{
            if (baseDebug.isChecked()){
                holder.mTv.setTextColor(Color.parseColor("#5199F7"));
                holder.mTv.setBackgroundResource(R.drawable.week_tag_blue);
            }else{
                holder.mTv.setTextColor(Color.parseColor("#666666"));
                holder.mTv.setBackgroundResource(R.drawable.week_tag_gray);
            }
            holder.mTv.setText(baseDebug.getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ono!=null){
                        ono.select(i);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTv = itemView.findViewById(R.id.tv);
        }
    }

    public interface onSelect{
        void select(int i);
    }
    public void select(onSelect onSelect){
        ono = onSelect;
    }
}

package com.edencity.store.home.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.entity.BaseDebug;


// Created by Ardy on 2020/3/3.
public class CountItemAdapter extends RecyclerView.Adapter {

    private ArrayList<BaseDebug> mList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_count, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        BaseDebug baseDebug = mList.get(i);
        switch (baseDebug.getResource()) {
            case 1:
                holder.bg.setImageResource(R.drawable.first_bg);
                break;
            case 2:
                holder.bg.setImageResource(R.drawable.second_bg);
                break;
            case 3:
                holder.bg.setImageResource(R.drawable.second_bg);
                break;
            case 4:
                holder.bg.setImageResource(R.drawable.founth_bg);
                break;
        }
        holder.type.setText(baseDebug.getTitle());
        holder.day.setText(baseDebug.getDate());
        holder.point.setText(baseDebug.getUrl());
        if (baseDebug.isChecked()){
            holder.point.setTextColor(Color.parseColor("#8FD789"));
            holder.img_type.setImageResource(R.mipmap.down);
        }else{
            holder.point.setTextColor(Color.parseColor("#F65353"));
            holder.img_type.setImageResource(R.mipmap.up);
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

        private final ImageView img_type;
        private final ImageView bg;
        private final MyNormalTextView type;
        private final MyNormalTextView point;
        private final MyNormalTextView day;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.type);
            day = itemView.findViewById(R.id.day);
            point = itemView.findViewById(R.id.point);
            img_type = itemView.findViewById(R.id.img_type);
            bg = itemView.findViewById(R.id.bg);
        }
    }
}

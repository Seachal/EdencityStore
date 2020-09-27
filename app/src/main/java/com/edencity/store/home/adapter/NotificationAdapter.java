package com.edencity.store.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.edencity.store.R;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.entity.MsgEntity;

// Created by Ardy on 2020/3/5.
public class NotificationAdapter extends RecyclerView.Adapter {


    private ArrayList<MsgEntity.MessageListBean.ListBean>mList = new ArrayList<>();
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notification2, viewGroup,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        holder.desc.setText(mList.get(i).getContent());
        holder.desc2.setText(mList.get(i).getTitle());
        holder.tag.setText(mList.get(i).getCreatetime());
    }

    @Override
    public int getItemCount() {
        return mList.size()>0?mList.size():0;
    }

    public void addData(List<MsgEntity.MessageListBean.ListBean> objects) {
        mList.clear();
        mList.addAll(objects);
        notifyDataSetChanged();
    }

    public void addNewData(List<MsgEntity.MessageListBean.ListBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addBean(MsgEntity.MessageListBean.ListBean listBean) {
        mList.clear();
        mList.add(listBean);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView desc;
        private final TextView tag;
        private final TextView desc2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            desc = itemView.findViewById(R.id.desc1);
            desc2 = itemView.findViewById(R.id.desc2);
            tag = itemView.findViewById(R.id.tag);
        }
    }
}

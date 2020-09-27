package com.edencity.store.user.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.entity.BaseDebug;


// Created by Ardy on 2020/2/25.
public class FeedMsgAdapter extends RecyclerView.Adapter {

    private ArrayList<BaseDebug> mList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i==1){
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feed_msg, viewGroup,false);
            return new ViewHolder1(inflate);
        }else{
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_foot, viewGroup,false);
            return new ViewHolder2(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int itemViewType = getItemViewType(i);

        if (itemViewType==1){
           ViewHolder1 holder = (ViewHolder1) viewHolder;
           holder.desc.setText(mList.get(i).getUrl());
           holder.time.setText(mList.get(i).getSubtitle());
           holder.title.setText(mList.get(i).getTitle());
           if (mList.get(i).isChecked()){
               holder.tag.setVisibility(View.VISIBLE);
           }else{
               holder.tag.setVisibility(View.GONE);
           }
        }else{
            ViewHolder2 holder = (ViewHolder2) viewHolder;
            holder.foot_text.setText("没有更多内容了");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.size()>0){
           if (position > mList.size()-1){
               return 2;
           }else{
               return 1;
           }
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mList.size()==0?0:mList.size()+1;
    }

    public void addData(ArrayList<BaseDebug> objects) {
        mList.clear();
        mList.addAll(objects);
        notifyDataSetChanged();
    }

    public void changeState(boolean isCkeck) {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setChecked(isCkeck);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private final MyMediumTextView title;
        private final TextView tag;
        private final MyNormalTextView time;
        private final TextView desc;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            tag = itemView.findViewById(R.id.tag);
            time = itemView.findViewById(R.id.time_tag);
            desc = itemView.findViewById(R.id.desc);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

        private final MyNormalTextView foot_text;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);

            foot_text = itemView.findViewById(R.id.foot_text);

        }
    }
}

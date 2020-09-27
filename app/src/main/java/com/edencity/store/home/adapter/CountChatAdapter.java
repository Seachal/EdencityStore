package com.edencity.store.home.adapter;

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


// Created by Ardy on 2020/3/3.
public class CountChatAdapter extends RecyclerView.Adapter {

    private ArrayList<BaseDebug> mList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_line, viewGroup,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;


       holder.count.setText(mList.get(i).getTitle());
       holder.count2.setText(mList.get(i).getSubtitle());

        if (mList.get(i).getResource()==0){
            holder.title.setText("营收数据");
            holder.subtitle.setText("实时营收");
            holder.subtitle2.setText("累计营收");
            holder.tag.setText("元");
            holder.tag2.setText("元");
            holder.img.setImageResource(R.drawable.count1);
            holder.img2.setImageResource(R.drawable.count2);

        }else if (mList.get(i).getResource()==1){
            holder.title.setText("营收订单数据");
            holder.subtitle.setText("实时收入订单");
            holder.subtitle2.setText("累计收入订单");
            holder.tag.setText("个");
            holder.tag2.setText("个");
            holder.img.setImageResource(R.drawable.count5);
            holder.img2.setImageResource(R.drawable.count4);
        }else if (mList.get(i).getResource()==2){
            holder.title.setText("客单价数据");
            holder.subtitle.setText("单日客单价");
            holder.subtitle2.setText("平均客单价");
            holder.tag.setText("元");
            holder.tag2.setText("元");
            holder.img.setImageResource(R.drawable.count3);
            holder.img2.setImageResource(R.drawable.count6);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() > 0 ? mList.size() : 0;
    }

    public void addData(ArrayList<BaseDebug> objects) {
        mList.clear();
        mList.addAll(objects);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final MyMediumTextView title;
        private final MyMediumTextView count;
        private final MyNormalTextView subtitle;
        private final ImageView img;

        private final MyNormalTextView subtitle2;
        private final MyMediumTextView count2;
        private final ImageView img2;
        private final MyNormalTextView tag;
        private final MyNormalTextView tag2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.img);
            count = itemView.findViewById(R.id.count);
            subtitle = itemView.findViewById(R.id.subtitle);
            subtitle2 = itemView.findViewById(R.id.subtitle2);
            img2 = itemView.findViewById(R.id.img2);
            count2 = itemView.findViewById(R.id.count2);
            tag = itemView.findViewById(R.id.tag);
            tag2 = itemView.findViewById(R.id.tag2);
        }
    }
}

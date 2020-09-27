package com.edencity.store.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.edencity.store.R;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.RoundImageView15dp;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.entity.HomeBannerEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeBannerAdapter extends RecyclerView.Adapter {

    private  Context mContext;
    private ArrayList<HomeBannerEntity.BannerListBean>mList = new ArrayList<>();
    private onItemClick ono;

    public HomeBannerAdapter(Context activity) {
        this.mContext = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_banner_list, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder viewHolder1 = (ViewHolder) viewHolder;

        Glide.with(mContext).load(mList.get(i).getBannerImg()).into(viewHolder1.item_iv);
        viewHolder1.itemView.setOnClickListener(v -> {
            if (ono!=null){
                ono.onClick(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size()>0?mList.size():0;
    }

    public void addData(List<HomeBannerEntity.BannerListBean> objects) {
        mList.clear();
        mList.addAll(objects);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final RoundImageView15dp item_iv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_iv  = itemView.findViewById(R.id.item_iv);

        }
    }
    public interface onItemClick{
        void onClick(int position);
    }
    public void onClick(onItemClick itemClick){
        ono = itemClick;
    }
    }

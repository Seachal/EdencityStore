package com.edencity.store.home.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edencity.store.R;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;

// Created by Ardy on 2020/3/8.

public class MyReadyAdapter extends RecyclerView.Adapter {

    private int type;
    private onSureClickListener ono;

    public MyReadyAdapter(int type) {
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ready_list, null);
       return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        if (type==0){

            holder.time_tag.setVisibility(View.VISIBLE);
            holder.time_tag.setText("今天16:00");

            holder.ready_layout.setVisibility(View.VISIBLE);
            holder.btn_sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ono!=null){
                        ono.onSure(i);
                    }
                }
            });
            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ono!=null){
                        ono.onCancle(i);
                    }
                }
            });

        }else if (type==1){
            holder.time_tag.setVisibility(View.GONE);
            holder.ready_layout.setVisibility(View.GONE);
            holder.end_layout.setVisibility(View.VISIBLE);
            holder.btn_diss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ono!=null){
                        ono.onDismiss(i);
                    }
                }
            });

        }else if (type==2){
            holder.time_tag.setVisibility(View.GONE);
            holder.ready_layout.setVisibility(View.GONE);
            holder.end_layout.setVisibility(View.GONE);
        }

        holder.name.setText("土豪用户");
        holder.people.setText("12人");
        holder.phone.setText("17630360083");
        holder.time.setText("2月1号 周一 11:08");
        holder.other.setText("各个国家有各个国家的国歌");
    }

    @Override
    public int getItemCount() {
        if (type==0){
            return 8;
        }else if (type==1){
            return 5;
        }
        return 3;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private final MyNormalTextView time_tag;
        private final MyNormalTextView name;
        private final MyNormalTextView phone;
        private final MyNormalTextView people;
        private final MyNormalTextView time;
        private final MyNormalTextView other;
        private final MyMediumTextView btn_diss;
        private final MyMediumTextView btn_sure;
        private final MyMediumTextView btn_cancel;
        private final ConstraintLayout end_layout;
        private final ConstraintLayout ready_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            time_tag = itemView.findViewById(R.id.time_tag);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            people = itemView.findViewById(R.id.people);
            time = itemView.findViewById(R.id.time);
            end_layout = itemView.findViewById(R.id.end_layout);
            ready_layout = itemView.findViewById(R.id.ready_layout);
            other = itemView.findViewById(R.id.other);
            btn_sure = itemView.findViewById(R.id.btn_sure);
            btn_cancel = itemView.findViewById(R.id.btn_cancle);
            btn_diss = itemView.findViewById(R.id.btn_diss);
        }
    }

    public interface onSureClickListener{
        void onSure(int position);
        void onCancle(int position);
        void onDismiss(int position);
    }

    public void onReadyListener(onSureClickListener listener){
        ono = listener ;
    }

}

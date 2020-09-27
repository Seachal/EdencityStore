package com.edencity.store.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edencity.store.R;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.RoundImageView4dp;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.entity.HomeActiveListEntity;
import com.edencity.store.util.DateFormatUtils;
import com.edencity.store.util.ResUtils;

public class HomeListAdapter extends RecyclerView.Adapter {

    private  Context mContext;
    private ArrayList<HomeActiveListEntity.ActivitiesListBean>mList = new ArrayList<>();
    private onItemClick ono;

    public HomeListAdapter(Context activity) {
        this.mContext = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_list, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        viewHolder1.item_title.setText(mList.get(i).getActivitiesName());
        viewHolder1.tv_address.setText("活动地点："+mList.get(i).getActivitiesAddress());
        viewHolder1.tv_shop.setText("主办方："+mList.get(i).getSponsor());

        //设置时间
        long toDate = DateFormatUtils.getStringToDate(mList.get(i).getRunTimeStart(), DateFormatUtils.FORMAT_6);
        String startime = DateFormatUtils.getDateToString(toDate, DateFormatUtils.FORMAT_7);
        long toDate2 = DateFormatUtils.getStringToDate(mList.get(i).getRunTimeEnd(), DateFormatUtils.FORMAT_6);
        String endtime = DateFormatUtils.getDateToString(toDate2, DateFormatUtils.FORMAT_7);

        viewHolder1.tv_time.setText("活动时间："+startime+" - "+endtime);
        Date date = DateFormatUtils.getDateFromString(System.currentTimeMillis() + "");


        if (mList.get(i).getHadSignCount() < mList.get(i).getAllowedNumber()){

            if (getTimeCompareSize(date,mList.get(i).getRunTimeStart())!=3
                    && getTimeCompareSize(date,mList.get(i).getRunTimeEnd())==3
              ){
                viewHolder1.will_end.setVisibility(View.GONE);
                viewHolder1.will_join.setVisibility(View.VISIBLE);
                viewHolder1.will_join.setText("报名中");
            }else if (getTimeCompareSize(date,mList.get(i).getRunTimeEnd())==1){
                Log.d("ssss",date.toString()+"----"+endtime);
                viewHolder1.will_end.setVisibility(View.VISIBLE);
                viewHolder1.will_join.setVisibility(View.GONE);
            }else if (getTimeCompareSize(date,mList.get(i).getRunTimeStart())==3){

                viewHolder1.will_end.setVisibility(View.GONE);
                viewHolder1.will_join.setVisibility(View.VISIBLE);
                viewHolder1.will_join.setText("未开始");
                viewHolder1.will_join.setTextColor(Color.parseColor("#666666"));
            }
        }else{
            viewHolder1.will_end.setVisibility(View.GONE);
            viewHolder1.will_join.setVisibility(View.VISIBLE);
            viewHolder1.will_join.setText("报名人数已满");
        }
        Glide.with(mContext).load(mList.get(i).getCoverImg()).into(viewHolder1.item_iv);
        viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ono!=null){
                    ono.onClick(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size()>0?mList.size():0;
    }

    public void addData(List<HomeActiveListEntity.ActivitiesListBean> objects) {
        mList.clear();
        mList.addAll(objects);
        notifyDataSetChanged();

    }
    public void addNewData(List<HomeActiveListEntity.ActivitiesListBean> objects) {
        mList.addAll(objects);
        notifyDataSetChanged();
    }
    public static int getTimeCompareSize(Date startTime, String endTime){
        int i=0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//年-月-日 时-分

        Date date2 = null;//结束时间
        try {
            date2 = dateFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime()<startTime.getTime()){
                i= 1;
            }else if (date2.getTime()==startTime.getTime()){
                i= 2;
            }else if (date2.getTime()>startTime.getTime()) {
                //正常情况下的逻辑操作.
                i = 3;
            }

        return  i;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final RoundImageView4dp item_iv;
        private final MyMediumTextView item_title;
        private final MyNormalTextView tv_address;
        private final MyNormalTextView tv_time;
        private final MyNormalTextView tv_shop;
        private final MyNormalTextView will_join;
        private final RelativeLayout will_end;
        /*private final RelativeLayout will_end2;*/

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_iv  = itemView.findViewById(R.id.item_iv);
            item_title= itemView.findViewById(R.id.item_title);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_shop = itemView.findViewById(R.id.tv_shop);
            will_join = itemView.findViewById(R.id.will_join);
            will_end = itemView.findViewById(R.id.will_state_end);
            /*will_end2 = itemView.findViewById(R.id.will_state_end2);*/
        }
    }
    public interface onItemClick{
        void onClick(int position);
    }
    public void onClick(onItemClick itemClick){
        ono = itemClick;
    }
    }

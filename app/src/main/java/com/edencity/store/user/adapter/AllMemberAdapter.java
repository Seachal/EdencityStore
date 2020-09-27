package com.edencity.store.user.adapter;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.edencity.store.R;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.entity.AllMemberEntity;
import com.edencity.store.util.ResUtils;

import java.util.ArrayList;
import java.util.List;

// Created by Ardy on 2020/3/28.
public class AllMemberAdapter extends RecyclerView.Adapter {

    private ArrayList<AllMemberEntity.AllMembershipRightsBean> mList = new ArrayList<>();
    private String type;

    public AllMemberAdapter(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mamber, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
       ViewHolder holder = (ViewHolder) viewHolder;
        AllMemberEntity.AllMembershipRightsBean bean = mList.get(i);
       /* if (bean.getRightsType() . equals(type)){
            holder.img2.setVisibility(View.GONE);
       }else{
            holder.img2.setVisibility(View.VISIBLE);
        }*/
        boolean a = true;
        String configMemberId = bean.getConfigMemberId();
        switch (type){
            case "1":
                if (configMemberId!=null && !configMemberId.equals("")){
                    if (configMemberId.contains(",")){
                        String[] split = configMemberId.split(",");
                        for (int j = 0; j < split.length; j++) {
                            String s = split[j];
                            if (s.equals("1002010730")){
                                a = false;
                            }
                        }
                    }else{
                        if (configMemberId.equals("1002010730")){
                            a = false;
                        }
                    }
                }
                break;
            case "2":
                if (configMemberId!=null && !configMemberId.equals("")){
                    if (configMemberId.contains(",")){
                        String[] split = configMemberId.split(",");
                        for (int j = 0; j < split.length; j++) {
                            String s = split[j];
                            if (s.equals("1002010731")){
                                a = false;
                            }
                        }
                    }else{
                        if (configMemberId.equals("1002010731")){
                            a = false;
                        }
                    }
                }
                break;
            case "3":
                if (configMemberId!=null && !configMemberId.equals("")){
                    if (configMemberId.contains(",")){
                        String[] split = configMemberId.split(",");
                        for (int j = 0; j < split.length; j++) {
                            String s = split[j];
                            if (s.equals("1002010732")){
                                a = false;
                            }
                        }
                    }else{
                        if (configMemberId.equals("1002010732")){
                            a = false;
                        }
                    }
                }
                break;
        }
        String rightsIcon = bean.getRightsIcon();
        String rightsIconInvisible = bean.getRightsIconInvisible();

        /*byte [] input = Base64.decode(rightsIcon, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(input, 0, input.length);
       */
        RequestOptions error = RequestOptions.centerCropTransform().error(R.drawable.yellow_text);

        if (a){
            /*ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0f); // 设置饱和度:0为纯黑白，饱和度为0；1为饱和度为100，即原图；
            ColorMatrixColorFilter grayColorFilter = new ColorMatrixColorFilter(cm);
            holder.img.setColorFilter(grayColorFilter);*/
            Glide.with(holder.img.getContext()).load(rightsIconInvisible).apply(error).into(holder.img);
        }else{
            /*mList.set(0,bean);*/
            Glide.with(holder.img.getContext()).load(rightsIcon).apply(error).into(holder.img);
        }
        holder.name.setText(bean.getRightsName());
    }

    @Override
    public int getItemCount() {
        return mList.size() > 0 ? mList.size() : 0 ;
    }

    public void addData(List<AllMemberEntity.AllMembershipRightsBean> allMembershipRights) {
        mList.clear();
        mList.addAll(allMembershipRights);
        notifyDataSetChanged();
    }

    public int getItemType(String right) {
        int a = 0 ;
        for (int i = 0; i < mList.size() ; i++) {
            AllMemberEntity.AllMembershipRightsBean bean = mList.get(i);
           switch (right){
               case "1":
                   String configMemberId = bean.getConfigMemberId();
                   if (configMemberId!=null && !configMemberId.equals("")){
                       if (configMemberId.contains(",")){
                           String[] split = configMemberId.split(",");
                           for (int j = 0; j < split.length; j++) {
                               String s = split[j];
                               if (s.equals("1002010730")){
                                   a++;
                               }
                           }
                       }else{
                           if (configMemberId.equals("1002010730")){
                               a++;
                           }
                       }
                   }
                   break;
               case "2":
                   String configMemberId2 = bean.getConfigMemberId();
                   if (configMemberId2!=null && !configMemberId2.equals("")){
                       if (configMemberId2.contains(",")){
                           String[] split = configMemberId2.split(",");
                           for (int j = 0; j < split.length; j++) {
                               String s = split[j];
                               if (s.equals("1002010731")){
                                   a++;
                               }
                           }
                       }else{
                           if (configMemberId2.equals("1002010731")){
                               a++;
                           }
                       }
                   }
                   break;
               case "3":
                   String configMemberId3 = bean.getConfigMemberId();
                   if (configMemberId3!=null && !configMemberId3.equals("")){
                       if (configMemberId3.contains(",")){
                           String[] split = configMemberId3.split(",");
                           for (int j = 0; j < split.length; j++) {
                               String s = split[j];
                               if (s.equals("1002010732")){
                                   a++;
                               }
                           }
                       }else{
                           if (configMemberId3.equals("1002010732")){
                               a++;
                           }
                       }
                   }
                   break;
           }
        }
        return a;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final ImageView img2;
        private final MyNormalTextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            img2 = itemView.findViewById(R.id.img2);
            name = itemView.findViewById(R.id.name);
        }
    }
}

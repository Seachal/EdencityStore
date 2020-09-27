package com.edencity.store.user.fragment;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.base.BaseDialog;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.flowlayout.FlowLayout;
import com.edencity.store.custum.flowlayout.TagAdapter;
import com.edencity.store.custum.flowlayout.TagFlowLayout;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.user.adapter.TimeAdapter;
import com.edencity.store.user.adapter.WeekAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserShopFragment extends BaseFragment2 {


    private ImageView back;
    private ImageView shop_img;
    private MyNormalTextView shop_name;
    private MyNormalTextView shop_type;
    private MyNormalTextView shop_address;
    private MyNormalTextView address_detail;
    private RadioGroup radio;
    private MyNormalTextView shop_time;
    private MyNormalTextView chat_us;
    private MyMediumTextView save;
    private TagFlowLayout flv;
    private BaseDialog baseDialog;

    public UserShopFragment() {
        // Required empty public constructor
    }

    public static UserShopFragment newInstance() {
        return new UserShopFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_user_shop, container, false);
        initView(inflate);
        initLogic();
        return inflate;
    }

    private void initLogic() {

        //流式布局
        ArrayList<BaseDebug> objects = new ArrayList<>();
        objects.add(new BaseDebug("可停车",true));
        objects.add(new BaseDebug("可拼桌",false));
        objects.add(new BaseDebug("宝宝椅",false));
        objects.add(new BaseDebug("吸烟区",true));
        objects.add(new BaseDebug("有景观位",false));        objects.add(new BaseDebug("可自助点餐",false));
        objects.add(new BaseDebug("WIFI",false));
        objects.add(new BaseDebug("可刷卡",true));
        objects.add(new BaseDebug("有包间",false));
        objects.add(new BaseDebug("充电宝",false));
        objects.add(new BaseDebug("午式套餐",false));

        flv.setAdapter(new TagAdapter<BaseDebug>(objects) {
            @Override
            public View getView(FlowLayout parent, int position, BaseDebug baseDebug) {
                MyNormalTextView inflate = (MyNormalTextView)LayoutInflater.from(getContext()).inflate(R.layout.tag_tv, parent, false);
                inflate.setText(baseDebug.getTitle());
                return inflate;
            }
        });
        flv.setMaxSelectCount(-1);

        //营业时间
        shop_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialkog();
            }
        });

    }

    private void showTimeDialkog() {
        ArrayList<BaseDebug> objects = new ArrayList<>();
        objects.add(new BaseDebug("周一",false));
        objects.add(new BaseDebug("周二",false));
        objects.add(new BaseDebug("周三",false));
        objects.add(new BaseDebug("周四",true));
        objects.add(new BaseDebug("周五",false));
        objects.add(new BaseDebug("周六",true));
        objects.add(new BaseDebug("周日",false));
        objects.add(new BaseDebug("",false));
        objects.add(new BaseDebug("",false));

        ArrayList<BaseDebug> objects2 = new ArrayList<>();
        objects.add(new BaseDebug("01:00-02:00",false));
        objects.add(new BaseDebug("02:00-03:00",false));
        objects.add(new BaseDebug("03:00-04:00",false));
        objects.add(new BaseDebug("04:00-05:00",true));
        objects.add(new BaseDebug("05:00-06:00",false));
        objects.add(new BaseDebug("06:00-07:00",true));
        objects.add(new BaseDebug("07:00-08:00",false));
        objects.add(new BaseDebug("08:00-09:00",false));
        objects.add(new BaseDebug("09:00-10:00",false));
        objects.add(new BaseDebug("10:00-11:00",false));
        objects.add(new BaseDebug("11:00-12:00",false));
        objects.add(new BaseDebug("12:00-13:00",false));
        objects.add(new BaseDebug("13:00-14:00",false));
        objects.add(new BaseDebug("14:00-15:00",false));
        objects.add(new BaseDebug("15:00-16:00",false));
        objects.add(new BaseDebug("16:00-17:00",false));
        objects.add(new BaseDebug("17:00-18:00",false));
        objects.add(new BaseDebug("18:00-19:00",false));
        objects.add(new BaseDebug("19:00-20:00",false));
        objects.add(new BaseDebug("20:00-21:00",false));
        objects.add(new BaseDebug("21:00-22:00",false));
        objects.add(new BaseDebug("22:00-23:00",false));
        objects.add(new BaseDebug("23:00-24:00",false));
        objects.add(new BaseDebug("24:00-01:00",false));


        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_time, null);
        RecyclerView rlv_week = inflate.findViewById(R.id.rlv_week);
        ConstraintLayout dio = inflate.findViewById(R.id.ddd);
        RecyclerView rlv_time = inflate.findViewById(R.id.rlv_time);
        MyMediumTextView sure =  inflate.findViewById(R.id.sure);
        MyMediumTextView cancle = inflate.findViewById(R.id.cancle);

        //星期列表
        rlv_week.setLayoutManager(new GridLayoutManager(getContext(),3));
        WeekAdapter weekAdapter = new WeekAdapter();
        rlv_week.setAdapter(weekAdapter);
        weekAdapter.addData(objects);
        weekAdapter.select(new WeekAdapter.onSelect() {
            @Override
            public void select(int position) {
                if (weekAdapter.mList.get(position).isChecked()){
                    weekAdapter.mList.get(position).setChecked(false);
                }else{
                    weekAdapter.mList.get(position).setChecked(true);
                }
                weekAdapter.notifyItemChanged(position);
            }
        });
        //时间列表
        rlv_time.setLayoutManager(new GridLayoutManager(getContext(),3));
        TimeAdapter timeAdapter = new TimeAdapter();
        rlv_time.setAdapter(timeAdapter);
        timeAdapter.addData(objects2);

        timeAdapter.select(new TimeAdapter.onSelect() {
            @Override
            public void select(int position) {
                if (timeAdapter.mList.get(position).isChecked()){
                    timeAdapter.mList.get(position).setChecked(false);
                }else{
                    timeAdapter.mList.get(position).setChecked(true);
                }
                timeAdapter.notifyItemChanged(position);
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.dismiss();
            }
        });cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.dismiss();
            }
        });dio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.dismiss();
            }
        });
        baseDialog = new BaseDialog(getContext(), inflate, Gravity.BOTTOM);
        baseDialog.show();
    }

    private void initView(View inflate) {

        back = inflate.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        shop_img = inflate.findViewById(R.id.shop_img);
        shop_name = inflate.findViewById(R.id.shop_name);
        shop_type = inflate.findViewById(R.id.shop_type);
        shop_address = inflate.findViewById(R.id.shop_address);
        address_detail = inflate.findViewById(R.id.address_detail);
        radio = inflate.findViewById(R.id.radio);
        shop_time = inflate.findViewById(R.id.shop_time);
        flv = inflate.findViewById(R.id.flv);
        save = inflate.findViewById(R.id.save);
        chat_us = inflate.findViewById(R.id.chat_us);
    }
}

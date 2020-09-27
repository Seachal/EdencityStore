package com.edencity.store.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.BillDetailEntity;
import com.edencity.store.entity.CountBillEntity;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.user.activity.CountDetailActivity;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BillDetailActivity extends BaseActivity {

    private ImageView mBack;
    private ImageView mUserHead;
    /**
     * 伊甸园亚当
     */
    private MyMediumTextView mUserName;
    /**
     * +25.00
     */
    private MyMediumTextView mPrice;
    /**
     * 收入
     */
    private MyMediumTextView mType;
    /**
     * 2019.12.18 13:58:16
     */
    private MyMediumTextView mPayTime;
    /**
     * 291241054189234u12421
     */
    private MyMediumTextView mOrderCount;
    /**
     * 二维码收款
     */
    private MyMediumTextView mOther;
    /**
     * 预付卡
     */
    private MyMediumTextView mPayType;
    private MyNormalTextView mPayState;
    private String recordid;
    private String sourcetype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        StatusBarCompat.changeToLightStatusBar(this);
        setContentView(R.layout.activity_bill_detail);
        Intent intent = getIntent();
        if (intent!=null){
             recordid = intent.getStringExtra("recordid");
             sourcetype = intent.getStringExtra("sourcetype");
        }
        initView();
        getData();
    }

    private void getData() {

        HashMap paramsMap = ParamsUtils.getParamsMap("sourceRecordId", recordid,
                "sourceType", sourcetype);
        String sign = ParamsUtils.getSign(paramsMap);
        try {
            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataService.getUserService().getBillDetail(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<BillDetailEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<BillDetailEntity> o) {
                        Log.d("feed", "----" + o.toString());
                        if (o.getResult_code() == 0 && o.getData()!=null && o.getData().getAccountBillDetail()!=null) {
                            BillDetailEntity.AccountBillDetailBean billDetail = o.getData().getAccountBillDetail();
                            RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.user_head).circleCrop();
                            Glide.with(BillDetailActivity.this).load(billDetail.getUserPortrait()).apply(requestOptions).into(mUserHead);

                            mUserName.setText(billDetail.getNickName());

                            mPayTime.setText(billDetail.getDealtime());
                            mType.setText(billDetail.getBillType());
                            mPayType.setText(billDetail.getPayType());
                            mPrice.setText(billDetail.getDealAmount()+"");
                            mPayState.setText("交易成功");
                            mOrderCount.setText(billDetail.getOrderId());
                            mOther.setText(billDetail.getPayMethod());
                        }else if (o.getResult_code()== -3){
                            AdiUtils.loginOut();
                        }else {
                            AdiUtils.showToast(o.getResult_msg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mUserHead = (ImageView) findViewById(R.id.user_head);
        mUserName = (MyMediumTextView) findViewById(R.id.user_name);
        mPrice = (MyMediumTextView) findViewById(R.id.all_price);
        mType = (MyMediumTextView) findViewById(R.id.type);
        mPayTime = (MyMediumTextView) findViewById(R.id.pay_time);
        mOrderCount = (MyMediumTextView) findViewById(R.id.order_count);
        mOther = (MyMediumTextView) findViewById(R.id.other);
        mPayType = (MyMediumTextView) findViewById(R.id.pay_type);
        mPayState =  findViewById(R.id.state);
    }
}

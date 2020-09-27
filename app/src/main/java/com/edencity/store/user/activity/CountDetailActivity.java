package com.edencity.store.user.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.entity.CountBillEntity;
import com.edencity.store.entity.CountHistoryEntity;
import com.edencity.store.util.DateFormatUtils;
import com.edencity.store.util.ResUtils;

public class CountDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    /**
     * 1000.00
     */
    private MyMediumTextView mPrice;
    /**
     * 中国工商银行
     */
    private MyNormalTextView mBankName;
    /**
     * 结算成功
     */
    private MyNormalTextView mState;
    /**
     * 结算
     */
    private MyNormalTextView mCountType;
    /**
     * 999.99
     */
    private MyNormalTextView mTotalReceive;
    /**
     * 提出申请
     */
    private MyNormalTextView mState1;
    /**
     * 12.09 20.09
     */
    private MyNormalTextView mStatetime1;
    /**
     * 12.10 15:30
     */
    private MyNormalTextView mStatetime2;
    /**
     * 成功打款
     */
    private MyNormalTextView mState2;
    /**
     * 中国工商银行（3209）猫先生
     */
    private MyNormalTextView mCreateAt;
    /**
     * 2019.12.18 13:53:02
     */
    private MyNormalTextView mCreateTime;
    /**
     * 20419759815713923
     */
    private MyNormalTextView mOrderNumber;
    /**
     * 我是备注内容
     */
    private MyNormalTextView mOther;
    private View line1;
    private View line2;
    private ImageView img1;
    private ImageView img2;
    private CountBillEntity bean;
    private CountHistoryEntity.AccountBillsBean.ListBean bean2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        StatusBarCompat.changeToLightStatusBar(this);
        setContentView(R.layout.activity_count_detail);
        Intent intent = getIntent();
        initView();
        if (intent!=null ){
            if (intent.getStringExtra("type").equals("1")){
                bean = (CountBillEntity)intent.getSerializableExtra("id");
                initData();
            }else{
                bean2 = (CountHistoryEntity.AccountBillsBean.ListBean)intent.getSerializableExtra("id2");
                initData2();
            }

        }

    }

    private void initData2() {

        mPrice.setText(bean2.getBalanceAmount()+"");
        mTotalReceive.setText(bean2.getActualAmount()+"");
        mBankName.setText(bean2.getBalanceBank());
        switch (bean2.getBalanceState()){
            case "1010011501":
                mState.setText("待结算");
                break;
            case "1010011502":
                mState.setText("结算中");
                break;
            case "1010011503":
                mState.setText("已结算");
                break;
            case "1010011504":
                mState.setText("不予结算");
                break;
        }

        if (bean2.getOperatetime()!=null){
            mState2.setVisibility(View.VISIBLE);
            mStatetime2.setVisibility(View.VISIBLE);
            mStatetime2.setText(bean2.getOperatetime());
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);
            if (bean2.getBalanceState().equals("1010011503")){
                img2.setBackgroundResource(R.mipmap.sele);
                line2.setBackgroundColor(ResUtils.getColor(this,R.color.blue_nomal));
            }else if (bean2.getBalanceState().equals("1010011502")){
                mState2.setVisibility(View.GONE);
                mStatetime2.setVisibility(View.GONE);
                line2.setVisibility(View.GONE);
                line1.setVisibility(View.GONE);
                img2.setVisibility(View.GONE);
            }else{
                mState2.setText("打款失败");
                img2.setBackgroundResource(R.mipmap.unsele);
                line2.setBackgroundColor(Color.parseColor("#C9C8C8"));
                mState2.setTextColor(Color.parseColor("#C9C8C8"));
            }
        }else{
            mState2.setVisibility(View.GONE);
            mStatetime2.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
            img2.setVisibility(View.GONE);
        }

        mStatetime1.setText(bean2.getCreatetime());
        mCreateAt.setText(bean2.getBalanceBank()+bean2.getBankCardHolder());
        mOrderNumber.setText(bean2.getBalanceNo());
        mOther.setText(bean2.getNote()==null || bean2.getNote().equals("")?"暂无":bean2.getNote());
    }

    private void initData() {

        CountBillEntity.BalanceDetailBean bean1 = bean.getBalanceDetail();
        mPrice.setText(bean1.getBalanceAmount()+"");
        mTotalReceive.setText(bean1.getActualAmount()+"");
        if (bean1.getBalanceBank()!=null && !bean1.getBalanceBank().equals("")){
            if (bean1.getBankCardNumber()!=null && !bean1.getBankCardNumber().equals("")){
                String bankCardNumber = bean1.getBankCardNumber();
                String substring = bankCardNumber.substring(bankCardNumber.length() - 4);
                mBankName.setText(bean1.getBalanceBank()+"("+substring+")");
            }else{
                mBankName.setText(bean1.getBalanceBank());
            }
        }else{
            mBankName.setText("中国工商银行");
        }

        switch (bean1.getBalanceState()){
            case "1010011501":
                mState.setText("待结算");
               break;
                case "1010011502":
                    mState.setText("结算中");
               break;
                case "1010011503":
                    mState.setText("已结算");
                break;
                case "1010011504":
                    mState.setText("不予结算");
                break;
        }

        if (bean1.getOperatetime()!=null){
            mState2.setVisibility(View.VISIBLE);
            mStatetime2.setVisibility(View.VISIBLE);
            mStatetime2.setText(bean1.getOperatetime());

        }else{
            mState2.setVisibility(View.GONE);
            mStatetime2.setVisibility(View.GONE);
        }
        long time = bean1.getCreatetime().getTime();
        String dateToString = DateFormatUtils.getDateToString(time, DateFormatUtils.FORMAT_6);
        mStatetime1.setText(dateToString);

        mCreateAt.setText(bean1.getBankCardHolder()==null || bean1.getBankCardHolder().equals("")?bean1.getBankCardNumber():bean1.getBankCardHolder());
        mOrderNumber.setText(bean1.getBalanceNo());
        mOther.setText(bean1.getNote().equals("")?"暂无":bean1.getNote());
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mPrice = (MyMediumTextView) findViewById(R.id.all_price);
        mBankName = (MyNormalTextView) findViewById(R.id.bank_name);
        mState = (MyNormalTextView) findViewById(R.id.state);
        mCountType = (MyNormalTextView) findViewById(R.id.count_type);
        mTotalReceive = (MyNormalTextView) findViewById(R.id.total_receive);
        mState1 = (MyNormalTextView) findViewById(R.id.state1);
        mStatetime1 = (MyNormalTextView) findViewById(R.id.statetime1);
        mStatetime2 = (MyNormalTextView) findViewById(R.id.statetime2);
        mState2 = (MyNormalTextView) findViewById(R.id.state2);


        mCreateAt = (MyNormalTextView) findViewById(R.id.create_at);
        mCreateTime = (MyNormalTextView) findViewById(R.id.create_time);
        mOrderNumber = (MyNormalTextView) findViewById(R.id.order_number);
        mOther = (MyNormalTextView) findViewById(R.id.other);
        line1 =  findViewById(R.id.line1);
        img1 =  findViewById(R.id.img1);
        line2 =  findViewById(R.id.line2);


        img2 =  findViewById(R.id.img2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}

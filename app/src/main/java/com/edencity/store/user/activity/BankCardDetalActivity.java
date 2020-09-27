package com.edencity.store.user.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;

public class BankCardDetalActivity extends BaseActivity {

    private ImageView mBack;
    /**
     * 毛先生
     */
    private MyNormalTextView mUserName;
    /**
     * 华夏银行
     */
    private MyNormalTextView mBankName;
    /**
     * 请选择
     */
    private MyNormalTextView mBankSecondName;
    /**
     * *** **** **** 6687
     */
    private MyNormalTextView mCardCode;
    /**
     * *** **** **** 6687
     */
    private MyNormalTextView mSureCardCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.changeToLightStatusBar(this);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        setContentView(R.layout.activity_bank_card_detal);
        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mUserName = (MyNormalTextView) findViewById(R.id.user_name);
        mBankName = (MyNormalTextView) findViewById(R.id.bank_name);
        mBankSecondName = (MyNormalTextView) findViewById(R.id.bank_second_name);
        mCardCode = (MyNormalTextView) findViewById(R.id.card_code);
        mSureCardCode = (MyNormalTextView) findViewById(R.id.sure_card_code);
    }
}

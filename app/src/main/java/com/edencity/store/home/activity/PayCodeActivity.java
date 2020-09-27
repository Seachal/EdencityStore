package com.edencity.store.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.custum.statubar.StatusBarCompat;

public class PayCodeActivity extends BaseActivity {

    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#FBAB43"));
        StatusBarCompat.cancelLightStatusBar(this);
        setContentView(R.layout.activity_pay_code);
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
    }
}

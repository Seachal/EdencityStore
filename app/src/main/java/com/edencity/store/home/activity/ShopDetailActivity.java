package com.edencity.store.home.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.custum.statubar.StatusBarCompat;

public class ShopDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        StatusBarCompat.changeToLightStatusBar(this);
        setContentView(R.layout.activity_shop_detail);
    }
}

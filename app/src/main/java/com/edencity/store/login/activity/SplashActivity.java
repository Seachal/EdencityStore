package com.edencity.store.login.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.base.IBaseCallBack;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.UserMsgEntity;
import com.edencity.store.home.activity.MainActivity;
import com.edencity.store.util.DeeSpUtil;


public class SplashActivity extends BaseActivity {

    /**
     * 登录
     */
    private TextView mLogin;
    /**
     * 注册
     */
    private TextView mRegist;
    private boolean mFirstLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        StatusBarCompat.changeToLightStatusBar(this);
        setContentView(R.layout.activity_splash);
        mFirstLaunch = true;
        initView();

        //检验是否登录
        if (DeeSpUtil.getInstance().getString("userId")!=null && DeeSpUtil.getInstance().getString("ticket")!=null
        &&!DeeSpUtil.getInstance().getString("userId").equals("")&&!DeeSpUtil.getInstance().getString("ticket").equals("")){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }else{
           mLogin.setVisibility(View.VISIBLE);
           mRegist.setVisibility(View.VISIBLE);
        }
    }

/*
    private void syncUser(String userId, String ticket) {
        if (mFirstLaunch) {
            mFirstLaunch = false;
            DataService.getInstance().syncUser(userId, ticket, new IBaseCallBack<UserMsgEntity>() {
                @Override
                public void onSuccess(UserMsgEntity data) {
                    Log.d("splash",data.toString());
                    App.defaultApp().saveUserMsg(data);
                    mLogin.setVisibility(View.GONE);
                    mRegist.setVisibility(View.GONE);
                    mRegist.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, 1000);
                }

                @Override
                public void onFail(String msg) {
                    Log.d("splash", msg);
                    mLogin.setVisibility(View.VISIBLE);
                    mRegist.setVisibility(View.VISIBLE);
                    App.defaultApp().saveUserMsg(null);
                }
            });*/



    private void initView() {
        mLogin = (TextView) findViewById(R.id.login);
        mRegist = (TextView) findViewById(R.id.regist);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        mRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.edencity.store.login.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;


import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.base.BaseEventMsg;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.login.fragment.AmoutAndVerifyLoginFragment;
import com.edencity.store.login.fragment.ForgetPwdFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends BaseActivity {

    private FrameLayout mFrag;
    private FragmentManager manager;

    private int bar_type = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        StatusBarCompat.changeToLightStatusBar(this);
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);
         manager = getSupportFragmentManager();
        initView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (bar_type==2){
            StatusBarCompat.setStatusBarColor(this, Color.WHITE);
            StatusBarCompat.changeToLightStatusBar(this);
        }else if (bar_type==1){

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEventMsg eventMsg){
        String type = eventMsg.getType();
        if (type.equals("forget")){
            addFragment(manager, ForgetPwdFragment.class,R.id.frag,null);
            bar_type = 2 ;
        }else if (type.equals("login")){
            bar_type = 1;
            StatusBarCompat.setStatusBarColor(this, Color.WHITE);
            StatusBarCompat.changeToLightStatusBar(this);
            addFragment(manager, AmoutAndVerifyLoginFragment.class,R.id.frag,null);
        }else{

        }
    }
    private void initView() {
        mFrag = (FrameLayout) findViewById(R.id.frag);
        bar_type = 1 ;
        addFragment(manager, AmoutAndVerifyLoginFragment.class,R.id.frag,null);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

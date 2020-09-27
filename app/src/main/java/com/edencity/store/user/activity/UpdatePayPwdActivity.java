package com.edencity.store.user.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.base.BaseEventMsg;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.user.fragment.UpdatePayPwdFragment1;
import com.edencity.store.user.fragment.UpdatePayPwdFragment2;
import com.edencity.store.user.fragment.UpdatePayPwdFragment3;

public class UpdatePayPwdActivity extends BaseActivity {

    private ImageView mBack;
    private FrameLayout mFrag;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        StatusBarCompat.cancelLightStatusBar(this);
        setContentView(R.layout.activity_update_pay_pwd);
        EventBus.getDefault().register(this);
         fragmentManager = getSupportFragmentManager();
        initView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEventMsg eventMsg){

        if (eventMsg.getType()!=null && eventMsg.getType().equals("pay")){
            if (eventMsg.getMsg()!=null && eventMsg.getMsg().equals("1")){
                Bundle bundle = new Bundle();
                bundle.putString("verify",eventMsg.getParamStr2());
                addFragment(fragmentManager, UpdatePayPwdFragment2.class,R.id.frag,bundle);
            }else if (eventMsg.getMsg()!=null && eventMsg.getMsg().equals("2")){
                Bundle bundle = new Bundle();
                bundle.putString("verify",eventMsg.getParamStr2());
                bundle.putString("vCode",eventMsg.getParamStr());
                addFragment(fragmentManager, UpdatePayPwdFragment3.class,R.id.frag,bundle);
            }else if (eventMsg.getMsg()!=null && eventMsg.getMsg().equals("3")){
                Bundle bundle = new Bundle();
                bundle.putString("verify",eventMsg.getParamStr2());
                addFragment(fragmentManager, UpdatePayPwdFragment2.class,R.id.frag,bundle);
            }
        }

    }


    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mFrag = (FrameLayout) findViewById(R.id.frag);
        addFragment(fragmentManager, UpdatePayPwdFragment1.class,R.id.frag,null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

package com.edencity.store.user.activity;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.data.AppContant;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.RegexUtils;
import com.edencity.store.util.SHA1Utils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UpdateLoginPwdActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    /**
     * 请输入验证码
     */
    private EditText mEtVerify;
    /**
     * 设置新密码
     */
    private EditText mEtSetpwd;
    /**
     * 再次输入新密码
     */
    private EditText mEtSetpwd2;
    /**
     * 二十公分的感受到如果不惹我
     */
    private MyMediumTextView mTvPhone;
    /**
     * 获取验证码
     */
    private MyMediumTextView mBtnGetverify;
    /**
     * 确定
     */
    private MyMediumTextView mBtnNext;
    private View mView11;
    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;

    //还剩多长时间可以重新发送验证码
    private long mLastResendSecond;
    private Timer mResendTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        StatusBarCompat.cancelLightStatusBar(this);
        setContentView(R.layout.activity_update_login_pwd);
        initView();
        initLogic();
    }

    private void initLogic() {

        AdiUtils.showDeleteButton(mEtSetpwd,2,mCheckBox1);
        AdiUtils.showDeleteButton(mEtSetpwd2,2,mCheckBox2);
        String login_phone = (String) App.getCache("login_phone");
        mBtnGetverify.setText(login_phone==null||login_phone.equals("")?"获取验证码":(String)App.getCache("login_phone"));
        //处理重新发送时间
        Object lastSend= App.getCache("login_code_timestamp");
        if (lastSend==null){
            mLastResendSecond = 0;
        }else {
            mLastResendSecond = 60 - (System.currentTimeMillis() - Long.parseLong((String) lastSend))/1000;
            if (mLastResendSecond<=0){
                App.removeCache("login_code_timestamp");
                mLastResendSecond = 0;
            }
        }
        if (mLastResendSecond == 0){
            mBtnGetverify.setEnabled(true);
            mBtnGetverify.setText("获取验证码");
            mTvPhone.setText("验证码即将发送至：" +  RegexUtils.hidePhone(App.userMsg().getProvider().getPhone()) + " ,请注意查收!");
        }else {
            setupResendTimer();
        }
    }


    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mEtVerify = (EditText) findViewById(R.id.et_verify);
        mEtSetpwd = (EditText) findViewById(R.id.et_setpwd);
        mEtSetpwd2 = (EditText) findViewById(R.id.et_setpwd2);
        mTvPhone = (MyMediumTextView) findViewById(R.id.tv_phone);
        mBtnGetverify = (MyMediumTextView) findViewById(R.id.btn_getverify);
        mBtnGetverify.setOnClickListener(this);
        mBtnNext = (MyMediumTextView) findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(this);
        mView11 = (View) findViewById(R.id.view11);
        mCheckBox1 = (CheckBox) findViewById(R.id.checkBox1);
        mCheckBox2 = (CheckBox) findViewById(R.id.checkBox2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                if (mResendTimer!=null){
                    mResendTimer.cancel();
                    mResendTimer=null;
                }
                finish();
                break;
            case R.id.btn_getverify:
                onResendVerifyCode();
                break;
            case R.id.btn_next:

                if (!mEtVerify.getText().toString().equals("")){
                    if (!mEtSetpwd.getText().toString().equals("")||!mEtSetpwd2.getText().toString().equals("")){
                        if (mEtVerify.getText().length()==4){
                            if (AdiUtils.isPassword(mEtSetpwd.getText().toString())){
                                onUpdate();
                            }
                        }else{
                            AdiUtils.showToast("验证码格式不正确");
                        }
                    }else{
                        AdiUtils.showToast("密码不能为空");
                    }
                }else{
                    AdiUtils.showToast("验证码不能为空");
                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mResendTimer!=null){
            mResendTimer.cancel();
            mResendTimer=null;
        }
        super.onBackPressed();
    }

    private void onUpdate() {

        if (mEtSetpwd.getText().toString().equals(mEtSetpwd2.getText().toString())){
            // phone
            // verificationCode
            // password
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("phone", App.userMsg().getProvider().getPhone());
            hashMap.put("verificationCode",  mEtVerify.getText().toString());
            hashMap.put("password", mEtSetpwd.getText().toString());
            hashMap.put("app_id", AppContant.APPID);
            hashMap.put("nonce", "1");
            String sign = ParamsUtils.getSign(hashMap);
            try {
                hashMap.put("sign",SHA1Utils.strToSHA1(sign));
            } catch (Exception e) {
                e.printStackTrace();
            }

            DataService.getService().resetLoginPwd(hashMap)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseResult>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseResult o) {

                            if (o.getResult_code()==0){
                                if (App.userMsg().getProvider().getPassword().equals("true")){
                                    AdiUtils.showToast("修改成功,请重新登录！");
                                    Intent intent = new Intent(UpdateLoginPwdActivity.this, LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    App.defaultApp().saveUserMsg(null);
                                }else{
                                    App.userMsg().getProvider().setPassword("true");
                                    AdiUtils.showToast("登录密码设置成功！");
                                    finish();
                                }


                            }else{
                                AdiUtils.showToast(o.getResult_msg());
                            }

                        }

                        @Override
                        public void onError(Throwable e) {

                            AdiUtils.showToast(e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else{
            AdiUtils.showToast("两次密码不一致！");
        }
    }

    private void onResendVerifyCode() {
        if (mResendTimer!=null){
            return;
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("phone", App.userMsg().getProvider().getPhone());
        hashMap.put("type", "modify");
        hashMap.put("app_id", AppContant.APPID);
        hashMap.put("nonce", "1");
        String sign = ParamsUtils.getSign(hashMap);
        try {
            hashMap.put("sign", SHA1Utils.strToSHA1(sign));

        } catch (Exception e) {
            e.printStackTrace();
        }

        DataService.getService().get(hashMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {
                        if (baseResult.getResult_code() == 0) {
                            mLastResendSecond=60;
                            App.setCache("login_phone",App.userMsg().getProvider().getPhone());
                            App.setCache("login_code_timestamp",String.valueOf(System.currentTimeMillis()));
                            setupResendTimer();
                            AdiUtils.showToast("验证码发送成功");
                            mTvPhone.setText("验证码已发送至：" + RegexUtils.hidePhone(App.userMsg().getProvider().getPhone()));
                        } else {
                            AdiUtils.showToast(baseResult.getResult_msg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        AdiUtils.showToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    private void setupResendTimer(){
        mBtnGetverify.setEnabled(false);
        mBtnGetverify.setText(mLastResendSecond+"S");
        //启动定时器
        mResendTimer = new Timer();
        mResendTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mLastResendSecond -- ;
                if (UpdateLoginPwdActivity.this!=null){
                    if (mLastResendSecond <=0){
                        mResendTimer.cancel();
                        mResendTimer=null;
                        runOnUiThread(()->{

                            mBtnGetverify.setEnabled(true);
                            mBtnGetverify.setText("获取验证码");
                            if (App.userMsg()!=null && App.userMsg().getProvider()!=null){
                                mTvPhone.setText("验证码即将发送至：" + RegexUtils.hidePhone(App.userMsg().getProvider().getPhone()));
                            }
                        });
                    }else {
                        runOnUiThread(()->{
                            if (App.userMsg()!=null && App.userMsg().getProvider()!=null){
                                mBtnGetverify.setText(mLastResendSecond+"S");
                                mTvPhone.setText("验证码已发送至：" +RegexUtils.hidePhone(App.userMsg().getProvider().getPhone()));
                            }
                        });
                    }
                }

            }
        },1000,1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mResendTimer!=null){
            mResendTimer.cancel();
            mResendTimer=null;
        }
    }
}

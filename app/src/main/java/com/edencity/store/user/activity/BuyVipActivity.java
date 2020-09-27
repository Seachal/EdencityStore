package com.edencity.store.user.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.alipay.sdk.app.PayTask;
import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.alipay.PayResult;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.base.BaseDialog;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.data.AppContant;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.AliPayEntity;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.MemberEntity;
import com.edencity.store.entity.WeChatPayEntity;
import com.edencity.store.fragment.ScanPayFragment;
import com.edencity.store.home.activity.MainActivity;
import com.edencity.store.home.adapter.RechargeTypeAdapter;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.ButtonUtils;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;
import com.edencity.store.view.LoadingDialog;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BuyVipActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton mBtnBack;
    /**
     *
     */
    private MyMediumTextView mTitle;
    /**
     * 开通所需金额
     */
    private MyNormalTextView mBlod;
    private RecyclerView mRlv;
    private MyMediumTextView mBtnSubmit;
    private MyNormalTextView mGood;
    private BaseDialog baseDialog;
    private RechargeTypeAdapter rechargeTypeAdapter;
    private IWXAPI wxapi;
    private String sn_ali;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    AdiUtils.showToast("支付成功");
                    finish();
                }else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    AdiUtils.showToast(payResult.getMemo());
                }
            }


        }
    };
    private BaseDialog baseDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this,Color.WHITE);
        StatusBarCompat.changeToLightStatusBar(this);
        setContentView(R.layout.activity_buy_vip);
        initView();
        initData();
    }

    private void initData() {
        ArrayList<BaseDebug> objects = new ArrayList<>();

        objects.add(new BaseDebug("支付宝支付",R.mipmap.check_blue,R.mipmap.check_gray,
                false,R.mipmap.ali_blue,R.mipmap.ali_gay));

        objects.add(new BaseDebug("微信支付",R.mipmap.check_blue,R.mipmap.check_gray,
                true,R.mipmap.wechat_green,R.mipmap.wechat_gray));
        /*objects.add(new BaseDebug("伊甸币支付",R.mipmap.check_blue,R.mipmap.check_gray,
                false,R.mipmap.yidianbi,R.mipmap.ali_gay));*/

        mRlv.setHasFixedSize(true);
        mRlv.setNestedScrollingEnabled(false);
        mRlv.setLayoutManager(new LinearLayoutManager(BuyVipActivity.this));
        rechargeTypeAdapter = new RechargeTypeAdapter(2);
        mRlv.setAdapter(rechargeTypeAdapter);
        rechargeTypeAdapter.addData(objects);
        rechargeTypeAdapter.onItemCheck(new RechargeTypeAdapter.onItemCheckedListener() {
            @Override
            public void onItemCheck(int position) {
                rechargeTypeAdapter.changeState(position);
            }
        });

    }

    private void initView() {
        mBtnBack = (ImageButton) findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(this);
        mTitle = (MyMediumTextView) findViewById(R.id.title);
        mGood = (MyNormalTextView) findViewById(R.id.good);
        mBlod = (MyNormalTextView) findViewById(R.id.blod);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mBtnSubmit = (MyMediumTextView) findViewById(R.id.pay);
        mBtnSubmit.setOnClickListener(this);
        String userVipLevel = App.userMsg().getProvider().getUserVipLevel();
        if (userVipLevel.equals("一级成员")){
            mTitle.setText("成为二级成员");
            mBtnSubmit.setText("立即开通");
            initLevel("1002010731");
        }else {
            mTitle.setText("续费二级成员");
            mBtnSubmit.setText("立即续费");
            initLevel("1002010731");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.btn_back)){
                    finish();
                }
                break;
            case R.id.pay:
                if (!ButtonUtils.isFastDoubleClick(R.id.btn_back)){
                    if (App.userMsg()!=null && App.userMsg().getProvider()!=null &&
                            App.userMsg().getProvider().getProviderStatus()!=null){
                        if (App.userMsg().getProvider().getProviderStatus().equals("1002010716") || App.userMsg().getProvider().getProviderStatus().equals("1002010710")){
                            int checkedPosition = rechargeTypeAdapter.getCheckedPosition();
                            goToPay(checkedPosition);
                        }else{
                            initDialog();
                        }
                    }

                }
                break;
        }
    }

    private void initDialog() {
        View inflate = LayoutInflater.from(BuyVipActivity.this).inflate(R.layout.dialog_notifation, null);
        MyNormalTextView desc = inflate.findViewById(R.id.desc);
        MyMediumTextView ok = inflate.findViewById(R.id.ok);

        desc.setText("您的门店还没通过审核！");

        ok.setOnClickListener(v -> baseDialog2.dismiss());
        baseDialog2 = new BaseDialog(BuyVipActivity.this, inflate, Gravity.CENTER);
        baseDialog2.show();
    }


    private void goToPay(int checkedPosition) {
        switch (checkedPosition){
            case 0:
                //支付宝

                if (App.userMsg().getProvider().getUserVipLevel().equals("一级成员")){
                    onAliPay();
                }else {
                    onAliRePay();
                }
                break;
            case 1:
                //微信
                if (App.userMsg().getProvider().getUserVipLevel().equals("一级成员")){
                    onWeixinPay();
                }else {
                    onWeixinRePay();
                }

                break;
         /*   case 2:
                //伊甸币
                break;*/
        }
    }

    private void onAliRePay() {
        LoadingDialog.showLoading(getSupportFragmentManager());
        HashMap hashMap = ParamsUtils.getParamsMap();
        String sign = ParamsUtils.getSign(hashMap);
        try {
            hashMap.put("sign", SHA1Utils.strToSHA1(sign));

            Log.e("card",hashMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataService.getUserService().aliRePay(hashMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<AliPayEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<AliPayEntity> baseResult) {
                        LoadingDialog.hideLoading();
                        Log.e("card", baseResult.toString());
                        if (baseResult.getResult_code() == 0) {
                            sn_ali = baseResult.getData().getPrePayMessage();

                            Runnable payRunnable = () -> {
                                PayTask alipay = new PayTask(BuyVipActivity.this);
                                Map<String, String> result = alipay.payV2(sn_ali, true);
                                Message msg = new Message();
                                msg.what = 0;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            };

                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        }else if (baseResult.getResult_code()== -3){
                            AdiUtils.loginOut();
                        } else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("card", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void onWeixinRePay() {
        LoadingDialog.showLoading(getSupportFragmentManager());

        HashMap hashMap = ParamsUtils.getParamsMap();
        String sign = ParamsUtils.getSign(hashMap);
        try {
            hashMap.put("sign", SHA1Utils.strToSHA1(sign));

            Log.e("card",hashMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataService.getUserService().wechatRePay(hashMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<WeChatPayEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<WeChatPayEntity> baseResult) {
                        LoadingDialog.hideLoading();
                        Log.e("card", baseResult.toString());
                        if (baseResult.getResult_code() == 0) {
                            WeChatPayEntity data = baseResult.getData();
                            if (!App.mWxApi.isWXAppInstalled()){
                                AdiUtils.showToast("请您先安装微信，然后才能使用微信支付");
                            }else{

                                wxapi = WXAPIFactory.createWXAPI(BuyVipActivity.this, null);
                                wxapi.registerApp(AppContant.WXAPPID);
                                PayReq req = new PayReq();
                                //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                                req.appId			= data.getAppid();
                                req.partnerId		= data.getMch_id();
                                req.prepayId		=  data.getPrepay_id();
                                req.nonceStr		= data.getNonce_str();
                                req.timeStamp		= data.getTimestamp();
                                //默认
                                req.packageValue	= "Sign=WXPay";
                                req.sign			= data.getSign();
                                wxapi.sendReq(req);

                            }

                        }else if (baseResult.getResult_code()== -3){
                            AdiUtils.loginOut();
                        } else {
                            AdiUtils.showToast("支付失败，"+(baseResult.getResult_msg()==null?"请您重试":baseResult.getResult_msg()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("card", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initLevel(String type) {
        HashMap paramsMap = ParamsUtils.getParamsMapWithNoId("memberType",type);
        String sign = ParamsUtils.getSign(paramsMap);
        try {

            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataService.getUserService().getMemberShipDetail(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<MemberEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<MemberEntity> o) {
                        Log.e("mem",o.toString());
                        if (o.getResult_code()==0 &&
                                o.getData()!=null &&
                                o.getData().getMembershipDetail()!=null){
                            mBlod.setText("开通所需金额："+o.getData().getMembershipDetail().getPayLimit());
                            mGood.setText("权益："+o.getData().getMembershipDetail().getRightsName());
                        }else if (o.getResult_code()== -3){
                            AdiUtils.loginOut();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("mem",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 进行支付
     */
    private void onWeixinPay(){
        LoadingDialog.showLoading(getSupportFragmentManager());

        HashMap hashMap = ParamsUtils.getParamsMap();
        String sign = ParamsUtils.getSign(hashMap);
        try {
            hashMap.put("sign", SHA1Utils.strToSHA1(sign));

            Log.e("card",hashMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataService.getUserService().wechatPay(hashMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<WeChatPayEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<WeChatPayEntity> baseResult) {
                        LoadingDialog.hideLoading();
                        Log.e("card", baseResult.toString());
                        if (baseResult.getResult_code() == 0) {
                            WeChatPayEntity data = baseResult.getData();


                            if (!App.mWxApi.isWXAppInstalled()){
                                AdiUtils.showToast("请您先安装微信，然后才能使用微信支付");
                            }else{

                                wxapi = WXAPIFactory.createWXAPI(BuyVipActivity.this, null);
                                wxapi.registerApp(AppContant.WXAPPID);
                                PayReq req = new PayReq();
                                //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                                req.appId			= data.getAppid();
                                req.partnerId		= data.getMch_id();
                                req.prepayId		=  data.getPrepay_id();
                                req.nonceStr		= data.getNonce_str();
                                req.timeStamp		= data.getTimestamp();
                                //默认
                                req.packageValue	= "Sign=WXPay";
                                req.sign			= data.getSign();
                                wxapi.sendReq(req);

                            }

                        } else if (baseResult.getResult_code()== -3){
                            AdiUtils.loginOut();
                        }else {
                            AdiUtils.showToast("支付失败，"+(baseResult.getResult_msg()==null?"请您重试":baseResult.getResult_msg()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("card", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void onAliPay(){
        LoadingDialog.showLoading(getSupportFragmentManager());
        HashMap hashMap = ParamsUtils.getParamsMap();
        String sign = ParamsUtils.getSign(hashMap);
        try {
            hashMap.put("sign", SHA1Utils.strToSHA1(sign));

            Log.e("card",hashMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataService.getUserService().aliPay(hashMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<AliPayEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<AliPayEntity> baseResult) {
                        LoadingDialog.hideLoading();
                        Log.e("card", baseResult.toString());
                        if (baseResult.getResult_code() == 0) {
                            sn_ali = baseResult.getData().getPrePayMessage();
                            Runnable payRunnable = () -> {
                                PayTask alipay = new PayTask(BuyVipActivity.this);
                                Map<String, String> result = alipay.payV2(sn_ali, true);
                                Message msg = new Message();
                                msg.what = 0;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            };
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        } else if (baseResult.getResult_code()== -3){
                            AdiUtils.loginOut();
                        }else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("card", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}

package com.edencity.store.base;



import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.HashMap;

import com.edencity.store.R;
import com.edencity.store.data.AppContant;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.login.activity.RegisterActivity;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.edencity.store.base.BaseActivity.TODO;

public abstract class BaseFragment extends RxFragment {

    private BaseActivity mBaseActivity;

    private String TAG;


    public BaseFragment() {
        TAG = getClass().getSimpleName();
    }

    public int enter() {
        if (!isNeedAnimation()) {
            return 0;
        }
        return R.anim.common_page_right_in;
    }

    public int exit() {
        if (!isNeedAnimation()) {
            return 0;
        }
        return R.anim.common_page_left_out;
    }

    public int popEnter() {
        if (!isNeedAnimation()) {
            return 0;
        }
        return R.anim.common_page_left_in;
    }

    public int popExit() {
        if (!isNeedAnimation()) {
            return 0;
        }
        return R.anim.common_page_right_out;
    }


    public boolean isNeedAnimation() {
        return true;
    }

    protected boolean isNeedToAddBackStack() {
        return true;
    }

    protected void onActivityBackPress(){
        getActivity().onBackPressed();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), container, false);

        if (v instanceof FrameLayout || !isNeedShowLoadingPage()) {
            return v;
        }

        FrameLayout frameLayout = new FrameLayout(getContext());

        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        frameLayout.addView(v);


        return frameLayout;
    }

    abstract protected int getLayoutId();



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) activity;
        }
    }


    protected BaseFragment addFragment(FragmentManager manager, Class<? extends BaseFragment> aClass, int containerId, Bundle args) {
        if (mBaseActivity != null) {
            return mBaseActivity.addFragment(manager, aClass, containerId, args);
        }
        return null;
    }





    protected String getLogTag() {
        return TAG;
    }

    protected boolean isNeedShowLoadingPage() {
        return true;
    }




    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return TODO;
        }
        String imei = telephonyManager.getDeviceId();

        return imei;
    }
    public void initVerify(String type, String phone , IBaseCallBack<String> callBack) {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        hashMap.put("type", type);
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
                            callBack.onSuccess("验证码发送成功");

                            /*AdiUtils.showToast("验证码发送成功");*/
                        }else if (baseResult.getResult_code()==-7){
                            AdiUtils.showToast("该手机号不是商家用户，请先注册");
                           /* Intent intent = new Intent(getContext(), RegisterActivity.class);
                            startActivity(intent);*/
                        }else {
                            callBack.onFail(baseResult.getResult_msg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

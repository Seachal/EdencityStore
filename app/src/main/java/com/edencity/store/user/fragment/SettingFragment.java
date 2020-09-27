package com.edencity.store.user.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edencity.store.App;
import com.edencity.store.base.BaseDialog;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.R;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.user.activity.UpdateLoginPwdActivity;
import com.edencity.store.user.activity.UpdatePayPwdActivity;
import com.edencity.store.util.RegexUtils;
import com.edencity.store.view.ConfirmDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends BaseFragment2 {


    private MyNormalTextView version;
    private MyNormalTextView phone;
    private BaseDialog baseDialog;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance(){
        return new SettingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        StatusBarCompat.changeToLightStatusBar(getActivity());
    }

    private void initView(View inflate) {
         version = inflate.findViewById(R.id.version);
        phone = inflate.findViewById(R.id.phone);
        if (App.userMsg().getProvider().getPhone()!=null){
            phone.setText(RegexUtils.hidePhone(App.userMsg().getProvider().getPhone()));
        }
        if (getAppVersionCode(getActivity())==1 ||
                getAppVersionCode(getActivity())==2 ||
                getAppVersionCode(getActivity())==3 ||
                getAppVersionCode(getActivity())==4){
            version.setText("版本号 V"+ getAppVersionCode(getActivity())+".00");
        }else{
            version.setText("版本号 V"+ getAppVersionCode(getActivity())+"");
        }

    }

    public long getAppVersionCode(Context context) {
        long appVersionCode = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                appVersionCode = packageInfo.getLongVersionCode();
            } else {
                appVersionCode = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
        }

        return appVersionCode;
    }
    @Override
    public void onViewItemClicked(View view) {
        switch (view.getId()){
            case R.id.btn_back: {
                pop();
            }break;

            //退出登录
            case R.id.btn_quit: {
                ConfirmDialog.showConfirm(getFragmentManager(),"您确定要退出系统，重新登录吗？",(dialog,btn) -> {
                    if (btn == DialogInterface.BUTTON_POSITIVE){
                        App.defaultApp().saveUserMsg(null);
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

            }break;
            //登录账号
            case R.id.phone:{
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_call, null);
                MyMediumTextView phone = inflate.findViewById(R.id.phone);
                MyNormalTextView textView3 = inflate.findViewById(R.id.textView3);
                textView3.setText("如需修改，请拨打");
                phone.setText("400-836205");
                           /*  MyMediumTextView sure = inflate.findViewById(R.id.sure);
                             sure.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     Intent intent = new Intent(Intent.ACTION_CALL);
                                     Uri data = Uri.parse("400-836205");
                                     intent.setData(data);
                                     startActivity(intent);
                                     baseDialog.dismiss();
                                 }
                             });
                             MyMediumTextView cancle = inflate.findViewById(R.id.cancle);
                             cancle.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     baseDialog.dismiss();
                                 }
                             });*/
                baseDialog = new BaseDialog(getContext(), inflate, Gravity.CENTER);
                baseDialog.show();
            }break;
           //登录密码
            case R.id.pwd:{
                Intent intent = new Intent(getContext(), UpdateLoginPwdActivity.class);
                startActivity(intent);
            }break;
            //支付密码
            case R.id.paypwd:{
                Intent intent = new Intent(getContext(), UpdatePayPwdActivity.class);
                startActivity(intent);
            }break;


        }
    }

}

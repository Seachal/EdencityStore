package com.edencity.store.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.base.BaseDialog;
import com.edencity.store.base.NormalWebActivity;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.data.AppContant;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.AllMemberEntity;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.login.WebFragment;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.user.activity.BuyVipActivity;
import com.edencity.store.user.adapter.AllMemberAdapter;
import com.edencity.store.user.fragment.IdentityVerifyFragment;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.ButtonUtils;
import com.edencity.store.util.DateFormatUtils;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;
import com.edencity.store.view.LoadingDialog;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportActivity;

public class VipLevelActivity extends SupportActivity {

    private ImageView back;
    private MyNormalTextView rule;
    private MyMediumTextView vip_count;
    private AllMemberAdapter allMemberAdapter;
    private RecyclerView rlv_vip;
    private MyNormalTextView one;
    private MyNormalTextView two;
    private MyNormalTextView there;
    private MyMediumTextView vip_level;
    private MyNormalTextView time;
    private MyMediumTextView up;
    private BaseDialog baseDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_level);

        Window window = getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE );
        }else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }


        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        initView();
        if (App.userMsg()!=null && App.userMsg().getProvider()!=null){

            vip_level.setText(App.userMsg().getProvider().getUserVipLevel());
            if (App.userMsg().getProvider().getMemberEndTime()!=null && !App.userMsg().getProvider().getMemberEndTime().equals("")){
                time.setVisibility(View.VISIBLE);
                long stringToDate = DateFormatUtils.getStringToDate(App.userMsg().getProvider().getMemberEndTime(), DateFormatUtils.FORMAT_6);
                String formattedDateString = DateFormatUtils.getDateToString(stringToDate, DateFormatUtils.FORMAT_5);
                time.setText("有效期至："+formattedDateString);
            }else{
                time.setVisibility(View.GONE);
            }
            if (App.userMsg().getProvider().getUserVipLevel().equals("一级成员")){
                initData("一级特权","1");
                one.setBackgroundResource(R.drawable.text_bg_yellow);
                two.setBackgroundResource(0);
                there.setBackgroundResource(0);
                up.setEnabled(true);
                up.setBackgroundColor(Color.parseColor("#548EDC"));
            }else if (App.userMsg().getProvider().getUserVipLevel().equals("二级成员")){
                initData("二级特权","2");
                two.setBackgroundResource(R.drawable.text_bg_yellow);
                one.setBackgroundResource(0);
                there.setBackgroundResource(0);
                up.setEnabled(true);
                up.setBackgroundColor(Color.parseColor("#548EDC"));
            }else{
                initData("三级特权","3");
                there.setBackgroundResource(R.drawable.text_bg_yellow);
                two.setBackgroundResource(0);
                one.setBackgroundResource(0);
                up.setEnabled(false);
                up.setBackgroundColor(Color.parseColor("#CECECE"));
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData(String type , String memberType) {

        LoadingDialog.showLoading(getSupportFragmentManager());
        HashMap paramsMap = ParamsUtils.getParamsMapWithNoId(null,null);
        String sign = ParamsUtils.getSign(paramsMap);
        try {

            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataService.getUserService().getAllMemberShip(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<AllMemberEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<AllMemberEntity> o) {

                        /*Log.e("mem",o.toString());*/

                        if (o.getData()!=null && o.getData().getAllMembershipRights()!=null &&
                                o.getData().getAllMembershipRights().size()>0){
                             allMemberAdapter = new AllMemberAdapter(memberType);
                             rlv_vip.setLayoutManager(new GridLayoutManager(VipLevelActivity.this,4));
                             rlv_vip.setAdapter(allMemberAdapter);
                             allMemberAdapter.addData(o.getData().getAllMembershipRights());
                            int itemType = allMemberAdapter.getItemType(memberType);
                            vip_count.setText(type + itemType+"项");
                        }else if (o.getResult_code()== -3){
                            AdiUtils.loginOut();
                        }
                        LoadingDialog.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LoadingDialog.hideLoading();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        back = findViewById(R.id.back);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        there = findViewById(R.id.there);
        rlv_vip = findViewById(R.id.rlv_vip);
        up = findViewById(R.id.up);
        vip_level = findViewById(R.id.vip_level);
        time = findViewById(R.id.time);
        back.setOnClickListener(v -> finish());
        rule = findViewById(R.id.rule);
        vip_count = findViewById(R.id.vip_count);
        up.setOnClickListener(v -> {
            if (!ButtonUtils.isFastDoubleClick(R.id.up)){
                if (App.userMsg()!=null && App.userMsg().getProvider()!=null &&
                        App.userMsg().getProvider().getProviderStatus()!=null){
                    if (App.userMsg().getProvider().getProviderStatus().equals("1002010716") || App.userMsg().getProvider().getProviderStatus().equals("1002010710")){
                        Intent intent = new Intent(VipLevelActivity.this, BuyVipActivity.class);
                        startActivity(intent);
                    }else{
                        initDialog();
                    }
                }
            }
        });
        rule.setOnClickListener(v -> {
            if (!ButtonUtils.isFastDoubleClick(R.id.rule)) {
                Intent intent = new Intent(this, NormalWebActivity.class);
                intent.putExtra("url", "http://121.42.184.173:8093/remittanceRank.html");
                startActivity(intent);
            }
        });
        one.setOnClickListener(v -> {
            initData("一级特权","1");
            one.setBackgroundResource(R.drawable.text_bg_yellow);
            two.setBackgroundResource(0);
            there.setBackgroundResource(0);
            up.setEnabled(true);
            up.setBackgroundColor(Color.parseColor("#548EDC"));
        });
        two.setOnClickListener(v -> {
            initData("二级特权","2");
            two.setBackgroundResource(R.drawable.text_bg_yellow);
            one.setBackgroundResource(0);
            there.setBackgroundResource(0);
            up.setEnabled(true);
            up.setBackgroundColor(Color.parseColor("#548EDC"));
        });
        there.setOnClickListener(v -> {
            initData("三级特权","3");
            there.setBackgroundResource(R.drawable.text_bg_yellow);
            two.setBackgroundResource(0);
            one.setBackgroundResource(0);
            up.setEnabled(false);
            up.setBackgroundColor(Color.parseColor("#CECECE"));
        });

    }

    private void initDialog() {
        View inflate = LayoutInflater.from(VipLevelActivity.this).inflate(R.layout.dialog_notifation, null);
        MyNormalTextView desc = inflate.findViewById(R.id.desc);
        MyMediumTextView ok = inflate.findViewById(R.id.ok);

        desc.setText("您的门店还没通过审核！");

        ok.setOnClickListener(v -> baseDialog2.dismiss());
        baseDialog2 = new BaseDialog(VipLevelActivity.this, inflate, Gravity.CENTER);
        baseDialog2.show();
    }
}

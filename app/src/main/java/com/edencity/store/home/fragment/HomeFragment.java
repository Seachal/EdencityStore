package com.edencity.store.home.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.edencity.store.base.BaseDialog;
import com.edencity.store.base.NormalWebActivity;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.HomeActiveListEntity;
import com.edencity.store.entity.HomeBannerEntity;
import com.edencity.store.home.activity.PayCodeActivity;
import com.edencity.store.home.adapter.HomeBannerAdapter;
import com.edencity.store.util.ButtonUtils;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;
import java.util.ArrayList;
import java.util.HashMap;
import com.edencity.store.App;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.home.activity.MainActivity;
import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.fragment.ScanPayFragment;
import com.edencity.store.home.activity.ReceiveMoneyCodeActivity;
import com.edencity.store.home.adapter.HomeListAdapter;
import com.edencity.store.home.adapter.UltraPagerAdapter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
                       .::::.
                     .::::::::.
                    :::::::::::  FUCK YOU
                ..:::::::::::'
              '::::::::::::'
                .::::::::::
           '::::::::::::::..
                ..::::::::::::.
              ``::::::::::::::::
               ::::``:::::::::'        .:::.
              ::::'   ':::::'       .::::::::.
            .::::'      ::::     .:::::::'::::.
           .:::'       :::::  .:::::::::' ':::::.
          .::'        :::::.:::::::::'      ':::::.
         .::'         ::::::::::::::'         ``::::.
     ...:::           ::::::::::::'              ``::.
    ````':.          ':::::::::'                  ::::..
                       '.:::::'                    ':'````..
*/

public class  HomeFragment extends BaseFragment2 {


    //收款
    private RelativeLayout pay_scan;
    //收/付款码
    private RelativeLayout scan_search;
    //对账
    private RelativeLayout rechargr;
    //预定
    private RelativeLayout ready;

    //列表
    private RecyclerView rlv_shop;
    private HomeListAdapter homeListAdapter;
    private UltraViewPager mBanner;
    private RecyclerView rlv_banner;
    private BaseDialog baseDialog;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home2, container, false);
        initView(inflate);

        /*initBanner();*/
        initTop();
        getDataList();
        return inflate;
    }



    private void initTop() {
        HashMap paramsMap = ParamsUtils.getParamsMapWithNoId("bannerType","2");
        String sign = ParamsUtils.getSign(paramsMap);
        try {

            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataService.getHomeService().getBannerList(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<HomeBannerEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<HomeBannerEntity> o) {
                        Log.e("banner",o.toString());
                        if (o.getData()!=null && o.getData().getBannerList()!=null
                                && o.getData().getBannerList().size()>0){

                            rlv_banner.setLayoutManager(new LinearLayoutManager(getContext()));
                            rlv_banner.setHasFixedSize(true);
                            rlv_banner.setNestedScrollingEnabled(false);

                            HomeBannerAdapter homeBannerAdapter = new HomeBannerAdapter(getActivity());
                            homeBannerAdapter.addData(o.getData().getBannerList());
                            rlv_banner.setAdapter(homeBannerAdapter);

                            homeBannerAdapter.onClick(position -> {
                                Intent intent = new Intent(getContext(), NormalWebActivity.class);
                                intent.putExtra("url", o.getData().getBannerList().get(position).getBannerContentUrl());
                                startActivity(intent);
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getDataList() {
        HashMap paramsMap = ParamsUtils.getParamsMap();
        String sign = ParamsUtils.getSign(paramsMap);
        try {

            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataService.getHomeService().getHomeActiveList(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<HomeActiveListEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<HomeActiveListEntity> o) {
                        Log.e("active",o.toString());
                        if (o.getData()!=null && o.getData().getActivitiesList()!=null && o.getData().getActivitiesList().size()>0){
                            homeListAdapter = new HomeListAdapter(getActivity());
                            rlv_shop.setLayoutManager(new LinearLayoutManager(getContext()));

                            rlv_shop.setAdapter(homeListAdapter);
                            homeListAdapter.addData(o.getData().getActivitiesList());
                            rlv_shop.setHasFixedSize(true);
                            rlv_shop.setNestedScrollingEnabled(false);

                            homeListAdapter.onClick(position -> {
                                Intent intent = new Intent(getContext(), NormalWebActivity.class);
                                intent.putExtra("url", o.getData().getActivitiesList().get(position).getDetailUrl());
                                startActivity(intent);
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initBanner() {


        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(R.drawable.banner);
        objects.add(R.drawable.banner);
        objects.add(R.drawable.banner);
        objects.add(R.drawable.banner);
        objects.add(R.drawable.banner);
        mBanner.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //UltraPagerAdapter 绑定子view到UltraViewPager
        PagerAdapter adapter = new UltraPagerAdapter(true,objects);
        mBanner.setAdapter(adapter);

        mBanner.setMultiScreen(0.8f);
        mBanner.setItemRatio(1.0f);
        mBanner.setRatio(1.8f);

        mBanner.setMaxHeight(300);
        mBanner.setPageTransformer(false, new UltraDepthScaleTransformer());
           //设定页面循环播放
        mBanner.setInfiniteLoop(true);
           //设定页面自动切换  间隔2秒
        mBanner.setAutoScroll(3000);
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        Window window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        StatusBarCompat.changeToLightStatusBar(getActivity());
    }


    private void initView(View inflate) {
        pay_scan = inflate.findViewById(R.id.pay_scan);
        scan_search = inflate.findViewById(R.id.scan_search);
        rechargr = inflate.findViewById(R.id.rechargr);
        ready = inflate.findViewById(R.id.ready);
        rlv_shop = inflate.findViewById(R.id.rlv_shop);
        rlv_banner = inflate.findViewById(R.id.rlv_banner);
        /*mBanner = inflate.findViewById(R.id.banner);*/

        //付款
        scan_search.setOnClickListener(v -> {

            if (!ButtonUtils.isFastDoubleClick(R.id.scan_search)){
                if (App.userMsg()!=null && App.userMsg().getProvider()!=null &&
                        App.userMsg().getProvider().getProviderStatus()!=null){
                    if (App.userMsg().getProvider().getProviderStatus().equals("1002010716") || App.userMsg().getProvider().getProviderStatus().equals("1002010710")){
                        Intent intent = new Intent(getActivity(), PayCodeActivity.class);
                        startActivity(intent);
                    }else{
                        initDialog(1);
                    }
                }
            }

        });

        //扫一扫
        pay_scan.setOnClickListener(v -> {
            if (!ButtonUtils.isFastDoubleClick(R.id.pay_scan)){
                if (App.userMsg()!=null && App.userMsg().getProvider()!=null &&
                        App.userMsg().getProvider().getProviderStatus()!=null){
                    if (App.userMsg().getProvider().getProviderStatus().equals("1002010716") || App.userMsg().getProvider().getProviderStatus().equals("1002010710")){
                        ((MainActivity)getActivity()).start(ScanPayFragment.newInstance());
                    }else{
                        initDialog(2);
                    }
                }
            }
        });
        //收款
        rechargr.setOnClickListener(v -> {
            if (!ButtonUtils.isFastDoubleClick(R.id.rechargr)){
                if (App.userMsg()!=null && App.userMsg().getProvider()!=null &&
                        App.userMsg().getProvider().getProviderStatus()!=null){
                    if (App.userMsg().getProvider().getProviderStatus().equals("1002010716") || App.userMsg().getProvider().getProviderStatus().equals("1002010710")){
                        Intent intent = new Intent(getActivity(), ReceiveMoneyCodeActivity.class);
                        startActivity(intent);
                    }else{
                        initDialog(3);

                    }
                }
            }
        });

    }

    private void initDialog(int i) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_notifation, null);
        MyNormalTextView desc = inflate.findViewById(R.id.desc);
        MyMediumTextView ok = inflate.findViewById(R.id.ok);

        if (i==1){
            desc.setText("您还没通过审核，不能收款！");
        }else if (i==2){
            desc.setText("您还没通过审核，不能付款！");
        }else{
            desc.setText("您还没通过审核，不能收款！");
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.dismiss();
            }
        });
        baseDialog = new BaseDialog(getContext(), inflate, Gravity.CENTER);
         baseDialog.show();
    }


}

package com.edencity.store.home.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.edencity.store.App;
import com.edencity.store.custum.TwoBallRotationProgressBar;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.HomeActiveListEntity;
import com.edencity.store.entity.TodayCountEntity;
import com.edencity.store.home.adapter.HomeListAdapter;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.DeeSpUtil;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;

import com.edencity.store.R;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.home.activity.CountActivity;
import com.edencity.store.home.adapter.CountChatAdapter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖镇楼                 BUG辟易
/**
 * A simple {@link Fragment} subclass.
 */
public class VipFragment extends BaseFragment2 {



    private SmartRefreshLayout mSmartRefresh;
   /* private MyNormalTextView end_with;*/
    private RecyclerView rlv_chat;
    private TwoBallRotationProgressBar loading;
    /* private MyMediumTextView count;*/

    public VipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_vip, container, false);
        initView(inflate);
        return inflate;
    }

    private void initData() {



        DeeSpUtil instance = App.getSp();
        String userId = instance.getString("userId");
        String ticket = instance.getString("ticket");
        Log.e("aaa",userId+ticket+"------=-");
        HashMap paramsMap = ParamsUtils.getParamsMap();
        DeeSpUtil instance2 = App.getSp();
        String userId2 = instance2.getString("userId");
        String ticket2 = instance2.getString("ticket");
        Log.e("aaa",userId2+ticket2+"------");
        String sign = ParamsUtils.getSign(paramsMap);
        try {

            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataService.getHomeService().getTotalCensus(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<TodayCountEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<TodayCountEntity> o) {
                        Log.e("active",o.toString());

                        if (o.getData()!=null && o.getData().getTodayIncomeCensusData()
                                !=null){
                             ArrayList<BaseDebug> objects = new ArrayList<>();
                            TodayCountEntity.TodayIncomeCensusDataBean bean = o.getData().getTodayIncomeCensusData();
                            objects.add(new BaseDebug(bean.getSaleAmount()+"",
                                    bean.getTotalOrderAmount()+"",false,0));

                             objects.add(new BaseDebug(""+bean.getSaleCount(),
                                     ""+bean.getTotalOrderCount(),false,1));

                             objects.add(new BaseDebug(""+bean.getCustomerUnitPrice(),
                                     ""+bean.getTotalCustomerUnitPrice(),false,2));

                            rlv_chat.setLayoutManager(new LinearLayoutManager(getContext()));
                            CountChatAdapter countChatAdapter = new CountChatAdapter();
                            countChatAdapter.addData(objects);
                            rlv_chat.setAdapter(countChatAdapter);
                        }else if (o.getResult_code()== -3){
                            AdiUtils.loginOut();
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
        initData();
    }

    private void initView(View inflate) {

        mSmartRefresh = inflate.findViewById(R.id.smart);

        /*count = inflate.findViewById(R.id.count);*/
        rlv_chat = inflate.findViewById(R.id.rlv_chat);


    }


}

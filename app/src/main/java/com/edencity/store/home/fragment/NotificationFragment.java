package com.edencity.store.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edencity.store.App;
import com.edencity.store.custum.TwoBallRotationProgressBar;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.MsgEntity;
import com.edencity.store.entity.TodayCountEntity;
import com.edencity.store.home.adapter.CountChatAdapter;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.DateFormatUtils;
import com.edencity.store.util.DeeSpUtil;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;

import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.home.adapter.NotificationAdapter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment2 {


    private RecyclerView rlv_chat;
    private SmartRefreshLayout mSmart;
    private int page = 1;
    private NotificationAdapter adapter;
    private TwoBallRotationProgressBar loading;
    private boolean loadOk = false;


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_notification, container, false);
        initView(inflate);
        initLogic();
        return inflate;
    }

    private void initLogic() {
        rlv_chat.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NotificationAdapter();
        rlv_chat.setAdapter(adapter);
        mSmart.setOnRefreshListener(refreshLayout -> {
            page = 1;
            initData();
        });
        mSmart.setOnLoadMoreListener(refreshLayout -> {
            page ++;
            initData();
        });
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        loadOk = false;
        page = 1;
        initData();
    }

    private void initData() {


        HashMap paramsMap = ParamsUtils.getParamsMap("pageNum",page,
                "pageSize",10);
        String sign = ParamsUtils.getSign(paramsMap);
        try {

            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataService.getUserService().getMsg(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<MsgEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<MsgEntity> o) {
                        Log.e("active",o.toString());
                        if (o.getData()!=null && o.getData().getMessageList()!=null &&
                                o.getData().getMessageList().getList()!=null && o.getData().
                                getMessageList().getList().size()>0){
/*
                        if (o.getData()==null){
*/
                            if (loadOk){

                            }else{
                                if (page==1){
                                    adapter.addData(o.getData().getMessageList().getList());
                                }else{
                                    if (o.getData().getMessageList().isHasNextPage()){
                                        adapter.addNewData(o.getData().getMessageList().getList());
                                    }else{
                                        loadOk = true;
                                    }
                                }
                            }
                        }else if (o.getResult_code()== -3){
                            AdiUtils.loginOut();
                        }else{

                        }
                        Log.e("feed",o.toString());
                        if (mSmart.isRefreshing()){
                            mSmart.finishRefresh();
                        }
                        if (mSmart.isLoading()){
                            mSmart.finishLoadMore();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        if (mSmart.isRefreshing()){
                            mSmart.finishRefresh();
                        }
                        if (mSmart.isLoading()){
                            mSmart.finishLoadMore();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View inflate) {
        rlv_chat = inflate.findViewById(R.id.rlv_chat);
        mSmart = inflate.findViewById(R.id.smart);

    }

}

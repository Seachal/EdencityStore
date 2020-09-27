package com.edencity.store.user.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.FeedBackListEntity;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.user.adapter.FeedListAdapter;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.umeng.message.PushAgent;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportActivity;

public class FeedHistoryActivity extends SupportActivity {

    private ImageView mBack;
    private RecyclerView mRlvMsg;
    private SmartRefreshLayout mSmart;
    private MyNormalTextView mTagText;
    private ConstraintLayout mLoadfail;
    private FeedListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(this).onAppStart();
        StatusBarCompat.setStatusBarColor(this,Color.WHITE);
        setContentView(R.layout.activity_feed_history);
        initView();
        initRlv();
        initData();

    }
    private void initRlv() {
        mRlvMsg.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FeedListAdapter();
        mRlvMsg.setAdapter(adapter);

    }
    private void initData() {
        HashMap paramsMap = ParamsUtils.getParamsMap();
        String sign = ParamsUtils.getSign(paramsMap);
        try {
            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataService.getUserService().getUserFeedBackHistory(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<FeedBackListEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<FeedBackListEntity> o) {
                        Log.e("feeds",o.toString());
                        if (o.getResult_code()==0){
                            mLoadfail.setVisibility(View.GONE);
                            mSmart.setVisibility(View.VISIBLE);
                            adapter.addData(o.getData().getFeedbackList());
                        }else if (o.getResult_code()== -3){
                            AdiUtils.loginOut();
                        }else{
                            mLoadfail.setVisibility(View.VISIBLE);
                            mTagText.setText("我是有底线的");
                            mSmart.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoadfail.setVisibility(View.VISIBLE);
                        mTagText.setText("我是有底线的");
                        mSmart.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(v ->{
            finish();
        });
        mRlvMsg = (RecyclerView) findViewById(R.id.rlv_msg);
        mSmart = (SmartRefreshLayout) findViewById(R.id.smart);
        mTagText = (MyNormalTextView) findViewById(R.id.tag_text);
        mLoadfail = (ConstraintLayout) findViewById(R.id.loadfail);
    }
}

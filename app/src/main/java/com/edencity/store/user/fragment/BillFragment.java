package com.edencity.store.user.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.TwoBallRotationProgressBar;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.BillEntity;
import com.edencity.store.home.activity.BillDetailActivity;
import com.edencity.store.home.activity.OrderDetailActivity;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.user.adapter.BillAdapter;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;
import com.edencity.store.view.RecyclerLineDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import razerdp.basepopup.QuickPopupBuilder;
import razerdp.basepopup.QuickPopupConfig;
import razerdp.widget.QuickPopup;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillFragment extends BaseFragment2 {

    @BindView(R.id.pop)
    MyNormalTextView billTypeButton;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    @BindView(R.id.loadfail)
    ConstraintLayout mLoadFail;
    @BindView(R.id.bill_list)
    RecyclerView billList;
    @BindView(R.id.tag_text)
    MyNormalTextView mTag;
    @BindView(R.id.btn_back)
    ImageView mBack;
    @BindView(R.id.loading)
    TwoBallRotationProgressBar loading;


    private List<BillEntity.AccountBillsBean> billItems;
    private boolean isFirstLaunch;
    private QuickPopup show;
    private boolean loadOk = false;
    private BillAdapter adapter;
    private int page = 1;
    private int billType = 2;

    public BillFragment() {
        // Required empty public constructor
    }

    public static BillFragment newInstance(){
        return new BillFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout view= (RelativeLayout)inflater.inflate(R.layout.fragment_bill, container, false);
        ButterKnife.bind(this,view);


        initVIew();
        initData(page );
        return view;
    }

    private void initVIew() {

        adapter = new BillAdapter();
        billList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        billList.setAdapter(adapter);
        billList.addItemDecoration(new RecyclerLineDivider(getContext(),LinearLayoutManager.HORIZONTAL,1,
                ContextCompat.getColor(getContext(), R.color.bg_main)));
        mSmart.setOnRefreshListener(refreshLayout -> {
            initData(1);
        });
        mSmart.setOnLoadMoreListener(refreshLayout -> {
            page ++;
            initData(page);
        });
        mBack.setOnClickListener(v -> pop());
        adapter.onItem(i -> {
            Intent intent = new Intent(getContext(), BillDetailActivity.class);
            intent.putExtra("recordid",adapter.mList.get(i).getSourceRecordId());
            intent.putExtra("sourcetype",adapter.mList.get(i).getSourceType());
            startActivity(intent);
        });
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

            StatusBarCompat.changeToLightStatusBar(getActivity());

    }

    //1:全部
    private void initData(int page) {
        loading.startAnimator();
        HashMap paramsMap = ParamsUtils.getParamsMap("pageNum",page,"billType",billType,"pageSize",10);
        String sign = ParamsUtils.getSign(paramsMap);
        try {
            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataService.getUserService().userAccountList(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<BillEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<BillEntity> o) {
                        Log.d("feed",page+"----"+o.toString());
                        if (o.getData()!=null && o.getData().getAccountBills()!=null &&
                                o.getData().getAccountBills().getList()!=null && o.getData().
                                getAccountBills().getList().size()>0){
                            mLoadFail.setVisibility(View.GONE);
                            mSmart.setVisibility(View.VISIBLE);

                            if (loadOk){

                            }else{
                                if (page==1){
                                    adapter.addData(o.getData().getAccountBills().getList());
                                }else{
                                    if (o.getData().getAccountBills().isHasNextPage()){
                                        loadOk = true;
                                        adapter.addNewData(o.getData().getAccountBills().getList());
                                    }
                                }
                            }
                        }else if (o.getResult_code()== -3){
                            AdiUtils.loginOut();
                        }else{
                            mLoadFail.setVisibility(View.VISIBLE);
                            mTag.setText("我是有底线的");
                            mSmart.setVisibility(View.GONE);
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
                        mLoadFail.setVisibility(View.VISIBLE);
                        mTag.setText("我是有底线的");
                        mSmart.setVisibility(View.GONE);
                        if (mSmart.isRefreshing()){
                            mSmart.finishRefresh();
                        }
                        if (mSmart.isLoading()){
                            mSmart.finishLoadMore();
                        }
                    }

                    @Override
                    public void onComplete() {

                        loading.stopAnimator();
                    }
                });

    }

    @Override
    public void onViewItemClicked(View view) {
        switch (view.getId()){

            case R.id.pop: {

                //支出
                //收款
                show = QuickPopupBuilder.with(getContext())
                        .contentView(R.layout.pop_bill)
                        .config(new QuickPopupConfig()
                                .gravity(Gravity.BOTTOM)
                                //全部
                                .withClick(R.id.all, v -> {
                                    billTypeButton.setText("全部");
                                    page =1 ;
                                    billType = 2 ;
                                    initData(page);
                                    show.dismiss();
                                }).withClick(R.id.rechage, v -> {
                                    billTypeButton.setText("收款");
                                    billType = 0;
                                    page = 1;
                                    initData(page);
                                    show.dismiss();

                                }).withClick(R.id.buy, v -> {
                                    billType = 1;
                                    initData(page);
                                    page = 1;
                                    billTypeButton.setText("支出");
                                    show.dismiss();
                                })
                        ).show(billTypeButton);

            }
                break;
        }
    }


}

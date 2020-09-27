package com.edencity.store.user.fragment;


import android.content.Intent;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.edencity.store.App;
import com.edencity.store.custum.TwoBallRotationProgressBar;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.BillEntity;
import com.edencity.store.entity.CountHistoryEntity;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.user.adapter.BillAdapter;
import com.edencity.store.user.adapter.BillCountAdapter;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;

import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.user.activity.CountDetailActivity;
import com.edencity.store.view.RecyclerLineDivider;

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
public class BillCountFragment extends BaseFragment2 {


    private RecyclerView billList;
    private ImageView back;
    private MyNormalTextView pop;
    private MyMediumTextView total_count;
    private SmartRefreshLayout mSmart;
    private ConstraintLayout mLoadFail;
    private MyNormalTextView mTag;
    private boolean loadOk = false;
    private BillCountAdapter adapter;
    private int page = 1;
    private String billType = "0";
    private QuickPopup show;
    private TwoBallRotationProgressBar loading;

    public BillCountFragment() {
        // Required empty public constructor
    }
    public static BillCountFragment newInstance(){
        return new BillCountFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_bill_count, container, false);
        initView(inflate);
        initLogic();
        initList();
        initData(page);
        return inflate;
    }

    private void initLogic() {

        total_count.setText("累计结算成功  "+ App.userMsg().getProvider().getPossess());
        back.setOnClickListener(v -> pop());
        pop.setOnClickListener(v -> show = QuickPopupBuilder.with(getContext())
                .contentView(R.layout.pop_bill2)
                .config(new QuickPopupConfig()
                        .gravity(Gravity.BOTTOM)
                        //全部
                        .withClick(R.id.all, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                billType = "0";
                                page = 1;
                                initData(page);
                                pop.setText("全部");
                                show.dismiss();

                            }

                        }).withClick(R.id.wait, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                billType = "1010011501";
                                page = 1;
                                initData(page);
                                pop.setText("待结算");
                                show.dismiss();

                            }

                        }).withClick(R.id.rechage, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                billType = "1010011502";
                                page = 1;
                                initData(page);
                                pop.setText("结算中");
                                show.dismiss();

                            }

                        }).withClick(R.id.buy, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                billType = "1010011503";
                                page = 1;
                                initData(page);
                                pop.setText("结算成功");
                                show.dismiss();
                            }
                        }).withClick(R.id.give, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                billType = "1010011504";
                                page = 1;
                                initData(page);
                                pop.setText("结算失败");
                                show.dismiss();
                            }
                        })
                ).show(pop));
    }

    private void initList() {
        adapter = new BillCountAdapter();
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
        adapter.onItemClick(i -> {
            Intent intent = new Intent(getContext(), CountDetailActivity.class);
            CountHistoryEntity.AccountBillsBean.ListBean listBean = adapter.mList.get(i);
            intent.putExtra("id2",listBean);
            intent.putExtra("type","2");
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
        HashMap paramsMap;

        if (billType.equals("0")){
             paramsMap = ParamsUtils.getParamsMap("pageNum",page
                    ,"pageSize",10);
        }else{
             paramsMap = ParamsUtils.getParamsMap("pageNum",page,
                    "balanceStatus",billType,"pageSize",10);
        }

        String sign = ParamsUtils.getSign(paramsMap);
        try {
            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataService.getUserService().getBalanceBill(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<CountHistoryEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<CountHistoryEntity> o) {
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
                            mTag.setText("暂时没有结算记录哦~");
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
                        mTag.setText("暂时没有结算记录哦");
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
    private void initView(View inflate) {
        billList =  inflate.findViewById(R.id.bill_list);
        mSmart =  inflate.findViewById(R.id.smart);
        total_count =  inflate.findViewById(R.id.total_count);
        back = inflate.findViewById(R.id.back);
        mLoadFail = inflate.findViewById(R.id.loadfail);
        mTag = inflate.findViewById(R.id.tag_text);
        pop = inflate.findViewById(R.id.pop);
        loading = inflate.findViewById(R.id.loading);
    }

}

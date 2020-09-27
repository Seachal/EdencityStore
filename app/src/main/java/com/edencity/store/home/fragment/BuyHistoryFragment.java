package com.edencity.store.home.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;

import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.home.adapter.OrderHistoryAdapter;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyHistoryFragment extends BaseFragment2 {


    private RecyclerView rlv_order;
    private ImageView back;
    private SmartRefreshLayout smart;

    public BuyHistoryFragment() {
        // Required empty public constructor
    }

    public static BuyHistoryFragment getInstance(){
        return new BuyHistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_buy_history, container, false);
       initView(inflate);
        initOrderList();
        return inflate;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        StatusBarCompat.changeToLightStatusBar(getActivity());
    }

    private void initOrderList() {

        HashMap paramsMap = ParamsUtils.getParamsMap();
        String sign = ParamsUtils.getSign(paramsMap);
        try {
            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* DataService.getUserService().vipBuyHistory(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult o) {

                        Log.e("vip",o.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
        OrderHistoryAdapter adapter = new OrderHistoryAdapter();
        rlv_order.setLayoutManager(new LinearLayoutManager(getContext()));
        rlv_order.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initView(View inflate) {
        rlv_order = inflate.findViewById(R.id.rlv_order);
        back = inflate.findViewById(R.id.back);
        smart = inflate.findViewById(R.id.smart);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }

    }

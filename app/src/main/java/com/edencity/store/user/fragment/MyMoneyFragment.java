package com.edencity.store.user.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.entity.UserMsgEntity;
import com.edencity.store.home.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMoneyFragment extends BaseFragment2 {


    private ImageView back;

    //结算
    private MyNormalTextView count;

    //账单明细
    private MyNormalTextView order_detail;

    //结算记录
    private MyNormalTextView count_history;

    //银行卡号
    private MyMediumTextView bank_cade;
    private MyNormalTextView change_card;
    private MyMediumTextView money;

    public MyMoneyFragment() {
        // Required empty public constructor
    }

    public static MyMoneyFragment newInstance() {
        return new MyMoneyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_my_money, container, false);
        initView(inflate);

        return inflate;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        StatusBarCompat.changeToLightStatusBar(getActivity());
        sysn();
    }

    private void sysn() {
        if (App.userMsg()!=null && App.userMsg().getProvider()!=null){
            UserMsgEntity.ProviderBean provider = App.userMsg().getProvider();
            money.setText("已结算金额：" + provider.getPossess());
            String bankNumber = provider.getBankNumber();
            if (bankNumber != null && !bankNumber.equals("") && bankNumber.length() > 12) {
                String replace = bankNumber.replace(bankNumber.substring(4, 12), "********");
                bank_cade.setText(replace);
            }
        }


    }

    private void initView(View inflate) {

        back = inflate.findViewById(R.id.back);
        count = inflate.findViewById(R.id.count);
        order_detail = inflate.findViewById(R.id.order_detail);
        count_history = inflate.findViewById(R.id.count_history);
        bank_cade = inflate.findViewById(R.id.bank_cade);
        money = inflate.findViewById(R.id.money);
        change_card = inflate.findViewById(R.id.change_card);


        back.setOnClickListener(v -> pop());

        order_detail.setOnClickListener(v -> ((MainActivity) getActivity()).start(BillFragment.newInstance()));
        count.setOnClickListener(v -> ((MainActivity) getActivity()).start(MoneyCountFragment.getInstance()));
        count_history.setOnClickListener(v -> ((MainActivity) getActivity()).start(BillCountFragment.newInstance()));
    }

}

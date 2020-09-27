package com.edencity.store.user.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.entity.UserMsgEntity;
import com.edencity.store.home.activity.MainActivity;
import com.edencity.store.pojo.Customer;
import com.edencity.store.user.activity.UpdateLoginPwdActivity;
import com.edencity.store.user.activity.UpdatePayPwdActivity;

public class SafeFragment extends BaseFragment2 {

    @BindView(R.id.text_name_state)
    TextView name_state;

    public SafeFragment() {
        // Required empty public constructor
    }

    public static SafeFragment newInstance(){
        return new SafeFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_safe, container, false);
        ButterKnife.bind(this,view);
        initView();
        /*EventBus.getDefault().register(this);*/
        return view;
    }

    private void initView() {
      /*  UserMsgEntity userMsg = App.userMsg();
        if (userMsg.getProvider()!=null && userMsg.getProvider().getUserVipLevel()){
            name_state.setText(userMsg.getCustomer().getUserApprovalStatusDesc());
        }*/
    }

    @Override
    public void onViewItemClicked(View view) {
        switch (view.getId()){
            case R.id.btn_back: {
                pop();
            }break;
            //实名认证
            case R.id.text_name_state: {
               /* if (!App.userMsg().getProvider().get()) {
                    //没有实名认证
                    if (App.userMsg().getCustomer().getUserApprovalStatus() == Customer.USER_APPROVAL_NOT) {

                         ((MainActivity)getActivity()).start(IdentityVerifyFragment.newInstance());

                    } else if (App.userMsg().getCustomer().getUserApprovalStatus() == Customer.USER_APPROVAL_FAIL){
                       //审核失败
                        ((MainActivity)getActivity()).start(IdentityVerifyFragment.newInstance());
                    }else{

                    }
                }else{//认证了

                }*/
            }break;
            //支付密码
            case R.id.update_pay_pwd: {
                Intent intent = new Intent(getContext(), UpdatePayPwdActivity.class);
                startActivity(intent);
            }break;
            //登录密码
            case R.id.update_login_pwd: {
                Intent intent = new Intent(getContext(), UpdateLoginPwdActivity.class);
                startActivity(intent);
            }break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*EventBus.getDefault().unregister(this);*/
    }
}

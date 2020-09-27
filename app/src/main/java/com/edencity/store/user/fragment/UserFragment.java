package com.edencity.store.user.fragment;


import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.edencity.store.App;
import com.edencity.store.base.BaseDialog;
import com.edencity.store.base.IBaseCallBack;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.UserMsgEntity;
import com.edencity.store.home.activity.MainActivity;
import com.edencity.store.R;
import com.edencity.store.home.activity.VipLevelActivity;
import com.edencity.store.pojo.Customer;
import com.edencity.store.user.activity.BuyVipActivity;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.ButtonUtils;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment2 implements View.OnClickListener {

    @BindView(R.id.user_phone)
    MyMediumTextView mNameTextView;
    @BindView(R.id.text_user_approval_result)
    MyNormalTextView mApproval;
    //立即开通
    @BindView(R.id.recharge)
    MyMediumTextView mBtn_recharge;


    @BindView(R.id.image_avatar)
    ImageView mAvatarView;
    //我的活动
    @BindView(R.id.layout_active)
    LinearLayout mActive;
    //我的资产
    @BindView(R.id.layout_money)
    LinearLayout mMoney;
    //银行卡管理
    @BindView(R.id.layout_card)
    LinearLayout mCard;
    //意见反馈
    @BindView(R.id.layout_feedback)
    LinearLayout mFeedback;
    //发票管理
    @BindView(R.id.layout_fapiao)
    LinearLayout mFapiao;
    //代缴账单
    @BindView(R.id.layout_bill)
    LinearLayout mBill;

    private boolean firstLaunch;
    private String avatarLoaded;

    @BindView(R.id.setting)
    ImageView mBtn_setting;

    //余额
    @BindView(R.id.vip_desc)
    MyMediumTextView mDesc;

   //门店管理
    @BindView(R.id.unline_shop)
    LinearLayout mUnLineShop;
    //客服中心
    @BindView(R.id.waiter_center)
    LinearLayout mWaiter_center;





    private int unReadMessageCount;
    private BaseDialog baseDialog;
    private BaseDialog baseDialog2;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance(){
        return new UserFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user2, container, false);
        ButterKnife.bind(this,view);
        firstLaunch=true;
        initView();
        return view;

    }

    private void initView() {
        mAvatarView.setOnClickListener(this);
        mBtn_recharge.setOnClickListener(this);
        mActive.setOnClickListener(this);
        mBill.setOnClickListener(this);
        mBtn_setting.setOnClickListener(this);
        mMoney.setOnClickListener(this);
        mFeedback.setOnClickListener(this);
        mFapiao.setOnClickListener(this);
        mWaiter_center.setOnClickListener(this);
        mUnLineShop.setOnClickListener(this);
        mCard.setOnClickListener(this);
        if (App.userMsg()!=null && App.userMsg().getProvider()!=null
                && App.userMsg().getProvider().getUserVipLevel()!=null &&
        App.userMsg().getProvider().getUserVipLevel().equals("一级成员")){
            mBtn_recharge.setText("升级成员等级 >");
        }else{
            mBtn_recharge.setText("续费成员等级 >");
        }
        mApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ButtonUtils.isFastDoubleClick(R.id.text_user_approval_result)) {
                    Intent intent = new Intent(getActivity(), VipLevelActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void sysnState() {

        DataService.getInstance().syncUser(App.getSp().getString("userId"),
                App.getSp().getString("ticket"), new IBaseCallBack<UserMsgEntity>() {
                    @Override
                    public void onSuccess(UserMsgEntity data) {
                        App.defaultApp().saveUserMsg(data);
                        if (data!=null && data.getProvider()!=null){

                            mApproval.setText(data.getProvider().getUserVipLevel());

                            mNameTextView.setText(data.getProvider().getStoreName());
                            if (App.userMsg().getProvider().getStoreFacadeImg()!=null && !App.userMsg().getProvider().getStoreFacadeImg().equals("")){
                                Picasso.with(getContext()).load(data.getProvider().getStoreFacadeImg()).into(mAvatarView);
                            }
                            mDesc.setText("伊甸果余额："+data.getProvider().getGross()+"");
                        }
                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });

    }

  @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        sysnState();
      StatusBarCompat.cancelLightStatusBar(getActivity());
  }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //设置
            case R.id.setting:
                if (!ButtonUtils.isFastDoubleClick(R.id.setting)){
                    ((MainActivity) getActivity()).start(SettingFragment.newInstance());
                }

                break;

                //头像
                case R.id.image_avatar:
                    if (!ButtonUtils.isFastDoubleClick(R.id.image_avatar)){
                        ((MainActivity) getActivity()).start(ProfileFragment.newInstance());
                    }
                break;

                //立即开通
                case R.id.recharge:
                    if (!ButtonUtils.isFastDoubleClick(R.id.recharge)){
                        if (App.userMsg()!=null && App.userMsg().getProvider()!=null &&
                                App.userMsg().getProvider().getProviderStatus()!=null){
                            if (App.userMsg().getProvider().getProviderStatus().equals("1002010716") || App.userMsg().getProvider().getProviderStatus().equals("1002010710")){
                                Intent intent = new Intent(getActivity(), BuyVipActivity.class);
                                startActivity(intent);
                            }else{
                                initDialog();
                            }
                        }

                    }
                    break;

                //我的活动
                 case R.id.layout_active:
                     if (!ButtonUtils.isFastDoubleClick(R.id.layout_active)){
                         ((MainActivity) getActivity()).start(MyActiveFragment.newInstance());
                     }
                     break;

                //我的资产
                case R.id.layout_money:
                    if (!ButtonUtils.isFastDoubleClick(R.id.layout_money)){
                        ((MainActivity) getActivity()).start(MyMoneyFragment.newInstance());                    }

                break;

                //银行卡管理
                case R.id.layout_card:
                    if (!ButtonUtils.isFastDoubleClick(R.id.layout_card)){
                        ((MainActivity) getActivity()).start(MyBankCardFragment.newInstance());
                    }

                break;
                //发票管理
                case R.id.layout_fapiao:
                    if (!ButtonUtils.isFastDoubleClick(R.id.layout_fapiao)){
                        ((MainActivity) getActivity()).start(MyTicketFragment.newInstance());
                    }
                break;
                //代缴账单
                case R.id.layout_bill:
                   /* if (!ButtonUtils.isFastDoubleClick(R.id.layout_bill)){
                        ((MainActivity) getActivity()).start(WaitBillFragment.newInstance());
                    }*/

                    AdiUtils.showToast("敬请期待！");
                break;
                //门店管理
                 case R.id.unline_shop:
                     if (!ButtonUtils.isFastDoubleClick(R.id.unline_shop)){
                         ((MainActivity) getActivity()).start(UserShopFragment.newInstance());
                     }

                break;
                //客服中心
                 case R.id.waiter_center:
                     if (!ButtonUtils.isFastDoubleClick(R.id.waiter_center)){
                             View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_call, null);
                             MyMediumTextView phone = inflate.findViewById(R.id.phone);
                             MyNormalTextView textView3 = inflate.findViewById(R.id.textView3);
                             phone.setText("400-836205");
                           /*  MyMediumTextView sure = inflate.findViewById(R.id.sure);
                             sure.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     Intent intent = new Intent(Intent.ACTION_CALL);
                                     Uri data = Uri.parse("400-836205");
                                     intent.setData(data);
                                     startActivity(intent);
                                     baseDialog.dismiss();
                                 }
                             });
                             MyMediumTextView cancle = inflate.findViewById(R.id.cancle);
                             cancle.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     baseDialog.dismiss();
                                 }
                             });*/
                             baseDialog = new BaseDialog(getContext(), inflate, Gravity.CENTER);
                             baseDialog.show();
                     }
                break;
                //意见反馈
                case R.id.layout_feedback:
                    if (!ButtonUtils.isFastDoubleClick(R.id.layout_feedback)){
                        ((MainActivity) getActivity()).start(FeedbackFragment.newInstance());
                    }

                break;

        }
    }

    private void initDialog() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_notifation, null);
        MyNormalTextView desc = inflate.findViewById(R.id.desc);
        MyMediumTextView ok = inflate.findViewById(R.id.ok);

        desc.setText("您的门店还没通过审核！");

        ok.setOnClickListener(v -> baseDialog2.dismiss());
        baseDialog2 = new BaseDialog(getContext(), inflate, Gravity.CENTER);
        baseDialog2.show();
    }
}

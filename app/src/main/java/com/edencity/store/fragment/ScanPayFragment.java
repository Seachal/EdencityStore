package com.edencity.store.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.home.activity.ScanFailedActivity;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.pojo.EventMessage;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.Logger;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import com.edencity.store.App;
import com.edencity.store.base.BaseDialog;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.home.activity.MainActivity;
import com.edencity.store.R;
import com.edencity.store.pojo.FuncResult;
import com.edencity.store.util.StringUtil;
import com.edencity.store.util.ToastUtil;
import com.edencity.store.view.AlertDialog;
import com.edencity.store.view.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanPayFragment extends BaseFragment2 {

    @BindView(R.id.edit_fee)
    EditText totalFeeEdit;
    @BindView(R.id.btn_back)
    ImageButton btn_back;
    @BindView(R.id.btn_submit)
    MyMediumTextView btn_submit;

    private BigDecimal payFee;
    private String providerId;
    private String storeName;
    private String storePhoto;
    private BaseDialog baseDialog;

    public ScanPayFragment() {
        // Required empty public constructor
    }

    public static ScanPayFragment newInstance(String providerId){
        ScanPayFragment fragment = new ScanPayFragment();
        Bundle args = new Bundle();
        args.putString("provider_id", providerId);
        fragment.setArguments(args);
        return fragment;
    }
   public static ScanPayFragment newInstance(){
        ScanPayFragment fragment = new ScanPayFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            providerId = getArguments().getString("provider_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_scan_pay, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        totalFeeEdit.requestFocus();
        totalFeeEdit.addTextChangedListener(textWatcher);
        ((MainActivity)getActivity()).showSoftKeyboard(totalFeeEdit);
    }

    //处理验证码输入
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3);
                    totalFeeEdit.setText(s);
                    totalFeeEdit.setSelection(s.length());
                }
            } else {
                if (s.length() > 8) {
                    totalFeeEdit.setText(s.subSequence(0, 8));
                    totalFeeEdit.setSelection(s.length() - 1);
                }
            }

            if (s.toString().trim().substring(0).equals(".")) {
                s = "0" + s;
                totalFeeEdit.setText(s);
                totalFeeEdit.setSelection(2);
            }

            if (s.toString().startsWith("0")
                    && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    totalFeeEdit.setText(s.subSequence(0, 1));
                    totalFeeEdit.setSelection(1);
                    return;
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        ((MainActivity)getActivity()).hideSoftKeyboard();
    }

    @Override
    public void onViewItemClicked(View view) {
        if (view.getId()==R.id.btn_back){
            pop();
        }else if (view.getId()==R.id.btn_submit){
            onPay();
           /* initDialogNoMoney();*/
        }
    }

    private void initDialogNoMoney() {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_no_money, null);
        ImageView close = inflate.findViewById(R.id.close);
        //充值
        TextView recharge = inflate.findViewById(R.id.go_to_recherge);
        //剩余伊甸币
        TextView total_money = inflate.findViewById(R.id.money_total);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.dismiss();
            }
        });

        baseDialog = new BaseDialog(getActivity(), inflate, Gravity.CENTER);
        baseDialog.show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage(EventMessage message) {
        if (message.type == EventMessage.EVENT_QRCODE && message.data != null) {
            onScanPay((String) message.data);
        }
    }

    private void onScanPay(String qrcode) {
        Log.e("aaa",qrcode);
        //扫码支付，检查扫到的二维码是否是伊甸城的有效商户

        final String[] codes = qrcode.split("\\$");
        if (codes.length != 2) {
            Intent intent = new Intent(getActivity(), ScanFailedActivity.class);
            intent.putExtra("result",qrcode);
            startActivity(intent);
            return;
        }

        String price = (String)App.getCache("price");
        if (price!=null && !price.equals("")){
            receiverMoney(codes[0],codes[1],price);
            App.removeCache("price");
        }

    }

    private void receiverMoney(String qrcode ,String stub,String price) {
        //将以上参数排序，拼接keySecret
        HashMap hashMap = ParamsUtils.getParamsMap("payCode", qrcode,
                "stub",stub,"dealAmount",price);
        String sign = ParamsUtils.getSign(hashMap);
        try {
            //加密
            hashMap.put("sign", SHA1Utils.strToSHA1(sign));

        } catch (Exception e) {
            e.printStackTrace();
        }
        DataService.getHomeService().storeReceive(hashMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    private String price2;

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {

                        if (baseResult.getResult_code() == 0) {

                            pop();

                            if (price.contains(".")){
                                price2 = price;
                            }else{
                                 price2 = price+".00";
                            }
                            ((MainActivity)getActivity()).start(PayResultFragment2.newInstance(
                                    true,"收款成功",price2+"伊甸果"));
                            ((MainActivity)getActivity()).hideSoftKeyboard();

                        }else if (baseResult.getResult_code()== -3){
                            AdiUtils.loginOut();
                        }else {
                            ((MainActivity)getActivity()).hideSoftKeyboard();
                            ((MainActivity) getActivity()).start(PayResultFragment2.
                                    newInstance(false,"收款失败","失败原因："+baseResult.getResult_msg()));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    private void onPay(){
        payFee=StringUtil.parseDecimal(totalFeeEdit.getText().toString().trim(),BigDecimal.ZERO);
        if (payFee.compareTo(BigDecimal.ZERO)<=0){
            ToastUtil.showToast(getContext(),"请输入有效的支付金额");
            return;
        }
        ((MainActivity) getActivity()).doQrcodeScan();
        App.setCache("price",totalFeeEdit.getText().toString().trim());

    }

}

package com.edencity.store.user.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.base.BaseDialog;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.base.IBaseCallBack;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.CountBillEntity;
import com.edencity.store.entity.UserMsgEntity;
import com.edencity.store.home.activity.MainActivity;
import com.edencity.store.login.activity.LoginActivity;
import com.edencity.store.user.activity.CountDetailActivity;
import com.edencity.store.user.activity.UpdatePayPwdActivity;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.ButtonUtils;
import com.edencity.store.util.DeeSpUtil;
import com.edencity.store.util.ParamsUtils;
import com.edencity.store.util.SHA1Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoneyCountFragment extends BaseFragment2 {


    private ImageView back;
    private MyMediumTextView bank_name;
    private MyMediumTextView bank_card;
    private MyMediumTextView count;
    private MyMediumTextView max_money;
    private EditText et_total_money;
    private MyNormalTextView reduce_money;
    private MyNormalTextView total_get;


    private EditText et_verify_code_1;
    private EditText et_verify_code_2;
    private EditText et_verify_code_3;
    private EditText et_verify_code_4;
    private EditText et_verify_code_5;
    private EditText et_verify_code_6;
    private EditText curEditText;
    private BaseDialog baseDialog2;
    private EditText et_verify_code_7;
    private MyNormalTextView mTag;
    private MyNormalTextView rechage_guo;
    private MyNormalTextView song_guo;

    public MoneyCountFragment() {
        // Required empty public constructor
    }

    public static MoneyCountFragment getInstance() {
        return new MoneyCountFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_money_count, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

        StatusBarCompat.changeToLightStatusBar(getActivity());
        String userId = DeeSpUtil.getInstance().getString("userId");
        String ticket = DeeSpUtil.getInstance().getString("ticket");
        DataService.getInstance().syncUser(userId, ticket, new IBaseCallBack<UserMsgEntity>() {
            @Override
            public void onSuccess(UserMsgEntity data) {
                if (data!=null){
                    App.defaultApp().saveUserMsg(data);
                }
                sysn();
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void sysn() {
        UserMsgEntity.ProviderBean provider = App.userMsg().getProvider();
        BigDecimal decimal = new BigDecimal(provider.getGross() - provider.getFrozenProvision());
        max_money.setText(decimal.setScale(2,RoundingMode.HALF_UP)+"");
        bank_name.setText(provider.getBank());
        if (provider.getBankNumber().length() > 4) {
            String substring = provider.getBankNumber().substring(provider.getBankNumber().length() - 4);
            bank_card.setText("尾号" + substring);
        } else {
            bank_card.setText(provider.getBankNumber());
        }
        float settlementRate = provider.getSettlementRate();
        String v = settlementRate + "";
        /*String substring = v.substring(v.indexOf(".")+1);*/
        reduce_money.setText(v + "%");
        et_total_money.setText(decimal.setScale(2,RoundingMode.HALF_UP)+"");

        /*float a = ( 1- settlementRate/100) *(provider.getGross() - provider.getFrozenProvision());*/
        BigDecimal bigDecimal = new BigDecimal(et_total_money.getText().toString());
        BigDecimal settlementFee =
                (bigDecimal.multiply(new BigDecimal(settlementRate))
                        .divide(new BigDecimal(100)));

        total_get.setText(bigDecimal.subtract(settlementFee).setScale(2, RoundingMode.HALF_UP) + "");
    }

    private void initView(View inflate) {
        back = inflate.findViewById(R.id.back);
        back.setOnClickListener(v -> pop());
        bank_name = inflate.findViewById(R.id.bank_name);
        bank_card = inflate.findViewById(R.id.bank_card);
        rechage_guo = inflate.findViewById(R.id.rechage_guo);
        song_guo = inflate.findViewById(R.id.song_guo);


        //结算按钮
        count = inflate.findViewById(R.id.count);
        //可结算
        max_money = inflate.findViewById(R.id.max_money);
        //当前结算
        et_total_money = inflate.findViewById(R.id.et_total_money);
        //折扣
        reduce_money = inflate.findViewById(R.id.reduce_money);
        //实际到账
        total_get = inflate.findViewById(R.id.total_get);
        et_total_money.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        count.setOnClickListener(v -> {
            if (!ButtonUtils.isFastDoubleClick(R.id.count)) {
                if (App.userMsg().getProvider().getPayPassword()!=null
                        && App.userMsg().getProvider().getPayPassword().equals("true")) {
                    if (Float.parseFloat(et_total_money.getText().toString().trim()) >= 100) {
                        getPayPwd();
                        et_verify_code_1.requestFocus();
                        ((MainActivity) getActivity()).showSoftKeyboard(et_verify_code_1);
                    } else {
                        AdiUtils.showToast("单次结算不能低于100伊甸果");
                    }
                } else {
                    View inflate2 = getLayoutInflater().inflate(R.layout.dialog_no_paypwd, null);

                    RelativeLayout close = inflate2.findViewById(R.id.ll);
                    MyMediumTextView sure = inflate2.findViewById(R.id.sure);
                    MyMediumTextView cancle = inflate2.findViewById(R.id.cancle);

                    close.setOnClickListener(view -> baseDialog2.dismiss());
                    cancle.setOnClickListener(view -> baseDialog2.dismiss());

                    sure.setOnClickListener(view -> {
                        Intent intent = new Intent(getActivity(), UpdatePayPwdActivity.class);
                        startActivity(intent);
                        baseDialog2.dismiss();
                    });
                    baseDialog2 = new BaseDialog(getActivity(), inflate2, Gravity.CENTER);
                    baseDialog2.show();
                }

            }

        });
        et_total_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int unt) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        et_total_money.setText(s);
                        et_total_money.setSelection(s.length());
                    }
                } else {
                    if (s.length() > 8) {
                        et_total_money.setText(s.subSequence(0, 8));
                        et_total_money.setSelection(s.length() - 1);
                    }
                }

                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    et_total_money.setText(s);
                    et_total_money.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        et_total_money.setText(s.subSequence(0, 1));
                        et_total_money.setSelection(1);
                        return;
                    }
                }
                if (s.toString().equals("0")
                        || s.toString().equals("0.0")
                        || s.toString().equals("0.00")
                        || s.toString().equals("")
                        || Float.parseFloat(s.toString()) > (App.userMsg().getProvider().getGross() -
                        App.userMsg().getProvider().getFrozenProvision())
                        || s.toString().equals("0.")) {
                    count.setEnabled(false);
                    count.setBackgroundResource(R.drawable.text_bg_gray);
                } else {
                    count.setEnabled(true);
                    count.setBackgroundResource(R.drawable.text_bg_blue);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_total_money.getText().toString().length() != 0) {
                    float settlementRate = App.userMsg().getProvider().getSettlementRate();
                    BigDecimal bigDecimal = new BigDecimal(et_total_money.getText().toString());
                    BigDecimal settlementFee =
                            (bigDecimal.multiply(new BigDecimal(settlementRate))
                                    .divide(new BigDecimal(100)));
                    total_get.setText(bigDecimal
                            .subtract(settlementFee)
                            .setScale(2, RoundingMode.HALF_UP) + "");
                } else {
                    total_get.setText("0");
                }

            }
        });
    }

    private void getPayPwd() {

        View inflate = getLayoutInflater().inflate(R.layout.dialog_pay_pwd, null);
        ImageView close = inflate.findViewById(R.id.close);
        RelativeLayout null_layout = inflate.findViewById(R.id.null_layout);
        et_verify_code_1 = inflate.findViewById(R.id.et_verify_code_1);
        et_verify_code_2 = inflate.findViewById(R.id.et_verify_code_2);
        et_verify_code_3 = inflate.findViewById(R.id.et_verify_code_3);
        et_verify_code_4 = inflate.findViewById(R.id.et_verify_code_4);
        et_verify_code_5 = inflate.findViewById(R.id.et_verify_code_5);
        et_verify_code_6 = inflate.findViewById(R.id.et_verify_code_6);
        et_verify_code_7 = inflate.findViewById(R.id.et_verify_code_7);

        initEditText();
        mTag = inflate.findViewById(R.id.tag_text);
        MyNormalTextView mForget = inflate.findViewById(R.id.forget);
        MyMediumTextView price = inflate.findViewById(R.id.all_price);
        price.setText(et_total_money.getText().toString());
        mTag.setVisibility(View.GONE);
        close.setOnClickListener(v -> baseDialog2.dismiss());


        baseDialog2 = new BaseDialog(getActivity(), inflate, Gravity.BOTTOM);
        baseDialog2.show();

    }

    private void initEditText() {
        //处理验证码输入
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0 && curEditText != null) {
                    if (curEditText.getId() == R.id.et_verify_code_1) {
                        et_verify_code_2.requestFocus();
                    } else if (curEditText.getId() == R.id.et_verify_code_2) {
                        et_verify_code_3.requestFocus();
                    } else if (curEditText.getId() == R.id.et_verify_code_3) {
                        et_verify_code_4.requestFocus();
                    } else if (curEditText.getId() == R.id.et_verify_code_4) {
                        et_verify_code_5.requestFocus();
                    } else if (curEditText.getId() == R.id.et_verify_code_5) {
                        et_verify_code_6.requestFocus();
                    } else if (curEditText.getId() == R.id.et_verify_code_6) {
                        onVerifyCode(et_total_money.getText().toString());
                    }
                }
            }
        };

        View.OnKeyListener onKeyListener = (view, i, keyEvent) -> {
            if (view instanceof EditText) {
                EditText curEdit = (EditText) view;
                if (i == KeyEvent.KEYCODE_DEL) {
                    if (curEdit.getText().length() > 0) {
                        curEdit.setText(null);
                    } else {
                        if (curEdit.getId() == R.id.et_verify_code_2) {
                            et_verify_code_1.setText(null);
                            et_verify_code_1.requestFocus();
                        } else if (curEdit.getId() == R.id.et_verify_code_3) {
                            et_verify_code_2.setText(null);
                            et_verify_code_2.requestFocus();
                        } else if (curEdit.getId() == R.id.et_verify_code_4) {
                            et_verify_code_3.setText(null);
                            et_verify_code_3.requestFocus();
                        } else if (curEdit.getId() == R.id.et_verify_code_5) {
                            et_verify_code_4.setText(null);
                            et_verify_code_4.requestFocus();
                        } else if (curEdit.getId() == R.id.et_verify_code_6) {
                            et_verify_code_5.setText(null);
                            et_verify_code_5.requestFocus();
                        }
                    }
                } else if (i == KeyEvent.KEYCODE_ENTER) {
                    onVerifyCode(et_total_money.getText().toString());
                }
            }
            return false;
        };

        et_verify_code_1.setOnKeyListener(onKeyListener);
        et_verify_code_2.setOnKeyListener(onKeyListener);
        et_verify_code_3.setOnKeyListener(onKeyListener);
        et_verify_code_4.setOnKeyListener(onKeyListener);
        et_verify_code_5.setOnKeyListener(onKeyListener);
        et_verify_code_6.setOnKeyListener(onKeyListener);

        et_verify_code_1.addTextChangedListener(textWatcher);
        et_verify_code_2.addTextChangedListener(textWatcher);
        et_verify_code_3.addTextChangedListener(textWatcher);
        et_verify_code_4.addTextChangedListener(textWatcher);
        et_verify_code_5.addTextChangedListener(textWatcher);
        et_verify_code_6.addTextChangedListener(textWatcher);

        View.OnFocusChangeListener fouceListener = (view, b) -> {
            if (b && view instanceof EditText) {
                curEditText = (EditText) view;
            }
        };
        et_verify_code_5.setOnFocusChangeListener(fouceListener);
        et_verify_code_6.setOnFocusChangeListener(fouceListener);
        et_verify_code_3.setOnFocusChangeListener(fouceListener);
        et_verify_code_4.setOnFocusChangeListener(fouceListener);
        et_verify_code_1.setOnFocusChangeListener(fouceListener);
        et_verify_code_2.setOnFocusChangeListener(fouceListener);
    }

    private void onVerifyCode(String totalPrice) {
        final String code = et_verify_code_1.getText().toString()
                + et_verify_code_2.getText().toString()
                + et_verify_code_3.getText().toString()
                + et_verify_code_4.getText().toString()
                + et_verify_code_5.getText().toString()
                + et_verify_code_6.getText().toString();
        goToCount(code, totalPrice);
    }

    private void goToCount(String pwd, String totalPrice) {
        HashMap paramsMap = ParamsUtils.getParamsMap("balanceAmount", totalPrice,
                "payPassword", pwd);
        String sign = ParamsUtils.getSign(paramsMap);
        try {
            paramsMap.put("sign", SHA1Utils.strToSHA1(sign));
            Log.d("feed", paramsMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataService.getUserService().goToCountBill(paramsMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<CountBillEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<CountBillEntity> o) {
                        Log.d("feed", "----" + o.toString());
                        if (o.getResult_code() == 0) {
                            if (o.getData() != null && o.getData().getBalanceDetail() != null) {
                             /*   String s = et_total_money.getText().toString();
                                float v = Float.parseFloat(s);
                                float b = App.userMsg().getProvider().getGross() - v;

                                App.userMsg().getProvider().setGross(b);
                                App.userMsg().getProvider().setPossess(App.userMsg().getProvider().getPossess()+v);*/
                                Intent intent = new Intent(getContext(), CountDetailActivity.class);
                                CountBillEntity balanceDetail = o.getData();
                                intent.putExtra("id", balanceDetail);
                                intent.putExtra("type", "1");
                                startActivity(intent);
                                baseDialog2.dismiss();

                            }
                        }else if (o.getResult_code()== -3){
                            AdiUtils.loginOut();
                        } else {
                            AdiUtils.showToast(o.getResult_msg());
                            baseDialog2.dismiss();
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

}

package com.edencity.store.home.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.home.adapter.RechargeTypeAdapter;

public class OrderDetailActivity extends BaseActivity {

    private ImageView mBack;
    private ImageView mShopImg;

    /**
     * 30000.00
     */

    private MyMediumTextView mHadMoney;
    /**
     * 应缴账单日期2020-02-04
     */
    private MyMediumTextView mShouldPayTime;
    /**
     * 伊甸城4号楼2层506室
     */
    private MyNormalTextView mPlace;
    /**
     * 12270.00
     */
    private MyNormalTextView mShouldPay;
    /**
     * 12270.00
     */
    private MyNormalTextView mAllMoney;
    private RecyclerView mRlvType;
    private ImageView mCheck;
    /**
     * 伊甸币可抵扣3000.00
     */
    private MyMediumTextView mDikou;
    /**
     * 9270.00
     */
    private EditText mEtMoney;
    /**
     * 修改金额
     */
    private MyNormalTextView mUpdateMoney;
    /*
     * 支付9270.00
     */
    private MyMediumTextView mPay;
    private RechargeTypeAdapter adapter;
    private boolean isCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.changeToLightStatusBar(this);
        StatusBarCompat.setStatusBarColor(this,Color.WHITE);
        setContentView(R.layout.activity_order_detail);
        initView();
        initCheck();
    }

    private void initCheck() {

        ArrayList<BaseDebug> objects = new ArrayList<>();

        objects.add(new BaseDebug("微信支付",R.mipmap.check_blue,R.mipmap.check_gray,
                true,R.mipmap.wechat_green,R.mipmap.wechat_gray));

        objects.add(new BaseDebug("支付宝支付",R.mipmap.check_blue,R.mipmap.check_gray,
                false,R.mipmap.ali_blue,R.mipmap.ali_gay));

        mRlvType.setHasFixedSize(true);
        mRlvType.setNestedScrollingEnabled(false);
        mRlvType.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        adapter = new RechargeTypeAdapter(1);
        mRlvType.setAdapter(adapter);
        adapter.addData(objects);
        adapter.onItemCheck(new RechargeTypeAdapter.onItemCheckedListener() {
            @Override
            public void onItemCheck(int position) {
                adapter.changeState(position);
            }
        });

        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCheck){
                    isCheck = false ;
                    mCheck.setImageResource(R.mipmap.check_gray);

                }else{

                    isCheck = true ;
                    mCheck.setImageResource(R.mipmap.check_blue);
                }
            }
        });
        mCheck.setImageResource(R.mipmap.check_blue);
        showDeleteButton(mEtMoney,1,null);
        mUpdateMoney.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View v) {

                mEtMoney.setFocusableInTouchMode(true);
                mEtMoney.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mEtMoney,0);
                mEtMoney.setSelection(mEtMoney.getText().toString().length());
                mEtMoney.setCompoundDrawablesWithIntrinsicBounds(null,
                        null, App.defaultApp().getResources().getDrawable(R.mipmap.closeee), null);

                mEtMoney.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        //获取到下标为2，也就是drawableRight的图片
                        Drawable drawables = mEtMoney.getCompoundDrawables()[2];
                        //如果为空说明未设置drawableRight的图片,直接返回
                        if (drawables == null) {
                            return false;
                        }
                        //如果用户不是点击的图片，也返回
                        if (motionEvent.getAction() != MotionEvent.ACTION_UP) {
                            return false;
                        }
                        //当发生点击事件的X轴坐标在 超过了输入框字符长度加上与到图片的距离的长度
                        //即点击事件发生在图片上，删除文字，随后删除按钮
                        if (motionEvent.getX() > mEtMoney.getWidth()
                                - mEtMoney.getPaddingRight()
                                - drawables.getIntrinsicWidth()) {
                            mEtMoney.setText("");
                            mEtMoney.setCompoundDrawablesWithIntrinsicBounds(null,
                                    null, null, null);
                        }
                        return false;

                    }
                });
            }
        });
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                finish();
            }
        });
        mShopImg = (ImageView) findViewById(R.id.shop_img);
        mHadMoney = (MyMediumTextView) findViewById(R.id.had_money);
        mShouldPayTime = (MyMediumTextView) findViewById(R.id.should_pay_time);
        mPlace = (MyNormalTextView) findViewById(R.id.place);
        mShouldPay = (MyNormalTextView) findViewById(R.id.should_pay);
        mAllMoney = (MyNormalTextView) findViewById(R.id.all_money);
        mRlvType = (RecyclerView) findViewById(R.id.rlv_type);
        mCheck = (ImageView) findViewById(R.id.check);
        mDikou = (MyMediumTextView) findViewById(R.id.dikou);
        mEtMoney = (EditText) findViewById(R.id.et_money);
        mUpdateMoney = (MyNormalTextView) findViewById(R.id.update_money);
        mPay = (MyMediumTextView) findViewById(R.id.pay);
    }
    public static void showDeleteButton(final EditText editText, int mode, CheckBox view) {


        //禁止回车键
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return false;
            }
        });


            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    //当输入框内容长度改变显示按钮,按钮图片自己添加
                    editText.setCompoundDrawablesWithIntrinsicBounds(null,
                            null, App.defaultApp().getResources().getDrawable(R.mipmap.closeee), null);

                    //输入框字符删除完，按钮消失
                    if (charSequence.length() == 0) {
                        editText.setCompoundDrawablesWithIntrinsicBounds(null,
                                null, null, null);
                    }

                }

                @SuppressLint("ClickableViewAccessibility")
                @Override
                public void afterTextChanged(Editable editable) {
                    //删除按钮的点击事件
                    editText.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            //获取到下标为2，也就是drawableRight的图片
                            Drawable drawables = editText.getCompoundDrawables()[2];
                            //如果为空说明未设置drawableRight的图片,直接返回
                            if (drawables == null) {
                                return false;
                            }
                            //如果用户不是点击的图片，也返回
                            if (motionEvent.getAction() != MotionEvent.ACTION_UP) {
                                return false;
                            }
                            //当发生点击事件的X轴坐标在 超过了输入框字符长度加上与到图片的距离的长度
                            //即点击事件发生在图片上，删除文字，随后删除按钮
                            if (motionEvent.getX() > editText.getWidth()
                                    - editText.getPaddingRight()
                                    - drawables.getIntrinsicWidth()) {
                                editText.setText("");
                                editText.setCompoundDrawablesWithIntrinsicBounds(null,
                                        null, null, null);
                            }
                            return false;

                        }
                    });
                }
            });
        }
    }

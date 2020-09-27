package com.edencity.store.user.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.entity.BaseDebug;
import com.edencity.store.user.adapter.FeedMsgAdapter;

public class FeedMsgActivity extends BaseActivity {

    private ImageView mBack;
    private RecyclerView mRlvMsg;
    private SmartRefreshLayout mSmart;
    private MyMediumTextView mChangeState;
    private int type;
    private boolean isCkeck = false;
    private FeedMsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        StatusBarCompat.changeToLightStatusBar(this);
        setContentView(R.layout.fragment_feed_msg);
        Intent intent = getIntent();
         type = intent.getIntExtra("type", 0);
        initView();
        initRlv();
        initData();
    }

    private void initRlv() {
         mRlvMsg.setLayoutManager(new LinearLayoutManager(this));
         adapter = new FeedMsgAdapter();
         mRlvMsg.setAdapter(adapter);
    }

    private void initData() {
        ArrayList<BaseDebug> objects = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            objects.add(new BaseDebug("反馈回复"+i,
                    "我的吧hiufvbhewuvbfywhergvbowjiebwjhjefvjnwe无附件为萨芬的辅导费你说的你看的歌我如果范围",
                    "2020-01-18",
                    false));
        }
        adapter.addData(objects);
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRlvMsg = (RecyclerView) findViewById(R.id.rlv_msg);
        mSmart = (SmartRefreshLayout) findViewById(R.id.smart);
        mChangeState = findViewById(R.id.change_read_state);


        if (type==0){
            mChangeState.setText("全部标为未读");
            isCkeck = false;
        }else{
            mChangeState.setText("全部标为已读");
            isCkeck = true ;
        }


        mChangeState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCkeck){
                    isCkeck = false;
                    mChangeState.setText("全部标为未读");
                    adapter.changeState(isCkeck);
                }else{
                    mChangeState.setText("全部标为已读");
                    isCkeck = true;
                    adapter.changeState(isCkeck);
                }
            }
        });
    }
}

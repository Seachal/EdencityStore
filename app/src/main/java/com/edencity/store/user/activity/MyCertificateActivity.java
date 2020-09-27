package com.edencity.store.user.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

import com.edencity.store.R;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.user.adapter.MyCertVpAdapter;
import com.edencity.store.user.fragment.MyCertFragment;
import com.umeng.message.PushAgent;

import me.yokeyword.fragmentation.SupportActivity;

public class MyCertificateActivity extends SupportActivity {

    private ImageView mBack;
    private SmartTabLayout mTabLayout;
    private ViewPager mVp;
    private ArrayList<String> mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(this).onAppStart();
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);
        StatusBarCompat.changeToLightStatusBar(this);
        setContentView(R.layout.activity_my_certificate);
        initView();
        initLogic();
    }

    private void initLogic() {



        mTitle = new ArrayList<>();
        mTitle.add("新预订");
        mTitle.add("已确认");
        mTitle.add("已结束");
        ArrayList<Fragment> objects = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            MyCertFragment myCertFragment = new MyCertFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("tag",i);
            myCertFragment.setArguments(bundle);
            objects.add(myCertFragment);
        }

        MyCertVpAdapter vpAdapter = new MyCertVpAdapter(getSupportFragmentManager(), objects,mTitle);
        mVp.setAdapter(vpAdapter);
        mTabLayout.setViewPager(mVp);
    }


   /* public View updatePoint( int position,String text , int visibility) {
        View tabView = LayoutInflater.from(this).inflate(R.layout.item_tablayout, null);
        MyMediumTextView tabTitle =  tabView.findViewById(R.id.title);
        TextView point =  tabView.findViewById(R.id.point);
        point.setText(text);
        point.setVisibility(visibility);
        tabTitle.setText(mTitle.get(position));
        return tabView;
    }*/

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTabLayout = (SmartTabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}

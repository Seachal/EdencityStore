package com.edencity.store.user.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.dtr.zxing.utils.ZXingUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;

import com.edencity.store.R;
import com.edencity.store.base.BaseDialog;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.home.adapter.MyReadyAdapter;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.DisplayInfoUtils;
import com.edencity.store.util.ResUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCertFragment extends BaseFragment2 {


    private SmartRefreshLayout mSmart;
    private RecyclerView mRlv;
    private int type;
        private BaseDialog baseDialog;
    private HashMap hashMap;
    private ConstraintLayout mLoadFail;
    private MyNormalTextView nTag;

    public MyCertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my_cert, container, false);
        Bundle bundle = getArguments();
        if (bundle!=null){
            type = bundle.getInt("tag");
        }
        initView(inflate);
        initList();
                return inflate;
    }

    private void initList() {

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));

        MyReadyAdapter adapter = new MyReadyAdapter(type);
        mRlv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.onReadyListener(new MyReadyAdapter.onSureClickListener() {
            @Override
            public void onSure(int position) {
                AdiUtils.showToast("预定成功");
            }

            @Override
            public void onCancle(int position) {

                AdiUtils.showToast("不可预订");
            }

            @Override
            public void onDismiss(int position) {

                AdiUtils.showToast("取消预订");
            }
        });

    }


    private void initCertCodeDialog(String Qcode) {
        float dimens = ResUtils.getDimens(R.dimen.dp_200);
        int i = DisplayInfoUtils.getInstance().dp2pxInt(dimens);
        Bitmap qrImage = ZXingUtil.createQRImage(Qcode,i,
                i);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.cert_code_dialog, null);
        ImageView close = inflate.findViewById(R.id.close);
        ImageView code = inflate.findViewById(R.id.iv_code);
        code.setImageBitmap(qrImage);
        ConstraintLayout null_area = inflate.findViewById(R.id.null_area);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.dismiss();
            }
        });
        null_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.dismiss();
            }
        });
         baseDialog = new BaseDialog(getContext(), inflate, Gravity.CENTER);
         baseDialog.show();
    }

    private void initView(View inflate) {
        mSmart = inflate.findViewById(R.id.smart);
        mRlv = inflate.findViewById(R.id.rlv_cert);
        mLoadFail = inflate.findViewById(R.id.loadfail);
        nTag = inflate.findViewById(R.id.tag_text);
    }

}

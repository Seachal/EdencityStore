package com.edencity.store.user.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.base.BaseDialog;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.entity.UserMsgEntity;
import com.edencity.store.pojo.EventMessage;
import com.edencity.store.pojo.FuncResult;
import com.edencity.store.user.adapter.FullyGridLayoutManager;
import com.edencity.store.user.adapter.GridImageAdapter;
import com.edencity.store.util.ToastUtil;
import com.edencity.store.view.LoadingDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

/*import cn.zhongyu.edencity.user.adapter.GridImageAdapter;*/


/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends BaseFragment2 {

    @BindView(R.id.name)
    MyMediumTextView name;
    @BindView(R.id.phone)
    MyNormalTextView phone;
    @BindView(R.id.address)
    MyNormalTextView address;


    public static AddressFragment newInstance() {
        return new AddressFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        UserMsgEntity.ProviderBean provider = App.userMsg().getProvider();
        name.setText(provider.getStoreName()==null?"":provider.getStoreName());
        phone.setText(provider.getPhone()==null?"":provider.getPhone());
        address.setText(provider.getLocation()+provider.getStoreDetailAddress());
    }

    @Override
    public void onViewItemClicked(View view) {
        if (view.getId() == R.id.btn_back) {
            pop();
        }
    }

}

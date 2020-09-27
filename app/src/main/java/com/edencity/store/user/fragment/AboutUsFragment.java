package com.edencity.store.user.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends BaseFragment2 {


    @BindView(R.id.btn_back)
    ImageButton mBack;
    @BindView(R.id.icon)
    ImageView mIcon;
    @BindView(R.id.version)
    TextView mVersion;

    public AboutUsFragment() {
        // Required empty public constructor
    }

    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this, view);
        initVIew();
        return view;
    }

    private void initVIew() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }


}

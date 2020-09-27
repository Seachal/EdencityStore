package com.edencity.store.user.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.edencity.store.App;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.home.activity.MainActivity;
import com.edencity.store.R;
import com.edencity.store.pojo.EventMessage;
import com.edencity.store.pojo.FuncResult;
import com.edencity.store.util.IDCardUtil;
import com.edencity.store.util.ToastUtil;
import com.edencity.store.view.AlertDialog;
import com.edencity.store.view.LoadingDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class IdentityVerifyFragment extends BaseFragment2 {

    @BindView(R.id.edit_name)
    EditText mNameView;
    @BindView(R.id.edit_card_no)
    EditText mCardNoView;
    @BindView(R.id.image_id_front)
    ImageView mFrontImage;
    @BindView(R.id.image_id_back)
    ImageView mBackImage;

    private String mFrontImagePath;
    private String mBackImagePath;


    private boolean mIsFrontImageClicked;

    public IdentityVerifyFragment() {
    }

    public static IdentityVerifyFragment newInstance() {
        return new IdentityVerifyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_identity_verify, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        StatusBarCompat.changeToLightStatusBar(getActivity());
    }

    @Override
    public void onViewItemClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back: {
                pop();
            }
            break;
           /* case R.id.btn_why_id_verify: {
                //显示使用协议

            }break;*/
            case R.id.image_id_front: {
                mIsFrontImageClicked = true;
                ((MainActivity) getActivity()).getPhoto(3, 2, 1280, 800);
            }
            break;
            case R.id.image_id_back: {
                mIsFrontImageClicked = false;
                ((MainActivity) getActivity()).getPhoto(3, 2, 1280, 800);
            }
            break;
            case R.id.btn_submit: {
                onSubmit();
            }
            break;
        }
    }

    private void onSubmit() {
        final String userName = mNameView.getText().toString().trim();
        final String idNo = mCardNoView.getText().toString().trim();

        if (userName.length() < 2 || userName.length() > 5) {
            ToastUtil.showToast(getContext(), "请输入正确的姓名");
            return;
        }
        if (idNo.length() != 18 || !IDCardUtil.isValid(idNo)) {
            ToastUtil.showToast(getContext(), "请输入正确的身份证号");
            return;
        }

        if (mFrontImagePath == null || mBackImagePath == null) {
            ToastUtil.showToast(getContext(), "请选择身份证照片");
            return;
        }

        LoadingDialog.showLoading(getFragmentManager());

       /* App.execute(()->{
            final FuncResult fr=App.webService().updateUserApproval(App.userMsg().getCustomer().getUserId(),
                    App.userMsg().getTicket(),userName,idNo,new File(mFrontImagePath),new File(mBackImagePath));
            getActivity().runOnUiThread(()->{
                LoadingDialog.hideLoading();
                if (fr.code==0){
                    App.userMsg().getCustomer().setUserApproval("1010011411");
                    AlertDialog.showAlert(getFragmentManager(),"您的身份信息已经提交成功，请耐心等待审核完成。",dialogInterface -> {
                        pop();
                    });
                }else {
                    ToastUtil.showToast(getContext(),fr.msg);
                }
            });
        });*/
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPhotoSuccess(EventMessage message) {
        try {
            if (message.type == EventMessage.EVENT_PHOTO && message.data != null) {
                Bitmap image = BitmapFactory.decodeFile((String) message.data);
                if (mIsFrontImageClicked) {
                    mFrontImagePath = (String) message.data;
                    mFrontImage.setImageBitmap(image);
                } else {
                    mBackImagePath = (String) message.data;
                    mBackImage.setImageBitmap(image);
                }
            }
        } catch (Exception e) {
            ToastUtil.showToast(getContext(), "处理图像出现错误，请您重试");
        }
    }
}

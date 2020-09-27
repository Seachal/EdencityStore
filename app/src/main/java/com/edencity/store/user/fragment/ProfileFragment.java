package com.edencity.store.user.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.edencity.store.App;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.entity.UserMsgEntity;
import com.edencity.store.home.activity.MainActivity;
import com.edencity.store.R;
import com.edencity.store.pojo.EventMessage;
import com.edencity.store.user.activity.UpdateUserNameActivity;
import com.edencity.store.util.ButtonUtils;
import com.edencity.store.util.RegexUtils;
import com.edencity.store.util.ToastUtil;
import com.edencity.store.view.LoadingDialog;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment2 {

    @BindView(R.id.profile_image)
    ImageView avatarView;
    @BindView(R.id.btn_back)
    ImageButton mBtn_back;
    @BindView(R.id.shop_name)
    MyNormalTextView mShop_name;
    @BindView(R.id.user_level)
    MyNormalTextView mUser_level;
    @BindView(R.id.user_phone)
    MyNormalTextView mUser_phone;
    @BindView(R.id.user_email)
    MyNormalTextView mUser_email;
    @BindView(R.id.user_adddress)
    MyNormalTextView mUser_adddress;
    @BindView(R.id.user_ticket)
    MyNormalTextView mUser_ticket;



    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        EventBus.getDefault().register(this);

        return view;
    }

    private void initView() {

        mUser_phone.setText(RegexUtils.hidePhone(App.userMsg().getProvider().getPhone()));
        mUser_email.setText("暂无");
        UserMsgEntity userMsg = App.userMsg();
        mUser_level.setText(App.userMsg().getProvider().getUserVipLevel());
        mShop_name.setText(userMsg.getProvider().getStoreName());
        if (App.userMsg().getProvider().getStoreFacadeImg()!=null && !App.userMsg().getProvider().getStoreFacadeImg().equals("")){
            Picasso.with(getContext()).load(userMsg.getProvider().getStoreFacadeImg()).into(avatarView);
        }
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

        StatusBarCompat.changeToLightStatusBar(getActivity());
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewItemClicked(View view) {
        switch (view.getId()){
            case R.id.btn_back: {
                pop();
            }break;
            //头像
            case R.id.layout_avatar: {
/*
                if (!ButtonUtils.isFastDoubleClick(R.id.layout_avatar)){
                    ((MainActivity)getActivity()).getPhoto(1,1,200,200);
                }
*/

            }break;
            //成员等级
            case R.id.user_level: {
                if (!ButtonUtils.isFastDoubleClick(R.id.user_level)){

                }
            }break;
            //品牌名称
            case R.id.shop_name: {
             /*   if (!ButtonUtils.isFastDoubleClick(R.id.shop_name)){
                    Intent intent = new Intent(getActivity(), UpdateUserNameActivity.class);
                    if (mShop_name.getText().toString()==null||!mShop_name.getText().toString().equals("")){
                        intent.putExtra("name",mShop_name.getText().toString().trim());
                    }
                    startActivityForResult(intent,200);
                }*/

            }break;
            //手机
            case R.id.user_phone: {
                if (!ButtonUtils.isFastDoubleClick(R.id.user_phone)){

                }
            }break;
            //邮箱
            case R.id.user_email: {
                if (!ButtonUtils.isFastDoubleClick(R.id.user_email)){

                }
            }break;
            //地址
            case R.id.user_adddress: {
                if (!ButtonUtils.isFastDoubleClick(R.id.user_adddress)){
                    ((MainActivity)getActivity()).start(AddressFragment.newInstance());
                }

            }break;
            //发票
            case R.id.user_ticket: {
                if (!ButtonUtils.isFastDoubleClick(R.id.user_ticket)){
                    ((MainActivity)getActivity()).start(FaPiaoFragment.newInstance());
                }
            }break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==200&&resultCode==400){
            mShop_name.setText(data.getStringExtra("namee"));
        }
    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onPhotoSuccess(EventMessage message) {
        try {
            if (message.type == EventMessage.EVENT_PHOTO && message.data!=null){
                Bitmap image=BitmapFactory.decodeFile((String)message.data);
                avatarView.setImageBitmap(image);
                LoadingDialog.showLoading(getFragmentManager());
               /* App.execute(()->{
                    final FuncResult fr=App.webService().updateUserMsg("portrait",new File((String)message.data),
                            mShop_name.getText().toString().trim());
                    getActivity().runOnUiThread(()->{
                        LoadingDialog.hideLoading();
                        if (fr.code==0){
                           *//* App.curUser().userAvatar= (String) fr.data;
                            Picasso.with(getContext()).load((String) fr.data).into(avatarView);*//*
                           AdiUtils.showToast("修改成功");
                            Log.e("user",fr.toString());
                        }else if (fr.code==1){
                            AdiUtils.showToast(fr.msg);
                            App.defaultApp().saveCustomer(null);
                            App.defaultApp().saveUserMsg(null);
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else {
                            Log.e("user",fr==null?"":fr.toString());
                            ToastUtil.showToast(getContext(),fr.msg==null?"更新用户信息出错，请重试":fr.msg);
                        }
                    });
                });*/
            }
        }catch (Exception e){
            ToastUtil.showToast(getContext(),"处理图像出现错误，请您重试");
        }
    }

}

package com.edencity.store.home.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.dtr.zxing.activity.CaptureActivity;

import com.edencity.store.base.BaseDialog;
import com.edencity.store.base.BaseEventMsg;
import com.edencity.store.base.IBaseCallBack;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.data.DataService;
import com.edencity.store.entity.UserMsgEntity;
import com.edencity.store.user.activity.UpdateLoginPwdActivity;
import com.luck.picture.lib.config.PictureConfig;
import com.umeng.message.PushAgent;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;
import java.util.Locale;

import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.base.BaseFragment2;
import com.edencity.store.home.fragment.IndexFragment;
import com.edencity.store.pojo.EventMessage;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivity;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends SupportActivity implements EasyPermissions.PermissionCallbacks{

    private boolean mFirstLaunch;

    private RelativeLayout mainContainer;
    //照片和剪裁
    private boolean cropAfterCapture;
    private int cropRatioX=0,cropRatioY=0;
    private int cropMaxWidth=0,cropMaxHeight=0;
    private File cropedImageFile;
    private BaseDialog baseDialog;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_main);

        mFirstLaunch = true;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        mainContainer = findViewById(R.id.main_container);
        //设置状态栏透明
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE );
        }else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        loadRootFragment(R.id.main_container, IndexFragment.newInstance());
        if(Build.VERSION.SDK_INT>=23){
            if (EasyPermissions.hasPermissions(getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE
                    )){
            }else {

                EasyPermissions.requestPermissions(this,"",123,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE
                );
            }
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,mPermissionList,123);

        }

        DataService.getInstance().syncUser(App.getSp().getString("userId"),
                App.getSp().getString("ticket"), new IBaseCallBack<UserMsgEntity>() {
                    @Override
                    public void onSuccess(UserMsgEntity data) {
                        Log.d("splash",data.toString());
                        if (data!=null && data.getProvider()!=null){
                            App.defaultApp().saveUserMsg(data);
                           /* if (data.getProvider().getPassword()!=null && !data.getProvider().getPassword().equals("true")){
                                View inflate = getLayoutInflater().inflate(R.layout.dialog_no_paypwd, null);
                                RelativeLayout close = inflate.findViewById(R.id.ll);

                                MyMediumTextView sure = inflate.findViewById(R.id.sure);
                                MyNormalTextView tv = inflate.findViewById(R.id.tv);

                                tv.setText("您还没有设置登录密码，请先设置");
                                MyMediumTextView cancle = inflate.findViewById(R.id.cancle);
                                close.setOnClickListener(v -> baseDialog.dismiss());
                                cancle.setOnClickListener(v -> baseDialog.dismiss());

                                sure.setOnClickListener(v -> {
                                    Intent intent = new Intent(MainActivity.this, UpdateLoginPwdActivity.class);
                                    startActivity(intent);
                                    baseDialog.dismiss();
                                });
                                baseDialog = new BaseDialog(MainActivity.this, inflate, Gravity.CENTER);
                                baseDialog.show();
                            }else{
                            }*/
                        }

                    }
                    @Override
                    public void onFail(String msg) {
                        App.defaultApp().saveUserMsg(null);
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
      /* App.tts.speak("惊雷，这通天修为天塌地陷紫金锤，紫电，这玄真火焰九天玄剑惊天变",//输入中文，若不支持的设备则不会读出来
                TextToSpeech.QUEUE_FLUSH, null);*/
        /*App.getTTSPlayer().playText("好吃不过饺子");*/
    }



    /**
     * 响应界面的点击事件
     * @param view 点击的视图
     */
    public void onViewItemClicked(View view) {
        try {
            ISupportFragment fragment = getTopFragment();
            if (fragment!=null){
                ((BaseFragment2)fragment).onViewItemClicked(view);
            }
        }catch (Exception e){
            Log.e("edencity",e.getMessage(),e);
        }
    }

    /**
     * 当认证令牌失效时，要求用户重新登录
     */
    public void onAuthTicketExpire(boolean showAlert){

    }

    /**
     * 退出系统登录
     */
    public void logout(){
        App.defaultApp().saveUserMsg(null);
    }

    /**
     * 软键盘管理
     */
    public void showSoftKeyboard(View targetView){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (null != imm) {
            imm.showSoftInput(targetView, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


    /**
     * 从相册/拍照获取图片
     */
    public void getPhoto(int cropRatioX,int cropRatioY, int maxWidth, int maxHeight){
        this.cropRatioX = cropRatioX;
        this.cropRatioY = cropRatioY;
        this.cropMaxWidth=maxWidth;
        this.cropMaxHeight=maxHeight;
        cropAfterCapture = cropRatioX + cropRatioY + maxWidth + maxHeight > 0;
        if (EasyPermissions.hasPermissions(getApplicationContext(),Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            onPermissionsGranted(100,null);
        }else {
            EasyPermissions.requestPermissions(this,"应用需要获取您的身份证照片以完成实名认证",100,Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    /**
     * 扫二维码
     */
    public void doQrcodeScan(){
        if (EasyPermissions.hasPermissions(getApplicationContext(),Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            Intent it=new Intent(MainActivity.this,CaptureActivity.class);
            startActivityForResult(it,1101);

        }else {
            EasyPermissions.requestPermissions(this,"应用需要使用相机扫码",101,
                    Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == 100){
            if (EventBus.getDefault().hasSubscriberForEvent(EventMessage.class)){
                Matisse.from(this)
                        //选择视频和图片
                        .choose(MimeType.ofImage())
                        //自定义选择选择的类型
                        //.choose(MimeType.of(MimeType.JPEG,MimeType.AVI))
                        //是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                        .showSingleMediaType(true)
                        //有序选择图片 123456...
                        /*.countable(true)*/
                        //最大选择数量为9
                        //.maxSelectable(9)
                        //蓝色主题
                        //.theme(R.style.Matisse_Zhihu)
                        //黑色主题
                        .theme(R.style.Matisse_Dracula)
                        //Glide加载方式
                        //.imageEngine(new GlideEngine())
                        //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                        .countable(true)
                        .captureStrategy(new CaptureStrategy(true,"cn.zhongyu.edencity.fileprovider"))
                        //选择方向
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        //界面中缩略图的质量
                        .thumbnailScale(0.85f)
                        //Picasso加载方式
                        .imageEngine(new PicassoEngine())
                        //请求码
                        .forResult(1000);
            }
        }else if (requestCode==101){
            Intent it=new Intent(MainActivity.this,CaptureActivity.class);
            startActivityForResult(it,1101);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            /*new AppSettingsDialog.Builder(this).build().show();*/
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000){
            if (resultCode == Activity.RESULT_OK){
                if (cropAfterCapture){
                    List<Uri> uris=Matisse.obtainResult(data);
                    if (uris!=null && uris.size()>0){
                        cropImage(uris.get(0));
                        return;
                    }
                }else {
                    List<String> uris=Matisse.obtainPathResult(data);
                    if (uris!=null && uris.size()>0){
                        EventBus.getDefault().post(new EventMessage(EventMessage.EVENT_PHOTO,uris.get(0)));
                    }
                }
            }
        }else if (requestCode == UCrop.REQUEST_CROP){
            if (resultCode == Activity.RESULT_OK){
                if (cropedImageFile !=null && cropedImageFile.exists()){
                    EventBus.getDefault().post(new EventMessage(EventMessage.EVENT_PHOTO,cropedImageFile.getPath()));
                }
            }
            cropedImageFile=null;
        }else if (requestCode==1101){
            if (resultCode == Activity.RESULT_OK){
                EventBus.getDefault().post(new EventMessage(EventMessage.EVENT_QRCODE,data.getStringExtra("result")));
            }
        }else if (requestCode== PictureConfig.CHOOSE_REQUEST){
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        if (data!=null){
                            EventBus.getDefault().post(new EventMessage(EventMessage.EVENT_FEEDBACK,data));
                        }
                        break;
                }

        }
    }

    private void cropImage(Uri uri){
        UCrop.Options options = new UCrop.Options();
        //裁剪后图片保存在文件夹中
        cropedImageFile=new File(getCacheDir(), "snap"+System.currentTimeMillis()+".jpg");
        Uri destinationUri = Uri.fromFile(cropedImageFile);
        UCrop uCrop = UCrop.of(uri, destinationUri);//第一个参数是裁剪前的uri,第二个参数是裁剪后的uri
        if (cropRatioX>0 && cropRatioY>0){
            uCrop.withAspectRatio(cropRatioX,cropRatioY);
        }
        if (cropMaxWidth>0 && cropMaxHeight>0){
            uCrop.withMaxResultSize(cropMaxWidth,cropMaxHeight);
        }
        //下面参数分别是缩放,旋转,裁剪框的比例
        options.setAllowedGestures(UCropActivity.ALL,UCropActivity.NONE,UCropActivity.ALL);
        options.setToolbarTitle("图片裁剪");//设置标题栏文字
        options.setMaxScaleMultiplier(3);//设置最大缩放比例
        options.setHideBottomControls(false);//隐藏下边控制栏
        options.setShowCropGrid(false);  //设置是否显示裁剪网格

        options.setToolbarWidgetColor(Color.parseColor("#000000"));//标题字的颜色以及按钮颜色
        options.setDimmedLayerColor(Color.parseColor("#AA000000"));//设置裁剪外颜色
        options.setToolbarColor(Color.parseColor("#ffffff")); // 设置标题栏颜色
        options.setStatusBarColor(Color.parseColor("#ffffff"));//设置状态栏颜色
        options.setCropFrameColor(Color.YELLOW);//设置裁剪框的颜色
        uCrop.withOptions(options);
        uCrop.start(this);
    }
}

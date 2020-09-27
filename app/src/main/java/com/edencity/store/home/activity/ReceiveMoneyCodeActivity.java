package com.edencity.store.home.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.edencity.store.App;
import com.edencity.store.R;
import com.edencity.store.base.BaseActivity;
import com.edencity.store.custum.MyMediumTextView;
import com.edencity.store.custum.MyNormalTextView;
import com.edencity.store.custum.statubar.StatusBarCompat;
import com.edencity.store.util.AdiUtils;
import com.edencity.store.util.DeeSpUtil;
import com.edencity.store.util.DisplayInfoUtils;
import com.edencity.store.util.QRCodeUtil;
import com.edencity.store.util.ResUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiveMoneyCodeActivity extends BaseActivity {

    private ImageView mBack;
    private ImageView mBtnPayCode;
    private MyMediumTextView mShop_name;

    private MyNormalTextView download;
    private ImageView mCode;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#2C7FEB"));
        StatusBarCompat.cancelLightStatusBar(this);
        setContentView(R.layout.activity_receive_money_code);
        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mShop_name =  findViewById(R.id.shop_name);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnPayCode = (ImageView) findViewById(R.id.btn_pay_code);
        mBtnPayCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceiveMoneyCodeActivity.this, PayCodeActivity.class);
                startActivity(intent);
            }
        });
        download = findViewById(R.id.download);
        mCode = findViewById(R.id.code);


        if (App.userMsg()!=null && App.userMsg().getProvider()!=null ){
            if ( App.userMsg().getProvider().getStoreName()!=null){
                mShop_name.setText(App.userMsg().getProvider().getStoreName());
            }
            if (App.userMsg().getProvider().getProviderId()!=null){
                String providerId = App.userMsg().getProvider().getProviderId();
                String img = App.userMsg().getProvider().getStoreFacadeImg();

                if (img==null){
                    Bitmap qrImage = QRCodeUtil.createQRCode("edencity$"+providerId+"$edencity",
                            DisplayInfoUtils.getInstance().dp2pxInt(420),
                            DisplayInfoUtils.getInstance().dp2pxInt(420),
                             BitmapFactory.decodeResource(getResources(),R.mipmap.logoo));
                    Glide.with(this).load(qrImage).into(mCode);
                }else{

                    new Thread() {
                        public void run() {
                            try {
                                RequestOptions requestOptions = new RequestOptions().circleCrop();
                                Bitmap myBitmap = Glide.with(ReceiveMoneyCodeActivity.this)
                                        .asBitmap()
                                        .load(img)
                                        .apply(requestOptions)
                                        .submit(100, 100).get();
                                bitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Bitmap qrImage = QRCodeUtil.createQRCode("edencity$"+providerId+"$edencity",
                                                DisplayInfoUtils.getInstance().dp2pxInt(420),
                                                DisplayInfoUtils.getInstance().dp2pxInt(420),
                                                bitmap);
                                        Glide.with(ReceiveMoneyCodeActivity.this).load(qrImage).into(mCode);
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }.start();
                }
            }
        }

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] PERMISSIONS = {
                        "android.permission.READ_EXTERNAL_STORAGE",
                        "android.permission.WRITE_EXTERNAL_STORAGE" };
                       //检测是否有写的权限
                int permission = ContextCompat.checkSelfPermission(ReceiveMoneyCodeActivity.this,
                        "android.permission.WRITE_EXTERNAL_STORAGE");
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // 没有写的权限，去申请写的权限，会弹出对话框
                    ActivityCompat.requestPermissions(ReceiveMoneyCodeActivity.this, PERMISSIONS,1);
                }else{
                    saveBitmapFromView(mCode);
                }

            }
        });
    }
    public void saveBitmapFromView(View view) {
        int w = view.getWidth();
        int h = view.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        view.layout(20, 20, w+20, h+20);
        view.draw(c);
        // 缩小图片
        Matrix matrix = new Matrix();
        matrix.postScale(1.0f,1.0f); //长和宽放大缩小的比例
        bmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        saveBitmap(bmp,format.format(new Date())+".JPEG");
    }


    public void saveBitmap(Bitmap bitmap, String bitName) {
        String fileName;
        File file;
        if (Build.BRAND.equals("Xiaomi")) { // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
        } else { // Meizu 、Oppo
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + bitName;
        }
        file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
                out.flush();
                out.close();
        // 插入图库
                MediaStore.Images.Media.insertImage(this.getContentResolver(),
                        file.getAbsolutePath(), bitName, null);
                AdiUtils.showToast("保存成功");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 发送广播，通知刷新图库的显示
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));
    }
}

package com.edencity.store.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.edencity.store.R;
import com.edencity.store.custum.statubar.StatusBarCompat;

public class NormalWebActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.web)
    WebView     mWeb;
    @BindView(R.id.close)
    TextView mClose;
    @BindView(R.id.process)
    ProgressBar mProcess;
    @BindView(R.id.title)
    TextView mTitle;
    private RelativeLayout outrl;
    private String url;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this,Color.WHITE);
        StatusBarCompat.changeToLightStatusBar(this);
        setContentView(R.layout.activity_normal_web);
        ButterKnife.bind(this);
        Intent intent = getIntent();
         url = intent.getStringExtra("url");
        initView();
    }

    //让webview可以回退
    @Override
    public void onBackPressed() {
        if (mWeb!=null&&mWeb.canGoBack()) {
            mWeb.goBack();
        }else{
            finish();
        }
    }


    @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
    private void initView() {
        mClose.setVisibility(View.GONE);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //webview设置

        mWeb.loadUrl(url);
        mWeb.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                settings.setBlockNetworkImage(false);
                //如果网页可以回退,则说明现在不处于web页最外层,这时候显示关闭按钮
                if (mWeb.canGoBack()){
                    mClose.setVisibility(View.VISIBLE);
                    mClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }else{
                    mClose.setVisibility(View.GONE);
                }
                super.onPageFinished(view, url);
            }
        });

        mWeb.canGoBack();
         settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings .setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom (false);
        settings.setTextZoom(100);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setBlockNetworkImage(true);
        settings.setLoadWithOverviewMode(true);
        settings .setJavaScriptCanOpenWindowsAutomatically(true);

        mWeb.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTitle.setText(title);
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //加载进度
                mProcess.setProgress(newProgress);
                mProcess.setVisibility(View.VISIBLE);
                if (newProgress==100){
                    mProcess.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeb.clearCache(true);
        mWeb.destroy();
    }
}

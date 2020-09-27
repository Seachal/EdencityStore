package com.edencity.store;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.edencity.store.base.BaseEventMsg;
import com.edencity.store.entity.Meg;
import com.edencity.store.home.activity.MainActivity;
import com.edencity.store.pojo.EventMessage;
import com.edencity.store.util.AdiUtils;
import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.edencity.store.data.AppContant;
import com.edencity.store.entity.UserMsgEntity;
import com.edencity.store.service.WebService;
import com.edencity.store.util.DeeSpUtil;
import com.edencity.store.util.HttpsClient;
import com.edencity.store.util.Logger;
import com.edencity.store.util.PreferencesUtil;
import com.edencity.store.view.LoadingDialog;
import com.umeng.socialize.PlatformConfig;


import org.android.agoo.xiaomi.MiPushRegistar;

import okhttp3.OkHttpClient;

import static anet.channel.bytes.a.TAG;

public class App extends MultiDexApplication {

    private static App defaultApp;
    private WebService mWebService;
    private HttpsClient mHttpsClient;
    private PreferencesUtil preferences;
    private DeeSpUtil spUtil;
    //全局缓存
    private HashMap<String, Object> cache;
    private ExecutorService mExecService;

    private OkHttpClient httpClient = new OkHttpClient();
    private UserMsgEntity userMsg;
    public static Typeface medium;
    public static Typeface normal;
    public static IWXAPI mWxApi;

 /*   public String appDataDirPath;

    private SpeechSynthesizer mTTSPlayer;*/
    private   TextToSpeech tts;
    /*private TextToSpeech tts;*/

    @SuppressLint("NewApi")
    @Override
    public void onCreate() {
        super.onCreate();
        defaultApp = this;
        registerToWx();

        DeeSpUtil.init(this, "edencityStore", Context.MODE_PRIVATE);
        spUtil = DeeSpUtil.getInstance();
        // 自定义字体，在自定义控件里频繁创建typeface会导致加载卡顿所以在application里加载
         medium = Typeface.createFromAsset(getAssets(), "fonts/medium.otf");
         normal = Typeface.createFromAsset(getAssets(), "fonts/normal.otf");
        PlatformConfig.setWeixin(AppContant.WXAPPID, AppContant.APPSERCRET);

        //初始化友盟
        UMConfigure.init(this, AppContant.YOUMENG_APPKEY,//友盟AppKey
                "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                AppContant.YOUMENG_MSG_SECRET);//友盟刚获取的Umeng Message Secret
        UMConfigure.setLogEnabled(true);
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
        // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER); //服务端控制声音
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i(TAG, "注册成功：deviceToken：-------->  " + deviceToken);

            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e(TAG, "注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });

        mPushAgent.addAlias(getSp().getString("userId"), "PERSONID", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String message) {
                Log.i(TAG, isSuccess+"");
            }
        });
        //别名绑定，将某一类型的别名ID绑定至某设备，老的绑定设备信息被覆盖，别名ID和deviceToken是一对一的映射关系
        mPushAgent.setAlias(getSp().getString("userId"), "PERSONID", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String message) {
                Log.i(TAG, isSuccess+"");
            }
        });

        tts = new TextToSpeech(getApplicationContext(), status -> {
            if (status == tts.SUCCESS) {
                // Toast.makeText(MainActivity.this,"成功输出语音",
                // Toast.LENGTH_SHORT).show();
                // Locale loc1=new Locale("us");
                // Locale loc2=new Locale("china");
                int result1 = tts.setLanguage(Locale.US);
                int result2 = tts.setLanguage(Locale.CHINESE);
                tts.setPitch(1.4f);
                // 设置语速
                tts.setSpeechRate(1.4f);

                if (result1 == TextToSpeech.LANG_MISSING_DATA
                        || result1 == TextToSpeech.LANG_NOT_SUPPORTED
                        || result2 == TextToSpeech.LANG_MISSING_DATA
                        || result2 == TextToSpeech.LANG_NOT_SUPPORTED)

                {
                    Toast.makeText(getApplicationContext(),"数据丢失或不支持",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //bugly
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(context, AppContant.BUGLY_APPID, false);//正式环境设置成false

        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                for (Map.Entry entry : msg.extra.entrySet()) {
                    String key = (String)entry.getKey();
                    String value = (String)entry.getValue();
                    Meg meg = new Gson().fromJson(value, Meg.class);
                    Log.e("kkk",key+"--0---"+meg.getAmount());
                    String s = "伊甸果到账"+meg.getAmount();
                    /*getTTSPlayer().playText(s);*/
                    tts.speak(s,//输入中文，若不支持的设备则不会读出来
                            TextToSpeech.QUEUE_FLUSH, null);

                }

                return super.getNotification(context, msg);
            }
        };

        mPushAgent.setMessageHandler(messageHandler);
        //移除别名ID
                /*mPushAgent.deleteAlias(getSp().getString("userId"), "PERSONID", new UTrack.ICallBack() {
                    @Override
                    public void onMessage(boolean isSuccess, String message) {
                    }
                });
                */

        //小米推送
        MiPushRegistar.register(getApplicationContext(), AppContant.XIAOMI_ID,AppContant.XIAOMI_KEY);
        mHttpsClient = new HttpsClient();
        mHttpsClient.initHttpsClient();
        mWebService = new WebService();
        mWebService.setHttpsClient(mHttpsClient);
        cache = new HashMap<>();
        Logger.setDebug(true);

    }

    private String getProcessName(int myPid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + myPid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


    private void registerToWx() {
        mWxApi = WXAPIFactory.createWXAPI(this, AppContant.WXAPPID,false);
        mWxApi . registerApp ( AppContant.WXAPPID );
    }

    public static App defaultApp() {
        return defaultApp;
    }

    public static WebService webService() {
        if (defaultApp.mWebService == null)
            defaultApp.mWebService = new WebService();
        return defaultApp.mWebService;
    }

    public static DeeSpUtil getSp() {
        return defaultApp.spUtil;
    }


    public static UserMsgEntity userMsg() {

        return defaultApp.userMsg;
    }


    public void saveUserMsg(UserMsgEntity user) {

        if (user == null) {
            spUtil.remove("userId");
            spUtil.remove("ticket");
        } else {
            if (user.getProvider()==null){
            }else{
                this.userMsg= new UserMsgEntity(user.getProvider());
            }
        }
    }


    public static void setCache(String key, Object value) {
        defaultApp.cache.put(key, value);
    }

    public static void removeCache(String key) {
        defaultApp.cache.remove(key);
    }

    public static Object getCache(String key) {
        return defaultApp.cache.get(key);
    }

    /**
     * 执行任务
     *
     * @param task
     * @return
     */
    public static boolean execute(Runnable task) {
        try {
            if (defaultApp.mExecService == null) {
                defaultApp.mExecService = Executors.newCachedThreadPool();
            }
            defaultApp.mExecService.execute(task);
            return true;
        } catch (Exception e) {
            LoadingDialog.hideLoading();
            Log.e("edencity", e.getMessage(), e);
            return false;
        }

    }

    private void copyAssetsFile(String fileName,File desDir){
        try {
            InputStream inputStream=getAssets().open(fileName);
            File file=new File(desDir,fileName);
            if(!file.exists() || file.length()==0) {
                FileOutputStream fos=new FileOutputStream(file);
                int len;
                byte[] buffer=new byte[10240];
                while ((len=inputStream.read(buffer))!=-1){
                    fos.write(buffer,0,len);
                }
                fos.flush();
                inputStream.close();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

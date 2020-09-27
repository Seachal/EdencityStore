package com.edencity.store.data;

import rxhttp.wrapper.annotation.DefaultDomain;

public class AppContant {


    //线上线下开关
    //0 线下  1 线上
    public static int TYPE = 0;
    public static final String DEBUG= "http://pay.edencity.cn";
   public static final String RELEASE= "http://pay.edencitybrand.com";

   public static final String WebUrl= "http://h5.edencitybrand.com/";

    public static  String BASE_URL = TYPE== 0 ? DEBUG:RELEASE;

    public static final String WXAPPID = "wx60aca7f20d0cc05d";
    public static final String APPSERCRET = "b2bd3e9e7cc46b6af2c98dfecc178df8";

    public static final String YOUMENG_APPKEY = "5e7ac7e10cafb2cb76000027";
    public static final String YOUMENG_MSG_SECRET = "b36a4a43cf4e6a1988d37278ea249031";
    public static final String YOUMENG_MASTER_SECRET = "spligorlrx44ebjuoapd2pkbpuvljagy";

    public static final String APPID = "edencity_1";
    public static final String APPKEY = "edencity_2";

    public static final String XIAOMI_ID = "2882303761518357554";
    public static final String XIAOMI_KEY = "5621835730554";

    /*@DefaultDomain
    public static final String BASE_RELEASE= "http://121.42.184.173:8090/";*/
    //腾讯bugly
    public static final String BUGLY_APPID = "108b8c33f9";
    public static final String BUGLY_APPKEY = "50321ddb-81f7-4cab-a95f-ba617b9e95df";
}





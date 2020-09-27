package com.edencity.store.data;


import java.util.Map;


import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.UserLoginEntity;
import com.edencity.store.entity.UserMsgEntity;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    /*
    *
    * 登录注册相关
    *
    */


    //获取验证码 phone
    // type (login,regist,forget,modify)
    @POST("api/provider/verificationCode/get")
    @FormUrlEncoded
    Observable<BaseResult> get(@FieldMap Map<String,Object>map);

    //注册
    // phone
    // verificationCode
    // password
    @POST("api/provider/regist")
    @FormUrlEncoded
    Observable<BaseResult<UserLoginEntity>>register(@FieldMap Map<String,Object>map);

    //验证码登录
    // phone
    // verificationCode
    @POST("api/provider/login/verificationCode")
    @FormUrlEncoded
    Observable<BaseResult<UserLoginEntity>>verifyLogin(@FieldMap Map<String,Object>map);

    //手机号密码登录
    // phone
    // password
    @POST("api/provider/login/password")
    @FormUrlEncoded
    Observable<BaseResult<UserLoginEntity>>normalLogin(@FieldMap Map<String,Object>map);

    //忘记、修改密码
    // phone
    // verificationCode
    // password
    @POST("api/provider/password/reset")
    @FormUrlEncoded
    Observable<BaseResult>resetLoginPwd(@FieldMap Map<String,Object>map);

    //忘记、修改支付密码
    // phone
    // verificationCode
    // payPassword
    // userId
    // ticket
    @POST("api/provider/payPassword/reset")
    @FormUrlEncoded
    Observable<BaseResult>resetPayPwd(@FieldMap Map<String,Object>map);

    //获取充值规则
    // ticket
    // userId
    @POST("api/customer/charge/getCurrentRule")
    @FormUrlEncoded
    Observable<BaseResult>getRechargeRule(@FieldMap Map<String,Object>map);

}

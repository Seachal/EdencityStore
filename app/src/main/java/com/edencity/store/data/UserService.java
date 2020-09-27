package com.edencity.store.data;

// Created by Ardy on 2020/1/7.

import java.util.Map;

import com.edencity.store.entity.AliPayEntity;
import com.edencity.store.entity.AllMemberEntity;
import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.BillDetailEntity;
import com.edencity.store.entity.BillEntity;
import com.edencity.store.entity.CountBillEntity;
import com.edencity.store.entity.CountHistoryEntity;
import com.edencity.store.entity.FeedBackListEntity;
import com.edencity.store.entity.MemberEntity;
import com.edencity.store.entity.MsgEntity;
import com.edencity.store.entity.UserMsgEntity;
import com.edencity.store.entity.WeChatPayEntity;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface UserService {

    /*
     *
     * 用户相关
     *
     */

    //获取用户账单列表
    // userId
    // ticket
    // pageNum
    // pageSize
    @POST("api/customer/accountChangeRecordList")
    @FormUrlEncoded
    Observable<BaseResult>getUserOrder(@FieldMap Map<String,Object>map);

    //同步用户信息
    // userId
    // ticket
    @POST("api/provider/sync")
    @FormUrlEncoded
    Observable<BaseResult<UserMsgEntity>>sysnUserDesc(@FieldMap Map<String,Object>map);

    //同步用户信息
    // userId
    // ticket
    @POST("api/provider/sync")
    @FormUrlEncoded
    Observable<BaseResult>sysnUserDesc2(@FieldMap Map<String,Object>map);

    //修改用户信息
    // nickName
    // gender
    // birthday
    // userId
    // ticket
    @POST("api/provider/edit")
    @FormUrlEncoded
    Observable<BaseResult>editUserDesc(@FieldMap Map<String,Object>map);

    //实名认证
    // userId
    // ticket
    // userName
    // idCard
    // frontImage
    // backImage
    @POST("api/customer/certificate")
    @Multipart
    Observable<BaseResult>userCertificate(@PartMap Map<String,Object>map);

    //充值 微信开通
    // userId
    // ticket
    // chargeAmount
    @POST("api/provider/member/establish/weichatpay")
    @FormUrlEncoded
    Observable<BaseResult<WeChatPayEntity>>wechatPay(@FieldMap Map<String,Object>map);

    //充值 阿里支付开通
    // userId
    // ticket
    // chargeAmount
    @POST("api/provider/member/establish/alipay")
    @FormUrlEncoded
    Observable<BaseResult<AliPayEntity>>aliPay(@FieldMap Map<String,Object>map);

    //充值 微信续费会员
    // userId
    // ticket
    // chargeAmount
    @POST("api/provider/member/renew/weichatpay")
    @FormUrlEncoded
    Observable<BaseResult<WeChatPayEntity>>wechatRePay(@FieldMap Map<String,Object>map);

    //充值 阿里支付续费会员
    // userId
    // ticket
    // chargeAmount
    @POST("api/provider/member/renew/alipay")
    @FormUrlEncoded
    Observable<BaseResult<AliPayEntity>>aliRePay(@FieldMap Map<String,Object>map);




    //获取所有会员权益信息
    @POST("api/provider/allMembershipRights")
    @FormUrlEncoded
    Observable<BaseResult<AllMemberEntity>>getAllMemberShip(@FieldMap Map<String,Object>map);

    //获取指定成员等级 门槛及 享受权益信息
    @POST("api/provider/membershipDetail")
    @FormUrlEncoded
    Observable<BaseResult<MemberEntity>>getMemberShipDetail(@FieldMap Map<String,Object>map);


    /*用户账单
     userId  ticket  billType 0 收入  1 支出  2 全部
     pageNum  pageSize
    返回 sourceType 1订单2结算3广告4房租费用支出5.退款
    changeType  0增加1减少
    */
    @POST("api/provider/accountBill")
    @FormUrlEncoded
    Observable<BaseResult<BillEntity>>userAccountList(@FieldMap Map<String,Object>map);


    /*获取账单详情
    sourceRecordId sourceType userId  ticket
    支出详情  EXPENSES_TYPE 支出类型（1.房租,2.广告）*/

    @POST("api/provider/accountBill/detail")
    @FormUrlEncoded
    Observable<BaseResult<BillDetailEntity>>getBillDetail(@FieldMap Map<String,Object>map);

    //发起结算
    @POST("api/provider/balance")
    @FormUrlEncoded
    Observable<BaseResult<CountBillEntity>>goToCountBill(@FieldMap Map<String,Object>map);

    //获取结算账单
    @POST("api/provider/balanceBill")
    @FormUrlEncoded
    Observable<BaseResult<CountHistoryEntity>>getBalanceBill(@FieldMap Map<String,Object>map);


    //获取用户反馈记录
    @POST("api/customer/feedback/list")
    @FormUrlEncoded
    Observable<BaseResult<FeedBackListEntity>>getUserFeedBackHistory(@FieldMap Map<String,Object>map);

    //消息
    @POST("api/provider/message/list")
    @FormUrlEncoded
    Observable<BaseResult<MsgEntity>>getMsg(@FieldMap Map<String,Object>map);


}

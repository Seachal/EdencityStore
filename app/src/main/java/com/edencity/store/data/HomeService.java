package com.edencity.store.data;

// Created by Ardy on 2020/1/7.

import java.util.Map;

import com.edencity.store.entity.BaseResult;
import com.edencity.store.entity.HomeActiveListEntity;
import com.edencity.store.entity.HomeBannerEntity;
import com.edencity.store.entity.HomeShopListEntity;
import com.edencity.store.entity.TodayCountEntity;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HomeService {


    String BASE = "api/provider/";
    /*
     *
     * 首页相关
     *
     */



    //获取首页活动列表
    @POST(BASE + "activities/list")
    @FormUrlEncoded
    Observable<BaseResult<HomeActiveListEntity>>getHomeActiveList(@FieldMap Map<String,Object>map);

    //报名活动 activitiesId   storeName contact contactPhone participantNumber userId  ticket

    @POST(BASE + "activities/enroll")
    @FormUrlEncoded
    Observable<BaseResult>EnrollActive(@FieldMap Map<String,Object>map);

    //banner
    @POST(BASE + "banner/list")
    @FormUrlEncoded
    Observable<BaseResult<HomeBannerEntity>>getBannerList(@FieldMap Map<String,Object>map);

    /*
    商户端获取订单列表
     userId  ticket pageNum  pageSize
     */
    @POST(BASE + "myDeal")
    @FormUrlEncoded
    Observable<BaseResult>getMyOrderList(@FieldMap Map<String,Object>map);

    /*报名活动
    activitiesId   storeName contact contactPhone participantNumber userId  ticket
    */
    @POST(BASE + "activities/enroll")
    @FormUrlEncoded
    Observable<BaseResult>joinActivities(@FieldMap Map<String,Object>map);

    /*商家收款
    payCode stub dealAmount userId  ticket
    */
    @POST(BASE + "gather")
    @FormUrlEncoded
    Observable<BaseResult>storeReceive(@FieldMap Map<String,Object>map);

    /*获取今日实时统计数据
    userId  ticket
   */
    @POST(BASE + "todayIncomeCensus")
    @FormUrlEncoded
    Observable<BaseResult<TodayCountEntity>>getTotalCensus(@FieldMap Map<String,Object>map);



}

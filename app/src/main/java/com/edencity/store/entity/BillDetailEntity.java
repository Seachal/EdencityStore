package com.edencity.store.entity;

// Created by Ardy on 2020/4/13.

public class BillDetailEntity {

    /**
     * accountBillDetail : {"mydealId":"c26e4520039041519bc356b5fa3c1759","dealtime":"2020-04-11 10:50:37","payType":"预付卡","userPortrait":"http://edencity-cdn.oss-cn-qingdao.aliyuncs.com/userportrait1585202231926_snap1585202201075.jpg","orderId":"01202004111050373256","dealAmount":888,"payMethod":"二维码收款（用户被扫）","nickName":"张迪","billType":"收入","userName":"张迪","userId":"1e06e976dfcf484792c119a1fe411eae","dealInfo":"e9486bdc9d10491f9c9896e7b4838cf9"}
     */

    private AccountBillDetailBean accountBillDetail;

    public AccountBillDetailBean getAccountBillDetail() {
        return accountBillDetail;
    }

    public void setAccountBillDetail(AccountBillDetailBean accountBillDetail) {
        this.accountBillDetail = accountBillDetail;
    }

    public static class AccountBillDetailBean {
        /**
         * mydealId : c26e4520039041519bc356b5fa3c1759
         * dealtime : 2020-04-11 10:50:37
         * payType : 预付卡
         * userPortrait : http://edencity-cdn.oss-cn-qingdao.aliyuncs.com/userportrait1585202231926_snap1585202201075.jpg
         * orderId : 01202004111050373256
         * dealAmount : 888
         * payMethod : 二维码收款（用户被扫）
         * nickName : 张迪
         * billType : 收入
         * userName : 张迪
         * userId : 1e06e976dfcf484792c119a1fe411eae
         * dealInfo : e9486bdc9d10491f9c9896e7b4838cf9
         */

        private String mydealId;
        private String dealtime;
        private String payType;
        private String userPortrait;
        private String orderId;
        private float dealAmount;
        private String payMethod;
        private String nickName;
        private String billType;
        private String userName;
        private String userId;
        private String dealInfo;

        public String getMydealId() {
            return mydealId;
        }

        public void setMydealId(String mydealId) {
            this.mydealId = mydealId;
        }

        public String getDealtime() {
            return dealtime;
        }

        public void setDealtime(String dealtime) {
            this.dealtime = dealtime;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getUserPortrait() {
            return userPortrait;
        }

        public void setUserPortrait(String userPortrait) {
            this.userPortrait = userPortrait;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public float getDealAmount() {
            return dealAmount;
        }

        public void setDealAmount(float dealAmount) {
            this.dealAmount = dealAmount;
        }

        public String getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(String payMethod) {
            this.payMethod = payMethod;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getBillType() {
            return billType;
        }

        public void setBillType(String billType) {
            this.billType = billType;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDealInfo() {
            return dealInfo;
        }

        public void setDealInfo(String dealInfo) {
            this.dealInfo = dealInfo;
        }
    }
}

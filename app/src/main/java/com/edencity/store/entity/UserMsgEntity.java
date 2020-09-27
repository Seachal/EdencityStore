package com.edencity.store.entity;

// Created by Ardy on 2020/1/13.

public class UserMsgEntity {
    /**
     * provider : {"memberEndTime":null,"providerCode":"01202003141547535887","totalExpenses":0,"storeFacadeImg":null,"businessTime":null,"accountStatus":"1002010701","frozenProvision":0,"password":"true","bank":null,"storeLatitude":null,"providerId":"db09c94fc8d44e9c97664494c9ac7970","storeName":null,"withdrawTime":null,"store_category":null,"bankSubbranch":null,"email":null,"bankNumber":null,"brandName":null,"gross":0,"possess":0,"businessStatus":"1002010751","storeCategory":null,"portrait":null,"storeDetailAddress":null,"brandVendorId":null,"storeLongitude":null,"phone":"17630360083","installationService":null,"location":null,"memberType":"1002010730","memberStartTime":null,"bankHolder":null,"payPassword":"false","providerStatus":"1002010714"}
     */

    private ProviderBean provider;

    public UserMsgEntity(ProviderBean provider) {

        this.provider = provider;
    }

    public ProviderBean getProvider() {
        return provider;
    }

    public void setProvider(ProviderBean provider) {
        this.provider = provider;
    }

    public static class ProviderBean {
        /**
         * memberEndTime : null
         * providerCode : 01202003141547535887
         * totalExpenses : 0
         * storeFacadeImg : "http://edencity-cdn.oss-cn-qingdao.aliyuncs.com/brand/bbc57ae84abf4234a60a03b314e2797c/logo/1561425868412_画板 copy 6@3x.JPG"
         * businessTime : "周一至周五：8时至24时
         * accountStatus : 1002010701
         * frozenProvision : 0
         * password : true
         * bank : null
         * storeLatitude : ":32.000004
         * providerId : db09c94fc8d44e9c97664494c9ac7970
         * storeName : "必胜客伊甸城店"
         * withdrawTime : null
         * store_category : null
         * bankSubbranch : null
         * email : null
         * bankNumber : null
         * brandName : "必胜客"
         * gross : 0.69
         * possess : 0
         * businessStatus : 1002010751
         * storeCategory : null
         * portrait : null
         * storeDetailAddress : ""
         * brandVendorId : "e8657d077bdd4ac581aa309474651260"
         * storeLongitude : 47.2
         * phone : 17630360083
         * installationService : "可停车,有雅座"
         * location : "山西省晋中市"
         * memberType : 1002010730
         * memberStartTime : null
         * bankHolder : null
         * payPassword : false
         * providerStatus : 1002010714
         */

        private String memberEndTime;
        private String providerCode;
        private float totalExpenses; //总支出
        private String storeFacadeImg;
        private String businessTime;
        private float settlementRate;//结算费率  结算金额 * （1-费率 ）= 实际到账
        private String accountStatus;
        private float frozenProvision; //冻结金额
        private String password;
        private String bank;
        private String storeLatitude;
        private String providerId;
        private String storeName;
        private String withdrawTime;
        private String store_category;
        private String bankSubbranch;
        private String email;
        private String bankNumber;
        private String brandName;
        private float gross;
        private float possess;
        private String businessStatus;
        private String storeCategory;
        private String portrait;
        private String storeDetailAddress;
        private String brandVendorId;
        private String storeLongitude;
        private String phone;
        private String installationService;
        private String location;
        private String memberType;
        private String memberStartTime;
        private String bankHolder;
        private String payPassword;
        private String providerStatus;




        public String getUserVipLevel(){

            switch (memberType){
                case "1002010730":
                    return "一级成员";
                case "1002010731":
                    return "二级成员";
                case "1002010732":
                    return "三级成员";
            }
            return "";
        }

        public String getMemberEndTime() {
            return memberEndTime;
        }

        public void setMemberEndTime(String memberEndTime) {
            this.memberEndTime = memberEndTime;
        }

        public String getProviderCode() {
            return providerCode;
        }

        public void setProviderCode(String providerCode) {
            this.providerCode = providerCode;
        }

        public float getTotalExpenses() {
            return totalExpenses;
        }

        public void setTotalExpenses(float totalExpenses) {
            this.totalExpenses = totalExpenses;
        }

        public String getStoreFacadeImg() {
            return storeFacadeImg;
        }

        public void setStoreFacadeImg(String storeFacadeImg) {
            this.storeFacadeImg = storeFacadeImg;
        }

        public String getBusinessTime() {
            return businessTime;
        }

        public void setBusinessTime(String businessTime) {
            this.businessTime = businessTime;
        }

        public float getSettlementRate() {
            return settlementRate;
        }

        public void setSettlementRate(float settlementRate) {
            this.settlementRate = settlementRate;
        }

        public String getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
        }

        public float getFrozenProvision() {
            return frozenProvision;
        }

        public void setFrozenProvision(float frozenProvision) {
            this.frozenProvision = frozenProvision;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getStoreLatitude() {
            return storeLatitude;
        }

        public void setStoreLatitude(String storeLatitude) {
            this.storeLatitude = storeLatitude;
        }

        public String getProviderId() {
            return providerId;
        }

        public void setProviderId(String providerId) {
            this.providerId = providerId;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getWithdrawTime() {
            return withdrawTime;
        }

        public void setWithdrawTime(String withdrawTime) {
            this.withdrawTime = withdrawTime;
        }

        public String getStore_category() {
            return store_category;
        }

        public void setStore_category(String store_category) {
            this.store_category = store_category;
        }

        public String getBankSubbranch() {
            return bankSubbranch;
        }

        public void setBankSubbranch(String bankSubbranch) {
            this.bankSubbranch = bankSubbranch;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBankNumber() {
            return bankNumber;
        }

        public void setBankNumber(String bankNumber) {
            this.bankNumber = bankNumber;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public float getGross() {
            return gross;
        }

        public void setGross(float gross) {
            this.gross = gross;
        }

        public float getPossess() {
            return possess;
        }

        public void setPossess(float possess) {
            this.possess = possess;
        }

        public String getBusinessStatus() {
            return businessStatus;
        }

        public void setBusinessStatus(String businessStatus) {
            this.businessStatus = businessStatus;
        }

        public String getStoreCategory() {
            return storeCategory;
        }

        public void setStoreCategory(String storeCategory) {
            this.storeCategory = storeCategory;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getStoreDetailAddress() {
            return storeDetailAddress;
        }

        public void setStoreDetailAddress(String storeDetailAddress) {
            this.storeDetailAddress = storeDetailAddress;
        }

        public String getBrandVendorId() {
            return brandVendorId;
        }

        public void setBrandVendorId(String brandVendorId) {
            this.brandVendorId = brandVendorId;
        }

        public String getStoreLongitude() {
            return storeLongitude;
        }

        public void setStoreLongitude(String storeLongitude) {
            this.storeLongitude = storeLongitude;
        }

        public String getPhone() {
            if (phone.equals("")){
                return "";
            }
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getInstallationService() {
            return installationService;
        }

        public void setInstallationService(String installationService) {
            this.installationService = installationService;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getMemberType() {
            return memberType;
        }

        public void setMemberType(String memberType) {
            this.memberType = memberType;
        }

        public String getMemberStartTime() {
            return memberStartTime;
        }

        public void setMemberStartTime(String memberStartTime) {
            this.memberStartTime = memberStartTime;
        }

        public String getBankHolder() {
            return bankHolder;
        }

        public void setBankHolder(String bankHolder) {
            this.bankHolder = bankHolder;
        }

        public String getPayPassword() {
            return payPassword;
        }

        public void setPayPassword(String payPassword) {
            this.payPassword = payPassword;
        }

        public String getProviderStatus() {
            return providerStatus;
        }

        public void setProviderStatus(String providerStatus) {
            this.providerStatus = providerStatus;
        }
    }

}



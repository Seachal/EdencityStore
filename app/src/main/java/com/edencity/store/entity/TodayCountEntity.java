package com.edencity.store.entity;

// Created by Ardy on 2020/3/28.
public class TodayCountEntity {


    /**
     * todayIncomeCensusData : {"saleAmount":"0.03","saleCount":3,"customerUnitPrice":"0.01","totalOrderCount":0,"totalOrderAmount":2546.14,"totalCustomerUnitPrice":0}
     */

    private TodayIncomeCensusDataBean todayIncomeCensusData;

    public TodayIncomeCensusDataBean getTodayIncomeCensusData() {
        return todayIncomeCensusData;
    }

    public void setTodayIncomeCensusData(TodayIncomeCensusDataBean todayIncomeCensusData) {
        this.todayIncomeCensusData = todayIncomeCensusData;
    }

    public static class TodayIncomeCensusDataBean {
        /**
         * saleAmount : 0.03
         * saleCount : 3
         * customerUnitPrice : 0.01
         * totalOrderCount : 0
         * totalOrderAmount : 2546.14
         * totalCustomerUnitPrice : 0
         */

        private String saleAmount;
        private int saleCount;
        private String customerUnitPrice;
        private int totalOrderCount;
        private float totalOrderAmount;
        private float totalCustomerUnitPrice;

        public String getSaleAmount() {
            return saleAmount;
        }

        public void setSaleAmount(String saleAmount) {
            this.saleAmount = saleAmount;
        }

        public int getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(int saleCount) {
            this.saleCount = saleCount;
        }

        public String getCustomerUnitPrice() {
            return customerUnitPrice;
        }

        public void setCustomerUnitPrice(String customerUnitPrice) {
            this.customerUnitPrice = customerUnitPrice;
        }

        public int getTotalOrderCount() {
            return totalOrderCount;
        }

        public void setTotalOrderCount(int totalOrderCount) {
            this.totalOrderCount = totalOrderCount;
        }

        public float getTotalOrderAmount() {
            return totalOrderAmount;
        }

        public void setTotalOrderAmount(float totalOrderAmount) {
            this.totalOrderAmount = totalOrderAmount;
        }

        public float getTotalCustomerUnitPrice() {
            return totalCustomerUnitPrice;
        }

        public void setTotalCustomerUnitPrice(float totalCustomerUnitPrice) {
            this.totalCustomerUnitPrice = totalCustomerUnitPrice;
        }
    }
}

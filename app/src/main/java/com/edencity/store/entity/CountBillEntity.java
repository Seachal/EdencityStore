package com.edencity.store.entity;

import java.io.Serializable;

// Created by Ardy on 2020/3/31.
public class CountBillEntity implements Serializable {

    private static final long serialVersionUID = 3829635962034178762L;
    /**
     * balanceDetail : {"actualAmount":0.01,"approvalStatus":"","approvalUser":"","balanceAmount":0.01,"balanceBank":"","balanceId":"98fa66d847e0441c98aacf2edf68a7df","balanceNo":"202003311605340679","balanceState":"1010011501","bankCardHolder":"","bankCardNumber":"","createby":"","createtime":{"date":31,"day":2,"hours":16,"minutes":5,"month":2,"seconds":34,"time":1585641934909,"timezoneOffset":-480,"year":120},"evidence":"","marketingCharge":0,"note":"","operateby":"","operatetime":null,"rejectReason":"","serviceCharge":0,"userId":"f1eb1d7de77747d084c15bb30bba5027"}
     */

    private BalanceDetailBean balanceDetail;

    public BalanceDetailBean getBalanceDetail() {
        return balanceDetail;
    }

    public void setBalanceDetail(BalanceDetailBean balanceDetail) {
        this.balanceDetail = balanceDetail;
    }

    public static class BalanceDetailBean implements Serializable  {
        private static final long serialVersionUID = -1157676717199915434L;
        /**
         * actualAmount : 0.01
         * approvalStatus :
         * approvalUser :
         * balanceAmount : 0.01
         * balanceBank :
         * balanceId : 98fa66d847e0441c98aacf2edf68a7df
         * balanceNo : 202003311605340679
         * balanceState : 1010011501
         * bankCardHolder :
         * bankCardNumber :
         * createby :
         * createtime : {"date":31,"day":2,"hours":16,"minutes":5,"month":2,"seconds":34,"time":1585641934909,"timezoneOffset":-480,"year":120}
         * evidence :
         * marketingCharge : 0
         * note :
         * operateby :
         * operatetime : null
         * rejectReason :
         * serviceCharge : 0
         * userId : f1eb1d7de77747d084c15bb30bba5027
         */

        private float actualAmount;
        private String approvalStatus;
        private String approvalUser;
        private float balanceAmount;
        private String balanceBank;
        private String balanceId;
        private String balanceNo;
        private String balanceState;
        private String bankCardHolder;
        private String bankCardNumber;
        private String createby;
        private CreatetimeBean createtime;
        private String evidence;
        private float marketingCharge;
        private String note;
        private String operateby;
        private String operatetime;
        private String rejectReason;
        private float serviceCharge;
        private String userId;

        public float getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(float actualAmount) {
            this.actualAmount = actualAmount;
        }

        public String getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(String approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public String getApprovalUser() {
            return approvalUser;
        }

        public void setApprovalUser(String approvalUser) {
            this.approvalUser = approvalUser;
        }

        public float getBalanceAmount() {
            return balanceAmount;
        }

        public void setBalanceAmount(float balanceAmount) {
            this.balanceAmount = balanceAmount;
        }

        public String getBalanceBank() {
            return balanceBank;
        }

        public void setBalanceBank(String balanceBank) {
            this.balanceBank = balanceBank;
        }

        public String getBalanceId() {
            return balanceId;
        }

        public void setBalanceId(String balanceId) {
            this.balanceId = balanceId;
        }

        public String getBalanceNo() {
            return balanceNo;
        }

        public void setBalanceNo(String balanceNo) {
            this.balanceNo = balanceNo;
        }

        public String getBalanceState() {
            return balanceState;
        }

        public void setBalanceState(String balanceState) {
            this.balanceState = balanceState;
        }

        public String getBankCardHolder() {
            return bankCardHolder;
        }

        public void setBankCardHolder(String bankCardHolder) {
            this.bankCardHolder = bankCardHolder;
        }

        public String getBankCardNumber() {
            return bankCardNumber;
        }

        public void setBankCardNumber(String bankCardNumber) {
            this.bankCardNumber = bankCardNumber;
        }

        public String getCreateby() {
            return createby;
        }

        public void setCreateby(String createby) {
            this.createby = createby;
        }

        public CreatetimeBean getCreatetime() {
            return createtime;
        }

        public void setCreatetime(CreatetimeBean createtime) {
            this.createtime = createtime;
        }

        public String getEvidence() {
            return evidence;
        }

        public void setEvidence(String evidence) {
            this.evidence = evidence;
        }

        public float getMarketingCharge() {
            return marketingCharge;
        }

        public void setMarketingCharge(float marketingCharge) {
            this.marketingCharge = marketingCharge;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getOperateby() {
            return operateby;
        }

        public void setOperateby(String operateby) {
            this.operateby = operateby;
        }

        public String getOperatetime() {
            return operatetime;
        }

        public void setOperatetime(String operatetime) {
            this.operatetime = operatetime;
        }

        public String getRejectReason() {
            return rejectReason;
        }

        public void setRejectReason(String rejectReason) {
            this.rejectReason = rejectReason;
        }

        public float getServiceCharge() {
            return serviceCharge;
        }

        public void setServiceCharge(float serviceCharge) {
            this.serviceCharge = serviceCharge;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public static class CreatetimeBean implements Serializable{
            private static final long serialVersionUID = 1314651064249575467L;
            /**
             * date : 31
             * day : 2
             * hours : 16
             * minutes : 5
             * month : 2
             * seconds : 34
             * time : 1585641934909
             * timezoneOffset : -480
             * year : 120
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}

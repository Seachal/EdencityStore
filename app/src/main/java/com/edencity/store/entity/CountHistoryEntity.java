package com.edencity.store.entity;

import java.io.Serializable;
import java.util.List;

// Created by Ardy on 2020/3/31.
public class CountHistoryEntity implements Serializable {

    private static final long serialVersionUID = -5163758164870380015L;
    /**
     * accountBills : {"endRow":1,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"operateby":"dc23cd6142794f63b0b0f0deded9eb3d","note":null,"createtime":"2019-10-29 10:01:28","bankCardNumber":null,"evidence":null,"balanceBank":null,"bankCardHolder":null,"actualAmount":54450,"balanceState":"1010011502","balanceAmount":55000,"userId":"cf3da73eb0b04b93bb659bd767ef8e95","createby":null,"serviceCharge":550,"rejectReason":"44444","balanceNo":"201910291001283431","operatetime":"2020-03-26 14:08:51","marketingCharge":0,"balanceId":"09f4f86872474e7990de0713769e6926"}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"orderBy":"","pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":1,"startRow":1,"total":1}
     */

    private AccountBillsBean accountBills;

    public AccountBillsBean getAccountBills() {
        return accountBills;
    }

    public void setAccountBills(AccountBillsBean accountBills) {
        this.accountBills = accountBills;
    }

    public static class AccountBillsBean implements Serializable{
        private static final long serialVersionUID = 5234291117890061641L;
        /**
         * endRow : 1
         * firstPage : 1
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * lastPage : 1
         * list : [{"operateby":"dc23cd6142794f63b0b0f0deded9eb3d","note":null,"createtime":"2019-10-29 10:01:28","bankCardNumber":null,"evidence":null,"balanceBank":null,"bankCardHolder":null,"actualAmount":54450,"balanceState":"1010011502","balanceAmount":55000,"userId":"cf3da73eb0b04b93bb659bd767ef8e95","createby":null,"serviceCharge":550,"rejectReason":"44444","balanceNo":"201910291001283431","operatetime":"2020-03-26 14:08:51","marketingCharge":0,"balanceId":"09f4f86872474e7990de0713769e6926"}]
         * navigatePages : 8
         * navigatepageNums : [1]
         * nextPage : 0
         * orderBy :
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * prePage : 0
         * size : 1
         * startRow : 1
         * total : 1
         */

        private int endRow;
        private int firstPage;
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private int lastPage;
        private int navigatePages;
        private int nextPage;
        private String orderBy;
        private int pageNum;
        private int pageSize;
        private int pages;
        private int prePage;
        private int size;
        private int startRow;
        private int total;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean implements Serializable{

            private static final long serialVersionUID = -3590275453531812960L;
            /**
             * operateby : dc23cd6142794f63b0b0f0deded9eb3d
             * note : null
             * createtime : 2019-10-29 10:01:28
             * bankCardNumber : null
             * evidence : null
             * balanceBank : null
             * bankCardHolder : null
             * actualAmount : 54450
             * balanceState : 1010011502
             * balanceAmount : 55000
             * userId : cf3da73eb0b04b93bb659bd767ef8e95
             * createby : null
             * serviceCharge : 550
             * rejectReason : 44444
             * balanceNo : 201910291001283431
             * operatetime : 2020-03-26 14:08:51
             * marketingCharge : 0
             * balanceId : 09f4f86872474e7990de0713769e6926
             */

            private String operateby;
            private String note;
            private String createtime;
            private String bankCardNumber;
            private String evidence;
            private String balanceBank;
            private String bankCardHolder;
            private float actualAmount;
            private String balanceState;
            private float balanceAmount;
            private String userId;
            private String createby;
            private float serviceCharge;
            private String rejectReason;
            private String balanceNo;
            private String operatetime;
            private float marketingCharge;
            private String balanceId;

            public String getOperateby() {
                return operateby;
            }

            public void setOperateby(String operateby) {
                this.operateby = operateby;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getBankCardNumber() {
                return bankCardNumber;
            }

            public void setBankCardNumber(String bankCardNumber) {
                this.bankCardNumber = bankCardNumber;
            }

            public String getEvidence() {
                return evidence;
            }

            public void setEvidence(String evidence) {
                this.evidence = evidence;
            }

            public String getBalanceBank() {
                return balanceBank;
            }

            public void setBalanceBank(String balanceBank) {
                this.balanceBank = balanceBank;
            }

            public String getBankCardHolder() {
                return bankCardHolder;
            }

            public void setBankCardHolder(String bankCardHolder) {
                this.bankCardHolder = bankCardHolder;
            }

            public float getActualAmount() {
                return actualAmount;
            }

            public void setActualAmount(float actualAmount) {
                this.actualAmount = actualAmount;
            }

            public String getBalanceState() {
                return balanceState;
            }

            public void setBalanceState(String balanceState) {
                this.balanceState = balanceState;
            }

            public float getBalanceAmount() {
                return balanceAmount;
            }

            public void setBalanceAmount(float balanceAmount) {
                this.balanceAmount = balanceAmount;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getCreateby() {
                return createby;
            }

            public void setCreateby(String createby) {
                this.createby = createby;
            }

            public float getServiceCharge() {
                return serviceCharge;
            }

            public void setServiceCharge(float serviceCharge) {
                this.serviceCharge = serviceCharge;
            }

            public String getRejectReason() {
                return rejectReason;
            }

            public void setRejectReason(String rejectReason) {
                this.rejectReason = rejectReason;
            }

            public String getBalanceNo() {
                return balanceNo;
            }

            public void setBalanceNo(String balanceNo) {
                this.balanceNo = balanceNo;
            }

            public String getOperatetime() {
                return operatetime;
            }

            public void setOperatetime(String operatetime) {
                this.operatetime = operatetime;
            }

            public float getMarketingCharge() {
                return marketingCharge;
            }

            public void setMarketingCharge(float marketingCharge) {
                this.marketingCharge = marketingCharge;
            }

            public String getBalanceId() {
                return balanceId;
            }

            public void setBalanceId(String balanceId) {
                this.balanceId = balanceId;
            }
        }
    }
}

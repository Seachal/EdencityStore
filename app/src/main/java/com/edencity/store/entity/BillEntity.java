package com.edencity.store.entity;

import java.util.List;

// Created by Ardy on 2020/3/28.
public class BillEntity {

    /**
     * accountBills : {"endRow":8,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"sourceRecordId":"48d17a9c23614804a16036aa475d73dd","changeBalance":0.01,"sourceOrderId":"01202003231010149997","createtime":"2020-03-23 10:10:14","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"aea3b8a3a4724ab581b4d3b8350be392"},{"sourceRecordId":"59e17283c56d488f9b6e672309718a8d","changeBalance":0.23,"sourceOrderId":"01202003211800300292","createtime":"2020-03-21 18:00:30","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"3d6c6221cf534350a727a7f70ec883bd"},{"sourceRecordId":"310be5f2ef574dc481d5dced732cd26d","changeBalance":0.19,"sourceOrderId":"01202003211709311870","createtime":"2020-03-21 17:09:31","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"95fd3a8a6d614693818b215918a515db"},{"sourceRecordId":"726bfa9fb9784a22a8b2c5f662fd40d7","changeBalance":0.12,"sourceOrderId":"01202003211658276435","createtime":"2020-03-21 16:58:27","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"7bbc2e8d382444519e3fb2cd97074993"},{"sourceRecordId":"a12733436a0b4815af71a1c4f41df8b2","changeBalance":0.05,"sourceOrderId":"01202003211559551211","createtime":"2020-03-21 15:59:55","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"e0725f062b164234bb91e1bbd0050441"},{"sourceRecordId":"7915e7e2e2be4a03b815150fe49e2a81","changeBalance":0.05,"sourceOrderId":"01202003211546078145","createtime":"2020-03-21 15:46:07","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"11aba65677d04ad7afd995e993048b71"},{"sourceRecordId":"985ae3cbd0574547acb98e63096f3427","changeBalance":0.03,"sourceOrderId":"01202003211545110668","createtime":"2020-03-21 15:45:11","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"d46c25bebffa41eda90d68ff7913fc3b"},{"sourceRecordId":"01ac9adc95974b3aaa33a28a3b2bc8ca","changeBalance":0.01,"sourceOrderId":"01202003211530526541","createtime":"2020-03-21 15:30:52","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"dd500899c513432eb960d49a3941427d"}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"orderBy":"","pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":8,"startRow":1,"total":8}
     */

    private AccountBillsBean accountBills;

    public AccountBillsBean getAccountBills() {
        return accountBills;
    }

    public void setAccountBills(AccountBillsBean accountBills) {
        this.accountBills = accountBills;
    }

    public static class AccountBillsBean {
        /**
         * endRow : 8
         * firstPage : 1
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * lastPage : 1
         * list : [{"sourceRecordId":"48d17a9c23614804a16036aa475d73dd","changeBalance":0.01,"sourceOrderId":"01202003231010149997","createtime":"2020-03-23 10:10:14","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"aea3b8a3a4724ab581b4d3b8350be392"},{"sourceRecordId":"59e17283c56d488f9b6e672309718a8d","changeBalance":0.23,"sourceOrderId":"01202003211800300292","createtime":"2020-03-21 18:00:30","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"3d6c6221cf534350a727a7f70ec883bd"},{"sourceRecordId":"310be5f2ef574dc481d5dced732cd26d","changeBalance":0.19,"sourceOrderId":"01202003211709311870","createtime":"2020-03-21 17:09:31","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"95fd3a8a6d614693818b215918a515db"},{"sourceRecordId":"726bfa9fb9784a22a8b2c5f662fd40d7","changeBalance":0.12,"sourceOrderId":"01202003211658276435","createtime":"2020-03-21 16:58:27","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"7bbc2e8d382444519e3fb2cd97074993"},{"sourceRecordId":"a12733436a0b4815af71a1c4f41df8b2","changeBalance":0.05,"sourceOrderId":"01202003211559551211","createtime":"2020-03-21 15:59:55","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"e0725f062b164234bb91e1bbd0050441"},{"sourceRecordId":"7915e7e2e2be4a03b815150fe49e2a81","changeBalance":0.05,"sourceOrderId":"01202003211546078145","createtime":"2020-03-21 15:46:07","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"11aba65677d04ad7afd995e993048b71"},{"sourceRecordId":"985ae3cbd0574547acb98e63096f3427","changeBalance":0.03,"sourceOrderId":"01202003211545110668","createtime":"2020-03-21 15:45:11","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"d46c25bebffa41eda90d68ff7913fc3b"},{"sourceRecordId":"01ac9adc95974b3aaa33a28a3b2bc8ca","changeBalance":0.01,"sourceOrderId":"01202003211530526541","createtime":"2020-03-21 15:30:52","sourceType":"1","providerId":"cf3da73eb0b04b93bb659bd767ef8e95","changeType":"0","changeRecordId":"dd500899c513432eb960d49a3941427d"}]
         * navigatePages : 8
         * navigatepageNums : [1]
         * nextPage : 0
         * orderBy :
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * prePage : 0
         * size : 8
         * startRow : 1
         * total : 8
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

        public static class ListBean {
            /**
             * sourceRecordId : 48d17a9c23614804a16036aa475d73dd
             * changeBalance : 0.01
             * sourceOrderId : 01202003231010149997
             * createtime : 2020-03-23 10:10:14
             * sourceType : 1
             * providerId : cf3da73eb0b04b93bb659bd767ef8e95
             * changeType : 0
             * changeRecordId : aea3b8a3a4724ab581b4d3b8350be392
             */

            private String sourceRecordId;
            private double changeBalance;
            private String sourceOrderId;
            private String createtime;
            private String sourceType;
            private String providerId;
            private String changeType;
            private String changeRecordId;

            public String getSourceRecordId() {
                return sourceRecordId;
            }

            public void setSourceRecordId(String sourceRecordId) {
                this.sourceRecordId = sourceRecordId;
            }

            public double getChangeBalance() {
                return changeBalance;
            }

            public void setChangeBalance(double changeBalance) {
                this.changeBalance = changeBalance;
            }

            public String getSourceOrderId() {
                return sourceOrderId;
            }

            public void setSourceOrderId(String sourceOrderId) {
                this.sourceOrderId = sourceOrderId;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getSourceType() {
                return sourceType;
            }

            public void setSourceType(String sourceType) {
                this.sourceType = sourceType;
            }

            public String getProviderId() {
                return providerId;
            }

            public void setProviderId(String providerId) {
                this.providerId = providerId;
            }

            public String getChangeType() {
                return changeType;
            }

            public void setChangeType(String changeType) {
                this.changeType = changeType;
            }

            public String getChangeRecordId() {
                return changeRecordId;
            }

            public void setChangeRecordId(String changeRecordId) {
                this.changeRecordId = changeRecordId;
            }
        }
    }
}

package com.edencity.store.entity;

import java.util.List;

// Created by Ardy on 2020/4/1.
public class MsgEntity {

    /**
     * messageList : {"endRow":5,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"createtime":"2020-04-01 11:29:24","myMessageId":"c8e5da3f2539479baf0d7ac4dd57810e","providerId":"e9486bdc9d10491f9c9896e7b4838cf9","title":"收款通知","content":"主人，小伊恭喜您于2020-04-01 11:29:24收到0.01伊甸币。"},{"createtime":"2020-04-01 11:28:55","myMessageId":"3d8ce9af87614ead932c7c39e5e0ed2f","providerId":"e9486bdc9d10491f9c9896e7b4838cf9","title":"开通成员通知","content":"主人，小伊恭喜您成为伊甸城EB联盟付费成员，有效期至2023-04-01 11:12:00，更多权益等着您哟！"},{"createtime":"2020-04-01 11:18:54","myMessageId":"a64a16a5669b4279a3980854964ce863","providerId":"e9486bdc9d10491f9c9896e7b4838cf9","title":"收款通知","content":"主人，小伊恭喜您于2020-04-01 11:18:54收到0.01伊甸币。"},{"createtime":"2020-04-01 11:16:56","myMessageId":"93e17248853742f1a1a997a5067dc6bc","providerId":"e9486bdc9d10491f9c9896e7b4838cf9","title":"开通成员通知","content":"主人，小伊恭喜您成为伊甸城EB联盟付费成员，有效期至2022-04-01 11:12:00，更多权益等着您哟！"},{"createtime":"2020-04-01 11:12:30","myMessageId":"e6b3632518bd427db24f043e87ff2c62","providerId":"e9486bdc9d10491f9c9896e7b4838cf9","title":"开通成员通知","content":"主人，小伊恭喜您成为伊甸城EB联盟付费成员，有效期至2021-04-01 11:12:00，更多权益等着您哟！"}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"orderBy":"","pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":5,"startRow":1,"total":5}
     */

    private MessageListBean messageList;

    public MessageListBean getMessageList() {
        return messageList;
    }

    public void setMessageList(MessageListBean messageList) {
        this.messageList = messageList;
    }

    public static class MessageListBean {
        /**
         * endRow : 5
         * firstPage : 1
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * lastPage : 1
         * list : [{"createtime":"2020-04-01 11:29:24","myMessageId":"c8e5da3f2539479baf0d7ac4dd57810e","providerId":"e9486bdc9d10491f9c9896e7b4838cf9","title":"收款通知","content":"主人，小伊恭喜您于2020-04-01 11:29:24收到0.01伊甸币。"},{"createtime":"2020-04-01 11:28:55","myMessageId":"3d8ce9af87614ead932c7c39e5e0ed2f","providerId":"e9486bdc9d10491f9c9896e7b4838cf9","title":"开通成员通知","content":"主人，小伊恭喜您成为伊甸城EB联盟付费成员，有效期至2023-04-01 11:12:00，更多权益等着您哟！"},{"createtime":"2020-04-01 11:18:54","myMessageId":"a64a16a5669b4279a3980854964ce863","providerId":"e9486bdc9d10491f9c9896e7b4838cf9","title":"收款通知","content":"主人，小伊恭喜您于2020-04-01 11:18:54收到0.01伊甸币。"},{"createtime":"2020-04-01 11:16:56","myMessageId":"93e17248853742f1a1a997a5067dc6bc","providerId":"e9486bdc9d10491f9c9896e7b4838cf9","title":"开通成员通知","content":"主人，小伊恭喜您成为伊甸城EB联盟付费成员，有效期至2022-04-01 11:12:00，更多权益等着您哟！"},{"createtime":"2020-04-01 11:12:30","myMessageId":"e6b3632518bd427db24f043e87ff2c62","providerId":"e9486bdc9d10491f9c9896e7b4838cf9","title":"开通成员通知","content":"主人，小伊恭喜您成为伊甸城EB联盟付费成员，有效期至2021-04-01 11:12:00，更多权益等着您哟！"}]
         * navigatePages : 8
         * navigatepageNums : [1]
         * nextPage : 0
         * orderBy :
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * prePage : 0
         * size : 5
         * startRow : 1
         * total : 5
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
             * createtime : 2020-04-01 11:29:24
             * myMessageId : c8e5da3f2539479baf0d7ac4dd57810e
             * providerId : e9486bdc9d10491f9c9896e7b4838cf9
             * title : 收款通知
             * content : 主人，小伊恭喜您于2020-04-01 11:29:24收到0.01伊甸币。
             */

            private String createtime;
            private String myMessageId;
            private String providerId;
            private String title;
            private String content;

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getMyMessageId() {
                return myMessageId;
            }

            public void setMyMessageId(String myMessageId) {
                this.myMessageId = myMessageId;
            }

            public String getProviderId() {
                return providerId;
            }

            public void setProviderId(String providerId) {
                this.providerId = providerId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}

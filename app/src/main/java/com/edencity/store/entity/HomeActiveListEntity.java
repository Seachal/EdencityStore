package com.edencity.store.entity;

import java.util.List;

// Created by Ardy on 2020/3/27.
public class HomeActiveListEntity {

    private List<ActivitiesListBean> activitiesList;

    public List<ActivitiesListBean> getActivitiesList() {
        return activitiesList;
    }

    public void setActivitiesList(List<ActivitiesListBean> activitiesList) {
        this.activitiesList = activitiesList;
    }

    @Override
    public String toString() {
        return "HomeActiveListEntity{" +
                "activitiesList=" + activitiesList +
                '}';
    }

    public static class ActivitiesListBean {


        @Override
        public String toString() {
            return "ActivitiesListBean{" +
                    "runTimeEnd='" + runTimeEnd + '\'' +
                    ", sponsor='" + sponsor + '\'' +
                    ", runTimeStart='" + runTimeStart + '\'' +
                    ", activitiesAddress='" + activitiesAddress + '\'' +
                    ", coverImg='" + coverImg + '\'' +
                    ", activitiesStatus='" + activitiesStatus + '\'' +
                    ", rules='" + rules + '\'' +
                    ", detailUrl='" + detailUrl + '\'' +
                    ", allowedNumber=" + allowedNumber +
                    ", activitiesName='" + activitiesName + '\'' +
                    ", hadSignCount=" + hadSignCount +
                    ", isSign=" + isSign +
                    ", activitiesId='" + activitiesId + '\'' +
                    ", deadline='" + deadline + '\'' +
                    ", propagateImg='" + propagateImg + '\'' +
                    '}';
        }

        /**
         * runTimeEnd : 2020-02-09 00:00:00
         * sponsor : 55
         * runTimeStart : 2020-02-08 00:00:00
         * activitiesAddress : 55
         * coverImg : http://edencity-cdn.oss-cn-qingdao.aliyuncs.com/activity/goods/8b4a28ed185a44c98b6d4ca3ad048ce0co
         * verImg/1582101484850_1569946955324.jpeg
         * activitiesStatus : 1
         * rules : 55
         * allowedNumber : 55
         * activitiesName : 测试活动05
         * hadSignCount : 0
         * isSign : 0
         * activitiesId : 8b4a28ed185a44c98b6d4ca3ad048ce0
         * deadline : 2020-02-29 16:02:00
         * propagateImg : http://edencity-cdn.oss-cn-qingdao.aliyuncs.com/activity/goods/8b4a28ed185a44c98b6d4
         * ca3ad048ce0propagateImg/1582101485582_IMG20200123020444.jpg
         */

        private String runTimeEnd;
        private String sponsor;
        private String runTimeStart;
        private String activitiesAddress;
        private String coverImg;
        private String activitiesStatus;
        private String rules;
        private String detailUrl;
        private int allowedNumber;
        private String activitiesName;
        private int hadSignCount;
        private int isSign;
        private String activitiesId;
        private String deadline;
        private String propagateImg;

        public String getRunTimeEnd() {
            return runTimeEnd;
        }

        public void setRunTimeEnd(String runTimeEnd) {
            this.runTimeEnd = runTimeEnd;
        }

        public String getSponsor() {
            return sponsor;
        }

        public void setSponsor(String sponsor) {
            this.sponsor = sponsor;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getRunTimeStart() {
            return runTimeStart;
        }

        public void setRunTimeStart(String runTimeStart) {
            this.runTimeStart = runTimeStart;
        }

        public String getActivitiesAddress() {
            return activitiesAddress;
        }

        public void setActivitiesAddress(String activitiesAddress) {
            this.activitiesAddress = activitiesAddress;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public String getActivitiesStatus() {
            return activitiesStatus;
        }

        public void setActivitiesStatus(String activitiesStatus) {
            this.activitiesStatus = activitiesStatus;
        }

        public String getRules() {
            return rules;
        }

        public void setRules(String rules) {
            this.rules = rules;
        }

        public int getAllowedNumber() {
            return allowedNumber;
        }

        public void setAllowedNumber(int allowedNumber) {
            this.allowedNumber = allowedNumber;
        }

        public String getActivitiesName() {
            return activitiesName;
        }

        public void setActivitiesName(String activitiesName) {
            this.activitiesName = activitiesName;
        }

        public int getHadSignCount() {
            return hadSignCount;
        }

        public void setHadSignCount(int hadSignCount) {
            this.hadSignCount = hadSignCount;
        }

        public int getIsSign() {
            return isSign;
        }

        public void setIsSign(int isSign) {
            this.isSign = isSign;
        }

        public String getActivitiesId() {
            return activitiesId;
        }

        public void setActivitiesId(String activitiesId) {
            this.activitiesId = activitiesId;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getPropagateImg() {
            return propagateImg;
        }

        public void setPropagateImg(String propagateImg) {
            this.propagateImg = propagateImg;
        }
    }
}

package com.edencity.store.entity;

import java.util.List;

// Created by Ardy on 2020/3/28.
public class AllMemberEntity {

    private List<AllMembershipRightsBean> allMembershipRights;

    public List<AllMembershipRightsBean> getAllMembershipRights() {
        return allMembershipRights;
    }

    public void setAllMembershipRights(List<AllMembershipRightsBean> allMembershipRights) {
        this.allMembershipRights = allMembershipRights;
    }

    public static class AllMembershipRightsBean {
        /**
         * rightsName : 会员培训
         * rightsIcon : null
         * rightsStatus : 1
         * rightsType : 1
         * rightsDesc : 对会员进行技能知识培训
         * memberRightsId : ceshi02
         */

        private String rightsName;
        private String rightsIcon;
        private String rightsStatus;
        private String rightsIconInvisible;
        private String rightsType;
        private String rightsDesc;
        private String memberRightsId;
        private String configMemberId;

        public String getConfigMemberId() {
            return configMemberId;
        }

        public void setConfigMemberId(String configMemberId) {
            this.configMemberId = configMemberId;
        }

        public String getRightsIconInvisible() {
            return rightsIconInvisible;
        }

        public void setRightsIconInvisible(String rightsIconInvisible) {
            this.rightsIconInvisible = rightsIconInvisible;
        }

        public String getRightsName() {
            return rightsName;
        }

        public void setRightsName(String rightsName) {
            this.rightsName = rightsName;
        }

        public String getRightsIcon() {
            return rightsIcon;
        }

        public void setRightsIcon(String rightsIcon) {
            this.rightsIcon = rightsIcon;
        }

        public String getRightsStatus() {
            return rightsStatus;
        }

        public void setRightsStatus(String rightsStatus) {
            this.rightsStatus = rightsStatus;
        }

        public String getRightsType() {
            return rightsType;
        }

        public void setRightsType(String rightsType) {
            this.rightsType = rightsType;
        }

        public String getRightsDesc() {
            return rightsDesc;
        }

        public void setRightsDesc(String rightsDesc) {
            this.rightsDesc = rightsDesc;
        }

        public String getMemberRightsId() {
            return memberRightsId;
        }

        public void setMemberRightsId(String memberRightsId) {
            this.memberRightsId = memberRightsId;
        }
    }
}

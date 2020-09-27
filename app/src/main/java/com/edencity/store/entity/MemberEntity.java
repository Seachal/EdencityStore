package com.edencity.store.entity;

// Created by Ardy on 2020/4/1.
public class MemberEntity {

    /**
     * membershipDetail : {"turnoverLimit":100,"rightsName":"会员停车,成员活动,会员培训","rights":"ceshi01,ceshi02,ceshi03","customerFlowLimit":10,"payLimit":11,"memberConfigId":"ceshi015678123456781234567812345","memberId":"1002010730"}
     */

    private MembershipDetailBean membershipDetail;

    public MembershipDetailBean getMembershipDetail() {
        return membershipDetail;
    }

    public void setMembershipDetail(MembershipDetailBean membershipDetail) {
        this.membershipDetail = membershipDetail;
    }

    public static class MembershipDetailBean {
        /**
         * turnoverLimit : 100
         * rightsName : 会员停车,成员活动,会员培训
         * rights : ceshi01,ceshi02,ceshi03
         * customerFlowLimit : 10
         * payLimit : 11
         * memberConfigId : ceshi015678123456781234567812345
         * memberId : 1002010730
         */

        private float turnoverLimit;
        private String rightsName;
        private String rights;
        private float customerFlowLimit;
        private float payLimit;
        private String memberConfigId;
        private String memberId;

        public float getTurnoverLimit() {
            return turnoverLimit;
        }

        public void setTurnoverLimit(float turnoverLimit) {
            this.turnoverLimit = turnoverLimit;
        }

        public String getRightsName() {
            return rightsName;
        }

        public void setRightsName(String rightsName) {
            this.rightsName = rightsName;
        }

        public String getRights() {
            return rights;
        }

        public void setRights(String rights) {
            this.rights = rights;
        }

        public float getCustomerFlowLimit() {
            return customerFlowLimit;
        }

        public void setCustomerFlowLimit(float customerFlowLimit) {
            this.customerFlowLimit = customerFlowLimit;
        }

        public float getPayLimit() {
            return payLimit;
        }

        public void setPayLimit(float payLimit) {
            this.payLimit = payLimit;
        }

        public String getMemberConfigId() {
            return memberConfigId;
        }

        public void setMemberConfigId(String memberConfigId) {
            this.memberConfigId = memberConfigId;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }
    }
}

package com.mparticle.commerce;

public class TransactionAttributes {
    private String mAffiliation = null;
    private String mCouponCode = null;
    private String mId = null;
    private Double mRevenue;
    private Double mShipping;
    private Double mTax;

    public TransactionAttributes(TransactionAttributes transactionAttributes) {
        this.mAffiliation = transactionAttributes.mAffiliation;
        this.mRevenue = transactionAttributes.mRevenue;
        this.mShipping = transactionAttributes.mShipping;
        this.mTax = transactionAttributes.mTax;
        this.mCouponCode = transactionAttributes.mCouponCode;
        this.mId = transactionAttributes.mId;
    }

    public String getId() {
        return this.mId;
    }

    public TransactionAttributes() {
    }

    public TransactionAttributes(String transactionId) {
        setId(transactionId);
    }

    public TransactionAttributes setId(String id) {
        this.mId = id;
        return this;
    }

    public String getCouponCode() {
        return this.mCouponCode;
    }

    public TransactionAttributes setCouponCode(String couponCode) {
        this.mCouponCode = couponCode;
        return this;
    }

    public Double getTax() {
        return this.mTax;
    }

    public TransactionAttributes setTax(Double tax) {
        this.mTax = tax;
        return this;
    }

    public Double getShipping() {
        return this.mShipping;
    }

    public TransactionAttributes setShipping(Double shipping) {
        this.mShipping = shipping;
        return this;
    }

    public Double getRevenue() {
        return this.mRevenue;
    }

    public TransactionAttributes setRevenue(Double revenue) {
        this.mRevenue = revenue;
        return this;
    }

    public String getAffiliation() {
        return this.mAffiliation;
    }

    public TransactionAttributes setAffiliation(String affiliation) {
        this.mAffiliation = affiliation;
        return this;
    }
}

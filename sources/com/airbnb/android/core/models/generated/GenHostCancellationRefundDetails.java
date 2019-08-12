package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHostCancellationRefundDetails implements Parcelable {
    @JsonProperty("coupon_bonus_formatted")
    protected String mCouponBonusFormatted;
    @JsonProperty("has_host_cancel_coupon")
    protected boolean mHasHostCancelCoupon;
    @JsonProperty("refund_amount_formatted")
    protected String mRefundAmountFormatted;
    @JsonProperty("total_refund_formatted")
    protected String mTotalRefundFormatted;

    protected GenHostCancellationRefundDetails(String couponBonusFormatted, String refundAmountFormatted, String totalRefundFormatted, boolean hasHostCancelCoupon) {
        this();
        this.mCouponBonusFormatted = couponBonusFormatted;
        this.mRefundAmountFormatted = refundAmountFormatted;
        this.mTotalRefundFormatted = totalRefundFormatted;
        this.mHasHostCancelCoupon = hasHostCancelCoupon;
    }

    protected GenHostCancellationRefundDetails() {
    }

    public String getCouponBonusFormatted() {
        return this.mCouponBonusFormatted;
    }

    @JsonProperty("coupon_bonus_formatted")
    public void setCouponBonusFormatted(String value) {
        this.mCouponBonusFormatted = value;
    }

    public String getRefundAmountFormatted() {
        return this.mRefundAmountFormatted;
    }

    @JsonProperty("refund_amount_formatted")
    public void setRefundAmountFormatted(String value) {
        this.mRefundAmountFormatted = value;
    }

    public String getTotalRefundFormatted() {
        return this.mTotalRefundFormatted;
    }

    @JsonProperty("total_refund_formatted")
    public void setTotalRefundFormatted(String value) {
        this.mTotalRefundFormatted = value;
    }

    public boolean hasHostCancelCoupon() {
        return this.mHasHostCancelCoupon;
    }

    @JsonProperty("has_host_cancel_coupon")
    public void setHasHostCancelCoupon(boolean value) {
        this.mHasHostCancelCoupon = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mCouponBonusFormatted);
        parcel.writeString(this.mRefundAmountFormatted);
        parcel.writeString(this.mTotalRefundFormatted);
        parcel.writeBooleanArray(new boolean[]{this.mHasHostCancelCoupon});
    }

    public void readFromParcel(Parcel source) {
        this.mCouponBonusFormatted = source.readString();
        this.mRefundAmountFormatted = source.readString();
        this.mTotalRefundFormatted = source.readString();
        this.mHasHostCancelCoupon = source.createBooleanArray()[0];
    }
}

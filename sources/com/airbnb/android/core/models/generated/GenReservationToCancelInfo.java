package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReservationToCancelInfo implements Parcelable {
    @JsonProperty("allow_host_behavior_reason")
    protected boolean mAllowHostBehaviorReason;
    @JsonProperty("has_payment_problem")
    protected boolean mHasPaymentProblem;

    protected GenReservationToCancelInfo(boolean allowHostBehaviorReason, boolean hasPaymentProblem) {
        this();
        this.mAllowHostBehaviorReason = allowHostBehaviorReason;
        this.mHasPaymentProblem = hasPaymentProblem;
    }

    protected GenReservationToCancelInfo() {
    }

    public boolean isAllowHostBehaviorReason() {
        return this.mAllowHostBehaviorReason;
    }

    @JsonProperty("allow_host_behavior_reason")
    public void setAllowHostBehaviorReason(boolean value) {
        this.mAllowHostBehaviorReason = value;
    }

    public boolean hasPaymentProblem() {
        return this.mHasPaymentProblem;
    }

    @JsonProperty("has_payment_problem")
    public void setHasPaymentProblem(boolean value) {
        this.mHasPaymentProblem = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeBooleanArray(new boolean[]{this.mAllowHostBehaviorReason, this.mHasPaymentProblem});
    }

    public void readFromParcel(Parcel source) {
        boolean[] bools = source.createBooleanArray();
        this.mAllowHostBehaviorReason = bools[0];
        this.mHasPaymentProblem = bools[1];
    }
}

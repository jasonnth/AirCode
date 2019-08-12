package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenDynamicPricingExplanation implements Parcelable {
    @JsonProperty("positive")
    protected boolean mPositive;
    @JsonProperty("reason")
    protected String mReason;

    protected GenDynamicPricingExplanation(String reason, boolean positive) {
        this();
        this.mReason = reason;
        this.mPositive = positive;
    }

    protected GenDynamicPricingExplanation() {
    }

    public String getReason() {
        return this.mReason;
    }

    @JsonProperty("reason")
    public void setReason(String value) {
        this.mReason = value;
    }

    public boolean isPositive() {
        return this.mPositive;
    }

    @JsonProperty("positive")
    public void setPositive(boolean value) {
        this.mPositive = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mReason);
        parcel.writeBooleanArray(new boolean[]{this.mPositive});
    }

    public void readFromParcel(Parcel source) {
        this.mReason = source.readString();
        this.mPositive = source.createBooleanArray()[0];
    }
}

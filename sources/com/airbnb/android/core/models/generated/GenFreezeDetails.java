package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenFreezeDetails implements Parcelable {
    @JsonProperty("reason")
    protected String mReason;
    @JsonProperty("should_be_frozen")
    protected boolean mShouldBeFrozen;

    protected GenFreezeDetails(String reason, boolean shouldBeFrozen) {
        this();
        this.mReason = reason;
        this.mShouldBeFrozen = shouldBeFrozen;
    }

    protected GenFreezeDetails() {
    }

    public String getReason() {
        return this.mReason;
    }

    @JsonProperty("reason")
    public void setReason(String value) {
        this.mReason = value;
    }

    public boolean isShouldBeFrozen() {
        return this.mShouldBeFrozen;
    }

    @JsonProperty("should_be_frozen")
    public void setShouldBeFrozen(boolean value) {
        this.mShouldBeFrozen = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mReason);
        parcel.writeBooleanArray(new boolean[]{this.mShouldBeFrozen});
    }

    public void readFromParcel(Parcel source) {
        this.mReason = source.readString();
        this.mShouldBeFrozen = source.createBooleanArray()[0];
    }
}

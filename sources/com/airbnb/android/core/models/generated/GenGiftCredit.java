package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGiftCredit implements Parcelable {
    @JsonProperty("amount")
    protected int mAmount;
    @JsonProperty("status")
    protected String mStatus;

    protected GenGiftCredit(String status, int amount) {
        this();
        this.mStatus = status;
        this.mAmount = amount;
    }

    protected GenGiftCredit() {
    }

    public String getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(String value) {
        this.mStatus = value;
    }

    public int getAmount() {
        return this.mAmount;
    }

    @JsonProperty("amount")
    public void setAmount(int value) {
        this.mAmount = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mStatus);
        parcel.writeInt(this.mAmount);
    }

    public void readFromParcel(Parcel source) {
        this.mStatus = source.readString();
        this.mAmount = source.readInt();
    }
}

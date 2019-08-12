package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGiftCreditBalance implements Parcelable {
    @JsonProperty("balance_formatted")
    protected String mFormattedBalance;
    @JsonProperty("balance_is_nonzero")
    protected boolean mIsNonzero;
    @JsonProperty("user_id")
    protected int mUserId;

    protected GenGiftCreditBalance(String formattedBalance, boolean isNonzero, int userId) {
        this();
        this.mFormattedBalance = formattedBalance;
        this.mIsNonzero = isNonzero;
        this.mUserId = userId;
    }

    protected GenGiftCreditBalance() {
    }

    public String getFormattedBalance() {
        return this.mFormattedBalance;
    }

    @JsonProperty("balance_formatted")
    public void setFormattedBalance(String value) {
        this.mFormattedBalance = value;
    }

    public boolean isNonzero() {
        return this.mIsNonzero;
    }

    @JsonProperty("balance_is_nonzero")
    public void setIsNonzero(boolean value) {
        this.mIsNonzero = value;
    }

    public int getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(int value) {
        this.mUserId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mFormattedBalance);
        parcel.writeBooleanArray(new boolean[]{this.mIsNonzero});
        parcel.writeInt(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mFormattedBalance = source.readString();
        this.mIsNonzero = source.createBooleanArray()[0];
        this.mUserId = source.readInt();
    }
}

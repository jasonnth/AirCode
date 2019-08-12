package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSecurityCheckPhoneNumber implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("last_four_digits")
    protected String mLastFourDigits;

    protected GenSecurityCheckPhoneNumber(String lastFourDigits, long id) {
        this();
        this.mLastFourDigits = lastFourDigits;
        this.mId = id;
    }

    protected GenSecurityCheckPhoneNumber() {
    }

    public String getLastFourDigits() {
        return this.mLastFourDigits;
    }

    @JsonProperty("last_four_digits")
    public void setLastFourDigits(String value) {
        this.mLastFourDigits = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mLastFourDigits);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mLastFourDigits = source.readString();
        this.mId = source.readLong();
    }
}

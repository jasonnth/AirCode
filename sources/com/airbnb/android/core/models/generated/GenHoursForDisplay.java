package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHoursForDisplay implements Parcelable {
    @JsonProperty("days")
    protected String mDays;
    @JsonProperty("times")
    protected String mTimes;

    protected GenHoursForDisplay(String days, String times) {
        this();
        this.mDays = days;
        this.mTimes = times;
    }

    protected GenHoursForDisplay() {
    }

    public String getDays() {
        return this.mDays;
    }

    @JsonProperty("days")
    public void setDays(String value) {
        this.mDays = value;
    }

    public String getTimes() {
        return this.mTimes;
    }

    @JsonProperty("times")
    public void setTimes(String value) {
        this.mTimes = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mDays);
        parcel.writeString(this.mTimes);
    }

    public void readFromParcel(Parcel source) {
        this.mDays = source.readString();
        this.mTimes = source.readString();
    }
}

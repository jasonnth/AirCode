package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenMaxDaysNoticeSetting implements Parcelable {
    @JsonProperty("days")
    protected int mDays;

    protected GenMaxDaysNoticeSetting(int days) {
        this();
        this.mDays = days;
    }

    protected GenMaxDaysNoticeSetting() {
    }

    public int getDays() {
        return this.mDays;
    }

    @JsonProperty("days")
    public void setDays(int value) {
        this.mDays = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mDays);
    }

    public void readFromParcel(Parcel source) {
        this.mDays = source.readInt();
    }
}

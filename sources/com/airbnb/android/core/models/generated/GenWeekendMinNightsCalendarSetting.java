package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenWeekendMinNightsCalendarSetting implements Parcelable {
    @JsonProperty("min_nights")
    protected int mMinNights;

    protected GenWeekendMinNightsCalendarSetting(int minNights) {
        this();
        this.mMinNights = minNights;
    }

    protected GenWeekendMinNightsCalendarSetting() {
    }

    public int getMinNights() {
        return this.mMinNights;
    }

    @JsonProperty("min_nights")
    public void setMinNights(int value) {
        this.mMinNights = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mMinNights);
    }

    public void readFromParcel(Parcel source) {
        this.mMinNights = source.readInt();
    }
}

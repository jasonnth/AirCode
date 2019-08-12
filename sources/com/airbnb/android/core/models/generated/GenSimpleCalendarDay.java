package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSimpleCalendarDay implements Parcelable {
    @JsonProperty("available")
    protected boolean mAvailable;
    @JsonProperty("date")
    protected AirDate mDate;

    protected GenSimpleCalendarDay(AirDate date, boolean available) {
        this();
        this.mDate = date;
        this.mAvailable = available;
    }

    protected GenSimpleCalendarDay() {
    }

    public AirDate getDate() {
        return this.mDate;
    }

    @JsonProperty("date")
    public void setDate(AirDate value) {
        this.mDate = value;
    }

    public boolean isAvailable() {
        return this.mAvailable;
    }

    @JsonProperty("available")
    public void setAvailable(boolean value) {
        this.mAvailable = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mDate, 0);
        parcel.writeBooleanArray(new boolean[]{this.mAvailable});
    }

    public void readFromParcel(Parcel source) {
        this.mDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mAvailable = source.createBooleanArray()[0];
    }
}

package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSeasonalMinNightsCalendarSetting implements Parcelable {
    @JsonProperty("end_date")
    protected AirDate mEndDate;
    @JsonProperty("min_nights")
    protected int mMinNights;
    @JsonProperty("start_date")
    protected AirDate mStartDate;
    @JsonProperty("start_day_of_week")
    protected Integer mStartDayOfWeek;

    protected GenSeasonalMinNightsCalendarSetting(AirDate startDate, AirDate endDate, Integer startDayOfWeek, int minNights) {
        this();
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.mStartDayOfWeek = startDayOfWeek;
        this.mMinNights = minNights;
    }

    protected GenSeasonalMinNightsCalendarSetting() {
    }

    public AirDate getStartDate() {
        return this.mStartDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(AirDate value) {
        this.mStartDate = value;
    }

    public AirDate getEndDate() {
        return this.mEndDate;
    }

    @JsonProperty("end_date")
    public void setEndDate(AirDate value) {
        this.mEndDate = value;
    }

    public Integer getStartDayOfWeek() {
        return this.mStartDayOfWeek;
    }

    @JsonProperty("start_day_of_week")
    public void setStartDayOfWeek(Integer value) {
        this.mStartDayOfWeek = value;
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
        parcel.writeParcelable(this.mStartDate, 0);
        parcel.writeParcelable(this.mEndDate, 0);
        parcel.writeValue(this.mStartDayOfWeek);
        parcel.writeInt(this.mMinNights);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mEndDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mStartDayOfWeek = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mMinNights = source.readInt();
    }
}

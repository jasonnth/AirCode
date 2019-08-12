package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAvailabilityCondition implements Parcelable {
    @JsonProperty("closed_to_arrival")
    protected boolean mClosedToArrival;
    @JsonProperty("closed_to_departure")
    protected boolean mClosedToDeparture;
    @JsonProperty("max_nights")
    protected int mMaxNights;
    @JsonProperty("min_nights")
    protected int mMinNights;
    @JsonProperty("start_day_of_week")
    protected Integer mStartDayOfWeek;

    protected GenAvailabilityCondition(Integer startDayOfWeek, boolean closedToArrival, boolean closedToDeparture, int minNights, int maxNights) {
        this();
        this.mStartDayOfWeek = startDayOfWeek;
        this.mClosedToArrival = closedToArrival;
        this.mClosedToDeparture = closedToDeparture;
        this.mMinNights = minNights;
        this.mMaxNights = maxNights;
    }

    protected GenAvailabilityCondition() {
    }

    public Integer getStartDayOfWeek() {
        return this.mStartDayOfWeek;
    }

    @JsonProperty("start_day_of_week")
    public void setStartDayOfWeek(Integer value) {
        this.mStartDayOfWeek = value;
    }

    public boolean isClosedToArrival() {
        return this.mClosedToArrival;
    }

    @JsonProperty("closed_to_arrival")
    public void setClosedToArrival(boolean value) {
        this.mClosedToArrival = value;
    }

    public boolean isClosedToDeparture() {
        return this.mClosedToDeparture;
    }

    @JsonProperty("closed_to_departure")
    public void setClosedToDeparture(boolean value) {
        this.mClosedToDeparture = value;
    }

    public int getMinNights() {
        return this.mMinNights;
    }

    @JsonProperty("min_nights")
    public void setMinNights(int value) {
        this.mMinNights = value;
    }

    public int getMaxNights() {
        return this.mMaxNights;
    }

    @JsonProperty("max_nights")
    public void setMaxNights(int value) {
        this.mMaxNights = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mStartDayOfWeek);
        parcel.writeBooleanArray(new boolean[]{this.mClosedToArrival, this.mClosedToDeparture});
        parcel.writeInt(this.mMinNights);
        parcel.writeInt(this.mMaxNights);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDayOfWeek = (Integer) source.readValue(Integer.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mClosedToArrival = bools[0];
        this.mClosedToDeparture = bools[1];
        this.mMinNights = source.readInt();
        this.mMaxNights = source.readInt();
    }
}

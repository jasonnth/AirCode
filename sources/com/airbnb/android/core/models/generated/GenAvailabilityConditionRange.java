package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.AvailabilityCondition;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAvailabilityConditionRange implements Parcelable {
    @JsonProperty("conditions")
    protected AvailabilityCondition mConditions;
    @JsonProperty("end_date")
    protected AirDate mEndDate;
    @JsonProperty("start_date")
    protected AirDate mStartDate;

    protected GenAvailabilityConditionRange(AirDate startDate, AirDate endDate, AvailabilityCondition conditions) {
        this();
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.mConditions = conditions;
    }

    protected GenAvailabilityConditionRange() {
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

    public AvailabilityCondition getConditions() {
        return this.mConditions;
    }

    @JsonProperty("conditions")
    public void setConditions(AvailabilityCondition value) {
        this.mConditions = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartDate, 0);
        parcel.writeParcelable(this.mEndDate, 0);
        parcel.writeParcelable(this.mConditions, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mEndDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mConditions = (AvailabilityCondition) source.readParcelable(AvailabilityCondition.class.getClassLoader());
    }
}

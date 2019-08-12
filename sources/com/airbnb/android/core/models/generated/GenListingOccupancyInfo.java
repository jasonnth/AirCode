package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingOccupancyInfo implements Parcelable {
    @JsonProperty("show_occupancy_message")
    protected boolean mOccupancyMessageNeeded;

    protected GenListingOccupancyInfo(boolean occupancyMessageNeeded) {
        this();
        this.mOccupancyMessageNeeded = occupancyMessageNeeded;
    }

    protected GenListingOccupancyInfo() {
    }

    public boolean isOccupancyMessageNeeded() {
        return this.mOccupancyMessageNeeded;
    }

    @JsonProperty("show_occupancy_message")
    public void setOccupancyMessageNeeded(boolean value) {
        this.mOccupancyMessageNeeded = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeBooleanArray(new boolean[]{this.mOccupancyMessageNeeded});
    }

    public void readFromParcel(Parcel source) {
        this.mOccupancyMessageNeeded = source.createBooleanArray()[0];
    }
}

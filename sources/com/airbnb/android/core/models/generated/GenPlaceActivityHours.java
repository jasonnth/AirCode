package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.HoursForDisplay;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenPlaceActivityHours implements Parcelable {
    @JsonProperty("hours_for_display")
    protected List<HoursForDisplay> mHoursForDisplay;
    @JsonProperty("open_status")
    protected String mOpenStatus;

    protected GenPlaceActivityHours(List<HoursForDisplay> hoursForDisplay, String openStatus) {
        this();
        this.mHoursForDisplay = hoursForDisplay;
        this.mOpenStatus = openStatus;
    }

    protected GenPlaceActivityHours() {
    }

    public List<HoursForDisplay> getHoursForDisplay() {
        return this.mHoursForDisplay;
    }

    @JsonProperty("hours_for_display")
    public void setHoursForDisplay(List<HoursForDisplay> value) {
        this.mHoursForDisplay = value;
    }

    public String getOpenStatus() {
        return this.mOpenStatus;
    }

    @JsonProperty("open_status")
    public void setOpenStatus(String value) {
        this.mOpenStatus = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mHoursForDisplay);
        parcel.writeString(this.mOpenStatus);
    }

    public void readFromParcel(Parcel source) {
        this.mHoursForDisplay = source.createTypedArrayList(HoursForDisplay.CREATOR);
        this.mOpenStatus = source.readString();
    }
}

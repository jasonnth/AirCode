package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCheckinTimeSelectionOptions implements Parcelable {
    @JsonProperty("formatted_hour")
    protected String mFormattedHour;
    @JsonProperty("is_check_in_time_instant_bookable")
    protected Boolean mIsCheckInTimeInstantBookable;
    @JsonProperty("localized_guest_checkin_window")
    protected String mLocalizedGuestCheckinWindow;
    @JsonProperty("localized_hour_string")
    protected String mLocalizedHourString;

    protected GenCheckinTimeSelectionOptions(Boolean isCheckInTimeInstantBookable, String formattedHour, String localizedHourString, String localizedGuestCheckinWindow) {
        this();
        this.mIsCheckInTimeInstantBookable = isCheckInTimeInstantBookable;
        this.mFormattedHour = formattedHour;
        this.mLocalizedHourString = localizedHourString;
        this.mLocalizedGuestCheckinWindow = localizedGuestCheckinWindow;
    }

    protected GenCheckinTimeSelectionOptions() {
    }

    public Boolean isIsCheckInTimeInstantBookable() {
        return this.mIsCheckInTimeInstantBookable;
    }

    @JsonProperty("is_check_in_time_instant_bookable")
    public void setIsCheckInTimeInstantBookable(Boolean value) {
        this.mIsCheckInTimeInstantBookable = value;
    }

    public String getFormattedHour() {
        return this.mFormattedHour;
    }

    @JsonProperty("formatted_hour")
    public void setFormattedHour(String value) {
        this.mFormattedHour = value;
    }

    public String getLocalizedHourString() {
        return this.mLocalizedHourString;
    }

    @JsonProperty("localized_hour_string")
    public void setLocalizedHourString(String value) {
        this.mLocalizedHourString = value;
    }

    public String getLocalizedGuestCheckinWindow() {
        return this.mLocalizedGuestCheckinWindow;
    }

    @JsonProperty("localized_guest_checkin_window")
    public void setLocalizedGuestCheckinWindow(String value) {
        this.mLocalizedGuestCheckinWindow = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mIsCheckInTimeInstantBookable);
        parcel.writeString(this.mFormattedHour);
        parcel.writeString(this.mLocalizedHourString);
        parcel.writeString(this.mLocalizedGuestCheckinWindow);
    }

    public void readFromParcel(Parcel source) {
        this.mIsCheckInTimeInstantBookable = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mFormattedHour = source.readString();
        this.mLocalizedHourString = source.readString();
        this.mLocalizedGuestCheckinWindow = source.readString();
    }
}

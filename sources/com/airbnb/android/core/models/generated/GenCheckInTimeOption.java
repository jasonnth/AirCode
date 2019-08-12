package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCheckInTimeOption implements Parcelable {
    @JsonProperty("formatted_hour")
    protected String mFormattedHour;
    @JsonProperty("localized_hour")
    protected String mLocalizedHour;

    protected GenCheckInTimeOption(String formattedHour, String localizedHour) {
        this();
        this.mFormattedHour = formattedHour;
        this.mLocalizedHour = localizedHour;
    }

    protected GenCheckInTimeOption() {
    }

    public String getFormattedHour() {
        return this.mFormattedHour;
    }

    @JsonProperty("formatted_hour")
    public void setFormattedHour(String value) {
        this.mFormattedHour = value;
    }

    public String getLocalizedHour() {
        return this.mLocalizedHour;
    }

    @JsonProperty("localized_hour")
    public void setLocalizedHour(String value) {
        this.mLocalizedHour = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mFormattedHour);
        parcel.writeString(this.mLocalizedHour);
    }

    public void readFromParcel(Parcel source) {
        this.mFormattedHour = source.readString();
        this.mLocalizedHour = source.readString();
    }
}

package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingRegistrationDateAnswer implements Parcelable {
    @JsonProperty("day")
    protected String mDay;
    @JsonProperty("month")
    protected String mMonth;
    @JsonProperty("year")
    protected String mYear;

    protected GenListingRegistrationDateAnswer(String month, String day, String year) {
        this();
        this.mMonth = month;
        this.mDay = day;
        this.mYear = year;
    }

    protected GenListingRegistrationDateAnswer() {
    }

    public String getMonth() {
        return this.mMonth;
    }

    @JsonProperty("month")
    public void setMonth(String value) {
        this.mMonth = value;
    }

    public String getDay() {
        return this.mDay;
    }

    @JsonProperty("day")
    public void setDay(String value) {
        this.mDay = value;
    }

    public String getYear() {
        return this.mYear;
    }

    @JsonProperty("year")
    public void setYear(String value) {
        this.mYear = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mMonth);
        parcel.writeString(this.mDay);
        parcel.writeString(this.mYear);
    }

    public void readFromParcel(Parcel source) {
        this.mMonth = source.readString();
        this.mDay = source.readString();
        this.mYear = source.readString();
    }
}

package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSupportPhoneNumber implements Parcelable {
    @JsonProperty("localized_country")
    protected String mCountry;
    @JsonProperty("number")
    protected String mNumber;

    protected GenSupportPhoneNumber(String country, String number) {
        this();
        this.mCountry = country;
        this.mNumber = number;
    }

    protected GenSupportPhoneNumber() {
    }

    public String getCountry() {
        return this.mCountry;
    }

    @JsonProperty("localized_country")
    public void setCountry(String value) {
        this.mCountry = value;
    }

    public String getNumber() {
        return this.mNumber;
    }

    @JsonProperty("number")
    public void setNumber(String value) {
        this.mNumber = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mCountry);
        parcel.writeString(this.mNumber);
    }

    public void readFromParcel(Parcel source) {
        this.mCountry = source.readString();
        this.mNumber = source.readString();
    }
}

package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenLocation implements Parcelable {
    @JsonProperty("country_code")
    protected String mCountryCode;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("state")
    protected String mState;

    protected GenLocation(String state, String name, String countryCode) {
        this();
        this.mState = state;
        this.mName = name;
        this.mCountryCode = countryCode;
    }

    protected GenLocation() {
    }

    public String getState() {
        return this.mState;
    }

    @JsonProperty("state")
    public void setState(String value) {
        this.mState = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    @JsonProperty("country_code")
    public void setCountryCode(String value) {
        this.mCountryCode = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mState);
        parcel.writeString(this.mName);
        parcel.writeString(this.mCountryCode);
    }

    public void readFromParcel(Parcel source) {
        this.mState = source.readString();
        this.mName = source.readString();
        this.mCountryCode = source.readString();
    }
}

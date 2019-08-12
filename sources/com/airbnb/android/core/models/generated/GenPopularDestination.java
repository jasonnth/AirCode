package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPopularDestination implements Parcelable {
    @JsonProperty("locale")
    protected String mLocale;
    @JsonProperty("region")
    protected String mRegion;

    protected GenPopularDestination(String locale, String region) {
        this();
        this.mLocale = locale;
        this.mRegion = region;
    }

    protected GenPopularDestination() {
    }

    public String getLocale() {
        return this.mLocale;
    }

    @JsonProperty("locale")
    public void setLocale(String value) {
        this.mLocale = value;
    }

    public String getRegion() {
        return this.mRegion;
    }

    @JsonProperty("region")
    public void setRegion(String value) {
        this.mRegion = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mLocale);
        parcel.writeString(this.mRegion);
    }

    public void readFromParcel(Parcel source) {
        this.mLocale = source.readString();
        this.mRegion = source.readString();
    }
}

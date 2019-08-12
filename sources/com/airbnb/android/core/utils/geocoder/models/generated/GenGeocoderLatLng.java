package com.airbnb.android.core.utils.geocoder.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGeocoderLatLng implements Parcelable {
    @JsonProperty("lat")
    protected double mLat;
    @JsonProperty("lng")
    protected double mLng;

    protected GenGeocoderLatLng(double lat, double lng) {
        this();
        this.mLat = lat;
        this.mLng = lng;
    }

    protected GenGeocoderLatLng() {
    }

    public double getLat() {
        return this.mLat;
    }

    @JsonProperty("lat")
    public void setLat(double value) {
        this.mLat = value;
    }

    public double getLng() {
        return this.mLng;
    }

    @JsonProperty("lng")
    public void setLng(double value) {
        this.mLng = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeDouble(this.mLat);
        parcel.writeDouble(this.mLng);
    }

    public void readFromParcel(Parcel source) {
        this.mLat = source.readDouble();
        this.mLng = source.readDouble();
    }
}

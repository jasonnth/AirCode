package com.airbnb.android.core.utils.geocoder.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.utils.geocoder.models.GeocoderLatLng;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGeocoderViewport implements Parcelable {
    @JsonProperty("northeast")
    protected GeocoderLatLng mNortheast;
    @JsonProperty("southwest")
    protected GeocoderLatLng mSouthwest;

    protected GenGeocoderViewport(GeocoderLatLng northeast, GeocoderLatLng southwest) {
        this();
        this.mNortheast = northeast;
        this.mSouthwest = southwest;
    }

    protected GenGeocoderViewport() {
    }

    public GeocoderLatLng getNortheast() {
        return this.mNortheast;
    }

    @JsonProperty("northeast")
    public void setNortheast(GeocoderLatLng value) {
        this.mNortheast = value;
    }

    public GeocoderLatLng getSouthwest() {
        return this.mSouthwest;
    }

    @JsonProperty("southwest")
    public void setSouthwest(GeocoderLatLng value) {
        this.mSouthwest = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mNortheast, 0);
        parcel.writeParcelable(this.mSouthwest, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mNortheast = (GeocoderLatLng) source.readParcelable(GeocoderLatLng.class.getClassLoader());
        this.mSouthwest = (GeocoderLatLng) source.readParcelable(GeocoderLatLng.class.getClassLoader());
    }
}

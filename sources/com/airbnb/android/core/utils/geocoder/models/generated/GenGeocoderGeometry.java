package com.airbnb.android.core.utils.geocoder.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.utils.geocoder.models.GeocoderLatLng;
import com.airbnb.android.core.utils.geocoder.models.GeocoderViewport;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGeocoderGeometry implements Parcelable {
    @JsonProperty("location")
    protected GeocoderLatLng mLocation;
    @JsonProperty("location_type")
    protected String mLocationType;
    @JsonProperty("viewport")
    protected GeocoderViewport mViewport;

    protected GenGeocoderGeometry(GeocoderLatLng location, GeocoderViewport viewport, String locationType) {
        this();
        this.mLocation = location;
        this.mViewport = viewport;
        this.mLocationType = locationType;
    }

    protected GenGeocoderGeometry() {
    }

    public GeocoderLatLng getLocation() {
        return this.mLocation;
    }

    @JsonProperty("location")
    public void setLocation(GeocoderLatLng value) {
        this.mLocation = value;
    }

    public GeocoderViewport getViewport() {
        return this.mViewport;
    }

    @JsonProperty("viewport")
    public void setViewport(GeocoderViewport value) {
        this.mViewport = value;
    }

    public String getLocationType() {
        return this.mLocationType;
    }

    @JsonProperty("location_type")
    public void setLocationType(String value) {
        this.mLocationType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mLocation, 0);
        parcel.writeParcelable(this.mViewport, 0);
        parcel.writeString(this.mLocationType);
    }

    public void readFromParcel(Parcel source) {
        this.mLocation = (GeocoderLatLng) source.readParcelable(GeocoderLatLng.class.getClassLoader());
        this.mViewport = (GeocoderViewport) source.readParcelable(GeocoderViewport.class.getClassLoader());
        this.mLocationType = source.readString();
    }
}

package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSearchGeography implements Parcelable {
    @JsonProperty("city")
    protected String mCity;
    @JsonProperty("country")
    protected String mCountry;
    @JsonProperty("country_code")
    protected String mCountryCode;
    @JsonProperty("lat")
    protected double mLat;
    @JsonProperty("lng")
    protected double mLng;
    @JsonProperty("place_id")
    protected String mPlaceId;
    @JsonProperty("precision")
    protected String mPrecision;
    @JsonProperty("state")
    protected String mState;

    protected GenSearchGeography(String countryCode, String precision, String city, String state, String country, String placeId, double lat, double lng) {
        this();
        this.mCountryCode = countryCode;
        this.mPrecision = precision;
        this.mCity = city;
        this.mState = state;
        this.mCountry = country;
        this.mPlaceId = placeId;
        this.mLat = lat;
        this.mLng = lng;
    }

    protected GenSearchGeography() {
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    @JsonProperty("country_code")
    public void setCountryCode(String value) {
        this.mCountryCode = value;
    }

    public String getPrecision() {
        return this.mPrecision;
    }

    @JsonProperty("precision")
    public void setPrecision(String value) {
        this.mPrecision = value;
    }

    public String getCity() {
        return this.mCity;
    }

    @JsonProperty("city")
    public void setCity(String value) {
        this.mCity = value;
    }

    public String getState() {
        return this.mState;
    }

    @JsonProperty("state")
    public void setState(String value) {
        this.mState = value;
    }

    public String getCountry() {
        return this.mCountry;
    }

    @JsonProperty("country")
    public void setCountry(String value) {
        this.mCountry = value;
    }

    public String getPlaceId() {
        return this.mPlaceId;
    }

    @JsonProperty("place_id")
    public void setPlaceId(String value) {
        this.mPlaceId = value;
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
        parcel.writeString(this.mCountryCode);
        parcel.writeString(this.mPrecision);
        parcel.writeString(this.mCity);
        parcel.writeString(this.mState);
        parcel.writeString(this.mCountry);
        parcel.writeString(this.mPlaceId);
        parcel.writeDouble(this.mLat);
        parcel.writeDouble(this.mLng);
    }

    public void readFromParcel(Parcel source) {
        this.mCountryCode = source.readString();
        this.mPrecision = source.readString();
        this.mCity = source.readString();
        this.mState = source.readString();
        this.mCountry = source.readString();
        this.mPlaceId = source.readString();
        this.mLat = source.readDouble();
        this.mLng = source.readDouble();
    }
}

package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenNightCount implements Parcelable {
    @JsonProperty("regulatory_body_display")
    protected String mCityName;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("max_nights")
    protected int mMaxNights;
    @JsonProperty("nights_booked")
    protected int mNightsBooked;

    protected GenNightCount(String cityName, int maxNights, int nightsBooked, long listingId) {
        this();
        this.mCityName = cityName;
        this.mMaxNights = maxNights;
        this.mNightsBooked = nightsBooked;
        this.mListingId = listingId;
    }

    protected GenNightCount() {
    }

    public String getCityName() {
        return this.mCityName;
    }

    @JsonProperty("regulatory_body_display")
    public void setCityName(String value) {
        this.mCityName = value;
    }

    public int getMaxNights() {
        return this.mMaxNights;
    }

    @JsonProperty("max_nights")
    public void setMaxNights(int value) {
        this.mMaxNights = value;
    }

    public int getNightsBooked() {
        return this.mNightsBooked;
    }

    @JsonProperty("nights_booked")
    public void setNightsBooked(int value) {
        this.mNightsBooked = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("listing_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mCityName);
        parcel.writeInt(this.mMaxNights);
        parcel.writeInt(this.mNightsBooked);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mCityName = source.readString();
        this.mMaxNights = source.readInt();
        this.mNightsBooked = source.readInt();
        this.mListingId = source.readLong();
    }
}

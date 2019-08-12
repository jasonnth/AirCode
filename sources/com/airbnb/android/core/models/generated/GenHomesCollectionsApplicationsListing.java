package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHomesCollectionsApplicationsListing implements Parcelable {
    @JsonProperty("city")
    protected String mCity;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("state")
    protected String mState;
    @JsonProperty("thumbnail_url")
    protected String mThumbnailUrl;

    protected GenHomesCollectionsApplicationsListing(String city, String name, String state, String thumbnailUrl, long id) {
        this();
        this.mCity = city;
        this.mName = name;
        this.mState = state;
        this.mThumbnailUrl = thumbnailUrl;
        this.mId = id;
    }

    protected GenHomesCollectionsApplicationsListing() {
    }

    public String getCity() {
        return this.mCity;
    }

    @JsonProperty("city")
    public void setCity(String value) {
        this.mCity = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getState() {
        return this.mState;
    }

    @JsonProperty("state")
    public void setState(String value) {
        this.mState = value;
    }

    public String getThumbnailUrl() {
        return this.mThumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(String value) {
        this.mThumbnailUrl = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mCity);
        parcel.writeString(this.mName);
        parcel.writeString(this.mState);
        parcel.writeString(this.mThumbnailUrl);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mCity = source.readString();
        this.mName = source.readString();
        this.mState = source.readString();
        this.mThumbnailUrl = source.readString();
        this.mId = source.readLong();
    }
}

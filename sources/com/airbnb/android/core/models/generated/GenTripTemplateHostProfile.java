package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.TripTemplateHost;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenTripTemplateHostProfile implements Parcelable {
    @JsonProperty("bio")
    protected String mBio;
    @JsonProperty("bio_photo_url")
    protected String mBioPictureUrl;
    @JsonProperty("host")
    protected TripTemplateHost mHost;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("instagram")
    protected String mInstagram;

    protected GenTripTemplateHostProfile(String bio, String bioPictureUrl, String instagram, TripTemplateHost host, long id) {
        this();
        this.mBio = bio;
        this.mBioPictureUrl = bioPictureUrl;
        this.mInstagram = instagram;
        this.mHost = host;
        this.mId = id;
    }

    protected GenTripTemplateHostProfile() {
    }

    public String getBio() {
        return this.mBio;
    }

    @JsonProperty("bio")
    public void setBio(String value) {
        this.mBio = value;
    }

    public String getBioPictureUrl() {
        return this.mBioPictureUrl;
    }

    @JsonProperty("bio_photo_url")
    public void setBioPictureUrl(String value) {
        this.mBioPictureUrl = value;
    }

    public String getInstagram() {
        return this.mInstagram;
    }

    @JsonProperty("instagram")
    public void setInstagram(String value) {
        this.mInstagram = value;
    }

    public TripTemplateHost getHost() {
        return this.mHost;
    }

    @JsonProperty("host")
    public void setHost(TripTemplateHost value) {
        this.mHost = value;
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
        parcel.writeString(this.mBio);
        parcel.writeString(this.mBioPictureUrl);
        parcel.writeString(this.mInstagram);
        parcel.writeParcelable(this.mHost, 0);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mBio = source.readString();
        this.mBioPictureUrl = source.readString();
        this.mInstagram = source.readString();
        this.mHost = (TripTemplateHost) source.readParcelable(TripTemplateHost.class.getClassLoader());
        this.mId = source.readLong();
    }
}

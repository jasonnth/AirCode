package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Photo;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenDestination implements Parcelable {
    @JsonProperty("display_name")
    protected String mDisplayName;
    @JsonProperty("picture")
    protected Photo mPicture;
    @JsonProperty("query_name")
    protected String mQueryName;

    protected GenDestination(Photo picture, String displayName, String queryName) {
        this();
        this.mPicture = picture;
        this.mDisplayName = displayName;
        this.mQueryName = queryName;
    }

    protected GenDestination() {
    }

    public Photo getPicture() {
        return this.mPicture;
    }

    @JsonProperty("picture")
    public void setPicture(Photo value) {
        this.mPicture = value;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    @JsonProperty("display_name")
    public void setDisplayName(String value) {
        this.mDisplayName = value;
    }

    public String getQueryName() {
        return this.mQueryName;
    }

    @JsonProperty("query_name")
    public void setQueryName(String value) {
        this.mQueryName = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mPicture, 0);
        parcel.writeString(this.mDisplayName);
        parcel.writeString(this.mQueryName);
    }

    public void readFromParcel(Parcel source) {
        this.mPicture = (Photo) source.readParcelable(Photo.class.getClassLoader());
        this.mDisplayName = source.readString();
        this.mQueryName = source.readString();
    }
}

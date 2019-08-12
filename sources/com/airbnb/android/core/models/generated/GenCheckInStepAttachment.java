package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCheckInStepAttachment implements Parcelable {
    @JsonProperty("picture_url")
    protected String mPictureUrl;
    @JsonProperty("type")
    protected String mType;

    protected GenCheckInStepAttachment(String type, String pictureUrl) {
        this();
        this.mType = type;
        this.mPictureUrl = pictureUrl;
    }

    protected GenCheckInStepAttachment() {
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public String getPictureUrl() {
        return this.mPictureUrl;
    }

    @JsonProperty("picture_url")
    public void setPictureUrl(String value) {
        this.mPictureUrl = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mType);
        parcel.writeString(this.mPictureUrl);
    }

    public void readFromParcel(Parcel source) {
        this.mType = source.readString();
        this.mPictureUrl = source.readString();
    }
}

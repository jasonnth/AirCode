package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenStoryProductLinkDetails implements Parcelable {
    @JsonProperty("header")
    protected String mHeader;
    @JsonProperty("object_id")
    protected long mObjectId;
    @JsonProperty("object_type")
    protected String mObjectTypeString;
    @JsonProperty("picture_url")
    protected String mPictureUrl;
    @JsonProperty("rating")
    protected float mRating;
    @JsonProperty("subtitle")
    protected String mSubtitle;
    @JsonProperty("title")
    protected String mTitle;

    protected GenStoryProductLinkDetails(String header, String pictureUrl, String subtitle, String title, String objectTypeString, float rating, long objectId) {
        this();
        this.mHeader = header;
        this.mPictureUrl = pictureUrl;
        this.mSubtitle = subtitle;
        this.mTitle = title;
        this.mObjectTypeString = objectTypeString;
        this.mRating = rating;
        this.mObjectId = objectId;
    }

    protected GenStoryProductLinkDetails() {
    }

    public String getHeader() {
        return this.mHeader;
    }

    @JsonProperty("header")
    public void setHeader(String value) {
        this.mHeader = value;
    }

    public String getPictureUrl() {
        return this.mPictureUrl;
    }

    @JsonProperty("picture_url")
    public void setPictureUrl(String value) {
        this.mPictureUrl = value;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(String value) {
        this.mSubtitle = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getObjectTypeString() {
        return this.mObjectTypeString;
    }

    @JsonProperty("object_type")
    public void setObjectTypeString(String value) {
        this.mObjectTypeString = value;
    }

    public float getRating() {
        return this.mRating;
    }

    @JsonProperty("rating")
    public void setRating(float value) {
        this.mRating = value;
    }

    public long getObjectId() {
        return this.mObjectId;
    }

    @JsonProperty("object_id")
    public void setObjectId(long value) {
        this.mObjectId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mHeader);
        parcel.writeString(this.mPictureUrl);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mObjectTypeString);
        parcel.writeFloat(this.mRating);
        parcel.writeLong(this.mObjectId);
    }

    public void readFromParcel(Parcel source) {
        this.mHeader = source.readString();
        this.mPictureUrl = source.readString();
        this.mSubtitle = source.readString();
        this.mTitle = source.readString();
        this.mObjectTypeString = source.readString();
        this.mRating = source.readFloat();
        this.mObjectId = source.readLong();
    }
}

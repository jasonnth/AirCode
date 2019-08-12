package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSelectRoomMedia implements Parcelable {
    @JsonProperty("caption")
    protected String mCaption;
    @JsonProperty("cover")
    protected String mCover;
    @JsonProperty("id")
    protected String mId;
    @JsonProperty("original_url")
    protected String mOriginalUrl;
    @JsonProperty("shot_type")
    protected String mShotType;

    protected GenSelectRoomMedia(String caption, String cover, String id, String shotType, String originalUrl) {
        this();
        this.mCaption = caption;
        this.mCover = cover;
        this.mId = id;
        this.mShotType = shotType;
        this.mOriginalUrl = originalUrl;
    }

    protected GenSelectRoomMedia() {
    }

    public String getCaption() {
        return this.mCaption;
    }

    @JsonProperty("caption")
    public void setCaption(String value) {
        this.mCaption = value;
    }

    public String getCover() {
        return this.mCover;
    }

    @JsonProperty("cover")
    public void setCover(String value) {
        this.mCover = value;
    }

    public String getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(String value) {
        this.mId = value;
    }

    public String getShotType() {
        return this.mShotType;
    }

    @JsonProperty("shot_type")
    public void setShotType(String value) {
        this.mShotType = value;
    }

    public String getOriginalUrl() {
        return this.mOriginalUrl;
    }

    @JsonProperty("original_url")
    public void setOriginalUrl(String value) {
        this.mOriginalUrl = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mCaption);
        parcel.writeString(this.mCover);
        parcel.writeString(this.mId);
        parcel.writeString(this.mShotType);
        parcel.writeString(this.mOriginalUrl);
    }

    public void readFromParcel(Parcel source) {
        this.mCaption = source.readString();
        this.mCover = source.readString();
        this.mId = source.readString();
        this.mShotType = source.readString();
        this.mOriginalUrl = source.readString();
    }
}

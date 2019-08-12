package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenStoryPhotoResponse implements Parcelable {
    @JsonProperty("image_name")
    protected String mImageName;
    @JsonProperty("picture_id")
    protected String mPictureId;
    @JsonProperty("story_id")
    protected String mStoryId;

    protected GenStoryPhotoResponse(String imageName, String pictureId, String storyId) {
        this();
        this.mImageName = imageName;
        this.mPictureId = pictureId;
        this.mStoryId = storyId;
    }

    protected GenStoryPhotoResponse() {
    }

    public String getImageName() {
        return this.mImageName;
    }

    @JsonProperty("image_name")
    public void setImageName(String value) {
        this.mImageName = value;
    }

    public String getPictureId() {
        return this.mPictureId;
    }

    @JsonProperty("picture_id")
    public void setPictureId(String value) {
        this.mPictureId = value;
    }

    public String getStoryId() {
        return this.mStoryId;
    }

    @JsonProperty("story_id")
    public void setStoryId(String value) {
        this.mStoryId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mImageName);
        parcel.writeString(this.mPictureId);
        parcel.writeString(this.mStoryId);
    }

    public void readFromParcel(Parcel source) {
        this.mImageName = source.readString();
        this.mPictureId = source.readString();
        this.mStoryId = source.readString();
    }
}

package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenArticleBodyElement implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("image_name")
    protected String mImageIdentifier;
    @JsonProperty("preview_encoded_png")
    protected String mImagePreview;
    @JsonProperty("image_url_p")
    protected String mImagePreviewPortrait;
    @JsonProperty("image_ratio")
    protected double mImageRatio;
    @JsonProperty("image_url")
    protected String mImageUrl;
    @JsonProperty("image_url_portrait")
    protected String mImageUrlPortrait;
    @JsonProperty("xxl_image_url")
    protected String mImageUrlXXL;
    @JsonProperty("text")
    protected String mText;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("type")
    protected String mTypeString;
    @JsonProperty("cover_photo_url")
    protected String mVideoPreviewImageUrl;
    @JsonProperty("mp4_url")
    protected String mVideoUrl;

    protected GenArticleBodyElement(String title, String text, String imageUrl, String imagePreview, String imageUrlPortrait, String imagePreviewPortrait, String imageUrlXXL, String typeString, String imageIdentifier, String videoUrl, String videoPreviewImageUrl, double imageRatio, long id) {
        this();
        this.mTitle = title;
        this.mText = text;
        this.mImageUrl = imageUrl;
        this.mImagePreview = imagePreview;
        this.mImageUrlPortrait = imageUrlPortrait;
        this.mImagePreviewPortrait = imagePreviewPortrait;
        this.mImageUrlXXL = imageUrlXXL;
        this.mTypeString = typeString;
        this.mImageIdentifier = imageIdentifier;
        this.mVideoUrl = videoUrl;
        this.mVideoPreviewImageUrl = videoPreviewImageUrl;
        this.mImageRatio = imageRatio;
        this.mId = id;
    }

    protected GenArticleBodyElement() {
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getText() {
        return this.mText;
    }

    @JsonProperty("text")
    public void setText(String value) {
        this.mText = value;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String value) {
        this.mImageUrl = value;
    }

    public String getImagePreview() {
        return this.mImagePreview;
    }

    @JsonProperty("preview_encoded_png")
    public void setImagePreview(String value) {
        this.mImagePreview = value;
    }

    public String getImageUrlPortrait() {
        return this.mImageUrlPortrait;
    }

    @JsonProperty("image_url_portrait")
    public void setImageUrlPortrait(String value) {
        this.mImageUrlPortrait = value;
    }

    public String getImagePreviewPortrait() {
        return this.mImagePreviewPortrait;
    }

    @JsonProperty("image_url_p")
    public void setImagePreviewPortrait(String value) {
        this.mImagePreviewPortrait = value;
    }

    public String getImageUrlXXL() {
        return this.mImageUrlXXL;
    }

    @JsonProperty("xxl_image_url")
    public void setImageUrlXXL(String value) {
        this.mImageUrlXXL = value;
    }

    public String getTypeString() {
        return this.mTypeString;
    }

    @JsonProperty("type")
    public void setTypeString(String value) {
        this.mTypeString = value;
    }

    public String getImageIdentifier() {
        return this.mImageIdentifier;
    }

    @JsonProperty("image_name")
    public void setImageIdentifier(String value) {
        this.mImageIdentifier = value;
    }

    public String getVideoUrl() {
        return this.mVideoUrl;
    }

    @JsonProperty("mp4_url")
    public void setVideoUrl(String value) {
        this.mVideoUrl = value;
    }

    public String getVideoPreviewImageUrl() {
        return this.mVideoPreviewImageUrl;
    }

    @JsonProperty("cover_photo_url")
    public void setVideoPreviewImageUrl(String value) {
        this.mVideoPreviewImageUrl = value;
    }

    public double getImageRatio() {
        return this.mImageRatio;
    }

    @JsonProperty("image_ratio")
    public void setImageRatio(double value) {
        this.mImageRatio = value;
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
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mText);
        parcel.writeString(this.mImageUrl);
        parcel.writeString(this.mImagePreview);
        parcel.writeString(this.mImageUrlPortrait);
        parcel.writeString(this.mImagePreviewPortrait);
        parcel.writeString(this.mImageUrlXXL);
        parcel.writeString(this.mTypeString);
        parcel.writeString(this.mImageIdentifier);
        parcel.writeString(this.mVideoUrl);
        parcel.writeString(this.mVideoPreviewImageUrl);
        parcel.writeDouble(this.mImageRatio);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mTitle = source.readString();
        this.mText = source.readString();
        this.mImageUrl = source.readString();
        this.mImagePreview = source.readString();
        this.mImageUrlPortrait = source.readString();
        this.mImagePreviewPortrait = source.readString();
        this.mImageUrlXXL = source.readString();
        this.mTypeString = source.readString();
        this.mImageIdentifier = source.readString();
        this.mVideoUrl = source.readString();
        this.mVideoPreviewImageUrl = source.readString();
        this.mImageRatio = source.readDouble();
        this.mId = source.readLong();
    }
}

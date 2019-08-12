package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenStoryImageDetails implements Parcelable {
    @JsonProperty("image_name")
    protected String mImageIdentifier;
    @JsonProperty("preview_encoded_png")
    protected String mImagePreview;
    @JsonProperty("image_ratio")
    protected double mImageRatio;
    @JsonProperty("image_url")
    protected String mImageUrl;

    protected GenStoryImageDetails(String imagePreview, String imageIdentifier, String imageUrl, double imageRatio) {
        this();
        this.mImagePreview = imagePreview;
        this.mImageIdentifier = imageIdentifier;
        this.mImageUrl = imageUrl;
        this.mImageRatio = imageRatio;
    }

    protected GenStoryImageDetails() {
    }

    public String getImagePreview() {
        return this.mImagePreview;
    }

    @JsonProperty("preview_encoded_png")
    public void setImagePreview(String value) {
        this.mImagePreview = value;
    }

    public String getImageIdentifier() {
        return this.mImageIdentifier;
    }

    @JsonProperty("image_name")
    public void setImageIdentifier(String value) {
        this.mImageIdentifier = value;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String value) {
        this.mImageUrl = value;
    }

    public double getImageRatio() {
        return this.mImageRatio;
    }

    @JsonProperty("image_ratio")
    public void setImageRatio(double value) {
        this.mImageRatio = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mImagePreview);
        parcel.writeString(this.mImageIdentifier);
        parcel.writeString(this.mImageUrl);
        parcel.writeDouble(this.mImageRatio);
    }

    public void readFromParcel(Parcel source) {
        this.mImagePreview = source.readString();
        this.mImageIdentifier = source.readString();
        this.mImageUrl = source.readString();
        this.mImageRatio = source.readDouble();
    }
}

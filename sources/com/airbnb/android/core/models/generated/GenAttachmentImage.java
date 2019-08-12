package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAttachmentImage implements Parcelable {
    @JsonProperty("file_size")
    protected int mFileSize;
    @JsonProperty("height")
    protected int mHeightPx;
    @JsonProperty("size")
    protected String mSizeType;
    @JsonProperty("url")
    protected String mUrl;
    @JsonProperty("width")
    protected int mWidthPx;

    protected GenAttachmentImage(String url, String sizeType, int widthPx, int heightPx, int fileSize) {
        this();
        this.mUrl = url;
        this.mSizeType = sizeType;
        this.mWidthPx = widthPx;
        this.mHeightPx = heightPx;
        this.mFileSize = fileSize;
    }

    protected GenAttachmentImage() {
    }

    public String getUrl() {
        return this.mUrl;
    }

    @JsonProperty("url")
    public void setUrl(String value) {
        this.mUrl = value;
    }

    public String getSizeType() {
        return this.mSizeType;
    }

    @JsonProperty("size")
    public void setSizeType(String value) {
        this.mSizeType = value;
    }

    public int getWidthPx() {
        return this.mWidthPx;
    }

    @JsonProperty("width")
    public void setWidthPx(int value) {
        this.mWidthPx = value;
    }

    public int getHeightPx() {
        return this.mHeightPx;
    }

    @JsonProperty("height")
    public void setHeightPx(int value) {
        this.mHeightPx = value;
    }

    public int getFileSize() {
        return this.mFileSize;
    }

    @JsonProperty("file_size")
    public void setFileSize(int value) {
        this.mFileSize = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mSizeType);
        parcel.writeInt(this.mWidthPx);
        parcel.writeInt(this.mHeightPx);
        parcel.writeInt(this.mFileSize);
    }

    public void readFromParcel(Parcel source) {
        this.mUrl = source.readString();
        this.mSizeType = source.readString();
        this.mWidthPx = source.readInt();
        this.mHeightPx = source.readInt();
        this.mFileSize = source.readInt();
    }
}

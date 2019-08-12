package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingRegistrationFileAnswer implements Parcelable {
    @JsonProperty("content")
    protected String mContent;
    @JsonProperty("file_name")
    protected String mFileName;
    @JsonProperty("file_type")
    protected String mFileType;
    @JsonProperty("url")
    protected String mUrl;
    @JsonProperty("value")
    protected String mValue;

    protected GenListingRegistrationFileAnswer(String content, String fileName, String fileType, String url, String value) {
        this();
        this.mContent = content;
        this.mFileName = fileName;
        this.mFileType = fileType;
        this.mUrl = url;
        this.mValue = value;
    }

    protected GenListingRegistrationFileAnswer() {
    }

    public String getContent() {
        return this.mContent;
    }

    @JsonProperty("content")
    public void setContent(String value) {
        this.mContent = value;
    }

    public String getFileName() {
        return this.mFileName;
    }

    @JsonProperty("file_name")
    public void setFileName(String value) {
        this.mFileName = value;
    }

    public String getFileType() {
        return this.mFileType;
    }

    @JsonProperty("file_type")
    public void setFileType(String value) {
        this.mFileType = value;
    }

    public String getUrl() {
        return this.mUrl;
    }

    @JsonProperty("url")
    public void setUrl(String value) {
        this.mUrl = value;
    }

    public String getValue() {
        return this.mValue;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mContent);
        parcel.writeString(this.mFileName);
        parcel.writeString(this.mFileType);
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mValue);
    }

    public void readFromParcel(Parcel source) {
        this.mContent = source.readString();
        this.mFileName = source.readString();
        this.mFileType = source.readString();
        this.mUrl = source.readString();
        this.mValue = source.readString();
    }
}

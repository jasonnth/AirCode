package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGuidebook implements Parcelable {
    @JsonProperty("guidebook_url")
    protected String mGuidebookPath;
    @JsonProperty("header_image_url")
    protected String mHeaderImageUrl;
    @JsonProperty("photo_url")
    protected String mPhotoUrl;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("xl_photo_url")
    protected String mXlPhotoUrl;

    protected GenGuidebook(String title, String photoUrl, String xlPhotoUrl, String guidebookPath, String headerImageUrl) {
        this();
        this.mTitle = title;
        this.mPhotoUrl = photoUrl;
        this.mXlPhotoUrl = xlPhotoUrl;
        this.mGuidebookPath = guidebookPath;
        this.mHeaderImageUrl = headerImageUrl;
    }

    protected GenGuidebook() {
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getPhotoUrl() {
        return this.mPhotoUrl;
    }

    @JsonProperty("photo_url")
    public void setPhotoUrl(String value) {
        this.mPhotoUrl = value;
    }

    public String getXlPhotoUrl() {
        return this.mXlPhotoUrl;
    }

    @JsonProperty("xl_photo_url")
    public void setXlPhotoUrl(String value) {
        this.mXlPhotoUrl = value;
    }

    public String getGuidebookPath() {
        return this.mGuidebookPath;
    }

    @JsonProperty("guidebook_url")
    public void setGuidebookPath(String value) {
        this.mGuidebookPath = value;
    }

    public String getHeaderImageUrl() {
        return this.mHeaderImageUrl;
    }

    @JsonProperty("header_image_url")
    public void setHeaderImageUrl(String value) {
        this.mHeaderImageUrl = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mPhotoUrl);
        parcel.writeString(this.mXlPhotoUrl);
        parcel.writeString(this.mGuidebookPath);
        parcel.writeString(this.mHeaderImageUrl);
    }

    public void readFromParcel(Parcel source) {
        this.mTitle = source.readString();
        this.mPhotoUrl = source.readString();
        this.mXlPhotoUrl = source.readString();
        this.mGuidebookPath = source.readString();
        this.mHeaderImageUrl = source.readString();
    }
}

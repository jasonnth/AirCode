package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReservationAlert implements Parcelable {
    @JsonProperty("body_text")
    protected String mBody;
    @JsonProperty("link_url")
    protected String mLinkPath;
    @JsonProperty("link_text")
    protected String mLinkText;
    @JsonProperty("title_text")
    protected String mTitle;
    @JsonProperty("alert_type")
    protected String mType;

    protected GenReservationAlert(String type, String body, String linkText, String linkPath, String title) {
        this();
        this.mType = type;
        this.mBody = body;
        this.mLinkText = linkText;
        this.mLinkPath = linkPath;
        this.mTitle = title;
    }

    protected GenReservationAlert() {
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("alert_type")
    public void setType(String value) {
        this.mType = value;
    }

    public String getBody() {
        return this.mBody;
    }

    @JsonProperty("body_text")
    public void setBody(String value) {
        this.mBody = value;
    }

    public String getLinkText() {
        return this.mLinkText;
    }

    @JsonProperty("link_text")
    public void setLinkText(String value) {
        this.mLinkText = value;
    }

    public String getLinkPath() {
        return this.mLinkPath;
    }

    @JsonProperty("link_url")
    public void setLinkPath(String value) {
        this.mLinkPath = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title_text")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mType);
        parcel.writeString(this.mBody);
        parcel.writeString(this.mLinkText);
        parcel.writeString(this.mLinkPath);
        parcel.writeString(this.mTitle);
    }

    public void readFromParcel(Parcel source) {
        this.mType = source.readString();
        this.mBody = source.readString();
        this.mLinkText = source.readString();
        this.mLinkPath = source.readString();
        this.mTitle = source.readString();
    }
}

package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingRegistrationSubmissionData implements Parcelable {
    @JsonProperty("key")
    protected String mKey;
    @JsonProperty("label")
    protected String mLabel;
    @JsonProperty("link")
    protected String mLink;
    @JsonProperty("link_text")
    protected String mLinkText;
    @JsonProperty("value")
    protected String mValue;

    protected GenListingRegistrationSubmissionData(String label, String value, String key, String link, String linkText) {
        this();
        this.mLabel = label;
        this.mValue = value;
        this.mKey = key;
        this.mLink = link;
        this.mLinkText = linkText;
    }

    protected GenListingRegistrationSubmissionData() {
    }

    public String getLabel() {
        return this.mLabel;
    }

    @JsonProperty("label")
    public void setLabel(String value) {
        this.mLabel = value;
    }

    public String getValue() {
        return this.mValue;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.mValue = value;
    }

    public String getKey() {
        return this.mKey;
    }

    @JsonProperty("key")
    public void setKey(String value) {
        this.mKey = value;
    }

    public String getLink() {
        return this.mLink;
    }

    @JsonProperty("link")
    public void setLink(String value) {
        this.mLink = value;
    }

    public String getLinkText() {
        return this.mLinkText;
    }

    @JsonProperty("link_text")
    public void setLinkText(String value) {
        this.mLinkText = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mLabel);
        parcel.writeString(this.mValue);
        parcel.writeString(this.mKey);
        parcel.writeString(this.mLink);
        parcel.writeString(this.mLinkText);
    }

    public void readFromParcel(Parcel source) {
        this.mLabel = source.readString();
        this.mValue = source.readString();
        this.mKey = source.readString();
        this.mLink = source.readString();
        this.mLinkText = source.readString();
    }
}

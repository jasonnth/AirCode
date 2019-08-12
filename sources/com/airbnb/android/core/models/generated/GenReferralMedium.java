package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReferralMedium implements Parcelable {
    @JsonProperty("link")
    protected String mLink;
    @JsonProperty("localized_message")
    protected String mLocalizedMessage;
    @JsonProperty("localized_title")
    protected String mLocalizedTitle;
    @JsonProperty("name")
    protected String mName;

    protected GenReferralMedium(String link, String localizedMessage, String localizedTitle, String name) {
        this();
        this.mLink = link;
        this.mLocalizedMessage = localizedMessage;
        this.mLocalizedTitle = localizedTitle;
        this.mName = name;
    }

    protected GenReferralMedium() {
    }

    public String getLink() {
        return this.mLink;
    }

    @JsonProperty("link")
    public void setLink(String value) {
        this.mLink = value;
    }

    public String getLocalizedMessage() {
        return this.mLocalizedMessage;
    }

    @JsonProperty("localized_message")
    public void setLocalizedMessage(String value) {
        this.mLocalizedMessage = value;
    }

    public String getLocalizedTitle() {
        return this.mLocalizedTitle;
    }

    @JsonProperty("localized_title")
    public void setLocalizedTitle(String value) {
        this.mLocalizedTitle = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mLink);
        parcel.writeString(this.mLocalizedMessage);
        parcel.writeString(this.mLocalizedTitle);
        parcel.writeString(this.mName);
    }

    public void readFromParcel(Parcel source) {
        this.mLink = source.readString();
        this.mLocalizedMessage = source.readString();
        this.mLocalizedTitle = source.readString();
        this.mName = source.readString();
    }
}

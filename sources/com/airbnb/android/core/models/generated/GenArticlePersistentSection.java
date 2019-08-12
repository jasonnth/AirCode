package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenArticlePersistentSection implements Parcelable {
    @JsonProperty("action_text")
    protected String mActionText;
    @JsonProperty("action_url")
    protected String mActionUrl;
    @JsonProperty("deep_link")
    protected String mDeepLink;
    @JsonProperty("object_type")
    protected String mObjectType;
    @JsonProperty("subtitle")
    protected String mSubtitle;
    @JsonProperty("title")
    protected String mTitle;

    protected GenArticlePersistentSection(String title, String subtitle, String deepLink, String actionText, String actionUrl, String objectType) {
        this();
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mDeepLink = deepLink;
        this.mActionText = actionText;
        this.mActionUrl = actionUrl;
        this.mObjectType = objectType;
    }

    protected GenArticlePersistentSection() {
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(String value) {
        this.mSubtitle = value;
    }

    public String getDeepLink() {
        return this.mDeepLink;
    }

    @JsonProperty("deep_link")
    public void setDeepLink(String value) {
        this.mDeepLink = value;
    }

    public String getActionText() {
        return this.mActionText;
    }

    @JsonProperty("action_text")
    public void setActionText(String value) {
        this.mActionText = value;
    }

    public String getActionUrl() {
        return this.mActionUrl;
    }

    @JsonProperty("action_url")
    public void setActionUrl(String value) {
        this.mActionUrl = value;
    }

    public String getObjectType() {
        return this.mObjectType;
    }

    @JsonProperty("object_type")
    public void setObjectType(String value) {
        this.mObjectType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mDeepLink);
        parcel.writeString(this.mActionText);
        parcel.writeString(this.mActionUrl);
        parcel.writeString(this.mObjectType);
    }

    public void readFromParcel(Parcel source) {
        this.mTitle = source.readString();
        this.mSubtitle = source.readString();
        this.mDeepLink = source.readString();
        this.mActionText = source.readString();
        this.mActionUrl = source.readString();
        this.mObjectType = source.readString();
    }
}

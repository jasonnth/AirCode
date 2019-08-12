package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingRegistrationHelpLink implements Parcelable {
    @JsonProperty("article_id")
    protected int mArticleId;
    @JsonProperty("link_text")
    protected String mLinkText;
    @JsonProperty("url")
    protected String mUrl;

    protected GenListingRegistrationHelpLink(String linkText, String url, int articleId) {
        this();
        this.mLinkText = linkText;
        this.mUrl = url;
        this.mArticleId = articleId;
    }

    protected GenListingRegistrationHelpLink() {
    }

    public String getLinkText() {
        return this.mLinkText;
    }

    @JsonProperty("link_text")
    public void setLinkText(String value) {
        this.mLinkText = value;
    }

    public String getUrl() {
        return this.mUrl;
    }

    @JsonProperty("url")
    public void setUrl(String value) {
        this.mUrl = value;
    }

    public int getArticleId() {
        return this.mArticleId;
    }

    @JsonProperty("article_id")
    public void setArticleId(int value) {
        this.mArticleId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mLinkText);
        parcel.writeString(this.mUrl);
        parcel.writeInt(this.mArticleId);
    }

    public void readFromParcel(Parcel source) {
        this.mLinkText = source.readString();
        this.mUrl = source.readString();
        this.mArticleId = source.readInt();
    }
}

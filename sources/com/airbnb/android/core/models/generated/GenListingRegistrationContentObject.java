package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.enums.ListingRegistrationContentStyle;
import com.airbnb.android.core.models.ListingRegistrationHelpLink;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingRegistrationContentObject implements Parcelable {
    @JsonProperty("link")
    protected ListingRegistrationHelpLink mLink;
    @JsonProperty("style")
    protected ListingRegistrationContentStyle mStyle;
    @JsonProperty("texts")
    protected List<String> mTexts;
    @JsonProperty("title")
    protected String mTitle;

    protected GenListingRegistrationContentObject(List<String> texts, ListingRegistrationContentStyle style, ListingRegistrationHelpLink link, String title) {
        this();
        this.mTexts = texts;
        this.mStyle = style;
        this.mLink = link;
        this.mTitle = title;
    }

    protected GenListingRegistrationContentObject() {
    }

    public List<String> getTexts() {
        return this.mTexts;
    }

    @JsonProperty("texts")
    public void setTexts(List<String> value) {
        this.mTexts = value;
    }

    public ListingRegistrationContentStyle getStyle() {
        return this.mStyle;
    }

    @JsonProperty("style")
    public void setStyle(ListingRegistrationContentStyle value) {
        this.mStyle = value;
    }

    public ListingRegistrationHelpLink getLink() {
        return this.mLink;
    }

    @JsonProperty("link")
    public void setLink(ListingRegistrationHelpLink value) {
        this.mLink = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringList(this.mTexts);
        parcel.writeParcelable(this.mStyle, 0);
        parcel.writeParcelable(this.mLink, 0);
        parcel.writeString(this.mTitle);
    }

    public void readFromParcel(Parcel source) {
        this.mTexts = source.createStringArrayList();
        this.mStyle = (ListingRegistrationContentStyle) source.readParcelable(ListingRegistrationContentStyle.class.getClassLoader());
        this.mLink = (ListingRegistrationHelpLink) source.readParcelable(ListingRegistrationHelpLink.class.getClassLoader());
        this.mTitle = source.readString();
    }
}

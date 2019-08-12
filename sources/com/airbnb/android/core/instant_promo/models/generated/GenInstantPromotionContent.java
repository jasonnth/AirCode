package com.airbnb.android.core.instant_promo.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenInstantPromotionContent implements Parcelable {
    @JsonProperty("caption")
    protected String mCaption;
    @JsonProperty("image_url")
    protected String mImageUrl;
    @JsonProperty("position")
    protected int mPosition;
    @JsonProperty("primary_button_text")
    protected String mPrimaryButtonText;
    @JsonProperty("primary_button_url")
    protected String mPrimaryButtonUrl;
    @JsonProperty("section_title")
    protected String mSectionTitle;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("version")
    protected int mVersion;

    protected GenInstantPromotionContent(String sectionTitle, String title, String caption, String primaryButtonText, String primaryButtonUrl, String imageUrl, int version, int position) {
        this();
        this.mSectionTitle = sectionTitle;
        this.mTitle = title;
        this.mCaption = caption;
        this.mPrimaryButtonText = primaryButtonText;
        this.mPrimaryButtonUrl = primaryButtonUrl;
        this.mImageUrl = imageUrl;
        this.mVersion = version;
        this.mPosition = position;
    }

    protected GenInstantPromotionContent() {
    }

    public String getSectionTitle() {
        return this.mSectionTitle;
    }

    @JsonProperty("section_title")
    public void setSectionTitle(String value) {
        this.mSectionTitle = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getCaption() {
        return this.mCaption;
    }

    @JsonProperty("caption")
    public void setCaption(String value) {
        this.mCaption = value;
    }

    public String getPrimaryButtonText() {
        return this.mPrimaryButtonText;
    }

    @JsonProperty("primary_button_text")
    public void setPrimaryButtonText(String value) {
        this.mPrimaryButtonText = value;
    }

    public String getPrimaryButtonUrl() {
        return this.mPrimaryButtonUrl;
    }

    @JsonProperty("primary_button_url")
    public void setPrimaryButtonUrl(String value) {
        this.mPrimaryButtonUrl = value;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String value) {
        this.mImageUrl = value;
    }

    public int getVersion() {
        return this.mVersion;
    }

    @JsonProperty("version")
    public void setVersion(int value) {
        this.mVersion = value;
    }

    public int getPosition() {
        return this.mPosition;
    }

    @JsonProperty("position")
    public void setPosition(int value) {
        this.mPosition = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mSectionTitle);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mCaption);
        parcel.writeString(this.mPrimaryButtonText);
        parcel.writeString(this.mPrimaryButtonUrl);
        parcel.writeString(this.mImageUrl);
        parcel.writeInt(this.mVersion);
        parcel.writeInt(this.mPosition);
    }

    public void readFromParcel(Parcel source) {
        this.mSectionTitle = source.readString();
        this.mTitle = source.readString();
        this.mCaption = source.readString();
        this.mPrimaryButtonText = source.readString();
        this.mPrimaryButtonUrl = source.readString();
        this.mImageUrl = source.readString();
        this.mVersion = source.readInt();
        this.mPosition = source.readInt();
    }
}

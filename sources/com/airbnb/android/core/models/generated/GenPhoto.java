package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPhoto implements Parcelable {
    @JsonProperty("caption")
    protected String mCaption;
    @JsonProperty("dominant_saturated_color")
    protected int mDominantSaturatedColor;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("large_ro")
    protected String mLargeRo;
    @JsonProperty("large")
    protected String mLargeUrl;
    @JsonProperty("medium")
    protected String mMediumUrl;
    @JsonProperty("poster")
    protected String mPosterUrl;
    @JsonProperty("preview_encoded_png")
    protected String mPreviewEncodedPng;
    @JsonProperty("promo_picture")
    protected String mPromoPicture;
    @JsonProperty("scrim_color")
    protected int mScrimColor;
    @JsonProperty("small")
    protected String mSmallUrl;
    @JsonProperty("sort_order")
    protected int mSortOrder;
    @JsonProperty("x_large")
    protected String mXLargeUrl;
    @JsonProperty("x_medium")
    protected String mXMediumUrl;
    @JsonProperty("x_small")
    protected String mXSmallUrl;
    @JsonProperty("xl_poster")
    protected String mXlPosterUrl;
    @JsonProperty("xx_large")
    protected String mXxLargeUrl;

    protected GenPhoto(String xlPosterUrl, String posterUrl, String xSmallUrl, String smallUrl, String mediumUrl, String xMediumUrl, String largeUrl, String xLargeUrl, String xxLargeUrl, String caption, String previewEncodedPng, String largeRo, String promoPicture, int sortOrder, int scrimColor, int dominantSaturatedColor, long id) {
        this();
        this.mXlPosterUrl = xlPosterUrl;
        this.mPosterUrl = posterUrl;
        this.mXSmallUrl = xSmallUrl;
        this.mSmallUrl = smallUrl;
        this.mMediumUrl = mediumUrl;
        this.mXMediumUrl = xMediumUrl;
        this.mLargeUrl = largeUrl;
        this.mXLargeUrl = xLargeUrl;
        this.mXxLargeUrl = xxLargeUrl;
        this.mCaption = caption;
        this.mPreviewEncodedPng = previewEncodedPng;
        this.mLargeRo = largeRo;
        this.mPromoPicture = promoPicture;
        this.mSortOrder = sortOrder;
        this.mScrimColor = scrimColor;
        this.mDominantSaturatedColor = dominantSaturatedColor;
        this.mId = id;
    }

    protected GenPhoto() {
    }

    public String getXlPosterUrl() {
        return this.mXlPosterUrl;
    }

    @JsonProperty("xl_poster")
    public void setXlPosterUrl(String value) {
        this.mXlPosterUrl = value;
    }

    public String getPosterUrl() {
        return this.mPosterUrl;
    }

    @JsonProperty("poster")
    public void setPosterUrl(String value) {
        this.mPosterUrl = value;
    }

    public String getXSmallUrl() {
        return this.mXSmallUrl;
    }

    @JsonProperty("x_small")
    public void setXSmallUrl(String value) {
        this.mXSmallUrl = value;
    }

    public String getSmallUrl() {
        return this.mSmallUrl;
    }

    @JsonProperty("small")
    public void setSmallUrl(String value) {
        this.mSmallUrl = value;
    }

    public String getMediumUrl() {
        return this.mMediumUrl;
    }

    @JsonProperty("medium")
    public void setMediumUrl(String value) {
        this.mMediumUrl = value;
    }

    public String getXMediumUrl() {
        return this.mXMediumUrl;
    }

    @JsonProperty("x_medium")
    public void setXMediumUrl(String value) {
        this.mXMediumUrl = value;
    }

    public String getLargeUrl() {
        return this.mLargeUrl;
    }

    @JsonProperty("large")
    public void setLargeUrl(String value) {
        this.mLargeUrl = value;
    }

    public String getXLargeUrl() {
        return this.mXLargeUrl;
    }

    @JsonProperty("x_large")
    public void setXLargeUrl(String value) {
        this.mXLargeUrl = value;
    }

    public String getXxLargeUrl() {
        return this.mXxLargeUrl;
    }

    @JsonProperty("xx_large")
    public void setXxLargeUrl(String value) {
        this.mXxLargeUrl = value;
    }

    public String getCaption() {
        return this.mCaption;
    }

    @JsonProperty("caption")
    public void setCaption(String value) {
        this.mCaption = value;
    }

    public String getPreviewEncodedPng() {
        return this.mPreviewEncodedPng;
    }

    @JsonProperty("preview_encoded_png")
    public void setPreviewEncodedPng(String value) {
        this.mPreviewEncodedPng = value;
    }

    public String getLargeRo() {
        return this.mLargeRo;
    }

    @JsonProperty("large_ro")
    public void setLargeRo(String value) {
        this.mLargeRo = value;
    }

    public String getPromoPicture() {
        return this.mPromoPicture;
    }

    @JsonProperty("promo_picture")
    public void setPromoPicture(String value) {
        this.mPromoPicture = value;
    }

    public int getSortOrder() {
        return this.mSortOrder;
    }

    @JsonProperty("sort_order")
    public void setSortOrder(int value) {
        this.mSortOrder = value;
    }

    public int getScrimColor() {
        return this.mScrimColor;
    }

    public int getDominantSaturatedColor() {
        return this.mDominantSaturatedColor;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mXlPosterUrl);
        parcel.writeString(this.mPosterUrl);
        parcel.writeString(this.mXSmallUrl);
        parcel.writeString(this.mSmallUrl);
        parcel.writeString(this.mMediumUrl);
        parcel.writeString(this.mXMediumUrl);
        parcel.writeString(this.mLargeUrl);
        parcel.writeString(this.mXLargeUrl);
        parcel.writeString(this.mXxLargeUrl);
        parcel.writeString(this.mCaption);
        parcel.writeString(this.mPreviewEncodedPng);
        parcel.writeString(this.mLargeRo);
        parcel.writeString(this.mPromoPicture);
        parcel.writeInt(this.mSortOrder);
        parcel.writeInt(this.mScrimColor);
        parcel.writeInt(this.mDominantSaturatedColor);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mXlPosterUrl = source.readString();
        this.mPosterUrl = source.readString();
        this.mXSmallUrl = source.readString();
        this.mSmallUrl = source.readString();
        this.mMediumUrl = source.readString();
        this.mXMediumUrl = source.readString();
        this.mLargeUrl = source.readString();
        this.mXLargeUrl = source.readString();
        this.mXxLargeUrl = source.readString();
        this.mCaption = source.readString();
        this.mPreviewEncodedPng = source.readString();
        this.mLargeRo = source.readString();
        this.mPromoPicture = source.readString();
        this.mSortOrder = source.readInt();
        this.mScrimColor = source.readInt();
        this.mDominantSaturatedColor = source.readInt();
        this.mId = source.readLong();
    }
}

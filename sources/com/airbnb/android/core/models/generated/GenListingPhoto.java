package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingPhoto implements Parcelable {
    @JsonProperty("caption")
    protected String mCaption;
    @JsonProperty("extra_large_url")
    protected String mExtraLargeUrl;
    @JsonProperty("extra_medium_url")
    protected String mExtraMediumUrl;
    @JsonProperty("id")
    protected String mId;
    @JsonProperty("large_url")
    protected String mLargeUrl;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("small_url")
    protected String mSmallUrl;
    @JsonProperty("sort_order")
    protected int mSortOrder;
    @JsonProperty("thumbnail_url")
    protected String mThumbnailUrl;

    protected GenListingPhoto(String caption, String extraLargeUrl, String extraMediumUrl, String id, String largeUrl, String smallUrl, String thumbnailUrl, int sortOrder, long listingId) {
        this();
        this.mCaption = caption;
        this.mExtraLargeUrl = extraLargeUrl;
        this.mExtraMediumUrl = extraMediumUrl;
        this.mId = id;
        this.mLargeUrl = largeUrl;
        this.mSmallUrl = smallUrl;
        this.mThumbnailUrl = thumbnailUrl;
        this.mSortOrder = sortOrder;
        this.mListingId = listingId;
    }

    protected GenListingPhoto() {
    }

    public String getCaption() {
        return this.mCaption;
    }

    @JsonProperty("caption")
    public void setCaption(String value) {
        this.mCaption = value;
    }

    public String getExtraLargeUrl() {
        return this.mExtraLargeUrl;
    }

    @JsonProperty("extra_large_url")
    public void setExtraLargeUrl(String value) {
        this.mExtraLargeUrl = value;
    }

    public String getExtraMediumUrl() {
        return this.mExtraMediumUrl;
    }

    @JsonProperty("extra_medium_url")
    public void setExtraMediumUrl(String value) {
        this.mExtraMediumUrl = value;
    }

    public String getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(String value) {
        this.mId = value;
    }

    public String getLargeUrl() {
        return this.mLargeUrl;
    }

    @JsonProperty("large_url")
    public void setLargeUrl(String value) {
        this.mLargeUrl = value;
    }

    public String getSmallUrl() {
        return this.mSmallUrl;
    }

    @JsonProperty("small_url")
    public void setSmallUrl(String value) {
        this.mSmallUrl = value;
    }

    public String getThumbnailUrl() {
        return this.mThumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(String value) {
        this.mThumbnailUrl = value;
    }

    public int getSortOrder() {
        return this.mSortOrder;
    }

    @JsonProperty("sort_order")
    public void setSortOrder(int value) {
        this.mSortOrder = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("listing_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mCaption);
        parcel.writeString(this.mExtraLargeUrl);
        parcel.writeString(this.mExtraMediumUrl);
        parcel.writeString(this.mId);
        parcel.writeString(this.mLargeUrl);
        parcel.writeString(this.mSmallUrl);
        parcel.writeString(this.mThumbnailUrl);
        parcel.writeInt(this.mSortOrder);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mCaption = source.readString();
        this.mExtraLargeUrl = source.readString();
        this.mExtraMediumUrl = source.readString();
        this.mId = source.readString();
        this.mLargeUrl = source.readString();
        this.mSmallUrl = source.readString();
        this.mThumbnailUrl = source.readString();
        this.mSortOrder = source.readInt();
        this.mListingId = source.readLong();
    }
}

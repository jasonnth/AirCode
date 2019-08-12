package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGiftCreditTemplate implements Parcelable {
    @JsonProperty("campaign_name")
    protected String mCampaignName;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("main_url")
    protected String mMainUrl;
    @JsonProperty("thumbnail_url")
    protected String mThumbnailUrl;

    protected GenGiftCreditTemplate(String campaignName, String mainUrl, String thumbnailUrl, long id) {
        this();
        this.mCampaignName = campaignName;
        this.mMainUrl = mainUrl;
        this.mThumbnailUrl = thumbnailUrl;
        this.mId = id;
    }

    protected GenGiftCreditTemplate() {
    }

    public String getCampaignName() {
        return this.mCampaignName;
    }

    @JsonProperty("campaign_name")
    public void setCampaignName(String value) {
        this.mCampaignName = value;
    }

    public String getMainUrl() {
        return this.mMainUrl;
    }

    @JsonProperty("main_url")
    public void setMainUrl(String value) {
        this.mMainUrl = value;
    }

    public String getThumbnailUrl() {
        return this.mThumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(String value) {
        this.mThumbnailUrl = value;
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
        parcel.writeString(this.mCampaignName);
        parcel.writeString(this.mMainUrl);
        parcel.writeString(this.mThumbnailUrl);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mCampaignName = source.readString();
        this.mMainUrl = source.readString();
        this.mThumbnailUrl = source.readString();
        this.mId = source.readLong();
    }
}

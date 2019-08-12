package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.PaginationMetadata;
import com.airbnb.android.core.models.Photo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenExplorePlaylist implements Parcelable {
    @JsonProperty("campaign_id")
    protected long mCampaignId;
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("header_picture_sm")
    protected Photo mHeaderPictureSm;
    @JsonProperty("market")
    protected String mMarket;
    @JsonProperty("pagination_metadata")
    protected PaginationMetadata mPaginationMetadata;
    @JsonProperty("sections")
    protected List<ExploreSection> mSections;
    @JsonProperty("share_url")
    protected String mShareUrl;
    @JsonProperty("subtitle")
    protected String mSubtitle;
    @JsonProperty("title")
    protected String mTitle;

    protected GenExplorePlaylist(List<ExploreSection> sections, PaginationMetadata paginationMetadata, Photo headerPictureSm, String title, String subtitle, String description, String shareUrl, String market, long campaignId) {
        this();
        this.mSections = sections;
        this.mPaginationMetadata = paginationMetadata;
        this.mHeaderPictureSm = headerPictureSm;
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mDescription = description;
        this.mShareUrl = shareUrl;
        this.mMarket = market;
        this.mCampaignId = campaignId;
    }

    protected GenExplorePlaylist() {
    }

    public List<ExploreSection> getSections() {
        return this.mSections;
    }

    @JsonProperty("sections")
    public void setSections(List<ExploreSection> value) {
        this.mSections = value;
    }

    public PaginationMetadata getPaginationMetadata() {
        return this.mPaginationMetadata;
    }

    @JsonProperty("pagination_metadata")
    public void setPaginationMetadata(PaginationMetadata value) {
        this.mPaginationMetadata = value;
    }

    public Photo getHeaderPictureSm() {
        return this.mHeaderPictureSm;
    }

    @JsonProperty("header_picture_sm")
    public void setHeaderPictureSm(Photo value) {
        this.mHeaderPictureSm = value;
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

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getShareUrl() {
        return this.mShareUrl;
    }

    @JsonProperty("share_url")
    public void setShareUrl(String value) {
        this.mShareUrl = value;
    }

    public String getMarket() {
        return this.mMarket;
    }

    @JsonProperty("market")
    public void setMarket(String value) {
        this.mMarket = value;
    }

    public long getCampaignId() {
        return this.mCampaignId;
    }

    @JsonProperty("campaign_id")
    public void setCampaignId(long value) {
        this.mCampaignId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mSections);
        parcel.writeParcelable(this.mPaginationMetadata, 0);
        parcel.writeParcelable(this.mHeaderPictureSm, 0);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mShareUrl);
        parcel.writeString(this.mMarket);
        parcel.writeLong(this.mCampaignId);
    }

    public void readFromParcel(Parcel source) {
        this.mSections = source.createTypedArrayList(ExploreSection.CREATOR);
        this.mPaginationMetadata = (PaginationMetadata) source.readParcelable(PaginationMetadata.class.getClassLoader());
        this.mHeaderPictureSm = (Photo) source.readParcelable(Photo.class.getClassLoader());
        this.mTitle = source.readString();
        this.mSubtitle = source.readString();
        this.mDescription = source.readString();
        this.mShareUrl = source.readString();
        this.mMarket = source.readString();
        this.mCampaignId = source.readLong();
    }
}

package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.Video;
import com.airbnb.android.core.p008mt.models.RecommendationCardType;
import com.airbnb.android.core.p008mt.models.RecommendationItemType;
import com.airbnb.android.utils.ParcelableStringMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenRecommendationItem implements Parcelable {
    @JsonProperty("banner_picture")
    protected Photo mBannerPicture;
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("item_type")
    protected RecommendationItemType mItemType;
    @JsonProperty("picture")
    protected Photo mPicture;
    @JsonProperty("query_param")
    protected ParcelableStringMap mQueryParam;
    @JsonProperty("size")
    protected RecommendationCardType mSize;
    @JsonProperty("sub_text")
    protected String mSubText;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("video")
    protected Video mVideo;

    protected GenRecommendationItem(ParcelableStringMap queryParam, Photo picture, Photo bannerPicture, RecommendationCardType size, RecommendationItemType itemType, String title, String subText, String description, Video video, long id) {
        this();
        this.mQueryParam = queryParam;
        this.mPicture = picture;
        this.mBannerPicture = bannerPicture;
        this.mSize = size;
        this.mItemType = itemType;
        this.mTitle = title;
        this.mSubText = subText;
        this.mDescription = description;
        this.mVideo = video;
        this.mId = id;
    }

    protected GenRecommendationItem() {
    }

    public ParcelableStringMap getQueryParam() {
        return this.mQueryParam;
    }

    @JsonProperty("query_param")
    public void setQueryParam(ParcelableStringMap value) {
        this.mQueryParam = value;
    }

    public Photo getPicture() {
        return this.mPicture;
    }

    @JsonProperty("picture")
    public void setPicture(Photo value) {
        this.mPicture = value;
    }

    public Photo getBannerPicture() {
        return this.mBannerPicture;
    }

    @JsonProperty("banner_picture")
    public void setBannerPicture(Photo value) {
        this.mBannerPicture = value;
    }

    public RecommendationCardType getSize() {
        return this.mSize;
    }

    @JsonProperty("size")
    public void setSize(RecommendationCardType value) {
        this.mSize = value;
    }

    public RecommendationItemType getItemType() {
        return this.mItemType;
    }

    @JsonProperty("item_type")
    public void setItemType(RecommendationItemType value) {
        this.mItemType = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getSubText() {
        return this.mSubText;
    }

    @JsonProperty("sub_text")
    public void setSubText(String value) {
        this.mSubText = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public Video getVideo() {
        return this.mVideo;
    }

    @JsonProperty("video")
    public void setVideo(Video value) {
        this.mVideo = value;
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
        parcel.writeParcelable(this.mQueryParam, 0);
        parcel.writeParcelable(this.mPicture, 0);
        parcel.writeParcelable(this.mBannerPicture, 0);
        parcel.writeParcelable(this.mSize, 0);
        parcel.writeParcelable(this.mItemType, 0);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubText);
        parcel.writeString(this.mDescription);
        parcel.writeParcelable(this.mVideo, 0);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mQueryParam = (ParcelableStringMap) source.readParcelable(ParcelableStringMap.class.getClassLoader());
        this.mPicture = (Photo) source.readParcelable(Photo.class.getClassLoader());
        this.mBannerPicture = (Photo) source.readParcelable(Photo.class.getClassLoader());
        this.mSize = (RecommendationCardType) source.readParcelable(RecommendationCardType.class.getClassLoader());
        this.mItemType = (RecommendationItemType) source.readParcelable(RecommendationItemType.class.getClassLoader());
        this.mTitle = source.readString();
        this.mSubText = source.readString();
        this.mDescription = source.readString();
        this.mVideo = (Video) source.readParcelable(Video.class.getClassLoader());
        this.mId = source.readLong();
    }
}

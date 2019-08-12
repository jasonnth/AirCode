package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.C6049Location;
import com.airbnb.android.core.models.SearchParams;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenTravelDestination implements Parcelable {
    @JsonProperty("large_picture_url")
    protected String mLargePictureUrl;
    @JsonProperty("location")
    protected C6049Location mLocation;
    @JsonProperty("picture_url")
    protected String mPictureUrl;
    @JsonProperty("search_filter_set")
    protected SearchParams mSearchFilterSet;
    @JsonProperty("subtitle")
    protected String mSubtitle;
    @JsonProperty("thumbnail_url")
    protected String mThumbnailUrl;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("travel_destination_type")
    protected String mTravelDestinationType;

    protected GenTravelDestination(C6049Location location, SearchParams searchFilterSet, String title, String subtitle, String pictureUrl, String largePictureUrl, String thumbnailUrl, String travelDestinationType) {
        this();
        this.mLocation = location;
        this.mSearchFilterSet = searchFilterSet;
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mPictureUrl = pictureUrl;
        this.mLargePictureUrl = largePictureUrl;
        this.mThumbnailUrl = thumbnailUrl;
        this.mTravelDestinationType = travelDestinationType;
    }

    protected GenTravelDestination() {
    }

    public C6049Location getLocation() {
        return this.mLocation;
    }

    @JsonProperty("location")
    public void setLocation(C6049Location value) {
        this.mLocation = value;
    }

    public SearchParams getSearchFilterSet() {
        return this.mSearchFilterSet;
    }

    @JsonProperty("search_filter_set")
    public void setSearchFilterSet(SearchParams value) {
        this.mSearchFilterSet = value;
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

    public String getPictureUrl() {
        return this.mPictureUrl;
    }

    @JsonProperty("picture_url")
    public void setPictureUrl(String value) {
        this.mPictureUrl = value;
    }

    public String getLargePictureUrl() {
        return this.mLargePictureUrl;
    }

    @JsonProperty("large_picture_url")
    public void setLargePictureUrl(String value) {
        this.mLargePictureUrl = value;
    }

    public String getThumbnailUrl() {
        return this.mThumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(String value) {
        this.mThumbnailUrl = value;
    }

    public String getTravelDestinationType() {
        return this.mTravelDestinationType;
    }

    @JsonProperty("travel_destination_type")
    public void setTravelDestinationType(String value) {
        this.mTravelDestinationType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mLocation, 0);
        parcel.writeParcelable(this.mSearchFilterSet, 0);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mPictureUrl);
        parcel.writeString(this.mLargePictureUrl);
        parcel.writeString(this.mThumbnailUrl);
        parcel.writeString(this.mTravelDestinationType);
    }

    public void readFromParcel(Parcel source) {
        this.mLocation = (C6049Location) source.readParcelable(C6049Location.class.getClassLoader());
        this.mSearchFilterSet = (SearchParams) source.readParcelable(SearchParams.class.getClassLoader());
        this.mTitle = source.readString();
        this.mSubtitle = source.readString();
        this.mPictureUrl = source.readString();
        this.mLargePictureUrl = source.readString();
        this.mThumbnailUrl = source.readString();
        this.mTravelDestinationType = source.readString();
    }
}

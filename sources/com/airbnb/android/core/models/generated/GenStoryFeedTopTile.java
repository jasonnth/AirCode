package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.utils.ParcelableStringMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenStoryFeedTopTile implements Parcelable {
    @JsonProperty("image_url")
    protected String mImageUrl;
    @JsonProperty("main_text")
    protected String mMainText;
    @JsonProperty("query_params")
    protected ParcelableStringMap mQueryParams;
    @JsonProperty("secondary_text")
    protected String mSecondaryText;

    protected GenStoryFeedTopTile(ParcelableStringMap queryParams, String imageUrl, String mainText, String secondaryText) {
        this();
        this.mQueryParams = queryParams;
        this.mImageUrl = imageUrl;
        this.mMainText = mainText;
        this.mSecondaryText = secondaryText;
    }

    protected GenStoryFeedTopTile() {
    }

    public ParcelableStringMap getQueryParams() {
        return this.mQueryParams;
    }

    @JsonProperty("query_params")
    public void setQueryParams(ParcelableStringMap value) {
        this.mQueryParams = value;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String value) {
        this.mImageUrl = value;
    }

    public String getMainText() {
        return this.mMainText;
    }

    @JsonProperty("main_text")
    public void setMainText(String value) {
        this.mMainText = value;
    }

    public String getSecondaryText() {
        return this.mSecondaryText;
    }

    @JsonProperty("secondary_text")
    public void setSecondaryText(String value) {
        this.mSecondaryText = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mQueryParams, 0);
        parcel.writeString(this.mImageUrl);
        parcel.writeString(this.mMainText);
        parcel.writeString(this.mSecondaryText);
    }

    public void readFromParcel(Parcel source) {
        this.mQueryParams = (ParcelableStringMap) source.readParcelable(ParcelableStringMap.class.getClassLoader());
        this.mImageUrl = source.readString();
        this.mMainText = source.readString();
        this.mSecondaryText = source.readString();
    }
}

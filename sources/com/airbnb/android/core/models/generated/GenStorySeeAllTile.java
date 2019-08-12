package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenStorySeeAllTile implements Parcelable {
    @JsonProperty("photo_url")
    protected String mPhotoUrl;
    @JsonProperty("search_term")
    protected String mSearchTerm;
    @JsonProperty("see_all_text")
    protected String mSeeAllText;

    protected GenStorySeeAllTile(String photoUrl, String searchTerm, String seeAllText) {
        this();
        this.mPhotoUrl = photoUrl;
        this.mSearchTerm = searchTerm;
        this.mSeeAllText = seeAllText;
    }

    protected GenStorySeeAllTile() {
    }

    public String getPhotoUrl() {
        return this.mPhotoUrl;
    }

    @JsonProperty("photo_url")
    public void setPhotoUrl(String value) {
        this.mPhotoUrl = value;
    }

    public String getSearchTerm() {
        return this.mSearchTerm;
    }

    @JsonProperty("search_term")
    public void setSearchTerm(String value) {
        this.mSearchTerm = value;
    }

    public String getSeeAllText() {
        return this.mSeeAllText;
    }

    @JsonProperty("see_all_text")
    public void setSeeAllText(String value) {
        this.mSeeAllText = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mPhotoUrl);
        parcel.writeString(this.mSearchTerm);
        parcel.writeString(this.mSeeAllText);
    }

    public void readFromParcel(Parcel source) {
        this.mPhotoUrl = source.readString();
        this.mSearchTerm = source.readString();
        this.mSeeAllText = source.readString();
    }
}

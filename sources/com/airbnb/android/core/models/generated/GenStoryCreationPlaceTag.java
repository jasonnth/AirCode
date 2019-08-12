package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenStoryCreationPlaceTag implements Parcelable {
    @JsonProperty("google_place_id")
    protected String mGooglePlaceId;
    @JsonProperty("main_text")
    protected String mMainText;
    @JsonProperty("secondary_text")
    protected String mSecondaryText;

    protected GenStoryCreationPlaceTag(String mainText, String secondaryText, String googlePlaceId) {
        this();
        this.mMainText = mainText;
        this.mSecondaryText = secondaryText;
        this.mGooglePlaceId = googlePlaceId;
    }

    protected GenStoryCreationPlaceTag() {
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

    public String getGooglePlaceId() {
        return this.mGooglePlaceId;
    }

    @JsonProperty("google_place_id")
    public void setGooglePlaceId(String value) {
        this.mGooglePlaceId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mMainText);
        parcel.writeString(this.mSecondaryText);
        parcel.writeString(this.mGooglePlaceId);
    }

    public void readFromParcel(Parcel source) {
        this.mMainText = source.readString();
        this.mSecondaryText = source.readString();
        this.mGooglePlaceId = source.readString();
    }
}

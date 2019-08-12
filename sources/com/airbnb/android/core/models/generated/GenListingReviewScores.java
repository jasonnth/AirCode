package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingReviewScores implements Parcelable {
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("rating")
    protected String mStateKey;

    protected GenListingReviewScores(String description, String stateKey) {
        this();
        this.mDescription = description;
        this.mStateKey = stateKey;
    }

    protected GenListingReviewScores() {
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getStateKey() {
        return this.mStateKey;
    }

    @JsonProperty("rating")
    public void setStateKey(String value) {
        this.mStateKey = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mStateKey);
    }

    public void readFromParcel(Parcel source) {
        this.mDescription = source.readString();
        this.mStateKey = source.readString();
    }
}

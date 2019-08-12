package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHostRatingDistributionStatistic implements Parcelable {
    @JsonProperty("percentage")
    protected int mPercentage;
    @JsonProperty("rating")
    protected int mRating;

    protected GenHostRatingDistributionStatistic(int rating, int percentage) {
        this();
        this.mRating = rating;
        this.mPercentage = percentage;
    }

    protected GenHostRatingDistributionStatistic() {
    }

    public int getRating() {
        return this.mRating;
    }

    @JsonProperty("rating")
    public void setRating(int value) {
        this.mRating = value;
    }

    public int getPercentage() {
        return this.mPercentage;
    }

    @JsonProperty("percentage")
    public void setPercentage(int value) {
        this.mPercentage = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mRating);
        parcel.writeInt(this.mPercentage);
    }

    public void readFromParcel(Parcel source) {
        this.mRating = source.readInt();
        this.mPercentage = source.readInt();
    }
}

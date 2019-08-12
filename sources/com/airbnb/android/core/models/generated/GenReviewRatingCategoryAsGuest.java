package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReviewRatingCategoryAsGuest implements Parcelable {
    @JsonProperty("average_rating")
    protected float mAverageRating;
    @JsonProperty("reviews_count")
    protected int mReviewsCount;

    protected GenReviewRatingCategoryAsGuest(float averageRating, int reviewsCount) {
        this();
        this.mAverageRating = averageRating;
        this.mReviewsCount = reviewsCount;
    }

    protected GenReviewRatingCategoryAsGuest() {
    }

    public float getAverageRating() {
        return this.mAverageRating;
    }

    @JsonProperty("average_rating")
    public void setAverageRating(float value) {
        this.mAverageRating = value;
    }

    public int getReviewsCount() {
        return this.mReviewsCount;
    }

    @JsonProperty("reviews_count")
    public void setReviewsCount(int value) {
        this.mReviewsCount = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeFloat(this.mAverageRating);
        parcel.writeInt(this.mReviewsCount);
    }

    public void readFromParcel(Parcel source) {
        this.mAverageRating = source.readFloat();
        this.mReviewsCount = source.readInt();
    }
}

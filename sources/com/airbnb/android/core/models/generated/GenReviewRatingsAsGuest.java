package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ReviewRatingCategoryAsGuest;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReviewRatingsAsGuest implements Parcelable {
    @JsonProperty("cleanliness")
    protected ReviewRatingCategoryAsGuest mCleanliness;
    @JsonProperty("communication")
    protected ReviewRatingCategoryAsGuest mCommunication;
    @JsonProperty("overall")
    protected ReviewRatingCategoryAsGuest mOverall;
    @JsonProperty("respect_house_rules")
    protected ReviewRatingCategoryAsGuest mRespectHouseRules;

    protected GenReviewRatingsAsGuest(ReviewRatingCategoryAsGuest cleanliness, ReviewRatingCategoryAsGuest respectHouseRules, ReviewRatingCategoryAsGuest communication, ReviewRatingCategoryAsGuest overall) {
        this();
        this.mCleanliness = cleanliness;
        this.mRespectHouseRules = respectHouseRules;
        this.mCommunication = communication;
        this.mOverall = overall;
    }

    protected GenReviewRatingsAsGuest() {
    }

    public ReviewRatingCategoryAsGuest getCleanliness() {
        return this.mCleanliness;
    }

    @JsonProperty("cleanliness")
    public void setCleanliness(ReviewRatingCategoryAsGuest value) {
        this.mCleanliness = value;
    }

    public ReviewRatingCategoryAsGuest getRespectHouseRules() {
        return this.mRespectHouseRules;
    }

    @JsonProperty("respect_house_rules")
    public void setRespectHouseRules(ReviewRatingCategoryAsGuest value) {
        this.mRespectHouseRules = value;
    }

    public ReviewRatingCategoryAsGuest getCommunication() {
        return this.mCommunication;
    }

    @JsonProperty("communication")
    public void setCommunication(ReviewRatingCategoryAsGuest value) {
        this.mCommunication = value;
    }

    public ReviewRatingCategoryAsGuest getOverall() {
        return this.mOverall;
    }

    @JsonProperty("overall")
    public void setOverall(ReviewRatingCategoryAsGuest value) {
        this.mOverall = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCleanliness, 0);
        parcel.writeParcelable(this.mRespectHouseRules, 0);
        parcel.writeParcelable(this.mCommunication, 0);
        parcel.writeParcelable(this.mOverall, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mCleanliness = (ReviewRatingCategoryAsGuest) source.readParcelable(ReviewRatingCategoryAsGuest.class.getClassLoader());
        this.mRespectHouseRules = (ReviewRatingCategoryAsGuest) source.readParcelable(ReviewRatingCategoryAsGuest.class.getClassLoader());
        this.mCommunication = (ReviewRatingCategoryAsGuest) source.readParcelable(ReviewRatingCategoryAsGuest.class.getClassLoader());
        this.mOverall = (ReviewRatingCategoryAsGuest) source.readParcelable(ReviewRatingCategoryAsGuest.class.getClassLoader());
    }
}

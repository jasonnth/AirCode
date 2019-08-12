package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReviewRatingCategoryAsGuest;

public class ReviewRatingCategoryAsGuest extends GenReviewRatingCategoryAsGuest {
    public static final Creator<ReviewRatingCategoryAsGuest> CREATOR = new Creator<ReviewRatingCategoryAsGuest>() {
        public ReviewRatingCategoryAsGuest[] newArray(int size) {
            return new ReviewRatingCategoryAsGuest[size];
        }

        public ReviewRatingCategoryAsGuest createFromParcel(Parcel source) {
            ReviewRatingCategoryAsGuest object = new ReviewRatingCategoryAsGuest();
            object.readFromParcel(source);
            return object;
        }
    };
}

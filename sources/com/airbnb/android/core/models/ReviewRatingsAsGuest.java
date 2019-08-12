package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReviewRatingsAsGuest;

public class ReviewRatingsAsGuest extends GenReviewRatingsAsGuest {
    public static final Creator<ReviewRatingsAsGuest> CREATOR = new Creator<ReviewRatingsAsGuest>() {
        public ReviewRatingsAsGuest[] newArray(int size) {
            return new ReviewRatingsAsGuest[size];
        }

        public ReviewRatingsAsGuest createFromParcel(Parcel source) {
            ReviewRatingsAsGuest object = new ReviewRatingsAsGuest();
            object.readFromParcel(source);
            return object;
        }
    };
}

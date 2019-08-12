package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingReviewScores;

public class ListingReviewScores extends GenListingReviewScores {
    public static final Creator<ListingReviewScores> CREATOR = new Creator<ListingReviewScores>() {
        public ListingReviewScores[] newArray(int size) {
            return new ListingReviewScores[size];
        }

        public ListingReviewScores createFromParcel(Parcel source) {
            ListingReviewScores object = new ListingReviewScores();
            object.readFromParcel(source);
            return object;
        }
    };
}

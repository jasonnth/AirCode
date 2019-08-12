package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRatingAggregation;

public class ListingRatingAggregation extends GenListingRatingAggregation {
    public static final Creator<ListingRatingAggregation> CREATOR = new Creator<ListingRatingAggregation>() {
        public ListingRatingAggregation[] newArray(int size) {
            return new ListingRatingAggregation[size];
        }

        public ListingRatingAggregation createFromParcel(Parcel source) {
            ListingRatingAggregation object = new ListingRatingAggregation();
            object.readFromParcel(source);
            return object;
        }
    };
}

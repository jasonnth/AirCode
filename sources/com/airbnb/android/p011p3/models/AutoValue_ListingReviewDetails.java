package com.airbnb.android.p011p3.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

/* renamed from: com.airbnb.android.p3.models.AutoValue_ListingReviewDetails */
final class AutoValue_ListingReviewDetails extends C$AutoValue_ListingReviewDetails {
    public static final Creator<AutoValue_ListingReviewDetails> CREATOR = new Creator<AutoValue_ListingReviewDetails>() {
        public AutoValue_ListingReviewDetails createFromParcel(Parcel in) {
            return new AutoValue_ListingReviewDetails(in.readInt(), in.readArrayList(ReviewSummaryItem.class.getClassLoader()));
        }

        public AutoValue_ListingReviewDetails[] newArray(int size) {
            return new AutoValue_ListingReviewDetails[size];
        }
    };

    AutoValue_ListingReviewDetails(int reviewCount, List<ReviewSummaryItem> reviewSummary) {
        super(reviewCount, reviewSummary);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(reviewCount());
        dest.writeList(reviewSummary());
    }

    public int describeContents() {
        return 0;
    }
}

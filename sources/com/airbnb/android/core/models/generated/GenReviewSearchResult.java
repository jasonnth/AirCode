package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.AttributedTextRange;
import com.airbnb.android.core.models.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenReviewSearchResult implements Parcelable {
    @JsonProperty("attributed_texts_match_ranges")
    protected List<AttributedTextRange> mAttributedTextsMatchRanges;
    @JsonProperty("review")
    protected Review mReview;

    protected GenReviewSearchResult(List<AttributedTextRange> attributedTextsMatchRanges, Review review) {
        this();
        this.mAttributedTextsMatchRanges = attributedTextsMatchRanges;
        this.mReview = review;
    }

    protected GenReviewSearchResult() {
    }

    public List<AttributedTextRange> getAttributedTextsMatchRanges() {
        return this.mAttributedTextsMatchRanges;
    }

    public Review getReview() {
        return this.mReview;
    }

    @JsonProperty("review")
    public void setReview(Review value) {
        this.mReview = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mAttributedTextsMatchRanges);
        parcel.writeParcelable(this.mReview, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mAttributedTextsMatchRanges = source.createTypedArrayList(AttributedTextRange.CREATOR);
        this.mReview = (Review) source.readParcelable(Review.class.getClassLoader());
    }
}

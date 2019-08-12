package com.airbnb.android.p011p3.models;

import java.util.List;

/* renamed from: com.airbnb.android.p3.models.$AutoValue_ListingReviewDetails reason: invalid class name */
abstract class C$AutoValue_ListingReviewDetails extends ListingReviewDetails {
    private final int reviewCount;
    private final List<ReviewSummaryItem> reviewSummary;

    /* renamed from: com.airbnb.android.p3.models.$AutoValue_ListingReviewDetails$Builder */
    static final class Builder extends com.airbnb.android.p011p3.models.ListingReviewDetails.Builder {
        private Integer reviewCount;
        private List<ReviewSummaryItem> reviewSummary;

        Builder() {
        }

        public com.airbnb.android.p011p3.models.ListingReviewDetails.Builder reviewCount(int reviewCount2) {
            this.reviewCount = Integer.valueOf(reviewCount2);
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingReviewDetails.Builder reviewSummary(List<ReviewSummaryItem> reviewSummary2) {
            if (reviewSummary2 == null) {
                throw new NullPointerException("Null reviewSummary");
            }
            this.reviewSummary = reviewSummary2;
            return this;
        }

        public ListingReviewDetails build() {
            String missing = "";
            if (this.reviewCount == null) {
                missing = missing + " reviewCount";
            }
            if (this.reviewSummary == null) {
                missing = missing + " reviewSummary";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ListingReviewDetails(this.reviewCount.intValue(), this.reviewSummary);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ListingReviewDetails(int reviewCount2, List<ReviewSummaryItem> reviewSummary2) {
        this.reviewCount = reviewCount2;
        if (reviewSummary2 == null) {
            throw new NullPointerException("Null reviewSummary");
        }
        this.reviewSummary = reviewSummary2;
    }

    public int reviewCount() {
        return this.reviewCount;
    }

    public List<ReviewSummaryItem> reviewSummary() {
        return this.reviewSummary;
    }

    public String toString() {
        return "ListingReviewDetails{reviewCount=" + this.reviewCount + ", reviewSummary=" + this.reviewSummary + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ListingReviewDetails)) {
            return false;
        }
        ListingReviewDetails that = (ListingReviewDetails) o;
        if (this.reviewCount != that.reviewCount() || !this.reviewSummary.equals(that.reviewSummary())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.reviewCount) * 1000003) ^ this.reviewSummary.hashCode();
    }
}

package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.requests.AirRequestFactory.Provider;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ReviewsResponse extends BaseResponse implements Provider<Review> {
    @JsonProperty("metadata")
    public MetaData metaData;
    @JsonProperty("reviews")
    private List<Review> reviews;

    public static class MetaData {
        @JsonProperty("rated_reviews_count")
        public int ratedReviewsCount;
        /* access modifiers changed from: private */
        @JsonProperty("reviews_count")
        public int reviewsCount;
        @JsonProperty("should_show_review_translations")
        public boolean shouldShowReviewTranslations;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    public List<Review> provide() {
        return getReviews();
    }

    public int getReviewsCount() {
        if (this.metaData == null) {
            return 0;
        }
        return this.metaData.reviewsCount;
    }

    public int getRatedReviewsCount() {
        if (this.metaData == null) {
            return 0;
        }
        return this.metaData.ratedReviewsCount;
    }
}

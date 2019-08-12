package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;

public class TranslateReviewsResponse extends BaseResponse {
    @JsonProperty("translated_reviews")
    public List<TranslatedReview> translatedReviews;

    public HashMap<Long, TranslatedReview> reviewsAsHashMap() {
        HashMap<Long, TranslatedReview> map = new HashMap<>();
        for (TranslatedReview review : this.translatedReviews) {
            map.put(Long.valueOf(review.reviewId), review);
        }
        return map;
    }
}

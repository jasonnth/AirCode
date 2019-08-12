package com.airbnb.android.core.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class TranslatedReview {
    @JsonProperty("id")

    /* renamed from: id */
    public long f8499id;
    @JsonProperty("is_translated")
    public boolean isTranslated;
    @JsonProperty("review_id")
    public long reviewId;
    @JsonProperty("comments")
    public String translatedReview;
}

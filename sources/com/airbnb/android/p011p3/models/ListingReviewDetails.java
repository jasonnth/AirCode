package com.airbnb.android.p011p3.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
/* renamed from: com.airbnb.android.p3.models.ListingReviewDetails */
public abstract class ListingReviewDetails implements Parcelable {

    /* renamed from: com.airbnb.android.p3.models.ListingReviewDetails$Builder */
    public static abstract class Builder {
        public abstract ListingReviewDetails build();

        @JsonProperty
        public abstract Builder reviewCount(int i);

        @JsonProperty
        public abstract Builder reviewSummary(List<ReviewSummaryItem> list);
    }

    public abstract int reviewCount();

    public abstract List<ReviewSummaryItem> reviewSummary();

    public static Builder builder() {
        return new Builder();
    }
}

package com.airbnb.android.p011p3.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
/* renamed from: com.airbnb.android.p3.models.ReviewSummaryItem */
public abstract class ReviewSummaryItem implements Parcelable {

    /* renamed from: com.airbnb.android.p3.models.ReviewSummaryItem$Builder */
    public static abstract class Builder {
        public abstract ReviewSummaryItem build();

        @JsonProperty
        public abstract Builder label(String str);

        @JsonProperty
        public abstract Builder value(int i);
    }

    public abstract String label();

    public abstract int value();

    public static Builder builder() {
        return new Builder();
    }
}

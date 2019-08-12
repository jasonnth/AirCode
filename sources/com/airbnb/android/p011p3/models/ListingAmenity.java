package com.airbnb.android.p011p3.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
/* renamed from: com.airbnb.android.p3.models.ListingAmenity */
public abstract class ListingAmenity implements Parcelable {

    /* renamed from: com.airbnb.android.p3.models.ListingAmenity$Builder */
    public static abstract class Builder {
        public abstract ListingAmenity build();

        @JsonProperty
        public abstract Builder description(String str);

        @JsonProperty
        /* renamed from: id */
        public abstract Builder mo11029id(long j);

        @JsonProperty
        public abstract Builder name(String str);
    }

    public abstract String description();

    /* renamed from: id */
    public abstract long mo11025id();

    public abstract String name();

    public static Builder builder() {
        return new Builder();
    }
}

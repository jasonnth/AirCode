package com.airbnb.android.lib.paidamenities.models;

import android.os.Parcelable;
import com.airbnb.android.core.models.PaidAmenityCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class PaidAmenityDetails implements Parcelable {

    public static abstract class Builder {
        public abstract PaidAmenityDetails build();

        public abstract Builder setCurrency(String str);

        public abstract Builder setDescription(String str);

        public abstract Builder setIsComplementary(Boolean bool);

        public abstract Builder setListingId(Long l);

        public abstract Builder setPaidAmenityType(PaidAmenityCategory paidAmenityCategory);

        public abstract Builder setPrice(Integer num);

        public abstract Builder setTitle(String str);
    }

    @JsonProperty("currency")
    public abstract String currency();

    @JsonProperty("description")
    public abstract String description();

    @JsonProperty("is_complimentary")
    public abstract Boolean isComplementary();

    @JsonProperty("listing_id")
    public abstract Long listingId();

    @JsonUnwrapped
    public abstract PaidAmenityCategory paidAmenityType();

    @JsonProperty("price")
    public abstract Integer price();

    @JsonProperty("title")
    public abstract String title();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder();
    }
}

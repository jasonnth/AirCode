package com.airbnb.android.core.paidamenities.enums;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class PaidAmenityArguments implements Parcelable {

    public static abstract class Builder {
        public abstract PaidAmenityArguments build();

        @JsonProperty("confirmation_code")
        public abstract Builder confirmationCode(String str);

        @JsonProperty("has_paid_amenity_orders")
        public abstract Builder hasPaidAmenityOrders(boolean z);

        @JsonProperty("listing_id")
        public abstract Builder listingId(long j);

        @JsonProperty("thread_id")
        public abstract Builder threadId(long j);
    }

    public abstract String confirmationCode();

    public abstract boolean hasPaidAmenityOrders();

    public abstract long listingId();

    public abstract long threadId();

    public static Builder builder() {
        return new Builder();
    }
}

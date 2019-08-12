package com.airbnb.android.core.payments.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
public abstract class Bill implements Parcelable {

    @JsonDeserialize(builder = Builder.class)
    public static abstract class BillItem implements Parcelable {

        public static abstract class Builder {
            @JsonProperty("bill_id")
            public abstract Builder billId(long j);

            public abstract BillItem build();

            @JsonProperty("product_id")
            public abstract Builder productId(long j);

            @JsonProperty("product_metadata")
            public abstract Builder productMetadata(ProductMetadata productMetadata);

            @JsonProperty("product_type")
            public abstract Builder productType(String str);

            @JsonProperty("status")
            public abstract Builder status(long j);

            @JsonProperty("token")
            public abstract Builder token(String str);
        }

        @JsonProperty("bill_id")
        public abstract long billId();

        @JsonProperty("product_id")
        public abstract long productId();

        @JsonProperty("product_metadata")
        public abstract ProductMetadata productMetadata();

        @JsonProperty("product_type")
        public abstract String productType();

        @JsonProperty("status")
        public abstract long status();

        @JsonProperty("token")
        public abstract String token();

        public static Builder builder() {
            return new Builder();
        }
    }

    public static abstract class Builder {
        @JsonProperty("bill_items")
        public abstract Builder billItems(List<BillItem> list);

        @JsonProperty("booking_results")
        public abstract Builder bookingResults(List<BookingResult> list);

        public abstract Bill build();

        @JsonProperty("redirect_settings")
        public abstract Builder redirectSettings(RedirectSettings redirectSettings);

        @JsonProperty("status")
        public abstract Builder status(long j);

        @JsonProperty("token")
        public abstract Builder token(String str);

        @JsonProperty("user_id")
        public abstract Builder userId(long j);
    }

    @JsonDeserialize(builder = Builder.class)
    public static abstract class ProductMetadata implements Parcelable {

        public static abstract class Builder {
            public abstract ProductMetadata build();

            @JsonProperty("place_reservation_id")
            public abstract Builder placeReservationId(long j);
        }

        @JsonProperty("place_reservation_id")
        public abstract long placeReservationId();

        public static Builder builder() {
            return new Builder();
        }
    }

    @JsonProperty("bill_items")
    public abstract List<BillItem> billItems();

    @JsonProperty("booking_results")
    public abstract List<BookingResult> bookingResults();

    @JsonProperty("redirect_settings")
    public abstract RedirectSettings redirectSettings();

    @JsonProperty("status")
    public abstract long status();

    @JsonProperty("token")
    public abstract String token();

    @JsonProperty("user_id")
    public abstract long userId();

    public static Builder builder() {
        return new Builder();
    }
}

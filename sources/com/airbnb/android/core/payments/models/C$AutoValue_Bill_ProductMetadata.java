package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.payments.models.Bill.ProductMetadata;
import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_Bill_ProductMetadata reason: invalid class name */
abstract class C$AutoValue_Bill_ProductMetadata extends ProductMetadata {
    private final long placeReservationId;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_Bill_ProductMetadata$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.Bill.ProductMetadata.Builder {
        private Long placeReservationId;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.Bill.ProductMetadata.Builder placeReservationId(long placeReservationId2) {
            this.placeReservationId = Long.valueOf(placeReservationId2);
            return this;
        }

        public ProductMetadata build() {
            String missing = "";
            if (this.placeReservationId == null) {
                missing = missing + " placeReservationId";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Bill_ProductMetadata(this.placeReservationId.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Bill_ProductMetadata(long placeReservationId2) {
        this.placeReservationId = placeReservationId2;
    }

    @JsonProperty("place_reservation_id")
    public long placeReservationId() {
        return this.placeReservationId;
    }

    public String toString() {
        return "ProductMetadata{placeReservationId=" + this.placeReservationId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProductMetadata)) {
            return false;
        }
        if (this.placeReservationId != ((ProductMetadata) o).placeReservationId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (((long) (1 * 1000003)) ^ ((this.placeReservationId >>> 32) ^ this.placeReservationId));
    }
}

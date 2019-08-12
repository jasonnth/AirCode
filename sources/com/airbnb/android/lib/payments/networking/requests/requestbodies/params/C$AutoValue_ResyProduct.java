package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.$AutoValue_ResyProduct reason: invalid class name */
abstract class C$AutoValue_ResyProduct extends ResyProduct {
    private final long activityId;
    private final int numberOfGuests;
    private final String productType;
    private final long resyReservationId;

    /* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.$AutoValue_ResyProduct$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ResyProduct.Builder {
        private Long activityId;
        private Integer numberOfGuests;
        private String productType;
        private Long resyReservationId;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ResyProduct.Builder productType(String productType2) {
            if (productType2 == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ResyProduct.Builder resyReservationId(long resyReservationId2) {
            this.resyReservationId = Long.valueOf(resyReservationId2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ResyProduct.Builder numberOfGuests(int numberOfGuests2) {
            this.numberOfGuests = Integer.valueOf(numberOfGuests2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ResyProduct.Builder activityId(long activityId2) {
            this.activityId = Long.valueOf(activityId2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public ResyProduct autoBuild() {
            String missing = "";
            if (this.productType == null) {
                missing = missing + " productType";
            }
            if (this.resyReservationId == null) {
                missing = missing + " resyReservationId";
            }
            if (this.numberOfGuests == null) {
                missing = missing + " numberOfGuests";
            }
            if (this.activityId == null) {
                missing = missing + " activityId";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ResyProduct(this.productType, this.resyReservationId.longValue(), this.numberOfGuests.intValue(), this.activityId.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ResyProduct(String productType2, long resyReservationId2, int numberOfGuests2, long activityId2) {
        if (productType2 == null) {
            throw new NullPointerException("Null productType");
        }
        this.productType = productType2;
        this.resyReservationId = resyReservationId2;
        this.numberOfGuests = numberOfGuests2;
        this.activityId = activityId2;
    }

    @JsonProperty("product_type")
    public String productType() {
        return this.productType;
    }

    @JsonProperty("resy_availability_id")
    public long resyReservationId() {
        return this.resyReservationId;
    }

    @JsonProperty("number_of_guests")
    public int numberOfGuests() {
        return this.numberOfGuests;
    }

    @JsonProperty("activity_id")
    public long activityId() {
        return this.activityId;
    }

    public String toString() {
        return "ResyProduct{productType=" + this.productType + ", resyReservationId=" + this.resyReservationId + ", numberOfGuests=" + this.numberOfGuests + ", activityId=" + this.activityId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResyProduct)) {
            return false;
        }
        ResyProduct that = (ResyProduct) o;
        if (this.productType.equals(that.productType()) && this.resyReservationId == that.resyReservationId() && this.numberOfGuests == that.numberOfGuests() && this.activityId == that.activityId()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) (((long) (((((int) (((long) (((1 * 1000003) ^ this.productType.hashCode()) * 1000003)) ^ ((this.resyReservationId >>> 32) ^ this.resyReservationId))) * 1000003) ^ this.numberOfGuests) * 1000003)) ^ ((this.activityId >>> 32) ^ this.activityId));
    }
}

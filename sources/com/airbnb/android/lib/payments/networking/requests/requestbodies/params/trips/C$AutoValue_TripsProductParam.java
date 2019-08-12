package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips;

import com.airbnb.android.core.models.TripSecondaryGuest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips.$AutoValue_TripsProductParam reason: invalid class name */
abstract class C$AutoValue_TripsProductParam extends TripsProductParam {
    private final String couponCode;
    private final int numberOfGuests;
    private final String productType;
    private final List<TripSecondaryGuest> secondaryGuestInfos;
    private final long templateId;

    /* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips.$AutoValue_TripsProductParam$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips.TripsProductParam.Builder {
        private String couponCode;
        private Integer numberOfGuests;
        private String productType;
        private List<TripSecondaryGuest> secondaryGuestInfos;
        private Long templateId;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips.TripsProductParam.Builder productType(String productType2) {
            if (productType2 == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips.TripsProductParam.Builder templateId(long templateId2) {
            this.templateId = Long.valueOf(templateId2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips.TripsProductParam.Builder numberOfGuests(int numberOfGuests2) {
            this.numberOfGuests = Integer.valueOf(numberOfGuests2);
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips.TripsProductParam.Builder secondaryGuestInfos(List<TripSecondaryGuest> secondaryGuestInfos2) {
            if (secondaryGuestInfos2 == null) {
                throw new NullPointerException("Null secondaryGuestInfos");
            }
            this.secondaryGuestInfos = secondaryGuestInfos2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips.TripsProductParam.Builder couponCode(String couponCode2) {
            this.couponCode = couponCode2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public TripsProductParam autoBuild() {
            String missing = "";
            if (this.productType == null) {
                missing = missing + " productType";
            }
            if (this.templateId == null) {
                missing = missing + " templateId";
            }
            if (this.numberOfGuests == null) {
                missing = missing + " numberOfGuests";
            }
            if (this.secondaryGuestInfos == null) {
                missing = missing + " secondaryGuestInfos";
            }
            if (missing.isEmpty()) {
                return new AutoValue_TripsProductParam(this.productType, this.templateId.longValue(), this.numberOfGuests.intValue(), this.secondaryGuestInfos, this.couponCode);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_TripsProductParam(String productType2, long templateId2, int numberOfGuests2, List<TripSecondaryGuest> secondaryGuestInfos2, String couponCode2) {
        if (productType2 == null) {
            throw new NullPointerException("Null productType");
        }
        this.productType = productType2;
        this.templateId = templateId2;
        this.numberOfGuests = numberOfGuests2;
        if (secondaryGuestInfos2 == null) {
            throw new NullPointerException("Null secondaryGuestInfos");
        }
        this.secondaryGuestInfos = secondaryGuestInfos2;
        this.couponCode = couponCode2;
    }

    @JsonProperty("product_type")
    public String productType() {
        return this.productType;
    }

    @JsonProperty("mt_scheduled_template_id")
    public long templateId() {
        return this.templateId;
    }

    @JsonProperty("number_of_guests")
    public int numberOfGuests() {
        return this.numberOfGuests;
    }

    @JsonProperty("secondary_guest_infos")
    public List<TripSecondaryGuest> secondaryGuestInfos() {
        return this.secondaryGuestInfos;
    }

    @JsonProperty("coupon_code")
    public String couponCode() {
        return this.couponCode;
    }

    public String toString() {
        return "TripsProductParam{productType=" + this.productType + ", templateId=" + this.templateId + ", numberOfGuests=" + this.numberOfGuests + ", secondaryGuestInfos=" + this.secondaryGuestInfos + ", couponCode=" + this.couponCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TripsProductParam)) {
            return false;
        }
        TripsProductParam that = (TripsProductParam) o;
        if (this.productType.equals(that.productType()) && this.templateId == that.templateId() && this.numberOfGuests == that.numberOfGuests() && this.secondaryGuestInfos.equals(that.secondaryGuestInfos())) {
            if (this.couponCode == null) {
                if (that.couponCode() == null) {
                    return true;
                }
            } else if (this.couponCode.equals(that.couponCode())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((((int) (((long) (((1 * 1000003) ^ this.productType.hashCode()) * 1000003)) ^ ((this.templateId >>> 32) ^ this.templateId))) * 1000003) ^ this.numberOfGuests) * 1000003) ^ this.secondaryGuestInfos.hashCode()) * 1000003) ^ (this.couponCode == null ? 0 : this.couponCode.hashCode());
    }
}

package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.HomesGuestIdentifications;
import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.$AutoValue_HomesProductParam reason: invalid class name */
abstract class C$AutoValue_HomesProductParam extends HomesProductParam {
    private final HomesBusinessTravelProductParam businessTravel;
    private final String code;
    private final String couponCode;
    private final HomesGuestIdentifications guestIdentifications;
    private final String intents;
    private final Boolean isBusinessTrip;
    private final String productType;
    private final HomesRequestInfoParam requestInfo;
    private final HomesReservationDetailsProductParam reservationDetails;
    private final String searchRankingId;

    /* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.$AutoValue_HomesProductParam$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam.Builder {
        private HomesBusinessTravelProductParam businessTravel;
        private String code;
        private String couponCode;
        private HomesGuestIdentifications guestIdentifications;
        private String intents;
        private Boolean isBusinessTrip;
        private String productType;
        private HomesRequestInfoParam requestInfo;
        private HomesReservationDetailsProductParam reservationDetails;
        private String searchRankingId;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam.Builder productType(String productType2) {
            if (productType2 == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam.Builder code(String code2) {
            if (code2 == null) {
                throw new NullPointerException("Null code");
            }
            this.code = code2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam.Builder requestInfo(HomesRequestInfoParam requestInfo2) {
            if (requestInfo2 == null) {
                throw new NullPointerException("Null requestInfo");
            }
            this.requestInfo = requestInfo2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam.Builder reservationDetails(HomesReservationDetailsProductParam reservationDetails2) {
            this.reservationDetails = reservationDetails2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam.Builder couponCode(String couponCode2) {
            this.couponCode = couponCode2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam.Builder intents(String intents2) {
            this.intents = intents2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam.Builder searchRankingId(String searchRankingId2) {
            this.searchRankingId = searchRankingId2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam.Builder isBusinessTrip(Boolean isBusinessTrip2) {
            this.isBusinessTrip = isBusinessTrip2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam.Builder businessTravel(HomesBusinessTravelProductParam businessTravel2) {
            this.businessTravel = businessTravel2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam.Builder guestIdentifications(HomesGuestIdentifications guestIdentifications2) {
            this.guestIdentifications = guestIdentifications2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public HomesProductParam autoBuild() {
            String missing = "";
            if (this.productType == null) {
                missing = missing + " productType";
            }
            if (this.code == null) {
                missing = missing + " code";
            }
            if (this.requestInfo == null) {
                missing = missing + " requestInfo";
            }
            if (missing.isEmpty()) {
                return new AutoValue_HomesProductParam(this.productType, this.code, this.requestInfo, this.reservationDetails, this.couponCode, this.intents, this.searchRankingId, this.isBusinessTrip, this.businessTravel, this.guestIdentifications);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_HomesProductParam(String productType2, String code2, HomesRequestInfoParam requestInfo2, HomesReservationDetailsProductParam reservationDetails2, String couponCode2, String intents2, String searchRankingId2, Boolean isBusinessTrip2, HomesBusinessTravelProductParam businessTravel2, HomesGuestIdentifications guestIdentifications2) {
        if (productType2 == null) {
            throw new NullPointerException("Null productType");
        }
        this.productType = productType2;
        if (code2 == null) {
            throw new NullPointerException("Null code");
        }
        this.code = code2;
        if (requestInfo2 == null) {
            throw new NullPointerException("Null requestInfo");
        }
        this.requestInfo = requestInfo2;
        this.reservationDetails = reservationDetails2;
        this.couponCode = couponCode2;
        this.intents = intents2;
        this.searchRankingId = searchRankingId2;
        this.isBusinessTrip = isBusinessTrip2;
        this.businessTravel = businessTravel2;
        this.guestIdentifications = guestIdentifications2;
    }

    @JsonProperty("product_type")
    public String productType() {
        return this.productType;
    }

    @JsonProperty("code")
    public String code() {
        return this.code;
    }

    @JsonProperty("request_info")
    public HomesRequestInfoParam requestInfo() {
        return this.requestInfo;
    }

    @JsonProperty("reservation_details")
    public HomesReservationDetailsProductParam reservationDetails() {
        return this.reservationDetails;
    }

    @JsonProperty("coupon_code")
    public String couponCode() {
        return this.couponCode;
    }

    @JsonProperty("_intents")
    public String intents() {
        return this.intents;
    }

    @JsonProperty("search_ranking_id")
    public String searchRankingId() {
        return this.searchRankingId;
    }

    @JsonProperty("is_business_trip")
    public Boolean isBusinessTrip() {
        return this.isBusinessTrip;
    }

    @JsonProperty("business_travel")
    public HomesBusinessTravelProductParam businessTravel() {
        return this.businessTravel;
    }

    @JsonProperty("guest_identifications")
    public HomesGuestIdentifications guestIdentifications() {
        return this.guestIdentifications;
    }

    public String toString() {
        return "HomesProductParam{productType=" + this.productType + ", code=" + this.code + ", requestInfo=" + this.requestInfo + ", reservationDetails=" + this.reservationDetails + ", couponCode=" + this.couponCode + ", intents=" + this.intents + ", searchRankingId=" + this.searchRankingId + ", isBusinessTrip=" + this.isBusinessTrip + ", businessTravel=" + this.businessTravel + ", guestIdentifications=" + this.guestIdentifications + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomesProductParam)) {
            return false;
        }
        HomesProductParam that = (HomesProductParam) o;
        if (this.productType.equals(that.productType()) && this.code.equals(that.code()) && this.requestInfo.equals(that.requestInfo()) && (this.reservationDetails != null ? this.reservationDetails.equals(that.reservationDetails()) : that.reservationDetails() == null) && (this.couponCode != null ? this.couponCode.equals(that.couponCode()) : that.couponCode() == null) && (this.intents != null ? this.intents.equals(that.intents()) : that.intents() == null) && (this.searchRankingId != null ? this.searchRankingId.equals(that.searchRankingId()) : that.searchRankingId() == null) && (this.isBusinessTrip != null ? this.isBusinessTrip.equals(that.isBusinessTrip()) : that.isBusinessTrip() == null) && (this.businessTravel != null ? this.businessTravel.equals(that.businessTravel()) : that.businessTravel() == null)) {
            if (this.guestIdentifications == null) {
                if (that.guestIdentifications() == null) {
                    return true;
                }
            } else if (this.guestIdentifications.equals(that.guestIdentifications())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((((1 * 1000003) ^ this.productType.hashCode()) * 1000003) ^ this.code.hashCode()) * 1000003) ^ this.requestInfo.hashCode()) * 1000003) ^ (this.reservationDetails == null ? 0 : this.reservationDetails.hashCode())) * 1000003) ^ (this.couponCode == null ? 0 : this.couponCode.hashCode())) * 1000003) ^ (this.intents == null ? 0 : this.intents.hashCode())) * 1000003) ^ (this.searchRankingId == null ? 0 : this.searchRankingId.hashCode())) * 1000003) ^ (this.isBusinessTrip == null ? 0 : this.isBusinessTrip.hashCode())) * 1000003) ^ (this.businessTravel == null ? 0 : this.businessTravel.hashCode())) * 1000003;
        if (this.guestIdentifications != null) {
            i = this.guestIdentifications.hashCode();
        }
        return h ^ i;
    }
}

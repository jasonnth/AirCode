package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ProductParam;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.HomesGuestIdentifications;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class HomesProductParam extends ProductParam {

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract HomesProductParam autoBuild();

        public abstract Builder businessTravel(HomesBusinessTravelProductParam homesBusinessTravelProductParam);

        public abstract Builder code(String str);

        public abstract Builder couponCode(String str);

        public abstract Builder guestIdentifications(HomesGuestIdentifications homesGuestIdentifications);

        public abstract Builder intents(String str);

        public abstract Builder isBusinessTrip(Boolean bool);

        /* access modifiers changed from: 0000 */
        public abstract Builder productType(String str);

        public abstract Builder requestInfo(HomesRequestInfoParam homesRequestInfoParam);

        public abstract Builder reservationDetails(HomesReservationDetailsProductParam homesReservationDetailsProductParam);

        public abstract Builder searchRankingId(String str);

        public HomesProductParam build() {
            productType(BillProductType.Homes.getServerKey());
            return autoBuild();
        }
    }

    @JsonProperty("business_travel")
    public abstract HomesBusinessTravelProductParam businessTravel();

    @JsonProperty("code")
    public abstract String code();

    @JsonProperty("coupon_code")
    public abstract String couponCode();

    @JsonProperty("guest_identifications")
    public abstract HomesGuestIdentifications guestIdentifications();

    @JsonProperty("_intents")
    public abstract String intents();

    @JsonProperty("is_business_trip")
    public abstract Boolean isBusinessTrip();

    @JsonProperty("request_info")
    public abstract HomesRequestInfoParam requestInfo();

    @JsonProperty("reservation_details")
    public abstract HomesReservationDetailsProductParam reservationDetails();

    @JsonProperty("search_ranking_id")
    public abstract String searchRankingId();

    public static Builder builder() {
        return new Builder();
    }
}

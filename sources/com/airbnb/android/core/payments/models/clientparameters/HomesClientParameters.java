package com.airbnb.android.core.payments.models.clientparameters;

import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.payments.models.BillProductType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
public abstract class HomesClientParameters extends QuickPayParameters {

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract HomesClientParameters autoBuild();

        public abstract Builder businessTripNotes(String str);

        public abstract Builder guestIdentities(List<GuestIdentity> list);

        public abstract Builder isBusinessTrip(Boolean bool);

        public abstract Builder messageToHost(String str);

        public abstract Builder p4Steps(String str);

        /* access modifiers changed from: 0000 */
        public abstract Builder productType(BillProductType billProductType);

        public abstract Builder reservationConfirmationCode(String str);

        public abstract Builder searchRankingId(String str);

        public HomesClientParameters build() {
            productType(BillProductType.Homes);
            return autoBuild();
        }
    }

    @JsonProperty("business_trip_notes")
    public abstract String businessTripNotes();

    @JsonProperty("guest_identities")
    public abstract List<GuestIdentity> guestIdentities();

    @JsonProperty("is_business_trip")
    public abstract Boolean isBusinessTrip();

    @JsonProperty("message_to_host")
    public abstract String messageToHost();

    @JsonProperty("p4_steps")
    public abstract String p4Steps();

    @JsonProperty("reservation_confirmation_code")
    public abstract String reservationConfirmationCode();

    @JsonProperty("search_ranking_id")
    public abstract String searchRankingId();

    public static Builder builder() {
        return new Builder();
    }
}

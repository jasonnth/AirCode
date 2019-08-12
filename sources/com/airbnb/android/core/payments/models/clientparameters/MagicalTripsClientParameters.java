package com.airbnb.android.core.payments.models.clientparameters;

import com.airbnb.android.core.models.TripSecondaryGuest;
import com.airbnb.android.core.payments.models.BillProductType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import java.util.Map;

@JsonDeserialize(builder = Builder.class)
public abstract class MagicalTripsClientParameters extends QuickPayParameters {

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract MagicalTripsClientParameters autoBuild();

        @JsonProperty("guest_address")
        public abstract Builder guestAddress(Map<String, String> map);

        @JsonProperty("guest_count")
        public abstract Builder guestCount(int i);

        /* access modifiers changed from: 0000 */
        public abstract Builder productType(BillProductType billProductType);

        @JsonProperty("secondary_guest_infos")
        public abstract Builder secondaryGuests(ArrayList<TripSecondaryGuest> arrayList);

        @JsonProperty("scheduled_template_id")
        public abstract Builder templateId(long j);

        @JsonProperty("travel_purpose")
        public abstract Builder travelPurpose(Long l);

        public MagicalTripsClientParameters build() {
            productType(BillProductType.Trip);
            return autoBuild();
        }
    }

    public abstract Map<String, String> guestAddress();

    public abstract int guestCount();

    public abstract ArrayList<TripSecondaryGuest> secondaryGuests();

    public abstract long templateId();

    public abstract Long travelPurpose();

    public static Builder builder() {
        return new Builder();
    }
}

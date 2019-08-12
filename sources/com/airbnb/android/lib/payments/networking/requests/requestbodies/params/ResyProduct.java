package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import com.airbnb.android.core.payments.models.BillProductType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ResyProduct extends ProductParam {

    public static abstract class Builder {
        public abstract Builder activityId(long j);

        /* access modifiers changed from: 0000 */
        public abstract ResyProduct autoBuild();

        public abstract Builder numberOfGuests(int i);

        /* access modifiers changed from: 0000 */
        public abstract Builder productType(String str);

        public abstract Builder resyReservationId(long j);

        public ResyProduct build() {
            productType(BillProductType.ResyReservation.getServerKey());
            return autoBuild();
        }
    }

    @JsonProperty("activity_id")
    public abstract long activityId();

    @JsonProperty("number_of_guests")
    public abstract int numberOfGuests();

    @JsonProperty("resy_availability_id")
    public abstract long resyReservationId();

    public static Builder builder() {
        return new Builder();
    }
}

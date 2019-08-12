package com.airbnb.android.core.payments.models.clientparameters;

import com.airbnb.android.core.payments.models.BillProductType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class ResyClientParameters extends QuickPayParameters {

    public static abstract class Builder {
        public abstract Builder activityId(long j);

        /* access modifiers changed from: 0000 */
        public abstract ResyClientParameters autoBuild();

        public abstract Builder numberOfGuests(int i);

        /* access modifiers changed from: 0000 */
        public abstract Builder productType(BillProductType billProductType);

        public abstract Builder reservationId(long j);

        public ResyClientParameters build() {
            productType(BillProductType.ResyReservation);
            return autoBuild();
        }
    }

    public abstract long activityId();

    public abstract int numberOfGuests();

    public abstract long reservationId();

    public static Builder builder() {
        return new Builder();
    }
}

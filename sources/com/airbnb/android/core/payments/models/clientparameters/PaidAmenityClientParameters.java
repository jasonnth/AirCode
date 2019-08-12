package com.airbnb.android.core.payments.models.clientparameters;

import com.airbnb.android.core.payments.models.BillProductType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class PaidAmenityClientParameters extends QuickPayParameters {

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract PaidAmenityClientParameters autoBuild();

        public abstract Builder numberOfUnits(int i);

        public abstract Builder paidAmenityId(long j);

        /* access modifiers changed from: 0000 */
        public abstract Builder productType(BillProductType billProductType);

        public abstract Builder reservationConfirmationCode(String str);

        public PaidAmenityClientParameters build() {
            productType(BillProductType.PaidAmenity);
            return autoBuild();
        }
    }

    public abstract int numberOfUnits();

    public abstract long paidAmenityId();

    public abstract String reservationConfirmationCode();

    public static Builder builder() {
        return new Builder();
    }
}

package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.airbnb.android.core.payments.models.BillProductType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class PaidAmenityBillPriceQuoteRequestBody extends BillPriceQuoteRequestBodyV1 {

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract PaidAmenityBillPriceQuoteRequestBody autoBuild();

        public abstract Builder displayCurrency(String str);

        public abstract Builder isAirbnbCreditIncluded(boolean z);

        public abstract Builder numberOfUnits(int i);

        public abstract Builder paidAmenityId(long j);

        public abstract Builder paymentInstrumentId(String str);

        /* access modifiers changed from: 0000 */
        public abstract Builder productType(String str);

        public abstract Builder reservationConfirmationCode(String str);

        public abstract Builder userId(String str);

        public PaidAmenityBillPriceQuoteRequestBody build() {
            productType(BillProductType.PaidAmenity.getServerKey());
            return autoBuild();
        }
    }

    @JsonProperty("number_of_units")
    public abstract int numberOfUnits();

    @JsonProperty("paid_amenity_id")
    public abstract long paidAmenityId();

    @JsonProperty("reservation_confirmation_code")
    public abstract String reservationConfirmationCode();

    public static Builder builder() {
        return new Builder();
    }
}

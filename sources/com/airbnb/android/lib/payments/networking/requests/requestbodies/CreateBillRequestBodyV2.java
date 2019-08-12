package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CreateBillRequestBodyV2 implements CreateBillRequestBody {
    public static final String API_FORMAT = "for_creation";
    public static final String API_INTENT = "for_quickpay_mobile";
    public static final String API_TRIPS_FORMAT = "for_trip_creation";
    public static final int API_VERSION = 2;

    public static abstract class Builder implements com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBody.Builder {
        public abstract Builder _format(String str);

        public abstract Builder _intents(String str);

        /* access modifiers changed from: 0000 */
        public abstract CreateBillRequestBodyV2 autoBuild();

        public abstract Builder billPriceQuoteKey(String str);

        public abstract Builder idempodenceKey(String str);

        public abstract Builder paymentParam(PaymentParam paymentParam);

        public abstract Builder userId(long j);

        /* access modifiers changed from: 0000 */
        public abstract Builder version(int i);

        public CreateBillRequestBodyV2 build() {
            version(2);
            return autoBuild();
        }
    }

    @JsonProperty("_format")
    public abstract String _format();

    @JsonProperty("_intents")
    public abstract String _intents();

    @JsonProperty("bill_price_quote_key")
    public abstract String billPriceQuoteKey();

    @JsonProperty("idempotence_key")
    public abstract String idempodenceKey();

    @JsonProperty("payment_params")
    public abstract PaymentParam paymentParam();

    @JsonProperty("user_id")
    public abstract long userId();

    @JsonProperty("version")
    public abstract int version();

    public static Builder builder() {
        return new Builder();
    }
}

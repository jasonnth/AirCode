package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.clientparameters.GiftCreditParams;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GiftCreditBillPriceQuoteRequestBody extends BillPriceQuoteRequestBodyV1 {

    public static abstract class Builder {
        public abstract Builder amountMicros(long j);

        public abstract Builder amountMicrosNative(long j);

        public abstract Builder amountMicrosUsd(long j);

        /* access modifiers changed from: 0000 */
        public abstract GiftCreditBillPriceQuoteRequestBody autoBuild();

        public abstract Builder displayCurrency(String str);

        public abstract Builder giftCreditParams(GiftCreditParams giftCreditParams);

        public abstract Builder isAirbnbCreditIncluded(boolean z);

        public abstract Builder nativeCurrency(String str);

        public abstract Builder payment2ProductType(String str);

        public abstract Builder paymentInstrumentId(String str);

        /* access modifiers changed from: 0000 */
        public abstract Builder productType(String str);

        public abstract Builder userId(String str);

        public GiftCreditBillPriceQuoteRequestBody build() {
            productType(BillProductType.GiftCredit.getServerKey());
            return autoBuild();
        }
    }

    @JsonProperty("amount_micros")
    public abstract long amountMicros();

    @JsonProperty("amount_micros_native")
    public abstract long amountMicrosNative();

    @JsonProperty("amount_micros_usd")
    public abstract long amountMicrosUsd();

    @JsonProperty("gift_credit_params")
    public abstract GiftCreditParams giftCreditParams();

    @JsonProperty("native_currency")
    public abstract String nativeCurrency();

    @JsonProperty("payment2_product_type")
    public abstract String payment2ProductType();

    public static Builder builder() {
        return new Builder();
    }
}

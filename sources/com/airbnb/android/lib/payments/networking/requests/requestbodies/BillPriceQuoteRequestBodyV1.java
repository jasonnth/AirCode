package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BillPriceQuoteRequestBodyV1 implements BillPriceQuoteRequestBodyInterface {
    @JsonProperty("display_currency")
    public abstract String displayCurrency();

    @JsonProperty("include_airbnb_credit")
    public abstract boolean isAirbnbCreditIncluded();

    @JsonProperty("payment_instrument_id")
    public abstract String paymentInstrumentId();

    @JsonProperty("product_type")
    public abstract String productType();

    @JsonProperty("user_id")
    public abstract String userId();
}

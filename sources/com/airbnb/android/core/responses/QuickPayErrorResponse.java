package com.airbnb.android.core.responses;

import com.airbnb.airrequest.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuickPayErrorResponse extends ErrorResponse {
    public static final String ERROR_TYPE_CURRENCY_MISMATCH = "payment_method_bill_currency_mismatch";
    @JsonProperty("error_key")
    public String errorKey;
    @JsonProperty("settlement_currency")
    public String settlementCurrency;
}

package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.PaymentOption;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LegacyPaymentOptionResponse extends BaseResponse {
    @JsonProperty("payment_option")
    public PaymentOption paymentOption;
}

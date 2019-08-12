package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.PaymentInstrument;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentInstrumentResponse extends BaseResponse {
    @JsonProperty("payment_instrument")
    public PaymentInstrument paymentInstrument;
}

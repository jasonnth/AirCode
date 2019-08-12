package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ReservationPaymentRedirect;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlipayUrlResponse extends BaseResponse {
    @JsonProperty("reservation_payment_redirect")
    public ReservationPaymentRedirect paymentRedirect;
}

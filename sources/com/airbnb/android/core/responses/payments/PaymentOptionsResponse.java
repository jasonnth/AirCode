package com.airbnb.android.core.responses.payments;

import com.airbnb.android.core.models.PaymentOption;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PaymentOptionsResponse {
    @JsonProperty("payment_options")
    public List<PaymentOption> paymentOptions;
}

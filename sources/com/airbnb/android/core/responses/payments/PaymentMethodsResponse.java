package com.airbnb.android.core.responses.payments;

import com.airbnb.android.core.models.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PaymentMethodsResponse {
    @JsonProperty("payment_methods")
    public List<PaymentMethod> paymentMethods;
}

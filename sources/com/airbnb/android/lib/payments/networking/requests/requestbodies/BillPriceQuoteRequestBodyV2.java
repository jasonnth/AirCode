package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ProductParam;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class BillPriceQuoteRequestBodyV2 implements BillPriceQuoteRequestBodyInterface {
    public static final int version = 2;

    @JsonProperty("payment_params")
    public abstract PaymentParam paymentParams();

    @JsonProperty("products")
    public abstract List<ProductParam> products();

    @JsonProperty("version")
    public abstract int version();
}

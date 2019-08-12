package com.airbnb.android.lib.payments.networking.responses;

import com.airbnb.android.core.payments.models.Bill;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBillResponse {
    @JsonProperty("bill")
    public Bill bill;
}

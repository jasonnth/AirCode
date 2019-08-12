package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.PaymentInstrument;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class GetExistingPayoutMethodResponse extends BaseResponse {
    @JsonProperty("payment_instruments")
    public ArrayList<PaymentInstrument> paymentInstruments;
}

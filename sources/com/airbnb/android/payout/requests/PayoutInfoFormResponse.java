package com.airbnb.android.payout.requests;

import com.airbnb.android.payout.models.PayoutInfoForm;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PayoutInfoFormResponse {
    @JsonProperty("payout_info_forms")
    public List<PayoutInfoForm> payoutInfoForms;
}

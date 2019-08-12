package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.GiftCreditBalance;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GiftCreditBalanceResponse {
    @JsonProperty("gift_credit_balance")
    public GiftCreditBalance giftCreditBalance;
}

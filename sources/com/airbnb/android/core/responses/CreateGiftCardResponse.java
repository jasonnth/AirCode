package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.GiftCreditCheckout;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateGiftCardResponse {
    @JsonProperty("gift_credit_checkout")
    public GiftCreditCheckout giftCreditCheckout;
}

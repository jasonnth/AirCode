package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.GiftCredit;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClaimGiftCardResponse {
    @JsonProperty("gift_credit")
    public GiftCredit giftCredit;
}

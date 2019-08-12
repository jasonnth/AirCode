package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.GiftCreditTemplate;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class GetGiftCreditTemplatesResponse {
    @JsonProperty("gift_credit_templates")
    public ArrayList<GiftCreditTemplate> giftCreditTemplates;
}

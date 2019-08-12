package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.GiftCreditBalanceResponse;
import java.lang.reflect.Type;

public class GiftCreditBalanceRequest extends BaseRequestV2<GiftCreditBalanceResponse> {
    private final long userId;

    public GiftCreditBalanceRequest(long userId2) {
        this.userId = userId2;
    }

    public String getPath() {
        return "gift_credit_balances/" + this.userId;
    }

    public Type successResponseType() {
        return GiftCreditBalanceResponse.class;
    }
}

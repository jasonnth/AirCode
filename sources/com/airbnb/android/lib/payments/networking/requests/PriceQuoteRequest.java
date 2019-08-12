package com.airbnb.android.lib.payments.networking.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.PriceQuote;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.BillPriceQuoteRequestBodyInterface;
import java.lang.reflect.Type;

public class PriceQuoteRequest extends BaseRequestV2<PriceQuote> {
    private final BillPriceQuoteRequestBodyInterface body;

    public PriceQuoteRequest(BillPriceQuoteRequestBodyInterface body2) {
        this.body = body2;
    }

    public Type successResponseType() {
        return PriceQuote.class;
    }

    public String getPath() {
        return "bill_price_quotes";
    }

    public BillPriceQuoteRequestBodyInterface getBody() {
        return this.body;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}

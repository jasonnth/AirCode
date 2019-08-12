package com.airbnb.android.core.requests.payments;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import java.lang.reflect.Type;

public class QueryPaymentInstrumentRequest extends BaseRequestV2<PaymentInstrumentResponse> {

    /* renamed from: id */
    private final long f1089id;

    public static QueryPaymentInstrumentRequest forAlipayQuery(long id) {
        return new QueryPaymentInstrumentRequest(id);
    }

    private QueryPaymentInstrumentRequest(long id) {
        this.f1089id = id;
    }

    public String getPath() {
        return "payment_instruments/" + this.f1089id;
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Type successResponseType() {
        return PaymentInstrumentResponse.class;
    }
}

package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import java.lang.reflect.Type;

public class DeletePaymentInstrumentRequest extends BaseRequestV2<PaymentInstrumentResponse> {

    /* renamed from: id */
    private final long f8488id;

    public DeletePaymentInstrumentRequest(long id) {
        this.f8488id = id;
    }

    public String getPath() {
        return "payment_instruments/" + this.f8488id;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Type successResponseType() {
        return PaymentInstrumentResponse.class;
    }
}

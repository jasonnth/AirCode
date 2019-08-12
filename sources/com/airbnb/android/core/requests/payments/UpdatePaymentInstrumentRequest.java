package com.airbnb.android.core.requests.payments;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.requests.payments.requestbodies.UpdatePaymentInstrumentRequestBody;
import com.airbnb.android.core.requests.payments.requestbodies.UpdatePaymentInstrumentRequestBody.AlipayVerificationBody;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import java.lang.reflect.Type;

public class UpdatePaymentInstrumentRequest extends BaseRequestV2<PaymentInstrumentResponse> {
    private final UpdatePaymentInstrumentRequestBody body;
    private final long paymentInstrumentId;

    public static UpdatePaymentInstrumentRequest forAlipayVerification(long paymentInstrumentId2, AlipayVerificationBody alipayVerificationBody) {
        return new UpdatePaymentInstrumentRequest(paymentInstrumentId2, alipayVerificationBody);
    }

    private UpdatePaymentInstrumentRequest(long paymentInstrumentId2, UpdatePaymentInstrumentRequestBody body2) {
        this.body = body2;
        this.paymentInstrumentId = paymentInstrumentId2;
    }

    public Type successResponseType() {
        return PaymentInstrumentResponse.class;
    }

    public String getPath() {
        return "payment_instruments/" + this.paymentInstrumentId;
    }

    public UpdatePaymentInstrumentRequestBody getBody() {
        return this.body;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }
}

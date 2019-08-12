package com.airbnb.android.core.requests.payments;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.AlipayBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.AlipayV2Body;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.AndroidPayBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.BankTransferLegacyPayoutBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.BusinessTravelBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.CreditCardBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.DigitalRiverCreditCardBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.PayPalBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.PayPalLegacyPayoutBody;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import java.lang.reflect.Type;

public class CreatePaymentInstrumentRequest extends BaseRequestV2<PaymentInstrumentResponse> {
    private final CreatePaymentInstrumentRequestBody body;

    public static CreatePaymentInstrumentRequest forAlipay(AlipayBody body2) {
        return new CreatePaymentInstrumentRequest(body2);
    }

    public static CreatePaymentInstrumentRequest forAlipayV2() {
        return new CreatePaymentInstrumentRequest(new AlipayV2Body());
    }

    public static CreatePaymentInstrumentRequest forLegacyPayPalPayout(PayPalLegacyPayoutBody body2) {
        return new CreatePaymentInstrumentRequest(body2);
    }

    public static CreatePaymentInstrumentRequest forLegacyBankTransferPayout(BankTransferLegacyPayoutBody body2) {
        return new CreatePaymentInstrumentRequest(body2);
    }

    public static CreatePaymentInstrumentRequest forCreditCard(CreditCardBody body2) {
        return new CreatePaymentInstrumentRequest(body2);
    }

    public static CreatePaymentInstrumentRequest forPayPal(PayPalBody body2) {
        return new CreatePaymentInstrumentRequest(body2);
    }

    public static CreatePaymentInstrumentRequest forAndroidPay(AndroidPayBody body2) {
        return new CreatePaymentInstrumentRequest(body2);
    }

    public static CreatePaymentInstrumentRequest forBusinessTravel(BusinessTravelBody body2) {
        return new CreatePaymentInstrumentRequest(body2);
    }

    public static CreatePaymentInstrumentRequest forDigitalRiverCreditCard(DigitalRiverCreditCardBody body2) {
        return new CreatePaymentInstrumentRequest(body2);
    }

    private CreatePaymentInstrumentRequest(CreatePaymentInstrumentRequestBody body2) {
        this.body = body2;
    }

    public String getPath() {
        return "payment_instruments";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return PaymentInstrumentResponse.class;
    }

    public CreatePaymentInstrumentRequestBody getBody() {
        return this.body;
    }
}

package com.airbnb.android.lib.payments.braintree;

import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.models.payments.PayPalInstrument;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.PayPalBody;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.PayPal;
import java.util.List;
import p032rx.Observer;

public class PayPalTokenizer implements PayPalApi {
    private final BraintreeFragment braintreeFragment;
    private PayPalListener payPalListener;
    private final RequestManager requestManager;
    final RequestListener<PaymentInstrumentResponse> vaultPayPalListener = new C0699RL().onResponse(PayPalTokenizer$$Lambda$1.lambdaFactory$(this)).onError(PayPalTokenizer$$Lambda$2.lambdaFactory$(this)).build();

    public interface PayPalListener {
        void onPayPalVaultError(NetworkException networkException);

        void onPayPalVaulted(PaymentInstrument paymentInstrument);
    }

    public PayPalTokenizer(BraintreeFragment braintreeFragment2, RequestManager requestManager2, PayPalListener payPalListener2) {
        requestManager2.subscribe(this);
        this.braintreeFragment = braintreeFragment2;
        this.requestManager = requestManager2;
        this.payPalListener = payPalListener2;
    }

    public void tokenize(List<String> scopes) {
        PayPal.authorizeAccount(this.braintreeFragment, scopes);
    }

    public void vaultPayPalInstrument(PayPalInstrument payPalInstrument) {
        CreatePaymentInstrumentRequest.forPayPal(createPayPalBody(payPalInstrument)).withListener((Observer) this.vaultPayPalListener).execute(this.requestManager);
    }

    private PayPalBody createPayPalBody(PayPalInstrument payPalInstrument) {
        return PayPalBody.make().paymentMethodNonce(payPalInstrument.getNonce()).deviceData(payPalInstrument.getDeviceData()).build();
    }

    private PayPalListener getPayPalListener() {
        return this.payPalListener;
    }
}

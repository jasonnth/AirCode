package com.airbnb.android.lib.payments.paymentinstruments;

import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import p032rx.Observer;

public class PaymentInstrumentsDelegate implements PaymentInstrumentsApi {
    private final PaymentInstrumentsDelegateListener delegateListener;
    final RequestListener<PaymentInstrumentResponse> digitalRiverVaultListener = new C0699RL().onResponse(PaymentInstrumentsDelegate$$Lambda$1.lambdaFactory$(this)).onError(PaymentInstrumentsDelegate$$Lambda$2.lambdaFactory$(this)).build();
    private final RequestManager requestManager;

    public interface PaymentInstrumentsDelegateListener {
        void onPaymentInstrumentCreated(PaymentInstrument paymentInstrument);

        void onPaymentInstrumentFailure(NetworkException networkException);
    }

    public PaymentInstrumentsDelegate(RequestManager requestManager2, PaymentInstrumentsDelegateListener delegateListener2) {
        requestManager2.subscribe(this);
        this.requestManager = requestManager2;
        this.delegateListener = delegateListener2;
    }

    public void createPaymentInstrument(CreatePaymentInstrumentRequest request) {
        request.withListener((Observer) this.digitalRiverVaultListener).execute(this.requestManager);
    }

    private PaymentInstrumentsDelegateListener getDelegateListener() {
        return this.delegateListener;
    }
}

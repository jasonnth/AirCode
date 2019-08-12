package com.airbnb.android.lib.payments.paymentoptions;

import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.requests.payments.PaymentOptionsRequest;
import com.airbnb.android.core.responses.payments.PaymentOptionsResponse;
import java.util.List;
import p032rx.Observer;

public class PaymentOptionsDelegate implements PaymentOptionsApi {
    private final PaymentOptionsDelegateListener paymentOptionsDelegateListener;
    final RequestListener<PaymentOptionsResponse> paymentOptionsListener = new C0699RL().onResponse(PaymentOptionsDelegate$$Lambda$1.lambdaFactory$(this)).onError(PaymentOptionsDelegate$$Lambda$2.lambdaFactory$(this)).build();
    private final RequestManager requestManager;

    public interface PaymentOptionsDelegateListener {
        void onPaymentOptionsRequestError(NetworkException networkException);

        void onPaymentOptionsRequestSuccess(List<PaymentOption> list);
    }

    public PaymentOptionsDelegate(RequestManager requestManager2, PaymentOptionsDelegateListener paymentOptionsDelegateListener2) {
        requestManager2.subscribe(this);
        this.requestManager = requestManager2;
        this.paymentOptionsDelegateListener = paymentOptionsDelegateListener2;
    }

    public void getPaymentOptions(String productType, String countryCode, boolean shouldIncludeBusinessTravel) {
        PaymentOptionsRequest.make(productType, countryCode, shouldIncludeBusinessTravel).withListener((Observer) this.paymentOptionsListener).execute(this.requestManager);
    }

    private PaymentOptionsDelegateListener getPaymentOptionsDelegateListener() {
        return this.paymentOptionsDelegateListener;
    }
}

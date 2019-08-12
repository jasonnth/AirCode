package com.airbnb.android.lib.payments.braintree;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;

public interface BraintreeCreditCardApi {
    BraintreeCreditCard buildCreditCard(String str, String str2, String str3, String str4, String str5);

    void tokenize(BraintreeCreditCard braintreeCreditCard);

    void tokenizeCvv(BraintreeCreditCard braintreeCreditCard);

    void vaultCreditCard(BaseRequestListener<PaymentInstrumentResponse> baseRequestListener, RequestManager requestManager, BraintreeCreditCard braintreeCreditCard);
}

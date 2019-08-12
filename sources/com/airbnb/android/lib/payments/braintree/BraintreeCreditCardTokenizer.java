package com.airbnb.android.lib.payments.braintree;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.CreditCardDetails;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.CreditCardBody;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.Card;
import p032rx.Observer;

public class BraintreeCreditCardTokenizer implements BraintreeCreditCardApi {
    private final BraintreeFragment braintreeFragment;

    public BraintreeCreditCardTokenizer(BraintreeFragment braintreeFragment2) {
        this.braintreeFragment = braintreeFragment2;
    }

    public BraintreeCreditCard buildCreditCard(String cardNumber, String expiryMonth, String expiryYear, String cvv, String postalCode) {
        return CreditCardDetails.builder().cardNumber(cardNumber).expirationMonth(expiryMonth).expirationYear(expiryYear).cvv(cvv).postalCode(postalCode).build().toCreditCard();
    }

    public void tokenize(BraintreeCreditCard creditCard) {
        Card.tokenize(this.braintreeFragment, creditCard.buildCard());
    }

    public void tokenizeCvv(BraintreeCreditCard creditCard) {
        Card.tokenize(this.braintreeFragment, creditCard.buildCard());
    }

    public void vaultCreditCard(BaseRequestListener<PaymentInstrumentResponse> requestListener, RequestManager requestManager, BraintreeCreditCard creditCard) {
        CreatePaymentInstrumentRequest.forCreditCard(CreditCardBody.make().paymentMethodNonce(creditCard.getNonce()).postalCode(creditCard.getPostalCode()).country(creditCard.getCountry()).build()).withListener((Observer) requestListener).execute(requestManager);
    }
}

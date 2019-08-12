package com.airbnb.android.lib.payments.quickpay.fragments;

import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PaymentMethodNonce;

final /* synthetic */ class GiftCardQuickPayFragment$$Lambda$1 implements PaymentMethodNonceCreatedListener {
    private final GiftCardQuickPayFragment arg$1;

    private GiftCardQuickPayFragment$$Lambda$1(GiftCardQuickPayFragment giftCardQuickPayFragment) {
        this.arg$1 = giftCardQuickPayFragment;
    }

    public static PaymentMethodNonceCreatedListener lambdaFactory$(GiftCardQuickPayFragment giftCardQuickPayFragment) {
        return new GiftCardQuickPayFragment$$Lambda$1(giftCardQuickPayFragment);
    }

    public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
        GiftCardQuickPayFragment.lambda$new$0(this.arg$1, paymentMethodNonce);
    }
}

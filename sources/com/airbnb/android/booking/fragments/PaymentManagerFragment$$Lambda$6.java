package com.airbnb.android.booking.fragments;

import com.airbnb.android.core.models.payments.AndroidPayInstrument;
import com.braintreepayments.api.interfaces.TokenizationParametersListener;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import java.util.Collection;

final /* synthetic */ class PaymentManagerFragment$$Lambda$6 implements TokenizationParametersListener {
    private final PaymentManagerFragment arg$1;
    private final AndroidPayInstrument arg$2;
    private final int arg$3;

    private PaymentManagerFragment$$Lambda$6(PaymentManagerFragment paymentManagerFragment, AndroidPayInstrument androidPayInstrument, int i) {
        this.arg$1 = paymentManagerFragment;
        this.arg$2 = androidPayInstrument;
        this.arg$3 = i;
    }

    public static TokenizationParametersListener lambdaFactory$(PaymentManagerFragment paymentManagerFragment, AndroidPayInstrument androidPayInstrument, int i) {
        return new PaymentManagerFragment$$Lambda$6(paymentManagerFragment, androidPayInstrument, i);
    }

    public void onResult(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, Collection collection) {
        PaymentManagerFragment.lambda$loadMaskedAndroidPayWallet$0(this.arg$1, this.arg$2, this.arg$3, paymentMethodTokenizationParameters, collection);
    }
}

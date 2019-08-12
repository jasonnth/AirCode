package com.airbnb.android.lib.payments.digitalriver;

import android.webkit.ValueCallback;

final /* synthetic */ class DigitalRiverTokenizer$$Lambda$1 implements ValueCallback {
    private final DigitalRiverTokenizer arg$1;

    private DigitalRiverTokenizer$$Lambda$1(DigitalRiverTokenizer digitalRiverTokenizer) {
        this.arg$1 = digitalRiverTokenizer;
    }

    public static ValueCallback lambdaFactory$(DigitalRiverTokenizer digitalRiverTokenizer) {
        return new DigitalRiverTokenizer$$Lambda$1(digitalRiverTokenizer);
    }

    public void onReceiveValue(Object obj) {
        this.arg$1.onCreditCardTokenized((String) obj);
    }
}

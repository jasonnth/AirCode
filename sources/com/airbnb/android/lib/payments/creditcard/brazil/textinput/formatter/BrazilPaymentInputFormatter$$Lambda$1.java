package com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter;

import com.airbnb.p027n2.components.PaymentInputLayout;
import com.google.common.base.Predicate;

final /* synthetic */ class BrazilPaymentInputFormatter$$Lambda$1 implements Predicate {
    private static final BrazilPaymentInputFormatter$$Lambda$1 instance = new BrazilPaymentInputFormatter$$Lambda$1();

    private BrazilPaymentInputFormatter$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return BrazilPaymentInputFormatter.lambda$areInputsValid$0((PaymentInputLayout) obj);
    }
}

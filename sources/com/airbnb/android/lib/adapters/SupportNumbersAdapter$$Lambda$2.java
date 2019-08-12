package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.models.SupportPhoneNumber;
import com.google.common.base.Function;

final /* synthetic */ class SupportNumbersAdapter$$Lambda$2 implements Function {
    private final SupportNumbersAdapter arg$1;

    private SupportNumbersAdapter$$Lambda$2(SupportNumbersAdapter supportNumbersAdapter) {
        this.arg$1 = supportNumbersAdapter;
    }

    public static Function lambdaFactory$(SupportNumbersAdapter supportNumbersAdapter) {
        return new SupportNumbersAdapter$$Lambda$2(supportNumbersAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.createCallRow(((SupportPhoneNumber) obj).getCountry(), ((SupportPhoneNumber) obj).getNumber());
    }
}

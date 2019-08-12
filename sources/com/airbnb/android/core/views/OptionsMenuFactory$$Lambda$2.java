package com.airbnb.android.core.views;

import p032rx.functions.Func1;

final /* synthetic */ class OptionsMenuFactory$$Lambda$2 implements Func1 {
    private final OptionsMenuFactory arg$1;
    private final Func1 arg$2;

    private OptionsMenuFactory$$Lambda$2(OptionsMenuFactory optionsMenuFactory, Func1 func1) {
        this.arg$1 = optionsMenuFactory;
        this.arg$2 = func1;
    }

    public static Func1 lambdaFactory$(OptionsMenuFactory optionsMenuFactory, Func1 func1) {
        return new OptionsMenuFactory$$Lambda$2(optionsMenuFactory, func1);
    }

    public Object call(Object obj) {
        return this.arg$1.context.getString(((Integer) this.arg$2.call(obj)).intValue());
    }
}

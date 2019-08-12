package com.airbnb.android.core.views;

import com.google.common.base.Function;

final /* synthetic */ class OptionsMenuFactory$$Lambda$3 implements Function {
    private final OptionsMenuFactory arg$1;

    private OptionsMenuFactory$$Lambda$3(OptionsMenuFactory optionsMenuFactory) {
        this.arg$1 = optionsMenuFactory;
    }

    public static Function lambdaFactory$(OptionsMenuFactory optionsMenuFactory) {
        return new OptionsMenuFactory$$Lambda$3(optionsMenuFactory);
    }

    public Object apply(Object obj) {
        return OptionsMenuFactory.lambda$buildAndShow$2(this.arg$1, obj);
    }
}

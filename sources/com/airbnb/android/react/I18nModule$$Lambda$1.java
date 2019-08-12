package com.airbnb.android.react;

import com.squareup.otto.Bus;

final /* synthetic */ class I18nModule$$Lambda$1 implements Runnable {
    private final I18nModule arg$1;
    private final Bus arg$2;

    private I18nModule$$Lambda$1(I18nModule i18nModule, Bus bus) {
        this.arg$1 = i18nModule;
        this.arg$2 = bus;
    }

    public static Runnable lambdaFactory$(I18nModule i18nModule, Bus bus) {
        return new I18nModule$$Lambda$1(i18nModule, bus);
    }

    public void run() {
        this.arg$2.register(this.arg$1);
    }
}

package com.airbnb.android.lib.coldstart;

import p032rx.functions.Action1;

final /* synthetic */ class PreloadExecutor$$Lambda$3 implements Action1 {
    private final PreloadExecutor arg$1;

    private PreloadExecutor$$Lambda$3(PreloadExecutor preloadExecutor) {
        this.arg$1 = preloadExecutor;
    }

    public static Action1 lambdaFactory$(PreloadExecutor preloadExecutor) {
        return new PreloadExecutor$$Lambda$3(preloadExecutor);
    }

    public void call(Object obj) {
        PreloadExecutor.lambda$execute$2(this.arg$1, (Boolean) obj);
    }
}

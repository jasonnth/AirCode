package com.airbnb.android.lib.coldstart;

import java.util.concurrent.Callable;

final /* synthetic */ class PreloadExecutor$$Lambda$4 implements Callable {
    private final Preloader arg$1;

    private PreloadExecutor$$Lambda$4(Preloader preloader) {
        this.arg$1 = preloader;
    }

    public static Callable lambdaFactory$(Preloader preloader) {
        return new PreloadExecutor$$Lambda$4(preloader);
    }

    public Object call() {
        return this.arg$1.preload();
    }
}

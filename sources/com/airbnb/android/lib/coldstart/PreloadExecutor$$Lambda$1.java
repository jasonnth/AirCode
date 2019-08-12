package com.airbnb.android.lib.coldstart;

import p032rx.Observable;
import p032rx.functions.Func1;
import p032rx.schedulers.Schedulers;

final /* synthetic */ class PreloadExecutor$$Lambda$1 implements Func1 {
    private static final PreloadExecutor$$Lambda$1 instance = new PreloadExecutor$$Lambda$1();

    private PreloadExecutor$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return Observable.fromCallable(PreloadExecutor$$Lambda$4.lambdaFactory$((Preloader) obj)).subscribeOn(Schedulers.m4048io());
    }
}

package com.airbnb.android.lib.coldstart;

import java.util.List;
import p032rx.Observable;
import p032rx.functions.Func1;

final /* synthetic */ class PreloadExecutor$$Lambda$2 implements Func1 {
    private static final PreloadExecutor$$Lambda$2 instance = new PreloadExecutor$$Lambda$2();

    private PreloadExecutor$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return Observable.merge((Iterable<? extends Observable<? extends T>>) (List) obj);
    }
}

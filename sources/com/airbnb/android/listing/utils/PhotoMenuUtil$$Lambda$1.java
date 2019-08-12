package com.airbnb.android.listing.utils;

import p032rx.functions.Func1;

final /* synthetic */ class PhotoMenuUtil$$Lambda$1 implements Func1 {
    private static final PhotoMenuUtil$$Lambda$1 instance = new PhotoMenuUtil$$Lambda$1();

    private PhotoMenuUtil$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return Integer.valueOf(((Action) obj).optionStringRes);
    }
}

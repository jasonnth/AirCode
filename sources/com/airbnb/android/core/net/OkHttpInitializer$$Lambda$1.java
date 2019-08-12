package com.airbnb.android.core.net;

import okhttp3.OkHttpClient.Builder;

final /* synthetic */ class OkHttpInitializer$$Lambda$1 implements OkHttpInitializer {
    private static final OkHttpInitializer$$Lambda$1 instance = new OkHttpInitializer$$Lambda$1();

    private OkHttpInitializer$$Lambda$1() {
    }

    public static OkHttpInitializer lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return OkHttpInitializer$.lambda$static$0((Builder) obj);
    }
}

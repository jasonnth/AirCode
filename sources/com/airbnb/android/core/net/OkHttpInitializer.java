package com.airbnb.android.core.net;

import okhttp3.OkHttpClient.Builder;
import p032rx.functions.Func1;

public interface OkHttpInitializer extends Func1<Builder, Builder> {
    public static final OkHttpInitializer IDENTITY = OkHttpInitializer$$Lambda$1.lambdaFactory$();
}

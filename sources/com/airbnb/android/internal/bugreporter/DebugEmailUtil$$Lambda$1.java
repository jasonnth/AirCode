package com.airbnb.android.internal.bugreporter;

import android.content.Context;
import com.google.common.base.Function;

final /* synthetic */ class DebugEmailUtil$$Lambda$1 implements Function {
    private final Context arg$1;

    private DebugEmailUtil$$Lambda$1(Context context) {
        this.arg$1 = context;
    }

    public static Function lambdaFactory$(Context context) {
        return new DebugEmailUtil$$Lambda$1(context);
    }

    public Object apply(Object obj) {
        return DebugEmailUtil.makeExternalUri(this.arg$1, (String) obj);
    }
}

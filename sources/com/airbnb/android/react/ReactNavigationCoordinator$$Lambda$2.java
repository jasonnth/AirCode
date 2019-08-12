package com.airbnb.android.react;

import android.content.Context;
import com.facebook.react.bridge.ReadableMap;
import com.google.common.base.Function;

final /* synthetic */ class ReactNavigationCoordinator$$Lambda$2 implements Function {
    private final Context arg$1;
    private final ReadableMap arg$2;

    private ReactNavigationCoordinator$$Lambda$2(Context context, ReadableMap readableMap) {
        this.arg$1 = context;
        this.arg$2 = readableMap;
    }

    public static Function lambdaFactory$(Context context, ReadableMap readableMap) {
        return new ReactNavigationCoordinator$$Lambda$2(context, readableMap);
    }

    public Object apply(Object obj) {
        return ((ReactExposedActivityParams) obj).toIntent(this.arg$1, this.arg$2);
    }
}

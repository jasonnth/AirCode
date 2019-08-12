package com.airbnb.android.core;

import android.content.Context;
import javax.inject.Provider;

final /* synthetic */ class DeviceInfo$$Lambda$2 implements Provider {
    private final Context arg$1;

    private DeviceInfo$$Lambda$2(Context context) {
        this.arg$1 = context;
    }

    public static Provider lambdaFactory$(Context context) {
        return new DeviceInfo$$Lambda$2(context);
    }

    public Object get() {
        return DeviceInfo.lambda$new$1(this.arg$1);
    }
}

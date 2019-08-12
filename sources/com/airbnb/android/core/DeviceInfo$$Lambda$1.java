package com.airbnb.android.core;

import android.content.Context;
import android.provider.Settings.Secure;
import javax.inject.Provider;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

final /* synthetic */ class DeviceInfo$$Lambda$1 implements Provider {
    private final Context arg$1;

    private DeviceInfo$$Lambda$1(Context context) {
        this.arg$1 = context;
    }

    public static Provider lambdaFactory$(Context context) {
        return new DeviceInfo$$Lambda$1(context);
    }

    public Object get() {
        return Secure.getString(this.arg$1.getContentResolver(), JPushReportInterface.ANDROID_ID);
    }
}

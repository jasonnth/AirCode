package com.airbnb.android.core;

import android.content.Context;
import android.telephony.TelephonyManager;
import javax.inject.Provider;

final /* synthetic */ class DeviceInfo$$Lambda$3 implements Provider {
    private final Context arg$1;

    private DeviceInfo$$Lambda$3(Context context) {
        this.arg$1 = context;
    }

    public static Provider lambdaFactory$(Context context) {
        return new DeviceInfo$$Lambda$3(context);
    }

    public Object get() {
        return ((TelephonyManager) this.arg$1.getSystemService("phone")).getSimCountryIso();
    }
}

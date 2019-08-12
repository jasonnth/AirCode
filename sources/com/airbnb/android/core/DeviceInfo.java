package com.airbnb.android.core;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.MiscUtils;
import dagger.Lazy;
import dagger.internal.DoubleCheck;

public class DeviceInfo {
    private final Lazy<String> androidID;
    private final Lazy<String> deviceCountry;
    private final Lazy<String> userAgent;

    public DeviceInfo(Context context) {
        this.androidID = DoubleCheck.lazy(DeviceInfo$$Lambda$1.lambdaFactory$(context));
        this.userAgent = DoubleCheck.lazy(DeviceInfo$$Lambda$2.lambdaFactory$(context));
        this.deviceCountry = DoubleCheck.lazy(DeviceInfo$$Lambda$3.lambdaFactory$(context));
    }

    static /* synthetic */ String lambda$new$1(Context context) {
        String str;
        String telephonyOperator = MiscUtils.getTelephonyOperatorName(context);
        StringBuilder append = new StringBuilder().append("Airbnb/").append(BuildHelper.versionCode()).append(" Android/").append(VERSION.RELEASE).append(" Device/").append(Build.MANUFACTURER).append("_").append(Build.MODEL).append(" Carrier/");
        if (TextUtils.isEmpty(telephonyOperator)) {
            telephonyOperator = "None";
        }
        StringBuilder append2 = append.append(telephonyOperator).append(" Type/");
        if (MiscUtils.isTabletScreen(context)) {
            str = "Tablet";
        } else {
            str = "Phone";
        }
        return append2.append(str).toString();
    }

    public String getAndroidId() {
        return (String) this.androidID.get();
    }

    public String getUserAgent() {
        return (String) this.userAgent.get();
    }

    public String getDeviceCountry() {
        return (String) this.deviceCountry.get();
    }
}

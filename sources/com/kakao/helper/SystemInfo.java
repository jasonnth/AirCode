package com.kakao.helper;

import android.os.Build;
import android.os.Build.VERSION;
import java.util.Locale;

public class SystemInfo {
    private static final String COUNTRY_CODE = Locale.getDefault().getCountry().toUpperCase();
    private static final String DEVICE_MODEL = Build.MODEL.replaceAll("\\s", "-").toUpperCase();
    private static String KA_HEADER;
    private static final String LANGUAGE = Locale.getDefault().getLanguage().toLowerCase();
    private static final int OS_VERSION = VERSION.SDK_INT;

    public static String getKAHeader() {
        if (KA_HEADER == null) {
            KA_HEADER = "sdk/1.0.40 os/android-" + OS_VERSION + " " + "lang/" + LANGUAGE + "-" + COUNTRY_CODE + " " + "device/" + DEVICE_MODEL;
        }
        return KA_HEADER;
    }
}

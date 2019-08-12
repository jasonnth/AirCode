package com.airbnb.android.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import java.util.Locale;

public class LocaleUtil {
    @TargetApi(24)
    public static String getDisplayCountryFromCountryCode(Context context, String countryCode) {
        return new Locale("", countryCode).getDisplayName(getCurrentDeviceLocale(context));
    }

    public static String getDeviceDisplayLanguage(Context context) {
        return getCurrentDeviceLocale(context).getDisplayLanguage();
    }

    public static String getDeviceLanguage(Context context) {
        return getCurrentDeviceLocale(context).getLanguage();
    }

    @TargetApi(24)
    public static Locale getCurrentDeviceLocale(Context context) {
        if (AndroidVersion.isAtLeastNougat()) {
            return context.getResources().getConfiguration().getLocales().get(0);
        }
        return context.getResources().getConfiguration().locale;
    }

    public static String getString(Locale locale) {
        String localeStr = locale.getLanguage();
        String country = locale.getCountry();
        if (!TextUtils.isEmpty(country)) {
            return localeStr + "-" + country;
        }
        return localeStr;
    }
}

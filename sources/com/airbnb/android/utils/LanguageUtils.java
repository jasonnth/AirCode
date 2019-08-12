package com.airbnb.android.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

public final class LanguageUtils {
    private static final String[] ASIA_COUNTRIES = {Locale.CHINA.getCountry(), Locale.JAPAN.getCountry(), Locale.KOREA.getCountry(), Locale.TAIWAN.getCountry(), "HK", "ID", AirbnbConstants.COUNTRY_CODE_INDIA, "MY", "PH", "SG", "TH", "VN", ""};
    private static final Set<String> TRADITIONAL_CHINESE = ImmutableSet.m1300of("tw", "mo", "hk");

    private LanguageUtils() {
    }

    public static String getLanguage() {
        String result = Locale.getDefault().getLanguage();
        if (!result.equalsIgnoreCase(AirbnbConstants.LANGUAGE_CODE_CHINESE) || !TRADITIONAL_CHINESE.contains(Locale.getDefault().getCountry().toLowerCase())) {
            return result;
        }
        return result + "-TW";
    }

    @TargetApi(24)
    public static Locale getContextLocale(Context context) {
        if (VERSION.SDK_INT >= 24) {
            return context.getResources().getConfiguration().getLocales().get(0);
        }
        return context.getResources().getConfiguration().locale;
    }

    public static String getDisplayLanguage() {
        return Locale.getDefault().getDisplayLanguage();
    }

    public static boolean isUserPreferredLanguageChinese() {
        return AirbnbConstants.LANGUAGE_CODE_CHINESE.equalsIgnoreCase(Locale.getDefault().getLanguage());
    }

    public static boolean isUserPreferredLanguageSimplifiedChinese() {
        return AirbnbConstants.LANGUAGE_CODE_CHINESE.equalsIgnoreCase(getLanguage());
    }

    public static boolean isAsiaLocaleCountry() {
        return new ArrayList<>(Arrays.asList(ASIA_COUNTRIES)).contains(Locale.getDefault().getCountry().toUpperCase());
    }

    public static boolean isUsingCJKLanguage() {
        String language = Locale.getDefault().getLanguage();
        return AirbnbConstants.LANGUAGE_CODE_CHINESE.equalsIgnoreCase(language) || AirbnbConstants.LANGUAGE_CODE_KOREA.equalsIgnoreCase(language) || AirbnbConstants.LANGUAGE_CODE_JAPAN.equalsIgnoreCase(language);
    }
}

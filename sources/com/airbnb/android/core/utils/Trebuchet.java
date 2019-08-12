package com.airbnb.android.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.NumberUtils;
import com.airbnb.android.utils.Strap;
import java.util.HashSet;
import java.util.Set;

public final class Trebuchet {
    public static final String CHECKPOINT = "checkpoint";
    public static final String KEY_ACCOUNT_SWITCHER = "account_switcher";
    public static final String KEY_ANDROID_ENABLED = "android_enabled";
    public static final String KEY_ANDROID_ENG = "android_eng";
    public static final String KEY_ENABLED = "enabled";
    public static final String KEY_KILL_SWITCH = "kill_switch";
    public static final String KEY_OTHER_PAYMENTS_WEB_VIEW = "other_payment_methods_web_view";
    public static final String KEY_OTHER_PAYMENTS_WEB_VIEW_INDIA = "other_payment_methods_web_view_india";
    private static final String LEGACY_PREFS_KEY = "trebuchet_prefs";
    private static final String MOBILE_EXPERIMENT_EVENT = "mobile_experiment";
    public static final String OUTSTANDING_VERIFICATION = "outstanding_verification";
    public static final String PREFS_KEY = "trebuchet_prefs_v2";
    public static final String TREBUCHET_PREFIX = "TREBUCHET";
    private static final Set<String> mLoggedExperiments = new HashSet();

    private Trebuchet() {
    }

    public static boolean launch(String trebuchetKey) {
        return launch(trebuchetKey, false);
    }

    public static boolean launchProdOnly(String trebuchetKey) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            return true;
        }
        return launch(trebuchetKey, false);
    }

    public static boolean launch(String trebuchetKey, boolean defaultValue) {
        return getTrebuchetPrefs(CoreApplication.appContext()).getBoolean(trebuchetKey, defaultValue);
    }

    public static SharedPreferences getTrebuchetPrefs(Context context) {
        return context.getSharedPreferences(PREFS_KEY, 0);
    }

    public static String buildKey(String outerKey, String innerKey) {
        return "TREBUCHET_" + outerKey + "_" + innerKey;
    }

    public static String launch(String outerKey, String innerKey, String defaultValue) {
        return launch(outerKey, innerKey, defaultValue, false);
    }

    public static String launch(String outerKey, String innerKey, String defaultValue, boolean logUniqueInvocation) {
        String key = outerKey + "_" + innerKey;
        SharedPreferences sharedPrefs = getLegacyTrebuchetPrefs(CoreApplication.appContext());
        String keyTrebuchetPrefix = buildKey(outerKey, innerKey);
        String value = sharedPrefs.getString(keyTrebuchetPrefix, defaultValue);
        if (logUniqueInvocation || !mLoggedExperiments.contains(key)) {
            boolean inExperiment = sharedPrefs.contains(keyTrebuchetPrefix);
            mLoggedExperiments.add(key);
            AirbnbEventLogger.track(MOBILE_EXPERIMENT_EVENT, Strap.make().mo11639kv("experiment_key", key).mo11639kv("experiment_value", inExperiment ? value : AirbnbConstants.NOT_IN_EXPERIMENT_KEY));
        }
        return value;
    }

    public static SharedPreferences getLegacyTrebuchetPrefs(Context context) {
        return context.getSharedPreferences(LEGACY_PREFS_KEY, 0);
    }

    public static boolean launchGuestExperiment(String outerKey, String innerKey, String treatmentOnValue) {
        return launchGuestExperiment(outerKey, innerKey, treatmentOnValue, true);
    }

    private static boolean launchGuestExperiment(String outerKey, String innerKey, String treatmentOnValue, boolean logUniqueInvocation) {
        String trebuchet = launch(outerKey, innerKey, "", logUniqueInvocation);
        return !TextUtils.isEmpty(trebuchet) && treatmentOnValue.equalsIgnoreCase(trebuchet);
    }

    public static boolean launch(String outerKey, String innerKey, boolean defaultValue) {
        String trebuchet = launch(outerKey, innerKey, "");
        return TextUtils.isEmpty(trebuchet) ? defaultValue : Boolean.parseBoolean(trebuchet);
    }

    public static int launch(String outerKey, String innerKey, int defaultValue) {
        String trebuchet = launch(outerKey, innerKey, "");
        return TextUtils.isEmpty(trebuchet) ? defaultValue : NumberUtils.tryParseInt(trebuchet, defaultValue);
    }

    public static void clearLoggedExperiments() {
        mLoggedExperiments.clear();
    }
}

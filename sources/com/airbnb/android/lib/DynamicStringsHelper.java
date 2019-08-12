package com.airbnb.android.lib;

import android.content.SharedPreferences;
import com.airbnb.android.core.utils.PullStringsScheduler;

public class DynamicStringsHelper {
    public static boolean hasFetchedStrings(String currentLanguage, SharedPreferences stringsPreferences) {
        return currentLanguage.equalsIgnoreCase(stringsPreferences.getString(PullStringsScheduler.FETCHED_LANGUAGE_KEY, null));
    }
}

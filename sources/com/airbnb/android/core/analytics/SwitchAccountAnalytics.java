package com.airbnb.android.core.analytics;

import android.content.SharedPreferences;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.Strap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;

public final class SwitchAccountAnalytics {
    private static Integer loginCount = null;

    public static void logLogin(long userId, SharedPreferences prefs) {
        try {
            HashSet<Long> ids = new HashSet<>();
            ids.add(Long.valueOf(userId));
            JSONArray jsonArray = new JSONArray(prefs.getString(AirbnbConstants.PREFS_ACCOUNTS_LOG, "[]"));
            for (int i = 0; i < jsonArray.length(); i++) {
                ids.add(Long.valueOf(jsonArray.getLong(i)));
            }
            JSONArray outArray = new JSONArray();
            Iterator it = ids.iterator();
            while (it.hasNext()) {
                outArray.put((Long) it.next());
            }
            loginCount = Integer.valueOf(outArray.length());
            uploadAnalytics(loginCount.intValue(), outArray.toString());
            prefs.edit().putString(AirbnbConstants.PREFS_ACCOUNTS_LOG, outArray.toString()).apply();
        } catch (JSONException e) {
            prefs.edit().putString(AirbnbConstants.PREFS_ACCOUNTS_LOG, "[]").apply();
        }
    }

    public static int getAccountsCount() {
        if (loginCount == null) {
            return 1;
        }
        return loginCount.intValue();
    }

    private static void uploadAnalytics(int accountCount, String accounts) {
        if (accountCount > 1 && !BuildHelper.isDevelopmentBuild()) {
            AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv(Trebuchet.KEY_ACCOUNT_SWITCHER, "true").mo11637kv("accounts_count", accountCount).mo11639kv("account_ids", accounts));
        }
    }
}

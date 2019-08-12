package com.airbnb.android.core.analytics;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.Strap;
import java.util.regex.Pattern;

public final class ButtonAnalytics extends BaseAnalytics {
    private static final String TAG = "ButtonAnalytics";
    private static final Pattern YMD_PATTERN = Pattern.compile("20\\d{2}-\\d{2}-\\d{2}");

    public static void trackButton(String deeplinkDate, long userId, String deepLinkUri, boolean isSignup) {
        Uri uri = Uri.parse(deepLinkUri);
        if (validateArguments(deeplinkDate, userId, uri)) {
            Strap strap = Strap.make().mo11639kv("deeplink_date", deeplinkDate).mo11638kv("user_id", userId).mo11639kv("deeplink_uri", deepLinkUri).mo11640kv("is_signup", isSignup).mo11639kv("source", "android");
            for (String queryKey : uri.getQueryParameterNames()) {
                strap.mo11639kv(queryKey, uri.getQueryParameter(queryKey));
            }
            Log.d(TAG, strap.toString());
            AirbnbEventLogger.track("button_partnership", strap);
        }
    }

    private static boolean validateArguments(String deeplinkDate, long userId, Uri uri) {
        if (!YMD_PATTERN.matcher(deeplinkDate).matches()) {
            BugsnagWrapper.notify(new Throwable("button: date not in yyyy-MM-dd format"));
            return false;
        } else if (userId <= 0) {
            BugsnagWrapper.notify(new Throwable("button: user id invalid"));
            return false;
        } else if (uri == null) {
            BugsnagWrapper.notify(new Throwable("button: uri is invalid: " + uri.toString()));
            return false;
        } else if (!TextUtils.isEmpty(uri.getQueryParameter("btn_ref"))) {
            return true;
        } else {
            BugsnagWrapper.notify(new Throwable("button: btn_ref is empty"));
            return false;
        }
    }
}

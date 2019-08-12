package com.airbnb.android.core.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.bugsnag.MetaDataWrapper;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.bugsnag.android.Severity;
import java.util.regex.Pattern;

public final class DeepLinkUtils {
    private static final String AIRBNB_DEEP_LINK_SCHEME = "airbnb";

    private DeepLinkUtils() {
    }

    public static boolean isDeepLink(String uri) {
        return isDeepLink(Uri.parse(uri));
    }

    public static boolean isDeepLink(Uri uri) {
        CoreApplication.instance().component().debugSettings();
        if (DebugSettings.FORCE_DEEPLINK.isEnabled() && uri != null) {
            return true;
        }
        if (uri == null) {
            return false;
        }
        if (!PricingFeatureToggles.useDLDPricingScreens(uri.toString())) {
            return AIRBNB_DEEP_LINK_SCHEME.equals(uri.getScheme());
        }
        return true;
    }

    public static boolean isDeepLink(Intent intent) {
        return intent.getBooleanExtra("is_deep_link_flag", false);
    }

    public static void startActivityForDeepLink(Context context, String deepLinkUri) {
        Check.state(isDeepLink(deepLinkUri));
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(deepLinkUri)));
    }

    public static boolean isLegacyDeepLink(Uri uri) {
        return isDeepLink(uri) && !"d".equals(uri.getHost());
    }

    public static long getParamAsId(Intent intent, String... params) {
        Check.state(isDeepLink(intent), "Intent is not a deep link");
        int i = 0;
        while (i < params.length) {
            try {
                return Long.parseLong(intent.getStringExtra(params[i]));
            } catch (NumberFormatException e) {
                i++;
            }
        }
        return -1;
    }

    public static long getParamAsId(Bundle bundle, String... params) {
        int i = 0;
        while (i < params.length) {
            try {
                return Long.parseLong(bundle.getString(params[i]));
            } catch (NumberFormatException e) {
                i++;
            }
        }
        return -1;
    }

    public static String getParamAsString(Intent intent, String... params) {
        Check.state(isDeepLink(intent), "Intent is not a deep link");
        for (String param : params) {
            String str = intent.getStringExtra(param);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return null;
    }

    public static Long getLongParam(Bundle bundle, String param) {
        Long l = null;
        if (bundle.getString(param) == null) {
            return l;
        }
        try {
            return Long.valueOf(bundle.getString(param));
        } catch (NumberFormatException e) {
            return l;
        }
    }

    public static boolean hasDeepLinkPath(Intent intent, String... paths) {
        if (!isDeepLink(intent)) {
            return false;
        }
        Uri uri = getDeepLinkUri(intent);
        String path = uri.getPath();
        if (path == null) {
            return false;
        }
        if (isLegacyDeepLink(uri)) {
            path = uri.getHost() + uri.getPath();
        }
        int length = paths.length;
        for (int i = 0; i < length; i++) {
            if (Pattern.matches("/?" + paths[i] + "(/\\d*)?", path)) {
                return true;
            }
        }
        return false;
    }

    public static Uri getDeepLinkUri(Intent intent) {
        Check.state(isDeepLink(intent));
        return Uri.parse(intent.getStringExtra("deep_link_uri"));
    }

    public static void notifyUnhandledDeepLink(Intent intent) {
        Check.state(isDeepLink(intent));
        notifyUnhandledDeepLink(getDeepLinkUri(intent).toString(), null);
    }

    public static void notifyUnhandledDeepLink(String deepLinkUri, String errorMessage) {
        MetaDataWrapper metaData = new MetaDataWrapper();
        metaData.setGroupingHash(getBugsnagGroupingHash(deepLinkUri));
        metaData.addToTab("Deep Link", "uri", deepLinkUri);
        metaData.addToTab("Deep Link", "message", errorMessage);
        String str = deepLinkUri;
        BugsnagWrapper.notify("Unhandled Deep Link", str, "DeepLinkDispatch", Thread.currentThread().getStackTrace(), Severity.WARNING, metaData);
    }

    private static String getBugsnagGroupingHash(String deepLinkUri) {
        Uri uri = Uri.parse(deepLinkUri);
        String host = uri.getHost();
        return isLegacyDeepLink(uri) ? host : host + uri.getPath();
    }
}

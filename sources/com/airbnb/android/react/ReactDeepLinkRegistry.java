package com.airbnb.android.react;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.utils.BundleBuilder;
import java.util.Map;
import java.util.Set;

public class ReactDeepLinkRegistry {
    private static final String DOUBLE_PATTERN = "^[\\d\\.]+$";
    private static final String INT_PATTERN = "^\\d+$";
    private static final String SCHEME = "airbnb://";
    private final Map<String, String> deepLinkUrls;

    public ReactDeepLinkRegistry(Map<String, String> deepLinkUrls2) {
        this.deepLinkUrls = deepLinkUrls2;
    }

    public boolean canHandle(String deepLinkUrl) {
        return this.deepLinkUrls.containsKey(stripQueryString(deepLinkUrl));
    }

    public void add(String sceneName, String deepLinkUrl) {
        if (!deepLinkUrl.startsWith(SCHEME)) {
            deepLinkUrl = SCHEME + deepLinkUrl;
        }
        this.deepLinkUrls.put(deepLinkUrl, sceneName);
    }

    public void dispatch(Context context, String deepLinkUrl) {
        Intent intent;
        String sceneName = (String) this.deepLinkUrls.get(stripQueryString(deepLinkUrl));
        if (!TextUtils.isEmpty(sceneName)) {
            Bundle props = getQueryParamsBundle(deepLinkUrl);
            if (ReactDeepLinkOverrides.hasOverride(sceneName)) {
                intent = ReactDeepLinkOverrides.getIntent(context, sceneName, props);
            } else {
                intent = ReactNativeIntents.intentForDeepLink(context, sceneName, props);
            }
            context.startActivity(intent);
        }
    }

    private static Bundle getQueryParamsBundle(String deepLinkUrl) {
        Uri uri = Uri.parse(deepLinkUrl);
        Set<String> queryParamNames = uri.getQueryParameterNames();
        BundleBuilder builder = new BundleBuilder();
        for (String name : queryParamNames) {
            putQueryParam(builder, name, uri.getQueryParameter(name));
        }
        return builder.toBundle();
    }

    private static void putQueryParam(BundleBuilder builder, String name, String value) {
        if (value.equals("true")) {
            builder.putBoolean(name, true);
        } else if (value.equals(InternalLogger.EVENT_PARAM_EXTRAS_FALSE)) {
            builder.putBoolean(name, false);
        } else if (value.matches(INT_PATTERN)) {
            builder.putLong(name, Long.parseLong(value));
        } else if (value.matches(DOUBLE_PATTERN)) {
            builder.putDouble(name, Double.parseDouble(value));
        } else {
            builder.putString(name, value);
        }
    }

    private static String stripQueryString(String deepLinkUrl) {
        return deepLinkUrl.split("\\?")[0];
    }
}

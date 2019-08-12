package com.appboy.p028ui.actions;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.appboy.support.StringUtils;

/* renamed from: com.appboy.ui.actions.ActionFactory */
public class ActionFactory {
    public static IAction createUriAction(Context context, String url) {
        return createUriAction(context, url, null);
    }

    public static IAction createUriAction(Context context, String url, Bundle extras) {
        if (StringUtils.isNullOrBlank(url)) {
            return null;
        }
        Uri uri = Uri.parse(url);
        if (WebAction.getSupportedSchemes().contains(uri.getScheme())) {
            return new WebAction(url, extras);
        }
        if ("intent".equals(uri.getScheme())) {
            return new ActivityAction(context.getPackageName(), uri, extras);
        }
        return new ViewAction(uri, extras);
    }

    public static IAction createViewUriAction(String url, Bundle extras) {
        if (!StringUtils.isNullOrBlank(url)) {
            return new ViewAction(Uri.parse(url), extras);
        }
        return null;
    }
}

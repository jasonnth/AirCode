package com.appboy.p028ui;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import com.appboy.Constants;
import com.appboy.IAppboyNavigator;
import com.appboy.p028ui.actions.ActionFactory;
import com.appboy.p028ui.actions.ActivityAction;
import com.appboy.p028ui.activities.AppboyFeedActivity;
import com.appboy.support.AppboyLogger;

/* renamed from: com.appboy.ui.AppboyNavigator */
public class AppboyNavigator implements IAppboyNavigator {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyNavigator.class.getName()});

    @SuppressLint({"WrongConstant"})
    public void gotoNewsFeed(Context context, Bundle extras) {
        try {
            context.getPackageManager().getActivityInfo(new ComponentName(context, AppboyFeedActivity.class), 1);
            new ActivityAction(new Intent(context, AppboyFeedActivity.class)).execute(context);
        } catch (NameNotFoundException e) {
            AppboyLogger.m1733d(TAG, "The AppboyFeedActivity is not registered in the manifest. Ignoring request to display the news feed.");
        }
    }

    public void gotoURI(Context context, Uri uri, Bundle extras) {
        if (uri == null) {
            AppboyLogger.m1735e(TAG, "IAppboyNavigator cannot open URI because the URI is null.");
        } else {
            ActionFactory.createUriAction(context, uri.toString(), extras).execute(context);
        }
    }
}

package com.facebook.accountkit;

import android.content.Intent;

public abstract class AccessTokenTracker extends Tracker {
    public static final String ACTION_CURRENT_ACCESS_TOKEN_CHANGED = "com.facebook.accountkit.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
    public static final String EXTRA_NEW_ACCESS_TOKEN = "com.facebook.accountkit.sdk.EXTRA_NEW_ACCESS_TOKEN";
    public static final String EXTRA_OLD_ACCESS_TOKEN = "com.facebook.accountkit.sdk.EXTRA_OLD_ACCESS_TOKEN";

    /* access modifiers changed from: protected */
    public abstract void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2);

    public AccessTokenTracker() {
        startTracking();
    }

    /* access modifiers changed from: protected */
    public String getActionStateChanged() {
        return ACTION_CURRENT_ACCESS_TOKEN_CHANGED;
    }

    /* access modifiers changed from: protected */
    public void onReceive(Intent intent) {
        onCurrentAccessTokenChanged((AccessToken) intent.getParcelableExtra(EXTRA_OLD_ACCESS_TOKEN), (AccessToken) intent.getParcelableExtra(EXTRA_NEW_ACCESS_TOKEN));
    }
}

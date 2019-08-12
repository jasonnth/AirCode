package com.facebook.accountkit.p029ui;

import android.app.Activity;

/* renamed from: com.facebook.accountkit.ui.ContentControllerBase */
public abstract class ContentControllerBase implements ContentController {
    protected final AccountKitConfiguration configuration;

    /* access modifiers changed from: protected */
    public abstract void logImpression();

    ContentControllerBase(AccountKitConfiguration configuration2) {
        this.configuration = configuration2;
    }

    public void onPause(Activity activity) {
        ViewUtility.hideKeyboard(activity);
    }

    public void onResume(Activity activity) {
        logImpression();
    }

    public boolean isTransient() {
        return true;
    }
}

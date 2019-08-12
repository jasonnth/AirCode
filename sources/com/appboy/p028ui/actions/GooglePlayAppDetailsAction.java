package com.appboy.p028ui.actions;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.appboy.Constants;
import com.appboy.enums.AppStore;
import com.appboy.p028ui.AppboyWebViewActivity;
import com.appboy.support.AppboyLogger;

/* renamed from: com.appboy.ui.actions.GooglePlayAppDetailsAction */
public final class GooglePlayAppDetailsAction implements IAction {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, GooglePlayAppDetailsAction.class.getName()});
    private final AppStore mAppStore;
    private String mKindleId;
    private final String mPackageName;
    private boolean mUseAppboyWebView;

    public GooglePlayAppDetailsAction(String packageName, boolean useAppboyWebView, AppStore appStore, String kindleId) {
        this.mPackageName = packageName;
        this.mUseAppboyWebView = useAppboyWebView;
        this.mAppStore = appStore;
        this.mKindleId = kindleId;
    }

    public void execute(Context context) {
        String uriString;
        String uriString2;
        if (this.mAppStore != AppStore.KINDLE_STORE) {
            try {
                context.getPackageManager().getPackageInfo("com.google.android.gsf", 0);
            } catch (NameNotFoundException e) {
                AppboyLogger.m1737i(TAG, "Google Play Store not found, launching Play Store with WebView");
                this.mUseAppboyWebView = true;
            } catch (Exception e2) {
                AppboyLogger.m1735e(TAG, String.format("Unexpected exception while checking for %s.", new Object[]{"com.google.android.gsf"}));
                this.mUseAppboyWebView = true;
            }
        }
        String str = "";
        if (this.mUseAppboyWebView) {
            if (this.mAppStore == AppStore.KINDLE_STORE) {
                uriString2 = "http://www.amazon.com/gp/mas/dl/android?asin=" + this.mKindleId;
            } else {
                uriString2 = "https://play.google.com/store/apps/details?id=" + this.mPackageName;
            }
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(uriString2), context, AppboyWebViewActivity.class));
            return;
        }
        if (this.mAppStore == AppStore.KINDLE_STORE) {
            uriString = "amzn://apps/android?asin=" + this.mKindleId;
        } else {
            uriString = "market://details?id=" + this.mPackageName;
        }
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(uriString)));
    }
}

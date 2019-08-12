package com.airbnb.android.lib.utils.webintent;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.events.ResetPasswordEntryEvent;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.net.NoOpCallback;
import com.airbnb.android.core.utils.webintent.Path;
import com.airbnb.android.core.utils.webintent.WebIntentMatcherResult;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.Strap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.squareup.otto.Bus;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WebIntentDispatch extends Activity {
    private static final int DEFAULT_BROWSER_AFTER = 3;
    private static final String TAG = WebIntentDispatch.class.getSimpleName();
    private static final int VERSION = 5;
    private static long lastSelectedBrowser = 0;
    private static final ImmutableList<String> webIntentLoggedOutWhiteList = ImmutableList.m1285of("/users/set_password");
    private final int BREADCRUMB_TRUNCATE_LENGTH = (130 - TAG.length());
    AirbnbAccountManager accountManager;
    AffiliateInfo affiliateInfo;
    Bus bus;
    OkHttpClient okHttpClient;
    AirbnbPreferences preferences;

    public static boolean isWebIntentInLoggedOutWhiteList(String url) {
        UnmodifiableIterator it = webIntentLoggedOutWhiteList.iterator();
        while (it.hasNext()) {
            if (url.contains((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        Uri uri = getIntent().getData();
        WebIntentMatcherResult result = WebIntentMatcherUtil.getMatch(this, uri, this.accountManager.getCurrentUser());
        track(result);
        reportOpenUrl(uri);
        this.affiliateInfo.storeAffiliateParams(uri);
        logIntentOpen(result.hasPathMatch() ? result.getPath() + " " + uri : "null");
        if (result.hasIntentMatch() && result.hasPathMatch() && Path.ResetPassword == result.getPath()) {
            finish();
            this.bus.post(new ResetPasswordEntryEvent(result.getIntent().getStringExtra("secret")));
            startActivity(result.getIntent());
        } else if (result.hasIntentMatch()) {
            startActivityForResult(result.getIntent(), 0);
            finish();
        } else {
            try {
                sendToWebBrowser(uri);
            } catch (SecurityException exception) {
                Log.e(TAG, "Security exception launching a browser to handle web link.", exception);
                handleNoBrowserInstalled(uri);
            }
        }
    }

    private void sendToWebBrowser(Uri uri) {
        Intent webIntent = new Intent("android.intent.action.VIEW", uri);
        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("https://")), 0);
        if (resolveInfos.isEmpty()) {
            handleNoBrowserInstalled(uri);
        } else if (sendToPreferredBrowser(webIntent, resolveInfos)) {
            finish();
        } else if (resolveInfos.size() == 1) {
            startWebBroswerIntent(webIntent, (ResolveInfo) resolveInfos.get(0));
        } else if (resolveInfos.size() > 1) {
            WebBrowserListAdapter adapter = new WebBrowserListAdapter(this, resolveInfos);
            new Builder(this).setTitle(getString(C0880R.string.open_in_browser)).setAdapter(adapter, WebIntentDispatch$$Lambda$1.lambdaFactory$(this, webIntent, adapter)).create().show();
        }
    }

    /* access modifiers changed from: private */
    public void startWebBroswerIntent(Intent intent, ResolveInfo info) {
        intent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
        logPreferredBrowser(intent);
        Toast.makeText(this, C0880R.string.open_link_in_browser, 0).show();
        startActivity(intent);
        finish();
    }

    private void handleNoBrowserInstalled(Uri uri) {
        Intent intent = HomeActivityIntents.intentForDefaultTab(this);
        if (isWebIntentInLoggedOutWhiteList(uri.getPath())) {
            intent = WebViewIntentBuilder.newBuilder(this, uri.toString()).toIntent();
        }
        startActivity(intent);
        finish();
    }

    private void logPreferredBrowser(Intent baseIntent) {
        boolean userSelectedSameBrowser;
        int browserSelectedCount;
        SharedPreferences prefs = this.preferences.getGlobalSharedPreferences();
        String previousBrowser = prefs.getString(AirbnbConstants.PREFS_PREFERRED_WEB_BROWSER, "");
        String newBrowser = baseIntent.getComponent().getClassName();
        if (TextUtils.isEmpty(newBrowser) || !newBrowser.equals(previousBrowser)) {
            userSelectedSameBrowser = false;
        } else {
            userSelectedSameBrowser = true;
        }
        int browserSelectedCount2 = prefs.getInt(AirbnbConstants.PREFS_PREFERRED_WEB_BROWSER_COUNT, 0);
        if (userSelectedSameBrowser) {
            browserSelectedCount = browserSelectedCount2 + 1;
        } else {
            browserSelectedCount = 1;
        }
        prefs.edit().putString(AirbnbConstants.PREFS_PREFERRED_WEB_BROWSER, newBrowser).apply();
        prefs.edit().putInt(AirbnbConstants.PREFS_PREFERRED_WEB_BROWSER_COUNT, browserSelectedCount).apply();
        lastSelectedBrowser = System.currentTimeMillis();
    }

    private boolean sendToPreferredBrowser(Intent webIntent, List<ResolveInfo> resolveInfos) {
        SharedPreferences prefs = this.preferences.getGlobalSharedPreferences();
        String preferredBrowserClass = prefs.getString(AirbnbConstants.PREFS_PREFERRED_WEB_BROWSER, "");
        int preferredBrowserCount = prefs.getInt(AirbnbConstants.PREFS_PREFERRED_WEB_BROWSER_COUNT, 0);
        boolean recentlySelectedBrowser = System.currentTimeMillis() < lastSelectedBrowser + 5000;
        if (preferredBrowserCount >= 3 || recentlySelectedBrowser) {
            for (ResolveInfo resolve : resolveInfos) {
                ActivityInfo info = resolve.activityInfo;
                if (resolve.activityInfo.name.equals(preferredBrowserClass)) {
                    startActivity(webIntent.setClassName(info.packageName, info.name));
                    return true;
                }
            }
        }
        return false;
    }

    private void reportOpenUrl(Uri uri) {
        if (!TextUtils.isEmpty(uri.getQueryParameter("euid"))) {
            this.okHttpClient.newCall(new Request.Builder().url(uri.toString()).head().build()).enqueue(new NoOpCallback());
        }
    }

    private void track(WebIntentMatcherResult matcher) {
        AirbnbEventLogger.track("android_web_intent", Strap.make().mo11639kv("air_path", matcher.hasPathMatch() ? matcher.getPath().name() : "send_to_browser").mo11640kv("handled", matcher.hasIntentMatch()).mo11639kv("uri", matcher.getUri().toString()).mo11637kv("version", 5));
    }

    private void logIntentOpen(String message) {
        C0715L.m1189d(TAG, message);
        if (message.length() > this.BREADCRUMB_TRUNCATE_LENGTH) {
            C0715L.m1189d(TAG, ">>> " + message.substring(this.BREADCRUMB_TRUNCATE_LENGTH));
        }
    }
}

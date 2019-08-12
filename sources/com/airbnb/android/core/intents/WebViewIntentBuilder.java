package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.airbnb.android.core.Activities;
import p005cn.jpush.android.JPushConstants.PushService;

public class WebViewIntentBuilder {
    public static final String EXTRA_AUTHENTICATE = "extra_authenticate";
    public static final String EXTRA_BACKUP = "extra_backup";
    public static final String EXTRA_DISABLE_LOADER = "extra_disable_loader";
    public static final String EXTRA_INTENT_FLAG = "extra_intent_flag";
    public static final String EXTRA_POST = "extra_post";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_TITLE_STRING = "extra_title_string";
    public static final String EXTRA_URL = "extra_url";
    private final Intent intent;

    public WebViewIntentBuilder(Context context, Class<?> clazz) {
        this.intent = new Intent(context, clazz);
    }

    public static WebViewIntentBuilder newBuilder(Context context, String url) {
        return new WebViewIntentBuilder(context, Activities.webView()).url(buildUrl(url));
    }

    public static String buildUrl(String url) {
        return Uri.parse(url).buildUpon().appendQueryParameter("hide_nav", "true").appendQueryParameter(PushService.PARAM_PLATFORM, "android").build().toString();
    }

    public WebViewIntentBuilder url(String url) {
        this.intent.putExtra(EXTRA_URL, url);
        return this;
    }

    public WebViewIntentBuilder title(String title) {
        this.intent.putExtra(EXTRA_TITLE_STRING, title);
        return this;
    }

    public WebViewIntentBuilder title(int title) {
        this.intent.putExtra(EXTRA_TITLE, title);
        return this;
    }

    public WebViewIntentBuilder post() {
        this.intent.putExtra(EXTRA_POST, true);
        return this;
    }

    public WebViewIntentBuilder backupIntent(Intent backupIntent) {
        this.intent.putExtra(EXTRA_BACKUP, backupIntent);
        return this;
    }

    public WebViewIntentBuilder authenticate() {
        this.intent.putExtra(EXTRA_AUTHENTICATE, true);
        return this;
    }

    public WebViewIntentBuilder disableLoader() {
        this.intent.putExtra(EXTRA_DISABLE_LOADER, true);
        return this;
    }

    public WebViewIntentBuilder flags(int flags) {
        this.intent.setFlags(flags);
        return this;
    }

    public Intent toIntent() {
        return this.intent;
    }

    public WebViewIntentBuilder setClass(Context context, Class<?> klass) {
        this.intent.setClass(context, klass);
        return this;
    }

    public static void startMobileWebActivity(Context context, String link) {
        mobileWebActivityHelper(context, link, false);
    }

    public static void startAuthenticatedMobileWebActivity(Context context, String link) {
        mobileWebActivityHelper(context, link, true);
    }

    private static void mobileWebActivityHelper(Context context, String link, boolean authenticate) {
        WebViewIntentBuilder builder = newBuilder(context, link);
        if (authenticate) {
            builder.authenticate();
        }
        context.startActivity(builder.toIntent());
    }
}

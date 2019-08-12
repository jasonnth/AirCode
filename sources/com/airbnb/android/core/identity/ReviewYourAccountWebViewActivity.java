package com.airbnb.android.core.identity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import com.airbnb.android.core.activities.WebViewActivity;
import com.airbnb.android.core.intents.EntryActivityIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;

public class ReviewYourAccountWebViewActivity extends WebViewActivity {
    private static final String SUCCESS_REDIRECT = "airlock/redirect";
    private final AirWebViewCallbacks webViewCallbacks = new AirWebViewCallbacks() {
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (!ReviewYourAccountWebViewActivity.isSuccessPage(url)) {
                return super.shouldOverrideUrlLoading(webView, url);
            }
            ReviewYourAccountWebViewActivity.this.proceedToHomeScreen();
            return true;
        }
    };

    public static Intent intent(Context context, String url) {
        return WebViewIntentBuilder.newBuilder(context, url).setClass(context, ReviewYourAccountWebViewActivity.class).authenticate().toIntent();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.airWebView.setOverrideDeeplinks(false);
        addWebViewCallbacks(this.webViewCallbacks);
    }

    /* access modifiers changed from: private */
    public void proceedToHomeScreen() {
        startActivity(EntryActivityIntents.newIntent(this).addFlags(335544320));
        finish();
    }

    /* access modifiers changed from: private */
    public static boolean isSuccessPage(String url) {
        return url.contains(SUCCESS_REDIRECT);
    }
}

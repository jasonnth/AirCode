package com.airbnb.android.lib.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;
import com.airbnb.android.core.activities.WebViewActivity;
import com.airbnb.android.core.events.LoginEvent;
import com.airbnb.android.core.intents.AirlockActivityIntents;
import com.airbnb.android.core.intents.EntryActivityIntents;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;
import com.airbnb.android.lib.C0880R;
import icepick.State;

public class AirlockActivity extends WebViewActivity {
    private static final String AIRLOCK_SUCCESS = "airlock/redirect";
    @State
    boolean isLoginAirlock;
    private final AirWebViewCallbacks webViewCallbacks = new AirWebViewCallbacks() {
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (!AirlockActivity.isSuccessPage(url)) {
                return super.shouldOverrideUrlLoading(webView, url);
            }
            AirlockActivity.this.succeedAirlockActivity();
            return true;
        }

        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            super.onReceivedError(webView, errorCode, description, failingUrl);
            Toast.makeText(AirlockActivity.this, C0880R.string.airlock_error, 0).show();
            AirlockActivity.this.finish();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.airWebView.setOverrideDeeplinks(false);
        if (getIntent().hasExtra(AirlockActivityIntents.EXTRA_IS_LOGIN_AIRLOCK)) {
            this.isLoginAirlock = getIntent().getBooleanExtra(AirlockActivityIntents.EXTRA_IS_LOGIN_AIRLOCK, false);
        }
        addWebViewCallbacks(this.webViewCallbacks);
    }

    /* access modifiers changed from: private */
    public static boolean isSuccessPage(String url) {
        return url.contains(AIRLOCK_SUCCESS);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0880R.C0883menu.logout, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.menu_log_out) {
            return super.onOptionsItemSelected(item);
        }
        logout();
        return true;
    }

    public void onBackPressed() {
        failAirlockActivity();
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        failAirlockActivity();
        super.onHomeActionPressed();
    }

    private void failAirlockActivity() {
        if (this.isLoginAirlock) {
            logout();
        } else {
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void succeedAirlockActivity() {
        if (this.isLoginAirlock) {
            finishLogin();
        } else {
            finish();
        }
    }

    private void logout() {
        this.airbnbApi.clearUserSession();
        finishAffinity();
        startActivity(EntryActivityIntents.newIntent(this).addFlags(335544320));
    }

    private void finishLogin() {
        this.airbnbApi.enablePushNotifications();
        this.bus.post(new LoginEvent());
        startActivity(EntryActivityIntents.newIntent(this).addFlags(335544320));
        finish();
    }
}

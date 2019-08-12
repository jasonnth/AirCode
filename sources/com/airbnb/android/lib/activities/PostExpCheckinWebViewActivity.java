package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.WebViewActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;

public class PostExpCheckinWebViewActivity extends WebViewActivity {
    public static Intent intentForPostExpCheckin(Context context) {
        AirbnbEventLogger.track("mobile_help", Strap.make().mo11639kv("page", "post_exp_checkin").mo11639kv(BaseAnalytics.OPERATION, "impression"));
        return new WebViewIntentBuilder(context, PostExpCheckinWebViewActivity.class).url(HelpCenterIntents.getBaseHelpCenterUrl(context) + "/post_exp_checkin").title(C0880R.string.airbnb_help).authenticate().toIntent();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        processDeepLink(bundle);
        super.onCreate(bundle);
        addCloseCallbacks();
    }

    private void processDeepLink(Bundle bundle) {
        Intent intent = getIntent();
        if (!intent.getBooleanExtra("is_deep_link_flag", false)) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("PostExpCheckinWebViewActivity is created other than deeplink."));
            finish();
            return;
        }
        Bundle parameters = intent.getExtras();
        if (parameters == null || parameters.getString("confirmation_code") == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("PostExpCheckinWebViewActivity is deeplinked without confirmation_code param"));
            finish();
            return;
        }
        intent.putExtra(WebViewIntentBuilder.EXTRA_URL, WebViewIntentBuilder.buildUrl(Uri.parse(intent.getStringExtra(WebViewIntentBuilder.EXTRA_URL)).buildUpon().appendQueryParameter("code", parameters.getString("confirmation_code")).build().toString()));
    }

    private void addCloseCallbacks() {
        addWebViewCallbacks(new AirWebViewCallbacks() {
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                if (!PostExpCheckinWebViewActivity.this.airWebView.isAirbnbDomain(url) || !url.toLowerCase().contains("help/post_exp_checkin_close")) {
                    return super.shouldOverrideUrlLoading(webView, url);
                }
                PostExpCheckinWebViewActivity.this.finish();
                return true;
            }
        });
    }
}

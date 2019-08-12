package com.airbnb.android.payout.create;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.WebViewActivity;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;
import com.airbnb.android.payout.PayoutGraph;

public class PayoutRedirectWebviewActivity extends WebViewActivity {
    public static final String PAYONEER_REDIRECT_SUCCESS_URL = "payoneer_signup_complete";

    public static Intent intent(Context context, String url) {
        return new WebViewIntentBuilder(context, PayoutRedirectWebviewActivity.class).url(url).disableLoader().toIntent();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((PayoutGraph) CoreApplication.instance(this).component()).inject(this);
        addWebViewCallbacks(new AirWebViewCallbacks() {
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                Uri uri = Uri.parse(url);
                if (!PayoutRedirectWebviewActivity.this.airWebView.isAirbnbDomain(url) || !uri.getPath().contains(PayoutRedirectWebviewActivity.PAYONEER_REDIRECT_SUCCESS_URL)) {
                    return false;
                }
                PayoutRedirectWebviewActivity.this.setResult(-1);
                PayoutRedirectWebviewActivity.this.finish();
                return true;
            }
        });
    }
}

package com.airbnb.android.lib.payments.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import com.airbnb.android.core.activities.WebViewActivity;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;

public class PaymentRedirectWebViewActivity extends WebViewActivity {
    private static final String PAYMENT_EXPERIENCES_PATH = "experiences";
    private static final String PAYMENT_PAID_GROWTH_ACCEPTED_BOOKINGS_PATH = "paid_growth_accepted_bookings";
    private static final String PAYMENT_PENDING_PATH = "payments/payment_pending";
    private static final String PAYMENT_REQUEST_PATH = "reservation/requested";
    private static final String PAYMENT_SUCCESSFUL_PATH = "payments/adyen_payment_result";
    private final AirWebViewCallbacks webViewCallbacks = new AirWebViewCallbacks() {
        public void onPageFinished(WebView view, String url) {
            if (url.contains(PaymentRedirectWebViewActivity.PAYMENT_SUCCESSFUL_PATH) || url.contains(PaymentRedirectWebViewActivity.PAYMENT_REQUEST_PATH) || url.contains(PaymentRedirectWebViewActivity.PAYMENT_PENDING_PATH) || url.contains(PaymentRedirectWebViewActivity.PAYMENT_PAID_GROWTH_ACCEPTED_BOOKINGS_PATH) || url.contains(PaymentRedirectWebViewActivity.PAYMENT_EXPERIENCES_PATH)) {
                PaymentRedirectWebViewActivity.this.setResult(-1);
                PaymentRedirectWebViewActivity.this.finish();
            }
        }
    };

    public static Intent intentForRedirect(Context context, String url) {
        return new WebViewIntentBuilder(context, PaymentRedirectWebViewActivity.class).url(url).authenticate().toIntent();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        addWebViewCallbacks(this.webViewCallbacks);
    }
}

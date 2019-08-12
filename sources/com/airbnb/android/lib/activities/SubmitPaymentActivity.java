package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.Toast;
import com.airbnb.android.core.activities.WebViewActivity;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;
import com.airbnb.android.lib.C0880R;
import p005cn.jpush.android.JPushConstants.PushService;

public class SubmitPaymentActivity extends WebViewActivity {
    private static final String CONFIRMATION_CODE_BASE_URL = "https://www.airbnb.com/payments/pay_reservation/";
    private static final String PAYMENT_ID_BASE_URL = "https://www.airbnb.com/payments/pay/";
    private static final String PAYMENT_SUCCESSFUL_URL_SUBSTRING = "/payments/payment_successful/";
    private final AirWebViewCallbacks webViewCallbacks = new AirWebViewCallbacks() {
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (!SubmitPaymentActivity.isSuccessPage(url)) {
                return super.shouldOverrideUrlLoading(webView, url);
            }
            SubmitPaymentActivity.this.onPaymentSuccess();
            return true;
        }

        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            super.onReceivedError(webView, errorCode, description, failingUrl);
            SubmitPaymentActivity.toastErrorMessage(webView.getContext());
            SubmitPaymentActivity.this.finish();
        }
    };

    private static String buildUrlForPaymentId(long paymentId) {
        return PAYMENT_ID_BASE_URL + paymentId;
    }

    private static String buildUrlForConfirmationCode(String confirmationCode) {
        return CONFIRMATION_CODE_BASE_URL + confirmationCode;
    }

    public static Intent payIntent(Context context, Bundle bundle) {
        return forPaymentId(context, DeepLinkUtils.getLongParam(bundle, "payment_id").longValue()).toIntent();
    }

    public static WebViewIntentBuilder forConfirmationCode(Context context, String confirmationCode) {
        if (!TextUtils.isEmpty(confirmationCode)) {
            return buildIntent(context, buildUrlForConfirmationCode(confirmationCode));
        }
        throw new IllegalStateException("Invalid confirmationCode: " + confirmationCode);
    }

    public static WebViewIntentBuilder forPaymentId(Context context, long paymentId) {
        if (paymentId > 0) {
            return buildIntent(context, buildUrlForPaymentId(paymentId));
        }
        throw new IllegalStateException("Invalid paymentId: " + paymentId);
    }

    private static WebViewIntentBuilder buildIntent(Context context, String url) {
        return new WebViewIntentBuilder(context, SubmitPaymentActivity.class).url(appendUrlParams(url)).title(C0880R.string.title_submit_payment).authenticate();
    }

    private static String appendUrlParams(String url) {
        return Uri.parse(url).buildUpon().appendQueryParameter("hide_nav", "true").appendQueryParameter(PushService.PARAM_PLATFORM, "android").build().toString();
    }

    /* access modifiers changed from: protected */
    public boolean setFlagSecure() {
        return BuildHelper.isReleaseBuild();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addWebViewCallbacks(this.webViewCallbacks);
    }

    /* access modifiers changed from: private */
    public static void toastErrorMessage(Context context) {
        Toast.makeText(context, C0880R.string.error_updating_payment_instrument, 0).show();
    }

    /* access modifiers changed from: private */
    public static boolean isSuccessPage(String url) {
        return url.contains(PAYMENT_SUCCESSFUL_URL_SUBSTRING);
    }

    /* access modifiers changed from: protected */
    public void onPaymentSuccess() {
        finish();
    }
}

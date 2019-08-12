package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.Toast;
import com.airbnb.android.core.activities.WebViewActivity;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;
import com.airbnb.android.lib.C0880R;
import java.util.Locale;
import p005cn.jpush.android.JPushConstants.PushService;

public class BookingWebViewActivity extends WebViewActivity {
    private static final String BOOKING_RESERVATION_BASE_URL = "https://www.airbnb.com/payments/book";
    private static final String BOOKING_SUCCESS_PATH = "/reservation/requested";
    private static final String BOOKING_VERIFIED_ID_PATH = "/verification/index";
    public static final String RESULT_VERIFIED_ID = "verified_id";
    private final AirWebViewCallbacks webViewCallbacks = new AirWebViewCallbacks() {
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (BookingWebViewActivity.this.isSuccessPage(url)) {
                BookingWebViewActivity.this.setResultDataAndFinish(false);
                return true;
            } else if (!BookingWebViewActivity.this.isVerifiedIdPage(url)) {
                return super.shouldOverrideUrlLoading(webView, url);
            } else {
                BookingWebViewActivity.this.setResultDataAndFinish(true);
                return true;
            }
        }

        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            super.onReceivedError(webView, errorCode, description, failingUrl);
            BookingWebViewActivity.this.toastErrorMessage(webView.getContext());
            BookingWebViewActivity.this.finish();
        }
    };

    public static Intent forConfirmationCode(Context context, String confirmationCode, String messageToHost) {
        Check.notEmpty(confirmationCode, "confirmationCode cannot be blank");
        return new WebViewIntentBuilder(context, SubmitPaymentActivity.class).url(buildUrlString(confirmationCode, messageToHost)).title(C0880R.string.booking_webview_activity_complete).authenticate().setClass(context, BookingWebViewActivity.class).toIntent();
    }

    private static String buildUrlString(String confirmationCode, String messageToHost) {
        String country = Locale.getDefault().getCountry();
        Builder builder = Uri.parse(BOOKING_RESERVATION_BASE_URL).buildUpon().appendQueryParameter("hide_nav", "true").appendQueryParameter(PushService.PARAM_PLATFORM, "android");
        builder.appendQueryParameter("code", confirmationCode);
        builder.appendQueryParameter("country", country);
        if (!TextUtils.isEmpty(messageToHost)) {
            builder.appendQueryParameter("message_to_host", messageToHost);
        }
        return builder.build().toString();
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
    public void toastErrorMessage(Context context) {
        Toast.makeText(context, C0880R.string.booking_webview_activity_error, 0).show();
    }

    /* access modifiers changed from: private */
    public boolean isSuccessPage(String url) {
        return url.contains(BOOKING_SUCCESS_PATH);
    }

    /* access modifiers changed from: private */
    public boolean isVerifiedIdPage(String url) {
        return url.contains(BOOKING_VERIFIED_ID_PATH);
    }

    /* access modifiers changed from: private */
    public void setResultDataAndFinish(boolean isVerifiedIdRequired) {
        Intent data = new Intent();
        data.putExtra("verified_id", isVerifiedIdRequired);
        setResult(-1, data);
        finish();
    }
}

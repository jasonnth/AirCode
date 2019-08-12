package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.WebView;
import com.airbnb.android.core.activities.WebViewActivity;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;
import com.airbnb.android.lib.C0880R;

public class SesameVerificationWebViewActivity extends WebViewActivity {
    public static final String EXTRA_VERIFICATION_RESULT_ERROR_MESSAGE = "extra_verification_result_error_message";
    public static final String EXTRA_VERIFICATION_RESULT_SUCCESS = "extra_verification_result_success";
    private static final String REDIRECT_URL_PATH = "/users/edit_verification";
    private static final String VERIFICATION_PARAM_ERROR_MESSAGE = "errorMessage";
    private static final String VERIFICATION_PARAM_SUCCESS = "success";

    public static Intent intent(Context context, String url) {
        return new WebViewIntentBuilder(context, SesameVerificationWebViewActivity.class).url(url).title(C0880R.string.verified_id_connect_sesame_credit_title).disableLoader().toIntent();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addWebViewCallbacks(new AirWebViewCallbacks() {
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                Uri uri = Uri.parse(url);
                if (!SesameVerificationWebViewActivity.this.airWebView.isAirbnbDomain(url) || !uri.getPath().contains(SesameVerificationWebViewActivity.REDIRECT_URL_PATH)) {
                    return false;
                }
                Intent result = new Intent().putExtra(SesameVerificationWebViewActivity.EXTRA_VERIFICATION_RESULT_SUCCESS, uri.getBooleanQueryParameter("success", false));
                String errorMessage = uri.getQueryParameter(SesameVerificationWebViewActivity.VERIFICATION_PARAM_ERROR_MESSAGE);
                if (!TextUtils.isEmpty(errorMessage)) {
                    result.putExtra(SesameVerificationWebViewActivity.EXTRA_VERIFICATION_RESULT_ERROR_MESSAGE, new String(Base64.decode(errorMessage, 0)));
                }
                SesameVerificationWebViewActivity.this.setResult(-1, result);
                SesameVerificationWebViewActivity.this.finish();
                return true;
            }
        });
    }
}

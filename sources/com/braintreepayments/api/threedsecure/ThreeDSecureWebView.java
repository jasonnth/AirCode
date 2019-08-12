package com.braintreepayments.api.threedsecure;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.braintreepayments.api.internal.BraintreeHttpClient;

@SuppressLint({"SetJavaScriptEnabled"})
public class ThreeDSecureWebView extends WebView {
    public ThreeDSecureWebView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void init(ThreeDSecureWebViewActivity activity) {
        setId(16908312);
        WebSettings settings = getSettings();
        settings.setUserAgentString(BraintreeHttpClient.getUserAgent());
        settings.setCacheMode(1);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        setWebChromeClient(new ThreeDSecureWebChromeClient(activity));
        setWebViewClient(new ThreeDSecureWebViewClient(activity));
    }
}

package com.braintreepayments.api.threedsecure;

import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;

public class ThreeDSecureWebChromeClient extends WebChromeClient {
    private ThreeDSecureWebViewActivity mActivity;

    public ThreeDSecureWebChromeClient(ThreeDSecureWebViewActivity activity) {
        this.mActivity = activity;
    }

    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
        ThreeDSecureWebView newWebView = new ThreeDSecureWebView(this.mActivity.getApplicationContext());
        newWebView.init(this.mActivity);
        this.mActivity.pushNewWebView(newWebView);
        ((WebViewTransport) resultMsg.obj).setWebView(newWebView);
        resultMsg.sendToTarget();
        return true;
    }

    public void onCloseWindow(WebView window) {
        this.mActivity.popCurrentWebView();
    }

    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (newProgress < 100) {
            this.mActivity.setProgress(newProgress);
            this.mActivity.setProgressBarVisibility(true);
            return;
        }
        this.mActivity.setProgressBarVisibility(false);
    }
}

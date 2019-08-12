package com.appboy.p028ui.inappmessage.views;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;

/* renamed from: com.appboy.ui.inappmessage.views.AppboyInAppMessageHtmlFullView */
public class AppboyInAppMessageHtmlFullView extends AppboyInAppMessageHtmlBaseView {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyInAppMessageHtmlFullView.class.getName()});
    private WebView mMessageWebView;

    public AppboyInAppMessageHtmlFullView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebView getMessageWebView() {
        if (this.mMessageWebView == null) {
            this.mMessageWebView = (AppboyInAppMessageWebView) findViewById(R.id.com_appboy_inappmessage_html_full_webview);
            if (this.mMessageWebView != null) {
                WebSettings webSettings = this.mMessageWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setUseWideViewPort(true);
                webSettings.setLoadWithOverviewMode(true);
                if (VERSION.SDK_INT >= 11) {
                    webSettings.setDisplayZoomControls(false);
                    this.mMessageWebView.setLayerType(1, null);
                }
                this.mMessageWebView.setBackgroundColor(0);
                this.mMessageWebView.setWebChromeClient(new WebChromeClient() {
                    public boolean onConsoleMessage(ConsoleMessage cm) {
                        AppboyLogger.m1733d(AppboyInAppMessageHtmlFullView.TAG, String.format("Html In-app log. Line: %s. SourceId: %s. Log Level: %s. Message: %s", new Object[]{Integer.valueOf(cm.lineNumber()), cm.sourceId(), cm.messageLevel(), cm.message()}));
                        return true;
                    }
                });
            }
        }
        return this.mMessageWebView;
    }
}

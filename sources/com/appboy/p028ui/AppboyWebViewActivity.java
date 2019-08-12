package com.appboy.p028ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout.LayoutParams;
import com.appboy.Constants;
import com.appboy.p028ui.actions.ActionFactory;
import com.appboy.p028ui.actions.WebAction;
import com.appboy.p028ui.activities.AppboyBaseActivity;
import com.appboy.support.AppboyLogger;

/* renamed from: com.appboy.ui.AppboyWebViewActivity */
public class AppboyWebViewActivity extends AppboyBaseActivity {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyWebViewActivity.class.getName()});

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(2);
        requestWindowFeature(5);
        setContentView(R.layout.com_appboy_webview_activity);
        setProgressBarVisibility(true);
        WebView webView = (WebView) findViewById(R.id.com_appboy_webview_activity_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(false);
        webSettings.setPluginState(PluginState.OFF);
        setZoomSafe(webSettings);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webView.setLayoutParams(new LayoutParams(-1, -1));
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    AppboyWebViewActivity.this.setProgressBarVisibility(true);
                } else {
                    AppboyWebViewActivity.this.setProgressBarVisibility(false);
                }
            }
        });
        webView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(url));
                AppboyWebViewActivity.this.startActivity(intent);
            }
        });
        webView.getSettings().setCacheMode(2);
        setWebLayerTypeSafe(webView);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    if (!WebAction.getSupportedSchemes().contains(Uri.parse(url).getScheme())) {
                        ActionFactory.createViewUriAction(url, AppboyWebViewActivity.this.getIntent().getExtras()).execute(view.getContext());
                        AppboyWebViewActivity.this.finish();
                        return true;
                    }
                } catch (Exception e) {
                    AppboyLogger.m1738i(AppboyWebViewActivity.TAG, String.format("Unexpected exception while processing url %s. Passing url back to WebView.", new Object[]{url}), e);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("url")) {
            webView.loadUrl(extras.getString("url"));
        }
    }

    @TargetApi(11)
    private void setZoomSafe(WebSettings webSettings) {
        if (VERSION.SDK_INT >= 11) {
            webSettings.setDisplayZoomControls(false);
        }
    }

    @TargetApi(11)
    private void setWebLayerTypeSafe(WebView webView) {
        if (VERSION.SDK_INT >= 11) {
            webView.setLayerType(1, null);
        }
    }
}

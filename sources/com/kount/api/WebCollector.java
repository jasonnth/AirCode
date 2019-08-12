package com.kount.api;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

@SuppressLint({"SetJavaScriptEnabled"})
class WebCollector extends CollectorTaskBase {
    String calledServerName;
    final int merchantID;
    final String urlString;
    final WebView webView;

    WebCollector(Object debugHandler, WebView webView2, String urlString2, int merchantID2) {
        super(debugHandler);
        this.webView = webView2;
        this.urlString = urlString2;
        this.merchantID = merchantID2;
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return "Web Collector";
    }

    /* access modifiers changed from: 0000 */
    public String getInternalName() {
        return internalName();
    }

    static String internalName() {
        return "collector_web";
    }

    /* access modifiers changed from: 0000 */
    public void collect() {
        final String dcStartURL = String.format(Locale.US, "%s?m=%d&s=%s&no=1", new Object[]{this.urlString, Integer.valueOf(this.merchantID), this.sessionID});
        debugMessage(String.format("Calling URL: %s", new Object[]{dcStartURL}));
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
            public void run() {
                WebCollector.this.debugMessage("Loading Web View");
                WebSettings settings = WebCollector.this.webView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setDomStorageEnabled(true);
                settings.setPluginState(PluginState.ON);
                WebCollector.this.webView.setWebViewClient(new WebViewClient() {
                    public void onPageFinished(WebView view, String url) {
                        WebCollector.this.debugMessage("onPageFinished: " + url);
                        CookieSyncManager.getInstance().sync();
                        try {
                            URI uri = new URI(url);
                            WebCollector.this.calledServerName = String.format("https://%s", new Object[]{uri.getHost()});
                            WebCollector.this.debugMessage(String.format("Called server name: %s", new Object[]{WebCollector.this.calledServerName}));
                            view.loadUrl("javascript:(function(){var resultSrc=document.getElementsByTagName('html')[0].innerHTML; window.HTML_OUT.kwcContent(resultSrc);})()");
                        } catch (URISyntaxException e) {
                            WebCollector.this.debugMessage("Error parsing server name");
                            WebCollector.this.callCompletionHandler(Boolean.valueOf(false), null);
                        }
                    }

                    public void onReceivedError(WebView view, int ec, String description, String failingUrl) {
                        WebCollector.this.debugMessage(String.format("onReceivedError: %s", new Object[]{description}));
                        WebCollector.this.callCompletionHandler(Boolean.valueOf(false), null);
                    }

                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        WebCollector.this.debugMessage("shouldOverrideUrlLoading");
                        return false;
                    }
                });
                WebCollector.this.webView.addJavascriptInterface(new Object() {
                    @JavascriptInterface
                    public void kwcContent(String jsResult) {
                        WebCollector.this.debugMessage(jsResult);
                        if (jsResult.equals("<head></head><body></body>")) {
                            WebCollector.this.debugMessage("Warning, empty response received from Device Collector, skipping.");
                            WebCollector.this.addSoftError(SoftError.SKIPPED.toString());
                        }
                        WebCollector.this.callCompletionHandler(Boolean.valueOf(true), null);
                    }
                }, "HTML_OUT");
                WebCollector.this.webView.loadUrl(dcStartURL);
            }
        });
    }
}

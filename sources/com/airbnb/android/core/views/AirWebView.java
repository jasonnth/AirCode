package com.airbnb.android.core.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.interfaces.WebIntentMatcher;
import com.airbnb.android.core.models.UserWebSession;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.persist.DomainStore;
import com.airbnb.android.core.utils.AirCookieManager;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.webintent.WebIntentMatcherResult;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.RefreshLoader;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import p005cn.jpush.android.JPushConstants;

public class AirWebView extends FrameLayout {
    private static final Strap DEFAULT_HEADERS = Strap.make().mo11639kv("Accept-Language", Locale.getDefault().getLanguage()).mo11639kv("X-Airbnb-Country", Locale.getDefault().getCountry()).mo11639kv("X-Airbnb-Locale", LocaleUtil.getString(Locale.getDefault()));
    private static final int LOADER_PROGRESS_THRESHOLD = 80;
    /* access modifiers changed from: private */
    public static final String TAG = AirWebView.class.getSimpleName();
    /* access modifiers changed from: private */
    public Strap airbnbHeaders;
    AirCookieManager cookieManager;
    private boolean disableLoader;
    AirbnbAccountManager mAccountManager;
    AirbnbApi mAirbnbApi;
    /* access modifiers changed from: private */
    public Set<AirWebViewCallbacks> mCallbacks;
    DomainStore mDomainStore;
    @BindView
    WebView mWebView;
    /* access modifiers changed from: private */
    public boolean overrideDeeplinks = true;
    @BindView
    RefreshLoader refreshLoader;
    /* access modifiers changed from: private */
    public boolean shouldLoadAirbnbUrlsInExternalBrowser;
    WebIntentMatcher webIntentMatcher;

    public static class AirWebViewCallbacks {
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }

        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
        }

        public void onReceivedTitle(WebView view, String title) {
        }

        public void onPageStarted(WebView webView, String url) {
        }

        public void onPageFinished(WebView view, String url) {
        }

        public void onShowFileChooser(WebView view, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        }

        public void onShowFileChooser(ValueCallback<Uri> valueCallback, String acceptType) {
        }
    }

    protected class AirWebViewClient extends WebViewClient {
        private static final String CUSTOM_URL_SCHEME_BROWSER = "externalhttp://";
        private static final String CUSTOM_URL_SCHEME_BROWSER_SSL = "externalhttps://";
        private static final String PATH_DESKTOP_REDIRECT = "https://m.airbnb.com/desktop";

        protected AirWebViewClient() {
        }

        /* access modifiers changed from: protected */
        public boolean startsWithExplicitBrowserScheme(String url) {
            return url.startsWith(CUSTOM_URL_SCHEME_BROWSER) || url.startsWith(CUSTOM_URL_SCHEME_BROWSER_SSL);
        }

        @SuppressLint({"DefaultLocale"})
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            BuildHelper.debugLog(AirWebView.TAG, "shouldOverrideUrlLoading");
            for (AirWebViewCallbacks callback : AirWebView.this.mCallbacks) {
                if (callback.shouldOverrideUrlLoading(view, url)) {
                    return true;
                }
            }
            if (url.startsWith("mailto:") || url.startsWith("tel:")) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                if (AirWebView.this.getContext().getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
                    return true;
                }
                AirWebView.this.getContext().startActivity(intent);
                return true;
            } else if (startsWithExplicitBrowserScheme(url)) {
                AirWebView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url.replace(CUSTOM_URL_SCHEME_BROWSER, JPushConstants.HTTP_PRE).replace(CUSTOM_URL_SCHEME_BROWSER_SSL, "https://"))));
                return true;
            } else if (url.equals(PATH_DESKTOP_REDIRECT)) {
                AirWebView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                return true;
            } else if (DeepLinkUtils.isDeepLink(url)) {
                DeepLinkUtils.startActivityForDeepLink(AirWebView.this.getContext(), url);
                return true;
            } else if (!AirWebView.this.isAirbnbDomain(url)) {
                return super.shouldOverrideUrlLoading(view, url);
            } else {
                Uri uri = Uri.parse(url);
                if (AirWebView.this.overrideDeeplinks) {
                    WebIntentMatcherResult result = AirWebView.this.webIntentMatcher.forUri(AirWebView.this.getContext(), uri);
                    if (result.hasIntentMatch()) {
                        AirWebView.this.getContext().startActivity(result.getIntent());
                        return true;
                    }
                }
                if (AirWebView.this.shouldLoadAirbnbUrlsInExternalBrowser) {
                    AirWebView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    return true;
                }
                AirWebView.this.setAirbnbDataIfNeeded(url);
                AirWebView.this.mWebView.loadUrl(url, AirWebView.this.airbnbHeaders);
                return true;
            }
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            BuildHelper.debugLog(AirWebView.TAG, "onPageStarted");
            AirWebView.this.enableLoading();
            for (AirWebViewCallbacks callback : AirWebView.this.mCallbacks) {
                callback.onPageStarted(view, url);
            }
        }

        public void onPageFinished(WebView view, String url) {
            BuildHelper.debugLog(AirWebView.TAG, "onReceivedError");
            super.onPageFinished(view, url);
            for (AirWebViewCallbacks callback : AirWebView.this.mCallbacks) {
                callback.onPageFinished(view, url);
            }
            AirWebView.this.disableLoading();
            if (!AndroidVersion.isAtLeastKitKat()) {
                AirWebView.this.postDelayed(AirWebView$AirWebViewClient$$Lambda$1.lambdaFactory$(AirWebView.this), 600);
            }
        }

        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            BugsnagWrapper.notify((Throwable) new SecurityException(error.toString()));
        }

        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
            BugsnagWrapper.notify((Throwable) new SecurityException("AirWebView.onReceivedHttpAuthRequest(): host=" + host + ", realm=" + realm));
        }

        public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
            super.onReceivedLoginRequest(view, realm, account, args);
            BugsnagWrapper.notify((Throwable) new SecurityException("AirWebView.onReceivedLoginRequest(): account=" + account + ", args=" + args));
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            BuildHelper.debugLog(AirWebView.TAG, "onReceivedError");
            super.onReceivedError(view, errorCode, description, failingUrl);
            for (AirWebViewCallbacks callback : AirWebView.this.mCallbacks) {
                callback.onReceivedError(view, errorCode, description, failingUrl);
            }
        }
    }

    public AirWebView(Context context) {
        super(context);
        init();
    }

    public AirWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AirWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
    private void init() {
        int i;
        LayoutInflater.from(getContext()).inflate(C0716R.layout.air_webview, this, true);
        if (!isInEditMode()) {
            ((CoreGraph) CoreApplication.instance(getContext()).component()).inject(this);
            ButterKnife.bind((View) this);
            this.mCallbacks = new LinkedHashSet();
            if (BuildHelper.isDevelopmentBuild() && AndroidVersion.isAtLeastKitKat()) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
            if (AndroidVersion.isAtLeastLollipopMR1()) {
                i = 2;
            } else {
                i = 1;
            }
            setLayerType(i, null);
            WebSettings webViewSettings = this.mWebView.getSettings();
            webViewSettings.setSupportZoom(true);
            webViewSettings.setBuiltInZoomControls(false);
            webViewSettings.setJavaScriptEnabled(true);
            webViewSettings.setAllowContentAccess(false);
            webViewSettings.setAllowFileAccess(false);
            webViewSettings.setAllowFileAccessFromFileURLs(false);
            this.mWebView.setWebViewClient(new AirWebViewClient());
            this.mWebView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int newProgress) {
                    BuildHelper.debugLog(AirWebView.TAG, "onProgressChanged - " + newProgress);
                    super.onProgressChanged(view, newProgress);
                    if (newProgress >= 80) {
                        AirWebView.this.disableLoading();
                    }
                }

                public void onReceivedTitle(WebView view, String title) {
                    BuildHelper.debugLog(AirWebView.TAG, "onReceivedTitle");
                    for (AirWebViewCallbacks callback : AirWebView.this.mCallbacks) {
                        callback.onReceivedTitle(view, title);
                    }
                }

                public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                    for (AirWebViewCallbacks callback : AirWebView.this.mCallbacks) {
                        callback.onShowFileChooser(webView, filePathCallback, fileChooserParams);
                    }
                    return true;
                }
            });
            this.airbnbHeaders = Strap.make().mix(DEFAULT_HEADERS);
        }
    }

    public void onDestroy() {
        this.mWebView.destroy();
    }

    @SuppressLint({"JavascriptInterface"})
    public void addJavaScriptInterface(Object object, String name) {
        this.mWebView.addJavascriptInterface(object, name);
    }

    public void addCallbacks(AirWebViewCallbacks callbacks) {
        this.mCallbacks.add(callbacks);
    }

    public void removeCallbacks(AirWebViewCallbacks callbacks) {
        this.mCallbacks.remove(callbacks);
    }

    public void postUrl(String url) {
        setAirbnbDataIfNeeded(url);
        this.mWebView.postUrl(url, null);
        enableLoading();
    }

    public void loadUrl(String url) {
        setAirbnbDataIfNeeded(url);
        this.mWebView.loadUrl(url, this.airbnbHeaders);
        enableLoading();
    }

    public String getUrl() {
        return this.mWebView.getUrl();
    }

    public void setBackgroundColor(int color) {
        this.mWebView.setBackgroundColor(color);
    }

    public void setAirbnbSession(UserWebSession session) {
        this.cookieManager.setAirbnbSession(session);
    }

    public void setOverrideDeeplinks(boolean overrideDeeplinks2) {
        this.overrideDeeplinks = overrideDeeplinks2;
    }

    public void shouldLoadAirbnbUrlsInExternalBrowser(boolean shouldLoadAirbnbUrlsInExternalBrowser2) {
        this.shouldLoadAirbnbUrlsInExternalBrowser = shouldLoadAirbnbUrlsInExternalBrowser2;
    }

    public boolean saveState(Bundle outState) {
        return this.mWebView.saveState(outState) != null;
    }

    public boolean restoreState(Bundle inState) {
        return this.mWebView.restoreState(inState) != null;
    }

    /* access modifiers changed from: private */
    public void setAirbnbDataIfNeeded(String url) {
        boolean helpPage;
        boolean z = false;
        if (isAirbnbDomain(url)) {
            String path = Uri.parse(url).getPath();
            if (path == null || !path.startsWith("/help")) {
                helpPage = false;
            } else {
                helpPage = true;
            }
            if (!helpPage) {
                z = true;
            }
            enableAirbnbOauthHeader(z);
            enableAirbnbUserAgent(true);
            return;
        }
        enableAirbnbUserAgent(false);
        enableAirbnbOauthHeader(false);
    }

    private void enableAirbnbOauthHeader(boolean enable) {
        String token = this.mAccountManager.getAccessToken();
        if (!enable || TextUtils.isEmpty(token)) {
            this.airbnbHeaders.remove(ApiRequestHeadersInterceptor.HEADER_OAUTH);
        } else {
            this.airbnbHeaders.mo11639kv(ApiRequestHeadersInterceptor.HEADER_OAUTH, token);
        }
    }

    private void enableAirbnbUserAgent(boolean enable) {
        this.mWebView.getSettings().setUserAgentString(enable ? this.mAirbnbApi.getUserAgent() : null);
    }

    public boolean isAirbnbDomain(String url) {
        return this.mDomainStore.isAirbnbDomain(url);
    }

    public void enableLoading() {
        if (!this.disableLoader) {
            this.refreshLoader.setVisibility(0);
        }
    }

    public void disableLoading() {
        this.refreshLoader.setVisibility(8);
    }

    public boolean canGoBack() {
        return this.mWebView.canGoBack();
    }

    public boolean canGoBackOrForward(int steps) {
        return this.mWebView.canGoBackOrForward(steps);
    }

    public void goBack() {
        this.mWebView.goBack();
    }

    public void disableLoader() {
        this.disableLoader = true;
    }
}

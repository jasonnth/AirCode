package com.airbnb.android.core.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.requests.WebSessionRequest;
import com.airbnb.android.core.responses.WebSessionResponse;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;

public class WebViewActivity extends AirActivity {
    private static final int INVALID_ID = -1;
    private static final int REQUEST_SELECT_FILE = 100;
    @BindView
    protected AirWebView airWebView;
    @State
    boolean hasFinishedSessionRequest;
    final RequestListener<WebSessionResponse> sessionRequestListener = new RequestListener<WebSessionResponse>() {
        public void onResponse(WebSessionResponse response) {
            WebViewActivity.this.trackSessionRequestResponse(true, null);
            WebViewActivity.this.airWebView.setAirbnbSession(response.userSession);
            WebViewActivity.this.hasFinishedSessionRequest = true;
            WebViewActivity.this.loadOriginalUrl();
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            WebViewActivity.this.trackSessionRequestResponse(false, (ErrorResponse) e.errorResponse());
            WebViewActivity.this.hasFinishedSessionRequest = true;
            WebViewActivity.this.loadOriginalUrl();
        }
    };
    @BindView
    protected AirToolbar toolbar;
    /* access modifiers changed from: private */
    public ValueCallback<Uri> uploadFileValueCallback;
    /* access modifiers changed from: private */
    public ValueCallback<Uri[]> uploadFilesValueCallback;

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0716R.layout.activity_webview);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        setToolbarTitleFromIntent();
        addDefaultWebViewCallbacks();
        trackWebviewImpression();
        disableWebviewLoaderIfNeeded();
        if (MiscUtils.isUserAMonkey()) {
            finish();
        } else if (needsAuthentication()) {
            if (!isSessionRequestInFlight()) {
                requestSessionCookie();
            }
        } else if (!restoreWebViewState(bundle)) {
            loadOriginalUrl();
        }
    }

    private void trackWebviewImpression() {
        String titleString = getIntent().getStringExtra(WebViewIntentBuilder.EXTRA_TITLE_STRING);
        if (TextUtils.isEmpty(titleString)) {
            int titleId = getIntent().getIntExtra(WebViewIntentBuilder.EXTRA_TITLE, -1);
            if (titleId != -1) {
                titleString = getString(titleId);
            }
        }
        this.navigationAnalytics.trackImpressionExplicitly(NavigationTag.WebView, Strap.make().mo11639kv("title", titleString));
    }

    private void disableWebviewLoaderIfNeeded() {
        if (getIntent().getBooleanExtra(WebViewIntentBuilder.EXTRA_DISABLE_LOADER, false)) {
            this.airWebView.disableLoader();
        }
    }

    private void setToolbarTitleFromIntent() {
        String titleString = getIntent().getStringExtra(WebViewIntentBuilder.EXTRA_TITLE_STRING);
        int titleResId = getIntent().getIntExtra(WebViewIntentBuilder.EXTRA_TITLE, 0);
        String actualTitle = titleResId == 0 ? titleString : getString(titleResId);
        if (!TextUtils.isEmpty(actualTitle)) {
            setupActionBar(actualTitle);
        }
    }

    private boolean needsAuthentication() {
        if (!getIntent().getBooleanExtra(WebViewIntentBuilder.EXTRA_AUTHENTICATE, false) || this.hasFinishedSessionRequest || !this.accountManager.isCurrentUserAuthorized()) {
            return false;
        }
        return true;
    }

    private boolean isSessionRequestInFlight() {
        return this.requestManager.hasRequest((BaseRequestListener<T>) this.sessionRequestListener, WebSessionRequest.class);
    }

    private void requestSessionCookie() {
        this.requestManager.execute(new WebSessionRequest(this.sessionRequestListener));
        this.airWebView.enableLoading();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (isSessionRequestInFlight()) {
            this.airWebView.enableLoading();
        }
    }

    /* access modifiers changed from: private */
    public void trackSessionRequestResponse(boolean success, ErrorResponse e) {
        Strap strap = Strap.make().mo11639kv("air_webview_session", success ? "success" : "fail");
        if (e != null) {
            strap.mix(e.toMap());
        }
        AirbnbEventLogger.track(AirbnbEventLogger.EVENT_ENGINEERING_LOG_2, strap);
    }

    private void addDefaultWebViewCallbacks() {
        addWebViewCallbacks(new AirWebViewCallbacks() {
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                if (WebViewActivity.this.getOriginalUrl().equals(failingUrl) && WebViewActivity.this.hasBackUpIntent()) {
                    WebViewActivity.this.startActivity(WebViewActivity.this.getBackUpIntent());
                    WebViewActivity.this.finish();
                }
            }

            public void onShowFileChooser(ValueCallback<Uri> filePathCallback, String acceptType) {
                WebViewActivity.this.uploadFileValueCallback = filePathCallback;
                Intent i = new Intent("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE");
                if (TextUtils.isEmpty(acceptType)) {
                    i.setType("*/*");
                } else {
                    i.setType(acceptType);
                }
                WebViewActivity.this.startActivityForResult(Intent.createChooser(i, WebViewActivity.this.getString(C0716R.string.file_chooser)), 100);
            }

            @SuppressLint({"NewApi"})
            public void onShowFileChooser(WebView view, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                WebViewActivity.this.uploadFilesValueCallback = filePathCallback;
                Intent i = new Intent("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE");
                if (fileChooserParams == null || fileChooserParams.getAcceptTypes() == null || fileChooserParams.getAcceptTypes().length <= 0) {
                    i.setType("*/*");
                } else {
                    i.setType(fileChooserParams.getAcceptTypes()[0]);
                }
                WebViewActivity.this.startActivityForResult(Intent.createChooser(i, WebViewActivity.this.getString(C0716R.string.file_chooser)), 100);
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean hasBackUpIntent() {
        return getBackUpIntent() != null;
    }

    /* access modifiers changed from: private */
    public Intent getBackUpIntent() {
        return (Intent) getIntent().getParcelableExtra(WebViewIntentBuilder.EXTRA_BACKUP);
    }

    /* access modifiers changed from: protected */
    public void addWebViewCallbacks(AirWebViewCallbacks callbacks) {
        this.airWebView.addCallbacks(callbacks);
    }

    private boolean restoreWebViewState(Bundle bundle) {
        return bundle != null && this.airWebView.restoreState(bundle);
    }

    /* access modifiers changed from: private */
    public void loadOriginalUrl() {
        if (isPostRequest()) {
            this.airWebView.postUrl(getOriginalUrl());
        } else {
            this.airWebView.loadUrl(getOriginalUrl());
        }
    }

    private boolean isPostRequest() {
        return getIntent().getExtras().getBoolean(WebViewIntentBuilder.EXTRA_POST, false);
    }

    /* access modifiers changed from: protected */
    public String getOriginalUrl() {
        return getIntent().getStringExtra(WebViewIntentBuilder.EXTRA_URL);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            boolean validData = data != null && resultCode == -1;
            if (this.uploadFilesValueCallback != null) {
                this.uploadFilesValueCallback.onReceiveValue(validData ? new Uri[]{data.getData()} : null);
            }
            if (this.uploadFileValueCallback != null) {
                ValueCallback<Uri> valueCallback = this.uploadFileValueCallback;
                if (validData) {
                    uri = data.getData();
                } else {
                    uri = null;
                }
                valueCallback.onReceiveValue(uri);
            }
            this.uploadFilesValueCallback = null;
            this.uploadFileValueCallback = null;
        }
    }

    public void onBackPressed() {
        if (this.airWebView.canGoBack()) {
            this.airWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        super.onHomeActionPressed();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.airWebView.saveState(outState);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.airWebView.onDestroy();
        super.onDestroy();
    }
}

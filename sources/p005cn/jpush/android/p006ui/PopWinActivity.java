package p005cn.jpush.android.p006ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.File;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.data.ShowEntity;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;
import p005cn.jpush.android.webview.bridge.CustomChromeClient;
import p005cn.jpush.android.webview.bridge.HostJsScope;
import p005cn.jpush.android.webview.bridge.WebViewHelper;

/* renamed from: cn.jpush.android.ui.PopWinActivity */
public class PopWinActivity extends Activity {
    private static final String TAG = "PushActivity";
    public static WebViewHelper webViewHelper = null;
    private Entity entity = null;
    private WebView mWebView;
    private String msgId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            try {
                this.entity = (Entity) getIntent().getSerializableExtra("body");
                if (this.entity != null) {
                    this.msgId = this.entity.messageId;
                    initPopview();
                    processEntity();
                    return;
                }
                Logger.m1434ww(TAG, "Warning，null message entity! Close PushActivity!");
                finish();
            } catch (Exception e) {
                Logger.m1422ee(TAG, "Extra data is not serializable!");
                e.printStackTrace();
                finish();
            }
        } else {
            Logger.m1434ww(TAG, "PushActivity get NULL intent!");
            finish();
        }
    }

    private void initPopview() {
        int popwinLayoutId = getResources().getIdentifier("jpush_popwin_layout", "layout", getPackageName());
        if (popwinLayoutId == 0) {
            Logger.m1422ee(TAG, "Please add layout resource jpush_popwin_layout.xml to res/layout !");
            finish();
            return;
        }
        setContentView(popwinLayoutId);
        int popwinViewId = getResources().getIdentifier("wvPopwin", "id", getPackageName());
        if (popwinViewId == 0) {
            Logger.m1422ee(TAG, "Please use default code in jpush_popwin_layout.xml!");
            finish();
            return;
        }
        this.mWebView = (WebView) findViewById(popwinViewId);
        if (this.mWebView == null) {
            Logger.m1422ee(TAG, "Can not get webView in layout file!");
            finish();
            return;
        }
        this.mWebView.setScrollbarFadingEnabled(true);
        this.mWebView.setScrollBarStyle(33554432);
        WebSettings webSettings = this.mWebView.getSettings();
        webSettings.setDomStorageEnabled(true);
        AndroidUtil.webSettings(webSettings);
        this.mWebView.setBackgroundColor(0);
        webViewHelper = new WebViewHelper(this, this.entity);
        this.mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
        this.mWebView.setWebChromeClient(new CustomChromeClient(JPushConstants.PARAM_JS_MODULE, HostJsScope.class));
        this.mWebView.setWebViewClient(new JPushWebViewClient(this.entity));
        HostJsScope.setWebViewHelper(webViewHelper);
    }

    private void processEntity() {
        ShowEntity showEntity = (ShowEntity) this.entity;
        String savedPath = showEntity._webPagePath;
        String showUrl = showEntity.showUrl;
        Logger.m1416d(TAG, "showUrl = " + showUrl);
        if (TextUtils.isEmpty(savedPath) || !new File(savedPath.replace("file://", "")).exists()) {
            this.mWebView.loadUrl(showUrl);
        } else {
            this.mWebView.loadUrl(savedPath);
        }
        ReportHelper.reportMsgResult(this.msgId, 1000, this);
    }

    public void onBackPressed() {
        super.onBackPressed();
        ReportHelper.reportMsgResult(this.msgId, 1006, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mWebView != null) {
            this.mWebView.onResume();
            HostJsScope.setWebViewHelper(webViewHelper);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mWebView != null) {
            this.mWebView.onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mWebView != null) {
            this.mWebView.removeAllViews();
            this.mWebView.destroy();
            this.mWebView = null;
        }
        super.onDestroy();
    }

    public void startPushActivity(String url) {
        if (this.entity != null && this.mWebView != null && (this.entity instanceof ShowEntity)) {
            if (!StringUtils.isEmpty(url)) {
                ((ShowEntity) this.entity).showUrl = url;
                Intent intent = new Intent(this, PushActivity.class);
                intent.putExtra("body", this.entity);
                intent.putExtra(PushActivity.FROM_OTHER_WAY, true);
                startActivity(intent);
            }
            finish();
        }
    }
}

package p005cn.jpush.android.p006ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.data.ShowEntity;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.webview.bridge.CustomChromeClient;
import p005cn.jpush.android.webview.bridge.HostJsScope;
import p005cn.jpush.android.webview.bridge.WebViewHelper;

/* renamed from: cn.jpush.android.ui.FullScreenView */
public class FullScreenView extends LinearLayout {
    private static final String TAG = "FullScreenView";
    public static WebViewHelper webViewHelper = null;
    private OnClickListener btnBackClickListener = new OnClickListener() {
        public void onClick(View v) {
            if (FullScreenView.this.mContext != null) {
                ((Activity) FullScreenView.this.mContext).onBackPressed();
            }
        }
    };
    private ImageButton imgBtnBack;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public WebView mWebView;
    private RelativeLayout rlTitleBar;
    private TextView tvTitle;

    public FullScreenView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public void initModule(Context context, Entity entity) {
        ShowEntity showEntity = (ShowEntity) entity;
        String showTitle = showEntity.showTitle;
        setFocusable(true);
        this.mWebView = (WebView) findViewById(getResources().getIdentifier("fullWebView", "id", context.getPackageName()));
        this.rlTitleBar = (RelativeLayout) findViewById(getResources().getIdentifier("rlRichpushTitleBar", "id", context.getPackageName()));
        this.tvTitle = (TextView) findViewById(getResources().getIdentifier("tvRichpushTitle", "id", context.getPackageName()));
        this.imgBtnBack = (ImageButton) findViewById(getResources().getIdentifier("imgRichpushBtnBack", "id", context.getPackageName()));
        if (this.mWebView == null || this.rlTitleBar == null || this.tvTitle == null || this.imgBtnBack == null) {
            Logger.m1422ee(TAG, "Please use default code in jpush_webview_layout.xml!");
            ((Activity) this.mContext).finish();
        }
        if (1 == showEntity.richType) {
            this.rlTitleBar.setVisibility(8);
            ((Activity) context).getWindow().setFlags(1024, 1024);
        } else {
            this.tvTitle.setText(showTitle);
            this.imgBtnBack.setOnClickListener(this.btnBackClickListener);
        }
        this.mWebView.setScrollbarFadingEnabled(true);
        this.mWebView.setScrollBarStyle(33554432);
        WebSettings webSettings = this.mWebView.getSettings();
        webSettings.setDomStorageEnabled(true);
        AndroidUtil.webSettings(webSettings);
        webViewHelper = new WebViewHelper(context, entity);
        this.mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
        this.mWebView.setWebChromeClient(new CustomChromeClient(JPushConstants.PARAM_JS_MODULE, HostJsScope.class));
        this.mWebView.setWebViewClient(new JPushWebViewClient(entity));
        HostJsScope.setWebViewHelper(webViewHelper);
    }

    public boolean webviewCanGoBack() {
        if (this.mWebView != null) {
            return this.mWebView.canGoBack();
        }
        return false;
    }

    public void webviewGoBack() {
        if (this.mWebView != null) {
            this.mWebView.goBack();
        }
    }

    public void loadUrl(String url) {
        if (this.mWebView != null) {
            Logger.m1416d(TAG, "loadUrl:" + url);
            this.mWebView.loadUrl(url);
        }
    }

    public void resume() {
        if (this.mWebView != null) {
            this.mWebView.onResume();
            HostJsScope.setWebViewHelper(webViewHelper);
        }
    }

    public void pause() {
        if (this.mWebView != null) {
            this.mWebView.onPause();
        }
    }

    public void destory() {
        removeAllViews();
        if (this.mWebView != null) {
            this.mWebView.removeAllViews();
            this.mWebView.destroy();
            this.mWebView = null;
        }
    }

    public void showTitleBar() {
        if (this.rlTitleBar != null && this.rlTitleBar.getVisibility() == 8) {
            this.rlTitleBar.setVisibility(0);
            quitFullScreen();
            this.imgBtnBack.setOnClickListener(this.btnBackClickListener);
            if (this.mWebView != null) {
                this.mWebView.postDelayed(new Runnable() {
                    public void run() {
                        FullScreenView.this.mWebView.clearHistory();
                    }
                }, 1000);
            }
        }
    }

    private void quitFullScreen() {
        try {
            LayoutParams attrs = ((Activity) this.mContext).getWindow().getAttributes();
            attrs.flags &= -1025;
            ((Activity) this.mContext).getWindow().setAttributes(attrs);
            ((Activity) this.mContext).getWindow().clearFlags(512);
        } catch (Exception e) {
            Logger.m1432w(TAG, "quitFullScreen errno");
        }
    }
}

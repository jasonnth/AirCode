package p005cn.jpush.android.webview.bridge;

import android.webkit.WebView;
import p005cn.jpush.android.api.SystemAlertHelper;

/* renamed from: cn.jpush.android.webview.bridge.HostJsScope */
public class HostJsScope {
    private static final String TAG = "HostJsScope";
    private static WebViewHelper mWebViewHelper;

    public static void setWebViewHelper(WebViewHelper webViewHelper) {
        if (webViewHelper != null) {
            mWebViewHelper = webViewHelper;
        }
    }

    public static void createShortcut(WebView webView, String name, String url, String iconId) {
        if (mWebViewHelper != null) {
            mWebViewHelper.createShortcut(name, url, iconId);
        }
    }

    public static void click(WebView webView, String actionId, String shouldClose, String shouldCancelNotification) {
        if (mWebViewHelper != null) {
            mWebViewHelper.click(actionId, shouldClose, shouldCancelNotification);
        }
    }

    public static void download(WebView webView, String actionId, String downladUrl, String msgType) {
        if (mWebViewHelper != null) {
            mWebViewHelper.download(actionId, downladUrl, msgType);
        }
    }

    public static void startActivityByName(WebView webView, String activityName, String params) {
        if (mWebViewHelper != null) {
            mWebViewHelper.startActivityByName(activityName, params);
        }
    }

    public static void startActivityByIntent(WebView webView, String intentName, String params) {
        if (mWebViewHelper != null) {
            mWebViewHelper.startActivityByIntent(intentName, params);
        }
    }

    public static void triggerNativeAction(WebView webView, String params) {
        if (mWebViewHelper != null) {
            mWebViewHelper.triggerNativeAction(params);
        }
    }

    public static void startMainActivity(WebView webView, String params) {
        if (mWebViewHelper != null) {
            mWebViewHelper.startMainActivity(params);
        }
    }

    public static void download(WebView webView, String actionId, String downladUrl) {
        if (mWebViewHelper != null) {
            mWebViewHelper.download(actionId, downladUrl);
        }
    }

    public static void download(WebView webView, String downladUrl) {
        if (mWebViewHelper != null) {
            mWebViewHelper.download(downladUrl);
        }
    }

    public static void close(WebView webView) {
        if (mWebViewHelper != null) {
            mWebViewHelper.close();
        }
    }

    public static void showToast(WebView webView, String toast) {
        if (mWebViewHelper != null) {
            mWebViewHelper.showToast(toast);
        }
    }

    public static void executeMsgMessage(WebView webView, String json) {
        if (mWebViewHelper != null) {
            mWebViewHelper.executeMsgMessage(json);
        }
    }

    public static void startActivityByNameWithSystemAlert(WebView webView, String activityName, String params) {
        if (SystemAlertHelper.systemAlertHelper != null) {
            SystemAlertHelper.systemAlertHelper.startActivityByName(activityName, params);
        }
    }

    public static void showTitleBar(WebView webView) {
        if (mWebViewHelper != null) {
            mWebViewHelper.showTitleBar();
        }
    }

    public static void startPushActivity(WebView webView, String url) {
        if (mWebViewHelper != null) {
            mWebViewHelper.startPushActivity(url);
        }
    }
}

package p005cn.jpush.android.webview.bridge;

import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* renamed from: cn.jpush.android.webview.bridge.InjectedChromeClient */
public class InjectedChromeClient extends WebChromeClient {
    private final String TAG = "InjectedChromeClient";
    private boolean mIsInjectedJS;
    private JsCallJava mJsCallJava;

    public InjectedChromeClient(String injectedName, Class injectedCls) {
        this.mJsCallJava = new JsCallJava(injectedName, injectedCls);
    }

    public InjectedChromeClient(JsCallJava jsCallJava) {
        this.mJsCallJava = jsCallJava;
    }

    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        result.confirm();
        return true;
    }

    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress <= 25) {
            this.mIsInjectedJS = false;
        } else if (!this.mIsInjectedJS) {
            view.loadUrl(this.mJsCallJava.getPreloadInterfaceJS());
            this.mIsInjectedJS = true;
            Log.d("InjectedChromeClient", " inject js interface completely on progress " + newProgress);
        }
        super.onProgressChanged(view, newProgress);
    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        result.confirm(this.mJsCallJava.call(view, message));
        return true;
    }
}

package com.appboy.p028ui.inappmessage;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appboy.Constants;
import com.appboy.models.IInAppMessage;
import com.appboy.p028ui.inappmessage.listeners.IInAppMessageWebViewClientListener;
import com.appboy.p028ui.support.UriUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.Map;

/* renamed from: com.appboy.ui.inappmessage.InAppMessageWebViewClient */
public class InAppMessageWebViewClient extends WebViewClient {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, InAppMessageWebViewClient.class.getName()});
    private final IInAppMessage mInAppMessage;
    private IInAppMessageWebViewClientListener mInAppMessageWebViewClientListener;

    public InAppMessageWebViewClient(IInAppMessage inAppMessage, IInAppMessageWebViewClientListener inAppMessageWebViewClientListener) {
        this.mInAppMessageWebViewClientListener = inAppMessageWebViewClientListener;
        this.mInAppMessage = inAppMessage;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (this.mInAppMessageWebViewClientListener == null) {
            AppboyLogger.m1737i(TAG, "InAppMessageWebViewClient was given null IInAppMessageWebViewClientListener listener. Returning true.");
        } else if (StringUtils.isNullOrBlank(url)) {
            AppboyLogger.m1737i(TAG, "InAppMessageWebViewClient.shouldOverrideUrlLoading was given null or blank url. Returning true.");
        } else {
            Uri uri = Uri.parse(url);
            Bundle queryBundle = getBundleFromUrl(url);
            if (uri.getScheme().equals("appboy")) {
                String authority = uri.getAuthority();
                if (authority.equals("close")) {
                    this.mInAppMessageWebViewClientListener.onCloseAction(this.mInAppMessage, url, queryBundle);
                } else if (authority.equals("feed")) {
                    this.mInAppMessageWebViewClientListener.onNewsfeedAction(this.mInAppMessage, url, queryBundle);
                } else if (authority.equals("customEvent")) {
                    this.mInAppMessageWebViewClientListener.onCustomEventAction(this.mInAppMessage, url, queryBundle);
                }
            } else {
                this.mInAppMessageWebViewClientListener.onOtherUrlAction(this.mInAppMessage, url, queryBundle);
            }
        }
        return true;
    }

    static Bundle getBundleFromUrl(String url) {
        Bundle queryBundle = new Bundle();
        if (!StringUtils.isNullOrBlank(url)) {
            Map<String, String> queryParameterMap = UriUtils.getQueryParameters(Uri.parse(url));
            for (String queryKeyName : queryParameterMap.keySet()) {
                queryBundle.putString(queryKeyName, (String) queryParameterMap.get(queryKeyName));
            }
        }
        return queryBundle;
    }
}

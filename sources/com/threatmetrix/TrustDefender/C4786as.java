package com.threatmetrix.TrustDefender;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* renamed from: com.threatmetrix.TrustDefender.as */
class C4786as extends WebChromeClient {

    /* renamed from: a */
    private final C1500u f4319a;

    /* renamed from: b */
    private final String f4320b = C4834w.m2892a(C4786as.class);

    public C4786as(C1500u m_jsInterface) {
        this.f4319a = m_jsInterface;
    }

    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        C4834w.m2901c(this.f4320b, "onJsAlert() -" + message);
        this.f4319a.mo17670a(message);
        result.confirm();
        return true;
    }
}

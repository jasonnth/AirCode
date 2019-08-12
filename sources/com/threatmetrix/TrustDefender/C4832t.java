package com.threatmetrix.TrustDefender;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.threatmetrix.TrustDefender.t */
class C4832t extends C4787at {

    /* renamed from: d */
    private static final Method f4648d;

    /* renamed from: e */
    private static final Method f4649e;

    /* renamed from: f */
    private static final Method f4650f;

    /* renamed from: g */
    private static final Method f4651g;

    /* renamed from: h */
    private static final Method f4652h;

    /* renamed from: i */
    private static final String f4653i = C4834w.m2892a(C4832t.class);

    /* renamed from: m */
    private static final TreeMap<Integer, String> f4654m;

    /* renamed from: a */
    private WebView f4655a;

    /* renamed from: b */
    private boolean f4656b;

    /* renamed from: c */
    private C1500u f4657c;

    /* renamed from: j */
    private boolean f4658j;

    /* renamed from: k */
    private WebSettings f4659k;

    /* renamed from: l */
    private final boolean f4660l;

    static {
        Method a = m2743a(WebView.class, "evaluateJavascript", String.class, ValueCallback.class);
        f4648d = a;
        if (a == null && C4800a.f4363c >= 19) {
            C4834w.m2894a(f4653i, "Failed to find expected function: evaluateJavascript");
        }
        Method a2 = m2743a(WebSettings.class, "getDefaultUserAgent", Context.class);
        f4649e = a2;
        if (a2 == null && C4800a.f4363c >= 17) {
            C4834w.m2894a(f4653i, "Failed to find expected function: getDefaultUserAgent");
        }
        Method a3 = m2743a(WebSettings.class, "setPluginState", PluginState.class);
        f4650f = a3;
        if (a3 == null && (C4800a.f4363c >= 8 || C4800a.f4363c <= 18)) {
            C4834w.m2894a(f4653i, "Failed to find expected function: setPluginState");
        }
        Method a4 = m2743a(WebView.class, "removeJavascriptInterface", String.class);
        f4651g = a4;
        if (a4 == null && C4800a.f4363c >= 11) {
            C4834w.m2894a(f4653i, "Failed to find expected function: removeJavascriptInterface");
        }
        Method a5 = m2743a(WebView.class, "addJavascriptInterface", Object.class, String.class);
        f4652h = a5;
        if (a5 == null && C4800a.f4363c >= 17) {
            C4834w.m2894a(f4653i, "Failed to find expected function: addJavascriptInterface");
        }
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        f4654m = treeMap;
        treeMap.put(Integer.valueOf(C4801b.f4366b), "533.1");
        f4654m.put(Integer.valueOf(C4801b.f4367c), "533.1");
        f4654m.put(Integer.valueOf(C4801b.f4368d), "533.1");
        f4654m.put(Integer.valueOf(C4801b.f4369e), "533.1");
        f4654m.put(Integer.valueOf(C4801b.f4370f), "534.13");
        f4654m.put(Integer.valueOf(C4801b.f4371g), "534.30");
        f4654m.put(Integer.valueOf(C4801b.f4372h), "534.30");
        f4654m.put(Integer.valueOf(C4801b.f4373i), "534.30");
        f4654m.put(Integer.valueOf(C4801b.f4374j), "534.30");
        f4654m.put(Integer.valueOf(C4801b.f4375k), "534.30");
        f4654m.put(Integer.valueOf(C4801b.f4376l), "537.36");
        f4654m.put(Integer.valueOf(C4801b.f4377m), "537.36");
        f4654m.put(Integer.valueOf(C4801b.f4378n), "537.36");
        f4654m.put(Integer.valueOf(C4801b.f4379o), "537.36");
        f4654m.put(Integer.valueOf(23), "537.36");
    }

    /* renamed from: a */
    public static boolean m2884a() {
        return f4648d != null;
    }

    /* renamed from: b */
    public static boolean m2885b() {
        try {
            return C4800a.f4361a.startsWith("2.3");
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public final String mo46091a(Context context) {
        if (C4814o.m2821a()) {
            String userAgent = (String) m2741a((Object) null, f4649e, context);
            if (userAgent != null && !userAgent.isEmpty()) {
                return userAgent;
            }
            if (this.f4660l && this.f4659k != null) {
                userAgent = this.f4659k.getUserAgentString();
            }
            if (userAgent != null && !userAgent.isEmpty()) {
                return userAgent;
            }
        }
        return m2886c();
    }

    /* renamed from: c */
    public static String m2886c() {
        String webkitVersion;
        C4834w.m2901c(f4653i, "Generating a browser string");
        if (f4654m.containsKey(Integer.valueOf(C4800a.f4363c))) {
            webkitVersion = (String) f4654m.get(Integer.valueOf(C4800a.f4363c));
        } else {
            webkitVersion = ((String) f4654m.lastEntry().getValue()) + "+";
        }
        String lang = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        if (!country.isEmpty()) {
            lang = lang + "-" + country;
        }
        return "Mozilla/5.0 (Linux; U; Android " + C4800a.f4361a + "; " + lang.toLowerCase(Locale.US) + "; " + C4799b.f4356j + " Build/" + C4799b.f4357k + ") AppleWebKit/" + webkitVersion + " (KHTML, like Gecko) Version/4.0 Mobile Safari/" + webkitVersion + " " + C4785ar.f4269a;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public C4832t(Context context, C1500u jsInterface, boolean needWebView) {
        String str;
        this.f4655a = null;
        this.f4656b = false;
        this.f4657c = null;
        this.f4658j = false;
        this.f4658j = m2885b();
        String str2 = f4653i;
        StringBuilder append = new StringBuilder("JSExecutor() Build: ").append(C4800a.f4361a).append(this.f4658j ? " busted js interface " : " normal js interface ");
        if (m2884a()) {
            str = " has async interface ";
        } else {
            str = " has no async interface ";
        }
        C4834w.m2901c(str2, append.append(str).toString());
        this.f4657c = jsInterface;
        this.f4660l = needWebView;
        if (C4814o.m2821a() && needWebView) {
            boolean hasWebView = C4767ag.m2616a();
            this.f4656b = false;
            this.f4655a = C4767ag.m2615a(context);
            if (this.f4655a != null) {
                if (hasWebView && !this.f4656b) {
                    C4834w.m2899b(f4653i, "WebView re-used from previous instance. Using a short-lived TrustDefender object is not recommended. Better profiling performance will be achieved by re-using a long-lived TrustDefender instance");
                }
                C4834w.m2901c(f4653i, "Webview " + (this.f4656b ? "init'd" : "un-init'd"));
                if (this.f4657c == null) {
                    this.f4657c = new C1500u(null);
                }
                WebViewClient m_webViewClient = new WebViewClient();
                this.f4659k = this.f4655a.getSettings();
                this.f4659k.setJavaScriptEnabled(true);
                m2741a((Object) this.f4659k, f4650f, PluginState.ON);
                this.f4655a.setVisibility(4);
                if (!this.f4658j) {
                    m2741a((Object) this.f4655a, f4651g, "androidJSInterface");
                }
                this.f4655a.setWebViewClient(m_webViewClient);
                if (m2884a()) {
                    if (this.f4657c.f1393a == null) {
                        C4834w.m2894a(f4653i, "alternate JS interface but no global latch");
                    }
                    C4834w.m2901c(f4653i, "JSExecutor() alternate JS interface detected");
                } else if (!this.f4658j) {
                    m2741a((Object) this.f4655a, f4652h, this.f4657c, "androidJSInterface");
                } else {
                    if (this.f4657c.f1393a == null) {
                        C4834w.m2894a(f4653i, "broken JS interface but no global latch");
                    }
                    C4834w.m2901c(f4653i, "JSExecutor() Broken JS interface detected, using workaround");
                    this.f4655a.setWebChromeClient(new C4786as(this.f4657c));
                }
            }
        }
    }

    /* renamed from: d */
    public final void mo46094d() throws InterruptedException {
        String html;
        if (C4814o.m2821a()) {
            C4834w.m2901c(f4653i, "init() - init'd = " + this.f4656b);
            if (this.f4656b) {
                return;
            }
            if (this.f4655a == null) {
                C4834w.m2901c(f4653i, "init() - No web view, nothing needs to be done");
                this.f4656b = true;
                return;
            }
            C4834w.m2901c(f4653i, "init() loading bogus page");
            CountDownLatch latch = null;
            if (this.f4658j || m2884a()) {
                html = "<html><head></head><body></body></html>";
            } else {
                latch = new CountDownLatch(1);
                C4834w.m2901c(f4653i, "Creating latch: " + latch.hashCode() + " with count: " + latch.getCount());
                html = "<html><head></head><body onLoad='javascript:window.androidJSInterface.getString(1)'></body></html>";
                this.f4657c.mo17671a(latch);
                this.f4657c.f1394b = null;
            }
            if (!Thread.currentThread().isInterrupted()) {
                this.f4655a.loadData(html, "text/html", null);
                if (this.f4658j || latch == null || m2884a()) {
                    this.f4656b = true;
                    return;
                }
                C4834w.m2901c(f4653i, "waiting for latch: " + latch.hashCode() + " with count: " + latch.getCount());
                if (!latch.await(5, TimeUnit.SECONDS)) {
                    C4834w.m2894a(f4653i, "timed out waiting for javascript");
                    return;
                }
                this.f4656b = true;
                C4834w.m2901c(f4653i, "in init() count = " + latch.getCount());
                if (this.f4657c.f1394b == null) {
                    C4834w.m2901c(f4653i, "init() After latch: got null");
                } else {
                    C4834w.m2901c(f4653i, "init() After latch: got " + this.f4657c.f1394b);
                }
            }
        }
    }

    /* renamed from: a */
    public final String mo46092a(String js) throws InterruptedException {
        String fullJS;
        if (!this.f4656b) {
            return null;
        }
        if (Thread.currentThread().isInterrupted()) {
            return "";
        }
        CountDownLatch latch = null;
        if (!this.f4658j && !m2884a()) {
            latch = new CountDownLatch(1);
            this.f4657c.mo17671a(latch);
        }
        if (m2884a()) {
            fullJS = "javascript:(function(){try{return " + js + " + \"\";}catch(js_eval_err){return '';}})();";
        } else if (!this.f4658j) {
            fullJS = "javascript:window.androidJSInterface.getString((function(){try{return " + js + " + \"\";}catch(js_eval_err){return '';}})());";
        } else {
            fullJS = "javascript:alert((function(){try{return " + js + " + \"\";}catch(js_eval_err){return '';}})());";
        }
        C4834w.m2901c(f4653i, "getJSResult() attempting to execute: " + fullJS);
        this.f4657c.f1394b = null;
        boolean invokeFailed = false;
        if (m2884a()) {
            try {
                f4648d.invoke(this.f4655a, new Object[]{fullJS, this.f4657c});
            } catch (IllegalAccessException e) {
                C4834w.m2895a(f4653i, "getJSResult() invoke failed: ", (Throwable) e);
                invokeFailed = true;
            } catch (IllegalArgumentException e2) {
                C4834w.m2895a(f4653i, "getJSResult() invoke failed: ", (Throwable) e2);
                invokeFailed = true;
            } catch (InvocationTargetException e3) {
                C4834w.m2895a(f4653i, "getJSResult() invoke failed: ", (Throwable) e3);
                invokeFailed = true;
            } catch (RuntimeException e4) {
                C4834w.m2895a(f4653i, "getJSResult() invoke failed: ", (Throwable) e4);
                invokeFailed = true;
            }
        } else {
            try {
                this.f4655a.loadUrl(fullJS);
            } catch (RuntimeException e5) {
                C4834w.m2895a(f4653i, "getJSResult() invoke failed: ", (Throwable) e5);
                invokeFailed = true;
            }
        }
        if (invokeFailed) {
            if (this.f4657c.f1393a == null) {
                return null;
            }
            C4834w.m2901c(f4653i, "getJSResult countdown for latch: " + this.f4657c.f1393a.hashCode() + " with count: " + this.f4657c.f1393a.getCount());
            this.f4657c.f1393a.countDown();
            return null;
        } else if (this.f4658j || m2884a()) {
            return null;
        } else {
            if (latch != null) {
                C4834w.m2901c(f4653i, "getJSResult waiting for latch: " + latch.hashCode() + " with count: " + latch.getCount());
                if (!latch.await(5, TimeUnit.SECONDS)) {
                    C4834w.m2901c(f4653i, "getJSResult timeout waiting for latch: " + latch.hashCode() + " with count: " + latch.getCount());
                }
            } else {
                C4834w.m2894a(f4653i, "latch == null");
            }
            if (this.f4657c.f1394b == null) {
                C4834w.m2901c(f4653i, "getJSResult() After latch: got null");
            } else {
                C4834w.m2901c(f4653i, "getJSResult() After latch: got " + this.f4657c.f1394b);
            }
            return this.f4657c.f1394b;
        }
    }

    /* renamed from: a */
    public final boolean mo46093a(boolean needWebview) {
        return needWebview != this.f4660l || !this.f4656b;
    }
}

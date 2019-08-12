package com.threatmetrix.TrustDefender;

import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.threatmetrix.TrustDefender.u */
class C1500u implements ValueCallback<String> {

    /* renamed from: d */
    private static final String f1392d = C4834w.m2892a(C1500u.class);

    /* renamed from: a */
    CountDownLatch f1393a = null;

    /* renamed from: b */
    public String f1394b;

    /* renamed from: c */
    public final ArrayList<String> f1395c = new ArrayList<>();

    public /* synthetic */ void onReceiveValue(Object x0) {
        String str = (String) x0;
        if (str != null) {
            if (str.length() == 2 && str.equals("\"\"")) {
                str = "";
            } else if (str.length() > 1) {
                str = str.substring(1, str.length() - 1);
            }
        }
        m1302a(str, "onReceiveValue");
    }

    C1500u(CountDownLatch latch) {
        mo17671a(latch);
    }

    /* renamed from: a */
    public final void mo17671a(CountDownLatch latch) {
        if (this.f1393a != null) {
            C4834w.m2901c(f1392d, "existing latch: " + this.f1393a.hashCode() + " with count: " + this.f1393a.getCount());
            C4834w.m2901c(f1392d, "Setting latch when latch already has non-null value");
        }
        this.f1393a = latch;
        if (this.f1393a != null) {
            C4834w.m2901c(f1392d, "new latch: " + latch.hashCode() + " with count: " + latch.getCount());
        }
    }

    /* renamed from: a */
    private void m1302a(String value, String source) {
        try {
            CountDownLatch l = this.f1393a;
            String log_message = value;
            if (value == null) {
                log_message = "null";
            }
            long count = 0;
            if (l != null) {
                count = l.getCount();
            }
            C4834w.m2901c(f1392d, "in " + source + "(" + log_message + ") count = " + count);
            this.f1394b = value;
            if (value == null) {
                this.f1395c.add("");
            } else {
                this.f1395c.add(value);
            }
            if (l != null) {
                C4834w.m2901c(f1392d, "countdown latch: " + l.hashCode() + " with count: " + l.getCount());
                l.countDown();
                if (source == null) {
                    source = "null";
                }
                if (l == null) {
                    C4834w.m2901c(f1392d, "in " + source + "() with null latch");
                } else {
                    C4834w.m2901c(f1392d, "in " + source + "() count = " + l.getCount() + " and " + (l == this.f1393a ? "latch constant" : "latch changed"));
                }
            } else {
                C4834w.m2894a(f1392d, "in " + source + "() latch == null");
            }
        } catch (Exception e) {
            String str = f1392d;
        }
    }

    @JavascriptInterface
    /* renamed from: a */
    public final void mo17670a(String value) {
        m1302a(value, "getString");
    }
}

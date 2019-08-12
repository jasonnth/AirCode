package com.threatmetrix.TrustDefender;

import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.ae */
class C4765ae extends C4787at {

    /* renamed from: a */
    private static final Method f4190a;

    /* renamed from: b */
    private static final Method f4191b;

    /* renamed from: c */
    private static final String f4192c = C4834w.m2892a(C4765ae.class);

    /* renamed from: d */
    private String f4193d = null;

    /* renamed from: e */
    private int f4194e = 0;

    static {
        Class<?> proxyClass = m2745b("android.net.Proxy");
        f4190a = m2743a((Class) proxyClass, "getDefaultHost", new Class[0]);
        f4191b = m2743a((Class) proxyClass, "getDefaultPort", new Class[0]);
    }

    C4765ae() {
        String host = System.getProperty("http.proxyHost");
        if (host != null && !host.isEmpty()) {
            this.f4193d = host;
        }
        String portStr = System.getProperty("http.proxyPort");
        if (portStr != null && !portStr.isEmpty()) {
            try {
                this.f4194e = Integer.parseInt(portStr);
            } catch (NumberFormatException e) {
            }
        }
        if (this.f4193d == null || this.f4194e == 0) {
            Integer p = (Integer) m2741a((Object) null, f4191b, new Object[0]);
            if (p != null) {
                this.f4194e = p.intValue();
            }
            String h = (String) m2741a((Object) null, f4190a, new Object[0]);
            if (h != null) {
                this.f4193d = h;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final String mo45962a() {
        return this.f4193d;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final int mo45963b() {
        return this.f4194e;
    }
}

package com.paypal.android.sdk.onetouch.core.metadata;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.l */
public final class C4686l {

    /* renamed from: a */
    private static String f4017a = null;

    /* renamed from: b */
    private static long f4018b = 0;

    /* renamed from: c */
    private static long f4019c = 0;

    /* renamed from: a */
    public static synchronized void m2467a() {
        synchronized (C4686l.class) {
            f4017a = C4677ai.m2400b(Boolean.TRUE.booleanValue());
            f4018b = System.currentTimeMillis();
        }
    }

    /* renamed from: a */
    public static synchronized void m2468a(long j) {
        synchronized (C4686l.class) {
            f4019c = j;
        }
    }

    /* renamed from: b */
    public static synchronized String m2469b() {
        String str;
        synchronized (C4686l.class) {
            if (f4017a == null) {
                m2467a();
            }
            str = f4017a;
        }
        return str;
    }

    /* renamed from: c */
    public static synchronized boolean m2470c() {
        boolean z;
        synchronized (C4686l.class) {
            z = System.currentTimeMillis() - m2471d() <= f4019c;
        }
        return z;
    }

    /* renamed from: d */
    private static synchronized long m2471d() {
        long j;
        synchronized (C4686l.class) {
            if (f4018b == 0) {
                m2467a();
            }
            j = f4018b;
        }
        return j;
    }
}

package com.threatmetrix.TrustDefender;

import android.graphics.Point;
import android.view.Display;
import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.j */
class C4817j extends C4787at {

    /* renamed from: a */
    private static final String f4536a = C4834w.m2892a(C4817j.class);

    /* renamed from: b */
    private static final Class<?> f4537b = m2745b("android.graphics.Point");

    /* renamed from: c */
    private static final Class<?> f4538c = m2745b("android.view.WindowManager");

    /* renamed from: d */
    private static final Method f4539d = m2743a(Display.class, "getWidth", new Class[0]);

    /* renamed from: e */
    private static final Method f4540e = m2743a(Display.class, "getHeight", new Class[0]);

    /* renamed from: f */
    private static final Method f4541f;

    /* renamed from: g */
    private static final Method f4542g;

    /* renamed from: h */
    private static final Method f4543h;

    /* renamed from: i */
    private static final Method f4544i;

    /* renamed from: j */
    private static final Method f4545j = m2743a((Class) f4538c, "getDefaultDisplay", new Class[0]);

    /* renamed from: k */
    private Display f4546k;

    /* renamed from: l */
    private Point f4547l;

    static {
        if (f4537b != null) {
            f4541f = m2743a(Display.class, "getSize", Point.class);
            f4542g = m2743a(Display.class, "getRealSize", Point.class);
            f4543h = m2743a(Display.class, "getRawWidth", Point.class);
            f4544i = m2743a(Display.class, "getRawHeight", Point.class);
            return;
        }
        f4544i = null;
        f4543h = null;
        f4542g = null;
        f4541f = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003d, code lost:
        if (r2.y != 0) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C4817j(android.content.Context r9) {
        /*
            r8 = this;
            r3 = 0
            r8.<init>()
            java.lang.reflect.Method r2 = f4545j
            if (r2 == 0) goto L_0x001d
            java.lang.String r2 = "window"
            java.lang.Object r1 = r9.getSystemService(r2)     // Catch:{ SecurityException -> 0x0042, Exception -> 0x0046 }
            if (r1 == 0) goto L_0x001d
            boolean r2 = r1 instanceof android.view.WindowManager     // Catch:{ SecurityException -> 0x0042, Exception -> 0x0046 }
            if (r2 == 0) goto L_0x001d
            android.view.WindowManager r1 = (android.view.WindowManager) r1     // Catch:{ SecurityException -> 0x0042, Exception -> 0x0046 }
            android.view.Display r2 = r1.getDefaultDisplay()     // Catch:{ SecurityException -> 0x0042, Exception -> 0x0046 }
            r8.f4546k = r2     // Catch:{ SecurityException -> 0x0042, Exception -> 0x0046 }
        L_0x001d:
            java.lang.Class<?> r2 = f4537b
            if (r2 == 0) goto L_0x0053
            java.lang.reflect.Method r2 = f4542g
            if (r2 == 0) goto L_0x0051
            android.graphics.Point r2 = new android.graphics.Point
            r2.<init>()
            android.view.Display r4 = r8.f4546k
            java.lang.reflect.Method r5 = f4542g
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r7 = 0
            r6[r7] = r2
            m2741a(r4, r5, r6)
            int r4 = r2.x
            if (r4 == 0) goto L_0x0051
            int r4 = r2.y
            if (r4 == 0) goto L_0x0051
        L_0x003f:
            r8.f4547l = r2
            return
        L_0x0042:
            r2 = move-exception
            java.lang.String r2 = f4536a
            goto L_0x001d
        L_0x0046:
            r0 = move-exception
            java.lang.String r2 = f4536a
            java.lang.String r4 = r0.getMessage()
            com.threatmetrix.TrustDefender.C4834w.m2901c(r2, r4)
            goto L_0x001d
        L_0x0051:
            r2 = r3
            goto L_0x003f
        L_0x0053:
            r2 = r3
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4817j.<init>(android.content.Context):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int mo46057a() {
        /*
            r7 = this;
            r4 = 0
            android.view.Display r3 = r7.f4546k
            if (r3 != 0) goto L_0x0007
            r2 = r4
        L_0x0006:
            return r2
        L_0x0007:
            android.graphics.Point r3 = r7.f4547l
            if (r3 == 0) goto L_0x0010
            android.graphics.Point r3 = r7.f4547l
            int r2 = r3.x
            goto L_0x0006
        L_0x0010:
            java.lang.reflect.Method r3 = f4543h
            if (r3 == 0) goto L_0x0040
            android.view.Display r3 = r7.f4546k
            java.lang.reflect.Method r5 = f4543h
            java.lang.Object[] r6 = new java.lang.Object[r4]
            java.lang.Object r3 = m2741a(r3, r5, r6)
            java.lang.Integer r3 = (java.lang.Integer) r3
            if (r3 == 0) goto L_0x0040
            int r2 = r3.intValue()
        L_0x0026:
            if (r2 != 0) goto L_0x0006
            java.lang.reflect.Method r3 = f4541f
            if (r3 == 0) goto L_0x0042
            android.graphics.Point r0 = new android.graphics.Point
            r0.<init>()
            android.view.Display r3 = r7.f4546k
            java.lang.reflect.Method r5 = f4541f
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r4] = r0
            m2741a(r3, r5, r6)
            int r2 = r0.x
            goto L_0x0006
        L_0x0040:
            r2 = r4
            goto L_0x0026
        L_0x0042:
            java.lang.reflect.Method r3 = f4539d
            if (r3 == 0) goto L_0x0059
            android.view.Display r3 = r7.f4546k
            java.lang.reflect.Method r5 = f4539d
            java.lang.Object[] r6 = new java.lang.Object[r4]
            java.lang.Object r1 = m2741a(r3, r5, r6)
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 == 0) goto L_0x0059
            int r2 = r1.intValue()
            goto L_0x0006
        L_0x0059:
            java.lang.String r3 = f4536a
            r2 = r4
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4817j.mo46057a():int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int mo46058b() {
        /*
            r7 = this;
            r4 = 0
            android.view.Display r3 = r7.f4546k
            if (r3 != 0) goto L_0x0007
            r0 = r4
        L_0x0006:
            return r0
        L_0x0007:
            android.graphics.Point r3 = r7.f4547l
            if (r3 == 0) goto L_0x0010
            android.graphics.Point r3 = r7.f4547l
            int r0 = r3.y
            goto L_0x0006
        L_0x0010:
            java.lang.reflect.Method r3 = f4544i
            if (r3 == 0) goto L_0x0040
            android.view.Display r3 = r7.f4546k
            java.lang.reflect.Method r5 = f4544i
            java.lang.Object[] r6 = new java.lang.Object[r4]
            java.lang.Object r3 = m2741a(r3, r5, r6)
            java.lang.Integer r3 = (java.lang.Integer) r3
            if (r3 == 0) goto L_0x0040
            int r0 = r3.intValue()
        L_0x0026:
            if (r0 != 0) goto L_0x0006
            java.lang.reflect.Method r3 = f4541f
            if (r3 == 0) goto L_0x0042
            android.graphics.Point r1 = new android.graphics.Point
            r1.<init>()
            android.view.Display r3 = r7.f4546k
            java.lang.reflect.Method r5 = f4541f
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r4] = r1
            m2741a(r3, r5, r6)
            int r0 = r1.y
            goto L_0x0006
        L_0x0040:
            r0 = r4
            goto L_0x0026
        L_0x0042:
            java.lang.reflect.Method r3 = f4540e
            if (r3 == 0) goto L_0x0059
            android.view.Display r3 = r7.f4546k
            java.lang.reflect.Method r5 = f4540e
            java.lang.Object[] r6 = new java.lang.Object[r4]
            java.lang.Object r2 = m2741a(r3, r5, r6)
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 == 0) goto L_0x0059
            int r0 = r2.intValue()
            goto L_0x0006
        L_0x0059:
            java.lang.String r3 = f4536a
            r0 = r4
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4817j.mo46058b():int");
    }
}

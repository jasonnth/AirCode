package com.threatmetrix.TrustDefender;

import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.ah */
class C4769ah extends C4787at {

    /* renamed from: a */
    private static final Class<?> f4206a;

    /* renamed from: b */
    private static final Method f4207b;

    /* renamed from: c */
    private static final Method f4208c = m2743a((Class) f4206a, "getBlockSizeLong", new Class[0]);

    /* renamed from: d */
    private static final Method f4209d = m2743a((Class) f4206a, "getAvailableBlocks", new Class[0]);

    /* renamed from: e */
    private static final Method f4210e = m2743a((Class) f4206a, "getAvailableBlocksLong", new Class[0]);

    /* renamed from: f */
    private static final Method f4211f = m2743a((Class) f4206a, "getBlockCount", new Class[0]);

    /* renamed from: g */
    private static final Method f4212g = m2743a((Class) f4206a, "getBlockCountLong", new Class[0]);

    /* renamed from: h */
    private static final String f4213h = C4834w.m2892a(C4769ah.class);

    /* renamed from: i */
    private final Object f4214i;

    static {
        Class<?> b = m2745b("android.os.StatFs");
        f4206a = b;
        f4207b = m2743a((Class) b, "getBlockSize", new Class[0]);
    }

    public C4769ah(String path) {
        if (f4206a == null) {
            this.f4214i = null;
            return;
        }
        this.f4214i = C4787at.m2738a((Class) f4206a, new Class[]{String.class}, new Object[]{path});
    }

    /* renamed from: a */
    public final long mo45966a() {
        if (f4212g != null) {
            Long ret = (Long) m2741a(this.f4214i, f4212g, new Object[0]);
            if (ret != null) {
                return ret.longValue();
            }
        }
        if (f4211f != null) {
            Integer ret2 = (Integer) m2741a(this.f4214i, f4211f, new Object[0]);
            if (ret2 != null) {
                return (long) ret2.intValue();
            }
        }
        return 0;
    }

    /* renamed from: b */
    public final long mo45967b() {
        if (f4208c != null) {
            Long ret = (Long) m2741a(this.f4214i, f4208c, new Object[0]);
            if (ret != null) {
                return ret.longValue();
            }
        }
        if (f4207b != null) {
            Integer ret2 = (Integer) m2741a(this.f4214i, f4207b, new Object[0]);
            if (ret2 != null) {
                return (long) ret2.intValue();
            }
        }
        return 0;
    }

    /* renamed from: c */
    public final long mo45968c() {
        if (f4210e != null) {
            Long ret = (Long) m2741a(this.f4214i, f4210e, new Object[0]);
            if (ret != null) {
                return ret.longValue();
            }
        }
        if (f4209d != null) {
            Integer ret2 = (Integer) m2741a(this.f4214i, f4209d, new Object[0]);
            if (ret2 != null) {
                return (long) ret2.intValue();
            }
        }
        return 0;
    }
}

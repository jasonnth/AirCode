package p004bo.app;

import android.util.Log;

/* renamed from: bo.app.hn */
public final class C0599hn {

    /* renamed from: a */
    private static volatile boolean f805a = false;

    /* renamed from: b */
    private static volatile boolean f806b = true;

    /* renamed from: a */
    public static void m1062a(boolean z) {
        f805a = z;
    }

    /* renamed from: a */
    public static void m1060a(String str, Object... objArr) {
        if (f805a) {
            m1059a(3, null, str, objArr);
        }
    }

    /* renamed from: b */
    public static void m1063b(String str, Object... objArr) {
        m1059a(4, null, str, objArr);
    }

    /* renamed from: c */
    public static void m1064c(String str, Object... objArr) {
        m1059a(5, null, str, objArr);
    }

    /* renamed from: a */
    public static void m1061a(Throwable th) {
        m1059a(6, th, null, new Object[0]);
    }

    /* renamed from: d */
    public static void m1065d(String str, Object... objArr) {
        m1059a(6, null, str, objArr);
    }

    /* renamed from: a */
    private static void m1059a(int i, Throwable th, String str, Object... objArr) {
        String str2;
        if (f806b) {
            if (objArr.length > 0) {
                str2 = String.format(str, objArr);
            } else {
                str2 = str;
            }
            if (th != null) {
                if (str2 == null) {
                    str2 = th.getMessage();
                }
                str2 = String.format("%1$s\n%2$s", new Object[]{str2, Log.getStackTraceString(th)});
            }
            Log.println(i, C0543gf.f624a, str2);
        }
    }
}

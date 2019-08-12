package jumio.p317nv.nfc;

import com.jumio.commons.log.Log;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/* renamed from: jumio.nv.nfc.f */
/* compiled from: NfcLogger */
public class C5100f {

    /* renamed from: a */
    public static File f5572a;

    static {
        m3690a();
    }

    /* renamed from: a */
    private static void m3690a() {
        m3693a(null);
    }

    /* renamed from: a */
    public static void m3693a(String str) {
    }

    /* renamed from: a */
    public static void m3694a(String str, String str2) {
        Log.m1909d(str, str2);
        m3692a(f5572a, str + " :: " + str2);
    }

    /* renamed from: b */
    public static void m3696b(String str, String str2) {
        Log.m1919i(str, str2);
        m3692a(f5572a, str + " :: " + str2);
    }

    /* renamed from: c */
    public static void m3698c(String str, String str2) {
        Log.m1929w(str, str2);
        m3692a(f5572a, str + " :: " + str2);
    }

    /* renamed from: a */
    public static void m3695a(String str, String str2, Exception exc) {
        Log.m1930w(str, str2, (Throwable) exc);
        m3692a(f5572a, str + " :: " + str2);
        m3691a(f5572a, exc);
    }

    /* renamed from: b */
    public static void m3697b(String str, String str2, Exception exc) {
        Log.m1915e(str, str2, (Throwable) exc);
        m3692a(f5572a, str + " :: " + str2);
        m3691a(f5572a, exc);
    }

    /* renamed from: a */
    private static void m3691a(File file, Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        m3692a(file, stringWriter.toString());
    }

    /* renamed from: a */
    private static synchronized void m3692a(File file, String str) {
        synchronized (C5100f.class) {
        }
    }
}

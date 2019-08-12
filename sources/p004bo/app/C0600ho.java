package p004bo.app;

import java.util.Comparator;

/* renamed from: bo.app.ho */
public final class C0600ho {
    /* renamed from: a */
    public static String m1066a(String str, C0563gp gpVar) {
        return "_" + gpVar.mo7183a() + "x" + gpVar.mo7186b();
    }

    /* renamed from: a */
    public static Comparator<String> m1067a() {
        return new Comparator<String>() {
            /* renamed from: a */
            public int compare(String str, String str2) {
                return str.substring(0, str.lastIndexOf("_")).compareTo(str2.substring(0, str2.lastIndexOf("_")));
            }
        };
    }
}

package jumio.p317nv.nfc;

import com.facebook.react.uimanager.ViewProps;
import java.util.Enumeration;
import java.util.Properties;

/* renamed from: jumio.nv.nfc.dt */
/* compiled from: ParameterList */
public class C5079dt extends Properties {
    public C5079dt() {
    }

    public C5079dt(C5079dt dtVar) {
        super(dtVar);
    }

    /* renamed from: a */
    public C5079dt mo47132a() {
        return (C5079dt) this.defaults;
    }

    /* renamed from: a */
    public String mo47131a(String str) {
        String str2 = (String) get(str);
        if (str2 != null || this.defaults == null) {
            return str2;
        }
        return this.defaults.getProperty(str);
    }

    /* renamed from: b */
    public boolean mo47135b(String str) {
        String a = mo47131a(str);
        if (a == null) {
            throw new IllegalArgumentException("No parameter with name " + str);
        } else if (a.equals(ViewProps.f3131ON)) {
            return true;
        } else {
            if (a.equals("off")) {
                return false;
            }
            throw new C5080du("Parameter \"" + str + "\" is not boolean: " + a);
        }
    }

    /* renamed from: c */
    public int mo47136c(String str) {
        String a = mo47131a(str);
        if (a == null) {
            throw new IllegalArgumentException("No parameter with name " + str);
        }
        try {
            return Integer.parseInt(a);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Parameter \"" + str + "\" is not integer: " + e.getMessage());
        }
    }

    /* renamed from: d */
    public float mo47137d(String str) {
        String a = mo47131a(str);
        if (a == null) {
            throw new IllegalArgumentException("No parameter with name " + str);
        }
        try {
            return new Float(a).floatValue();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Parameter \"" + str + "\" is not floating-point: " + e.getMessage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0005 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo47133a(char r6, java.lang.String[] r7) {
        /*
            r5 = this;
            r2 = 0
            java.util.Enumeration r3 = r5.propertyNames()
        L_0x0005:
            boolean r0 = r3.hasMoreElements()
            if (r0 == 0) goto L_0x005a
            java.lang.Object r0 = r3.nextElement()
            java.lang.String r0 = (java.lang.String) r0
            int r1 = r0.length()
            if (r1 <= 0) goto L_0x0005
            char r1 = r0.charAt(r2)
            if (r1 != r6) goto L_0x0005
            if (r7 == 0) goto L_0x005b
            int r1 = r7.length
            int r1 = r1 + -1
        L_0x0022:
            if (r1 < 0) goto L_0x005b
            r4 = r7[r1]
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x0057
            r1 = 1
        L_0x002d:
            if (r1 != 0) goto L_0x0005
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Option '"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r2 = "' is "
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = "not a valid one."
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0057:
            int r1 = r1 + -1
            goto L_0x0022
        L_0x005a:
            return
        L_0x005b:
            r1 = r2
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C5079dt.mo47133a(char, java.lang.String[]):void");
    }

    /* renamed from: a */
    public void mo47134a(char[] cArr, String[] strArr) {
        boolean z;
        Enumeration propertyNames = propertyNames();
        String str = new String(cArr);
        while (propertyNames.hasMoreElements()) {
            String str2 = (String) propertyNames.nextElement();
            if (str2.length() > 0 && str.indexOf(str2.charAt(0)) == -1) {
                if (strArr != null) {
                    int length = strArr.length - 1;
                    while (true) {
                        if (length < 0) {
                            break;
                        } else if (str2.equals(strArr[length])) {
                            z = true;
                            break;
                        } else {
                            length--;
                        }
                    }
                }
                z = false;
                if (!z) {
                    throw new IllegalArgumentException("Option '" + str2 + "' is " + "not a valid one.");
                }
            }
        }
    }

    /* renamed from: a */
    public static String[] m3577a(String[][] strArr) {
        if (strArr == null) {
            return null;
        }
        String[] strArr2 = new String[strArr.length];
        for (int length = strArr.length - 1; length >= 0; length--) {
            strArr2[length] = strArr[length][0];
        }
        return strArr2;
    }
}

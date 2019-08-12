package com.paypal.android.sdk.onetouch.core.metadata;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.ag */
public class C4675ag {

    /* renamed from: a */
    public static final C4675ag f3827a = new C4675ag();

    /* renamed from: b */
    private static final String f3828b = C4675ag.class.getSimpleName();

    /* renamed from: c */
    private byte[] f3829c;

    private C4675ag() {
        this.f3829c = null;
        this.f3829c = null;
    }

    public C4675ag(String str) {
        this.f3829c = null;
        try {
            this.f3829c = new byte[((str.length() + 1) / 2)];
            int i = 0;
            int length = str.length() - 1;
            while (length >= 0) {
                int i2 = length - 1;
                if (i2 < 0) {
                    i2 = 0;
                }
                this.f3829c[i] = (byte) Integer.parseInt(str.substring(i2, length + 1), 16);
                length -= 2;
                i++;
            }
        } catch (Exception e) {
            C4677ai.m2391a(f3828b, "PPRiskDataBitSet initialize failed", (Throwable) e);
            this.f3829c = null;
        }
    }

    /* renamed from: a */
    public final boolean mo45403a(C4676ah ahVar) {
        int a = ahVar.mo45404a() / 8;
        if (this.f3829c == null) {
            return true;
        }
        if (a >= this.f3829c.length) {
            return false;
        }
        byte a2 = (byte) (1 << (ahVar.mo45404a() % 8));
        return (this.f3829c[a] & a2) == a2;
    }
}

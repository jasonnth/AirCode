package visidon.Lib;

public class VerificationAPI {

    /* renamed from: visidon.Lib.VerificationAPI$a */
    public enum C5527a {
        STILL(2),
        VIDEO(1);
        

        /* renamed from: c */
        private final int f7263c;

        private C5527a(int i) {
            this.f7263c = i;
        }

        /* renamed from: a */
        public final int mo55636a() {
            return this.f7263c;
        }
    }

    /* renamed from: visidon.Lib.VerificationAPI$b */
    public enum C5528b {
        AUTOMATIC(2),
        MANUAL(1);
        

        /* renamed from: c */
        private final int f7267c;

        private C5528b(int i) {
            this.f7267c = i;
        }

        /* renamed from: a */
        public final int mo55637a() {
            return this.f7267c;
        }
    }

    /* renamed from: visidon.Lib.VerificationAPI$d */
    public enum C5529d {
        OK(0),
        FAILED(1);

        private C5529d(int i) {
        }
    }

    /* renamed from: visidon.Lib.VerificationAPI$e */
    public enum C5530e {
        HIGH(55),
        LOW(30),
        OFF(0);
        

        /* renamed from: d */
        private final int f7275d;

        private C5530e(int i) {
            this.f7275d = i;
        }

        /* renamed from: a */
        public final int mo55638a() {
            return this.f7275d;
        }
    }

    /* renamed from: visidon.Lib.VerificationAPI$f */
    public enum C5531f {
        OK(0),
        FAILED(1);

        private C5531f(int i) {
        }
    }

    /* renamed from: visidon.Lib.VerificationAPI$h */
    public enum C5532h {
        MAXIMUM(100),
        MEDIUM(90),
        LOW(85);
        

        /* renamed from: d */
        private final int f7283d;

        private C5532h(int i) {
            this.f7283d = i;
        }

        /* renamed from: a */
        public final int mo55639a() {
            return this.f7283d;
        }
    }

    /* renamed from: visidon.Lib.VerificationAPI$i */
    public enum C5533i {
        ALLOW(0),
        DENY(1),
        NO_FACE(2),
        TOO_MANY_FACES(3),
        NO_LANDMARKS(4),
        ERROR(5);

        private C5533i(int i) {
        }
    }

    /* renamed from: a */
    private static native int m4049a(int i, int i2, String str, int i3, int i4, int i5, int i6, Object obj);

    /* renamed from: b */
    private static native int m4053b();

    /* renamed from: c */
    private static native int m4055c(byte[] bArr);

    private static native int[] ddd(byte[] bArr);

    /* renamed from: e */
    private static native int m4056e();

    /* renamed from: l */
    private static native int m4057l(byte[] bArr);

    /* renamed from: a */
    public static C5529d m4051a(byte[] bArr) {
        if (m4057l(bArr) == 1) {
            return C5529d.OK;
        }
        return C5529d.FAILED;
    }

    /* renamed from: a */
    public static C5529d m4050a(C5535b bVar) {
        if (bVar.f7299b == 0 || bVar.f7300c == 0) {
            return C5529d.FAILED;
        }
        return C5529d.values()[m4049a(bVar.f7299b, bVar.f7300c, bVar.f7301d, bVar.f7302e.mo55636a(), bVar.f7303f.mo55639a(), bVar.f7304g.mo55638a(), bVar.f7305h.mo55637a(), bVar.f7298a)];
    }

    /* renamed from: a */
    public static C5531f m4052a() {
        return C5531f.values()[m4053b()];
    }

    /* renamed from: b */
    public static C5534a m4054b(byte[] bArr) {
        return new C5534a(ddd(bArr));
    }

    static {
        System.loadLibrary("VDFaceVerificationAPI-jni");
    }
}

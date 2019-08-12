package jumio.p317nv.nfc;

import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* renamed from: jumio.nv.nfc.dp */
/* compiled from: ROIDeScaler */
public class C5075dp extends C5092ef implements C5069dj {

    /* renamed from: b */
    private static final String[][] f5525b = {new String[]{"Rno_roi", null, "", null}};

    /* renamed from: a */
    private C5074do f5526a;

    /* renamed from: c */
    private C5069dj f5527c;

    public C5075dp(C5069dj djVar, C5074do doVar) {
        super(djVar);
        this.f5527c = djVar;
        this.f5526a = doVar;
    }

    /* renamed from: e */
    public C5093eg mo47019e(int i, int i2) {
        return this.f5527c.mo47019e(i, i2);
    }

    /* renamed from: a */
    public static String[][] m3559a() {
        return f5525b;
    }

    /* renamed from: a */
    public C5054cv mo47090a(int i, int i2, int i3, C5093eg egVar, C5054cv cvVar) {
        return mo47091b(i, i2, i3, egVar, cvVar);
    }

    /* renamed from: b */
    public C5054cv mo47091b(int i, int i2, int i3, C5093eg egVar, C5054cv cvVar) {
        C5054cv b = this.f5527c.mo47091b(i, i2, i3, egVar, cvVar);
        boolean z = false;
        if (this.f5526a == null || this.f5526a.mo46970a(mo47013d(), i) == null) {
            z = true;
        }
        if (z || b == null) {
            return b;
        }
        int[] iArr = (int[]) b.mo47103b();
        int i4 = b.f5477a;
        int i5 = b.f5478b;
        int i6 = b.f5479c;
        int i7 = b.f5480d;
        int intValue = ((Integer) this.f5526a.mo46970a(mo47013d(), i)).intValue();
        int i8 = ((1 << egVar.f5566r) - 1) << (31 - egVar.f5566r);
        int i9 = (i8 ^ -1) & Integer.MAX_VALUE;
        int i10 = b.f5482f - i6;
        int i11 = ((b.f5481e + (b.f5482f * (i7 - 1))) + i6) - 1;
        int i12 = i7;
        while (i12 > 0) {
            int i13 = i11;
            int i14 = i6;
            while (i14 > 0) {
                int i15 = iArr[i13];
                if ((i15 & i8) == 0) {
                    iArr[i13] = (i15 << intValue) | (Integer.MIN_VALUE & i15);
                } else if ((i15 & i9) != 0) {
                    iArr[i13] = (i15 & (i9 ^ -1)) | (1 << (30 - egVar.f5566r));
                }
                i14--;
                i13--;
            }
            i12--;
            i11 = i13 - i10;
        }
        return b;
    }

    /* renamed from: a */
    public static C5075dp m3558a(C5069dj djVar, C5079dt dtVar, C5039cg cgVar) {
        dtVar.mo47133a((char) Matrix.MATRIX_TYPE_RANDOM_REGULAR, C5079dt.m3577a(f5525b));
        if (dtVar.mo47131a("Rno_roi") != null || cgVar.f5402b == null) {
            return new C5075dp(djVar, null);
        }
        return new C5075dp(djVar, cgVar.f5402b);
    }
}

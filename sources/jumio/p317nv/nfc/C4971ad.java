package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.ad */
/* compiled from: Resampler */
public class C4971ad extends C4967aa {

    /* renamed from: s */
    final int f4965s = 0;

    /* renamed from: t */
    final int f4966t = 0;

    /* renamed from: a */
    public static C5051cs m3214a(C5051cs csVar, C5140y yVar) throws C5144z {
        return new C4971ad(csVar, yVar);
    }

    protected C4971ad(C5051cs csVar, C5140y yVar) throws C5144z {
        super(csVar, yVar);
        int e = csVar.mo46944e(0);
        int f = csVar.mo46945f(0);
        int i = f;
        int i2 = e;
        for (int i3 = 1; i3 < this.f4950o; i3++) {
            i2 = Math.min(i2, csVar.mo46944e(i3));
            i = Math.min(i, csVar.mo46945f(i3));
            e = Math.max(e, csVar.mo46944e(i3));
            f = Math.max(f, csVar.mo46945f(i3));
        }
        if ((e != 1 && e != 2) || (f != 1 && f != 2)) {
            throw new C5144z("Upsampling by other than 2:1 not supported");
        }
    }

    /* renamed from: b */
    public C5054cv mo46936b(C5054cv cvVar, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        if (this.f4951p.mo46944e(i) == 1 && this.f4951p.mo46945f(i) == 1) {
            return this.f4951p.mo46936b(cvVar, i);
        }
        int e = this.f4951p.mo46944e(i);
        int f = this.f4951p.mo46945f(i);
        if ((e == 2 || e == 1) && (f == 2 || f == 1)) {
            int i8 = cvVar.f5478b;
            int i9 = (cvVar.f5480d + i8) - 1;
            int i10 = cvVar.f5477a;
            int i11 = (cvVar.f5479c + i10) - 1;
            int i12 = i8 / f;
            int i13 = i10 / e;
            int i14 = ((i11 / e) - i13) + 1;
            int i15 = ((i9 / f) - i12) + 1;
            switch (cvVar.mo47101a()) {
                case 3:
                    C5056cx cxVar = (C5056cx) this.f4951p.mo46936b(new C5056cx(i13, i12, i14, i15), i);
                    this.f4941f[i] = cxVar.mo47105c();
                    int[] iArr = (int[]) cvVar.mo47103b();
                    if (iArr == null || iArr.length != cvVar.f5479c * cvVar.f5480d) {
                        iArr = new int[(cvVar.f5480d * cvVar.f5479c)];
                        cvVar.mo47102a(iArr);
                    }
                    for (int i16 = i8; i16 <= i9; i16++) {
                        int i17 = cxVar.f5481e + (((i16 / f) - i12) * cxVar.f5482f);
                        int i18 = cxVar.f5479c + i17;
                        int i19 = cvVar.f5481e + ((i16 - i8) * cvVar.f5482f);
                        int i20 = i19 + cvVar.f5479c;
                        if ((i10 & 1) == 1) {
                            int i21 = i19 + 1;
                            i5 = i17 + 1;
                            iArr[i19] = this.f4941f[i][i17];
                            i6 = i21;
                        } else {
                            i5 = i17;
                            i6 = i19;
                        }
                        if ((i11 & 1) == 0) {
                            i7 = i20 - 1;
                        } else {
                            i7 = i20;
                        }
                        while (i6 < i7) {
                            int i22 = i6 + 1;
                            iArr[i6] = this.f4941f[i][i5];
                            int i23 = i22 + 1;
                            int i24 = i5 + 1;
                            iArr[i22] = this.f4941f[i][i5];
                            i5 = i24;
                            i6 = i23;
                        }
                        if ((i11 & 1) == 0) {
                            int i25 = i6 + 1;
                            iArr[i6] = this.f4941f[i][i5];
                        }
                    }
                    cvVar.f5483g = cxVar.f5483g;
                    return cvVar;
                case 4:
                    C5055cw cwVar = (C5055cw) this.f4951p.mo46936b(new C5055cw(i13, i12, i14, i15), i);
                    this.f4942g[i] = cwVar.mo47104c();
                    float[] fArr = (float[]) cvVar.mo47103b();
                    if (fArr == null || fArr.length != cvVar.f5479c * cvVar.f5480d) {
                        fArr = new float[(cvVar.f5480d * cvVar.f5479c)];
                        cvVar.mo47102a(fArr);
                    }
                    for (int i26 = i8; i26 <= i9; i26++) {
                        int i27 = cwVar.f5481e + (((i26 / f) - i12) * cwVar.f5482f);
                        int i28 = cwVar.f5479c + i27;
                        int i29 = cvVar.f5481e + ((i26 - i8) * cvVar.f5482f);
                        int i30 = i29 + cvVar.f5479c;
                        if ((i10 & 1) == 1) {
                            int i31 = i29 + 1;
                            i2 = i27 + 1;
                            fArr[i29] = this.f4942g[i][i27];
                            i3 = i31;
                        } else {
                            i2 = i27;
                            i3 = i29;
                        }
                        if ((i11 & 1) == 0) {
                            i4 = i30 - 1;
                        } else {
                            i4 = i30;
                        }
                        while (i3 < i4) {
                            int i32 = i3 + 1;
                            fArr[i3] = this.f4942g[i][i2];
                            int i33 = i32 + 1;
                            int i34 = i2 + 1;
                            fArr[i32] = this.f4942g[i][i2];
                            i2 = i34;
                            i3 = i33;
                        }
                        if ((i11 & 1) == 0) {
                            int i35 = i3 + 1;
                            fArr[i3] = this.f4942g[i][i2];
                        }
                    }
                    cvVar.f5483g = cwVar.f5483g;
                    return cvVar;
                default:
                    throw new IllegalArgumentException("invalid source datablock type");
            }
        } else {
            throw new IllegalArgumentException("Upsampling by other than 2:1 not supported");
        }
    }

    /* renamed from: a */
    public C5054cv mo46935a(C5054cv cvVar, int i) {
        return mo46936b(cvVar, i);
    }

    /* renamed from: c */
    public int mo46942c(int i) {
        return this.f4951p.mo46942c(i) * this.f4951p.mo46945f(i);
    }

    /* renamed from: d */
    public int mo46943d(int i) {
        return this.f4951p.mo46943d(i) * this.f4951p.mo46944e(i);
    }

    /* renamed from: e */
    public int mo46944e(int i) {
        return 1;
    }

    /* renamed from: f */
    public int mo46945f(int i) {
        return 1;
    }

    /* renamed from: a */
    public int mo46938a(int i, int i2) {
        return this.f4951p.mo46938a(i, i2) * this.f4951p.mo46945f(i2);
    }

    /* renamed from: b */
    public int mo46941b(int i, int i2) {
        return this.f4951p.mo46941b(i, i2) * this.f4951p.mo46944e(i2);
    }
}

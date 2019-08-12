package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.dm */
/* compiled from: StdDequantizer */
public class C5072dm extends C5070dk {

    /* renamed from: f */
    private C5068di f5518f;

    /* renamed from: g */
    private C5067dh f5519g;

    /* renamed from: h */
    private C5066dg f5520h;

    /* renamed from: i */
    private C5056cx f5521i;

    /* renamed from: j */
    private int f5522j;

    public C5072dm(C5069dj djVar, int[] iArr, C5039cg cgVar) {
        super(djVar, iArr, cgVar);
        if (iArr.length != djVar.mo47009c()) {
            throw new IllegalArgumentException("Invalid rb argument");
        }
        this.f5519g = cgVar.f5404d;
        this.f5518f = cgVar.f5403c;
        this.f5520h = cgVar.f5405e;
    }

    /* renamed from: h */
    public int mo47130h(int i) {
        return 0;
    }

    /* renamed from: a */
    public final C5054cv mo47129a(int i, int i2, int i3, C5093eg egVar, C5054cv cvVar) {
        int[] c;
        float[] fArr;
        int[] iArr;
        float f;
        boolean d = this.f5518f.mo47127d(this.f5562d, i);
        boolean c2 = this.f5518f.mo47126c(this.f5562d, i);
        C5073dn dnVar = (C5073dn) this.f5519g.mo46970a(this.f5562d, i);
        ((Integer) this.f5520h.mo46970a(this.f5562d, i)).intValue();
        this.f5522j = cvVar.mo47101a();
        if (!d || this.f5522j == 3) {
            switch (this.f5522j) {
                case 3:
                    cvVar = this.f5513a.mo47090a(i, i2, i3, egVar, cvVar);
                    c = null;
                    iArr = (int[]) cvVar.mo47103b();
                    fArr = null;
                    break;
                case 4:
                    this.f5521i = (C5056cx) this.f5513a.mo47091b(i, i2, i3, egVar, this.f5521i);
                    c = this.f5521i.mo47105c();
                    if (cvVar == null) {
                        cvVar = new C5055cw();
                    }
                    cvVar.f5477a = this.f5521i.f5477a;
                    cvVar.f5478b = this.f5521i.f5478b;
                    cvVar.f5479c = this.f5521i.f5479c;
                    cvVar.f5480d = this.f5521i.f5480d;
                    cvVar.f5481e = 0;
                    cvVar.f5482f = cvVar.f5479c;
                    cvVar.f5483g = this.f5521i.f5483g;
                    fArr = (float[]) cvVar.mo47103b();
                    if (fArr == null || fArr.length < cvVar.f5479c * cvVar.f5480d) {
                        fArr = new float[(cvVar.f5479c * cvVar.f5480d)];
                        cvVar.mo47102a(fArr);
                    }
                    iArr = null;
                    break;
                default:
                    fArr = null;
                    c = null;
                    iArr = null;
                    break;
            }
            int i4 = egVar.f5566r;
            if (!d) {
                if (c2) {
                    f = ((float) (1 << ((this.f5513a.mo47019e(mo47013d(), i).f5538d + (this.f5514b[i] + egVar.f5540f)) - egVar.f5537c))) * dnVar.f5524b[0][0];
                } else {
                    f = dnVar.f5524b[egVar.f5538d][egVar.f5541g] * ((float) (1 << (this.f5514b[i] + egVar.f5540f)));
                }
                float f2 = f / ((float) (1 << (31 - i4)));
                switch (this.f5522j) {
                    case 3:
                        for (int length = iArr.length - 1; length >= 0; length--) {
                            int i5 = iArr[length];
                            if (i5 < 0) {
                                i5 = -(i5 & Integer.MAX_VALUE);
                            }
                            iArr[length] = (int) (((float) i5) * f2);
                        }
                        break;
                    case 4:
                        int i6 = cvVar.f5479c;
                        int i7 = cvVar.f5480d;
                        int i8 = ((this.f5521i.f5481e + ((i7 - 1) * this.f5521i.f5482f)) + i6) - 1;
                        int i9 = (i7 - 1) * i6;
                        int i10 = (i6 * i7) - 1;
                        while (i10 >= 0) {
                            int i11 = i10;
                            int i12 = i8;
                            while (i11 >= i9) {
                                int i13 = c[i12];
                                if (i13 < 0) {
                                    i13 = -(i13 & Integer.MAX_VALUE);
                                }
                                fArr[i11] = ((float) i13) * f2;
                                i11--;
                                i12--;
                            }
                            i8 = i12 - (this.f5521i.f5482f - i6);
                            i9 -= i6;
                            i10 = i11;
                        }
                        break;
                }
            } else {
                int i14 = 31 - i4;
                for (int length2 = iArr.length - 1; length2 >= 0; length2--) {
                    int i15 = iArr[length2];
                    iArr[length2] = i15 >= 0 ? i15 >> i14 : -((i15 & Integer.MAX_VALUE) >> i14);
                }
            }
            return cvVar;
        }
        throw new IllegalArgumentException("Reversible quantizations must use int data");
    }
}

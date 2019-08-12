package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.ac */
/* compiled from: PalettizedColorSpaceMapper */
public class C4970ac extends C4967aa {

    /* renamed from: s */
    int[] f4962s;

    /* renamed from: t */
    int f4963t = 0;

    /* renamed from: u */
    private C4978ak f4964u;

    /* renamed from: a */
    public static C5051cs m3200a(C5051cs csVar, C5140y yVar) throws C5144z {
        return new C4970ac(csVar, yVar);
    }

    protected C4970ac(C5051cs csVar, C5140y yVar) throws C5144z {
        super(csVar, yVar);
        this.f4964u = yVar.mo47263e();
        m3201h();
    }

    /* renamed from: h */
    private void m3201h() throws C5144z {
        if (this.f4950o == 1 || this.f4950o == 3) {
            int b = mo46939b();
            this.f4962s = new int[b];
            for (int i = 0; i < b; i++) {
                this.f4962s[i] = 1 << (mo46940b(i) - 1);
            }
            return;
        }
        throw new C5144z("wrong number of components (" + this.f4950o + ") for palettized image");
    }

    /* renamed from: a */
    public C5054cv mo46935a(C5054cv cvVar, int i) {
        if (this.f4964u == null) {
            return this.f4951p.mo46935a(cvVar, i);
        }
        if (this.f4950o != 1) {
            return this.f4951p.mo46935a(cvVar, i);
        }
        m3188a(cvVar);
        switch (cvVar.mo47101a()) {
            case 3:
                m3189a((C5054cv) this.f4937b[0], cvVar);
                this.f4937b[0] = (C5056cx) this.f4951p.mo46936b(this.f4937b[0], 0);
                this.f4941f[0] = (int[]) this.f4937b[0].mo47103b();
                int[] c = ((C5056cx) cvVar).mo47105c();
                for (int i2 = 0; i2 < cvVar.f5480d; i2++) {
                    int i3 = this.f4937b[0].f5481e + (this.f4937b[0].f5482f * i2);
                    int i4 = i3 + this.f4937b[0].f5479c;
                    int i5 = cvVar.f5481e + (cvVar.f5482f * i2);
                    int i6 = cvVar.f5479c + i5;
                    while (i3 < i4) {
                        c[i5] = this.f4964u.mo46954a(i, this.f4941f[0][i3] + this.f4945j[0]) - this.f4962s[i];
                        i3++;
                        i5++;
                    }
                }
                cvVar.f5483g = this.f4937b[0].f5483g;
                break;
            case 4:
                m3189a((C5054cv) this.f4938c[0], cvVar);
                this.f4938c[0] = (C5055cw) this.f4951p.mo46936b(this.f4938c[0], 0);
                this.f4942g[0] = (float[]) this.f4938c[0].mo47103b();
                float[] c2 = ((C5055cw) cvVar).mo47104c();
                for (int i7 = 0; i7 < cvVar.f5480d; i7++) {
                    int i8 = this.f4938c[0].f5481e + (this.f4938c[0].f5482f * i7);
                    int i9 = i8 + this.f4938c[0].f5479c;
                    int i10 = cvVar.f5481e + (cvVar.f5482f * i7);
                    int i11 = cvVar.f5479c + i10;
                    while (i8 < i9) {
                        c2[i10] = (float) (this.f4964u.mo46954a(i, ((int) this.f4942g[0][i8]) + this.f4945j[0]) - this.f4962s[i]);
                        i8++;
                        i10++;
                    }
                }
                cvVar.f5483g = this.f4938c[0].f5483g;
                break;
            default:
                throw new IllegalArgumentException("invalid source datablock type");
        }
        cvVar.f5481e = 0;
        cvVar.f5482f = cvVar.f5479c;
        return cvVar;
    }

    /* renamed from: b */
    public C5054cv mo46936b(C5054cv cvVar, int i) {
        return mo46935a(cvVar, i);
    }

    /* renamed from: b */
    public int mo46940b(int i) {
        if (this.f4964u == null) {
            return this.f4951p.mo46940b(i);
        }
        return this.f4964u.mo46958b(i);
    }

    /* renamed from: b */
    public int mo46939b() {
        return this.f4964u == null ? this.f4951p.mo46939b() : this.f4964u.mo46957b();
    }

    /* renamed from: e */
    public int mo46944e(int i) {
        return this.f5487C.mo46944e(this.f4963t);
    }

    /* renamed from: f */
    public int mo46945f(int i) {
        return this.f5487C.mo46945f(this.f4963t);
    }

    /* renamed from: b */
    public int mo46941b(int i, int i2) {
        return this.f5487C.mo46941b(i, this.f4963t);
    }

    /* renamed from: a */
    public int mo46938a(int i, int i2) {
        return this.f5487C.mo46938a(i, this.f4963t);
    }

    /* renamed from: d */
    public int mo46943d(int i) {
        return this.f5487C.mo46943d(this.f4963t);
    }

    /* renamed from: c */
    public int mo46942c(int i) {
        return this.f5487C.mo46942c(this.f4963t);
    }

    /* renamed from: g */
    public int mo46946g(int i) {
        return this.f5487C.mo46946g(this.f4963t);
    }

    /* renamed from: h */
    public int mo46947h(int i) {
        return this.f5487C.mo46947h(this.f4963t);
    }
}

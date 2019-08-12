package jumio.p317nv.nfc;

import java.io.IOException;

/* renamed from: jumio.nv.nfc.aq */
/* compiled from: ICCProfiler */
public class C4984aq extends C4967aa {

    /* renamed from: s */
    protected static final String f5022s = System.getProperty("line.separator");

    /* renamed from: t */
    C4987at f5023t = null;

    /* renamed from: u */
    C4981an f5024u = null;

    /* renamed from: v */
    private C5056cx[] f5025v;

    /* renamed from: w */
    private C5055cw[] f5026w;

    /* renamed from: x */
    private Object f5027x = null;

    /* renamed from: y */
    private C4987at f5028y = null;

    /* renamed from: a */
    public static C5051cs m3262a(C5051cs csVar, C5140y yVar) throws IOException, C4982ao, C5144z {
        return new C4984aq(csVar, yVar);
    }

    protected C4984aq(C5051cs csVar, C5140y yVar) throws C5144z, IOException, C4982ao, IllegalArgumentException {
        super(csVar, yVar);
        m3263h();
        this.f5028y = m3261a(yVar);
        if (this.f4950o == 1) {
            this.f5027x = new C4998bd(this.f5028y, this.f4946k[0], this.f4945j[0]);
        } else {
            this.f5027x = new C4996bb(this.f5028y, this.f4946k, this.f4945j);
        }
    }

    /* renamed from: h */
    private void m3263h() {
        this.f5025v = new C5056cx[this.f4950o];
        this.f5026w = new C5055cw[this.f4950o];
        for (int i = 0; i < this.f4950o; i++) {
            this.f5025v[i] = new C5056cx();
            this.f5026w[i] = new C5055cw();
        }
    }

    /* renamed from: a */
    private C4987at m3261a(C5140y yVar) throws C5144z, C4982ao, IllegalArgumentException {
        switch (this.f4950o) {
            case 1:
                this.f5024u = C4980am.m3249a(yVar);
                this.f5023t = this.f5024u.mo46959a();
                if (this.f5023t.mo46960a() != 0) {
                    throw new IllegalArgumentException("wrong ICCProfile type for image");
                }
                break;
            case 3:
                this.f5024u = C4979al.m3248a(yVar);
                this.f5023t = this.f5024u.mo46959a();
                if (this.f5023t.mo46960a() != 1) {
                    throw new IllegalArgumentException("wrong ICCProfile type for image");
                }
                break;
            default:
                throw new IllegalArgumentException("illegal number of components (" + this.f4950o + ") in image");
        }
        return this.f5023t;
    }

    /* renamed from: a */
    public C5054cv mo46935a(C5054cv cvVar, int i) {
        try {
            if (this.f4950o != 1 && this.f4950o != 3) {
                return this.f4951p.mo46935a(cvVar, i);
            }
            int a = cvVar.mo47101a();
            for (int i2 = 0; i2 < this.f4950o; i2++) {
                int a2 = this.f4951p.mo46934a(i2);
                int i3 = this.f4945j[i2];
                int i4 = this.f4946k[i2];
                switch (a) {
                    case 3:
                        m3189a((C5054cv) this.f4939d[i2], cvVar);
                        m3189a((C5054cv) this.f5025v[i2], cvVar);
                        m3189a((C5054cv) this.f4937b[i2], cvVar);
                        m3188a(cvVar);
                        this.f4944i[i2] = (int[]) this.f4939d[i2].mo47103b();
                        this.f4937b[i2] = (C5056cx) this.f4951p.mo46936b(this.f4937b[i2], i2);
                        this.f4941f[i2] = this.f4937b[i2].mo47105c();
                        for (int i5 = 0; i5 < cvVar.f5480d; i5++) {
                            int i6 = this.f4937b[i2].f5481e + (this.f4937b[i2].f5482f * i5);
                            int i7 = i6 + this.f4937b[i2].f5479c;
                            int i8 = cvVar.f5481e + (cvVar.f5482f * i5);
                            int i9 = cvVar.f5479c + i8;
                            int i10 = i8;
                            for (int i11 = i6; i11 < i7; i11++) {
                                int i12 = (this.f4941f[i2][i11] >> a2) + i3;
                                int[] iArr = this.f4944i[i2];
                                if (i12 < 0) {
                                    i12 = 0;
                                } else if (i12 > i4) {
                                    i12 = i4;
                                }
                                iArr[i10] = i12;
                                i10++;
                            }
                        }
                        break;
                    case 4:
                        m3189a((C5054cv) this.f4940e[i2], cvVar);
                        m3189a((C5054cv) this.f5026w[i2], cvVar);
                        m3189a((C5054cv) this.f4938c[i2], cvVar);
                        m3188a(cvVar);
                        this.f4943h[i2] = (float[]) this.f4940e[i2].mo47103b();
                        this.f4938c[i2] = (C5055cw) this.f4951p.mo46936b(this.f4938c[i2], i2);
                        this.f4942g[i2] = this.f4938c[i2].mo47104c();
                        for (int i13 = 0; i13 < cvVar.f5480d; i13++) {
                            int i14 = this.f4938c[i2].f5481e + (this.f4938c[i2].f5482f * i13);
                            int i15 = i14 + this.f4938c[i2].f5479c;
                            int i16 = cvVar.f5481e + (cvVar.f5482f * i13);
                            int i17 = cvVar.f5479c + i16;
                            int i18 = i16;
                            for (int i19 = i14; i19 < i15; i19++) {
                                float f = (this.f4942g[i2][i19] / ((float) (1 << a2))) + ((float) i3);
                                float[] fArr = this.f4943h[i2];
                                if (f < 0.0f) {
                                    f = 0.0f;
                                } else if (f > ((float) i4)) {
                                    f = (float) i4;
                                }
                                fArr[i18] = f;
                                i18++;
                            }
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid source datablock type");
                }
            }
            switch (a) {
                case 3:
                    if (this.f4950o == 1) {
                        ((C4998bd) this.f5027x).mo46965a(this.f4939d[i], this.f5025v[i]);
                    } else {
                        ((C4996bb) this.f5027x).mo46963a(this.f4939d, this.f5025v);
                    }
                    cvVar.f5483g = this.f4937b[i].f5483g;
                    cvVar.mo47102a(this.f5025v[i].mo47103b());
                    break;
                case 4:
                    if (this.f4950o == 1) {
                        ((C4998bd) this.f5027x).mo46964a(this.f4940e[i], this.f5026w[i]);
                    } else {
                        ((C4996bb) this.f5027x).mo46962a(this.f4940e, this.f5026w);
                    }
                    cvVar.f5483g = this.f4938c[i].f5483g;
                    cvVar.mo47102a(this.f5026w[i].mo47103b());
                    break;
                default:
                    throw new IllegalArgumentException("invalid source datablock type");
            }
            cvVar.f5481e = 0;
            cvVar.f5482f = cvVar.f5479c;
            return cvVar;
        } catch (C4995ba e) {
            return null;
        } catch (C4997bc e2) {
            return null;
        }
    }

    /* renamed from: b */
    public C5054cv mo46936b(C5054cv cvVar, int i) {
        return mo46935a(cvVar, i);
    }
}

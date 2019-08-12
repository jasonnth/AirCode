package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.db */
/* compiled from: InvCompTransf */
public class C5061db extends C5058cz implements C5051cs {

    /* renamed from: a */
    private static final String[][] f5491a = null;

    /* renamed from: b */
    private C5051cs f5492b;

    /* renamed from: c */
    private C5052ct f5493c;

    /* renamed from: d */
    private C5099em f5494d;

    /* renamed from: e */
    private int f5495e = 0;

    /* renamed from: f */
    private int[][] f5496f = new int[3][];

    /* renamed from: g */
    private C5054cv f5497g;

    /* renamed from: h */
    private C5054cv f5498h;

    /* renamed from: i */
    private C5054cv f5499i;

    /* renamed from: j */
    private C5056cx f5500j = new C5056cx();

    /* renamed from: k */
    private int[] f5501k;

    /* renamed from: l */
    private boolean f5502l = false;

    public C5061db(C5051cs csVar, C5039cg cgVar, int[] iArr, C5079dt dtVar) {
        boolean z = false;
        super(csVar);
        this.f5493c = cgVar.f5411k;
        this.f5494d = cgVar.f5406f;
        this.f5492b = csVar;
        this.f5501k = iArr;
        if (!dtVar.mo47135b("comp_transf")) {
            z = true;
        }
        this.f5502l = z;
    }

    /* renamed from: a */
    public static String[][] m3525a() {
        return f5491a;
    }

    /* renamed from: a */
    public int mo46934a(int i) {
        return this.f5492b.mo46934a(i);
    }

    /* renamed from: a */
    public static int[] m3524a(int[] iArr, int i, int[] iArr2) {
        if (iArr.length >= 3 || i == 0) {
            if (iArr2 == null) {
                iArr2 = new int[iArr.length];
            }
            switch (i) {
                case 0:
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    break;
                case 1:
                    if (iArr.length > 3) {
                        System.arraycopy(iArr, 3, iArr2, 3, iArr.length - 3);
                    }
                    iArr2[0] = (C5078ds.m3575a((((1 << iArr[0]) + (2 << iArr[1])) + (1 << iArr[2])) - 1) - 2) + 1;
                    iArr2[1] = C5078ds.m3575a(((1 << iArr[2]) + (1 << iArr[1])) - 1) + 1;
                    iArr2[2] = C5078ds.m3575a(((1 << iArr[0]) + (1 << iArr[1])) - 1) + 1;
                    break;
                case 2:
                    if (iArr.length > 3) {
                        System.arraycopy(iArr, 3, iArr2, 3, iArr.length - 3);
                    }
                    iArr2[0] = C5078ds.m3575a(((int) Math.floor(((((double) (1 << iArr[0])) * 0.299072d) + (((double) (1 << iArr[1])) * 0.586914d)) + (((double) (1 << iArr[2])) * 0.114014d))) - 1) + 1;
                    iArr2[1] = C5078ds.m3575a(((int) Math.floor(((((double) (1 << iArr[0])) * 0.168701d) + (((double) (1 << iArr[1])) * 0.331299d)) + (((double) (1 << iArr[2])) * 0.5d))) - 1) + 1;
                    iArr2[2] = C5078ds.m3575a(((int) Math.floor(((((double) (1 << iArr[0])) * 0.5d) + (((double) (1 << iArr[1])) * 0.418701d)) + (((double) (1 << iArr[2])) * 0.081299d))) - 1) + 1;
                    break;
            }
            return iArr2;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: b */
    public int mo46940b(int i) {
        return this.f5501k[i];
    }

    /* renamed from: a */
    public C5054cv mo46935a(C5054cv cvVar, int i) {
        if (i >= 3 || this.f5495e == 0 || this.f5502l) {
            return this.f5492b.mo46935a(cvVar, i);
        }
        return mo46936b(cvVar, i);
    }

    /* renamed from: b */
    public C5054cv mo46936b(C5054cv cvVar, int i) {
        if (this.f5502l) {
            return this.f5492b.mo46936b(cvVar, i);
        }
        switch (this.f5495e) {
            case 0:
                return this.f5492b.mo46936b(cvVar, i);
            case 1:
                return m3526c(cvVar, i);
            case 2:
                return m3527d(cvVar, i);
            default:
                throw new IllegalArgumentException("Non JPEG 2000 part I component transformation");
        }
    }

    /* renamed from: c */
    private C5054cv m3526c(C5054cv cvVar, int i) {
        if (i >= 3 && i < mo46939b()) {
            return this.f5492b.mo46936b(cvVar, i);
        }
        if (this.f5496f[i] == null || this.f5500j.f5477a > cvVar.f5477a || this.f5500j.f5478b > cvVar.f5478b || this.f5500j.f5477a + this.f5500j.f5479c < cvVar.f5477a + cvVar.f5479c || this.f5500j.f5478b + this.f5500j.f5480d < cvVar.f5478b + cvVar.f5480d) {
            int i2 = cvVar.f5479c;
            int i3 = cvVar.f5480d;
            this.f5496f[i] = (int[]) cvVar.mo47103b();
            if (this.f5496f[i] == null || this.f5496f[i].length != i3 * i2) {
                this.f5496f[i] = new int[(i3 * i2)];
                cvVar.mo47102a(this.f5496f[i]);
            }
            this.f5496f[(i + 1) % 3] = new int[this.f5496f[i].length];
            this.f5496f[(i + 2) % 3] = new int[this.f5496f[i].length];
            if (this.f5497g == null || this.f5497g.mo47101a() != 3) {
                this.f5497g = new C5056cx();
            }
            if (this.f5498h == null || this.f5498h.mo47101a() != 3) {
                this.f5498h = new C5056cx();
            }
            if (this.f5499i == null || this.f5499i.mo47101a() != 3) {
                this.f5499i = new C5056cx();
            }
            C5054cv cvVar2 = this.f5497g;
            C5054cv cvVar3 = this.f5498h;
            C5054cv cvVar4 = this.f5499i;
            int i4 = cvVar.f5479c;
            cvVar4.f5479c = i4;
            cvVar3.f5479c = i4;
            cvVar2.f5479c = i4;
            C5054cv cvVar5 = this.f5497g;
            C5054cv cvVar6 = this.f5498h;
            C5054cv cvVar7 = this.f5499i;
            int i5 = cvVar.f5480d;
            cvVar7.f5480d = i5;
            cvVar6.f5480d = i5;
            cvVar5.f5480d = i5;
            C5054cv cvVar8 = this.f5497g;
            C5054cv cvVar9 = this.f5498h;
            C5054cv cvVar10 = this.f5499i;
            int i6 = cvVar.f5477a;
            cvVar10.f5477a = i6;
            cvVar9.f5477a = i6;
            cvVar8.f5477a = i6;
            C5054cv cvVar11 = this.f5497g;
            C5054cv cvVar12 = this.f5498h;
            C5054cv cvVar13 = this.f5499i;
            int i7 = cvVar.f5478b;
            cvVar13.f5478b = i7;
            cvVar12.f5478b = i7;
            cvVar11.f5478b = i7;
            this.f5497g = (C5056cx) this.f5492b.mo46936b(this.f5497g, 0);
            int[] iArr = (int[]) this.f5497g.mo47103b();
            this.f5498h = (C5056cx) this.f5492b.mo46936b(this.f5498h, 1);
            int[] iArr2 = (int[]) this.f5498h.mo47103b();
            this.f5499i = (C5056cx) this.f5492b.mo46936b(this.f5499i, 2);
            int[] iArr3 = (int[]) this.f5499i.mo47103b();
            cvVar.f5483g = this.f5497g.f5483g || this.f5498h.f5483g || this.f5499i.f5483g;
            cvVar.f5481e = 0;
            cvVar.f5482f = i2;
            this.f5500j.f5483g = cvVar.f5483g;
            this.f5500j.f5477a = cvVar.f5477a;
            this.f5500j.f5478b = cvVar.f5478b;
            this.f5500j.f5479c = cvVar.f5479c;
            this.f5500j.f5480d = cvVar.f5480d;
            int i8 = ((this.f5499i.f5481e + ((i3 - 1) * this.f5499i.f5482f)) + i2) - 1;
            int i9 = ((this.f5498h.f5481e + ((i3 - 1) * this.f5498h.f5482f)) + i2) - 1;
            int i10 = ((this.f5497g.f5481e + ((i3 - 1) * this.f5497g.f5482f)) + i2) - 1;
            int i11 = (i2 * i3) - 1;
            int i12 = i3 - 1;
            while (i12 >= 0) {
                int i13 = i11 - i2;
                while (i11 > i13) {
                    this.f5496f[1][i11] = iArr[i10] - ((iArr2[i9] + iArr3[i8]) >> 2);
                    this.f5496f[0][i11] = iArr3[i8] + this.f5496f[1][i11];
                    this.f5496f[2][i11] = iArr2[i9] + this.f5496f[1][i11];
                    i11--;
                    i10--;
                    i9--;
                    i8--;
                }
                int i14 = i10 - (this.f5497g.f5482f - i2);
                i12--;
                i8 -= this.f5499i.f5482f - i2;
                i9 -= this.f5498h.f5482f - i2;
                i10 = i14;
            }
            this.f5496f[i] = null;
            return cvVar;
        } else if (i < 0 || i >= 3) {
            throw new IllegalArgumentException();
        } else {
            cvVar.mo47102a(this.f5496f[i]);
            cvVar.f5483g = this.f5500j.f5483g;
            cvVar.f5481e = (((cvVar.f5478b - this.f5500j.f5478b) * this.f5500j.f5479c) + cvVar.f5477a) - this.f5500j.f5477a;
            cvVar.f5482f = this.f5500j.f5479c;
            this.f5496f[i] = null;
            return cvVar;
        }
    }

    /* renamed from: d */
    private C5054cv m3527d(C5054cv cvVar, int i) {
        if (i >= 3 && i < mo46939b()) {
            int i2 = cvVar.f5479c;
            int i3 = cvVar.f5480d;
            int[] iArr = (int[]) cvVar.mo47103b();
            if (iArr == null) {
                iArr = new int[(i3 * i2)];
                cvVar.mo47102a(iArr);
            }
            int[] iArr2 = iArr;
            C5055cw cwVar = new C5055cw(cvVar.f5477a, cvVar.f5478b, i2, i3);
            this.f5492b.mo46936b(cwVar, i);
            float[] fArr = (float[]) cwVar.mo47103b();
            int i4 = (i2 * i3) - 1;
            int i5 = i3 - 1;
            int i6 = ((cwVar.f5481e + ((i3 - 1) * cwVar.f5482f)) + i2) - 1;
            int i7 = i4;
            int i8 = i5;
            while (i8 >= 0) {
                int i9 = i7 - i2;
                while (i7 > i9) {
                    iArr2[i7] = (int) fArr[i6];
                    i7--;
                    i6--;
                }
                i8--;
                i6 -= cwVar.f5482f - i2;
            }
            cvVar.f5483g = cwVar.f5483g;
            cvVar.f5481e = 0;
            cvVar.f5482f = i2;
        } else if (this.f5496f[i] == null || this.f5500j.f5477a > cvVar.f5477a || this.f5500j.f5478b > cvVar.f5478b || this.f5500j.f5477a + this.f5500j.f5479c < cvVar.f5477a + cvVar.f5479c || this.f5500j.f5478b + this.f5500j.f5480d < cvVar.f5478b + cvVar.f5480d) {
            int i10 = cvVar.f5479c;
            int i11 = cvVar.f5480d;
            this.f5496f[i] = (int[]) cvVar.mo47103b();
            if (this.f5496f[i] == null || this.f5496f[i].length != i10 * i11) {
                this.f5496f[i] = new int[(i11 * i10)];
                cvVar.mo47102a(this.f5496f[i]);
            }
            this.f5496f[(i + 1) % 3] = new int[this.f5496f[i].length];
            this.f5496f[(i + 2) % 3] = new int[this.f5496f[i].length];
            if (this.f5497g == null || this.f5497g.mo47101a() != 4) {
                this.f5497g = new C5055cw();
            }
            if (this.f5499i == null || this.f5499i.mo47101a() != 4) {
                this.f5499i = new C5055cw();
            }
            if (this.f5498h == null || this.f5498h.mo47101a() != 4) {
                this.f5498h = new C5055cw();
            }
            C5054cv cvVar2 = this.f5497g;
            C5054cv cvVar3 = this.f5499i;
            C5054cv cvVar4 = this.f5498h;
            int i12 = cvVar.f5479c;
            cvVar4.f5479c = i12;
            cvVar3.f5479c = i12;
            cvVar2.f5479c = i12;
            C5054cv cvVar5 = this.f5497g;
            C5054cv cvVar6 = this.f5499i;
            C5054cv cvVar7 = this.f5498h;
            int i13 = cvVar.f5480d;
            cvVar7.f5480d = i13;
            cvVar6.f5480d = i13;
            cvVar5.f5480d = i13;
            C5054cv cvVar8 = this.f5497g;
            C5054cv cvVar9 = this.f5499i;
            C5054cv cvVar10 = this.f5498h;
            int i14 = cvVar.f5477a;
            cvVar10.f5477a = i14;
            cvVar9.f5477a = i14;
            cvVar8.f5477a = i14;
            C5054cv cvVar11 = this.f5497g;
            C5054cv cvVar12 = this.f5499i;
            C5054cv cvVar13 = this.f5498h;
            int i15 = cvVar.f5478b;
            cvVar13.f5478b = i15;
            cvVar12.f5478b = i15;
            cvVar11.f5478b = i15;
            this.f5497g = (C5055cw) this.f5492b.mo46936b(this.f5497g, 0);
            float[] fArr2 = (float[]) this.f5497g.mo47103b();
            this.f5499i = (C5055cw) this.f5492b.mo46936b(this.f5499i, 1);
            float[] fArr3 = (float[]) this.f5499i.mo47103b();
            this.f5498h = (C5055cw) this.f5492b.mo46936b(this.f5498h, 2);
            float[] fArr4 = (float[]) this.f5498h.mo47103b();
            cvVar.f5483g = this.f5497g.f5483g || this.f5498h.f5483g || this.f5499i.f5483g;
            cvVar.f5481e = 0;
            cvVar.f5482f = i10;
            this.f5500j.f5483g = cvVar.f5483g;
            this.f5500j.f5477a = cvVar.f5477a;
            this.f5500j.f5478b = cvVar.f5478b;
            this.f5500j.f5479c = cvVar.f5479c;
            this.f5500j.f5480d = cvVar.f5480d;
            int i16 = ((this.f5499i.f5481e + ((i11 - 1) * this.f5499i.f5482f)) + i10) - 1;
            int i17 = ((this.f5498h.f5481e + ((i11 - 1) * this.f5498h.f5482f)) + i10) - 1;
            int i18 = ((this.f5497g.f5481e + ((i11 - 1) * this.f5497g.f5482f)) + i10) - 1;
            int i19 = (i10 * i11) - 1;
            int i20 = i11 - 1;
            while (i20 >= 0) {
                int i21 = i19 - i10;
                while (i19 > i21) {
                    this.f5496f[0][i19] = (int) (fArr2[i18] + (1.402f * fArr4[i17]) + 0.5f);
                    this.f5496f[1][i19] = (int) (((fArr2[i18] - (0.34413f * fArr3[i16])) - (0.71414f * fArr4[i17])) + 0.5f);
                    this.f5496f[2][i19] = (int) (fArr2[i18] + (1.772f * fArr3[i16]) + 0.5f);
                    i19--;
                    i18--;
                    i16--;
                    i17--;
                }
                i17 -= this.f5498h.f5482f - i10;
                i20--;
                i16 -= this.f5499i.f5482f - i10;
                i18 -= this.f5497g.f5482f - i10;
            }
            this.f5496f[i] = null;
        } else if (i < 0 || i > 3) {
            throw new IllegalArgumentException();
        } else {
            cvVar.mo47102a(this.f5496f[i]);
            cvVar.f5483g = this.f5500j.f5483g;
            cvVar.f5481e = (((cvVar.f5478b - this.f5500j.f5478b) * this.f5500j.f5479c) + cvVar.f5477a) - this.f5500j.f5477a;
            cvVar.f5482f = this.f5500j.f5479c;
            this.f5496f[i] = null;
        }
        return cvVar;
    }

    /* renamed from: c */
    public void mo47108c(int i, int i2) {
        int i3;
        this.f5492b.mo47108c(i, i2);
        this.f5486B = mo47110e();
        if (((Integer) this.f5493c.mo46979d(this.f5486B)).intValue() == 0) {
            this.f5495e = 0;
            return;
        }
        int b = this.f5492b.mo46939b() > 3 ? 3 : this.f5492b.mo46939b();
        int i4 = 0;
        for (int i5 = 0; i5 < b; i5++) {
            if (this.f5494d.mo47163e(this.f5486B, i5)) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            i4 += i3;
        }
        if (i4 == 3) {
            this.f5495e = 1;
        } else if (i4 == 0) {
            this.f5495e = 2;
        } else {
            throw new IllegalArgumentException("Wavelet transformation and component transformation not coherent in tile" + this.f5486B);
        }
    }
}

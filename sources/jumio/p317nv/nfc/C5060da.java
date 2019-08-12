package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.da */
/* compiled from: ImgDataConverter */
public class C5060da extends C5058cz implements C5051cs {

    /* renamed from: a */
    private C5054cv f5488a = new C5056cx();

    /* renamed from: b */
    private C5051cs f5489b;

    /* renamed from: c */
    private int f5490c;

    public C5060da(C5051cs csVar, int i) {
        super(csVar);
        this.f5489b = csVar;
        this.f5490c = i;
    }

    /* renamed from: a */
    public int mo46934a(int i) {
        return this.f5490c;
    }

    /* renamed from: a */
    public C5054cv mo46935a(C5054cv cvVar, int i) {
        return m3520a(cvVar, i, false);
    }

    /* renamed from: b */
    public final C5054cv mo46936b(C5054cv cvVar, int i) {
        return m3520a(cvVar, i, true);
    }

    /* renamed from: a */
    private C5054cv m3520a(C5054cv cvVar, int i, boolean z) {
        C5054cv cvVar2;
        int a = cvVar.mo47101a();
        if (a == this.f5488a.mo47101a()) {
            cvVar2 = cvVar;
        } else {
            cvVar2 = this.f5488a;
            cvVar2.f5477a = cvVar.f5477a;
            cvVar2.f5478b = cvVar.f5478b;
            cvVar2.f5479c = cvVar.f5479c;
            cvVar2.f5480d = cvVar.f5480d;
        }
        if (z) {
            this.f5488a = this.f5489b.mo46936b(cvVar2, i);
        } else {
            this.f5488a = this.f5489b.mo46935a(cvVar2, i);
        }
        if (this.f5488a.mo47101a() == a) {
            return this.f5488a;
        }
        int i2 = this.f5488a.f5479c;
        int i3 = this.f5488a.f5480d;
        switch (a) {
            case 3:
                int[] iArr = (int[]) cvVar.mo47103b();
                if (iArr == null || iArr.length < i2 * i3) {
                    iArr = new int[(i2 * i3)];
                    cvVar.mo47102a(iArr);
                }
                int[] iArr2 = iArr;
                cvVar.f5482f = this.f5488a.f5479c;
                cvVar.f5481e = 0;
                cvVar.f5483g = this.f5488a.f5483g;
                float[] fArr = (float[]) this.f5488a.mo47103b();
                if (this.f5490c != 0) {
                    float f = (float) (1 << this.f5490c);
                    int i4 = (i2 * i3) - 1;
                    int i5 = ((((i3 - 1) * this.f5488a.f5482f) + this.f5488a.f5481e) + i2) - 1;
                    for (int i6 = i3 - 1; i6 >= 0; i6--) {
                        int i7 = i4 - i2;
                        while (i4 > i7) {
                            if (fArr[i5] > 0.0f) {
                                iArr2[i4] = (int) ((fArr[i5] * f) + 0.5f);
                            } else {
                                iArr2[i4] = (int) ((fArr[i5] * f) - 0.5f);
                            }
                            i4--;
                            i5--;
                        }
                        i5 -= this.f5488a.f5482f - i2;
                    }
                    return cvVar;
                }
                int i8 = (i2 * i3) - 1;
                int i9 = ((((i3 - 1) * this.f5488a.f5482f) + this.f5488a.f5481e) + i2) - 1;
                for (int i10 = i3 - 1; i10 >= 0; i10--) {
                    int i11 = i8 - i2;
                    while (i8 > i11) {
                        if (fArr[i9] > 0.0f) {
                            iArr2[i8] = (int) (fArr[i9] + 0.5f);
                        } else {
                            iArr2[i8] = (int) (fArr[i9] - 0.5f);
                        }
                        i8--;
                        i9--;
                    }
                    i9 -= this.f5488a.f5482f - i2;
                }
                return cvVar;
            case 4:
                float[] fArr2 = (float[]) cvVar.mo47103b();
                if (fArr2 == null || fArr2.length < i2 * i3) {
                    fArr2 = new float[(i2 * i3)];
                    cvVar.mo47102a(fArr2);
                }
                float[] fArr3 = fArr2;
                cvVar.f5482f = this.f5488a.f5479c;
                cvVar.f5481e = 0;
                cvVar.f5483g = this.f5488a.f5483g;
                int[] iArr3 = (int[]) this.f5488a.mo47103b();
                this.f5490c = this.f5489b.mo46934a(i);
                if (this.f5490c != 0) {
                    float f2 = 1.0f / ((float) (1 << this.f5490c));
                    int i12 = (i2 * i3) - 1;
                    int i13 = ((((i3 - 1) * this.f5488a.f5482f) + this.f5488a.f5481e) + i2) - 1;
                    for (int i14 = i3 - 1; i14 >= 0; i14--) {
                        int i15 = i12 - i2;
                        while (i12 > i15) {
                            fArr3[i12] = ((float) iArr3[i13]) * f2;
                            i12--;
                            i13--;
                        }
                        i13 -= this.f5488a.f5482f - i2;
                    }
                    return cvVar;
                }
                int i16 = (i2 * i3) - 1;
                int i17 = ((((i3 - 1) * this.f5488a.f5482f) + this.f5488a.f5481e) + i2) - 1;
                for (int i18 = i3 - 1; i18 >= 0; i18--) {
                    int i19 = i16 - i2;
                    while (i16 > i19) {
                        fArr3[i16] = (float) iArr3[i17];
                        i16--;
                        i17--;
                    }
                    i17 -= this.f5488a.f5482f - i2;
                }
                return cvVar;
            default:
                throw new IllegalArgumentException("Only integer and float data are supported by JJ2000");
        }
    }
}

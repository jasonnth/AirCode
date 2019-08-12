package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.bd */
/* compiled from: MonochromeTransformTosRGB */
public class C4998bd {

    /* renamed from: a */
    private static final String f5049a = System.getProperty("line.separator");

    /* renamed from: b */
    private short[] f5050b = null;

    /* renamed from: c */
    private int f5051c = 0;

    /* renamed from: d */
    private C4991ax f5052d = null;

    public C4998bd(C4987at atVar, int i, int i2) {
        int i3 = 0;
        if (atVar.mo46960a() != 0) {
            throw new IllegalArgumentException("MonochromeTransformTosRGB: wrong type ICCProfile supplied");
        }
        this.f5051c = i;
        this.f5050b = new short[(i + 1)];
        this.f5052d = C4991ax.m3274a(atVar.f5030b[0], i + 1);
        while (i3 <= i && ((double) this.f5052d.f5037d[i3]) <= 0.0031308d) {
            this.f5050b[i3] = (short) ((int) (Math.floor((3294.6d * ((double) this.f5052d.f5037d[i3])) + 0.5d) - ((double) i2)));
            i3++;
        }
        while (i3 <= i) {
            this.f5050b[i3] = (short) ((int) (Math.floor(((269.025d * Math.pow((double) this.f5052d.f5037d[i3], 0.4166666666666667d)) - 14.025d) + 0.5d) - ((double) i2)));
            i3++;
        }
    }

    /* renamed from: a */
    public void mo46965a(C5056cx cxVar, C5056cx cxVar2) throws C4997bc {
        int[] iArr = (int[]) cxVar.mo47103b();
        int[] iArr2 = (int[]) cxVar2.mo47103b();
        if (iArr2 == 0 || iArr2.length < iArr.length) {
            int[] iArr3 = new int[iArr.length];
            cxVar2.mo47102a(iArr3);
            iArr2 = iArr3;
        }
        cxVar2.f5478b = cxVar.f5478b;
        cxVar2.f5477a = cxVar.f5477a;
        cxVar2.f5480d = cxVar.f5480d;
        cxVar2.f5479c = cxVar.f5479c;
        cxVar2.f5481e = cxVar.f5481e;
        cxVar2.f5482f = cxVar.f5482f;
        int i = cxVar.f5481e;
        for (int i2 = 0; i2 < cxVar.f5480d * cxVar.f5479c; i2++) {
            int i3 = iArr[i2];
            if (i3 < 0) {
                i3 = 0;
            } else if (i3 > this.f5051c) {
                i3 = this.f5051c;
            }
            iArr2[i2] = this.f5050b[i3];
        }
    }

    /* renamed from: a */
    public void mo46964a(C5055cw cwVar, C5055cw cwVar2) throws C4997bc {
        float[] fArr = (float[]) cwVar.mo47103b();
        float[] fArr2 = (float[]) cwVar2.mo47103b();
        if (fArr2 == null || fArr2.length < fArr.length) {
            fArr2 = new float[fArr.length];
            cwVar2.mo47102a(fArr2);
            cwVar2.f5478b = cwVar.f5478b;
            cwVar2.f5477a = cwVar.f5477a;
            cwVar2.f5480d = cwVar.f5480d;
            cwVar2.f5479c = cwVar.f5479c;
            cwVar2.f5481e = cwVar.f5481e;
            cwVar2.f5482f = cwVar.f5482f;
        }
        int i = cwVar.f5481e;
        for (int i2 = 0; i2 < cwVar.f5480d * cwVar.f5479c; i2++) {
            int i3 = (int) fArr[i2];
            if (i3 < 0) {
                i3 = 0;
            } else if (i3 > this.f5051c) {
                i3 = this.f5051c;
            }
            fArr2[i2] = (float) this.f5050b[i3];
        }
    }
}

package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.ae */
/* compiled from: SYccColorSpaceMapper */
public class C4972ae extends C4967aa {

    /* renamed from: A */
    protected static float f4967A = 0.0f;

    /* renamed from: s */
    protected static float f4968s = 1.0f;

    /* renamed from: t */
    protected static float f4969t = 0.0f;

    /* renamed from: u */
    protected static float f4970u = 1.402f;

    /* renamed from: v */
    protected static float f4971v = 1.0f;

    /* renamed from: w */
    protected static float f4972w = -0.34413f;

    /* renamed from: x */
    protected static float f4973x = -0.71414f;

    /* renamed from: y */
    protected static float f4974y = 1.0f;

    /* renamed from: z */
    protected static float f4975z = 1.772f;

    /* renamed from: a */
    public static C5051cs m3223a(C5051cs csVar, C5140y yVar) throws C5144z {
        return new C4972ae(csVar, yVar);
    }

    protected C4972ae(C5051cs csVar, C5140y yVar) throws C5144z {
        super(csVar, yVar);
        m3226h();
    }

    /* renamed from: h */
    private void m3226h() throws C5144z {
        if (this.f4950o != 1 && this.f4950o != 3) {
            throw new C5144z("SYccColorSpaceMapper: ycc transformation _not_ applied to " + this.f4950o + " component image");
        }
    }

    /* renamed from: a */
    public C5054cv mo46935a(C5054cv cvVar, int i) {
        int a = cvVar.mo47101a();
        for (int i2 = 0; i2 < this.f4950o; i2++) {
            m3189a((C5054cv) this.f4939d[i2], cvVar);
            m3189a((C5054cv) this.f4940e[i2], cvVar);
            m3189a((C5054cv) this.f4937b[i2], cvVar);
            m3189a((C5054cv) this.f4938c[i2], cvVar);
            this.f4937b[i2] = (C5056cx) this.f4951p.mo46936b(this.f4937b[i2], i2);
        }
        if (a == 3) {
            if (this.f4950o == 1) {
                this.f4939d[i] = this.f4937b[i];
            } else {
                this.f4939d = m3225a(this.f4937b);
            }
            cvVar.f5483g = this.f4937b[i].f5483g;
            cvVar.mo47102a(this.f4939d[i].mo47103b());
        }
        if (a == 4) {
            if (this.f4950o == 1) {
                this.f4940e[i] = this.f4938c[i];
            } else {
                this.f4940e = m3224a(this.f4938c);
            }
            cvVar.f5483g = this.f4938c[i].f5483g;
            cvVar.mo47102a(this.f4940e[i].mo47103b());
        }
        cvVar.f5481e = 0;
        cvVar.f5482f = cvVar.f5479c;
        return cvVar;
    }

    /* renamed from: b */
    public C5054cv mo46936b(C5054cv cvVar, int i) {
        return mo46935a(cvVar, i);
    }

    /* renamed from: a */
    private static C5055cw[] m3224a(C5055cw[] cwVarArr) {
        if (cwVarArr.length != 3) {
            throw new IllegalArgumentException("bad input array size");
        }
        int i = cwVarArr[0].f5479c * cwVarArr[0].f5480d;
        C5055cw[] cwVarArr2 = new C5055cw[3];
        float[][] fArr = new float[3][];
        float[][] fArr2 = new float[3][];
        for (int i2 = 0; i2 < 3; i2++) {
            fArr2[i2] = cwVarArr[i2].mo47104c();
            cwVarArr2[i2] = new C5055cw();
            m3189a((C5054cv) cwVarArr2[i2], (C5054cv) cwVarArr[i2]);
            cwVarArr2[i2].f5481e = cwVarArr[i2].f5481e;
            fArr[i2] = new float[i];
            cwVarArr2[i2].mo47102a(fArr[i2]);
        }
        for (int i3 = 0; i3 < i; i3++) {
            fArr[0][i3] = (f4968s * fArr2[0][cwVarArr[0].f5481e + i3]) + (f4969t * fArr2[1][cwVarArr[1].f5481e + i3]) + (f4970u * fArr2[2][cwVarArr[2].f5481e + i3]);
            fArr[1][i3] = (f4971v * fArr2[0][cwVarArr[0].f5481e + i3]) + (f4972w * fArr2[1][cwVarArr[1].f5481e + i3]) + (f4973x * fArr2[2][cwVarArr[2].f5481e + i3]);
            fArr[2][i3] = (f4974y * fArr2[0][cwVarArr[0].f5481e + i3]) + (f4975z * fArr2[1][cwVarArr[1].f5481e + i3]) + (f4967A * fArr2[2][cwVarArr[2].f5481e + i3]);
        }
        return cwVarArr2;
    }

    /* renamed from: a */
    private static C5056cx[] m3225a(C5056cx[] cxVarArr) {
        if (cxVarArr.length != 3) {
            throw new IllegalArgumentException("bad input array size");
        }
        int i = cxVarArr[0].f5479c * cxVarArr[0].f5480d;
        C5056cx[] cxVarArr2 = new C5056cx[3];
        int[][] iArr = new int[3][];
        int[][] iArr2 = new int[3][];
        for (int i2 = 0; i2 < 3; i2++) {
            iArr2[i2] = cxVarArr[i2].mo47105c();
            cxVarArr2[i2] = new C5056cx();
            m3189a((C5054cv) cxVarArr2[i2], (C5054cv) cxVarArr[i2]);
            cxVarArr2[i2].f5481e = cxVarArr[i2].f5481e;
            iArr[i2] = new int[i];
            cxVarArr2[i2].mo47102a(iArr[i2]);
        }
        for (int i3 = 0; i3 < i; i3++) {
            iArr[0][i3] = (int) ((f4968s * ((float) iArr2[0][cxVarArr[0].f5481e + i3])) + (f4969t * ((float) iArr2[1][cxVarArr[1].f5481e + i3])) + (f4970u * ((float) iArr2[2][cxVarArr[2].f5481e + i3])));
            iArr[1][i3] = (int) ((f4971v * ((float) iArr2[0][cxVarArr[0].f5481e + i3])) + (f4972w * ((float) iArr2[1][cxVarArr[1].f5481e + i3])) + (f4973x * ((float) iArr2[2][cxVarArr[2].f5481e + i3])));
            iArr[2][i3] = (int) ((f4974y * ((float) iArr2[0][cxVarArr[0].f5481e + i3])) + (f4975z * ((float) iArr2[1][cxVarArr[1].f5481e + i3])) + (f4967A * ((float) iArr2[2][cxVarArr[2].f5481e + i3])));
        }
        return cxVarArr2;
    }
}

package jumio.p317nv.nfc;

import java.lang.reflect.Array;

/* renamed from: jumio.nv.nfc.bb */
/* compiled from: MatrixBasedTransformTosRGB */
public class C4996bb {

    /* renamed from: a */
    private static final String f5040a = System.getProperty("line.separator");

    /* renamed from: b */
    private final double[] f5041b;

    /* renamed from: c */
    private C4991ax[] f5042c = new C4991ax[3];

    /* renamed from: d */
    private C4990aw f5043d;

    /* renamed from: e */
    private final int[] f5044e;

    /* renamed from: f */
    private final int[] f5045f;

    /* renamed from: g */
    private int f5046g = 0;

    /* renamed from: h */
    private int f5047h = 0;

    /* renamed from: i */
    private float[][] f5048i = null;

    public C4996bb(C4987at atVar, int[] iArr, int[] iArr2) {
        if (atVar.mo46960a() != 1) {
            throw new IllegalArgumentException("MatrixBasedTransformTosRGB: wrong type ICCProfile supplied");
        }
        this.f5044e = iArr;
        this.f5045f = iArr2;
        for (int i = 0; i < 3; i++) {
            this.f5042c[i] = C4991ax.m3274a(atVar.f5030b[i], iArr[i] + 1);
        }
        this.f5041b = m3278a(atVar, iArr);
        this.f5043d = C4990aw.m3273a(iArr[0], iArr[0], 0.0031308d, 12.92d, 1.055d, 0.4166666666666667d, 0.055d);
    }

    /* renamed from: a */
    private double[] m3278a(C4987at atVar, int[] iArr) {
        double a = C5006bk.m3291a(atVar.f5031c[0].f5101a);
        double a2 = C5006bk.m3291a(atVar.f5031c[1].f5101a);
        double a3 = C5006bk.m3291a(atVar.f5031c[2].f5101a);
        double a4 = C5006bk.m3291a(atVar.f5031c[0].f5102b);
        double a5 = C5006bk.m3291a(atVar.f5031c[1].f5102b);
        double a6 = C5006bk.m3291a(atVar.f5031c[2].f5102b);
        double a7 = C5006bk.m3291a(atVar.f5031c[0].f5103c);
        double a8 = C5006bk.m3291a(atVar.f5031c[1].f5103c);
        double a9 = C5006bk.m3291a(atVar.f5031c[2].f5103c);
        return new double[]{((double) iArr[0]) * ((3.1337d * a) + (-1.6173d * a4) + (-0.4907d * a7)), ((double) iArr[0]) * ((3.1337d * a2) + (-1.6173d * a5) + (-0.4907d * a8)), ((double) iArr[0]) * ((3.1337d * a3) + (-1.6173d * a6) + (-0.4907d * a9)), ((double) iArr[1]) * ((-0.9785d * a) + (1.9162d * a4) + (0.0334d * a7)), ((double) iArr[1]) * ((-0.9785d * a2) + (1.9162d * a5) + (0.0334d * a8)), ((double) iArr[1]) * ((-0.9785d * a3) + (1.9162d * a6) + (0.0334d * a9)), ((a * 0.072d) + (a4 * -0.229d) + (1.4056d * a7)) * ((double) iArr[2]), ((a2 * 0.072d) + (a5 * -0.229d) + (1.4056d * a8)) * ((double) iArr[2]), ((double) iArr[2]) * ((a3 * 0.072d) + (-0.229d * a6) + (1.4056d * a9))};
    }

    /* renamed from: a */
    public void mo46963a(C5056cx[] cxVarArr, C5056cx[] cxVarArr2) throws C4995ba {
        int[][] iArr = new int[3][];
        int[][] iArr2 = new int[3][];
        int i = cxVarArr[0].f5480d;
        int i2 = cxVarArr[0].f5479c;
        if (this.f5048i == null || this.f5048i[0].length < i2 * i) {
            this.f5048i = (float[][]) Array.newInstance(Float.TYPE, new int[]{3, i * i2});
        }
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= 3) {
                break;
            }
            iArr[i4] = (int[]) cxVarArr[i4].mo47103b();
            iArr2[i4] = (int[]) cxVarArr2[i4].mo47103b();
            if (iArr2[i4] == null || iArr2[i4].length < iArr[i4].length) {
                iArr2[i4] = new int[iArr[i4].length];
                cxVarArr2[i4].mo47102a(iArr2[i4]);
            }
            m3277a(cxVarArr[i4], this.f5048i[i4], this.f5044e[i4], this.f5042c[i4]);
            i3 = i4 + 1;
        }
        float[] fArr = this.f5048i[0];
        float[] fArr2 = this.f5048i[1];
        float[] fArr3 = this.f5048i[2];
        int[] iArr3 = iArr2[0];
        int[] iArr4 = iArr2[1];
        int[] iArr5 = iArr2[2];
        int[] iArr6 = this.f5043d.f5036e;
        int i5 = 0;
        for (int i6 = 0; i6 < cxVarArr[0].f5480d; i6++) {
            int i7 = cxVarArr[0].f5479c + i5;
            while (i5 < i7) {
                double d = (double) fArr[i5];
                double d2 = (double) fArr2[i5];
                double d3 = (double) fArr3[i5];
                int i8 = (int) ((this.f5041b[0] * d) + (this.f5041b[1] * d2) + (this.f5041b[2] * d3) + 0.5d);
                if (i8 < 0) {
                    iArr3[i5] = iArr6[0];
                } else {
                    if (i8 >= iArr6.length) {
                        iArr3[i5] = iArr6[iArr6.length - 1];
                    } else {
                        iArr3[i5] = iArr6[i8];
                    }
                }
                int i9 = (int) ((this.f5041b[3] * d) + (this.f5041b[4] * d2) + (this.f5041b[5] * d3) + 0.5d);
                if (i9 < 0) {
                    iArr4[i5] = iArr6[0];
                } else {
                    if (i9 >= iArr6.length) {
                        iArr4[i5] = iArr6[iArr6.length - 1];
                    } else {
                        iArr4[i5] = iArr6[i9];
                    }
                }
                int i10 = (int) ((d * this.f5041b[6]) + (d2 * this.f5041b[7]) + (this.f5041b[8] * d3) + 0.5d);
                if (i10 < 0) {
                    iArr5[i5] = iArr6[0];
                } else if (i10 >= iArr6.length) {
                    iArr5[i5] = iArr6[iArr6.length - 1];
                } else {
                    iArr5[i5] = iArr6[i10];
                }
                i5++;
            }
        }
    }

    /* renamed from: a */
    public void mo46962a(C5055cw[] cwVarArr, C5055cw[] cwVarArr2) throws C4995ba {
        float[][] fArr = new float[3][];
        float[][] fArr2 = new float[3][];
        int i = cwVarArr[0].f5480d;
        int i2 = cwVarArr[0].f5479c;
        if (this.f5048i == null || this.f5048i[0].length < i2 * i) {
            this.f5048i = (float[][]) Array.newInstance(Float.TYPE, new int[]{3, i * i2});
        }
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= 3) {
                break;
            }
            fArr[i4] = (float[]) cwVarArr[i4].mo47103b();
            fArr2[i4] = (float[]) cwVarArr2[i4].mo47103b();
            if (fArr2[i4] == null || fArr2[i4].length < fArr[i4].length) {
                fArr2[i4] = new float[fArr[i4].length];
                cwVarArr2[i4].mo47102a(fArr2[i4]);
            }
            m3276a(cwVarArr[i4], this.f5048i[i4], (float) this.f5044e[i4], this.f5042c[i4]);
            i3 = i4 + 1;
        }
        int[] iArr = this.f5043d.f5036e;
        int i5 = 0;
        for (int i6 = 0; i6 < cwVarArr[0].f5480d; i6++) {
            int i7 = cwVarArr[0].f5479c + i5;
            while (i5 < i7) {
                int i8 = (int) ((this.f5041b[0] * ((double) this.f5048i[0][i5])) + (this.f5041b[1] * ((double) this.f5048i[1][i5])) + (this.f5041b[2] * ((double) this.f5048i[2][i5])) + 0.5d);
                if (i8 < 0) {
                    fArr2[0][i5] = (float) iArr[0];
                } else if (i8 >= iArr.length) {
                    fArr2[0][i5] = (float) iArr[iArr.length - 1];
                } else {
                    fArr2[0][i5] = (float) iArr[i8];
                }
                int i9 = (int) ((this.f5041b[3] * ((double) this.f5048i[0][i5])) + (this.f5041b[4] * ((double) this.f5048i[1][i5])) + (this.f5041b[5] * ((double) this.f5048i[2][i5])) + 0.5d);
                if (i9 < 0) {
                    fArr2[1][i5] = (float) iArr[0];
                } else if (i9 >= iArr.length) {
                    fArr2[1][i5] = (float) iArr[iArr.length - 1];
                } else {
                    fArr2[1][i5] = (float) iArr[i9];
                }
                int i10 = (int) ((this.f5041b[6] * ((double) this.f5048i[0][i5])) + (this.f5041b[7] * ((double) this.f5048i[1][i5])) + (this.f5041b[8] * ((double) this.f5048i[2][i5])) + 0.5d);
                if (i10 < 0) {
                    fArr2[2][i5] = (float) iArr[0];
                } else if (i10 >= iArr.length) {
                    fArr2[2][i5] = (float) iArr[iArr.length - 1];
                } else {
                    fArr2[2][i5] = (float) iArr[i10];
                }
                i5++;
            }
        }
    }

    /* renamed from: a */
    private static void m3277a(C5056cx cxVar, float[] fArr, int i, C4991ax axVar) {
        int i2;
        int[] iArr = (int[]) cxVar.mo47103b();
        float[] fArr2 = axVar.f5037d;
        int i3 = 0;
        for (int i4 = cxVar.f5478b; i4 < cxVar.f5478b + cxVar.f5480d; i4++) {
            int i5 = cxVar.f5477a;
            while (i5 < cxVar.f5477a + cxVar.f5479c) {
                int i6 = cxVar.f5481e + ((i4 - cxVar.f5478b) * cxVar.f5482f) + (i5 - cxVar.f5477a);
                if (iArr[i6] > i) {
                    i2 = i;
                } else if (iArr[i6] < 0) {
                    i2 = 0;
                } else {
                    i2 = iArr[i6];
                }
                int i7 = i3 + 1;
                fArr[i3] = fArr2[i2];
                i5++;
                i3 = i7;
            }
        }
    }

    /* renamed from: a */
    private static void m3276a(C5055cw cwVar, float[] fArr, float f, C4991ax axVar) {
        float f2;
        int i = 0;
        float[] fArr2 = (float[]) cwVar.mo47103b();
        float[] fArr3 = axVar.f5037d;
        for (int i2 = cwVar.f5478b; i2 < cwVar.f5478b + cwVar.f5480d; i2++) {
            int i3 = cwVar.f5477a;
            while (i3 < cwVar.f5477a + cwVar.f5479c) {
                int i4 = cwVar.f5481e + ((i2 - cwVar.f5478b) * cwVar.f5482f) + (i3 - cwVar.f5477a);
                if (fArr2[i4] > f) {
                    f2 = f;
                } else if (fArr2[i4] < 0.0f) {
                    f2 = 0.0f;
                } else {
                    f2 = fArr2[i4];
                }
                int i5 = i + 1;
                fArr[i] = fArr3[(int) f2];
                i3++;
                i = i5;
            }
        }
    }
}

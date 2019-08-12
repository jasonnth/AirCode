package p004bo.app;

import android.opengl.GLES10;

/* renamed from: bo.app.hl */
public final class C0595hl {

    /* renamed from: a */
    private static C0563gp f803a;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], 2048);
        f803a = new C0563gp(max, max);
    }

    /* renamed from: a */
    public static C0563gp m1052a(C0588he heVar, C0563gp gpVar) {
        int a = heVar.mo7259a();
        if (a <= 0) {
            a = gpVar.mo7183a();
        }
        int b = heVar.mo7262b();
        if (b <= 0) {
            b = gpVar.mo7186b();
        }
        return new C0563gp(a, b);
    }

    /* renamed from: a */
    public static int m1051a(C0563gp gpVar, C0563gp gpVar2, C0566gs gsVar, boolean z) {
        int min;
        int i = 1;
        int a = gpVar.mo7183a();
        int b = gpVar.mo7186b();
        int a2 = gpVar2.mo7183a();
        int b2 = gpVar2.mo7186b();
        switch (gsVar) {
            case FIT_INSIDE:
                if (!z) {
                    min = Math.max(a / a2, b / b2);
                    break;
                } else {
                    int i2 = a / 2;
                    int i3 = b / 2;
                    min = 1;
                    while (true) {
                        if (i2 / min <= a2 && i3 / min <= b2) {
                            break;
                        } else {
                            min *= 2;
                        }
                    }
                }
                break;
            case CROP:
                if (!z) {
                    min = Math.min(a / a2, b / b2);
                    break;
                } else {
                    int i4 = a / 2;
                    int i5 = b / 2;
                    int i6 = 1;
                    while (i4 / min > a2 && i5 / min > b2) {
                        i6 = min * 2;
                    }
                }
                break;
            default:
                min = 1;
                break;
        }
        if (min >= 1) {
            i = min;
        }
        return m1049a(a, b, i, z);
    }

    /* renamed from: a */
    private static int m1049a(int i, int i2, int i3, boolean z) {
        int a = f803a.mo7183a();
        int b = f803a.mo7186b();
        while (true) {
            if (i / i3 <= a && i2 / i3 <= b) {
                return i3;
            }
            if (z) {
                i3 *= 2;
            } else {
                i3++;
            }
        }
    }

    /* renamed from: a */
    public static int m1050a(C0563gp gpVar) {
        int a = gpVar.mo7183a();
        int b = gpVar.mo7186b();
        return Math.max((int) Math.ceil((double) (((float) a) / ((float) f803a.mo7183a()))), (int) Math.ceil((double) (((float) b) / ((float) f803a.mo7186b()))));
    }

    /* renamed from: b */
    public static float m1053b(C0563gp gpVar, C0563gp gpVar2, C0566gs gsVar, boolean z) {
        int i;
        int i2;
        int a = gpVar.mo7183a();
        int b = gpVar.mo7186b();
        int a2 = gpVar2.mo7183a();
        int b2 = gpVar2.mo7186b();
        float f = ((float) a) / ((float) a2);
        float f2 = ((float) b) / ((float) b2);
        if ((gsVar != C0566gs.FIT_INSIDE || f < f2) && (gsVar != C0566gs.CROP || f >= f2)) {
            i = (int) (((float) a) / f2);
            i2 = b2;
        } else {
            i = a2;
            i2 = (int) (((float) b) / f);
        }
        if ((z || i >= a || i2 >= b) && (!z || i == a || i2 == b)) {
            return 1.0f;
        }
        return ((float) i) / ((float) a);
    }
}

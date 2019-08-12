package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.ds */
/* compiled from: MathUtil */
public class C5078ds {
    /* renamed from: a */
    public static int m3575a(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("" + i + " <= 0");
        }
        int i2 = -1;
        while (i > 0) {
            i >>= 1;
            i2++;
        }
        return i2;
    }

    /* renamed from: a */
    public static final int m3576a(int i, int i2) {
        int i3;
        int i4;
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Cannot compute the GCD if one integer is negative.");
        }
        if (i > i2) {
            i3 = i2;
            i4 = i;
        } else {
            i3 = i;
            i4 = i2;
        }
        if (i3 == 0) {
            return 0;
        }
        int i5 = i3;
        int i6 = i4;
        int i7 = i5;
        while (i7 != 0) {
            int i8 = i6 % i7;
            i6 = i7;
            i7 = i8;
        }
        return i6;
    }
}

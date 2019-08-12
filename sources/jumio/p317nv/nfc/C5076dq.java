package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.dq */
/* compiled from: ArrayUtil */
public class C5076dq {
    /* renamed from: a */
    public static void m3563a(int[] iArr, int i) {
        int length = iArr.length;
        if (length < 8) {
            for (int i2 = length - 1; i2 >= 0; i2--) {
                iArr[i2] = i;
            }
            return;
        }
        int i3 = length >> 1;
        int i4 = 0;
        while (i4 < 4) {
            iArr[i4] = i;
            i4++;
        }
        while (i4 <= i3) {
            System.arraycopy(iArr, 0, iArr, i4, i4);
            i4 <<= 1;
        }
        if (i4 < length) {
            System.arraycopy(iArr, 0, iArr, i4, length - i4);
        }
    }
}

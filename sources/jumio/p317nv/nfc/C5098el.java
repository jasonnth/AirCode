package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.el */
/* compiled from: SynWTFilterIntLift5x3 */
public class C5098el extends C5097ek {
    /* renamed from: a */
    public void mo47159a(int[] iArr, int i, int i2, int i3, int[] iArr2, int i4, int i5, int i6, int[] iArr3, int i7, int i8) {
        int i9 = i2 + i5;
        int i10 = i8 * 2;
        if (i9 > 1) {
            iArr3[i7] = iArr[i] - ((iArr2[i4] + 1) >> 1);
        } else {
            iArr3[i7] = iArr[i];
        }
        int i11 = i + i3;
        int i12 = i4 + i6;
        int i13 = i7 + i10;
        for (int i14 = 2; i14 < i9 - 1; i14 += 2) {
            iArr3[i13] = iArr[i11] - (((iArr2[i12 - i6] + iArr2[i12]) + 2) >> 2);
            i11 += i3;
            i12 += i6;
            i13 += i10;
        }
        if (i9 % 2 == 1 && i9 > 2) {
            iArr3[i13] = iArr[i11] - (((iArr2[i12 - i6] * 2) + 2) >> 2);
        }
        int i15 = i7 + i8;
        for (int i16 = 1; i16 < i9 - 1; i16 += 2) {
            iArr3[i15] = iArr2[i4] + ((iArr3[i15 - i8] + iArr3[i15 + i8]) >> 1);
            i4 += i6;
            i15 += i10;
        }
        if (i9 % 2 == 0 && i9 > 1) {
            iArr3[i15] = iArr2[i4] + iArr3[i15 - i8];
        }
    }

    /* renamed from: b */
    public void mo47160b(int[] iArr, int i, int i2, int i3, int[] iArr2, int i4, int i5, int i6, int[] iArr3, int i7, int i8) {
        int i9 = i2 + i5;
        int i10 = i8 * 2;
        int i11 = i7 + i8;
        int i12 = i4;
        for (int i13 = 1; i13 < i9 - 1; i13 += 2) {
            iArr3[i11] = iArr[i] - (((iArr2[i12] + iArr2[i12 + i6]) + 2) >> 2);
            i += i3;
            i12 += i6;
            i11 += i10;
        }
        if (i9 > 1 && i9 % 2 == 0) {
            iArr3[i11] = iArr[i] - (((iArr2[i12] * 2) + 2) >> 2);
        }
        if (i9 > 1) {
            iArr3[i7] = iArr2[i4] + iArr3[i7 + i8];
        } else {
            iArr3[i7] = iArr2[i4] >> 1;
        }
        int i14 = i4 + i6;
        int i15 = i7 + i10;
        for (int i16 = 2; i16 < i9 - 1; i16 += 2) {
            iArr3[i15] = iArr2[i14] + ((iArr3[i15 - i8] + iArr3[i15 + i8]) >> 1);
            i14 += i6;
            i15 += i10;
        }
        if (i9 % 2 == 1 && i9 > 1) {
            iArr3[i15] = iArr2[i14] + iArr3[i15 - i8];
        }
    }

    /* renamed from: b */
    public boolean mo47147b() {
        return true;
    }
}

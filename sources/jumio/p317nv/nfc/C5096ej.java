package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.ej */
/* compiled from: SynWTFilterFloatLift9x7 */
public class C5096ej extends C5095ei {
    /* renamed from: a */
    public void mo47157a(float[] fArr, int i, int i2, int i3, float[] fArr2, int i4, int i5, int i6, float[] fArr3, int i7, int i8) {
        int i9 = i2 + i5;
        int i10 = i8 * 2;
        if (i9 > 1) {
            fArr3[i7] = (fArr[i] / 0.8128931f) - ((0.88701373f * fArr2[i4]) / 1.2301741f);
        } else {
            fArr3[i7] = fArr[i];
        }
        int i11 = i + i3;
        int i12 = i4 + i6;
        int i13 = i7 + i10;
        int i14 = 2;
        while (i14 < i9 - 1) {
            fArr3[i13] = (fArr[i11] / 0.8128931f) - ((0.44350687f * (fArr2[i12 - i6] + fArr2[i12])) / 1.2301741f);
            i14 += 2;
            i13 += i10;
            i11 += i3;
            i12 += i6;
        }
        if (i9 % 2 == 1 && i9 > 2) {
            fArr3[i13] = (fArr[i11] / 0.8128931f) - ((fArr2[i12 - i6] * 0.88701373f) / 1.2301741f);
        }
        int i15 = i7 + i8;
        int i16 = 1;
        while (i16 < i9 - 1) {
            fArr3[i15] = (fArr2[i4] / 1.2301741f) - (0.8829111f * (fArr3[i15 - i8] + fArr3[i15 + i8]));
            i16 += 2;
            i15 += i10;
            i4 += i6;
            i += i3;
        }
        if (i9 % 2 == 0) {
            fArr3[i15] = (fArr2[i4] / 1.2301741f) - (1.7658222f * fArr3[i15 - i8]);
        }
        if (i9 > 1) {
            fArr3[i7] = fArr3[i7] - (-0.105960235f * fArr3[i7 + i8]);
        }
        int i17 = i7 + i10;
        int i18 = 2;
        while (i18 < i9 - 1) {
            fArr3[i17] = fArr3[i17] - (-0.052980117f * (fArr3[i17 - i8] + fArr3[i17 + i8]));
            i18 += 2;
            i17 += i10;
        }
        if (i9 % 2 == 1 && i9 > 2) {
            fArr3[i17] = fArr3[i17] - (-0.105960235f * fArr3[i17 - i8]);
        }
        int i19 = i7 + i8;
        int i20 = 1;
        while (i20 < i9 - 1) {
            fArr3[i19] = fArr3[i19] - (-1.5861343f * (fArr3[i19 - i8] + fArr3[i19 + i8]));
            i20 += 2;
            i19 += i10;
        }
        if (i9 % 2 == 0) {
            fArr3[i19] = fArr3[i19] - (-3.1722686f * fArr3[i19 - i8]);
        }
    }

    /* renamed from: b */
    public void mo47158b(float[] fArr, int i, int i2, int i3, float[] fArr2, int i4, int i5, int i6, float[] fArr3, int i7, int i8) {
        int i9 = i2 + i5;
        int i10 = i8 * 2;
        if (i9 != 1) {
            int i11 = i9 >> 1;
            int i12 = i;
            int i13 = i4;
            for (int i14 = 0; i14 < i11; i14++) {
                fArr[i12] = fArr[i12] / 0.8128931f;
                fArr2[i13] = fArr2[i13] / 1.2301741f;
                i12 += i3;
                i13 += i6;
            }
            if (i9 % 2 == 1) {
                fArr2[i13] = fArr2[i13] / 1.2301741f;
            }
        } else {
            fArr2[i4] = fArr2[i4] / 2.0f;
        }
        int i15 = i7 + i8;
        int i16 = i4;
        for (int i17 = 1; i17 < i9 - 1; i17 += 2) {
            fArr3[i15] = fArr[i] - (0.44350687f * (fArr2[i16] + fArr2[i16 + i6]));
            i15 += i10;
            i += i3;
            i16 += i6;
        }
        if (i9 % 2 == 0 && i9 > 1) {
            fArr3[i15] = fArr[i] - (fArr2[i16] * 0.88701373f);
        }
        if (i9 > 1) {
            fArr3[i7] = fArr2[i4] - (1.7658222f * fArr3[i7 + i8]);
        } else {
            fArr3[i7] = fArr2[i4];
        }
        int i18 = i7 + i10;
        int i19 = i4 + i6;
        for (int i20 = 2; i20 < i9 - 1; i20 += 2) {
            fArr3[i18] = fArr2[i19] - (0.8829111f * (fArr3[i18 - i8] + fArr3[i18 + i8]));
            i18 += i10;
            i19 += i6;
        }
        if (i9 % 2 == 1 && i9 > 1) {
            fArr3[i18] = fArr2[i19] - (1.7658222f * fArr3[i18 - i8]);
        }
        int i21 = i7 + i8;
        for (int i22 = 1; i22 < i9 - 1; i22 += 2) {
            fArr3[i21] = fArr3[i21] - (-0.052980117f * (fArr3[i21 - i8] + fArr3[i21 + i8]));
            i21 += i10;
        }
        if (i9 % 2 == 0 && i9 > 1) {
            fArr3[i21] = fArr3[i21] - (-0.105960235f * fArr3[i21 - i8]);
        }
        if (i9 > 1) {
            fArr3[i7] = fArr3[i7] - (-3.1722686f * fArr3[i7 + i8]);
        }
        int i23 = i7 + i10;
        for (int i24 = 2; i24 < i9 - 1; i24 += 2) {
            fArr3[i23] = fArr3[i23] - (-1.5861343f * (fArr3[i23 - i8] + fArr3[i23 + i8]));
            i23 += i10;
        }
        if (i9 % 2 == 1 && i9 > 1) {
            fArr3[i23] = fArr3[i23] - (-3.1722686f * fArr3[i23 - i8]);
        }
    }

    /* renamed from: b */
    public boolean mo47147b() {
        return false;
    }
}

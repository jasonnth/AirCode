package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.cq */
/* compiled from: StdEntropyDecoder */
public class C5049cq extends C5047co {

    /* renamed from: i */
    private static final int[] f5457i = new int[256];

    /* renamed from: j */
    private static final int[] f5458j = new int[256];

    /* renamed from: k */
    private static final int[] f5459k = new int[256];

    /* renamed from: l */
    private static final int[] f5460l = new int[512];

    /* renamed from: m */
    private static final int[] f5461m = new int[512];

    /* renamed from: n */
    private static final int[] f5462n = {46, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    /* renamed from: b */
    private C5044cl f5463b;

    /* renamed from: c */
    private C5048cp f5464c;

    /* renamed from: f */
    private C5039cg f5465f;

    /* renamed from: g */
    private int f5466g;

    /* renamed from: h */
    private final boolean f5467h;

    /* renamed from: o */
    private final int[] f5468o;

    /* renamed from: p */
    private C5046cn f5469p;

    /* renamed from: q */
    private int f5470q;

    static {
        int i;
        f5457i[0] = 2;
        for (int i2 = 1; i2 < 16; i2++) {
            f5457i[i2] = 4;
        }
        for (int i3 = 0; i3 < 4; i3++) {
            f5457i[1 << i3] = 3;
        }
        for (int i4 = 0; i4 < 16; i4++) {
            f5457i[i4 | 32] = 5;
            f5457i[i4 | 16] = 5;
            f5457i[i4 | 48] = 6;
        }
        f5457i[128] = 7;
        f5457i[64] = 7;
        for (int i5 = 1; i5 < 16; i5++) {
            f5457i[i5 | 128] = 8;
            f5457i[i5 | 64] = 8;
        }
        for (int i6 = 1; i6 < 4; i6++) {
            for (int i7 = 0; i7 < 16; i7++) {
                f5457i[(i6 << 4) | 128 | i7] = 9;
                f5457i[(i6 << 4) | 64 | i7] = 9;
            }
        }
        for (int i8 = 0; i8 < 64; i8++) {
            f5457i[i8 | 192] = 10;
        }
        f5458j[0] = 2;
        for (int i9 = 1; i9 < 16; i9++) {
            f5458j[i9] = 4;
        }
        for (int i10 = 0; i10 < 4; i10++) {
            f5458j[1 << i10] = 3;
        }
        for (int i11 = 0; i11 < 16; i11++) {
            f5458j[i11 | 128] = 5;
            f5458j[i11 | 64] = 5;
            f5458j[i11 | 192] = 6;
        }
        f5458j[32] = 7;
        f5458j[16] = 7;
        for (int i12 = 1; i12 < 16; i12++) {
            f5458j[i12 | 32] = 8;
            f5458j[i12 | 16] = 8;
        }
        for (int i13 = 1; i13 < 4; i13++) {
            for (int i14 = 0; i14 < 16; i14++) {
                f5458j[(i13 << 6) | 32 | i14] = 9;
                f5458j[(i13 << 6) | 16 | i14] = 9;
            }
        }
        for (int i15 = 0; i15 < 4; i15++) {
            for (int i16 = 0; i16 < 16; i16++) {
                f5458j[(i15 << 6) | 32 | 16 | i16] = 10;
            }
        }
        int[] iArr = {3, 5, 6, 9, 10, 12};
        int[] iArr2 = {1, 2, 4, 8};
        int[] iArr3 = {3, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15};
        int[] iArr4 = {7, 11, 13, 14, 15};
        f5459k[0] = 2;
        for (int i17 : iArr2) {
            f5459k[i17 << 4] = 3;
        }
        for (int i18 : iArr3) {
            f5459k[i18 << 4] = 4;
        }
        for (int i19 : iArr2) {
            f5459k[i19] = 5;
        }
        for (int i20 = 0; i20 < iArr2.length; i20++) {
            for (int i21 : iArr2) {
                f5459k[(iArr2[i20] << 4) | i21] = 6;
            }
        }
        for (int i22 = 0; i22 < iArr3.length; i22++) {
            for (int i23 : iArr2) {
                f5459k[(iArr3[i22] << 4) | i23] = 7;
            }
        }
        for (int i24 : iArr) {
            f5459k[i24] = 8;
        }
        for (int i25 = 0; i25 < iArr.length; i25++) {
            for (int i26 = 1; i26 < 16; i26++) {
                f5459k[(i26 << 4) | iArr[i25]] = 9;
            }
        }
        for (int i27 = 0; i27 < 16; i27++) {
            for (int i28 : iArr4) {
                f5459k[(i27 << 4) | i28] = 10;
            }
        }
        int[] iArr5 = new int[36];
        iArr5[18] = 15;
        iArr5[17] = 14;
        iArr5[16] = 13;
        iArr5[10] = 12;
        iArr5[9] = 11;
        iArr5[8] = -2147483636;
        iArr5[2] = -2147483635;
        iArr5[1] = -2147483634;
        iArr5[0] = -2147483633;
        for (int i29 = 0; i29 < 511; i29++) {
            int i30 = i29 & 1;
            int i31 = (i29 >> 1) & 1;
            int i32 = (i29 >> 5) & 1;
            int i33 = (i29 >> 6) & 1;
            int i34 = (((i29 >> 2) & 1) * (1 - (((i29 >> 7) & 1) * 2))) + (((i29 >> 3) & 1) * (1 - (((i29 >> 8) & 1) * 2)));
            if (i34 < -1) {
                i34 = -1;
            }
            if (i34 <= 1) {
                i = i34;
            } else {
                i = 1;
            }
            int i35 = ((1 - (i33 * 2)) * i31) + (i30 * (1 - (i32 * 2)));
            if (i35 < -1) {
                i35 = -1;
            }
            if (i35 > 1) {
                i35 = 1;
            }
            f5460l[i29] = iArr5[(i35 + 1) | ((i + 1) << 3)];
        }
        f5461m[0] = 16;
        int i36 = 1;
        while (i36 < 256) {
            f5461m[i36] = 17;
            i36++;
        }
        while (i36 < 512) {
            f5461m[i36] = 18;
            i36++;
        }
    }

    public C5049cq(C5045cm cmVar, C5039cg cgVar, boolean z, boolean z2, int i) {
        super(cmVar);
        this.f5465f = cgVar;
        this.f5467h = z;
        this.f5470q = i;
        this.f5468o = new int[((cgVar.f5417q.mo47074a() + 2) * (((cgVar.f5417q.mo47077c() + 1) / 2) + 2))];
    }

    /* renamed from: a */
    public C5054cv mo47090a(int i, int i2, int i3, C5093eg egVar, C5054cv cvVar) {
        C5054cv cvVar2;
        C5043ck ckVar;
        int[] iArr;
        int i4;
        boolean z;
        int i5;
        int i6;
        boolean z2;
        int i7;
        int i8;
        this.f5469p = this.f5443a.mo47027a(i, i2, i3, egVar, 1, -1, this.f5469p);
        this.f5466g = ((Integer) this.f5465f.f5410j.mo46970a(this.f5562d, i)).intValue();
        C5076dq.m3563a(this.f5468o, 0);
        if (cvVar == null) {
            cvVar2 = new C5056cx();
        } else {
            cvVar2 = cvVar;
        }
        cvVar2.f5483g = this.f5469p.f5437j;
        cvVar2.f5477a = this.f5469p.f5432e;
        cvVar2.f5478b = this.f5469p.f5433f;
        cvVar2.f5479c = this.f5469p.f5434g;
        cvVar2.f5480d = this.f5469p.f5435h;
        cvVar2.f5481e = 0;
        cvVar2.f5482f = cvVar2.f5479c;
        int[] iArr2 = (int[]) cvVar2.mo47103b();
        if (iArr2 == null || iArr2.length < this.f5469p.f5434g * this.f5469p.f5435h) {
            cvVar2.mo47102a(new int[(this.f5469p.f5434g * this.f5469p.f5435h)]);
        } else {
            C5076dq.m3563a(iArr2, 0);
        }
        if (this.f5469p.f5438k > 0 && this.f5469p.f5440m > 0) {
            int i9 = this.f5469p.f5441n == null ? this.f5469p.f5436i : this.f5469p.f5441n[0];
            int i10 = this.f5469p.f5440m;
            if (this.f5464c == null) {
                C5043ck ckVar2 = new C5043ck(this.f5469p.f5424d, 0, i9);
                this.f5464c = new C5048cp(ckVar2, 19, f5462n);
                ckVar = ckVar2;
            } else {
                this.f5464c.mo47086a(this.f5469p.f5424d, 0, i9);
                this.f5464c.mo47088b();
                ckVar = null;
            }
            if ((this.f5466g & 1) != 0 && this.f5463b == null) {
                if (ckVar == null) {
                    ckVar = this.f5464c.mo47089c();
                }
                this.f5463b = new C5044cl(ckVar);
            }
            switch (egVar.f5536b) {
                case 0:
                case 2:
                    iArr = f5457i;
                    break;
                case 1:
                    iArr = f5458j;
                    break;
                case 3:
                    iArr = f5459k;
                    break;
                default:
                    throw new Error("JJ2000 internal error");
            }
            int i11 = 30 - this.f5469p.f5423c;
            if (this.f5470q == -1 || (this.f5470q * 3) - 2 >= i10) {
                i4 = i10;
            } else {
                i4 = (this.f5470q * 3) - 2;
            }
            if (i11 < 0 || i4 <= 0) {
                z = false;
                i5 = i11;
                i6 = i4;
            } else {
                z = m3460b(cvVar2, this.f5464c, i11, this.f5468o, iArr, (this.f5466g & 4) != 0 || ((this.f5466g & 1) != 0 && 27 - this.f5469p.f5423c >= i11));
                i6 = i4 - 1;
                i5 = (!z || !this.f5467h) ? i11 - 1 : i11;
            }
            if (!z || !this.f5467h) {
                int i12 = i6;
                boolean z3 = z;
                int i13 = 0;
                while (true) {
                    if (i5 < 0 || i12 <= 0) {
                        z = z3;
                    } else {
                        if ((this.f5466g & 1) == 0 || i5 >= 27 - this.f5469p.f5423c) {
                            if ((this.f5466g & 4) != 0) {
                                i13++;
                                this.f5464c.mo47086a(null, -1, this.f5469p.f5441n[i13]);
                            }
                            int i14 = i13;
                            z = m3458a(cvVar2, this.f5464c, i5, this.f5468o, iArr, (this.f5466g & 4) != 0);
                            i7 = i12 - 1;
                            if (i7 > 0 && (!z || !this.f5467h)) {
                                if ((this.f5466g & 4) != 0) {
                                    i14++;
                                    this.f5464c.mo47086a(null, -1, this.f5469p.f5441n[i14]);
                                }
                                i8 = i14;
                                z2 = m3457a(cvVar2, this.f5464c, i5, this.f5468o, (this.f5466g & 4) != 0 || ((this.f5466g & 1) != 0 && 27 - this.f5469p.f5423c > i5));
                            }
                        } else {
                            i8 = i13 + 1;
                            this.f5463b.mo47083a(null, -1, this.f5469p.f5441n[i8]);
                            z2 = m3456a(cvVar2, this.f5463b, i5, this.f5468o, (this.f5466g & 4) != 0);
                            i7 = i12 - 1;
                            if (i7 > 0) {
                                if (!z2 || !this.f5467h) {
                                    if ((this.f5466g & 4) != 0) {
                                        i8++;
                                        this.f5463b.mo47083a(null, -1, this.f5469p.f5441n[i8]);
                                    }
                                    z2 = m3459b(cvVar2, this.f5463b, i5, this.f5468o, (this.f5466g & 4) != 0 || ((this.f5466g & 1) != 0 && 27 - this.f5469p.f5423c > i5));
                                } else {
                                    z = z2;
                                }
                            }
                        }
                        int i15 = i7 - 1;
                        if (i15 > 0) {
                            if (!z2 || !this.f5467h) {
                                if ((this.f5466g & 4) != 0 || ((this.f5466g & 1) != 0 && i5 < 27 - this.f5469p.f5423c)) {
                                    i8++;
                                    this.f5464c.mo47086a(null, -1, this.f5469p.f5441n[i8]);
                                }
                                int i16 = i8;
                                z = m3460b(cvVar2, this.f5464c, i5, this.f5468o, iArr, (this.f5466g & 4) != 0 || ((this.f5466g & 1) != 0 && 27 - this.f5469p.f5423c >= i5));
                                int i17 = i15 - 1;
                                if (!z || !this.f5467h) {
                                    i5--;
                                    i12 = i17;
                                    z3 = z;
                                    i13 = i16;
                                }
                            } else {
                                z = z2;
                            }
                        }
                    }
                }
                z = z2;
            }
            if (z && this.f5467h) {
                m3455a(cvVar2, i5);
            }
        }
        return cvVar2;
    }

    /* renamed from: b */
    public C5054cv mo47091b(int i, int i2, int i3, C5093eg egVar, C5054cv cvVar) {
        return mo47090a(i, i2, i3, egVar, cvVar);
    }

    /* renamed from: a */
    private boolean m3458a(C5054cv cvVar, C5048cp cpVar, int i, int[] iArr, int[] iArr2, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5 = cvVar.f5482f;
        int i6 = cvVar.f5479c + 2;
        int i7 = ((i6 * 4) / 2) - cvVar.f5479c;
        int i8 = (i5 * 4) - cvVar.f5479c;
        int i9 = (3 << i) >> 1;
        int[] iArr3 = (int[]) cvVar.mo47103b();
        int i10 = ((cvVar.f5480d + 4) - 1) / 4;
        boolean z2 = (this.f5466g & 8) != 0;
        int i11 = (-i6) - 1;
        int i12 = (-i6) + 1;
        int i13 = i6 + 1;
        int i14 = i6 - 1;
        int i15 = cvVar.f5481e;
        int i16 = i6 + 1;
        for (int i17 = i10 - 1; i17 >= 0; i17--) {
            if (i17 != 0) {
                i2 = 4;
            } else {
                i2 = cvVar.f5480d - ((i10 - 1) * 4);
            }
            int i18 = i15 + cvVar.f5479c;
            while (true) {
                i3 = i16;
                i4 = i15;
                if (i4 >= i18) {
                    break;
                }
                int i19 = iArr[i3];
                if (((i19 ^ -1) & (i19 << 2) & -2147450880) != 0) {
                    if ((40960 & i19) == 8192) {
                        if (cpVar.mo47085a(iArr2[i19 & 255]) != 0) {
                            int i20 = f5460l[(i19 >>> 4) & 511];
                            int a = (i20 >>> 31) ^ cpVar.mo47085a(i20 & 15);
                            iArr3[i4] = (a << 31) | i9;
                            if (!z2) {
                                int i21 = i3 + i11;
                                iArr[i21] = iArr[i21] | 536936448;
                                int i22 = i3 + i12;
                                iArr[i22] = iArr[i22] | 537001984;
                            }
                            if (a != 0) {
                                i19 |= 606126080;
                                if (!z2) {
                                    int i23 = i3 - i6;
                                    iArr[i23] = iArr[i23] | 571473920;
                                }
                                int i24 = i3 + 1;
                                iArr[i24] = iArr[i24] | 537407616;
                                int i25 = i3 - 1;
                                iArr[i25] = iArr[i25] | 537143360;
                            } else {
                                i19 |= 539017216;
                                if (!z2) {
                                    int i26 = i3 - i6;
                                    iArr[i26] = iArr[i26] | 537919488;
                                }
                                int i27 = i3 + 1;
                                iArr[i27] = iArr[i27] | 537403520;
                                int i28 = i3 - 1;
                                iArr[i28] = iArr[i28] | 537141312;
                            }
                        } else {
                            i19 |= 16384;
                        }
                    }
                    if (i2 < 2) {
                        iArr[i3] = i19;
                        i15 = i4 + 1;
                        i16 = i3 + 1;
                    } else {
                        if ((-1610612736 & i19) == 536870912) {
                            int i29 = i4 + i5;
                            if (cpVar.mo47085a(iArr2[(i19 >>> 16) & 255]) != 0) {
                                int i30 = f5460l[(i19 >>> 20) & 511];
                                int a2 = (i30 >>> 31) ^ cpVar.mo47085a(i30 & 15);
                                iArr3[i29] = (a2 << 31) | i9;
                                int i31 = i3 + i14;
                                iArr[i31] = iArr[i31] | 8196;
                                int i32 = i3 + i13;
                                iArr[i32] = iArr[i32] | 8200;
                                if (a2 != 0) {
                                    i19 |= -1073733104;
                                    int i33 = i3 + i6;
                                    iArr[i33] = iArr[i33] | 9248;
                                    int i34 = i3 + 1;
                                    iArr[i34] = iArr[i34] | 813703170;
                                    int i35 = i3 - 1;
                                    iArr[i35] = iArr[i35] | 675291137;
                                } else {
                                    i19 |= -1073733616;
                                    int i36 = i3 + i6;
                                    iArr[i36] = iArr[i36] | 8224;
                                    int i37 = i3 + 1;
                                    iArr[i37] = iArr[i37] | 545267714;
                                    int i38 = i3 - 1;
                                    iArr[i38] = iArr[i38] | 541073409;
                                }
                            } else {
                                i19 |= 1073741824;
                            }
                        }
                        iArr[i3] = i19;
                    }
                }
                if (i2 >= 3) {
                    int i39 = i3 + i6;
                    int i40 = iArr[i39];
                    if (((i40 ^ -1) & (i40 << 2) & -2147450880) != 0) {
                        int i41 = (i5 << 1) + i4;
                        if ((40960 & i40) == 8192) {
                            if (cpVar.mo47085a(iArr2[i40 & 255]) != 0) {
                                int i42 = f5460l[(i40 >>> 4) & 511];
                                int a3 = (i42 >>> 31) ^ cpVar.mo47085a(i42 & 15);
                                iArr3[i41] = (a3 << 31) | i9;
                                int i43 = i39 + i11;
                                iArr[i43] = iArr[i43] | 536936448;
                                int i44 = i39 + i12;
                                iArr[i44] = iArr[i44] | 537001984;
                                if (a3 != 0) {
                                    i40 |= 606126080;
                                    int i45 = i39 - i6;
                                    iArr[i45] = iArr[i45] | 571473920;
                                    int i46 = i39 + 1;
                                    iArr[i46] = iArr[i46] | 537407616;
                                    int i47 = i39 - 1;
                                    iArr[i47] = iArr[i47] | 537143360;
                                } else {
                                    i40 |= 539017216;
                                    int i48 = i39 - i6;
                                    iArr[i48] = iArr[i48] | 537919488;
                                    int i49 = i39 + 1;
                                    iArr[i49] = iArr[i49] | 537403520;
                                    int i50 = i39 - 1;
                                    iArr[i50] = iArr[i50] | 537141312;
                                }
                            } else {
                                i40 |= 16384;
                            }
                        }
                        if (i2 < 4) {
                            iArr[i39] = i40;
                        } else {
                            if ((-1610612736 & i40) == 536870912) {
                                int i51 = i41 + i5;
                                if (cpVar.mo47085a(iArr2[(i40 >>> 16) & 255]) != 0) {
                                    int i52 = f5460l[(i40 >>> 20) & 511];
                                    int a4 = (i52 >>> 31) ^ cpVar.mo47085a(i52 & 15);
                                    iArr3[i51] = (a4 << 31) | i9;
                                    int i53 = i39 + i14;
                                    iArr[i53] = iArr[i53] | 8196;
                                    int i54 = i39 + i13;
                                    iArr[i54] = iArr[i54] | 8200;
                                    if (a4 != 0) {
                                        i40 |= -1073733104;
                                        int i55 = i39 + i6;
                                        iArr[i55] = iArr[i55] | 9248;
                                        int i56 = i39 + 1;
                                        iArr[i56] = iArr[i56] | 813703170;
                                        int i57 = i39 - 1;
                                        iArr[i57] = iArr[i57] | 675291137;
                                    } else {
                                        i40 |= -1073733616;
                                        int i58 = i39 + i6;
                                        iArr[i58] = iArr[i58] | 8224;
                                        int i59 = i39 + 1;
                                        iArr[i59] = iArr[i59] | 545267714;
                                        int i60 = i39 - 1;
                                        iArr[i60] = iArr[i60] | 541073409;
                                    }
                                } else {
                                    i40 |= 1073741824;
                                }
                            }
                            iArr[i39] = i40;
                        }
                    }
                }
                i15 = i4 + 1;
                i16 = i3 + 1;
            }
            i15 = i4 + i8;
            i16 = i3 + i7;
        }
        boolean z3 = false;
        if (z && (this.f5466g & 16) != 0) {
            z3 = cpVar.mo47087a();
        }
        if ((this.f5466g & 2) != 0) {
            cpVar.mo47088b();
        }
        return z3;
    }

    /* renamed from: a */
    private boolean m3456a(C5054cv cvVar, C5044cl clVar, int i, int[] iArr, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5 = cvVar.f5482f;
        int i6 = cvVar.f5479c + 2;
        int i7 = ((i6 * 4) / 2) - cvVar.f5479c;
        int i8 = (i5 * 4) - cvVar.f5479c;
        int i9 = (3 << i) >> 1;
        int[] iArr2 = (int[]) cvVar.mo47103b();
        int i10 = ((cvVar.f5480d + 4) - 1) / 4;
        boolean z2 = (this.f5466g & 8) != 0;
        int i11 = (-i6) - 1;
        int i12 = (-i6) + 1;
        int i13 = i6 + 1;
        int i14 = i6 - 1;
        int i15 = cvVar.f5481e;
        int i16 = i6 + 1;
        for (int i17 = i10 - 1; i17 >= 0; i17--) {
            if (i17 != 0) {
                i2 = 4;
            } else {
                i2 = cvVar.f5480d - ((i10 - 1) * 4);
            }
            int i18 = i15 + cvVar.f5479c;
            while (true) {
                i3 = i16;
                i4 = i15;
                if (i4 >= i18) {
                    break;
                }
                int i19 = iArr[i3];
                if (((i19 ^ -1) & (i19 << 2) & -2147450880) != 0) {
                    if ((40960 & i19) == 8192) {
                        if (clVar.mo47082a() != 0) {
                            int a = clVar.mo47082a();
                            iArr2[i4] = (a << 31) | i9;
                            if (!z2) {
                                int i20 = i3 + i11;
                                iArr[i20] = iArr[i20] | 536936448;
                                int i21 = i3 + i12;
                                iArr[i21] = iArr[i21] | 537001984;
                            }
                            if (a != 0) {
                                i19 |= 606126080;
                                if (!z2) {
                                    int i22 = i3 - i6;
                                    iArr[i22] = iArr[i22] | 571473920;
                                }
                                int i23 = i3 + 1;
                                iArr[i23] = iArr[i23] | 537407616;
                                int i24 = i3 - 1;
                                iArr[i24] = iArr[i24] | 537143360;
                            } else {
                                i19 |= 539017216;
                                if (!z2) {
                                    int i25 = i3 - i6;
                                    iArr[i25] = iArr[i25] | 537919488;
                                }
                                int i26 = i3 + 1;
                                iArr[i26] = iArr[i26] | 537403520;
                                int i27 = i3 - 1;
                                iArr[i27] = iArr[i27] | 537141312;
                            }
                        } else {
                            i19 |= 16384;
                        }
                    }
                    if (i2 < 2) {
                        iArr[i3] = i19;
                        i15 = i4 + 1;
                        i16 = i3 + 1;
                    } else {
                        if ((-1610612736 & i19) == 536870912) {
                            int i28 = i4 + i5;
                            if (clVar.mo47082a() != 0) {
                                int a2 = clVar.mo47082a();
                                iArr2[i28] = (a2 << 31) | i9;
                                int i29 = i3 + i14;
                                iArr[i29] = iArr[i29] | 8196;
                                int i30 = i3 + i13;
                                iArr[i30] = iArr[i30] | 8200;
                                if (a2 != 0) {
                                    i19 |= -1073733104;
                                    int i31 = i3 + i6;
                                    iArr[i31] = iArr[i31] | 9248;
                                    int i32 = i3 + 1;
                                    iArr[i32] = iArr[i32] | 813703170;
                                    int i33 = i3 - 1;
                                    iArr[i33] = iArr[i33] | 675291137;
                                } else {
                                    i19 |= -1073733616;
                                    int i34 = i3 + i6;
                                    iArr[i34] = iArr[i34] | 8224;
                                    int i35 = i3 + 1;
                                    iArr[i35] = iArr[i35] | 545267714;
                                    int i36 = i3 - 1;
                                    iArr[i36] = iArr[i36] | 541073409;
                                }
                            } else {
                                i19 |= 1073741824;
                            }
                        }
                        iArr[i3] = i19;
                    }
                }
                if (i2 >= 3) {
                    int i37 = i3 + i6;
                    int i38 = iArr[i37];
                    if (((i38 ^ -1) & (i38 << 2) & -2147450880) != 0) {
                        int i39 = (i5 << 1) + i4;
                        if ((40960 & i38) == 8192) {
                            if (clVar.mo47082a() != 0) {
                                int a3 = clVar.mo47082a();
                                iArr2[i39] = (a3 << 31) | i9;
                                int i40 = i37 + i11;
                                iArr[i40] = iArr[i40] | 536936448;
                                int i41 = i37 + i12;
                                iArr[i41] = iArr[i41] | 537001984;
                                if (a3 != 0) {
                                    i38 |= 606126080;
                                    int i42 = i37 - i6;
                                    iArr[i42] = iArr[i42] | 571473920;
                                    int i43 = i37 + 1;
                                    iArr[i43] = iArr[i43] | 537407616;
                                    int i44 = i37 - 1;
                                    iArr[i44] = iArr[i44] | 537143360;
                                } else {
                                    i38 |= 539017216;
                                    int i45 = i37 - i6;
                                    iArr[i45] = iArr[i45] | 537919488;
                                    int i46 = i37 + 1;
                                    iArr[i46] = iArr[i46] | 537403520;
                                    int i47 = i37 - 1;
                                    iArr[i47] = iArr[i47] | 537141312;
                                }
                            } else {
                                i38 |= 16384;
                            }
                        }
                        if (i2 < 4) {
                            iArr[i37] = i38;
                        } else {
                            if ((-1610612736 & i38) == 536870912) {
                                int i48 = i39 + i5;
                                if (clVar.mo47082a() != 0) {
                                    int a4 = clVar.mo47082a();
                                    iArr2[i48] = (a4 << 31) | i9;
                                    int i49 = i37 + i14;
                                    iArr[i49] = iArr[i49] | 8196;
                                    int i50 = i37 + i13;
                                    iArr[i50] = iArr[i50] | 8200;
                                    if (a4 != 0) {
                                        i38 |= -1073733104;
                                        int i51 = i37 + i6;
                                        iArr[i51] = iArr[i51] | 9248;
                                        int i52 = i37 + 1;
                                        iArr[i52] = iArr[i52] | 813703170;
                                        int i53 = i37 - 1;
                                        iArr[i53] = iArr[i53] | 675291137;
                                    } else {
                                        i38 |= -1073733616;
                                        int i54 = i37 + i6;
                                        iArr[i54] = iArr[i54] | 8224;
                                        int i55 = i37 + 1;
                                        iArr[i55] = iArr[i55] | 545267714;
                                        int i56 = i37 - 1;
                                        iArr[i56] = iArr[i56] | 541073409;
                                    }
                                } else {
                                    i38 |= 1073741824;
                                }
                            }
                            iArr[i37] = i38;
                        }
                    }
                }
                i15 = i4 + 1;
                i16 = i3 + 1;
            }
            i15 = i4 + i8;
            i16 = i3 + i7;
        }
        if (!z || (this.f5466g & 16) == 0) {
            return false;
        }
        return clVar.mo47084b();
    }

    /* renamed from: a */
    private boolean m3457a(C5054cv cvVar, C5048cp cpVar, int i, int[] iArr, boolean z) {
        int i2;
        int i3 = cvVar.f5482f;
        int i4 = cvVar.f5479c + 2;
        int i5 = ((i4 * 4) / 2) - cvVar.f5479c;
        int i6 = (i3 * 4) - cvVar.f5479c;
        int i7 = (1 << i) >> 1;
        int i8 = -1 << (i + 1);
        int[] iArr2 = (int[]) cvVar.mo47103b();
        int i9 = ((cvVar.f5480d + 4) - 1) / 4;
        int i10 = cvVar.f5481e;
        int i11 = i4 + 1;
        for (int i12 = i9 - 1; i12 >= 0; i12--) {
            if (i12 != 0) {
                i2 = 4;
            } else {
                i2 = cvVar.f5480d - ((i9 - 1) * 4);
            }
            int i13 = i10 + cvVar.f5479c;
            int i14 = i11;
            int i15 = i10;
            while (i15 < i13) {
                int i16 = iArr[i14];
                if (((i16 >>> 1) & (i16 ^ -1) & 1073758208) != 0) {
                    if ((49152 & i16) == 32768) {
                        int a = cpVar.mo47085a(f5461m[i16 & 511]);
                        iArr2[i15] = iArr2[i15] & i8;
                        iArr2[i15] = (a << i) | i7 | iArr2[i15];
                        i16 |= 256;
                    }
                    if (i2 < 2) {
                        iArr[i14] = i16;
                        i14++;
                        i15++;
                    } else {
                        if ((-1073741824 & i16) == Integer.MIN_VALUE) {
                            int i17 = i15 + i3;
                            int a2 = cpVar.mo47085a(f5461m[(i16 >>> 16) & 511]);
                            iArr2[i17] = iArr2[i17] & i8;
                            iArr2[i17] = (a2 << i) | i7 | iArr2[i17];
                            i16 |= 16777216;
                        }
                        iArr[i14] = i16;
                    }
                }
                if (i2 >= 3) {
                    int i18 = i14 + i4;
                    int i19 = iArr[i18];
                    if (((i19 >>> 1) & (i19 ^ -1) & 1073758208) != 0) {
                        int i20 = (i3 << 1) + i15;
                        if ((49152 & i19) == 32768) {
                            int a3 = cpVar.mo47085a(f5461m[i19 & 511]);
                            iArr2[i20] = iArr2[i20] & i8;
                            iArr2[i20] = (a3 << i) | i7 | iArr2[i20];
                            i19 |= 256;
                        }
                        if (i2 < 4) {
                            iArr[i18] = i19;
                        } else {
                            if ((iArr[i18] & -1073741824) == Integer.MIN_VALUE) {
                                int i21 = i20 + i3;
                                int a4 = cpVar.mo47085a(f5461m[(i19 >>> 16) & 511]);
                                iArr2[i21] = iArr2[i21] & i8;
                                iArr2[i21] = (a4 << i) | i7 | iArr2[i21];
                                i19 |= 16777216;
                            }
                            iArr[i18] = i19;
                        }
                    }
                }
                i14++;
                i15++;
            }
            i10 = i15 + i6;
            i11 = i14 + i5;
        }
        boolean z2 = false;
        if (z && (this.f5466g & 16) != 0) {
            z2 = cpVar.mo47087a();
        }
        if ((this.f5466g & 2) != 0) {
            cpVar.mo47088b();
        }
        return z2;
    }

    /* renamed from: b */
    private boolean m3459b(C5054cv cvVar, C5044cl clVar, int i, int[] iArr, boolean z) {
        int i2;
        int i3 = cvVar.f5482f;
        int i4 = cvVar.f5479c + 2;
        int i5 = ((i4 * 4) / 2) - cvVar.f5479c;
        int i6 = (i3 * 4) - cvVar.f5479c;
        int i7 = (1 << i) >> 1;
        int i8 = -1 << (i + 1);
        int[] iArr2 = (int[]) cvVar.mo47103b();
        int i9 = ((cvVar.f5480d + 4) - 1) / 4;
        int i10 = cvVar.f5481e;
        int i11 = i4 + 1;
        for (int i12 = i9 - 1; i12 >= 0; i12--) {
            if (i12 != 0) {
                i2 = 4;
            } else {
                i2 = cvVar.f5480d - ((i9 - 1) * 4);
            }
            int i13 = cvVar.f5479c + i10;
            while (i10 < i13) {
                int i14 = iArr[i11];
                if (((i14 >>> 1) & (i14 ^ -1) & 1073758208) != 0) {
                    if ((49152 & i14) == 32768) {
                        int a = clVar.mo47082a();
                        iArr2[i10] = iArr2[i10] & i8;
                        iArr2[i10] = (a << i) | i7 | iArr2[i10];
                    }
                    if (i2 < 2) {
                        i10++;
                        i11++;
                    } else if ((i14 & -1073741824) == Integer.MIN_VALUE) {
                        int i15 = i10 + i3;
                        int a2 = clVar.mo47082a();
                        iArr2[i15] = iArr2[i15] & i8;
                        iArr2[i15] = (a2 << i) | i7 | iArr2[i15];
                    }
                }
                if (i2 >= 3) {
                    int i16 = i11 + i4;
                    int i17 = iArr[i16];
                    if (((i17 >>> 1) & (i17 ^ -1) & 1073758208) != 0) {
                        int i18 = (i3 << 1) + i10;
                        if ((i17 & 49152) == 32768) {
                            int a3 = clVar.mo47082a();
                            iArr2[i18] = iArr2[i18] & i8;
                            iArr2[i18] = (a3 << i) | i7 | iArr2[i18];
                        }
                        if (i2 >= 4 && (iArr[i16] & -1073741824) == Integer.MIN_VALUE) {
                            int i19 = i18 + i3;
                            int a4 = clVar.mo47082a();
                            iArr2[i19] = iArr2[i19] & i8;
                            iArr2[i19] = (a4 << i) | i7 | iArr2[i19];
                        }
                    }
                }
                i10++;
                i11++;
            }
            i10 += i6;
            i11 += i5;
        }
        if (!z || (this.f5466g & 16) == 0) {
            return false;
        }
        return clVar.mo47084b();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0100, code lost:
        if ((r21 >> 1) != 0) goto L_0x0102;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m3460b(jumio.p317nv.nfc.C5054cv r27, jumio.p317nv.nfc.C5048cp r28, int r29, int[] r30, int[] r31, boolean r32) {
        /*
            r26 = this;
            r0 = r27
            int r10 = r0.f5482f
            r0 = r27
            int r2 = r0.f5479c
            int r11 = r2 + 2
            int r2 = r11 * 4
            int r2 = r2 / 2
            r0 = r27
            int r3 = r0.f5479c
            int r12 = r2 - r3
            int r2 = r10 * 4
            r0 = r27
            int r3 = r0.f5479c
            int r13 = r2 - r3
            r2 = 3
            int r2 = r2 << r29
            int r14 = r2 >> 1
            java.lang.Object r2 = r27.mo47103b()
            int[] r2 = (int[]) r2
            int[] r2 = (int[]) r2
            r0 = r27
            int r3 = r0.f5480d
            int r3 = r3 + 4
            int r3 = r3 + -1
            int r15 = r3 / 4
            r0 = r26
            int r3 = r0.f5466g
            r3 = r3 & 8
            if (r3 == 0) goto L_0x019e
            r3 = 1
        L_0x003c:
            int r4 = -r11
            int r16 = r4 + -1
            int r4 = -r11
            int r17 = r4 + 1
            int r18 = r11 + 1
            int r19 = r11 + -1
            r0 = r27
            int r5 = r0.f5481e
            int r7 = r11 + 1
            int r4 = r15 + -1
            r9 = r4
        L_0x004f:
            if (r9 < 0) goto L_0x04bc
            if (r9 == 0) goto L_0x01a1
            r4 = 4
        L_0x0054:
            r0 = r27
            int r6 = r0.f5479c
            int r20 = r5 + r6
            r8 = r5
        L_0x005b:
            r0 = r20
            if (r8 >= r0) goto L_0x04b4
            r5 = r30[r7]
            if (r5 != 0) goto L_0x026b
            int r6 = r7 + r11
            r6 = r30[r6]
            if (r6 != 0) goto L_0x026b
            r6 = 4
            if (r4 != r6) goto L_0x026b
            r6 = 1
            r0 = r28
            int r6 = r0.mo47085a(r6)
            if (r6 == 0) goto L_0x0197
            r6 = 0
            r0 = r28
            int r6 = r0.mo47085a(r6)
            int r6 = r6 << 1
            r21 = 0
            r0 = r28
            r1 = r21
            int r21 = r0.mo47085a(r1)
            r21 = r21 | r6
            int r6 = r21 * r10
            int r22 = r8 + r6
            r6 = 1
            r0 = r21
            if (r0 <= r6) goto L_0x050c
            int r6 = r7 + r11
            r5 = r30[r6]
        L_0x0097:
            r23 = r21 & 1
            if (r23 != 0) goto L_0x01d7
            int[] r23 = f5460l
            int r24 = r5 >> 4
            r0 = r24
            r0 = r0 & 511(0x1ff, float:7.16E-43)
            r24 = r0
            r23 = r23[r24]
            r24 = r23 & 15
            r0 = r28
            r1 = r24
            int r24 = r0.mo47085a(r1)
            int r23 = r23 >>> 31
            r23 = r23 ^ r24
            int r24 = r23 << 31
            r24 = r24 | r14
            r2[r22] = r24
            if (r21 != 0) goto L_0x00bf
            if (r3 != 0) goto L_0x00d3
        L_0x00bf:
            int r22 = r6 + r16
            r24 = r30[r22]
            r25 = 536936448(0x20010000, float:1.0926725E-19)
            r24 = r24 | r25
            r30[r22] = r24
            int r22 = r6 + r17
            r24 = r30[r22]
            r25 = 537001984(0x20020000, float:1.1011428E-19)
            r24 = r24 | r25
            r30[r22] = r24
        L_0x00d3:
            if (r23 == 0) goto L_0x01ac
            r22 = 606126080(0x2420c000, float:3.48571E-17)
            r5 = r5 | r22
            if (r21 != 0) goto L_0x00de
            if (r3 != 0) goto L_0x00e8
        L_0x00de:
            int r22 = r6 - r11
            r23 = r30[r22]
            r24 = 571473920(0x22100000, float:1.951564E-18)
            r23 = r23 | r24
            r30[r22] = r23
        L_0x00e8:
            int r22 = r6 + 1
            r23 = r30[r22]
            r24 = 537407616(0x20083080, float:1.1535695E-19)
            r23 = r23 | r24
            r30[r22] = r23
            int r22 = r6 + -1
            r23 = r30[r22]
            r24 = 537143360(0x20042840, float:1.1194153E-19)
            r23 = r23 | r24
            r30[r22] = r23
        L_0x00fe:
            int r21 = r21 >> 1
            if (r21 == 0) goto L_0x026c
        L_0x0102:
            int r21 = r5 >> 1
            r21 = r21 | r5
            r22 = 1073758208(0x40004000, float:2.0039062)
            r21 = r21 & r22
            r22 = 1073758208(0x40004000, float:2.0039062)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0483
            int r21 = r10 << 1
            int r21 = r21 + r8
            r22 = 49152(0xc000, float:6.8877E-41)
            r22 = r22 & r5
            if (r22 != 0) goto L_0x018a
            r0 = r5 & 255(0xff, float:3.57E-43)
            r22 = r0
            r22 = r31[r22]
            r0 = r28
            r1 = r22
            int r22 = r0.mo47085a(r1)
            if (r22 == 0) goto L_0x018a
            int[] r22 = f5460l
            int r23 = r5 >> 4
            r0 = r23
            r0 = r0 & 511(0x1ff, float:7.16E-43)
            r23 = r0
            r22 = r22[r23]
            r23 = r22 & 15
            r0 = r28
            r1 = r23
            int r23 = r0.mo47085a(r1)
            int r22 = r22 >>> 31
            r22 = r22 ^ r23
            int r23 = r22 << 31
            r23 = r23 | r14
            r2[r21] = r23
            int r23 = r6 + r16
            r24 = r30[r23]
            r25 = 536936448(0x20010000, float:1.0926725E-19)
            r24 = r24 | r25
            r30[r23] = r24
            int r23 = r6 + r17
            r24 = r30[r23]
            r25 = 537001984(0x20020000, float:1.1011428E-19)
            r24 = r24 | r25
            r30[r23] = r24
            if (r22 == 0) goto L_0x03df
            r22 = 606126080(0x2420c000, float:3.48571E-17)
            r5 = r5 | r22
            int r22 = r6 - r11
            r23 = r30[r22]
            r24 = 571473920(0x22100000, float:1.951564E-18)
            r23 = r23 | r24
            r30[r22] = r23
            int r22 = r6 + 1
            r23 = r30[r22]
            r24 = 537407616(0x20083080, float:1.1535695E-19)
            r23 = r23 | r24
            r30[r22] = r23
            int r22 = r6 + -1
            r23 = r30[r22]
            r24 = 537143360(0x20042840, float:1.1194153E-19)
            r23 = r23 | r24
            r30[r22] = r23
        L_0x018a:
            r22 = 4
            r0 = r22
            if (r4 >= r0) goto L_0x0406
            r21 = -1073758209(0xffffffffbfffbfff, float:-1.9980468)
            r5 = r5 & r21
            r30[r6] = r5
        L_0x0197:
            int r5 = r8 + 1
            int r7 = r7 + 1
            r8 = r5
            goto L_0x005b
        L_0x019e:
            r3 = 0
            goto L_0x003c
        L_0x01a1:
            r0 = r27
            int r4 = r0.f5480d
            int r6 = r15 + -1
            int r6 = r6 * 4
            int r4 = r4 - r6
            goto L_0x0054
        L_0x01ac:
            r22 = 539017216(0x2020c000, float:1.3616055E-19)
            r5 = r5 | r22
            if (r21 != 0) goto L_0x01b5
            if (r3 != 0) goto L_0x01bf
        L_0x01b5:
            int r22 = r6 - r11
            r23 = r30[r22]
            r24 = 537919488(0x20100000, float:1.2197274E-19)
            r23 = r23 | r24
            r30[r22] = r23
        L_0x01bf:
            int r22 = r6 + 1
            r23 = r30[r22]
            r24 = 537403520(0x20082080, float:1.1530401E-19)
            r23 = r23 | r24
            r30[r22] = r23
            int r22 = r6 + -1
            r23 = r30[r22]
            r24 = 537141312(0x20042040, float:1.1191506E-19)
            r23 = r23 | r24
            r30[r22] = r23
            goto L_0x00fe
        L_0x01d7:
            int[] r23 = f5460l
            int r24 = r5 >> 20
            r0 = r24
            r0 = r0 & 511(0x1ff, float:7.16E-43)
            r24 = r0
            r23 = r23[r24]
            r24 = r23 & 15
            r0 = r28
            r1 = r24
            int r24 = r0.mo47085a(r1)
            int r23 = r23 >>> 31
            r23 = r23 ^ r24
            int r24 = r23 << 31
            r24 = r24 | r14
            r2[r22] = r24
            int r22 = r6 + r19
            r24 = r30[r22]
            r0 = r24
            r0 = r0 | 8196(0x2004, float:1.1485E-41)
            r24 = r0
            r30[r22] = r24
            int r22 = r6 + r18
            r24 = r30[r22]
            r0 = r24
            r0 = r0 | 8200(0x2008, float:1.149E-41)
            r24 = r0
            r30[r22] = r24
            if (r23 == 0) goto L_0x0243
            r22 = -2147474928(0xffffffff80002210, float:-1.222E-41)
            r5 = r5 | r22
            int r22 = r6 + r11
            r23 = r30[r22]
            r0 = r23
            r0 = r0 | 9248(0x2420, float:1.2959E-41)
            r23 = r0
            r30[r22] = r23
            int r22 = r6 + 1
            r23 = r30[r22]
            r24 = 813703170(0x30802002, float:9.322323E-10)
            r23 = r23 | r24
            r30[r22] = r23
            int r22 = r6 + -1
            r23 = r30[r22]
            r24 = 675291137(0x28402001, float:1.0665081E-14)
            r23 = r23 | r24
            r30[r22] = r23
        L_0x0238:
            r30[r6] = r5
            int r5 = r21 >> 1
            if (r5 != 0) goto L_0x0197
            int r6 = r6 + r11
            r5 = r30[r6]
            goto L_0x0102
        L_0x0243:
            r22 = -2147475440(0xffffffff80002010, float:-1.1502E-41)
            r5 = r5 | r22
            int r22 = r6 + r11
            r23 = r30[r22]
            r0 = r23
            r0 = r0 | 8224(0x2020, float:1.1524E-41)
            r23 = r0
            r30[r22] = r23
            int r22 = r6 + 1
            r23 = r30[r22]
            r24 = 545267714(0x20802002, float:2.1705224E-19)
            r23 = r23 | r24
            r30[r22] = r23
            int r22 = r6 + -1
            r23 = r30[r22]
            r24 = 541073409(0x20402001, float:1.6273622E-19)
            r23 = r23 | r24
            r30[r22] = r23
            goto L_0x0238
        L_0x026b:
            r6 = r7
        L_0x026c:
            int r21 = r5 >> 1
            r21 = r21 | r5
            r22 = 1073758208(0x40004000, float:2.0039062)
            r21 = r21 & r22
            r22 = 1073758208(0x40004000, float:2.0039062)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x03a8
            r21 = 49152(0xc000, float:6.8877E-41)
            r21 = r21 & r5
            if (r21 != 0) goto L_0x02f4
            r0 = r5 & 255(0xff, float:3.57E-43)
            r21 = r0
            r21 = r31[r21]
            r0 = r28
            r1 = r21
            int r21 = r0.mo47085a(r1)
            if (r21 == 0) goto L_0x02f4
            int[] r21 = f5460l
            int r22 = r5 >>> 4
            r0 = r22
            r0 = r0 & 511(0x1ff, float:7.16E-43)
            r22 = r0
            r21 = r21[r22]
            r22 = r21 & 15
            r0 = r28
            r1 = r22
            int r22 = r0.mo47085a(r1)
            int r21 = r21 >>> 31
            r21 = r21 ^ r22
            int r22 = r21 << 31
            r22 = r22 | r14
            r2[r8] = r22
            if (r3 != 0) goto L_0x02cb
            int r22 = r6 + r16
            r23 = r30[r22]
            r24 = 536936448(0x20010000, float:1.0926725E-19)
            r23 = r23 | r24
            r30[r22] = r23
            int r22 = r6 + r17
            r23 = r30[r22]
            r24 = 537001984(0x20020000, float:1.1011428E-19)
            r23 = r23 | r24
            r30[r22] = r23
        L_0x02cb:
            if (r21 == 0) goto L_0x0303
            r21 = 606126080(0x2420c000, float:3.48571E-17)
            r5 = r5 | r21
            if (r3 != 0) goto L_0x02de
            int r21 = r6 - r11
            r22 = r30[r21]
            r23 = 571473920(0x22100000, float:1.951564E-18)
            r22 = r22 | r23
            r30[r21] = r22
        L_0x02de:
            int r21 = r6 + 1
            r22 = r30[r21]
            r23 = 537407616(0x20083080, float:1.1535695E-19)
            r22 = r22 | r23
            r30[r21] = r22
            int r21 = r6 + -1
            r22 = r30[r21]
            r23 = 537143360(0x20042840, float:1.1194153E-19)
            r22 = r22 | r23
            r30[r21] = r22
        L_0x02f4:
            r21 = 2
            r0 = r21
            if (r4 >= r0) goto L_0x032b
            r21 = -1073758209(0xffffffffbfffbfff, float:-1.9980468)
            r5 = r5 & r21
            r30[r6] = r5
            goto L_0x0197
        L_0x0303:
            r21 = 539017216(0x2020c000, float:1.3616055E-19)
            r5 = r5 | r21
            if (r3 != 0) goto L_0x0314
            int r21 = r6 - r11
            r22 = r30[r21]
            r23 = 537919488(0x20100000, float:1.2197274E-19)
            r22 = r22 | r23
            r30[r21] = r22
        L_0x0314:
            int r21 = r6 + 1
            r22 = r30[r21]
            r23 = 537403520(0x20082080, float:1.1530401E-19)
            r22 = r22 | r23
            r30[r21] = r22
            int r21 = r6 + -1
            r22 = r30[r21]
            r23 = 537141312(0x20042040, float:1.1191506E-19)
            r22 = r22 | r23
            r30[r21] = r22
            goto L_0x02f4
        L_0x032b:
            r21 = -1073741824(0xffffffffc0000000, float:-2.0)
            r21 = r21 & r5
            if (r21 != 0) goto L_0x03a8
            int r21 = r8 + r10
            int r22 = r5 >>> 16
            r0 = r22
            r0 = r0 & 255(0xff, float:3.57E-43)
            r22 = r0
            r22 = r31[r22]
            r0 = r28
            r1 = r22
            int r22 = r0.mo47085a(r1)
            if (r22 == 0) goto L_0x03a8
            int[] r22 = f5460l
            int r23 = r5 >>> 20
            r0 = r23
            r0 = r0 & 511(0x1ff, float:7.16E-43)
            r23 = r0
            r22 = r22[r23]
            r23 = r22 & 15
            r0 = r28
            r1 = r23
            int r23 = r0.mo47085a(r1)
            int r22 = r22 >>> 31
            r22 = r22 ^ r23
            int r23 = r22 << 31
            r23 = r23 | r14
            r2[r21] = r23
            int r21 = r6 + r19
            r23 = r30[r21]
            r0 = r23
            r0 = r0 | 8196(0x2004, float:1.1485E-41)
            r23 = r0
            r30[r21] = r23
            int r21 = r6 + r18
            r23 = r30[r21]
            r0 = r23
            r0 = r0 | 8200(0x2008, float:1.149E-41)
            r23 = r0
            r30[r21] = r23
            if (r22 == 0) goto L_0x03b7
            r21 = -1073733104(0xffffffffc0002210, float:-2.002079)
            r5 = r5 | r21
            int r21 = r6 + r11
            r22 = r30[r21]
            r0 = r22
            r0 = r0 | 9248(0x2420, float:1.2959E-41)
            r22 = r0
            r30[r21] = r22
            int r21 = r6 + 1
            r22 = r30[r21]
            r23 = 813703170(0x30802002, float:9.322323E-10)
            r22 = r22 | r23
            r30[r21] = r22
            int r21 = r6 + -1
            r22 = r30[r21]
            r23 = 675291137(0x28402001, float:1.0665081E-14)
            r22 = r22 | r23
            r30[r21] = r22
        L_0x03a8:
            r21 = -1073758209(0xffffffffbfffbfff, float:-1.9980468)
            r5 = r5 & r21
            r30[r6] = r5
            r5 = 3
            if (r4 < r5) goto L_0x0197
            int r6 = r6 + r11
            r5 = r30[r6]
            goto L_0x0102
        L_0x03b7:
            r21 = -1073733616(0xffffffffc0002010, float:-2.001957)
            r5 = r5 | r21
            int r21 = r6 + r11
            r22 = r30[r21]
            r0 = r22
            r0 = r0 | 8224(0x2020, float:1.1524E-41)
            r22 = r0
            r30[r21] = r22
            int r21 = r6 + 1
            r22 = r30[r21]
            r23 = 545267714(0x20802002, float:2.1705224E-19)
            r22 = r22 | r23
            r30[r21] = r22
            int r21 = r6 + -1
            r22 = r30[r21]
            r23 = 541073409(0x20402001, float:1.6273622E-19)
            r22 = r22 | r23
            r30[r21] = r22
            goto L_0x03a8
        L_0x03df:
            r22 = 539017216(0x2020c000, float:1.3616055E-19)
            r5 = r5 | r22
            int r22 = r6 - r11
            r23 = r30[r22]
            r24 = 537919488(0x20100000, float:1.2197274E-19)
            r23 = r23 | r24
            r30[r22] = r23
            int r22 = r6 + 1
            r23 = r30[r22]
            r24 = 537403520(0x20082080, float:1.1530401E-19)
            r23 = r23 | r24
            r30[r22] = r23
            int r22 = r6 + -1
            r23 = r30[r22]
            r24 = 537141312(0x20042040, float:1.1191506E-19)
            r23 = r23 | r24
            r30[r22] = r23
            goto L_0x018a
        L_0x0406:
            r22 = -1073741824(0xffffffffc0000000, float:-2.0)
            r22 = r22 & r5
            if (r22 != 0) goto L_0x0483
            int r21 = r21 + r10
            int r22 = r5 >>> 16
            r0 = r22
            r0 = r0 & 255(0xff, float:3.57E-43)
            r22 = r0
            r22 = r31[r22]
            r0 = r28
            r1 = r22
            int r22 = r0.mo47085a(r1)
            if (r22 == 0) goto L_0x0483
            int[] r22 = f5460l
            int r23 = r5 >>> 20
            r0 = r23
            r0 = r0 & 511(0x1ff, float:7.16E-43)
            r23 = r0
            r22 = r22[r23]
            r23 = r22 & 15
            r0 = r28
            r1 = r23
            int r23 = r0.mo47085a(r1)
            int r22 = r22 >>> 31
            r22 = r22 ^ r23
            int r23 = r22 << 31
            r23 = r23 | r14
            r2[r21] = r23
            int r21 = r6 + r19
            r23 = r30[r21]
            r0 = r23
            r0 = r0 | 8196(0x2004, float:1.1485E-41)
            r23 = r0
            r30[r21] = r23
            int r21 = r6 + r18
            r23 = r30[r21]
            r0 = r23
            r0 = r0 | 8200(0x2008, float:1.149E-41)
            r23 = r0
            r30[r21] = r23
            if (r22 == 0) goto L_0x048c
            r21 = -1073733104(0xffffffffc0002210, float:-2.002079)
            r5 = r5 | r21
            int r21 = r6 + r11
            r22 = r30[r21]
            r0 = r22
            r0 = r0 | 9248(0x2420, float:1.2959E-41)
            r22 = r0
            r30[r21] = r22
            int r21 = r6 + 1
            r22 = r30[r21]
            r23 = 813703170(0x30802002, float:9.322323E-10)
            r22 = r22 | r23
            r30[r21] = r22
            int r21 = r6 + -1
            r22 = r30[r21]
            r23 = 675291137(0x28402001, float:1.0665081E-14)
            r22 = r22 | r23
            r30[r21] = r22
        L_0x0483:
            r21 = -1073758209(0xffffffffbfffbfff, float:-1.9980468)
            r5 = r5 & r21
            r30[r6] = r5
            goto L_0x0197
        L_0x048c:
            r21 = -1073733616(0xffffffffc0002010, float:-2.001957)
            r5 = r5 | r21
            int r21 = r6 + r11
            r22 = r30[r21]
            r0 = r22
            r0 = r0 | 8224(0x2020, float:1.1524E-41)
            r22 = r0
            r30[r21] = r22
            int r21 = r6 + 1
            r22 = r30[r21]
            r23 = 545267714(0x20802002, float:2.1705224E-19)
            r22 = r22 | r23
            r30[r21] = r22
            int r21 = r6 + -1
            r22 = r30[r21]
            r23 = 541073409(0x20402001, float:1.6273622E-19)
            r22 = r22 | r23
            r30[r21] = r22
            goto L_0x0483
        L_0x04b4:
            int r4 = r9 + -1
            int r5 = r8 + r13
            int r7 = r7 + r12
            r9 = r4
            goto L_0x004f
        L_0x04bc:
            r0 = r26
            int r2 = r0.f5466g
            r2 = r2 & 32
            if (r2 == 0) goto L_0x050a
            r2 = 0
            r0 = r28
            int r2 = r0.mo47085a(r2)
            int r2 = r2 << 3
            r3 = 0
            r0 = r28
            int r3 = r0.mo47085a(r3)
            int r3 = r3 << 2
            r2 = r2 | r3
            r3 = 0
            r0 = r28
            int r3 = r0.mo47085a(r3)
            int r3 = r3 << 1
            r2 = r2 | r3
            r3 = 0
            r0 = r28
            int r3 = r0.mo47085a(r3)
            r2 = r2 | r3
            r3 = 10
            if (r2 == r3) goto L_0x0508
            r2 = 1
        L_0x04ee:
            if (r32 == 0) goto L_0x04fc
            r0 = r26
            int r3 = r0.f5466g
            r3 = r3 & 16
            if (r3 == 0) goto L_0x04fc
            boolean r2 = r28.mo47087a()
        L_0x04fc:
            r0 = r26
            int r3 = r0.f5466g
            r3 = r3 & 2
            if (r3 == 0) goto L_0x0507
            r28.mo47088b()
        L_0x0507:
            return r2
        L_0x0508:
            r2 = 0
            goto L_0x04ee
        L_0x050a:
            r2 = 0
            goto L_0x04ee
        L_0x050c:
            r6 = r7
            goto L_0x0097
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C5049cq.m3460b(jumio.nv.nfc.cv, jumio.nv.nfc.cp, int, int[], int[], boolean):boolean");
    }

    /* renamed from: a */
    private void m3455a(C5054cv cvVar, int i) {
        int i2 = 1 << i;
        int i3 = -1 << i;
        int[] iArr = (int[]) cvVar.mo47103b();
        int i4 = cvVar.f5481e;
        for (int i5 = cvVar.f5480d - 1; i5 >= 0; i5--) {
            int i6 = cvVar.f5479c + i4;
            while (i4 < i6) {
                int i7 = iArr[i4];
                if ((i7 & i3 & Integer.MAX_VALUE) != 0) {
                    iArr[i4] = (i7 & i3) | i2;
                } else {
                    iArr[i4] = 0;
                }
                i4++;
            }
            i4 += cvVar.f5482f - cvVar.f5479c;
        }
    }
}

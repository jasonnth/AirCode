package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.ec */
/* compiled from: InvWTFull */
public class C5089ec extends C5090ed {

    /* renamed from: e */
    private int f5557e = 0;

    /* renamed from: f */
    private C5084dy f5558f;

    /* renamed from: g */
    private int f5559g;

    /* renamed from: h */
    private C5054cv[] f5560h;

    /* renamed from: i */
    private int[] f5561i;

    public C5089ec(C5084dy dyVar, C5039cg cgVar) {
        super(dyVar, cgVar);
        this.f5558f = dyVar;
        int c = dyVar.mo47009c();
        this.f5560h = new C5054cv[c];
        this.f5561i = new int[c];
    }

    /* renamed from: b */
    public int mo46940b(int i) {
        return this.f5558f.mo47128g(i);
    }

    /* renamed from: a */
    public int mo46934a(int i) {
        return this.f5558f.mo47130h(i);
    }

    /* renamed from: b */
    public final C5054cv mo46936b(C5054cv cvVar, int i) {
        int e = mo47110e();
        if (this.f5558f.mo47019e(e, i).mo47153g() == null) {
            this.f5559g = 3;
        } else {
            this.f5559g = this.f5558f.mo47019e(e, i).mo47153g().mo47146a();
        }
        if (this.f5560h[i] == null) {
            switch (this.f5559g) {
                case 3:
                    this.f5560h[i] = new C5056cx(0, 0, mo46941b(e, i), mo46938a(e, i));
                    break;
                case 4:
                    this.f5560h[i] = new C5055cw(0, 0, mo46941b(e, i), mo46938a(e, i));
                    break;
            }
            m3621b(this.f5560h[i], this.f5558f.mo47019e(e, i), i);
        }
        if (cvVar.mo47101a() != this.f5559g) {
            if (this.f5559g == 3) {
                cvVar = new C5056cx(cvVar.f5477a, cvVar.f5478b, cvVar.f5479c, cvVar.f5480d);
            } else {
                cvVar = new C5055cw(cvVar.f5477a, cvVar.f5478b, cvVar.f5479c, cvVar.f5480d);
            }
        }
        cvVar.mo47102a(this.f5560h[i].mo47103b());
        cvVar.f5481e = (this.f5560h[i].f5479c * cvVar.f5478b) + cvVar.f5477a;
        cvVar.f5482f = this.f5560h[i].f5479c;
        cvVar.f5483g = false;
        return cvVar;
    }

    /* renamed from: a */
    public C5054cv mo46935a(C5054cv cvVar, int i) {
        float[] fArr = 0;
        switch (cvVar.mo47101a()) {
            case 3:
                fArr = (int[]) cvVar.mo47103b();
                if (fArr == 0 || fArr.length < cvVar.f5479c * cvVar.f5480d) {
                    fArr = new int[(cvVar.f5479c * cvVar.f5480d)];
                    break;
                }
            case 4:
                fArr = (float[]) cvVar.mo47103b();
                if (fArr == 0 || fArr.length < cvVar.f5479c * cvVar.f5480d) {
                    fArr = new float[(cvVar.f5479c * cvVar.f5480d)];
                    break;
                }
        }
        C5054cv b = mo46936b(cvVar, i);
        b.mo47102a(fArr);
        b.f5481e = 0;
        b.f5482f = b.f5479c;
        return b;
    }

    /* renamed from: a */
    private void m3620a(C5054cv cvVar, C5093eg egVar, int i) {
        if (egVar.f5546l != 0 && egVar.f5547m != 0) {
            Object b = cvVar.mo47103b();
            int i2 = egVar.f5544j;
            int i3 = egVar.f5545k;
            int i4 = egVar.f5546l;
            int i5 = egVar.f5547m;
            Object obj = null;
            switch (egVar.mo47153g().mo47146a()) {
                case 3:
                    obj = new int[(i4 >= i5 ? i4 : i5)];
                    break;
                case 4:
                    obj = new float[(i4 >= i5 ? i4 : i5)];
                    break;
            }
            int i6 = (((i3 - cvVar.f5478b) * cvVar.f5479c) + i2) - cvVar.f5477a;
            if (egVar.f5542h % 2 == 0) {
                int i7 = 0;
                while (true) {
                    int i8 = i7;
                    if (i8 < i5) {
                        System.arraycopy(b, i6, obj, 0, i4);
                        egVar.f5564p.mo47155a(obj, 0, (i4 + 1) / 2, 1, obj, (i4 + 1) / 2, i4 / 2, 1, b, i6, 1);
                        i7 = i8 + 1;
                        i6 += cvVar.f5479c;
                    }
                }
            } else {
                int i9 = 0;
                while (true) {
                    int i10 = i9;
                    if (i10 < i5) {
                        System.arraycopy(b, i6, obj, 0, i4);
                        egVar.f5564p.mo47156b(obj, 0, i4 / 2, 1, obj, i4 / 2, (i4 + 1) / 2, 1, b, i6, 1);
                        i9 = i10 + 1;
                        i6 += cvVar.f5479c;
                    }
                }
            }
            int i11 = (((i3 - cvVar.f5478b) * cvVar.f5479c) + i2) - cvVar.f5477a;
            switch (egVar.mo47154h().mo47146a()) {
                case 3:
                    int[] iArr = (int[]) b;
                    int[] iArr2 = (int[]) obj;
                    if (egVar.f5543i % 2 == 0) {
                        int i12 = 0;
                        while (true) {
                            int i13 = i12;
                            if (i13 < i4) {
                                int i14 = i5 - 1;
                                int i15 = (cvVar.f5479c * i14) + i11;
                                while (i14 >= 0) {
                                    iArr2[i14] = iArr[i15];
                                    i14--;
                                    i15 -= cvVar.f5479c;
                                }
                                egVar.f5565q.mo47155a(obj, 0, (i5 + 1) / 2, 1, obj, (i5 + 1) / 2, i5 / 2, 1, b, i11, cvVar.f5479c);
                                i12 = i13 + 1;
                                i11++;
                            } else {
                                return;
                            }
                        }
                    } else {
                        int i16 = 0;
                        while (true) {
                            int i17 = i16;
                            if (i17 < i4) {
                                int i18 = i5 - 1;
                                int i19 = (cvVar.f5479c * i18) + i11;
                                while (i18 >= 0) {
                                    iArr2[i18] = iArr[i19];
                                    i18--;
                                    i19 -= cvVar.f5479c;
                                }
                                egVar.f5565q.mo47156b(obj, 0, i5 / 2, 1, obj, i5 / 2, (i5 + 1) / 2, 1, b, i11, cvVar.f5479c);
                                i16 = i17 + 1;
                                i11++;
                            } else {
                                return;
                            }
                        }
                    }
                case 4:
                    float[] fArr = (float[]) b;
                    float[] fArr2 = (float[]) obj;
                    if (egVar.f5543i % 2 == 0) {
                        int i20 = 0;
                        while (true) {
                            int i21 = i20;
                            if (i21 < i4) {
                                int i22 = i5 - 1;
                                int i23 = (cvVar.f5479c * i22) + i11;
                                while (i22 >= 0) {
                                    fArr2[i22] = fArr[i23];
                                    i22--;
                                    i23 -= cvVar.f5479c;
                                }
                                egVar.f5565q.mo47155a(obj, 0, (i5 + 1) / 2, 1, obj, (i5 + 1) / 2, i5 / 2, 1, b, i11, cvVar.f5479c);
                                i20 = i21 + 1;
                                i11++;
                            } else {
                                return;
                            }
                        }
                    } else {
                        int i24 = 0;
                        while (true) {
                            int i25 = i24;
                            if (i25 < i4) {
                                int i26 = i5 - 1;
                                int i27 = (cvVar.f5479c * i26) + i11;
                                while (i26 >= 0) {
                                    fArr2[i26] = fArr[i27];
                                    i26--;
                                    i27 -= cvVar.f5479c;
                                }
                                egVar.f5565q.mo47156b(obj, 0, i5 / 2, 1, obj, i5 / 2, (i5 + 1) / 2, 1, b, i11, cvVar.f5479c);
                                i24 = i25 + 1;
                                i11++;
                            } else {
                                return;
                            }
                        }
                    }
                default:
                    return;
            }
        }
    }

    /* renamed from: b */
    private void m3621b(C5054cv cvVar, C5093eg egVar, int i) {
        C5054cv cwVar;
        if (!egVar.f5535a) {
            if (egVar.f5546l != 0 && egVar.f5547m != 0) {
                if (this.f5559g == 3) {
                    cwVar = new C5056cx();
                } else {
                    cwVar = new C5055cw();
                }
                C5053cu cuVar = egVar.f5539e;
                Object b = cvVar.mo47103b();
                int i2 = 0;
                while (i2 < cuVar.f5476b) {
                    C5054cv cvVar2 = cwVar;
                    for (int i3 = 0; i3 < cuVar.f5475a; i3++) {
                        cvVar2 = this.f5558f.mo47129a(i, i2, i3, egVar, cvVar2);
                        Object b2 = cvVar2.mo47103b();
                        for (int i4 = cvVar2.f5480d - 1; i4 >= 0; i4--) {
                            System.arraycopy(b2, cvVar2.f5481e + (cvVar2.f5482f * i4), b, ((cvVar2.f5478b + i4) * cvVar.f5479c) + cvVar2.f5477a, cvVar2.f5479c);
                        }
                    }
                    i2++;
                    cwVar = cvVar2;
                }
            }
        } else if (egVar.f5535a) {
            m3621b(cvVar, (C5093eg) egVar.mo47141b(), i);
            if (egVar.f5538d <= (this.f5555c - this.f5556d) + this.f5561i[i]) {
                m3621b(cvVar, (C5093eg) egVar.mo47142c(), i);
                m3621b(cvVar, (C5093eg) egVar.mo47143d(), i);
                m3621b(cvVar, (C5093eg) egVar.mo47144e(), i);
                m3620a(cvVar, egVar, i);
            }
        }
    }

    /* renamed from: c */
    public void mo47108c(int i, int i2) {
        super.mo47108c(i, i2);
        int c = this.f5558f.mo47009c();
        int d = this.f5558f.mo47013d();
        for (int i3 = 0; i3 < c; i3++) {
            this.f5561i[i3] = this.f5558f.mo47019e(d, i3).f5538d;
        }
        if (this.f5560h != null) {
            for (int length = this.f5560h.length - 1; length >= 0; length--) {
                this.f5560h[length] = null;
            }
        }
        this.f5557e = 0;
        for (int i4 = 0; i4 < c; i4++) {
            C5093eg e = this.f5558f.mo47019e(d, i4);
            for (int i5 = 0; i5 <= (this.f5555c - this.f5556d) + e.f5538d; i5++) {
                if (i5 == 0) {
                    C5093eg egVar = (C5093eg) e.mo47139a(0, 0);
                    if (egVar != null) {
                        this.f5557e = (egVar.f5539e.f5476b * egVar.f5539e.f5475a) + this.f5557e;
                    }
                } else {
                    C5093eg egVar2 = (C5093eg) e.mo47139a(i5, 1);
                    if (egVar2 != null) {
                        this.f5557e = (egVar2.f5539e.f5476b * egVar2.f5539e.f5475a) + this.f5557e;
                    }
                    C5093eg egVar3 = (C5093eg) e.mo47139a(i5, 2);
                    if (egVar3 != null) {
                        this.f5557e = (egVar3.f5539e.f5476b * egVar3.f5539e.f5475a) + this.f5557e;
                    }
                    C5093eg egVar4 = (C5093eg) e.mo47139a(i5, 3);
                    if (egVar4 != null) {
                        this.f5557e = (egVar4.f5539e.f5476b * egVar4.f5539e.f5475a) + this.f5557e;
                    }
                }
            }
        }
    }
}

package jumio.p317nv.nfc;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Vector;

/* renamed from: jumio.nv.nfc.cc */
/* compiled from: PktDecoder */
public class C5035cc {

    /* renamed from: A */
    private int f5354A;

    /* renamed from: B */
    private int f5355B;

    /* renamed from: C */
    private int f5356C;

    /* renamed from: D */
    private boolean f5357D;

    /* renamed from: a */
    private C5030by f5358a;

    /* renamed from: b */
    private boolean f5359b = false;

    /* renamed from: c */
    private ByteArrayInputStream f5360c;

    /* renamed from: d */
    private C5039cg f5361d;

    /* renamed from: e */
    private C5034cb f5362e;

    /* renamed from: f */
    private final int f5363f = 3;

    /* renamed from: g */
    private C5036cd f5364g;

    /* renamed from: h */
    private C5065df f5365h;

    /* renamed from: i */
    private C5053cu[][] f5366i;

    /* renamed from: j */
    private int f5367j;

    /* renamed from: k */
    private C5029bx[][][] f5368k;

    /* renamed from: l */
    private int[][][][][] f5369l;

    /* renamed from: m */
    private C5037ce[][][][] f5370m;

    /* renamed from: n */
    private C5037ce[][][][] f5371n;

    /* renamed from: o */
    private int f5372o = 0;

    /* renamed from: p */
    private int f5373p;

    /* renamed from: q */
    private boolean f5374q = false;

    /* renamed from: r */
    private boolean f5375r = false;

    /* renamed from: s */
    private int f5376s;

    /* renamed from: t */
    private Vector[] f5377t;

    /* renamed from: u */
    private int f5378u;

    /* renamed from: v */
    private int f5379v;

    /* renamed from: w */
    private boolean f5380w;

    /* renamed from: x */
    private int f5381x;

    /* renamed from: y */
    private int f5382y;

    /* renamed from: z */
    private int f5383z;

    public C5035cc(C5039cg cgVar, C5034cb cbVar, C5065df dfVar, C5030by byVar, boolean z, int i) {
        this.f5361d = cgVar;
        this.f5362e = cbVar;
        this.f5365h = dfVar;
        this.f5357D = z;
        this.f5364g = new C5036cd(dfVar);
        this.f5358a = byVar;
        this.f5378u = 0;
        this.f5380w = false;
        this.f5379v = i;
    }

    /* renamed from: a */
    public C5031bz[][][][][] mo47065a(int i, int[] iArr, int i2, C5031bz[][][][][] bzVarArr, boolean z, ByteArrayInputStream byteArrayInputStream) {
        int i3;
        this.f5373p = i;
        this.f5372o = i2;
        this.f5367j = this.f5358a.mo47013d();
        this.f5359b = z;
        this.f5360c = byteArrayInputStream;
        this.f5374q = ((Boolean) this.f5361d.f5415o.mo46979d(this.f5367j)).booleanValue();
        this.f5376s = 0;
        this.f5375r = ((Boolean) this.f5361d.f5416p.mo46979d(this.f5367j)).booleanValue();
        C5031bz[][][][][] bzVarArr2 = new C5031bz[i][][][][];
        this.f5369l = new int[i][][][][];
        this.f5370m = new C5037ce[i][][][];
        this.f5371n = new C5037ce[i][][][];
        this.f5366i = new C5053cu[i][];
        this.f5368k = new C5029bx[i][][];
        int a = this.f5358a.mo46998a();
        int b = this.f5358a.mo47004b();
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= i) {
                return bzVarArr2;
            }
            bzVarArr2[i5] = new C5031bz[(iArr[i5] + 1)][][][];
            this.f5369l[i5] = new int[(iArr[i5] + 1)][][][];
            this.f5370m[i5] = new C5037ce[(iArr[i5] + 1)][][];
            this.f5371n[i5] = new C5037ce[(iArr[i5] + 1)][][];
            this.f5366i[i5] = new C5053cu[(iArr[i5] + 1)];
            this.f5368k[i5] = new C5029bx[(iArr[i5] + 1)][];
            int c = this.f5358a.mo47011c(i5, iArr[i5]);
            int d = this.f5358a.mo47015d(i5, iArr[i5]);
            int a2 = c + this.f5358a.mo47001a(this.f5367j, i5, iArr[i5]);
            int b2 = d + this.f5358a.mo47007b(this.f5367j, i5, iArr[i5]);
            int i6 = 0;
            while (true) {
                int i7 = i6;
                if (i7 > iArr[i5]) {
                    break;
                }
                int ceil = (int) Math.ceil(((double) c) / ((double) (1 << (iArr[i5] - i7))));
                int ceil2 = (int) Math.ceil(((double) d) / ((double) (1 << (iArr[i5] - i7))));
                int ceil3 = (int) Math.ceil(((double) a2) / ((double) (1 << (iArr[i5] - i7))));
                int ceil4 = (int) Math.ceil(((double) b2) / ((double) (1 << (iArr[i5] - i7))));
                double a3 = (double) mo47061a(this.f5367j, i5, i7);
                double b3 = (double) mo47066b(this.f5367j, i5, i7);
                this.f5366i[i5][i7] = new C5053cu();
                if (ceil3 > ceil) {
                    this.f5366i[i5][i7].f5475a = ((int) Math.ceil(((double) (ceil3 - a)) / a3)) - ((int) Math.floor(((double) (ceil - a)) / a3));
                } else {
                    this.f5366i[i5][i7].f5475a = 0;
                }
                if (ceil4 > ceil2) {
                    this.f5366i[i5][i7].f5476b = ((int) Math.ceil(((double) (ceil4 - b)) / b3)) - ((int) Math.floor(((double) (ceil2 - b)) / b3));
                } else {
                    this.f5366i[i5][i7].f5476b = 0;
                }
                if (i7 == 0) {
                    i3 = 1;
                } else {
                    i3 = 4;
                }
                int i8 = this.f5366i[i5][i7].f5476b * this.f5366i[i5][i7].f5475a;
                this.f5370m[i5][i7] = (C5037ce[][]) Array.newInstance(C5037ce.class, new int[]{i8, i3 + 1});
                this.f5371n[i5][i7] = (C5037ce[][]) Array.newInstance(C5037ce.class, new int[]{i8, i3 + 1});
                bzVarArr2[i5][i7] = new C5031bz[(i3 + 1)][][];
                this.f5369l[i5][i7] = new int[(i3 + 1)][][];
                this.f5368k[i5][i7] = new C5029bx[i8];
                m3410d(i5, i7, iArr[i5]);
                C5093eg e = this.f5358a.mo47019e(this.f5367j, i5);
                for (int i9 = i7 == 0 ? 0 : 1; i9 < i3; i9++) {
                    C5053cu cuVar = ((C5093eg) e.mo47139a(i7, i9)).f5539e;
                    bzVarArr2[i5][i7][i9] = (C5031bz[][]) Array.newInstance(C5031bz.class, new int[]{cuVar.f5476b, cuVar.f5475a});
                    this.f5369l[i5][i7][i9] = (int[][]) Array.newInstance(Integer.TYPE, new int[]{cuVar.f5476b, cuVar.f5475a});
                    for (int i10 = cuVar.f5476b - 1; i10 >= 0; i10--) {
                        C5076dq.m3563a(this.f5369l[i5][i7][i9][i10], 3);
                    }
                }
                i6 = i7 + 1;
            }
            i4 = i5 + 1;
        }
    }

    /* renamed from: d */
    private void m3410d(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        if (this.f5368k[i][i2].length != 0) {
            C5053cu a = this.f5358a.mo47002a((C5053cu) null);
            C5053cu b = this.f5358a.mo47008b((C5053cu) null);
            int h = this.f5358a.mo47022h();
            int i13 = this.f5358a.mo47023i();
            int j = this.f5358a.mo47024j();
            int k = this.f5358a.mo47025k();
            int e = this.f5362e.mo47048e();
            int f = this.f5362e.mo47050f();
            this.f5362e.mo47042c();
            this.f5362e.mo47045d();
            int i14 = a.f5475a == 0 ? e : (a.f5475a * j) + h;
            if (a.f5476b == 0) {
                i4 = f;
            } else {
                i4 = (a.f5476b * k) + i13;
            }
            if (a.f5475a != b.f5475a - 1) {
                int i15 = ((a.f5475a + 1) * j) + h;
            }
            if (a.f5476b != b.f5476b - 1) {
                int i16 = ((a.f5476b + 1) * k) + i13;
            }
            int b2 = this.f5362e.mo47040b(i);
            int c = this.f5362e.mo47043c(i);
            int c2 = this.f5358a.mo47011c(i, i3);
            int d = this.f5358a.mo47015d(i, i3);
            int b3 = this.f5358a.mo47007b(this.f5367j, i, i3) + d;
            int i17 = i3 - i2;
            int ceil = (int) Math.ceil(((double) c2) / ((double) (1 << i17)));
            int ceil2 = (int) Math.ceil(((double) d) / ((double) (1 << i17)));
            int ceil3 = (int) Math.ceil(((double) (this.f5358a.mo47001a(this.f5367j, i, i3) + c2)) / ((double) (1 << i17)));
            int ceil4 = (int) Math.ceil(((double) b3) / ((double) (1 << i17)));
            int a2 = this.f5358a.mo46998a();
            int b4 = this.f5358a.mo47004b();
            double a3 = (double) mo47061a(this.f5367j, i, i2);
            double b5 = (double) mo47066b(this.f5367j, i, i2);
            int i18 = (int) (a3 / 2.0d);
            int i19 = (int) (b5 / 2.0d);
            int length = this.f5368k[i][i2].length;
            int i20 = 0;
            int floor = (int) Math.floor(((double) (ceil2 - b4)) / b5);
            int floor2 = (int) Math.floor(((double) ((ceil4 - 1) - b4)) / b5);
            int floor3 = (int) Math.floor(((double) (ceil - a2)) / a3);
            int floor4 = (int) Math.floor(((double) ((ceil3 - 1) - a2)) / a3);
            C5093eg e2 = this.f5358a.mo47019e(this.f5367j, i);
            int i21 = ((int) a3) << i17;
            int i22 = ((int) b5) << i17;
            int i23 = floor;
            while (i23 <= floor2) {
                int i24 = i20;
                for (int i25 = floor3; i25 <= floor4; i25++) {
                    if (i25 != floor3 || (ceil - a2) % (((int) a3) * b2) == 0) {
                        i5 = a2 + (i25 * b2 * (((int) a3) << i17));
                    } else {
                        i5 = i14;
                    }
                    if (i23 != floor || (ceil2 - b4) % (((int) b5) * c) == 0) {
                        i6 = b4 + (i23 * c * (((int) b5) << i17));
                    } else {
                        i6 = i4;
                    }
                    this.f5368k[i][i2][i24] = new C5029bx(i2, (int) (((double) a2) + (((double) i25) * a3)), (int) (((double) b4) + (((double) i23) * b5)), (int) a3, (int) b5, i5, i6, i21, i22);
                    if (i2 == 0) {
                        int i26 = a2 + (((int) a3) * i25);
                        int i27 = i26 + ((int) a3);
                        int i28 = b4 + (((int) b5) * i23);
                        int i29 = i28 + ((int) b5);
                        C5093eg egVar = (C5093eg) e2.mo47139a(0, 0);
                        int i30 = i26 < egVar.f5542h ? egVar.f5542h : i26;
                        if (i27 > egVar.f5542h + egVar.f5546l) {
                            i10 = egVar.f5542h + egVar.f5546l;
                        } else {
                            i10 = i27;
                        }
                        if (i28 < egVar.f5543i) {
                            i11 = egVar.f5543i;
                        } else {
                            i11 = i28;
                        }
                        if (i29 > egVar.f5543i + egVar.f5547m) {
                            i12 = egVar.f5543i + egVar.f5547m;
                        } else {
                            i12 = i29;
                        }
                        int i31 = egVar.f5548n;
                        int i32 = egVar.f5549o;
                        int floor5 = (int) Math.floor(((double) (egVar.f5543i - b4)) / ((double) i32));
                        int floor6 = (int) Math.floor(((double) (i11 - b4)) / ((double) i32));
                        int floor7 = (int) Math.floor(((double) ((i12 - 1) - b4)) / ((double) i32));
                        int floor8 = (int) Math.floor(((double) (egVar.f5542h - a2)) / ((double) i31));
                        int floor9 = (int) Math.floor(((double) (i30 - a2)) / ((double) i31));
                        int floor10 = (int) Math.floor(((double) ((i10 - 1) - a2)) / ((double) i31));
                        if (i10 - i30 <= 0 || i12 - i11 <= 0) {
                            this.f5368k[i][i2][i24].f5261k[0] = 0;
                            this.f5370m[i][i2][i24][0] = new C5037ce(0, 0);
                            this.f5371n[i][i2][i24][0] = new C5037ce(0, 0);
                        } else {
                            this.f5370m[i][i2][i24][0] = new C5037ce((floor7 - floor6) + 1, (floor10 - floor9) + 1);
                            this.f5371n[i][i2][i24][0] = new C5037ce((floor7 - floor6) + 1, (floor10 - floor9) + 1);
                            this.f5368k[i][i2][i24].f5260j[0] = (C5015bt[][]) Array.newInstance(C5015bt.class, new int[]{(floor7 - floor6) + 1, (floor10 - floor9) + 1});
                            this.f5368k[i][i2][i24].f5261k[0] = ((floor7 - floor6) + 1) * ((floor10 - floor9) + 1);
                            for (int i33 = floor6; i33 <= floor7; i33++) {
                                for (int i34 = floor9; i34 <= floor10; i34++) {
                                    C5015bt btVar = new C5015bt(i33 - floor5, i34 - floor8);
                                    if (i34 == floor8) {
                                        btVar.f5152b = egVar.f5544j;
                                    } else {
                                        btVar.f5152b = (egVar.f5544j + (i34 * i31)) - (egVar.f5542h - a2);
                                    }
                                    if (i33 == floor5) {
                                        btVar.f5153c = egVar.f5545k;
                                    } else {
                                        btVar.f5153c = (egVar.f5545k + (i33 * i32)) - (egVar.f5543i - b4);
                                    }
                                    int i35 = (i34 * i31) + a2;
                                    if (i35 <= egVar.f5542h) {
                                        i35 = egVar.f5542h;
                                    }
                                    int i36 = ((i34 + 1) * i31) + a2;
                                    if (i36 > egVar.f5542h + egVar.f5546l) {
                                        i36 = egVar.f5542h + egVar.f5546l;
                                    }
                                    btVar.f5154d = i36 - i35;
                                    int i37 = (i33 * i32) + b4;
                                    if (i37 <= egVar.f5543i) {
                                        i37 = egVar.f5543i;
                                    }
                                    int i38 = ((i33 + 1) * i32) + b4;
                                    if (i38 > egVar.f5543i + egVar.f5547m) {
                                        i38 = egVar.f5543i + egVar.f5547m;
                                    }
                                    btVar.f5155e = i38 - i37;
                                    this.f5368k[i][i2][i24].f5260j[0][i33 - floor6][i34 - floor9] = btVar;
                                }
                            }
                        }
                    } else {
                        int i39 = 0 + (i25 * i18);
                        int i40 = i39 + i18;
                        int i41 = b4 + (i23 * i19);
                        int i42 = i41 + i19;
                        C5093eg egVar2 = (C5093eg) e2.mo47139a(i2, 1);
                        int i43 = i39 < egVar2.f5542h ? egVar2.f5542h : i39;
                        if (i40 > egVar2.f5542h + egVar2.f5546l) {
                            i7 = egVar2.f5542h + egVar2.f5546l;
                        } else {
                            i7 = i40;
                        }
                        if (i41 < egVar2.f5543i) {
                            i8 = egVar2.f5543i;
                        } else {
                            i8 = i41;
                        }
                        if (i42 > egVar2.f5543i + egVar2.f5547m) {
                            i9 = egVar2.f5543i + egVar2.f5547m;
                        } else {
                            i9 = i42;
                        }
                        int i44 = egVar2.f5548n;
                        int i45 = egVar2.f5549o;
                        int floor11 = (int) Math.floor(((double) (egVar2.f5543i - b4)) / ((double) i45));
                        int floor12 = (int) Math.floor(((double) (i8 - b4)) / ((double) i45));
                        int floor13 = (int) Math.floor(((double) ((i9 - 1) - b4)) / ((double) i45));
                        int floor14 = (int) Math.floor(((double) (egVar2.f5542h - 0)) / ((double) i44));
                        int floor15 = (int) Math.floor(((double) (i43 - 0)) / ((double) i44));
                        int floor16 = (int) Math.floor(((double) ((i7 - 1) - 0)) / ((double) i44));
                        if (i7 - i43 <= 0 || i9 - i8 <= 0) {
                            this.f5368k[i][i2][i24].f5261k[1] = 0;
                            this.f5370m[i][i2][i24][1] = new C5037ce(0, 0);
                            this.f5371n[i][i2][i24][1] = new C5037ce(0, 0);
                        } else {
                            this.f5370m[i][i2][i24][1] = new C5037ce((floor13 - floor12) + 1, (floor16 - floor15) + 1);
                            this.f5371n[i][i2][i24][1] = new C5037ce((floor13 - floor12) + 1, (floor16 - floor15) + 1);
                            this.f5368k[i][i2][i24].f5260j[1] = (C5015bt[][]) Array.newInstance(C5015bt.class, new int[]{(floor13 - floor12) + 1, (floor16 - floor15) + 1});
                            this.f5368k[i][i2][i24].f5261k[1] = ((floor13 - floor12) + 1) * ((floor16 - floor15) + 1);
                            for (int i46 = floor12; i46 <= floor13; i46++) {
                                for (int i47 = floor15; i47 <= floor16; i47++) {
                                    C5015bt btVar2 = new C5015bt(i46 - floor11, i47 - floor14);
                                    if (i47 == floor14) {
                                        btVar2.f5152b = egVar2.f5544j;
                                    } else {
                                        btVar2.f5152b = (egVar2.f5544j + (i47 * i44)) - (egVar2.f5542h - 0);
                                    }
                                    if (i46 == floor11) {
                                        btVar2.f5153c = egVar2.f5545k;
                                    } else {
                                        btVar2.f5153c = (egVar2.f5545k + (i46 * i45)) - (egVar2.f5543i - b4);
                                    }
                                    int i48 = (i47 * i44) + 0;
                                    if (i48 <= egVar2.f5542h) {
                                        i48 = egVar2.f5542h;
                                    }
                                    int i49 = ((i47 + 1) * i44) + 0;
                                    if (i49 > egVar2.f5542h + egVar2.f5546l) {
                                        i49 = egVar2.f5542h + egVar2.f5546l;
                                    }
                                    btVar2.f5154d = i49 - i48;
                                    int i50 = (i46 * i45) + b4;
                                    if (i50 <= egVar2.f5543i) {
                                        i50 = egVar2.f5543i;
                                    }
                                    int i51 = ((i46 + 1) * i45) + b4;
                                    if (i51 > egVar2.f5543i + egVar2.f5547m) {
                                        i51 = egVar2.f5543i + egVar2.f5547m;
                                    }
                                    btVar2.f5155e = i51 - i50;
                                    this.f5368k[i][i2][i24].f5260j[1][i46 - floor12][i47 - floor15] = btVar2;
                                }
                            }
                        }
                        int i52 = a2 + (i25 * i18);
                        int i53 = i52 + i18;
                        int i54 = 0 + (i23 * i19);
                        int i55 = i54 + i19;
                        C5093eg egVar3 = (C5093eg) e2.mo47139a(i2, 2);
                        if (i52 < egVar3.f5542h) {
                            i52 = egVar3.f5542h;
                        }
                        if (i53 > egVar3.f5542h + egVar3.f5546l) {
                            i53 = egVar3.f5542h + egVar3.f5546l;
                        }
                        if (i54 < egVar3.f5543i) {
                            i54 = egVar3.f5543i;
                        }
                        if (i55 > egVar3.f5543i + egVar3.f5547m) {
                            i55 = egVar3.f5543i + egVar3.f5547m;
                        }
                        int i56 = egVar3.f5548n;
                        int i57 = egVar3.f5549o;
                        int floor17 = (int) Math.floor(((double) (egVar3.f5543i - 0)) / ((double) i57));
                        int floor18 = (int) Math.floor(((double) (i54 - 0)) / ((double) i57));
                        int floor19 = (int) Math.floor(((double) ((i55 - 1) - 0)) / ((double) i57));
                        int floor20 = (int) Math.floor(((double) (egVar3.f5542h - a2)) / ((double) i56));
                        int floor21 = (int) Math.floor(((double) (i52 - a2)) / ((double) i56));
                        int floor22 = (int) Math.floor(((double) ((i53 - 1) - a2)) / ((double) i56));
                        if (i53 - i52 <= 0 || i55 - i54 <= 0) {
                            this.f5368k[i][i2][i24].f5261k[2] = 0;
                            this.f5370m[i][i2][i24][2] = new C5037ce(0, 0);
                            this.f5371n[i][i2][i24][2] = new C5037ce(0, 0);
                        } else {
                            this.f5370m[i][i2][i24][2] = new C5037ce((floor19 - floor18) + 1, (floor22 - floor21) + 1);
                            this.f5371n[i][i2][i24][2] = new C5037ce((floor19 - floor18) + 1, (floor22 - floor21) + 1);
                            this.f5368k[i][i2][i24].f5260j[2] = (C5015bt[][]) Array.newInstance(C5015bt.class, new int[]{(floor19 - floor18) + 1, (floor22 - floor21) + 1});
                            this.f5368k[i][i2][i24].f5261k[2] = ((floor19 - floor18) + 1) * ((floor22 - floor21) + 1);
                            for (int i58 = floor18; i58 <= floor19; i58++) {
                                for (int i59 = floor21; i59 <= floor22; i59++) {
                                    C5015bt btVar3 = new C5015bt(i58 - floor17, i59 - floor20);
                                    if (i59 == floor20) {
                                        btVar3.f5152b = egVar3.f5544j;
                                    } else {
                                        btVar3.f5152b = (egVar3.f5544j + (i59 * i56)) - (egVar3.f5542h - a2);
                                    }
                                    if (i58 == floor17) {
                                        btVar3.f5153c = egVar3.f5545k;
                                    } else {
                                        btVar3.f5153c = (egVar3.f5545k + (i58 * i57)) - (egVar3.f5543i - 0);
                                    }
                                    int i60 = (i59 * i56) + a2;
                                    if (i60 <= egVar3.f5542h) {
                                        i60 = egVar3.f5542h;
                                    }
                                    int i61 = ((i59 + 1) * i56) + a2;
                                    if (i61 > egVar3.f5542h + egVar3.f5546l) {
                                        i61 = egVar3.f5542h + egVar3.f5546l;
                                    }
                                    btVar3.f5154d = i61 - i60;
                                    int i62 = (i58 * i57) + 0;
                                    if (i62 <= egVar3.f5543i) {
                                        i62 = egVar3.f5543i;
                                    }
                                    int i63 = ((i58 + 1) * i57) + 0;
                                    if (i63 > egVar3.f5543i + egVar3.f5547m) {
                                        i63 = egVar3.f5543i + egVar3.f5547m;
                                    }
                                    btVar3.f5155e = i63 - i62;
                                    this.f5368k[i][i2][i24].f5260j[2][i58 - floor18][i59 - floor21] = btVar3;
                                }
                            }
                        }
                        int i64 = 0 + (i25 * i18);
                        int i65 = i64 + i18;
                        int i66 = 0 + (i23 * i19);
                        int i67 = i66 + i19;
                        C5093eg egVar4 = (C5093eg) e2.mo47139a(i2, 3);
                        if (i64 < egVar4.f5542h) {
                            i64 = egVar4.f5542h;
                        }
                        if (i65 > egVar4.f5542h + egVar4.f5546l) {
                            i65 = egVar4.f5542h + egVar4.f5546l;
                        }
                        if (i66 < egVar4.f5543i) {
                            i66 = egVar4.f5543i;
                        }
                        if (i67 > egVar4.f5543i + egVar4.f5547m) {
                            i67 = egVar4.f5543i + egVar4.f5547m;
                        }
                        int i68 = egVar4.f5548n;
                        int i69 = egVar4.f5549o;
                        int floor23 = (int) Math.floor(((double) (egVar4.f5543i - 0)) / ((double) i69));
                        int floor24 = (int) Math.floor(((double) (i66 - 0)) / ((double) i69));
                        int floor25 = (int) Math.floor(((double) ((i67 - 1) - 0)) / ((double) i69));
                        int floor26 = (int) Math.floor(((double) (egVar4.f5542h - 0)) / ((double) i68));
                        int floor27 = (int) Math.floor(((double) (i64 - 0)) / ((double) i68));
                        int floor28 = (int) Math.floor(((double) ((i65 - 1) - 0)) / ((double) i68));
                        if (i65 - i64 <= 0 || i67 - i66 <= 0) {
                            this.f5368k[i][i2][i24].f5261k[3] = 0;
                            this.f5370m[i][i2][i24][3] = new C5037ce(0, 0);
                            this.f5371n[i][i2][i24][3] = new C5037ce(0, 0);
                        } else {
                            this.f5370m[i][i2][i24][3] = new C5037ce((floor25 - floor24) + 1, (floor28 - floor27) + 1);
                            this.f5371n[i][i2][i24][3] = new C5037ce((floor25 - floor24) + 1, (floor28 - floor27) + 1);
                            this.f5368k[i][i2][i24].f5260j[3] = (C5015bt[][]) Array.newInstance(C5015bt.class, new int[]{(floor25 - floor24) + 1, (floor28 - floor27) + 1});
                            this.f5368k[i][i2][i24].f5261k[3] = ((floor25 - floor24) + 1) * ((floor28 - floor27) + 1);
                            for (int i70 = floor24; i70 <= floor25; i70++) {
                                for (int i71 = floor27; i71 <= floor28; i71++) {
                                    C5015bt btVar4 = new C5015bt(i70 - floor23, i71 - floor26);
                                    if (i71 == floor26) {
                                        btVar4.f5152b = egVar4.f5544j;
                                    } else {
                                        btVar4.f5152b = (egVar4.f5544j + (i71 * i68)) - (egVar4.f5542h - 0);
                                    }
                                    if (i70 == floor23) {
                                        btVar4.f5153c = egVar4.f5545k;
                                    } else {
                                        btVar4.f5153c = (egVar4.f5545k + (i70 * i69)) - (egVar4.f5543i - 0);
                                    }
                                    int i72 = (i71 * i68) + 0;
                                    if (i72 <= egVar4.f5542h) {
                                        i72 = egVar4.f5542h;
                                    }
                                    int i73 = ((i71 + 1) * i68) + 0;
                                    if (i73 > egVar4.f5542h + egVar4.f5546l) {
                                        i73 = egVar4.f5542h + egVar4.f5546l;
                                    }
                                    btVar4.f5154d = i73 - i72;
                                    int i74 = (i70 * i69) + 0;
                                    if (i74 <= egVar4.f5543i) {
                                        i74 = egVar4.f5543i;
                                    }
                                    int i75 = ((i70 + 1) * i69) + 0;
                                    if (i75 > egVar4.f5543i + egVar4.f5547m) {
                                        i75 = egVar4.f5543i + egVar4.f5547m;
                                    }
                                    btVar4.f5155e = i75 - i74;
                                    this.f5368k[i][i2][i24].f5260j[3][i70 - floor24][i71 - floor27] = btVar4;
                                }
                            }
                        }
                    }
                    i24++;
                }
                i23++;
                i20 = i24;
            }
        }
    }

    /* renamed from: a */
    public int mo47060a(int i, int i2) {
        return this.f5366i[i][i2].f5475a * this.f5366i[i][i2].f5476b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x02e1 A[Catch:{ EOFException -> 0x0303 }] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x02e8 A[Catch:{ EOFException -> 0x0303 }, LOOP:6: B:105:0x02e2->B:107:0x02e8, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0307  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0362 A[Catch:{ EOFException -> 0x0303 }] */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x038d A[Catch:{ EOFException -> 0x0303 }] */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x03b9 A[Catch:{ EOFException -> 0x0303 }] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0495  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0287 A[Catch:{ EOFException -> 0x0303 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo47063a(int r30, int r31, int r32, int r33, jumio.p317nv.nfc.C5031bz[][][] r34, int[] r35) throws java.io.IOException {
        /*
            r29 = this;
            r5 = 0
            r0 = r29
            jumio.nv.nfc.df r3 = r0.f5365h
            int r19 = r3.mo47123e()
            r0 = r29
            jumio.nv.nfc.df r3 = r0.f5365h
            int r3 = r3.mo47124f()
            r0 = r19
            if (r0 < r3) goto L_0x0017
            r3 = 1
        L_0x0016:
            return r3
        L_0x0017:
            r0 = r29
            jumio.nv.nfc.by r3 = r0.f5358a
            int r20 = r3.mo47013d()
            r0 = r29
            jumio.nv.nfc.by r3 = r0.f5358a
            r0 = r20
            r1 = r32
            jumio.nv.nfc.eg r21 = r3.mo47019e(r0, r1)
            r0 = r29
            boolean r3 = r0.f5359b
            if (r3 == 0) goto L_0x0058
            jumio.nv.nfc.cd r3 = new jumio.nv.nfc.cd
            r0 = r29
            java.io.ByteArrayInputStream r4 = r0.f5360c
            r3.<init>(r4)
            r18 = r3
        L_0x003c:
            if (r31 != 0) goto L_0x005f
            r4 = 0
        L_0x003f:
            if (r31 != 0) goto L_0x0061
            r3 = 1
            r10 = r3
        L_0x0043:
            r3 = 0
            r6 = r4
        L_0x0045:
            if (r6 >= r10) goto L_0x0064
            r0 = r29
            jumio.nv.nfc.bx[][][] r7 = r0.f5368k
            r7 = r7[r32]
            r7 = r7[r31]
            int r7 = r7.length
            r0 = r33
            if (r0 >= r7) goto L_0x0055
            r3 = 1
        L_0x0055:
            int r6 = r6 + 1
            goto L_0x0045
        L_0x0058:
            r0 = r29
            jumio.nv.nfc.cd r3 = r0.f5364g
            r18 = r3
            goto L_0x003c
        L_0x005f:
            r4 = 1
            goto L_0x003f
        L_0x0061:
            r3 = 4
            r10 = r3
            goto L_0x0043
        L_0x0064:
            if (r3 != 0) goto L_0x0068
            r3 = 0
            goto L_0x0016
        L_0x0068:
            r0 = r29
            jumio.nv.nfc.bx[][][] r3 = r0.f5368k
            r3 = r3[r32]
            r3 = r3[r31]
            r22 = r3[r33]
            r18.mo47071b()
            int r3 = r18.mo47069a()
            if (r3 != 0) goto L_0x00d4
            int r3 = r10 + 1
            java.util.Vector[] r3 = new java.util.Vector[r3]
            r0 = r29
            r0.f5377t = r3
        L_0x0083:
            if (r4 >= r10) goto L_0x0093
            r0 = r29
            java.util.Vector[] r3 = r0.f5377t
            java.util.Vector r5 = new java.util.Vector
            r5.<init>()
            r3[r4] = r5
            int r4 = r4 + 1
            goto L_0x0083
        L_0x0093:
            r0 = r29
            int r3 = r0.f5376s
            int r3 = r3 + 1
            r0 = r29
            r0.f5376s = r3
            r0 = r29
            boolean r3 = r0.f5357D
            if (r3 == 0) goto L_0x00c4
            r0 = r29
            int r3 = r0.f5379v
            r4 = -1
            if (r3 != r4) goto L_0x00c4
            r0 = r29
            jumio.nv.nfc.df r3 = r0.f5365h
            int r3 = r3.mo47123e()
            int r3 = r3 - r19
            r4 = r35[r20]
            if (r3 <= r4) goto L_0x00be
            r3 = 0
            r35[r20] = r3
            r3 = 1
            goto L_0x0016
        L_0x00be:
            r4 = r35[r20]
            int r3 = r4 - r3
            r35[r20] = r3
        L_0x00c4:
            r0 = r29
            boolean r3 = r0.f5375r
            if (r3 == 0) goto L_0x00d1
            r0 = r29
            r1 = r18
            r0.mo47062a(r1)
        L_0x00d1:
            r3 = 0
            goto L_0x0016
        L_0x00d4:
            r0 = r29
            java.util.Vector[] r3 = r0.f5377t
            if (r3 == 0) goto L_0x00e3
            r0 = r29
            java.util.Vector[] r3 = r0.f5377t
            int r3 = r3.length
            int r6 = r10 + 1
            if (r3 >= r6) goto L_0x00eb
        L_0x00e3:
            int r3 = r10 + 1
            java.util.Vector[] r3 = new java.util.Vector[r3]
            r0 = r29
            r0.f5377t = r3
        L_0x00eb:
            r17 = r4
            r4 = r5
        L_0x00ee:
            r0 = r17
            if (r0 >= r10) goto L_0x04ba
            r0 = r29
            java.util.Vector[] r3 = r0.f5377t
            r3 = r3[r17]
            if (r3 != 0) goto L_0x0121
            r0 = r29
            java.util.Vector[] r3 = r0.f5377t
            java.util.Vector r5 = new java.util.Vector
            r5.<init>()
            r3[r17] = r5
        L_0x0105:
            r0 = r21
            r1 = r31
            r2 = r17
            jumio.nv.nfc.dv r3 = r0.mo47139a(r1, r2)
            r9 = r3
            jumio.nv.nfc.eg r9 = (jumio.p317nv.nfc.C5093eg) r9
            r0 = r22
            int[] r3 = r0.f5261k
            r3 = r3[r17]
            if (r3 != 0) goto L_0x012b
            r3 = r4
        L_0x011b:
            int r4 = r17 + 1
            r17 = r4
            r4 = r3
            goto L_0x00ee
        L_0x0121:
            r0 = r29
            java.util.Vector[] r3 = r0.f5377t
            r3 = r3[r17]
            r3.removeAllElements()
            goto L_0x0105
        L_0x012b:
            r0 = r29
            jumio.nv.nfc.ce[][][][] r3 = r0.f5370m
            r3 = r3[r32]
            r3 = r3[r31]
            r3 = r3[r33]
            r23 = r3[r17]
            r0 = r29
            jumio.nv.nfc.ce[][][][] r3 = r0.f5371n
            r3 = r3[r32]
            r3 = r3[r31]
            r3 = r3[r33]
            r24 = r3[r17]
            r0 = r22
            jumio.nv.nfc.bt[][][] r3 = r0.f5260j
            r3 = r3[r17]
            if (r3 != 0) goto L_0x01ff
            r3 = 0
            r11 = r3
        L_0x014d:
            r3 = 0
            r16 = r3
        L_0x0150:
            r0 = r16
            if (r0 >= r11) goto L_0x0508
            r0 = r22
            jumio.nv.nfc.bt[][][] r3 = r0.f5260j
            r3 = r3[r17]
            r3 = r3[r16]
            if (r3 != 0) goto L_0x0209
            r3 = 0
            r12 = r3
        L_0x0160:
            r3 = 0
            r15 = r3
            r13 = r4
        L_0x0163:
            if (r15 >= r12) goto L_0x04b3
            r0 = r22
            jumio.nv.nfc.bt[][][] r3 = r0.f5260j
            r3 = r3[r17]
            r3 = r3[r16]
            r3 = r3[r15]
            jumio.nv.nfc.cu r0 = r3.f5151a
            r25 = r0
            r0 = r25
            int r3 = r0.f5475a
            r0 = r25
            int r4 = r0.f5476b
            jumio.nv.nfc.cu r5 = r9.f5539e
            int r5 = r5.f5475a
            int r4 = r4 * r5
            int r3 = r3 + r4
            r3 = r34[r17]
            r0 = r25
            int r4 = r0.f5476b
            r3 = r3[r4]
            r0 = r25
            int r4 = r0.f5475a
            r14 = r3[r4]
            if (r14 == 0) goto L_0x0195
            int r3 = r14.f5300i     // Catch:{ EOFException -> 0x04fb }
            if (r3 != 0) goto L_0x0319
        L_0x0195:
            if (r14 != 0) goto L_0x0505
            r3 = r34[r17]     // Catch:{ EOFException -> 0x04fb }
            r0 = r25
            int r4 = r0.f5476b     // Catch:{ EOFException -> 0x04fb }
            r26 = r3[r4]     // Catch:{ EOFException -> 0x04fb }
            r0 = r25
            int r0 = r0.f5475a     // Catch:{ EOFException -> 0x04fb }
            r27 = r0
            jumio.nv.nfc.bz r3 = new jumio.nv.nfc.bz     // Catch:{ EOFException -> 0x04fb }
            r0 = r22
            jumio.nv.nfc.bt[][][] r4 = r0.f5260j     // Catch:{ EOFException -> 0x04fb }
            r4 = r4[r17]     // Catch:{ EOFException -> 0x04fb }
            r4 = r4[r16]     // Catch:{ EOFException -> 0x04fb }
            r4 = r4[r15]     // Catch:{ EOFException -> 0x04fb }
            int r4 = r4.f5152b     // Catch:{ EOFException -> 0x04fb }
            r0 = r22
            jumio.nv.nfc.bt[][][] r5 = r0.f5260j     // Catch:{ EOFException -> 0x04fb }
            r5 = r5[r17]     // Catch:{ EOFException -> 0x04fb }
            r5 = r5[r16]     // Catch:{ EOFException -> 0x04fb }
            r5 = r5[r15]     // Catch:{ EOFException -> 0x04fb }
            int r5 = r5.f5153c     // Catch:{ EOFException -> 0x04fb }
            r0 = r22
            jumio.nv.nfc.bt[][][] r6 = r0.f5260j     // Catch:{ EOFException -> 0x04fb }
            r6 = r6[r17]     // Catch:{ EOFException -> 0x04fb }
            r6 = r6[r16]     // Catch:{ EOFException -> 0x04fb }
            r6 = r6[r15]     // Catch:{ EOFException -> 0x04fb }
            int r6 = r6.f5154d     // Catch:{ EOFException -> 0x04fb }
            r0 = r22
            jumio.nv.nfc.bt[][][] r7 = r0.f5260j     // Catch:{ EOFException -> 0x04fb }
            r7 = r7[r17]     // Catch:{ EOFException -> 0x04fb }
            r7 = r7[r16]     // Catch:{ EOFException -> 0x04fb }
            r7 = r7[r15]     // Catch:{ EOFException -> 0x04fb }
            int r7 = r7.f5155e     // Catch:{ EOFException -> 0x04fb }
            r0 = r29
            int r8 = r0.f5372o     // Catch:{ EOFException -> 0x04fb }
            r3.<init>(r4, r5, r6, r7, r8)     // Catch:{ EOFException -> 0x04fb }
            r26[r27] = r3     // Catch:{ EOFException -> 0x04fb }
        L_0x01e0:
            int[] r4 = r3.f5302k     // Catch:{ EOFException -> 0x04ff }
            r0 = r29
            int r5 = r0.f5376s     // Catch:{ EOFException -> 0x04ff }
            r4[r30] = r5     // Catch:{ EOFException -> 0x04ff }
            int r4 = r30 + 1
            r0 = r23
            r1 = r16
            r2 = r18
            int r4 = r0.mo47072a(r1, r15, r4, r2)     // Catch:{ EOFException -> 0x04ff }
            r0 = r30
            if (r4 <= r0) goto L_0x0215
            r3 = r13
        L_0x01f9:
            int r4 = r15 + 1
            r15 = r4
            r13 = r3
            goto L_0x0163
        L_0x01ff:
            r0 = r22
            jumio.nv.nfc.bt[][][] r3 = r0.f5260j
            r3 = r3[r17]
            int r3 = r3.length
            r11 = r3
            goto L_0x014d
        L_0x0209:
            r0 = r22
            jumio.nv.nfc.bt[][][] r3 = r0.f5260j
            r3 = r3[r17]
            r3 = r3[r16]
            int r3 = r3.length
            r12 = r3
            goto L_0x0160
        L_0x0215:
            r5 = 1
            r4 = 1
        L_0x0217:
            if (r5 < r4) goto L_0x0226
            r0 = r24
            r1 = r16
            r2 = r18
            int r5 = r0.mo47072a(r1, r15, r4, r2)     // Catch:{ EOFException -> 0x04ff }
            int r4 = r4 + 1
            goto L_0x0217
        L_0x0226:
            int r4 = r4 + -2
            r3.f5296e = r4     // Catch:{ EOFException -> 0x04ff }
            r5 = 1
            r4 = 0
            r0 = r30
            r3.mo47026a(r0, r4)     // Catch:{ EOFException -> 0x04ff }
            r0 = r29
            int r4 = r0.f5378u     // Catch:{ EOFException -> 0x04ff }
            int r4 = r4 + 1
            r0 = r29
            r0.f5378u = r4     // Catch:{ EOFException -> 0x04ff }
            r0 = r29
            int r4 = r0.f5379v     // Catch:{ EOFException -> 0x04ff }
            r6 = -1
            if (r4 == r6) goto L_0x0502
            r0 = r29
            boolean r4 = r0.f5380w     // Catch:{ EOFException -> 0x04ff }
            if (r4 != 0) goto L_0x0502
            r0 = r29
            int r4 = r0.f5378u     // Catch:{ EOFException -> 0x04ff }
            r0 = r29
            int r6 = r0.f5379v     // Catch:{ EOFException -> 0x04ff }
            if (r4 != r6) goto L_0x0502
            r4 = 1
            r0 = r29
            r0.f5380w = r4     // Catch:{ EOFException -> 0x04ff }
            r0 = r20
            r1 = r29
            r1.f5381x = r0     // Catch:{ EOFException -> 0x04ff }
            r0 = r32
            r1 = r29
            r1.f5382y = r0     // Catch:{ EOFException -> 0x04ff }
            r0 = r17
            r1 = r29
            r1.f5383z = r0     // Catch:{ EOFException -> 0x04ff }
            r0 = r31
            r1 = r29
            r1.f5354A = r0     // Catch:{ EOFException -> 0x04ff }
            r0 = r25
            int r4 = r0.f5475a     // Catch:{ EOFException -> 0x04ff }
            r0 = r29
            r0.f5355B = r4     // Catch:{ EOFException -> 0x04ff }
            r0 = r25
            int r4 = r0.f5476b     // Catch:{ EOFException -> 0x04ff }
            r0 = r29
            r0.f5356C = r4     // Catch:{ EOFException -> 0x04ff }
            r8 = r3
        L_0x0280:
            int r3 = r18.mo47069a()     // Catch:{ EOFException -> 0x0303 }
            r4 = 1
            if (r3 != r4) goto L_0x02af
            r5 = 2
            int r3 = r18.mo47069a()     // Catch:{ EOFException -> 0x0303 }
            r4 = 1
            if (r3 != r4) goto L_0x02af
            r3 = 2
            r0 = r18
            int r3 = r0.mo47070a(r3)     // Catch:{ EOFException -> 0x0303 }
            int r5 = r3 + 3
            r4 = 3
            if (r3 != r4) goto L_0x02af
            r3 = 5
            r0 = r18
            int r3 = r0.mo47070a(r3)     // Catch:{ EOFException -> 0x0303 }
            int r5 = r5 + r3
            r4 = 31
            if (r3 != r4) goto L_0x02af
            r3 = 7
            r0 = r18
            int r3 = r0.mo47070a(r3)     // Catch:{ EOFException -> 0x0303 }
            int r5 = r5 + r3
        L_0x02af:
            r0 = r30
            r8.mo47026a(r0, r5)     // Catch:{ EOFException -> 0x0303 }
            int r7 = r13 + r5
            r0 = r29
            java.util.Vector[] r3 = r0.f5377t     // Catch:{ EOFException -> 0x0303 }
            r3 = r3[r17]     // Catch:{ EOFException -> 0x0303 }
            r0 = r22
            jumio.nv.nfc.bt[][][] r4 = r0.f5260j     // Catch:{ EOFException -> 0x0303 }
            r4 = r4[r17]     // Catch:{ EOFException -> 0x0303 }
            r4 = r4[r16]     // Catch:{ EOFException -> 0x0303 }
            r4 = r4[r15]     // Catch:{ EOFException -> 0x0303 }
            r3.addElement(r4)     // Catch:{ EOFException -> 0x0303 }
            r0 = r29
            jumio.nv.nfc.cg r3 = r0.f5361d     // Catch:{ EOFException -> 0x0303 }
            jumio.nv.nfc.br r3 = r3.f5410j     // Catch:{ EOFException -> 0x0303 }
            r0 = r20
            r1 = r32
            java.lang.Object r3 = r3.mo46970a(r0, r1)     // Catch:{ EOFException -> 0x0303 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ EOFException -> 0x0303 }
            int r13 = r3.intValue()     // Catch:{ EOFException -> 0x0303 }
            r3 = r13 & 4
            if (r3 == 0) goto L_0x032f
            r3 = r5
        L_0x02e2:
            int r4 = r18.mo47069a()     // Catch:{ EOFException -> 0x0303 }
            if (r4 == 0) goto L_0x035f
            r0 = r29
            int[][][][][] r4 = r0.f5369l     // Catch:{ EOFException -> 0x0303 }
            r4 = r4[r32]     // Catch:{ EOFException -> 0x0303 }
            r4 = r4[r31]     // Catch:{ EOFException -> 0x0303 }
            r4 = r4[r17]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r6 = r0.f5476b     // Catch:{ EOFException -> 0x0303 }
            r4 = r4[r6]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r6 = r0.f5475a     // Catch:{ EOFException -> 0x0303 }
            r14 = r4[r6]     // Catch:{ EOFException -> 0x0303 }
            int r14 = r14 + 1
            r4[r6] = r14     // Catch:{ EOFException -> 0x0303 }
            goto L_0x02e2
        L_0x0303:
            r3 = move-exception
            r3 = r8
        L_0x0305:
            if (r30 != 0) goto L_0x0495
            r3 = r34[r17]
            r0 = r25
            int r4 = r0.f5476b
            r3 = r3[r4]
            r0 = r25
            int r4 = r0.f5475a
            r5 = 0
            r3[r4] = r5
        L_0x0316:
            r3 = 1
            goto L_0x0016
        L_0x0319:
            int[] r3 = r14.f5302k     // Catch:{ EOFException -> 0x04fb }
            r0 = r29
            int r4 = r0.f5376s     // Catch:{ EOFException -> 0x04fb }
            r3[r30] = r4     // Catch:{ EOFException -> 0x04fb }
            int r3 = r18.mo47069a()     // Catch:{ EOFException -> 0x04fb }
            r4 = 1
            if (r3 == r4) goto L_0x032b
            r3 = r13
            goto L_0x01f9
        L_0x032b:
            r5 = 1
            r8 = r14
            goto L_0x0280
        L_0x032f:
            r3 = r13 & 1
            if (r3 == 0) goto L_0x035d
            int r3 = r8.f5300i     // Catch:{ EOFException -> 0x0303 }
            r4 = 10
            if (r3 > r4) goto L_0x033b
            r3 = 1
            goto L_0x02e2
        L_0x033b:
            r4 = 1
            int r3 = r8.f5300i     // Catch:{ EOFException -> 0x0303 }
            int r3 = r3 - r5
            r28 = r3
            r3 = r4
            r4 = r28
        L_0x0344:
            int r6 = r8.f5300i     // Catch:{ EOFException -> 0x0303 }
            int r6 = r6 + -1
            if (r4 >= r6) goto L_0x02e2
            r6 = 9
            if (r4 < r6) goto L_0x035a
            int r6 = r4 + 2
            int r6 = r6 % 3
            r14 = 1
            if (r6 == r14) goto L_0x0358
            r14 = 2
            if (r6 != r14) goto L_0x035a
        L_0x0358:
            int r3 = r3 + 1
        L_0x035a:
            int r4 = r4 + 1
            goto L_0x0344
        L_0x035d:
            r3 = 1
            goto L_0x02e2
        L_0x035f:
            r4 = 1
            if (r3 != r4) goto L_0x03b9
            r0 = r29
            int[][][][][] r3 = r0.f5369l     // Catch:{ EOFException -> 0x0303 }
            r3 = r3[r32]     // Catch:{ EOFException -> 0x0303 }
            r3 = r3[r31]     // Catch:{ EOFException -> 0x0303 }
            r3 = r3[r17]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r4 = r0.f5476b     // Catch:{ EOFException -> 0x0303 }
            r3 = r3[r4]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r4 = r0.f5475a     // Catch:{ EOFException -> 0x0303 }
            r3 = r3[r4]     // Catch:{ EOFException -> 0x0303 }
            int r4 = jumio.p317nv.nfc.C5078ds.m3575a(r5)     // Catch:{ EOFException -> 0x0303 }
            int r3 = r3 + r4
            r0 = r18
            int r3 = r0.mo47070a(r3)     // Catch:{ EOFException -> 0x0303 }
        L_0x0383:
            int[] r4 = r8.f5297f     // Catch:{ EOFException -> 0x0303 }
            r4[r30] = r3     // Catch:{ EOFException -> 0x0303 }
            r0 = r29
            boolean r3 = r0.f5357D     // Catch:{ EOFException -> 0x0303 }
            if (r3 == 0) goto L_0x0492
            r0 = r29
            int r3 = r0.f5379v     // Catch:{ EOFException -> 0x0303 }
            r4 = -1
            if (r3 != r4) goto L_0x0492
            r0 = r29
            jumio.nv.nfc.df r3 = r0.f5365h     // Catch:{ EOFException -> 0x0303 }
            int r3 = r3.mo47123e()     // Catch:{ EOFException -> 0x0303 }
            int r3 = r3 - r19
            r4 = r35[r20]     // Catch:{ EOFException -> 0x0303 }
            if (r3 <= r4) goto L_0x0492
            r3 = 0
            r35[r20] = r3     // Catch:{ EOFException -> 0x0303 }
            if (r30 != 0) goto L_0x0474
            r3 = r34[r17]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r4 = r0.f5476b     // Catch:{ EOFException -> 0x0303 }
            r3 = r3[r4]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r4 = r0.f5475a     // Catch:{ EOFException -> 0x0303 }
            r5 = 0
            r3[r4] = r5     // Catch:{ EOFException -> 0x0303 }
        L_0x03b6:
            r3 = 1
            goto L_0x0016
        L_0x03b9:
            int[][] r4 = r8.f5301j     // Catch:{ EOFException -> 0x0303 }
            int[] r3 = new int[r3]     // Catch:{ EOFException -> 0x0303 }
            r4[r30] = r3     // Catch:{ EOFException -> 0x0303 }
            r6 = 0
            r3 = r13 & 4
            if (r3 == 0) goto L_0x03fa
            int r3 = r8.f5300i     // Catch:{ EOFException -> 0x0303 }
            int r4 = r3 - r5
            r3 = 0
            r5 = r4
            r4 = r3
            r3 = r6
        L_0x03cc:
            int r6 = r8.f5300i     // Catch:{ EOFException -> 0x0303 }
            if (r5 >= r6) goto L_0x0383
            r0 = r29
            int[][][][][] r6 = r0.f5369l     // Catch:{ EOFException -> 0x0303 }
            r6 = r6[r32]     // Catch:{ EOFException -> 0x0303 }
            r6 = r6[r31]     // Catch:{ EOFException -> 0x0303 }
            r6 = r6[r17]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r13 = r0.f5476b     // Catch:{ EOFException -> 0x0303 }
            r6 = r6[r13]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r13 = r0.f5475a     // Catch:{ EOFException -> 0x0303 }
            r6 = r6[r13]     // Catch:{ EOFException -> 0x0303 }
            r0 = r18
            int r6 = r0.mo47070a(r6)     // Catch:{ EOFException -> 0x0303 }
            int[][] r13 = r8.f5301j     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r30]     // Catch:{ EOFException -> 0x0303 }
            r13[r4] = r6     // Catch:{ EOFException -> 0x0303 }
            int r6 = r6 + r3
            int r5 = r5 + 1
            int r3 = r4 + 1
            r4 = r3
            r3 = r6
            goto L_0x03cc
        L_0x03fa:
            int r3 = r8.f5300i     // Catch:{ EOFException -> 0x0303 }
            int r3 = r3 - r5
            int r4 = r3 + -1
            int r3 = r8.f5300i     // Catch:{ EOFException -> 0x0303 }
            int r5 = r3 - r5
            r3 = 0
        L_0x0404:
            int r13 = r8.f5300i     // Catch:{ EOFException -> 0x0303 }
            int r13 = r13 + -1
            if (r5 >= r13) goto L_0x0446
            r13 = 9
            if (r5 < r13) goto L_0x0414
            int r13 = r5 + 2
            int r13 = r13 % 3
            if (r13 != 0) goto L_0x0417
        L_0x0414:
            int r5 = r5 + 1
            goto L_0x0404
        L_0x0417:
            r0 = r29
            int[][][][][] r13 = r0.f5369l     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r32]     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r31]     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r17]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r14 = r0.f5476b     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r14]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r14 = r0.f5475a     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r14]     // Catch:{ EOFException -> 0x0303 }
            int r4 = r5 - r4
            int r4 = jumio.p317nv.nfc.C5078ds.m3575a(r4)     // Catch:{ EOFException -> 0x0303 }
            int r4 = r4 + r13
            r0 = r18
            int r4 = r0.mo47070a(r4)     // Catch:{ EOFException -> 0x0303 }
            int[][] r13 = r8.f5301j     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r30]     // Catch:{ EOFException -> 0x0303 }
            r13[r3] = r4     // Catch:{ EOFException -> 0x0303 }
            int r4 = r4 + r6
            int r3 = r3 + 1
            r6 = r4
            r4 = r5
            goto L_0x0414
        L_0x0446:
            r0 = r29
            int[][][][][] r13 = r0.f5369l     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r32]     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r31]     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r17]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r14 = r0.f5476b     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r14]     // Catch:{ EOFException -> 0x0303 }
            r0 = r25
            int r14 = r0.f5475a     // Catch:{ EOFException -> 0x0303 }
            r13 = r13[r14]     // Catch:{ EOFException -> 0x0303 }
            int r4 = r5 - r4
            int r4 = jumio.p317nv.nfc.C5078ds.m3575a(r4)     // Catch:{ EOFException -> 0x0303 }
            int r4 = r4 + r13
            r0 = r18
            int r5 = r0.mo47070a(r4)     // Catch:{ EOFException -> 0x0303 }
            int r4 = r6 + r5
            int[][] r6 = r8.f5301j     // Catch:{ EOFException -> 0x0303 }
            r6 = r6[r30]     // Catch:{ EOFException -> 0x0303 }
            r6[r3] = r5     // Catch:{ EOFException -> 0x0303 }
            r3 = r4
            goto L_0x0383
        L_0x0474:
            int[] r3 = r8.f5298g     // Catch:{ EOFException -> 0x0303 }
            int[] r4 = r8.f5297f     // Catch:{ EOFException -> 0x0303 }
            r5 = 0
            r4[r30] = r5     // Catch:{ EOFException -> 0x0303 }
            r3[r30] = r5     // Catch:{ EOFException -> 0x0303 }
            int r3 = r8.f5300i     // Catch:{ EOFException -> 0x0303 }
            int[] r4 = r8.f5299h     // Catch:{ EOFException -> 0x0303 }
            r4 = r4[r30]     // Catch:{ EOFException -> 0x0303 }
            int r3 = r3 - r4
            r8.f5300i = r3     // Catch:{ EOFException -> 0x0303 }
            int[] r3 = r8.f5299h     // Catch:{ EOFException -> 0x0303 }
            r4 = 0
            r3[r30] = r4     // Catch:{ EOFException -> 0x0303 }
            int[] r3 = r8.f5302k     // Catch:{ EOFException -> 0x0303 }
            r4 = -1
            r3[r30] = r4     // Catch:{ EOFException -> 0x0303 }
            goto L_0x03b6
        L_0x0492:
            r3 = r7
            goto L_0x01f9
        L_0x0495:
            int[] r4 = r3.f5298g
            int[] r5 = r3.f5297f
            r6 = 0
            r5[r30] = r6
            r4[r30] = r6
            int r4 = r3.f5300i
            int[] r5 = r3.f5299h
            r5 = r5[r30]
            int r4 = r4 - r5
            r3.f5300i = r4
            int[] r4 = r3.f5299h
            r5 = 0
            r4[r30] = r5
            int[] r3 = r3.f5302k
            r4 = -1
            r3[r30] = r4
            goto L_0x0316
        L_0x04b3:
            int r3 = r16 + 1
            r16 = r3
            r4 = r13
            goto L_0x0150
        L_0x04ba:
            r0 = r29
            boolean r3 = r0.f5375r
            if (r3 == 0) goto L_0x04c7
            r0 = r29
            r1 = r18
            r0.mo47062a(r1)
        L_0x04c7:
            r0 = r29
            int r3 = r0.f5376s
            int r3 = r3 + 1
            r0 = r29
            r0.f5376s = r3
            r0 = r29
            boolean r3 = r0.f5357D
            if (r3 == 0) goto L_0x04f8
            r0 = r29
            int r3 = r0.f5379v
            r4 = -1
            if (r3 != r4) goto L_0x04f8
            r0 = r29
            jumio.nv.nfc.df r3 = r0.f5365h
            int r3 = r3.mo47123e()
            int r3 = r3 - r19
            r4 = r35[r20]
            if (r3 <= r4) goto L_0x04f2
            r3 = 0
            r35[r20] = r3
            r3 = 1
            goto L_0x0016
        L_0x04f2:
            r4 = r35[r20]
            int r3 = r4 - r3
            r35[r20] = r3
        L_0x04f8:
            r3 = 0
            goto L_0x0016
        L_0x04fb:
            r3 = move-exception
            r3 = r14
            goto L_0x0305
        L_0x04ff:
            r4 = move-exception
            goto L_0x0305
        L_0x0502:
            r8 = r3
            goto L_0x0280
        L_0x0505:
            r3 = r14
            goto L_0x01e0
        L_0x0508:
            r3 = r4
            goto L_0x011b
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C5035cc.mo47063a(int, int, int, int, jumio.nv.nfc.bz[][][], int[]):boolean");
    }

    /* renamed from: b */
    public boolean mo47067b(int i, int i2, int i3, int i4, C5031bz[][][] bzVarArr, int[] iArr) throws IOException {
        int e = this.f5365h.mo47123e();
        int d = this.f5358a.mo47013d();
        int i5 = i2 == 0 ? 0 : 1;
        int i6 = i2 == 0 ? 1 : 4;
        boolean z = false;
        for (int i7 = i5; i7 < i6; i7++) {
            if (i4 < this.f5368k[i3][i2].length) {
                z = true;
            }
        }
        if (!z) {
            return false;
        }
        int i8 = i5;
        boolean z2 = false;
        int i9 = e;
        while (i8 < i6) {
            int i10 = 0;
            int i11 = i9;
            boolean z3 = z2;
            while (true) {
                int i12 = i10;
                if (i12 >= this.f5377t[i8].size()) {
                    break;
                }
                C5053cu cuVar = ((C5015bt) this.f5377t[i8].elementAt(i12)).f5151a;
                C5031bz bzVar = bzVarArr[i8][cuVar.f5476b][cuVar.f5475a];
                bzVar.f5298g[i] = i11;
                i11 += bzVar.f5297f[i];
                try {
                    this.f5365h.mo47121a(i11);
                    if (this.f5357D) {
                        if (z3 || bzVar.f5297f[i] > iArr[d]) {
                            if (i == 0) {
                                bzVarArr[i8][cuVar.f5476b][cuVar.f5475a] = null;
                            } else {
                                int[] iArr2 = bzVar.f5298g;
                                bzVar.f5297f[i] = 0;
                                iArr2[i] = 0;
                                bzVar.f5300i -= bzVar.f5299h[i];
                                bzVar.f5299h[i] = 0;
                                bzVar.f5302k[i] = -1;
                            }
                            z3 = true;
                        }
                        if (!z3) {
                            iArr[d] = iArr[d] - bzVar.f5297f[i];
                        }
                    }
                    if (this.f5380w && i2 == this.f5354A && i8 == this.f5383z && cuVar.f5475a == this.f5355B && cuVar.f5476b == this.f5356C && d == this.f5381x && i3 == this.f5382y) {
                        bzVarArr[i8][cuVar.f5476b][cuVar.f5475a] = null;
                        z3 = true;
                    }
                    i10 = i12 + 1;
                } catch (EOFException e2) {
                    if (i == 0) {
                        bzVarArr[i8][cuVar.f5476b][cuVar.f5475a] = null;
                    } else {
                        int[] iArr3 = bzVar.f5298g;
                        bzVar.f5297f[i] = 0;
                        iArr3[i] = 0;
                        bzVar.f5300i -= bzVar.f5299h[i];
                        bzVar.f5299h[i] = 0;
                        bzVar.f5302k[i] = -1;
                    }
                    throw new EOFException();
                }
            }
            i8++;
            z2 = z3;
            i9 = i11;
        }
        this.f5365h.mo47121a(i9);
        if (z2) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final int mo47061a(int i, int i2, int i3) {
        return this.f5361d.f5414n.mo47078a(i, i2, i3);
    }

    /* renamed from: b */
    public final int mo47066b(int i, int i2, int i3) {
        return this.f5361d.f5414n.mo47079b(i, i2, i3);
    }

    /* renamed from: a */
    public boolean mo47064a(int[] iArr, int i, int i2, int i3) throws IOException {
        int i4;
        int i5;
        byte[] bArr = new byte[6];
        int d = this.f5358a.mo47013d();
        if (i3 == 0) {
            i4 = 0;
        } else {
            i4 = 1;
        }
        if (i3 == 0) {
            i5 = 1;
        } else {
            i5 = 4;
        }
        boolean z = false;
        for (int i6 = i4; i6 < i5; i6++) {
            if (i < this.f5368k[i2][i3].length) {
                z = true;
            }
        }
        if (!z) {
            return false;
        }
        if (!this.f5374q) {
            return false;
        }
        int e = this.f5365h.mo47123e();
        if (((short) ((this.f5365h.mo47125g() << 8) | this.f5365h.mo47125g())) != -111) {
            this.f5365h.mo47121a(e);
            return false;
        }
        this.f5365h.mo47121a(e);
        if (iArr[d] < 6) {
            return true;
        }
        iArr[d] = iArr[d] - 6;
        this.f5365h.mo47122a(bArr, 0, 6);
        if (((bArr[0] << 8) | bArr[1]) != -111) {
            throw new Error("Corrupted Bitstream: Could not parse SOP marker !");
        } else if ((((bArr[2] & 255) << 8) | (bArr[3] & 255)) != 4) {
            throw new Error("Corrupted Bitstream: Corrupted SOP marker !");
        } else {
            byte b = ((bArr[4] & 255) << 8) | (bArr[5] & 255);
            if (!this.f5359b && b != this.f5376s) {
                throw new Error("Corrupted Bitstream: SOP marker out of sequence !");
            } else if (!this.f5359b || b == this.f5376s - 1) {
                return false;
            } else {
                throw new Error("Corrupted Bitstream: SOP marker out of sequence !");
            }
        }
    }

    /* renamed from: a */
    public void mo47062a(C5036cd cdVar) throws IOException {
        byte[] bArr = new byte[2];
        if (cdVar.f5386c) {
            cdVar.f5385b.read(bArr, 0, 2);
        } else {
            cdVar.f5384a.mo47122a(bArr, 0, 2);
        }
        if ((bArr[1] | (bArr[0] << 8)) != -110) {
            throw new Error("Corrupted Bitstream: Could not parse EPH marker ! ");
        }
    }

    /* renamed from: c */
    public C5029bx mo47068c(int i, int i2, int i3) {
        return this.f5368k[i][i2][i3];
    }
}

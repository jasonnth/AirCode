package jumio.p317nv.nfc;

import java.io.IOException;

/* renamed from: jumio.nv.nfc.by */
/* compiled from: BitstreamReaderAgent */
public abstract class C5030by implements C5045cm {

    /* renamed from: D */
    private static final String[][] f5262D = null;

    /* renamed from: A */
    protected int f5263A;

    /* renamed from: B */
    protected int f5264B;

    /* renamed from: C */
    protected float f5265C;

    /* renamed from: a */
    protected C5039cg f5266a;

    /* renamed from: b */
    protected boolean[] f5267b = null;

    /* renamed from: c */
    protected int[] f5268c = null;

    /* renamed from: d */
    protected C5073dn[] f5269d = null;

    /* renamed from: e */
    protected int[] f5270e;

    /* renamed from: f */
    protected final int f5271f;

    /* renamed from: g */
    protected int f5272g;

    /* renamed from: h */
    protected C5093eg[] f5273h;

    /* renamed from: i */
    protected final int f5274i;

    /* renamed from: j */
    protected final int f5275j;

    /* renamed from: k */
    protected final int f5276k;

    /* renamed from: l */
    protected final int f5277l;

    /* renamed from: m */
    protected final int f5278m;

    /* renamed from: n */
    protected final int f5279n;

    /* renamed from: o */
    protected final int[] f5280o;

    /* renamed from: p */
    protected final int[] f5281p;

    /* renamed from: q */
    protected final int[] f5282q;

    /* renamed from: r */
    protected final int[] f5283r;

    /* renamed from: s */
    protected final int f5284s;

    /* renamed from: t */
    protected final int f5285t;

    /* renamed from: u */
    protected final int f5286u;

    /* renamed from: v */
    protected final int f5287v;

    /* renamed from: w */
    protected final int f5288w;

    /* renamed from: x */
    protected int f5289x;

    /* renamed from: y */
    protected int f5290y;

    /* renamed from: z */
    protected final C5034cb f5291z;

    protected C5030by(C5034cb cbVar, C5039cg cgVar) {
        this.f5266a = cgVar;
        this.f5291z = cbVar;
        this.f5271f = cbVar.mo47053i();
        this.f5280o = new int[this.f5271f];
        this.f5281p = new int[this.f5271f];
        this.f5282q = new int[this.f5271f];
        this.f5283r = new int[this.f5271f];
        this.f5274i = cbVar.mo47042c();
        this.f5275j = cbVar.mo47045d();
        this.f5276k = cbVar.mo47048e();
        this.f5277l = cbVar.mo47050f();
        C5053cu a = cbVar.mo47033a((C5053cu) null);
        this.f5278m = a.f5475a;
        this.f5279n = a.f5476b;
        this.f5284s = cbVar.mo47051g();
        this.f5285t = cbVar.mo47052h();
        this.f5286u = ((((this.f5276k + this.f5274i) - this.f5278m) + this.f5284s) - 1) / this.f5284s;
        this.f5287v = ((((this.f5277l + this.f5275j) - this.f5279n) + this.f5285t) - 1) / this.f5285t;
        this.f5288w = this.f5286u * this.f5287v;
    }

    /* renamed from: a */
    public final int mo46998a() {
        return this.f5291z.mo47054j();
    }

    /* renamed from: b */
    public int mo47004b() {
        return this.f5291z.mo47055k();
    }

    /* renamed from: c */
    public final int mo47009c() {
        return this.f5271f;
    }

    /* renamed from: a */
    public final int mo46999a(int i) {
        return this.f5291z.mo47040b(i);
    }

    /* renamed from: b */
    public int mo47005b(int i) {
        return this.f5291z.mo47043c(i);
    }

    /* renamed from: c */
    public int mo47010c(int i) {
        int a = this.f5266a.f5407g.mo46967a();
        if (i > a) {
            throw new IllegalArgumentException("Requested resolution level is not available for, at least, one tile-component");
        }
        int i2 = a - i;
        return ((((this.f5276k + this.f5274i) + (1 << i2)) - 1) / (1 << i2)) - (((this.f5276k + (1 << i2)) - 1) / (1 << i2));
    }

    /* renamed from: d */
    public int mo47014d(int i) {
        int a = this.f5266a.f5407g.mo46967a();
        if (i > a) {
            throw new IllegalArgumentException("Requested resolution level is not available for, at least, one tile-component");
        }
        int i2 = a - i;
        return ((((this.f5277l + this.f5275j) + (1 << i2)) - 1) / (1 << i2)) - (((this.f5277l + (1 << i2)) - 1) / (1 << i2));
    }

    /* renamed from: e */
    public int mo47018e(int i) {
        int a = this.f5266a.f5407g.mo46967a();
        if (i > a) {
            throw new IllegalArgumentException("Requested resolution level is not available for, at least, one tile-component");
        }
        int i2 = a - i;
        return ((this.f5276k + (1 << i2)) - 1) / (1 << i2);
    }

    /* renamed from: f */
    public int mo47020f(int i) {
        int a = this.f5266a.f5407g.mo46967a();
        if (i > a) {
            throw new IllegalArgumentException("Requested resolution level is not available for, at least, one tile-component");
        }
        int i2 = a - i;
        return ((this.f5277l + (1 << i2)) - 1) / (1 << i2);
    }

    /* renamed from: a */
    public final int mo47001a(int i, int i2, int i3) {
        if (i != mo47013d()) {
            throw new Error("Asking the tile-component width of a tile different  from the current one.");
        }
        int i4 = this.f5270e[i2] - i3;
        return (((((((this.f5289x < this.f5286u + -1 ? this.f5278m + ((this.f5289x + 1) * this.f5284s) : this.f5276k + this.f5274i) + this.f5291z.mo47040b(i2)) - 1) / this.f5291z.mo47040b(i2)) + (1 << i4)) - 1) / (1 << i4)) - (((this.f5282q[i2] + (1 << i4)) - 1) / (1 << i4));
    }

    /* renamed from: b */
    public final int mo47007b(int i, int i2, int i3) {
        if (i != mo47013d()) {
            throw new Error("Asking the tile-component width of a tile different  from the current one.");
        }
        int i4 = this.f5270e[i2] - i3;
        return (((((((this.f5290y < this.f5287v + -1 ? this.f5279n + ((this.f5290y + 1) * this.f5285t) : this.f5277l + this.f5275j) + this.f5291z.mo47043c(i2)) - 1) / this.f5291z.mo47043c(i2)) + (1 << i4)) - 1) / (1 << i4)) - (((this.f5283r[i2] + (1 << i4)) - 1) / (1 << i4));
    }

    /* renamed from: a */
    public final int mo47000a(int i, int i2) {
        int a = this.f5266a.f5407g.mo46968a(i) - i2;
        return (((((((this.f5276k + this.f5274i) + this.f5291z.mo47040b(i)) - 1) / this.f5291z.mo47040b(i)) + (1 << a)) - 1) / (1 << a)) - ((((((this.f5276k + this.f5291z.mo47040b(i)) - 1) / this.f5291z.mo47040b(i)) + (1 << a)) - 1) / (1 << a));
    }

    /* renamed from: b */
    public final int mo47006b(int i, int i2) {
        int a = this.f5266a.f5407g.mo46968a(i) - i2;
        return (((((((this.f5277l + this.f5275j) + this.f5291z.mo47043c(i)) - 1) / this.f5291z.mo47043c(i)) + (1 << a)) - 1) / (1 << a)) - ((((((this.f5277l + this.f5291z.mo47043c(i)) - 1) / this.f5291z.mo47043c(i)) + (1 << a)) - 1) / (1 << a));
    }

    /* renamed from: a */
    public final C5053cu mo47002a(C5053cu cuVar) {
        if (cuVar == null) {
            return new C5053cu(this.f5289x, this.f5290y);
        }
        cuVar.f5475a = this.f5289x;
        cuVar.f5476b = this.f5290y;
        return cuVar;
    }

    /* renamed from: d */
    public final int mo47013d() {
        return (this.f5290y * this.f5286u) + this.f5289x;
    }

    /* renamed from: c */
    public final int mo47011c(int i, int i2) {
        int i3 = this.f5270e[i] - i2;
        if (i3 >= 0) {
            return (int) Math.ceil(((double) ((int) Math.ceil(((double) Math.max(this.f5278m + (this.f5289x * this.f5284s), this.f5276k)) / ((double) mo46999a(i))))) / ((double) (1 << i3)));
        }
        throw new IllegalArgumentException("Requested resolution level is not available for, at least, one component in tile: " + this.f5289x + "x" + this.f5290y);
    }

    /* renamed from: d */
    public final int mo47015d(int i, int i2) {
        int i3 = this.f5270e[i] - i2;
        if (i3 >= 0) {
            return (int) Math.ceil(((double) ((int) Math.ceil(((double) Math.max(this.f5279n + (this.f5290y * this.f5285t), this.f5277l)) / ((double) mo47005b(i))))) / ((double) (1 << i3)));
        }
        throw new IllegalArgumentException("Requested resolution level is not available for, at least, one component in tile: " + this.f5289x + "x" + this.f5290y);
    }

    /* renamed from: b */
    public final C5053cu mo47008b(C5053cu cuVar) {
        if (cuVar == null) {
            return new C5053cu(this.f5286u, this.f5287v);
        }
        cuVar.f5475a = this.f5286u;
        cuVar.f5476b = this.f5287v;
        return cuVar;
    }

    /* renamed from: e */
    public final int mo47017e() {
        return this.f5286u * this.f5287v;
    }

    /* renamed from: e */
    public final C5093eg mo47019e(int i, int i2) {
        if (i != mo47013d()) {
            throw new IllegalArgumentException("Can not request subband tree of a different tile than the current one");
        } else if (i2 >= 0 && i2 < this.f5271f) {
            return this.f5273h[i2];
        } else {
            throw new IllegalArgumentException("Component index out of range");
        }
    }

    /* renamed from: a */
    public static C5030by m3322a(C5065df dfVar, C5034cb cbVar, C5079dt dtVar, C5039cg cgVar, boolean z, C5018bw bwVar) throws IOException {
        dtVar.mo47133a('B', C5079dt.m3577a(m3323f()));
        return new C5033ca(cbVar, dfVar, cgVar, dtVar, z, bwVar);
    }

    /* renamed from: f */
    public static String[][] m3323f() {
        return f5262D;
    }

    /* renamed from: c */
    public final int mo47012c(int i, int i2, int i3) {
        return this.f5266a.f5414n.mo47078a(i, i2, i3);
    }

    /* renamed from: d */
    public final int mo47016d(int i, int i2, int i3) {
        return this.f5266a.f5414n.mo47079b(i, i2, i3);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo47003a(int i, C5093eg egVar) {
        int d = mo47013d();
        int i2 = egVar.f5538d;
        int a = this.f5266a.f5417q.mo47075a(3, d, i);
        int b = this.f5266a.f5417q.mo47076b(3, d, i);
        if (!egVar.f5535a) {
            if (this.f5291z.mo47056l()) {
                int a2 = C5078ds.m3575a(mo47012c(d, i, i2));
                int a3 = C5078ds.m3575a(mo47016d(d, i, i2));
                int a4 = C5078ds.m3575a(a);
                int a5 = C5078ds.m3575a(b);
                switch (egVar.f5538d) {
                    case 0:
                        egVar.f5548n = a4 < a2 ? 1 << a4 : 1 << a2;
                        egVar.f5549o = a5 < a3 ? 1 << a5 : 1 << a3;
                        break;
                    default:
                        egVar.f5548n = a4 < a2 + -1 ? 1 << a4 : 1 << (a2 - 1);
                        egVar.f5549o = a5 < a3 + -1 ? 1 << a5 : 1 << (a3 - 1);
                        break;
                }
            } else {
                egVar.f5548n = a;
                egVar.f5549o = b;
            }
            if (egVar.f5539e == null) {
                egVar.f5539e = new C5053cu();
            }
            if (egVar.f5546l == 0 || egVar.f5547m == 0) {
                egVar.f5539e.f5475a = 0;
                egVar.f5539e.f5476b = 0;
            } else {
                int a6 = mo46998a();
                int b2 = mo47004b();
                switch (egVar.f5541g) {
                    case 0:
                        break;
                    case 1:
                        a6 = 0;
                        break;
                    case 2:
                        b2 = 0;
                        break;
                    case 3:
                        b2 = 0;
                        a6 = 0;
                        break;
                    default:
                        throw new Error("Internal JJ2000 error");
                }
                if (egVar.f5542h - a6 < 0 || egVar.f5543i - b2 < 0) {
                    throw new IllegalArgumentException("Invalid code-blocks partition origin or image offset in the reference grid.");
                }
                int i3 = (egVar.f5542h - a6) + egVar.f5548n;
                egVar.f5539e.f5475a = (((egVar.f5546l + i3) - 1) / egVar.f5548n) - ((i3 / egVar.f5548n) - 1);
                int i4 = (egVar.f5543i - b2) + egVar.f5549o;
                egVar.f5539e.f5476b = (((egVar.f5547m + i4) - 1) / egVar.f5549o) - ((i4 / egVar.f5549o) - 1);
            }
            if (this.f5267b[i]) {
                egVar.f5566r = (this.f5268c[i] + (this.f5269d[i].f5523a[0][0] - (this.f5270e[i] - egVar.f5537c))) - 1;
            } else {
                egVar.f5566r = (this.f5268c[i] + this.f5269d[i].f5523a[egVar.f5538d][egVar.f5541g]) - 1;
            }
        } else {
            mo47003a(i, (C5093eg) egVar.mo47141b());
            mo47003a(i, (C5093eg) egVar.mo47142c());
            mo47003a(i, (C5093eg) egVar.mo47143d());
            mo47003a(i, (C5093eg) egVar.mo47144e());
        }
    }

    /* renamed from: g */
    public int mo47021g() {
        return this.f5272g;
    }

    /* renamed from: h */
    public int mo47022h() {
        return this.f5291z.mo47033a((C5053cu) null).f5475a;
    }

    /* renamed from: i */
    public int mo47023i() {
        return this.f5291z.mo47033a((C5053cu) null).f5476b;
    }

    /* renamed from: j */
    public int mo47024j() {
        return this.f5291z.mo47051g();
    }

    /* renamed from: k */
    public int mo47025k() {
        return this.f5291z.mo47052h();
    }
}

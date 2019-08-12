package jumio.p317nv.nfc;

import com.facebook.common.util.UriUtil;
import java.io.EOFException;
import java.io.IOException;
import java.util.Vector;
import jumio.p317nv.nfc.C5018bw.C5028j;

/* renamed from: jumio.nv.nfc.ca */
/* compiled from: FileBitstreamReaderAgent */
public class C5033ca extends C5030by {

    /* renamed from: D */
    public C5035cc f5303D;

    /* renamed from: E */
    private boolean f5304E = true;

    /* renamed from: F */
    private C5079dt f5305F;

    /* renamed from: G */
    private C5065df f5306G;

    /* renamed from: H */
    private int[][] f5307H;

    /* renamed from: I */
    private int[] f5308I;

    /* renamed from: J */
    private int[] f5309J;

    /* renamed from: K */
    private int[][] f5310K;

    /* renamed from: L */
    private int[] f5311L;

    /* renamed from: M */
    private int[] f5312M;

    /* renamed from: N */
    private int f5313N;

    /* renamed from: O */
    private double f5314O;

    /* renamed from: P */
    private int f5315P;

    /* renamed from: Q */
    private int f5316Q = 0;

    /* renamed from: R */
    private int[][] f5317R;

    /* renamed from: S */
    private Vector f5318S;

    /* renamed from: T */
    private boolean f5319T;

    /* renamed from: U */
    private int f5320U;

    /* renamed from: V */
    private int[] f5321V;

    /* renamed from: W */
    private int f5322W = 0;

    /* renamed from: X */
    private int[] f5323X;

    /* renamed from: Y */
    private int f5324Y;

    /* renamed from: Z */
    private int[][] f5325Z;

    /* renamed from: aa */
    private boolean f5326aa = false;

    /* renamed from: ab */
    private C5018bw f5327ab;

    /* renamed from: ac */
    private C5031bz[][][][][] f5328ac;

    /* renamed from: ad */
    private int f5329ad;

    /* renamed from: ae */
    private boolean f5330ae = false;

    public C5033ca(C5034cb cbVar, C5065df dfVar, C5039cg cgVar, C5079dt dtVar, boolean z, C5018bw bwVar) throws IOException {
        boolean z2;
        super(cbVar, cgVar);
        this.f5305F = dtVar;
        this.f5327ab = bwVar;
        this.f5330ae = dtVar.mo47135b("poc_quit");
        dtVar.mo47135b("parsing");
        try {
            this.f5265C = dtVar.mo47137d("rate");
            if (this.f5265C == -1.0f) {
                this.f5265C = Float.MAX_VALUE;
            }
            try {
                this.f5263A = dtVar.mo47136c("nbytes");
                if (((float) this.f5263A) != dtVar.mo47132a().mo47137d("nbytes")) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    this.f5265C = ((((float) this.f5263A) * 8.0f) / ((float) cbVar.mo47039b())) / ((float) cbVar.mo47029a());
                } else {
                    this.f5263A = ((int) ((this.f5265C * ((float) cbVar.mo47039b())) * ((float) cbVar.mo47029a()))) / 8;
                }
                this.f5319T = !dtVar.mo47135b("parsing");
                try {
                    int c = dtVar.mo47136c("ncb_quit");
                    if (c == -1 || this.f5319T) {
                        try {
                            this.f5329ad = dtVar.mo47136c("l_quit");
                            this.f5306G = dfVar;
                            this.f5303D = new C5035cc(cgVar, cbVar, dfVar, this, this.f5319T, c);
                            this.f5323X = new int[this.f5288w];
                            this.f5311L = new int[this.f5288w];
                            this.f5310K = new int[this.f5288w][];
                            this.f5325Z = new int[this.f5288w][];
                            this.f5307H = new int[this.f5288w][];
                            this.f5321V = new int[this.f5288w];
                            this.f5312M = new int[this.f5288w];
                            this.f5317R = new int[this.f5288w][];
                            this.f5308I = new int[this.f5288w];
                            this.f5309J = new int[this.f5288w];
                            cbVar.f5332a = new int[this.f5288w];
                            int i = cbVar.f5334c;
                            this.f5315P = this.f5306G.mo47123e() - i;
                            this.f5316Q = this.f5315P;
                            if (c == -1) {
                                this.f5264B = this.f5315P;
                            } else {
                                this.f5264B = 0;
                            }
                            if (this.f5264B > this.f5263A) {
                                throw new Error("Requested bitrate is too small.");
                            }
                            boolean z3 = false;
                            this.f5314O = 0.0d;
                            this.f5320U = this.f5288w;
                            int i2 = 0;
                            int i3 = this.f5288w;
                            int i4 = 0;
                            int i5 = 0;
                            while (true) {
                                try {
                                    if (this.f5320U == 0) {
                                        break;
                                    }
                                    int e = this.f5306G.mo47123e();
                                    i4 = m3361m();
                                    if (!this.f5326aa) {
                                        i2 = this.f5321V[i4];
                                        if (this.f5304E) {
                                            this.f5310K[i4][i2] = (this.f5306G.mo47124f() - 2) - e;
                                        }
                                        int e2 = this.f5306G.mo47123e();
                                        if (this.f5319T && c == -1 && e2 - i > this.f5263A) {
                                            this.f5307H[i4][i2] = this.f5306G.mo47124f();
                                            z3 = true;
                                            break;
                                        }
                                        this.f5307H[i4][i2] = e2;
                                        this.f5317R[i4][i2] = e2 - e;
                                        int[] iArr = this.f5311L;
                                        iArr[i4] = iArr[i4] + this.f5310K[i4][i2];
                                        int[] iArr2 = this.f5312M;
                                        iArr2[i4] = iArr2[i4] + this.f5317R[i4][i2];
                                        this.f5314O += (double) this.f5310K[i4][i2];
                                        if (!this.f5319T) {
                                            if (this.f5264B + this.f5317R[i4][i2] > this.f5263A) {
                                                break;
                                            }
                                            this.f5264B += this.f5317R[i4][i2];
                                            this.f5316Q += this.f5317R[i4][i2];
                                        } else if (this.f5264B + this.f5310K[i4][i2] > this.f5263A) {
                                            this.f5264B += this.f5317R[i4][i2];
                                            this.f5316Q += this.f5317R[i4][i2];
                                            z3 = true;
                                            int[] iArr3 = this.f5308I;
                                            iArr3[i4] = iArr3[i4] + (this.f5263A - this.f5264B);
                                            break;
                                        } else {
                                            this.f5264B += this.f5317R[i4][i2];
                                            this.f5316Q += this.f5317R[i4][i2];
                                            int[] iArr4 = this.f5308I;
                                            iArr4[i4] = iArr4[i4] + (this.f5310K[i4][i2] - this.f5317R[i4][i2]);
                                        }
                                        if (i5 == 0) {
                                            this.f5313N = this.f5317R[i4][i2];
                                        }
                                        int[] iArr5 = this.f5321V;
                                        iArr5[i4] = iArr5[i4] + 1;
                                        this.f5306G.mo47121a(e + this.f5310K[i4][i2]);
                                        this.f5320U--;
                                        i3--;
                                        i5++;
                                        if (this.f5304E) {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                } catch (EOFException e3) {
                                    this.f5307H[i4][i2] = this.f5306G.mo47124f();
                                    throw e3;
                                } catch (EOFException e4) {
                                    int f = this.f5306G.mo47124f();
                                    if (f < this.f5263A) {
                                        this.f5263A = f;
                                        this.f5265C = ((((float) this.f5263A) * 8.0f) / ((float) cbVar.mo47039b())) / ((float) cbVar.mo47029a());
                                    }
                                    if (!this.f5319T) {
                                        m3360l();
                                    }
                                    if (dtVar.mo47131a(UriUtil.LOCAL_RESOURCE_SCHEME) == null) {
                                        this.f5272g = cgVar.f5407g.mo46967a();
                                    } else {
                                        try {
                                            this.f5272g = dtVar.mo47136c(UriUtil.LOCAL_RESOURCE_SCHEME);
                                            if (this.f5272g < 0) {
                                                throw new IllegalArgumentException("Specified negative resolution level index: " + this.f5272g);
                                            }
                                        } catch (NumberFormatException e5) {
                                            throw new IllegalArgumentException("Invalid resolution level index ('-res' option) " + dtVar.mo47131a(UriUtil.LOCAL_RESOURCE_SCHEME));
                                        }
                                    }
                                    int a = cgVar.f5407g.mo46967a();
                                    if (this.f5272g > a) {
                                        this.f5272g = a;
                                    }
                                    for (int i6 = 0; i6 < this.f5288w; i6++) {
                                        this.f5309J[i6] = this.f5308I[i6];
                                    }
                                    return;
                                }
                            }
                            this.f5320U = 0;
                            if (dtVar.mo47131a(UriUtil.LOCAL_RESOURCE_SCHEME) == null) {
                                this.f5272g = cgVar.f5407g.mo46967a();
                            } else {
                                try {
                                    this.f5272g = dtVar.mo47136c(UriUtil.LOCAL_RESOURCE_SCHEME);
                                    if (this.f5272g < 0) {
                                        throw new IllegalArgumentException("Specified negative resolution level index: " + this.f5272g);
                                    }
                                } catch (NumberFormatException e6) {
                                    throw new IllegalArgumentException("Invalid resolution level index ('-res' option) " + dtVar.mo47131a(UriUtil.LOCAL_RESOURCE_SCHEME));
                                }
                            }
                            int a2 = cgVar.f5407g.mo46967a();
                            if (this.f5272g > a2) {
                                this.f5272g = a2;
                            }
                            if (!this.f5326aa && !this.f5304E && !z3) {
                                try {
                                    if (this.f5304E || this.f5306G.mo47117a() != -39) {
                                    }
                                } catch (EOFException e7) {
                                }
                            }
                            if (!this.f5319T) {
                                m3360l();
                            } else if (this.f5306G.mo47123e() >= this.f5263A) {
                                this.f5264B += 2;
                            }
                            for (int i7 = 0; i7 < this.f5288w; i7++) {
                                this.f5309J[i7] = this.f5308I[i7];
                            }
                        } catch (NumberFormatException e8) {
                            throw new Error("Invalid value in 'l_quit' option: " + dtVar.mo47131a("l_quit"));
                        } catch (IllegalArgumentException e9) {
                            throw new Error("'l_quit' option is missing");
                        }
                    } else {
                        throw new Error("Cannot use -parsing and -ncb_quit condition at the same time.");
                    }
                } catch (NumberFormatException e10) {
                    throw new Error("Invalid value in 'ncb_quit' option: " + dtVar.mo47131a("ncb_quit"));
                } catch (IllegalArgumentException e11) {
                    throw new Error("'ncb_quit' option is missing");
                }
            } catch (NumberFormatException e12) {
                throw new Error("Invalid value in 'nbytes' option: " + dtVar.mo47131a("nbytes"));
            } catch (IllegalArgumentException e13) {
                throw new Error("'nbytes' option is missing");
            }
        } catch (NumberFormatException e14) {
            throw new Error("Invalid value in 'rate' option: " + dtVar.mo47131a("rate"));
        } catch (IllegalArgumentException e15) {
            throw new Error("'rate' option is missing");
        }
    }

    /* renamed from: l */
    private void m3360l() {
        int i = this.f5263A;
        this.f5264B += 2;
        if (this.f5264B > i) {
            throw new Error("Requested bitrate is too small for parsing");
        }
        int i2 = i - this.f5264B;
        int i3 = i2;
        for (int i4 = this.f5288w - 1; i4 > 0; i4--) {
            int i5 = (int) (((double) i2) * (((double) this.f5311L[i4]) / this.f5314O));
            this.f5308I[i4] = i5;
            i3 -= i5;
        }
        this.f5308I[0] = i3;
    }

    /* renamed from: m */
    private int m3361m() throws IOException {
        boolean z;
        int i;
        int p;
        int i2 = 1;
        int i3 = 0;
        C5028j b = this.f5327ab.mo46981b();
        short a = this.f5306G.mo47117a();
        if (a == -112) {
            this.f5326aa = false;
            int b2 = this.f5306G.mo47118b();
            b.f5245a = b2;
            if (b2 != 10) {
                throw new C5017bv("Wrong length for SOT marker segment: " + b2);
            }
            int b3 = this.f5306G.mo47118b();
            b.f5246b = b3;
            if (b3 > 65534) {
                throw new C5017bv("Tile index too high in tile-part.");
            }
            int c = this.f5306G.mo47119c();
            b.f5247c = c;
            if (c != 0) {
                z = false;
            } else {
                z = true;
            }
            this.f5304E = z;
            if (c < 0) {
                throw new C5014bs("Tile length larger than maximum supported");
            }
            int g = this.f5306G.mo47125g();
            b.f5248d = g;
            if (g != this.f5321V[b3] || g < 0 || g > 254) {
                throw new C5017bv("Out of order tile-part");
            }
            int g2 = this.f5306G.mo47125g();
            b.f5249e = g2;
            this.f5327ab.f5157b.put("t" + b3 + "_tp" + g, b);
            if (g2 == 0) {
                if (this.f5323X[b3] == 0 || this.f5323X[b3] == this.f5310K.length) {
                    i2 = 2;
                    this.f5320U++;
                }
                int[] iArr = this.f5323X;
                iArr[b3] = iArr[b3] + i2;
                i = this.f5323X[b3];
                int[] iArr2 = this.f5310K[b3];
                this.f5310K[b3] = new int[i];
                for (int i4 = 0; i4 < i - i2; i4++) {
                    this.f5310K[b3][i4] = iArr2[i4];
                }
                int[] iArr3 = this.f5325Z[b3];
                this.f5325Z[b3] = new int[i];
                for (int i5 = 0; i5 < i - i2; i5++) {
                    this.f5325Z[b3][i5] = iArr3[i5];
                }
                int[] iArr4 = this.f5307H[b3];
                this.f5307H[b3] = new int[i];
                for (int i6 = 0; i6 < i - i2; i6++) {
                    this.f5307H[b3][i6] = iArr4[i6];
                }
                int[] iArr5 = this.f5317R[b3];
                this.f5317R[b3] = new int[i];
                while (i3 < i - i2) {
                    this.f5317R[b3][i3] = iArr5[i3];
                    i3++;
                }
            } else if (this.f5323X[b3] == 0) {
                this.f5320U += g2 - 1;
                this.f5323X[b3] = g2;
                this.f5310K[b3] = new int[g2];
                this.f5325Z[b3] = new int[g2];
                this.f5307H[b3] = new int[g2];
                this.f5317R[b3] = new int[g2];
                i = g2;
            } else if (this.f5323X[b3] > g2) {
                throw new C5017bv("Invalid number of tile-parts in tile " + b3 + ": " + g2);
            } else {
                this.f5320U += g2 - this.f5323X[b3];
                if (this.f5323X[b3] != g2) {
                    int[] iArr6 = this.f5310K[b3];
                    this.f5310K[b3] = new int[g2];
                    for (int i7 = 0; i7 < this.f5323X[b3] - 1; i7++) {
                        this.f5310K[b3][i7] = iArr6[i7];
                    }
                    int[] iArr7 = this.f5325Z[b3];
                    this.f5325Z[b3] = new int[g2];
                    for (int i8 = 0; i8 < this.f5323X[b3] - 1; i8++) {
                        this.f5325Z[b3][i8] = iArr7[i8];
                    }
                    int[] iArr8 = this.f5307H[b3];
                    this.f5307H[b3] = new int[g2];
                    for (int i9 = 0; i9 < this.f5323X[b3] - 1; i9++) {
                        this.f5307H[b3][i9] = iArr8[i9];
                    }
                    int[] iArr9 = this.f5317R[b3];
                    this.f5317R[b3] = new int[g2];
                    while (i3 < this.f5323X[b3] - 1) {
                        this.f5317R[b3][i3] = iArr9[i3];
                        i3++;
                    }
                }
                i = g2;
            }
            this.f5291z.mo47058n();
            this.f5291z.f5332a[b3] = i;
            do {
                this.f5291z.mo47038a(this.f5306G.mo47117a(), this.f5306G, b3, g);
                p = this.f5291z.mo47059p();
                C5034cb cbVar = this.f5291z;
            } while ((p & 8192) == 0);
            this.f5291z.mo47036a(b3, g);
            this.f5310K[b3][g] = c;
            this.f5325Z[b3][g] = this.f5322W;
            this.f5322W++;
            this.f5291z.mo47049e(b3);
            return b3;
        } else if (a == -39) {
            this.f5326aa = true;
            return -1;
        } else {
            throw new C5017bv("SOT tag not found in tile-part start");
        }
    }

    /* renamed from: a */
    private boolean m3354a(int[][] iArr, int i, int i2, int i3, int i4, int i5) throws IOException {
        boolean z;
        int i6;
        int i7 = 10000;
        int i8 = i4;
        while (i8 < i5) {
            if (i8 >= this.f5270e.length) {
                i6 = i7;
            } else {
                i6 = i7;
                for (int i9 = i2; i9 < i3; i9++) {
                    if (iArr[i8] != null && i9 < iArr[i8].length && iArr[i8][i9] < i6) {
                        i6 = iArr[i8][i9];
                    }
                }
            }
            i8++;
            i7 = i6;
        }
        int d = mo47013d();
        int i10 = ((this.f5307H[d][this.f5324Y] + this.f5310K[d][this.f5324Y]) - 1) - this.f5317R[d][this.f5324Y];
        int intValue = ((Integer) this.f5266a.f5408h.mo46979d(d)).intValue();
        if (((Boolean) this.f5266a.f5418r.mo46979d(d)).booleanValue()) {
            z = true;
        } else {
            z = false;
        }
        while (i7 < i) {
            for (int i11 = i2; i11 < i3; i11++) {
                for (int i12 = i4; i12 < i5; i12++) {
                    if (i12 < this.f5270e.length && i11 < iArr[i12].length && i11 <= this.f5270e[i12] && i7 >= iArr[i12][i11] && i7 < intValue) {
                        int a = this.f5303D.mo47060a(i12, i11);
                        for (int i13 = 0; i13 < a; i13++) {
                            int e = this.f5306G.mo47123e();
                            if (z) {
                                this.f5303D.mo47063a(i7, i11, i12, i13, this.f5328ac[i12][i11], this.f5308I);
                            }
                            if (e > i10 && this.f5324Y < this.f5307H[d].length - 1) {
                                this.f5324Y++;
                                this.f5306G.mo47121a(this.f5307H[d][this.f5324Y]);
                                i10 = ((this.f5306G.mo47123e() + this.f5310K[d][this.f5324Y]) - 1) - this.f5317R[d][this.f5324Y];
                            }
                            boolean a2 = this.f5303D.mo47064a(this.f5308I, i13, i12, i11);
                            if (a2) {
                                return true;
                            }
                            if (!z) {
                                a2 = this.f5303D.mo47063a(i7, i11, i12, i13, this.f5328ac[i12][i11], this.f5308I);
                            }
                            if (a2) {
                                return true;
                            }
                            this.f5318S.addElement(new Integer(this.f5306G.mo47123e() - e));
                            int e2 = this.f5306G.mo47123e() - e;
                            if (this.f5303D.mo47067b(i7, i11, i12, i13, this.f5328ac[i12][i11], this.f5308I)) {
                                return true;
                            }
                        }
                        continue;
                    }
                }
            }
            i7++;
        }
        return false;
    }

    /* renamed from: b */
    private boolean m3355b(int[][] iArr, int i, int i2, int i3, int i4, int i5) throws IOException {
        boolean z;
        int i6;
        int d = mo47013d();
        int i7 = ((this.f5307H[d][this.f5324Y] + this.f5310K[d][this.f5324Y]) - 1) - this.f5317R[d][this.f5324Y];
        int i8 = 10000;
        int i9 = i4;
        while (i9 < i5) {
            if (i9 >= this.f5270e.length) {
                i6 = i8;
            } else {
                i6 = i8;
                for (int i10 = i2; i10 < i3; i10++) {
                    if (i10 <= this.f5270e[i9] && iArr[i9] != null && i10 < iArr[i9].length && iArr[i9][i10] < i6) {
                        i6 = iArr[i9][i10];
                    }
                }
            }
            i9++;
            i8 = i6;
        }
        int intValue = ((Integer) this.f5266a.f5408h.mo46979d(d)).intValue();
        if (((Boolean) this.f5266a.f5418r.mo46979d(d)).booleanValue()) {
            z = true;
        } else {
            z = false;
        }
        for (int i11 = i2; i11 < i3; i11++) {
            for (int i12 = i8; i12 < i; i12++) {
                for (int i13 = i4; i13 < i5; i13++) {
                    if (i13 < this.f5270e.length && i11 <= this.f5270e[i13] && i11 < iArr[i13].length && i12 >= iArr[i13][i11] && i12 < intValue) {
                        int a = this.f5303D.mo47060a(i13, i11);
                        for (int i14 = 0; i14 < a; i14++) {
                            int e = this.f5306G.mo47123e();
                            if (z) {
                                this.f5303D.mo47063a(i12, i11, i13, i14, this.f5328ac[i13][i11], this.f5308I);
                            }
                            if (e > i7 && this.f5324Y < this.f5307H[d].length - 1) {
                                this.f5324Y++;
                                this.f5306G.mo47121a(this.f5307H[d][this.f5324Y]);
                                i7 = ((this.f5306G.mo47123e() + this.f5310K[d][this.f5324Y]) - 1) - this.f5317R[d][this.f5324Y];
                            }
                            boolean a2 = this.f5303D.mo47064a(this.f5308I, i14, i13, i11);
                            if (a2) {
                                return true;
                            }
                            if (!z) {
                                a2 = this.f5303D.mo47063a(i12, i11, i13, i14, this.f5328ac[i13][i11], this.f5308I);
                            }
                            if (a2) {
                                return true;
                            }
                            this.f5318S.addElement(new Integer(this.f5306G.mo47123e() - e));
                            int e2 = this.f5306G.mo47123e() - e;
                            if (this.f5303D.mo47067b(i12, i11, i13, i14, this.f5328ac[i13][i11], this.f5308I)) {
                                return true;
                            }
                        }
                        continue;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0156  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m3356c(int[][] r29, int r30, int r31, int r32, int r33, int r34) throws java.io.IOException {
        /*
            r28 = this;
            r1 = 0
            r0 = r28
            jumio.nv.nfc.cu r5 = r0.mo47008b(r1)
            r1 = 0
            r0 = r28
            jumio.nv.nfc.cu r6 = r0.mo47002a(r1)
            r0 = r28
            jumio.nv.nfc.cb r1 = r0.f5291z
            int r1 = r1.mo47048e()
            r0 = r28
            jumio.nv.nfc.cb r2 = r0.f5291z
            int r2 = r2.mo47050f()
            r0 = r28
            jumio.nv.nfc.cb r3 = r0.f5291z
            int r3 = r3.mo47042c()
            int r3 = r3 + r1
            r0 = r28
            jumio.nv.nfc.cb r4 = r0.f5291z
            int r4 = r4.mo47045d()
            int r4 = r4 + r2
            int r7 = r28.mo47022h()
            int r10 = r28.mo47023i()
            int r11 = r28.mo47024j()
            int r12 = r28.mo47025k()
            int r8 = r6.f5475a
            if (r8 != 0) goto L_0x00aa
            r8 = r1
        L_0x0045:
            int r1 = r6.f5476b
            if (r1 != 0) goto L_0x00b0
            r9 = r2
        L_0x004a:
            int r1 = r6.f5475a
            int r2 = r5.f5475a
            int r2 = r2 + -1
            if (r1 == r2) goto L_0x00b6
            int r1 = r6.f5475a
            int r1 = r1 + 1
            int r1 = r1 * r11
            int r1 = r1 + r7
        L_0x0058:
            int r2 = r6.f5476b
            int r3 = r5.f5476b
            int r3 = r3 + -1
            if (r2 == r3) goto L_0x00b8
            int r2 = r6.f5476b
            int r2 = r2 + 1
            int r2 = r2 * r12
            int r2 = r2 + r10
        L_0x0066:
            int r21 = r28.mo47013d()
            r20 = 0
            r19 = 0
            r6 = 0
            r0 = r34
            int[][] r0 = new int[r0][]
            r22 = r0
            r12 = 100000(0x186a0, float:1.4013E-40)
            r15 = r33
            r3 = r8
            r17 = r2
            r18 = r1
            r2 = r9
        L_0x0080:
            r0 = r34
            if (r15 >= r0) goto L_0x0175
            r14 = r31
            r4 = r17
            r5 = r18
            r1 = r12
            r7 = r19
            r10 = r20
        L_0x008f:
            r0 = r32
            if (r14 >= r0) goto L_0x0167
            r0 = r28
            int[] r11 = r0.f5270e
            int r11 = r11.length
            if (r15 < r11) goto L_0x00ba
            r27 = r2
            r2 = r5
            r5 = r1
            r1 = r27
        L_0x00a0:
            int r11 = r14 + 1
            r14 = r11
            r27 = r1
            r1 = r5
            r5 = r2
            r2 = r27
            goto L_0x008f
        L_0x00aa:
            int r1 = r6.f5475a
            int r1 = r1 * r11
            int r1 = r1 + r7
            r8 = r1
            goto L_0x0045
        L_0x00b0:
            int r1 = r6.f5476b
            int r1 = r1 * r12
            int r1 = r1 + r10
            r9 = r1
            goto L_0x004a
        L_0x00b6:
            r1 = r3
            goto L_0x0058
        L_0x00b8:
            r2 = r4
            goto L_0x0066
        L_0x00ba:
            r0 = r28
            int[] r11 = r0.f5270e
            r11 = r11[r15]
            if (r14 <= r11) goto L_0x00c9
            r27 = r2
            r2 = r5
            r5 = r1
            r1 = r27
            goto L_0x00a0
        L_0x00c9:
            r0 = r28
            int[] r11 = r0.f5270e
            r11 = r11[r15]
            int r11 = r11 + 1
            int[] r11 = new int[r11]
            r22[r15] = r11
            r11 = r29[r15]
            if (r11 == 0) goto L_0x00e8
            r11 = r29[r15]
            int r11 = r11.length
            if (r14 >= r11) goto L_0x00e8
            r11 = r29[r15]
            r11 = r11[r14]
            if (r11 >= r1) goto L_0x00e8
            r1 = r29[r15]
            r1 = r1[r14]
        L_0x00e8:
            r0 = r28
            jumio.nv.nfc.cc r11 = r0.f5303D
            int r11 = r11.mo47060a(r15, r14)
            int r11 = r11 + -1
            r13 = r11
            r27 = r5
            r5 = r2
            r2 = r27
        L_0x00f8:
            if (r13 < 0) goto L_0x036c
            r0 = r28
            jumio.nv.nfc.cc r11 = r0.f5303D
            jumio.nv.nfc.bx r16 = r11.mo47068c(r15, r14, r13)
            r0 = r16
            int r11 = r0.f5251a
            if (r11 == r8) goto L_0x0365
            r0 = r16
            int r11 = r0.f5251a
            if (r11 >= r2) goto L_0x0112
            r0 = r16
            int r2 = r0.f5251a
        L_0x0112:
            r0 = r16
            int r11 = r0.f5251a
            if (r11 <= r3) goto L_0x0365
            r0 = r16
            int r3 = r0.f5251a
            r27 = r3
            r3 = r2
            r2 = r27
        L_0x0121:
            r0 = r16
            int r11 = r0.f5252b
            if (r11 == r9) goto L_0x0361
            r0 = r16
            int r11 = r0.f5252b
            if (r11 >= r4) goto L_0x0131
            r0 = r16
            int r4 = r0.f5252b
        L_0x0131:
            r0 = r16
            int r11 = r0.f5252b
            if (r11 <= r5) goto L_0x0361
            r0 = r16
            int r5 = r0.f5252b
            r11 = r5
            r12 = r4
        L_0x013d:
            if (r6 != 0) goto L_0x0156
            r0 = r16
            int r5 = r0.f5253c
            r0 = r16
            int r4 = r0.f5254d
        L_0x0147:
            int r6 = r6 + 1
            int r7 = r13 + -1
            r10 = r5
            r13 = r7
            r7 = r4
            r5 = r11
            r4 = r12
            r27 = r3
            r3 = r2
            r2 = r27
            goto L_0x00f8
        L_0x0156:
            r0 = r16
            int r4 = r0.f5253c
            int r5 = jumio.p317nv.nfc.C5078ds.m3576a(r10, r4)
            r0 = r16
            int r4 = r0.f5254d
            int r4 = jumio.p317nv.nfc.C5078ds.m3576a(r7, r4)
            goto L_0x0147
        L_0x0167:
            int r11 = r15 + 1
            r15 = r11
            r17 = r4
            r18 = r5
            r12 = r1
            r19 = r7
            r20 = r10
            goto L_0x0080
        L_0x0175:
            if (r6 != 0) goto L_0x0180
            java.lang.Error r1 = new java.lang.Error
            java.lang.String r2 = "Image cannot have no precinct"
            r1.<init>(r2)
            throw r1
        L_0x0180:
            int r1 = r2 - r17
            int r1 = r1 / r19
            int r23 = r1 + 1
            int r1 = r3 - r18
            int r1 = r1 / r20
            int r24 = r1 + 1
            r0 = r28
            int[][] r1 = r0.f5307H
            r1 = r1[r21]
            r0 = r28
            int r2 = r0.f5324Y
            r1 = r1[r2]
            r0 = r28
            int[][] r2 = r0.f5310K
            r2 = r2[r21]
            r0 = r28
            int r3 = r0.f5324Y
            r2 = r2[r3]
            int r1 = r1 + r2
            int r1 = r1 + -1
            r0 = r28
            int[][] r2 = r0.f5317R
            r2 = r2[r21]
            r0 = r28
            int r3 = r0.f5324Y
            r2 = r2[r3]
            int r11 = r1 - r2
            r0 = r28
            jumio.nv.nfc.cg r1 = r0.f5266a
            jumio.nv.nfc.bq r1 = r1.f5408h
            r0 = r21
            java.lang.Object r1 = r1.mo46979d(r0)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r25 = r1.intValue()
            r2 = 0
            r0 = r28
            jumio.nv.nfc.cg r1 = r0.f5266a
            jumio.nv.nfc.br r1 = r1.f5418r
            r0 = r21
            java.lang.Object r1 = r1.mo46979d(r0)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x035e
            r1 = 1
            r10 = r1
        L_0x01de:
            r3 = r31
        L_0x01e0:
            r0 = r32
            if (r3 >= r0) goto L_0x035b
            r1 = 0
            r15 = r1
            r16 = r9
            r2 = r8
        L_0x01e9:
            r0 = r23
            if (r15 > r0) goto L_0x0357
            r1 = 0
            r13 = r1
            r14 = r2
        L_0x01f0:
            r0 = r24
            if (r13 > r0) goto L_0x0345
            r4 = r33
        L_0x01f6:
            r0 = r34
            if (r4 >= r0) goto L_0x0335
            r0 = r28
            int[] r1 = r0.f5270e
            int r1 = r1.length
            if (r4 < r1) goto L_0x0204
        L_0x0201:
            int r4 = r4 + 1
            goto L_0x01f6
        L_0x0204:
            r0 = r28
            int[] r1 = r0.f5270e
            r1 = r1[r4]
            if (r3 > r1) goto L_0x0201
            r1 = r22[r4]
            r1 = r1[r3]
            r0 = r28
            jumio.nv.nfc.cc r2 = r0.f5303D
            int r2 = r2.mo47060a(r4, r3)
            if (r1 >= r2) goto L_0x0201
            r0 = r28
            jumio.nv.nfc.cc r1 = r0.f5303D
            r2 = r22[r4]
            r2 = r2[r3]
            jumio.nv.nfc.bx r1 = r1.mo47068c(r4, r3, r2)
            int r2 = r1.f5251a
            if (r2 != r14) goto L_0x0201
            int r1 = r1.f5252b
            r0 = r16
            if (r1 != r0) goto L_0x0201
            r2 = r12
        L_0x0231:
            r0 = r30
            if (r2 >= r0) goto L_0x032b
            r1 = r29[r4]
            int r1 = r1.length
            if (r3 < r1) goto L_0x023d
        L_0x023a:
            int r2 = r2 + 1
            goto L_0x0231
        L_0x023d:
            r1 = r29[r4]
            r1 = r1[r3]
            if (r2 < r1) goto L_0x023a
            r0 = r25
            if (r2 >= r0) goto L_0x023a
            r0 = r28
            jumio.nv.nfc.df r1 = r0.f5306G
            int r26 = r1.mo47123e()
            if (r10 == 0) goto L_0x0268
            r0 = r28
            jumio.nv.nfc.cc r1 = r0.f5303D
            r5 = r22[r4]
            r5 = r5[r3]
            r0 = r28
            jumio.nv.nfc.bz[][][][][] r6 = r0.f5328ac
            r6 = r6[r4]
            r6 = r6[r3]
            r0 = r28
            int[] r7 = r0.f5308I
            r1.mo47063a(r2, r3, r4, r5, r6, r7)
        L_0x0268:
            r0 = r26
            if (r0 <= r11) goto L_0x02bd
            r0 = r28
            int r1 = r0.f5324Y
            r0 = r28
            int[][] r5 = r0.f5307H
            r5 = r5[r21]
            int r5 = r5.length
            int r5 = r5 + -1
            if (r1 >= r5) goto L_0x02bd
            r0 = r28
            int r1 = r0.f5324Y
            int r1 = r1 + 1
            r0 = r28
            r0.f5324Y = r1
            r0 = r28
            jumio.nv.nfc.df r1 = r0.f5306G
            r0 = r28
            int[][] r5 = r0.f5307H
            r5 = r5[r21]
            r0 = r28
            int r6 = r0.f5324Y
            r5 = r5[r6]
            r1.mo47121a(r5)
            r0 = r28
            jumio.nv.nfc.df r1 = r0.f5306G
            int r1 = r1.mo47123e()
            r0 = r28
            int[][] r5 = r0.f5310K
            r5 = r5[r21]
            r0 = r28
            int r6 = r0.f5324Y
            r5 = r5[r6]
            int r1 = r1 + r5
            int r1 = r1 + -1
            r0 = r28
            int[][] r5 = r0.f5317R
            r5 = r5[r21]
            r0 = r28
            int r6 = r0.f5324Y
            r5 = r5[r6]
            int r1 = r1 - r5
            r11 = r1
        L_0x02bd:
            r0 = r28
            jumio.nv.nfc.cc r1 = r0.f5303D
            r0 = r28
            int[] r5 = r0.f5308I
            r6 = r22[r4]
            r6 = r6[r3]
            boolean r1 = r1.mo47064a(r5, r6, r4, r3)
            if (r1 == 0) goto L_0x02d1
            r1 = 1
        L_0x02d0:
            return r1
        L_0x02d1:
            if (r10 != 0) goto L_0x02eb
            r0 = r28
            jumio.nv.nfc.cc r1 = r0.f5303D
            r5 = r22[r4]
            r5 = r5[r3]
            r0 = r28
            jumio.nv.nfc.bz[][][][][] r6 = r0.f5328ac
            r6 = r6[r4]
            r6 = r6[r3]
            r0 = r28
            int[] r7 = r0.f5308I
            boolean r1 = r1.mo47063a(r2, r3, r4, r5, r6, r7)
        L_0x02eb:
            if (r1 == 0) goto L_0x02ef
            r1 = 1
            goto L_0x02d0
        L_0x02ef:
            r0 = r28
            jumio.nv.nfc.df r1 = r0.f5306G
            int r1 = r1.mo47123e()
            int r1 = r1 - r26
            r0 = r28
            java.util.Vector r5 = r0.f5318S
            java.lang.Integer r6 = new java.lang.Integer
            r6.<init>(r1)
            r5.addElement(r6)
            r0 = r28
            jumio.nv.nfc.cc r1 = r0.f5303D
            r5 = r22[r4]
            r5 = r5[r3]
            r0 = r28
            jumio.nv.nfc.bz[][][][][] r6 = r0.f5328ac
            r6 = r6[r4]
            r6 = r6[r3]
            r0 = r28
            int[] r7 = r0.f5308I
            boolean r1 = r1.mo47067b(r2, r3, r4, r5, r6, r7)
            r0 = r28
            jumio.nv.nfc.df r5 = r0.f5306G
            int r5 = r5.mo47123e()
            int r5 = r5 - r26
            if (r1 == 0) goto L_0x023a
            r1 = 1
            goto L_0x02d0
        L_0x032b:
            r1 = r22[r4]
            r2 = r1[r3]
            int r2 = r2 + 1
            r1[r3] = r2
            goto L_0x0201
        L_0x0335:
            r0 = r24
            if (r13 == r0) goto L_0x0343
            int r1 = r13 * r20
            int r1 = r1 + r18
        L_0x033d:
            int r2 = r13 + 1
            r13 = r2
            r14 = r1
            goto L_0x01f0
        L_0x0343:
            r1 = r8
            goto L_0x033d
        L_0x0345:
            r0 = r23
            if (r15 == r0) goto L_0x0355
            int r1 = r15 * r19
            int r1 = r1 + r17
        L_0x034d:
            int r2 = r15 + 1
            r15 = r2
            r16 = r1
            r2 = r14
            goto L_0x01e9
        L_0x0355:
            r1 = r9
            goto L_0x034d
        L_0x0357:
            int r3 = r3 + 1
            goto L_0x01e0
        L_0x035b:
            r1 = 0
            goto L_0x02d0
        L_0x035e:
            r10 = r2
            goto L_0x01de
        L_0x0361:
            r11 = r5
            r12 = r4
            goto L_0x013d
        L_0x0365:
            r27 = r3
            r3 = r2
            r2 = r27
            goto L_0x0121
        L_0x036c:
            r27 = r5
            r5 = r1
            r1 = r27
            goto L_0x00a0
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C5033ca.m3356c(int[][], int, int, int, int, int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0156  */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m3357d(int[][] r29, int r30, int r31, int r32, int r33, int r34) throws java.io.IOException {
        /*
            r28 = this;
            r1 = 0
            r0 = r28
            jumio.nv.nfc.cu r5 = r0.mo47008b(r1)
            r1 = 0
            r0 = r28
            jumio.nv.nfc.cu r6 = r0.mo47002a(r1)
            r0 = r28
            jumio.nv.nfc.cb r1 = r0.f5291z
            int r1 = r1.mo47048e()
            r0 = r28
            jumio.nv.nfc.cb r2 = r0.f5291z
            int r2 = r2.mo47050f()
            r0 = r28
            jumio.nv.nfc.cb r3 = r0.f5291z
            int r3 = r3.mo47042c()
            int r3 = r3 + r1
            r0 = r28
            jumio.nv.nfc.cb r4 = r0.f5291z
            int r4 = r4.mo47045d()
            int r4 = r4 + r2
            int r7 = r28.mo47022h()
            int r10 = r28.mo47023i()
            int r11 = r28.mo47024j()
            int r12 = r28.mo47025k()
            int r8 = r6.f5475a
            if (r8 != 0) goto L_0x00aa
            r8 = r1
        L_0x0045:
            int r1 = r6.f5476b
            if (r1 != 0) goto L_0x00b0
            r9 = r2
        L_0x004a:
            int r1 = r6.f5475a
            int r2 = r5.f5475a
            int r2 = r2 + -1
            if (r1 == r2) goto L_0x00b6
            int r1 = r6.f5475a
            int r1 = r1 + 1
            int r1 = r1 * r11
            int r1 = r1 + r7
        L_0x0058:
            int r2 = r6.f5476b
            int r3 = r5.f5476b
            int r3 = r3 + -1
            if (r2 == r3) goto L_0x00b8
            int r2 = r6.f5476b
            int r2 = r2 + 1
            int r2 = r2 * r12
            int r2 = r2 + r10
        L_0x0066:
            int r21 = r28.mo47013d()
            r20 = 0
            r19 = 0
            r6 = 0
            r0 = r34
            int[][] r0 = new int[r0][]
            r22 = r0
            r12 = 100000(0x186a0, float:1.4013E-40)
            r15 = r33
            r3 = r8
            r17 = r2
            r18 = r1
            r2 = r9
        L_0x0080:
            r0 = r34
            if (r15 >= r0) goto L_0x0175
            r14 = r31
            r4 = r17
            r5 = r18
            r1 = r12
            r7 = r19
            r10 = r20
        L_0x008f:
            r0 = r32
            if (r14 >= r0) goto L_0x0167
            r0 = r28
            int[] r11 = r0.f5270e
            int r11 = r11.length
            if (r15 < r11) goto L_0x00ba
            r27 = r2
            r2 = r5
            r5 = r1
            r1 = r27
        L_0x00a0:
            int r11 = r14 + 1
            r14 = r11
            r27 = r1
            r1 = r5
            r5 = r2
            r2 = r27
            goto L_0x008f
        L_0x00aa:
            int r1 = r6.f5475a
            int r1 = r1 * r11
            int r1 = r1 + r7
            r8 = r1
            goto L_0x0045
        L_0x00b0:
            int r1 = r6.f5476b
            int r1 = r1 * r12
            int r1 = r1 + r10
            r9 = r1
            goto L_0x004a
        L_0x00b6:
            r1 = r3
            goto L_0x0058
        L_0x00b8:
            r2 = r4
            goto L_0x0066
        L_0x00ba:
            r0 = r28
            int[] r11 = r0.f5270e
            r11 = r11[r15]
            if (r14 <= r11) goto L_0x00c9
            r27 = r2
            r2 = r5
            r5 = r1
            r1 = r27
            goto L_0x00a0
        L_0x00c9:
            r0 = r28
            int[] r11 = r0.f5270e
            r11 = r11[r15]
            int r11 = r11 + 1
            int[] r11 = new int[r11]
            r22[r15] = r11
            r11 = r29[r15]
            if (r11 == 0) goto L_0x00e8
            r11 = r29[r15]
            int r11 = r11.length
            if (r14 >= r11) goto L_0x00e8
            r11 = r29[r15]
            r11 = r11[r14]
            if (r11 >= r1) goto L_0x00e8
            r1 = r29[r15]
            r1 = r1[r14]
        L_0x00e8:
            r0 = r28
            jumio.nv.nfc.cc r11 = r0.f5303D
            int r11 = r11.mo47060a(r15, r14)
            int r11 = r11 + -1
            r13 = r11
            r27 = r5
            r5 = r2
            r2 = r27
        L_0x00f8:
            if (r13 < 0) goto L_0x036b
            r0 = r28
            jumio.nv.nfc.cc r11 = r0.f5303D
            jumio.nv.nfc.bx r16 = r11.mo47068c(r15, r14, r13)
            r0 = r16
            int r11 = r0.f5251a
            if (r11 == r8) goto L_0x0364
            r0 = r16
            int r11 = r0.f5251a
            if (r11 >= r2) goto L_0x0112
            r0 = r16
            int r2 = r0.f5251a
        L_0x0112:
            r0 = r16
            int r11 = r0.f5251a
            if (r11 <= r3) goto L_0x0364
            r0 = r16
            int r3 = r0.f5251a
            r27 = r3
            r3 = r2
            r2 = r27
        L_0x0121:
            r0 = r16
            int r11 = r0.f5252b
            if (r11 == r9) goto L_0x0360
            r0 = r16
            int r11 = r0.f5252b
            if (r11 >= r4) goto L_0x0131
            r0 = r16
            int r4 = r0.f5252b
        L_0x0131:
            r0 = r16
            int r11 = r0.f5252b
            if (r11 <= r5) goto L_0x0360
            r0 = r16
            int r5 = r0.f5252b
            r11 = r5
            r12 = r4
        L_0x013d:
            if (r6 != 0) goto L_0x0156
            r0 = r16
            int r5 = r0.f5253c
            r0 = r16
            int r4 = r0.f5254d
        L_0x0147:
            int r6 = r6 + 1
            int r7 = r13 + -1
            r10 = r5
            r13 = r7
            r7 = r4
            r5 = r11
            r4 = r12
            r27 = r3
            r3 = r2
            r2 = r27
            goto L_0x00f8
        L_0x0156:
            r0 = r16
            int r4 = r0.f5253c
            int r5 = jumio.p317nv.nfc.C5078ds.m3576a(r10, r4)
            r0 = r16
            int r4 = r0.f5254d
            int r4 = jumio.p317nv.nfc.C5078ds.m3576a(r7, r4)
            goto L_0x0147
        L_0x0167:
            int r11 = r15 + 1
            r15 = r11
            r17 = r4
            r18 = r5
            r12 = r1
            r19 = r7
            r20 = r10
            goto L_0x0080
        L_0x0175:
            if (r6 != 0) goto L_0x0180
            java.lang.Error r1 = new java.lang.Error
            java.lang.String r2 = "Image cannot have no precinct"
            r1.<init>(r2)
            throw r1
        L_0x0180:
            int r1 = r2 - r17
            int r1 = r1 / r19
            int r23 = r1 + 1
            int r1 = r3 - r18
            int r1 = r1 / r20
            int r24 = r1 + 1
            r0 = r28
            int[][] r1 = r0.f5307H
            r1 = r1[r21]
            r0 = r28
            int r2 = r0.f5324Y
            r1 = r1[r2]
            r0 = r28
            int[][] r2 = r0.f5310K
            r2 = r2[r21]
            r0 = r28
            int r3 = r0.f5324Y
            r2 = r2[r3]
            int r1 = r1 + r2
            int r1 = r1 + -1
            r0 = r28
            int[][] r2 = r0.f5317R
            r2 = r2[r21]
            r0 = r28
            int r3 = r0.f5324Y
            r2 = r2[r3]
            int r11 = r1 - r2
            r0 = r28
            jumio.nv.nfc.cg r1 = r0.f5266a
            jumio.nv.nfc.bq r1 = r1.f5408h
            r0 = r21
            java.lang.Object r1 = r1.mo46979d(r0)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r25 = r1.intValue()
            r2 = 0
            r0 = r28
            jumio.nv.nfc.cg r1 = r0.f5266a
            jumio.nv.nfc.br r1 = r1.f5418r
            r0 = r21
            java.lang.Object r1 = r1.mo46979d(r0)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x035d
            r1 = 1
            r10 = r1
        L_0x01de:
            r1 = 0
            r15 = r1
            r2 = r8
            r16 = r9
        L_0x01e3:
            r0 = r23
            if (r15 > r0) goto L_0x035a
            r1 = 0
            r13 = r1
            r14 = r2
        L_0x01ea:
            r0 = r24
            if (r13 > r0) goto L_0x0348
            r4 = r33
        L_0x01f0:
            r0 = r34
            if (r4 >= r0) goto L_0x0338
            r0 = r28
            int[] r1 = r0.f5270e
            int r1 = r1.length
            if (r4 < r1) goto L_0x01fe
        L_0x01fb:
            int r4 = r4 + 1
            goto L_0x01f0
        L_0x01fe:
            r3 = r31
        L_0x0200:
            r0 = r32
            if (r3 >= r0) goto L_0x01fb
            r0 = r28
            int[] r1 = r0.f5270e
            r1 = r1[r4]
            if (r3 <= r1) goto L_0x020f
        L_0x020c:
            int r3 = r3 + 1
            goto L_0x0200
        L_0x020f:
            r1 = r22[r4]
            r1 = r1[r3]
            r0 = r28
            jumio.nv.nfc.cc r2 = r0.f5303D
            int r2 = r2.mo47060a(r4, r3)
            if (r1 >= r2) goto L_0x020c
            r0 = r28
            jumio.nv.nfc.cc r1 = r0.f5303D
            r2 = r22[r4]
            r2 = r2[r3]
            jumio.nv.nfc.bx r1 = r1.mo47068c(r4, r3, r2)
            int r2 = r1.f5251a
            if (r2 != r14) goto L_0x020c
            int r1 = r1.f5252b
            r0 = r16
            if (r1 != r0) goto L_0x020c
            r2 = r12
        L_0x0234:
            r0 = r30
            if (r2 >= r0) goto L_0x032e
            r1 = r29[r4]
            int r1 = r1.length
            if (r3 < r1) goto L_0x0240
        L_0x023d:
            int r2 = r2 + 1
            goto L_0x0234
        L_0x0240:
            r1 = r29[r4]
            r1 = r1[r3]
            if (r2 < r1) goto L_0x023d
            r0 = r25
            if (r2 >= r0) goto L_0x023d
            r0 = r28
            jumio.nv.nfc.df r1 = r0.f5306G
            int r26 = r1.mo47123e()
            if (r10 == 0) goto L_0x026b
            r0 = r28
            jumio.nv.nfc.cc r1 = r0.f5303D
            r5 = r22[r4]
            r5 = r5[r3]
            r0 = r28
            jumio.nv.nfc.bz[][][][][] r6 = r0.f5328ac
            r6 = r6[r4]
            r6 = r6[r3]
            r0 = r28
            int[] r7 = r0.f5308I
            r1.mo47063a(r2, r3, r4, r5, r6, r7)
        L_0x026b:
            r0 = r26
            if (r0 <= r11) goto L_0x02c0
            r0 = r28
            int r1 = r0.f5324Y
            r0 = r28
            int[][] r5 = r0.f5307H
            r5 = r5[r21]
            int r5 = r5.length
            int r5 = r5 + -1
            if (r1 >= r5) goto L_0x02c0
            r0 = r28
            int r1 = r0.f5324Y
            int r1 = r1 + 1
            r0 = r28
            r0.f5324Y = r1
            r0 = r28
            jumio.nv.nfc.df r1 = r0.f5306G
            r0 = r28
            int[][] r5 = r0.f5307H
            r5 = r5[r21]
            r0 = r28
            int r6 = r0.f5324Y
            r5 = r5[r6]
            r1.mo47121a(r5)
            r0 = r28
            jumio.nv.nfc.df r1 = r0.f5306G
            int r1 = r1.mo47123e()
            r0 = r28
            int[][] r5 = r0.f5310K
            r5 = r5[r21]
            r0 = r28
            int r6 = r0.f5324Y
            r5 = r5[r6]
            int r1 = r1 + r5
            int r1 = r1 + -1
            r0 = r28
            int[][] r5 = r0.f5317R
            r5 = r5[r21]
            r0 = r28
            int r6 = r0.f5324Y
            r5 = r5[r6]
            int r1 = r1 - r5
            r11 = r1
        L_0x02c0:
            r0 = r28
            jumio.nv.nfc.cc r1 = r0.f5303D
            r0 = r28
            int[] r5 = r0.f5308I
            r6 = r22[r4]
            r6 = r6[r3]
            boolean r1 = r1.mo47064a(r5, r6, r4, r3)
            if (r1 == 0) goto L_0x02d4
            r1 = 1
        L_0x02d3:
            return r1
        L_0x02d4:
            if (r10 != 0) goto L_0x02ee
            r0 = r28
            jumio.nv.nfc.cc r1 = r0.f5303D
            r5 = r22[r4]
            r5 = r5[r3]
            r0 = r28
            jumio.nv.nfc.bz[][][][][] r6 = r0.f5328ac
            r6 = r6[r4]
            r6 = r6[r3]
            r0 = r28
            int[] r7 = r0.f5308I
            boolean r1 = r1.mo47063a(r2, r3, r4, r5, r6, r7)
        L_0x02ee:
            if (r1 == 0) goto L_0x02f2
            r1 = 1
            goto L_0x02d3
        L_0x02f2:
            r0 = r28
            jumio.nv.nfc.df r1 = r0.f5306G
            int r1 = r1.mo47123e()
            int r1 = r1 - r26
            r0 = r28
            java.util.Vector r5 = r0.f5318S
            java.lang.Integer r6 = new java.lang.Integer
            r6.<init>(r1)
            r5.addElement(r6)
            r0 = r28
            jumio.nv.nfc.cc r1 = r0.f5303D
            r5 = r22[r4]
            r5 = r5[r3]
            r0 = r28
            jumio.nv.nfc.bz[][][][][] r6 = r0.f5328ac
            r6 = r6[r4]
            r6 = r6[r3]
            r0 = r28
            int[] r7 = r0.f5308I
            boolean r1 = r1.mo47067b(r2, r3, r4, r5, r6, r7)
            r0 = r28
            jumio.nv.nfc.df r5 = r0.f5306G
            int r5 = r5.mo47123e()
            int r5 = r5 - r26
            if (r1 == 0) goto L_0x023d
            r1 = 1
            goto L_0x02d3
        L_0x032e:
            r1 = r22[r4]
            r2 = r1[r3]
            int r2 = r2 + 1
            r1[r3] = r2
            goto L_0x020c
        L_0x0338:
            r0 = r24
            if (r13 == r0) goto L_0x0346
            int r1 = r13 * r20
            int r1 = r1 + r18
        L_0x0340:
            int r2 = r13 + 1
            r13 = r2
            r14 = r1
            goto L_0x01ea
        L_0x0346:
            r1 = r8
            goto L_0x0340
        L_0x0348:
            r0 = r23
            if (r15 == r0) goto L_0x0358
            int r1 = r15 * r19
            int r1 = r1 + r17
        L_0x0350:
            int r2 = r15 + 1
            r15 = r2
            r16 = r1
            r2 = r14
            goto L_0x01e3
        L_0x0358:
            r1 = r9
            goto L_0x0350
        L_0x035a:
            r1 = 0
            goto L_0x02d3
        L_0x035d:
            r10 = r2
            goto L_0x01de
        L_0x0360:
            r11 = r5
            r12 = r4
            goto L_0x013d
        L_0x0364:
            r27 = r3
            r3 = r2
            r2 = r27
            goto L_0x0121
        L_0x036b:
            r27 = r5
            r5 = r1
            r1 = r27
            goto L_0x00a0
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C5033ca.m3357d(int[][], int, int, int, int, int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0156  */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m3358e(int[][] r28, int r29, int r30, int r31, int r32, int r33) throws java.io.IOException {
        /*
            r27 = this;
            r1 = 0
            r0 = r27
            jumio.nv.nfc.cu r5 = r0.mo47008b(r1)
            r1 = 0
            r0 = r27
            jumio.nv.nfc.cu r6 = r0.mo47002a(r1)
            r0 = r27
            jumio.nv.nfc.cb r1 = r0.f5291z
            int r1 = r1.mo47048e()
            r0 = r27
            jumio.nv.nfc.cb r2 = r0.f5291z
            int r2 = r2.mo47050f()
            r0 = r27
            jumio.nv.nfc.cb r3 = r0.f5291z
            int r3 = r3.mo47042c()
            int r3 = r3 + r1
            r0 = r27
            jumio.nv.nfc.cb r4 = r0.f5291z
            int r4 = r4.mo47045d()
            int r4 = r4 + r2
            int r7 = r27.mo47022h()
            int r10 = r27.mo47023i()
            int r11 = r27.mo47024j()
            int r12 = r27.mo47025k()
            int r8 = r6.f5475a
            if (r8 != 0) goto L_0x00aa
            r8 = r1
        L_0x0045:
            int r1 = r6.f5476b
            if (r1 != 0) goto L_0x00b0
            r9 = r2
        L_0x004a:
            int r1 = r6.f5475a
            int r2 = r5.f5475a
            int r2 = r2 + -1
            if (r1 == r2) goto L_0x00b6
            int r1 = r6.f5475a
            int r1 = r1 + 1
            int r1 = r1 * r11
            int r1 = r1 + r7
        L_0x0058:
            int r2 = r6.f5476b
            int r3 = r5.f5476b
            int r3 = r3 + -1
            if (r2 == r3) goto L_0x00b8
            int r2 = r6.f5476b
            int r2 = r2 + 1
            int r2 = r2 * r12
            int r2 = r2 + r10
        L_0x0066:
            int r21 = r27.mo47013d()
            r20 = 0
            r19 = 0
            r6 = 0
            r0 = r33
            int[][] r0 = new int[r0][]
            r22 = r0
            r12 = 100000(0x186a0, float:1.4013E-40)
            r15 = r32
            r3 = r8
            r17 = r2
            r18 = r1
            r2 = r9
        L_0x0080:
            r0 = r33
            if (r15 >= r0) goto L_0x0175
            r14 = r30
            r4 = r17
            r5 = r18
            r1 = r12
            r7 = r19
            r10 = r20
        L_0x008f:
            r0 = r31
            if (r14 >= r0) goto L_0x0167
            r0 = r27
            int[] r11 = r0.f5270e
            int r11 = r11.length
            if (r15 < r11) goto L_0x00ba
            r26 = r2
            r2 = r5
            r5 = r1
            r1 = r26
        L_0x00a0:
            int r11 = r14 + 1
            r14 = r11
            r26 = r1
            r1 = r5
            r5 = r2
            r2 = r26
            goto L_0x008f
        L_0x00aa:
            int r1 = r6.f5475a
            int r1 = r1 * r11
            int r1 = r1 + r7
            r8 = r1
            goto L_0x0045
        L_0x00b0:
            int r1 = r6.f5476b
            int r1 = r1 * r12
            int r1 = r1 + r10
            r9 = r1
            goto L_0x004a
        L_0x00b6:
            r1 = r3
            goto L_0x0058
        L_0x00b8:
            r2 = r4
            goto L_0x0066
        L_0x00ba:
            r0 = r27
            int[] r11 = r0.f5270e
            r11 = r11[r15]
            if (r14 <= r11) goto L_0x00c9
            r26 = r2
            r2 = r5
            r5 = r1
            r1 = r26
            goto L_0x00a0
        L_0x00c9:
            r0 = r27
            int[] r11 = r0.f5270e
            r11 = r11[r15]
            int r11 = r11 + 1
            int[] r11 = new int[r11]
            r22[r15] = r11
            r11 = r28[r15]
            if (r11 == 0) goto L_0x00e8
            r11 = r28[r15]
            int r11 = r11.length
            if (r14 >= r11) goto L_0x00e8
            r11 = r28[r15]
            r11 = r11[r14]
            if (r11 >= r1) goto L_0x00e8
            r1 = r28[r15]
            r1 = r1[r14]
        L_0x00e8:
            r0 = r27
            jumio.nv.nfc.cc r11 = r0.f5303D
            int r11 = r11.mo47060a(r15, r14)
            int r11 = r11 + -1
            r13 = r11
            r26 = r5
            r5 = r2
            r2 = r26
        L_0x00f8:
            if (r13 < 0) goto L_0x0369
            r0 = r27
            jumio.nv.nfc.cc r11 = r0.f5303D
            jumio.nv.nfc.bx r16 = r11.mo47068c(r15, r14, r13)
            r0 = r16
            int r11 = r0.f5251a
            if (r11 == r8) goto L_0x0362
            r0 = r16
            int r11 = r0.f5251a
            if (r11 >= r2) goto L_0x0112
            r0 = r16
            int r2 = r0.f5251a
        L_0x0112:
            r0 = r16
            int r11 = r0.f5251a
            if (r11 <= r3) goto L_0x0362
            r0 = r16
            int r3 = r0.f5251a
            r26 = r3
            r3 = r2
            r2 = r26
        L_0x0121:
            r0 = r16
            int r11 = r0.f5252b
            if (r11 == r9) goto L_0x035e
            r0 = r16
            int r11 = r0.f5252b
            if (r11 >= r4) goto L_0x0131
            r0 = r16
            int r4 = r0.f5252b
        L_0x0131:
            r0 = r16
            int r11 = r0.f5252b
            if (r11 <= r5) goto L_0x035e
            r0 = r16
            int r5 = r0.f5252b
            r11 = r5
            r12 = r4
        L_0x013d:
            if (r6 != 0) goto L_0x0156
            r0 = r16
            int r5 = r0.f5253c
            r0 = r16
            int r4 = r0.f5254d
        L_0x0147:
            int r6 = r6 + 1
            int r7 = r13 + -1
            r10 = r5
            r13 = r7
            r7 = r4
            r5 = r11
            r4 = r12
            r26 = r3
            r3 = r2
            r2 = r26
            goto L_0x00f8
        L_0x0156:
            r0 = r16
            int r4 = r0.f5253c
            int r5 = jumio.p317nv.nfc.C5078ds.m3576a(r10, r4)
            r0 = r16
            int r4 = r0.f5254d
            int r4 = jumio.p317nv.nfc.C5078ds.m3576a(r7, r4)
            goto L_0x0147
        L_0x0167:
            int r11 = r15 + 1
            r15 = r11
            r17 = r4
            r18 = r5
            r12 = r1
            r19 = r7
            r20 = r10
            goto L_0x0080
        L_0x0175:
            if (r6 != 0) goto L_0x0180
            java.lang.Error r1 = new java.lang.Error
            java.lang.String r2 = "Image cannot have no precinct"
            r1.<init>(r2)
            throw r1
        L_0x0180:
            int r1 = r2 - r17
            int r1 = r1 / r19
            int r23 = r1 + 1
            int r1 = r3 - r18
            int r1 = r1 / r20
            int r24 = r1 + 1
            r0 = r27
            int[][] r1 = r0.f5307H
            r1 = r1[r21]
            r0 = r27
            int r2 = r0.f5324Y
            r1 = r1[r2]
            r0 = r27
            int[][] r2 = r0.f5310K
            r2 = r2[r21]
            r0 = r27
            int r3 = r0.f5324Y
            r2 = r2[r3]
            int r1 = r1 + r2
            int r1 = r1 + -1
            r0 = r27
            int[][] r2 = r0.f5317R
            r2 = r2[r21]
            r0 = r27
            int r3 = r0.f5324Y
            r2 = r2[r3]
            int r3 = r1 - r2
            r0 = r27
            jumio.nv.nfc.cg r1 = r0.f5266a
            jumio.nv.nfc.bq r1 = r1.f5408h
            r0 = r21
            java.lang.Object r1 = r1.mo46979d(r0)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r1.intValue()
            r2 = 0
            r0 = r27
            jumio.nv.nfc.cg r1 = r0.f5266a
            jumio.nv.nfc.br r1 = r1.f5418r
            r0 = r21
            java.lang.Object r1 = r1.mo46979d(r0)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x035b
            r1 = 1
            r10 = r1
        L_0x01dd:
            r4 = r32
            r1 = r3
        L_0x01e0:
            r0 = r33
            if (r4 >= r0) goto L_0x0358
            r0 = r27
            int[] r2 = r0.f5270e
            int r2 = r2.length
            if (r4 < r2) goto L_0x01ee
        L_0x01eb:
            int r4 = r4 + 1
            goto L_0x01e0
        L_0x01ee:
            r2 = 0
            r15 = r2
            r16 = r9
            r3 = r8
        L_0x01f3:
            r0 = r23
            if (r15 > r0) goto L_0x01eb
            r2 = 0
            r13 = r2
            r14 = r3
            r11 = r1
        L_0x01fb:
            r0 = r24
            if (r13 > r0) goto L_0x0345
            r3 = r30
        L_0x0201:
            r0 = r31
            if (r3 >= r0) goto L_0x0335
            r0 = r27
            int[] r1 = r0.f5270e
            r1 = r1[r4]
            if (r3 <= r1) goto L_0x0210
        L_0x020d:
            int r3 = r3 + 1
            goto L_0x0201
        L_0x0210:
            r1 = r22[r4]
            r1 = r1[r3]
            r0 = r27
            jumio.nv.nfc.cc r2 = r0.f5303D
            int r2 = r2.mo47060a(r4, r3)
            if (r1 >= r2) goto L_0x020d
            r0 = r27
            jumio.nv.nfc.cc r1 = r0.f5303D
            r2 = r22[r4]
            r2 = r2[r3]
            jumio.nv.nfc.bx r1 = r1.mo47068c(r4, r3, r2)
            int r2 = r1.f5251a
            if (r2 != r14) goto L_0x020d
            int r1 = r1.f5252b
            r0 = r16
            if (r1 != r0) goto L_0x020d
            r2 = r12
        L_0x0235:
            r0 = r29
            if (r2 >= r0) goto L_0x032b
            r1 = r28[r4]
            int r1 = r1.length
            if (r3 < r1) goto L_0x0241
        L_0x023e:
            int r2 = r2 + 1
            goto L_0x0235
        L_0x0241:
            r1 = r28[r4]
            r1 = r1[r3]
            if (r2 < r1) goto L_0x023e
            r0 = r27
            jumio.nv.nfc.df r1 = r0.f5306G
            int r25 = r1.mo47123e()
            if (r10 == 0) goto L_0x0268
            r0 = r27
            jumio.nv.nfc.cc r1 = r0.f5303D
            r5 = r22[r4]
            r5 = r5[r3]
            r0 = r27
            jumio.nv.nfc.bz[][][][][] r6 = r0.f5328ac
            r6 = r6[r4]
            r6 = r6[r3]
            r0 = r27
            int[] r7 = r0.f5308I
            r1.mo47063a(r2, r3, r4, r5, r6, r7)
        L_0x0268:
            r0 = r25
            if (r0 <= r11) goto L_0x02bd
            r0 = r27
            int r1 = r0.f5324Y
            r0 = r27
            int[][] r5 = r0.f5307H
            r5 = r5[r21]
            int r5 = r5.length
            int r5 = r5 + -1
            if (r1 >= r5) goto L_0x02bd
            r0 = r27
            int r1 = r0.f5324Y
            int r1 = r1 + 1
            r0 = r27
            r0.f5324Y = r1
            r0 = r27
            jumio.nv.nfc.df r1 = r0.f5306G
            r0 = r27
            int[][] r5 = r0.f5307H
            r5 = r5[r21]
            r0 = r27
            int r6 = r0.f5324Y
            r5 = r5[r6]
            r1.mo47121a(r5)
            r0 = r27
            jumio.nv.nfc.df r1 = r0.f5306G
            int r1 = r1.mo47123e()
            r0 = r27
            int[][] r5 = r0.f5310K
            r5 = r5[r21]
            r0 = r27
            int r6 = r0.f5324Y
            r5 = r5[r6]
            int r1 = r1 + r5
            int r1 = r1 + -1
            r0 = r27
            int[][] r5 = r0.f5317R
            r5 = r5[r21]
            r0 = r27
            int r6 = r0.f5324Y
            r5 = r5[r6]
            int r1 = r1 - r5
            r11 = r1
        L_0x02bd:
            r0 = r27
            jumio.nv.nfc.cc r1 = r0.f5303D
            r0 = r27
            int[] r5 = r0.f5308I
            r6 = r22[r4]
            r6 = r6[r3]
            boolean r1 = r1.mo47064a(r5, r6, r4, r3)
            if (r1 == 0) goto L_0x02d1
            r1 = 1
        L_0x02d0:
            return r1
        L_0x02d1:
            if (r10 != 0) goto L_0x02eb
            r0 = r27
            jumio.nv.nfc.cc r1 = r0.f5303D
            r5 = r22[r4]
            r5 = r5[r3]
            r0 = r27
            jumio.nv.nfc.bz[][][][][] r6 = r0.f5328ac
            r6 = r6[r4]
            r6 = r6[r3]
            r0 = r27
            int[] r7 = r0.f5308I
            boolean r1 = r1.mo47063a(r2, r3, r4, r5, r6, r7)
        L_0x02eb:
            if (r1 == 0) goto L_0x02ef
            r1 = 1
            goto L_0x02d0
        L_0x02ef:
            r0 = r27
            jumio.nv.nfc.df r1 = r0.f5306G
            int r1 = r1.mo47123e()
            int r1 = r1 - r25
            r0 = r27
            java.util.Vector r5 = r0.f5318S
            java.lang.Integer r6 = new java.lang.Integer
            r6.<init>(r1)
            r5.addElement(r6)
            r0 = r27
            jumio.nv.nfc.cc r1 = r0.f5303D
            r5 = r22[r4]
            r5 = r5[r3]
            r0 = r27
            jumio.nv.nfc.bz[][][][][] r6 = r0.f5328ac
            r6 = r6[r4]
            r6 = r6[r3]
            r0 = r27
            int[] r7 = r0.f5308I
            boolean r1 = r1.mo47067b(r2, r3, r4, r5, r6, r7)
            r0 = r27
            jumio.nv.nfc.df r5 = r0.f5306G
            int r5 = r5.mo47123e()
            int r5 = r5 - r25
            if (r1 == 0) goto L_0x023e
            r1 = 1
            goto L_0x02d0
        L_0x032b:
            r1 = r22[r4]
            r2 = r1[r3]
            int r2 = r2 + 1
            r1[r3] = r2
            goto L_0x020d
        L_0x0335:
            r0 = r24
            if (r13 == r0) goto L_0x0343
            int r1 = r13 * r20
            int r1 = r1 + r18
        L_0x033d:
            int r2 = r13 + 1
            r13 = r2
            r14 = r1
            goto L_0x01fb
        L_0x0343:
            r1 = r8
            goto L_0x033d
        L_0x0345:
            r0 = r23
            if (r15 == r0) goto L_0x0356
            int r1 = r15 * r19
            int r1 = r1 + r17
        L_0x034d:
            int r2 = r15 + 1
            r15 = r2
            r16 = r1
            r3 = r14
            r1 = r11
            goto L_0x01f3
        L_0x0356:
            r1 = r9
            goto L_0x034d
        L_0x0358:
            r1 = 0
            goto L_0x02d0
        L_0x035b:
            r10 = r2
            goto L_0x01dd
        L_0x035e:
            r11 = r5
            r12 = r4
            goto L_0x013d
        L_0x0362:
            r26 = r3
            r3 = r2
            r2 = r26
            goto L_0x0121
        L_0x0369:
            r26 = r5
            r5 = r1
            r1 = r26
            goto L_0x00a0
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C5033ca.m3358e(int[][], int, int, int, int, int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x01f7  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0218  */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m3359g(int r23) throws java.io.IOException {
        /*
            r22 = this;
            java.util.Vector r2 = new java.util.Vector
            r2.<init>()
            r0 = r22
            r0.f5318S = r2
            r0 = r22
            jumio.nv.nfc.cg r2 = r0.f5266a
            jumio.nv.nfc.bq r2 = r2.f5408h
            r0 = r23
            java.lang.Object r2 = r2.mo46979d(r0)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r5 = r2.intValue()
            r0 = r22
            jumio.nv.nfc.cg r2 = r0.f5266a
            jumio.nv.nfc.br r2 = r2.f5418r
            r0 = r23
            java.lang.Object r2 = r2.mo46979d(r0)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x00d6
            r0 = r22
            jumio.nv.nfc.cb r2 = r0.f5291z
            r0 = r23
            java.io.ByteArrayInputStream r8 = r2.mo47046d(r0)
            r0 = r22
            jumio.nv.nfc.cc r2 = r0.f5303D
            r0 = r22
            int r3 = r0.f5271f
            r0 = r22
            int[] r4 = r0.f5270e
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r6 = r0.f5328ac
            r7 = 1
            jumio.nv.nfc.bz[][][][][] r2 = r2.mo47065a(r3, r4, r5, r6, r7, r8)
            r0 = r22
            r0.f5328ac = r2
        L_0x0052:
            r0 = r22
            jumio.nv.nfc.cg r2 = r0.f5266a
            jumio.nv.nfc.br r2 = r2.f5412l
            r0 = r23
            java.lang.Object r2 = r2.mo46979d(r0)
            int[][] r2 = (int[][]) r2
            int[][] r2 = (int[][]) r2
            if (r2 != 0) goto L_0x00f2
            r3 = 1
            r4 = r3
        L_0x0066:
            r3 = 6
            int[] r3 = new int[]{r4, r3}
            java.lang.Class r6 = java.lang.Integer.TYPE
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r6, r3)
            int[][] r3 = (int[][]) r3
            r6 = 0
            r7 = 0
            r7 = r3[r7]
            r8 = 1
            r9 = 0
            r7[r8] = r9
            if (r2 != 0) goto L_0x00f6
            r7 = r3[r6]
            r8 = 0
            r0 = r22
            jumio.nv.nfc.cg r2 = r0.f5266a
            jumio.nv.nfc.bq r2 = r2.f5409i
            r0 = r23
            java.lang.Object r2 = r2.mo46979d(r0)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r7[r8] = r2
            r2 = r3[r6]
            r7 = 1
            r2[r7] = r5
            r2 = r3[r6]
            r7 = 2
            r8 = 0
            r2[r7] = r8
            r2 = r3[r6]
            r7 = 3
            r0 = r22
            jumio.nv.nfc.cg r8 = r0.f5266a
            jumio.nv.nfc.bq r8 = r8.f5407g
            r0 = r23
            int r8 = r8.mo46969b(r0)
            int r8 = r8 + 1
            r2[r7] = r8
            r2 = r3[r6]
            r7 = 4
            r8 = 0
            r2[r7] = r8
            r2 = r3[r6]
            r6 = 5
            r0 = r22
            int r7 = r0.f5271f
            r2[r6] = r7
        L_0x00c1:
            r0 = r22
            boolean r2 = r0.f5319T     // Catch:{ EOFException -> 0x04f6 }
            if (r2 == 0) goto L_0x00cd
            r0 = r22
            int[][] r2 = r0.f5307H     // Catch:{ EOFException -> 0x04f6 }
            if (r2 == 0) goto L_0x00d5
        L_0x00cd:
            r0 = r22
            int[][] r2 = r0.f5307H     // Catch:{ EOFException -> 0x04f6 }
            r2 = r2[r23]     // Catch:{ EOFException -> 0x04f6 }
            if (r2 != 0) goto L_0x0138
        L_0x00d5:
            return
        L_0x00d6:
            r0 = r22
            jumio.nv.nfc.cc r2 = r0.f5303D
            r0 = r22
            int r3 = r0.f5271f
            r0 = r22
            int[] r4 = r0.f5270e
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r6 = r0.f5328ac
            r7 = 0
            r8 = 0
            jumio.nv.nfc.bz[][][][][] r2 = r2.mo47065a(r3, r4, r5, r6, r7, r8)
            r0 = r22
            r0.f5328ac = r2
            goto L_0x0052
        L_0x00f2:
            int r3 = r2.length
            r4 = r3
            goto L_0x0066
        L_0x00f6:
            r6 = 0
        L_0x00f7:
            if (r6 >= r4) goto L_0x00c1
            r7 = r3[r6]
            r8 = 0
            r9 = r2[r6]
            r10 = 5
            r9 = r9[r10]
            r7[r8] = r9
            r7 = r3[r6]
            r8 = 1
            r9 = r2[r6]
            r10 = 2
            r9 = r9[r10]
            r7[r8] = r9
            r7 = r3[r6]
            r8 = 2
            r9 = r2[r6]
            r10 = 0
            r9 = r9[r10]
            r7[r8] = r9
            r7 = r3[r6]
            r8 = 3
            r9 = r2[r6]
            r10 = 3
            r9 = r9[r10]
            r7[r8] = r9
            r7 = r3[r6]
            r8 = 4
            r9 = r2[r6]
            r10 = 1
            r9 = r9[r10]
            r7[r8] = r9
            r7 = r3[r6]
            r8 = 5
            r9 = r2[r6]
            r10 = 4
            r9 = r9[r10]
            r7[r8] = r9
            int r6 = r6 + 1
            goto L_0x00f7
        L_0x0138:
            r0 = r22
            jumio.nv.nfc.df r2 = r0.f5306G     // Catch:{ EOFException -> 0x04f6 }
            r0 = r22
            int[][] r6 = r0.f5307H     // Catch:{ EOFException -> 0x04f6 }
            r6 = r6[r23]     // Catch:{ EOFException -> 0x04f6 }
            r7 = 0
            r6 = r6[r7]     // Catch:{ EOFException -> 0x04f6 }
            r2.mo47121a(r6)     // Catch:{ EOFException -> 0x04f6 }
            r2 = 0
            r0 = r22
            r0.f5324Y = r2
            r8 = 0
            r0 = r22
            int[] r2 = r0.f5308I
            r14 = r2[r23]
            r0 = r22
            int r2 = r0.f5271f
            int[][] r7 = new int[r2][]
            r2 = 0
            r6 = r2
        L_0x015c:
            r0 = r22
            int r2 = r0.f5271f
            if (r6 >= r2) goto L_0x017e
            r0 = r22
            jumio.nv.nfc.cg r2 = r0.f5266a
            jumio.nv.nfc.bq r2 = r2.f5407g
            r0 = r23
            java.lang.Object r2 = r2.mo46970a(r0, r6)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            int r2 = r2 + 1
            int[] r2 = new int[r2]
            r7[r6] = r2
            int r2 = r6 + 1
            r6 = r2
            goto L_0x015c
        L_0x017e:
            r2 = 0
            r13 = r2
            r2 = r8
        L_0x0181:
            if (r13 >= r4) goto L_0x01f1
            r2 = r3[r13]     // Catch:{ EOFException -> 0x01ad }
            r6 = 1
            r8 = r2[r6]     // Catch:{ EOFException -> 0x01ad }
            r2 = r3[r13]     // Catch:{ EOFException -> 0x01ad }
            r6 = 2
            r9 = r2[r6]     // Catch:{ EOFException -> 0x01ad }
            r2 = r3[r13]     // Catch:{ EOFException -> 0x01ad }
            r6 = 3
            r10 = r2[r6]     // Catch:{ EOFException -> 0x01ad }
            r2 = r3[r13]     // Catch:{ EOFException -> 0x01ad }
            r6 = 4
            r11 = r2[r6]     // Catch:{ EOFException -> 0x01ad }
            r2 = r3[r13]     // Catch:{ EOFException -> 0x01ad }
            r6 = 5
            r12 = r2[r6]     // Catch:{ EOFException -> 0x01ad }
            r2 = r3[r13]     // Catch:{ EOFException -> 0x01ad }
            r6 = 0
            r2 = r2[r6]     // Catch:{ EOFException -> 0x01ad }
            switch(r2) {
                case 0: goto L_0x01af;
                case 1: goto L_0x01bd;
                case 2: goto L_0x01c4;
                case 3: goto L_0x01cb;
                case 4: goto L_0x01d2;
                default: goto L_0x01a4;
            }     // Catch:{ EOFException -> 0x01ad }
        L_0x01a4:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ EOFException -> 0x01ad }
            java.lang.String r3 = "Not recognized progression type"
            r2.<init>(r3)     // Catch:{ EOFException -> 0x01ad }
            throw r2     // Catch:{ EOFException -> 0x01ad }
        L_0x01ad:
            r2 = move-exception
            throw r2
        L_0x01af:
            r6 = r22
            boolean r2 = r6.m3354a(r7, r8, r9, r10, r11, r12)     // Catch:{ EOFException -> 0x01ad }
        L_0x01b5:
            if (r11 >= r12) goto L_0x01e9
            int r6 = r7.length     // Catch:{ EOFException -> 0x01ad }
            if (r11 < r6) goto L_0x01d9
        L_0x01ba:
            int r11 = r11 + 1
            goto L_0x01b5
        L_0x01bd:
            r6 = r22
            boolean r2 = r6.m3355b(r7, r8, r9, r10, r11, r12)     // Catch:{ EOFException -> 0x01ad }
            goto L_0x01b5
        L_0x01c4:
            r6 = r22
            boolean r2 = r6.m3356c(r7, r8, r9, r10, r11, r12)     // Catch:{ EOFException -> 0x01ad }
            goto L_0x01b5
        L_0x01cb:
            r6 = r22
            boolean r2 = r6.m3357d(r7, r8, r9, r10, r11, r12)     // Catch:{ EOFException -> 0x01ad }
            goto L_0x01b5
        L_0x01d2:
            r6 = r22
            boolean r2 = r6.m3358e(r7, r8, r9, r10, r11, r12)     // Catch:{ EOFException -> 0x01ad }
            goto L_0x01b5
        L_0x01d9:
            r6 = r9
        L_0x01da:
            if (r6 >= r10) goto L_0x01ba
            r15 = r7[r11]     // Catch:{ EOFException -> 0x01ad }
            int r15 = r15.length     // Catch:{ EOFException -> 0x01ad }
            if (r6 < r15) goto L_0x01e4
        L_0x01e1:
            int r6 = r6 + 1
            goto L_0x01da
        L_0x01e4:
            r15 = r7[r11]     // Catch:{ EOFException -> 0x01ad }
            r15[r6] = r8     // Catch:{ EOFException -> 0x01ad }
            goto L_0x01e1
        L_0x01e9:
            if (r2 != 0) goto L_0x01f1
            r0 = r22
            boolean r6 = r0.f5330ae     // Catch:{ EOFException -> 0x01ad }
            if (r6 == 0) goto L_0x0213
        L_0x01f1:
            r0 = r22
            boolean r3 = r0.f5319T
            if (r3 == 0) goto L_0x0218
            r0 = r22
            int r3 = r0.f5264B
            r0 = r22
            int[] r4 = r0.f5308I
            r4 = r4[r23]
            int r4 = r14 - r4
            int r3 = r3 + r4
            r0 = r22
            r0.f5264B = r3
            if (r2 == 0) goto L_0x00d5
            r0 = r22
            int[] r2 = r0.f5308I
            r3 = 0
            r2[r23] = r3
            goto L_0x00d5
        L_0x0213:
            int r6 = r13 + 1
            r13 = r6
            goto L_0x0181
        L_0x0218:
            r0 = r22
            int[] r2 = r0.f5308I
            r2 = r2[r23]
            r0 = r22
            int[] r3 = r0.f5311L
            r3 = r3[r23]
            r0 = r22
            int[] r4 = r0.f5312M
            r4 = r4[r23]
            int r3 = r3 - r4
            if (r2 >= r3) goto L_0x04b5
            r3 = 0
            r0 = r22
            java.util.Vector r2 = r0.f5318S
            int r2 = r2.size()
            int[] r15 = new int[r2]
            r0 = r22
            java.util.Vector r2 = r0.f5318S
            int r2 = r2.size()
            int r2 = r2 + -1
            r4 = r2
        L_0x0243:
            if (r4 < 0) goto L_0x0259
            r0 = r22
            java.util.Vector r2 = r0.f5318S
            java.lang.Object r2 = r2.elementAt(r4)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r15[r4] = r2
            int r2 = r4 + -1
            r4 = r2
            goto L_0x0243
        L_0x0259:
            r4 = 0
            r2 = 0
            r10 = r2
            r2 = r3
            r3 = r4
        L_0x025e:
            if (r10 >= r5) goto L_0x00d5
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r4 = r0.f5328ac
            if (r4 != 0) goto L_0x026a
        L_0x0266:
            int r4 = r10 + 1
            r10 = r4
            goto L_0x025e
        L_0x026a:
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r4 = r0.f5328ac
            int r0 = r4.length
            r16 = r0
            r9 = 0
            r4 = 0
            r6 = r4
        L_0x0274:
            r0 = r16
            if (r6 >= r0) goto L_0x0294
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r4 = r0.f5328ac
            r4 = r4[r6]
            if (r4 == 0) goto L_0x04f9
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r4 = r0.f5328ac
            r4 = r4[r6]
            int r4 = r4.length
            if (r4 <= r9) goto L_0x04f9
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r4 = r0.f5328ac
            r4 = r4[r6]
            int r4 = r4.length
        L_0x0290:
            int r6 = r6 + 1
            r9 = r4
            goto L_0x0274
        L_0x0294:
            r4 = 0
            r11 = r4
            r4 = r3
            r3 = r2
        L_0x0298:
            if (r11 >= r9) goto L_0x04ff
            r8 = 0
            r2 = 0
            r6 = r2
        L_0x029d:
            r0 = r16
            if (r6 >= r0) goto L_0x02cb
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r2 = r0.f5328ac
            r2 = r2[r6]
            if (r2 == 0) goto L_0x04fc
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r2 = r0.f5328ac
            r2 = r2[r6]
            r2 = r2[r11]
            if (r2 == 0) goto L_0x04fc
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r2 = r0.f5328ac
            r2 = r2[r6]
            r2 = r2[r11]
            int r2 = r2.length
            if (r2 <= r8) goto L_0x04fc
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r2 = r0.f5328ac
            r2 = r2[r6]
            r2 = r2[r11]
            int r2 = r2.length
        L_0x02c7:
            int r6 = r6 + 1
            r8 = r2
            goto L_0x029d
        L_0x02cb:
            r2 = 0
            r12 = r2
            r2 = r3
            r3 = r4
        L_0x02cf:
            if (r12 >= r8) goto L_0x04ae
            if (r11 != 0) goto L_0x02d9
            if (r12 == 0) goto L_0x02d9
        L_0x02d5:
            int r4 = r12 + 1
            r12 = r4
            goto L_0x02cf
        L_0x02d9:
            if (r11 == 0) goto L_0x02dd
            if (r12 == 0) goto L_0x02d5
        L_0x02dd:
            r7 = 0
            r4 = 0
            r6 = r4
        L_0x02e0:
            r0 = r16
            if (r6 >= r0) goto L_0x031e
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r4 = r0.f5328ac
            r4 = r4[r6]
            if (r4 == 0) goto L_0x0503
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r4 = r0.f5328ac
            r4 = r4[r6]
            r4 = r4[r11]
            if (r4 == 0) goto L_0x0503
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r4 = r0.f5328ac
            r4 = r4[r6]
            r4 = r4[r11]
            r4 = r4[r12]
            if (r4 == 0) goto L_0x0503
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r4 = r0.f5328ac
            r4 = r4[r6]
            r4 = r4[r11]
            r4 = r4[r12]
            int r4 = r4.length
            if (r4 <= r7) goto L_0x0503
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r4 = r0.f5328ac
            r4 = r4[r6]
            r4 = r4[r11]
            r4 = r4[r12]
            int r4 = r4.length
        L_0x031a:
            int r6 = r6 + 1
            r7 = r4
            goto L_0x02e0
        L_0x031e:
            r4 = 0
            r14 = r4
            r6 = r3
            r3 = r2
        L_0x0322:
            if (r14 >= r7) goto L_0x0509
            r4 = 0
            r2 = 0
            r13 = r2
        L_0x0327:
            r0 = r16
            if (r13 >= r0) goto L_0x0378
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r2 = r0.f5328ac
            r2 = r2[r13]
            if (r2 == 0) goto L_0x0506
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r2 = r0.f5328ac
            r2 = r2[r13]
            r2 = r2[r11]
            if (r2 == 0) goto L_0x0506
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r2 = r0.f5328ac
            r2 = r2[r13]
            r2 = r2[r11]
            r2 = r2[r12]
            if (r2 == 0) goto L_0x0506
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r2 = r0.f5328ac
            r2 = r2[r13]
            r2 = r2[r11]
            r2 = r2[r12]
            r2 = r2[r14]
            if (r2 == 0) goto L_0x0506
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r2 = r0.f5328ac
            r2 = r2[r13]
            r2 = r2[r11]
            r2 = r2[r12]
            r2 = r2[r14]
            int r2 = r2.length
            if (r2 <= r4) goto L_0x0506
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r2 = r0.f5328ac
            r2 = r2[r13]
            r2 = r2[r11]
            r2 = r2[r12]
            r2 = r2[r14]
            int r2 = r2.length
        L_0x0373:
            int r4 = r13 + 1
            r13 = r4
            r4 = r2
            goto L_0x0327
        L_0x0378:
            r2 = 0
            r13 = r2
        L_0x037a:
            if (r13 >= r4) goto L_0x04a9
            r2 = 0
            r21 = r2
            r2 = r3
            r3 = r6
            r6 = r21
        L_0x0383:
            r0 = r16
            if (r6 >= r0) goto L_0x04a2
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r0 = r0.f5328ac
            r17 = r0
            r17 = r17[r6]
            if (r17 == 0) goto L_0x03cd
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r0 = r0.f5328ac
            r17 = r0
            r17 = r17[r6]
            r17 = r17[r11]
            if (r17 == 0) goto L_0x03cd
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r0 = r0.f5328ac
            r17 = r0
            r17 = r17[r6]
            r17 = r17[r11]
            r17 = r17[r12]
            if (r17 == 0) goto L_0x03cd
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r0 = r0.f5328ac
            r17 = r0
            r17 = r17[r6]
            r17 = r17[r11]
            r17 = r17[r12]
            r17 = r17[r14]
            if (r17 == 0) goto L_0x03cd
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r0 = r0.f5328ac
            r17 = r0
            r17 = r17[r6]
            r17 = r17[r11]
            r17 = r17[r12]
            r17 = r17[r14]
            r17 = r17[r13]
            if (r17 != 0) goto L_0x03d0
        L_0x03cd:
            int r6 = r6 + 1
            goto L_0x0383
        L_0x03d0:
            r0 = r22
            jumio.nv.nfc.bz[][][][][] r0 = r0.f5328ac
            r17 = r0
            r17 = r17[r6]
            r17 = r17[r11]
            r17 = r17[r12]
            r17 = r17[r14]
            r17 = r17[r13]
            if (r3 != 0) goto L_0x03fc
            r0 = r22
            int[] r0 = r0.f5308I
            r18 = r0
            r18 = r18[r23]
            r0 = r17
            int[] r0 = r0.f5302k
            r19 = r0
            r19 = r19[r10]
            r19 = r15[r19]
            r0 = r18
            r1 = r19
            if (r0 >= r1) goto L_0x0449
            r2 = 1
            r3 = 1
        L_0x03fc:
            r0 = r17
            int[] r0 = r0.f5297f
            r18 = r0
            r18 = r18[r10]
            if (r18 == 0) goto L_0x03cd
            r0 = r17
            int[] r0 = r0.f5297f
            r18 = r0
            r18 = r18[r10]
            r0 = r22
            int[] r0 = r0.f5308I
            r19 = r0
            r19 = r19[r23]
            r0 = r18
            r1 = r19
            if (r0 >= r1) goto L_0x0487
            if (r3 != 0) goto L_0x0487
            r0 = r22
            int[] r0 = r0.f5308I
            r18 = r0
            r19 = r18[r23]
            r0 = r17
            int[] r0 = r0.f5297f
            r20 = r0
            r20 = r20[r10]
            int r19 = r19 - r20
            r18[r23] = r19
            r0 = r22
            int r0 = r0.f5264B
            r18 = r0
            r0 = r17
            int[] r0 = r0.f5297f
            r17 = r0
            r17 = r17[r10]
            int r17 = r17 + r18
            r0 = r17
            r1 = r22
            r1.f5264B = r0
            goto L_0x03cd
        L_0x0449:
            if (r2 != 0) goto L_0x03fc
            r0 = r22
            int[] r0 = r0.f5308I
            r18 = r0
            r19 = r18[r23]
            r0 = r17
            int[] r0 = r0.f5302k
            r20 = r0
            r20 = r20[r10]
            r20 = r15[r20]
            int r19 = r19 - r20
            r18[r23] = r19
            r0 = r22
            int r0 = r0.f5264B
            r18 = r0
            r0 = r17
            int[] r0 = r0.f5302k
            r19 = r0
            r19 = r19[r10]
            r19 = r15[r19]
            int r18 = r18 + r19
            r0 = r18
            r1 = r22
            r1.f5264B = r0
            r0 = r17
            int[] r0 = r0.f5302k
            r18 = r0
            r18 = r18[r10]
            r19 = 0
            r15[r18] = r19
            goto L_0x03fc
        L_0x0487:
            r0 = r17
            int[] r3 = r0.f5297f
            r0 = r17
            int[] r0 = r0.f5298g
            r18 = r0
            r0 = r17
            int[] r0 = r0.f5299h
            r17 = r0
            r19 = 0
            r17[r10] = r19
            r18[r10] = r19
            r3[r10] = r19
            r3 = 1
            goto L_0x03cd
        L_0x04a2:
            int r6 = r13 + 1
            r13 = r6
            r6 = r3
            r3 = r2
            goto L_0x037a
        L_0x04a9:
            int r2 = r14 + 1
            r14 = r2
            goto L_0x0322
        L_0x04ae:
            int r4 = r11 + 1
            r11 = r4
            r4 = r3
            r3 = r2
            goto L_0x0298
        L_0x04b5:
            r0 = r22
            int r2 = r0.f5264B
            r0 = r22
            int[] r3 = r0.f5311L
            r3 = r3[r23]
            r0 = r22
            int[] r4 = r0.f5312M
            r4 = r4[r23]
            int r3 = r3 - r4
            int r2 = r2 + r3
            r0 = r22
            r0.f5264B = r2
            int r2 = r22.mo47017e()
            int r2 = r2 + -1
            r0 = r23
            if (r0 >= r2) goto L_0x00d5
            r0 = r22
            int[] r2 = r0.f5308I
            int r3 = r23 + 1
            r4 = r2[r3]
            r0 = r22
            int[] r5 = r0.f5308I
            r5 = r5[r23]
            r0 = r22
            int[] r6 = r0.f5311L
            r6 = r6[r23]
            r0 = r22
            int[] r7 = r0.f5312M
            r7 = r7[r23]
            int r6 = r6 - r7
            int r5 = r5 - r6
            int r4 = r4 + r5
            r2[r3] = r4
            goto L_0x00d5
        L_0x04f6:
            r2 = move-exception
            goto L_0x00d5
        L_0x04f9:
            r4 = r9
            goto L_0x0290
        L_0x04fc:
            r2 = r8
            goto L_0x02c7
        L_0x04ff:
            r2 = r3
            r3 = r4
            goto L_0x0266
        L_0x0503:
            r4 = r7
            goto L_0x031a
        L_0x0506:
            r2 = r4
            goto L_0x0373
        L_0x0509:
            r2 = r3
            r3 = r6
            goto L_0x02d5
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C5033ca.m3359g(int):void");
    }

    /* renamed from: f */
    public void mo47028f(int i, int i2) {
        int i3 = 0;
        if (i < 0 || i2 < 0 || i >= this.f5286u || i2 >= this.f5287v) {
            throw new IllegalArgumentException();
        }
        int i4 = (this.f5286u * i2) + i;
        if (i4 == 0) {
            this.f5264B = this.f5316Q;
            if (!this.f5319T) {
                this.f5264B += 2;
            }
            for (int i5 = 0; i5 < this.f5288w; i5++) {
                this.f5308I[i5] = this.f5309J[i5];
            }
        }
        this.f5289x = i;
        this.f5290y = i2;
        int i6 = i == 0 ? this.f5276k : this.f5278m + (this.f5284s * i);
        int i7 = i2 == 0 ? this.f5277l : this.f5279n + (this.f5285t * i2);
        for (int i8 = this.f5271f - 1; i8 >= 0; i8--) {
            this.f5282q[i8] = ((this.f5291z.mo47040b(i8) + i6) - 1) / this.f5291z.mo47040b(i8);
            this.f5283r[i8] = ((this.f5291z.mo47043c(i8) + i7) - 1) / this.f5291z.mo47043c(i8);
            this.f5280o[i8] = (((this.f5278m + (this.f5284s * i)) + this.f5291z.mo47040b(i8)) - 1) / this.f5291z.mo47040b(i8);
            this.f5281p[i8] = (((this.f5279n + (this.f5285t * i2)) + this.f5291z.mo47043c(i8)) - 1) / this.f5291z.mo47043c(i8);
        }
        this.f5273h = new C5093eg[this.f5271f];
        this.f5270e = new int[this.f5271f];
        this.f5267b = new boolean[this.f5271f];
        this.f5269d = new C5073dn[this.f5271f];
        this.f5268c = new int[this.f5271f];
        while (true) {
            int i9 = i3;
            if (i9 < this.f5271f) {
                this.f5267b[i9] = this.f5266a.f5403c.mo47126c(i4, i9);
                this.f5269d[i9] = (C5073dn) this.f5266a.f5404d.mo46970a(i4, i9);
                this.f5268c[i9] = ((Integer) this.f5266a.f5405e.mo46970a(i4, i9)).intValue();
                this.f5270e[i9] = ((Integer) this.f5266a.f5407g.mo46970a(i4, i9)).intValue();
                this.f5273h[i9] = new C5093eg(mo47001a(i4, i9, this.f5270e[i9]), mo47007b(i4, i9, this.f5270e[i9]), mo47011c(i9, this.f5270e[i9]), mo47015d(i9, this.f5270e[i9]), this.f5270e[i9], this.f5266a.f5406f.mo47161c(i4, i9), this.f5266a.f5406f.mo47162d(i4, i9));
                mo47003a(i9, this.f5273h[i9]);
                i3 = i9 + 1;
            } else {
                try {
                    m3359g(i4);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new Error("IO Error when reading tile " + i + " x " + i2);
                }
            }
        }
    }

    /* renamed from: a */
    public C5046cn mo47027a(int i, int i2, int i3, C5093eg egVar, int i4, int i5, C5046cn cnVar) {
        int i6;
        int i7;
        int i8;
        int i9;
        int d = mo47013d();
        int i10 = egVar.f5538d;
        int i11 = egVar.f5541g;
        int intValue = ((Integer) this.f5266a.f5408h.mo46979d(d)).intValue();
        int intValue2 = ((Integer) this.f5266a.f5410j.mo46970a(d, i)).intValue();
        if (i5 < 0) {
            i5 = (intValue - i4) + 1;
        }
        if (this.f5329ad != -1 && i4 + i5 > this.f5329ad) {
            i5 = this.f5329ad - i4;
        }
        if (i10 > (mo47019e(d, i).f5538d + this.f5272g) - this.f5266a.f5407g.mo46967a()) {
            throw new Error("JJ2000 error: requesting a code-block disallowed by the '-res' option.");
        }
        try {
            C5031bz bzVar = this.f5328ac[i][i10][i11][i2][i3];
            if (i4 < 1 || i4 > intValue || (i4 + i5) - 1 > intValue) {
                throw new IllegalArgumentException();
            }
            if (cnVar == null) {
                cnVar = new C5046cn();
            }
            cnVar.f5422b = i2;
            cnVar.f5421a = i3;
            cnVar.f5438k = 0;
            cnVar.f5436i = 0;
            cnVar.f5440m = 0;
            if (bzVar == null) {
                cnVar.f5423c = 0;
                cnVar.f5437j = false;
                cnVar.f5433f = 0;
                cnVar.f5432e = 0;
                cnVar.f5435h = 0;
                cnVar.f5434g = 0;
            } else {
                cnVar.f5423c = bzVar.f5296e;
                cnVar.f5432e = bzVar.f5292a;
                cnVar.f5433f = bzVar.f5293b;
                cnVar.f5434g = bzVar.f5294c;
                cnVar.f5435h = bzVar.f5295d;
                cnVar.f5439l = 0;
                int i12 = 0;
                while (i12 < bzVar.f5297f.length && bzVar.f5297f[i12] == 0) {
                    cnVar.f5439l += bzVar.f5299h[i12];
                    i12++;
                }
                for (int i13 = i4 - 1; i13 < (i4 + i5) - 1; i13++) {
                    cnVar.f5438k++;
                    cnVar.f5436i += bzVar.f5297f[i13];
                    cnVar.f5440m += bzVar.f5299h[i13];
                }
                if ((intValue2 & 4) != 0) {
                    i6 = cnVar.f5440m - cnVar.f5439l;
                } else if ((intValue2 & 1) == 0) {
                    i6 = 1;
                } else if (cnVar.f5440m <= 10) {
                    i6 = 1;
                } else {
                    i6 = 1;
                    for (int i14 = cnVar.f5439l; i14 < cnVar.f5440m; i14++) {
                        if (i14 >= 9) {
                            int i15 = (i14 + 2) % 3;
                            if (i15 == 1 || i15 == 2) {
                                i6++;
                            }
                        }
                    }
                }
                if (cnVar.f5424d == null || cnVar.f5424d.length < cnVar.f5436i) {
                    cnVar.f5424d = new byte[cnVar.f5436i];
                }
                if (i6 > 1 && (cnVar.f5441n == null || cnVar.f5441n.length < i6)) {
                    cnVar.f5441n = new int[i6];
                } else if (i6 > 1 && (intValue2 & 5) == 1) {
                    C5076dq.m3563a(cnVar.f5441n, 0);
                }
                int i16 = cnVar.f5439l;
                int i17 = 0;
                int i18 = i4 - 1;
                int i19 = cnVar.f5439l;
                int i20 = -1;
                int i21 = i16;
                while (i18 < (i4 + i5) - 1) {
                    int i22 = i19 + bzVar.f5299h[i18];
                    if (bzVar.f5297f[i18] != 0) {
                        try {
                            this.f5306G.mo47121a(bzVar.f5298g[i18]);
                            this.f5306G.mo47122a(cnVar.f5424d, i20 + 1, bzVar.f5297f[i18]);
                            int i23 = i20 + bzVar.f5297f[i18];
                            if (i6 == 1) {
                                i20 = i23;
                            } else if ((intValue2 & 4) != 0) {
                                int i24 = 0;
                                while (i21 < i22) {
                                    if (bzVar.f5301j[i18] != null) {
                                        i9 = i17 + 1;
                                        cnVar.f5441n[i17] = bzVar.f5301j[i18][i24];
                                    } else {
                                        i9 = i17 + 1;
                                        cnVar.f5441n[i17] = bzVar.f5297f[i18];
                                    }
                                    i24++;
                                    i21++;
                                    i17 = i9;
                                }
                                i20 = i23;
                            } else {
                                int i25 = i21;
                                int i26 = 0;
                                while (i25 < i22) {
                                    if (i25 < 9 || (i25 + 2) % 3 == 0) {
                                        i7 = i26;
                                        i8 = i17;
                                    } else if (bzVar.f5301j[i18] != null) {
                                        int[] iArr = cnVar.f5441n;
                                        int i27 = i17 + 1;
                                        i7 = i26 + 1;
                                        iArr[i17] = bzVar.f5301j[i18][i26] + iArr[i17];
                                        int[] iArr2 = bzVar.f5297f;
                                        iArr2[i18] = iArr2[i18] - bzVar.f5301j[i18][i7 - 1];
                                        i8 = i27;
                                    } else {
                                        int[] iArr3 = cnVar.f5441n;
                                        int i28 = i17 + 1;
                                        iArr3[i17] = iArr3[i17] + bzVar.f5297f[i18];
                                        bzVar.f5297f[i18] = 0;
                                        int i29 = i26;
                                        i8 = i28;
                                        i7 = i29;
                                    }
                                    i25++;
                                    i17 = i8;
                                    i26 = i7;
                                }
                                if (bzVar.f5301j[i18] == null || i26 >= bzVar.f5301j[i18].length) {
                                    if (i17 < i6) {
                                        int[] iArr4 = cnVar.f5441n;
                                        iArr4[i17] = iArr4[i17] + bzVar.f5297f[i18];
                                        bzVar.f5297f[i18] = 0;
                                    }
                                    i20 = i23;
                                    i21 = i25;
                                } else {
                                    int[] iArr5 = cnVar.f5441n;
                                    iArr5[i17] = iArr5[i17] + bzVar.f5301j[i18][i26];
                                    int[] iArr6 = bzVar.f5297f;
                                    iArr6[i18] = iArr6[i18] - bzVar.f5301j[i18][i26];
                                    i20 = i23;
                                    i21 = i25;
                                }
                            }
                        } catch (IOException e) {
                            throw new Error(e);
                        }
                    }
                    i18++;
                    i19 = i22;
                }
                if (i6 == 1 && cnVar.f5441n != null) {
                    cnVar.f5441n[0] = cnVar.f5436i;
                }
                int i30 = (i4 + i5) - 1;
                if (i30 < intValue - 1) {
                    for (int i31 = i30 + 1; i31 < intValue; i31++) {
                        if (bzVar.f5297f[i31] != 0) {
                            cnVar.f5437j = true;
                        }
                    }
                }
            }
            return cnVar;
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new IllegalArgumentException("Code-block (t:" + d + ", c:" + i + ", r:" + i10 + ", s:" + i11 + ", " + i2 + "x" + i3 + ") not found in codestream");
        } catch (NullPointerException e3) {
            throw new IllegalArgumentException("Code-block (t:" + d + ", c:" + i + ", r:" + i10 + ", s:" + i11 + ", " + i2 + "x" + i3 + ") not found in bit stream");
        }
    }
}

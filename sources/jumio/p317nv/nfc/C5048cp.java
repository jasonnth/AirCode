package jumio.p317nv.nfc;

import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar;

/* renamed from: jumio.nv.nfc.cp */
/* compiled from: MQDecoder */
public class C5048cp {

    /* renamed from: a */
    static final int[] f5444a = {22017, 13313, 6145, 2753, 1313, 545, 22017, 21505, 18433, 14337, 12289, 9217, 7169, 5633, 22017, 21505, 20737, 18433, 14337, 13313, 12289, 10241, 9217, 8705, 7169, 6145, 5633, 5121, 4609, 4353, 2753, 2497, 2209, 1313, 1089, 673, 545, 321, 273, 133, 73, 37, 21, 9, 5, 1, 22017};

    /* renamed from: b */
    static final int[] f5445b = {1, 2, 3, 4, 5, 38, 7, 8, 9, 10, 11, 12, 13, 29, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 45, 46};

    /* renamed from: c */
    static final int[] f5446c = {1, 6, 9, 12, 29, 33, 6, 14, 14, 14, 17, 18, 20, 21, 14, 14, 15, 16, 17, 18, 19, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 46};

    /* renamed from: d */
    static final int[] f5447d = {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    /* renamed from: e */
    C5043ck f5448e;

    /* renamed from: f */
    int[] f5449f;

    /* renamed from: g */
    int[] f5450g;

    /* renamed from: h */
    int f5451h;

    /* renamed from: i */
    int f5452i;

    /* renamed from: j */
    int f5453j;

    /* renamed from: k */
    int f5454k;

    /* renamed from: l */
    boolean f5455l;

    /* renamed from: m */
    final int[] f5456m;

    public C5048cp(C5043ck ckVar, int i, int[] iArr) {
        this.f5448e = ckVar;
        this.f5450g = new int[i];
        this.f5449f = new int[i];
        this.f5456m = iArr;
        m3449e();
        mo47088b();
    }

    /* renamed from: a */
    public final int mo47085a(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = this.f5450g[i];
        int i7 = f5444a[i6];
        this.f5453j -= i7;
        if ((this.f5451h >>> 16) >= this.f5453j) {
            int i8 = this.f5453j;
            this.f5451h -= i8 << 16;
            if (i8 < i7) {
                i3 = this.f5449f[i];
                this.f5450g[i] = f5445b[i6];
                if (this.f5452i == 0) {
                    m3448d();
                }
                i2 = i7 << 1;
                this.f5451h <<= 1;
                this.f5452i--;
            } else {
                int i9 = 1 - this.f5449f[i];
                if (f5447d[i6] == 1) {
                    this.f5449f[i] = 1 - this.f5449f[i];
                }
                this.f5450g[i] = f5446c[i6];
                int i10 = i7;
                do {
                    if (this.f5452i == 0) {
                        m3448d();
                    }
                    i10 <<= 1;
                    this.f5451h <<= 1;
                    this.f5452i--;
                } while (i10 < 32768);
                i2 = i10;
                i3 = i9;
            }
            this.f5453j = i2;
            return i3;
        } else if (this.f5453j >= 32768) {
            return this.f5449f[i];
        } else {
            int i11 = this.f5453j;
            if (i11 >= i7) {
                int i12 = this.f5449f[i];
                this.f5450g[i] = f5445b[i6];
                if (this.f5452i == 0) {
                    m3448d();
                }
                int i13 = i11 << 1;
                this.f5451h <<= 1;
                this.f5452i--;
                int i14 = i12;
                i4 = i13;
                i5 = i14;
            } else {
                int i15 = 1 - this.f5449f[i];
                if (f5447d[i6] == 1) {
                    this.f5449f[i] = 1 - this.f5449f[i];
                }
                this.f5450g[i] = f5446c[i6];
                do {
                    if (this.f5452i == 0) {
                        m3448d();
                    }
                    i11 <<= 1;
                    this.f5451h <<= 1;
                    this.f5452i--;
                } while (i11 < 32768);
                int i16 = i15;
                i4 = i11;
                i5 = i16;
            }
            this.f5453j = i4;
            return i5;
        }
    }

    /* renamed from: a */
    public boolean mo47087a() {
        if (this.f5454k != 255 && !this.f5455l) {
            return true;
        }
        if (this.f5452i != 0 && !this.f5455l) {
            return true;
        }
        if (this.f5452i == 1) {
            return false;
        }
        if (this.f5452i == 0) {
            if (!this.f5455l) {
                this.f5454k = this.f5448e.mo47080a() & 255;
                if (this.f5454k <= 143) {
                    return true;
                }
            }
            this.f5452i = 8;
        }
        int i = 32768 >> (this.f5452i - 1);
        this.f5453j -= i;
        if ((this.f5451h >>> 16) < this.f5453j) {
            return true;
        }
        this.f5451h -= this.f5453j << 16;
        this.f5453j = i;
        do {
            if (this.f5452i == 0) {
                m3448d();
            }
            this.f5453j <<= 1;
            this.f5451h <<= 1;
            this.f5452i--;
        } while (this.f5453j < 32768);
        return false;
    }

    /* renamed from: d */
    private void m3448d() {
        if (this.f5455l) {
            this.f5452i = 8;
        } else if (this.f5454k == 255) {
            this.f5454k = this.f5448e.mo47080a() & 255;
            if (this.f5454k > 143) {
                this.f5455l = true;
                this.f5452i = 8;
                return;
            }
            this.f5451h += 65024 - (this.f5454k << 9);
            this.f5452i = 7;
        } else {
            this.f5454k = this.f5448e.mo47080a() & 255;
            this.f5451h += ExploreBaseRangeSeekBar.ACTION_POINTER_INDEX_MASK - (this.f5454k << 8);
            this.f5452i = 8;
        }
    }

    /* renamed from: b */
    public final void mo47088b() {
        System.arraycopy(this.f5456m, 0, this.f5450g, 0, this.f5450g.length);
        C5076dq.m3563a(this.f5449f, 0);
    }

    /* renamed from: a */
    public final void mo47086a(byte[] bArr, int i, int i2) {
        this.f5448e.mo47081a(bArr, i, i2);
        m3449e();
    }

    /* renamed from: c */
    public C5043ck mo47089c() {
        return this.f5448e;
    }

    /* renamed from: e */
    private void m3449e() {
        this.f5455l = false;
        this.f5454k = this.f5448e.mo47080a() & 255;
        this.f5451h = (this.f5454k ^ 255) << 16;
        m3448d();
        this.f5451h <<= 7;
        this.f5452i -= 7;
        this.f5453j = 32768;
    }
}

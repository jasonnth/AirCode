package jumio.p317nv.nfc;

import java.io.IOException;

/* renamed from: jumio.nv.nfc.ce */
/* compiled from: TagTreeDecoder */
public class C5037ce {

    /* renamed from: a */
    protected int f5390a;

    /* renamed from: b */
    protected int f5391b;

    /* renamed from: c */
    protected int f5392c;

    /* renamed from: d */
    protected int[][] f5393d;

    /* renamed from: e */
    protected int[][] f5394e;

    public C5037ce(int i, int i2) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException();
        }
        this.f5390a = i2;
        this.f5391b = i;
        if (i2 != 0 && i != 0) {
            this.f5392c = 1;
            while (true) {
                if (i == 1 && i2 == 1) {
                    break;
                }
                i2 = (i2 + 1) >> 1;
                i = (i + 1) >> 1;
                this.f5392c++;
            }
        } else {
            this.f5392c = 0;
        }
        this.f5393d = new int[this.f5392c][];
        this.f5394e = new int[this.f5392c][];
        int i3 = this.f5390a;
        int i4 = this.f5391b;
        for (int i5 = 0; i5 < this.f5392c; i5++) {
            this.f5393d[i5] = new int[(i4 * i3)];
            C5076dq.m3563a(this.f5393d[i5], Integer.MAX_VALUE);
            this.f5394e[i5] = new int[(i4 * i3)];
            i3 = (i3 + 1) >> 1;
            i4 = (i4 + 1) >> 1;
        }
    }

    /* renamed from: a */
    public int mo47072a(int i, int i2, int i3, C5036cd cdVar) throws IOException {
        if (i >= this.f5391b || i2 >= this.f5390a || i3 < 0) {
            throw new IllegalArgumentException();
        }
        int i4 = this.f5392c - 1;
        int i5 = this.f5394e[i4][0];
        int i6 = i >> i4;
        int i7 = this.f5390a;
        while (true) {
            int i8 = (i6 * (((i7 + (1 << i4)) - 1) >> i4)) + (i2 >> i4);
            int i9 = this.f5394e[i4][i8];
            int i10 = this.f5393d[i4][i8];
            if (i9 >= i5) {
                i5 = i9;
            }
            while (true) {
                if (i3 > i5) {
                    if (i10 < i5) {
                        i5 = i3;
                        break;
                    } else if (cdVar.mo47069a() == 0) {
                        i5++;
                    } else {
                        int i11 = i5;
                        i5++;
                        i10 = i11;
                    }
                } else {
                    break;
                }
            }
            this.f5394e[i4][i8] = i5;
            this.f5393d[i4][i8] = i10;
            if (i4 <= 0) {
                return i10;
            }
            if (i5 >= i10) {
                i5 = i10;
            }
            i4--;
            i6 = i >> i4;
            i7 = this.f5390a;
        }
    }
}

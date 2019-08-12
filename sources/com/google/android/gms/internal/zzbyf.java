package com.google.android.gms.internal;

public final class zzbyf implements Cloneable {
    private static final zzbyg zzcwE = new zzbyg();
    private int mSize;
    private boolean zzcwF;
    private int[] zzcwG;
    private zzbyg[] zzcwH;

    zzbyf() {
        this(10);
    }

    zzbyf(int i) {
        this.zzcwF = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzcwG = new int[idealIntArraySize];
        this.zzcwH = new zzbyg[idealIntArraySize];
        this.mSize = 0;
    }

    private int idealByteArraySize(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            if (i <= (1 << i2) - 12) {
                return (1 << i2) - 12;
            }
        }
        return i;
    }

    private int idealIntArraySize(int i) {
        return idealByteArraySize(i * 4) / 4;
    }

    private boolean zza(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private boolean zza(zzbyg[] zzbygArr, zzbyg[] zzbygArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!zzbygArr[i2].equals(zzbygArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    private int zzrv(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzcwG[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbyf)) {
            return false;
        }
        zzbyf zzbyf = (zzbyf) obj;
        if (size() != zzbyf.size()) {
            return false;
        }
        return zza(this.zzcwG, zzbyf.zzcwG, this.mSize) && zza(this.zzcwH, zzbyf.zzcwH, this.mSize);
    }

    public int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzcwG[i2]) * 31) + this.zzcwH[i2].hashCode();
        }
        return i;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /* access modifiers changed from: 0000 */
    public int size() {
        return this.mSize;
    }

    /* access modifiers changed from: 0000 */
    public void zza(int i, zzbyg zzbyg) {
        int zzrv = zzrv(i);
        if (zzrv >= 0) {
            this.zzcwH[zzrv] = zzbyg;
            return;
        }
        int i2 = zzrv ^ -1;
        if (i2 >= this.mSize || this.zzcwH[i2] != zzcwE) {
            if (this.mSize >= this.zzcwG.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zzbyg[] zzbygArr = new zzbyg[idealIntArraySize];
                System.arraycopy(this.zzcwG, 0, iArr, 0, this.zzcwG.length);
                System.arraycopy(this.zzcwH, 0, zzbygArr, 0, this.zzcwH.length);
                this.zzcwG = iArr;
                this.zzcwH = zzbygArr;
            }
            if (this.mSize - i2 != 0) {
                System.arraycopy(this.zzcwG, i2, this.zzcwG, i2 + 1, this.mSize - i2);
                System.arraycopy(this.zzcwH, i2, this.zzcwH, i2 + 1, this.mSize - i2);
            }
            this.zzcwG[i2] = i;
            this.zzcwH[i2] = zzbyg;
            this.mSize++;
            return;
        }
        this.zzcwG[i2] = i;
        this.zzcwH[i2] = zzbyg;
    }

    /* renamed from: zzafr */
    public final zzbyf clone() {
        int size = size();
        zzbyf zzbyf = new zzbyf(size);
        System.arraycopy(this.zzcwG, 0, zzbyf.zzcwG, 0, size);
        for (int i = 0; i < size; i++) {
            if (this.zzcwH[i] != null) {
                zzbyf.zzcwH[i] = (zzbyg) this.zzcwH[i].clone();
            }
        }
        zzbyf.mSize = size;
        return zzbyf;
    }

    /* access modifiers changed from: 0000 */
    public zzbyg zzrt(int i) {
        int zzrv = zzrv(i);
        if (zzrv < 0 || this.zzcwH[zzrv] == zzcwE) {
            return null;
        }
        return this.zzcwH[zzrv];
    }

    /* access modifiers changed from: 0000 */
    public zzbyg zzru(int i) {
        return this.zzcwH[i];
    }
}

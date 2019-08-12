package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzbyj {
    protected volatile int zzcwL = -1;

    public static final <T extends zzbyj> T zza(T t, byte[] bArr) throws zzbyi {
        return zzb(t, bArr, 0, bArr.length);
    }

    public static final void zza(zzbyj zzbyj, byte[] bArr, int i, int i2) {
        try {
            zzbyc zzc = zzbyc.zzc(bArr, i, i2);
            zzbyj.zza(zzc);
            zzc.zzafo();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends zzbyj> T zzb(T t, byte[] bArr, int i, int i2) throws zzbyi {
        try {
            zzbyb zzb = zzbyb.zzb(bArr, i, i2);
            t.zzb(zzb);
            zzb.zzrc(0);
            return t;
        } catch (zzbyi e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public static final byte[] zzf(zzbyj zzbyj) {
        byte[] bArr = new byte[zzbyj.zzafB()];
        zza(zzbyj, bArr, 0, bArr.length);
        return bArr;
    }

    public String toString() {
        return zzbyk.zzg(this);
    }

    public void zza(zzbyc zzbyc) throws IOException {
    }

    public int zzafA() {
        if (this.zzcwL < 0) {
            zzafB();
        }
        return this.zzcwL;
    }

    public int zzafB() {
        int zzu = zzu();
        this.zzcwL = zzu;
        return zzu;
    }

    /* renamed from: zzafq */
    public zzbyj clone() throws CloneNotSupportedException {
        return (zzbyj) super.clone();
    }

    public abstract zzbyj zzb(zzbyb zzbyb) throws IOException;

    /* access modifiers changed from: protected */
    public int zzu() {
        return 0;
    }
}

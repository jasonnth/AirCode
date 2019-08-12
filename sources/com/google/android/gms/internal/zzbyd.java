package com.google.android.gms.internal;

import com.google.android.gms.internal.zzbyd;
import java.io.IOException;

public abstract class zzbyd<M extends zzbyd<M>> extends zzbyj {
    protected zzbyf zzcwC;

    private void zza(int i, zzbyl zzbyl) {
        zzbyg zzbyg = null;
        if (this.zzcwC == null) {
            this.zzcwC = new zzbyf();
        } else {
            zzbyg = this.zzcwC.zzrt(i);
        }
        if (zzbyg == null) {
            zzbyg = new zzbyg();
            this.zzcwC.zza(i, zzbyg);
        }
        zzbyg.zza(zzbyl);
    }

    public void zza(zzbyc zzbyc) throws IOException {
        if (this.zzcwC != null) {
            for (int i = 0; i < this.zzcwC.size(); i++) {
                this.zzcwC.zzru(i).zza(zzbyc);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzbyb zzbyb, int i) throws IOException {
        int position = zzbyb.getPosition();
        if (!zzbyb.zzrd(i)) {
            return false;
        }
        zza(zzbym.zzrx(i), new zzbyl(i, zzbyb.zzI(position, zzbyb.getPosition() - position)));
        return true;
    }

    /* renamed from: zzafp */
    public M clone() throws CloneNotSupportedException {
        M m = (zzbyd) super.clone();
        zzbyh.zza(this, (zzbyd) m);
        return m;
    }

    public /* synthetic */ zzbyj zzafq() throws CloneNotSupportedException {
        return (zzbyd) clone();
    }

    /* access modifiers changed from: protected */
    public int zzu() {
        if (this.zzcwC == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzcwC.size(); i2++) {
            i += this.zzcwC.zzru(i2).zzu();
        }
        return i;
    }
}

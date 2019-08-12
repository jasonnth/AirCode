package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzbyl {
    final int tag;
    final byte[] zzbyc;

    zzbyl(int i, byte[] bArr) {
        this.tag = i;
        this.zzbyc = bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbyl)) {
            return false;
        }
        zzbyl zzbyl = (zzbyl) obj;
        return this.tag == zzbyl.tag && Arrays.equals(this.zzbyc, zzbyl.zzbyc);
    }

    public int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzbyc);
    }

    /* access modifiers changed from: 0000 */
    public void zza(zzbyc zzbyc2) throws IOException {
        zzbyc2.zzrp(this.tag);
        zzbyc2.zzak(this.zzbyc);
    }

    /* access modifiers changed from: 0000 */
    public int zzu() {
        return zzbyc.zzrq(this.tag) + 0 + this.zzbyc.length;
    }
}

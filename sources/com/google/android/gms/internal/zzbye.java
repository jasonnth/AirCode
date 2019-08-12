package com.google.android.gms.internal;

import com.google.android.gms.internal.zzbyd;
import java.io.IOException;
import java.lang.reflect.Array;

public class zzbye<M extends zzbyd<M>, T> {
    public final int tag;
    protected final int type;
    protected final Class<T> zzckL;
    protected final boolean zzcwD;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbye)) {
            return false;
        }
        zzbye zzbye = (zzbye) obj;
        return this.type == zzbye.type && this.zzckL == zzbye.zzckL && this.tag == zzbye.tag && this.zzcwD == zzbye.zzcwD;
    }

    public int hashCode() {
        return (this.zzcwD ? 1 : 0) + ((((((this.type + 1147) * 31) + this.zzckL.hashCode()) * 31) + this.tag) * 31);
    }

    /* access modifiers changed from: 0000 */
    public void zza(Object obj, zzbyc zzbyc) throws IOException {
        if (this.zzcwD) {
            zzc(obj, zzbyc);
        } else {
            zzb(obj, zzbyc);
        }
    }

    /* access modifiers changed from: 0000 */
    public int zzaV(Object obj) {
        return this.zzcwD ? zzaW(obj) : zzaX(obj);
    }

    /* access modifiers changed from: protected */
    public int zzaW(Object obj) {
        int i = 0;
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            if (Array.get(obj, i2) != null) {
                i += zzaX(Array.get(obj, i2));
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int zzaX(Object obj) {
        int zzrx = zzbym.zzrx(this.tag);
        switch (this.type) {
            case 10:
                return zzbyc.zzb(zzrx, (zzbyj) obj);
            case 11:
                return zzbyc.zzc(zzrx, (zzbyj) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }

    /* access modifiers changed from: protected */
    public void zzb(Object obj, zzbyc zzbyc) {
        try {
            zzbyc.zzrp(this.tag);
            switch (this.type) {
                case 10:
                    zzbyj zzbyj = (zzbyj) obj;
                    int zzrx = zzbym.zzrx(this.tag);
                    zzbyc.zzb(zzbyj);
                    zzbyc.zzN(zzrx, 4);
                    return;
                case 11:
                    zzbyc.zzc((zzbyj) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    /* access modifiers changed from: protected */
    public void zzc(Object obj, zzbyc zzbyc) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                zzb(obj2, zzbyc);
            }
        }
    }
}

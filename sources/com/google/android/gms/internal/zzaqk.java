package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zzaqk extends zza {
    public static final Creator<zzaqk> CREATOR = new zzaql();
    public final int versionCode;
    private zzag.zza zzbgk = null;
    private byte[] zzbgl;

    zzaqk(int i, byte[] bArr) {
        this.versionCode = i;
        this.zzbgl = bArr;
        zzzT();
    }

    private void zzzR() {
        if (!zzzS()) {
            try {
                this.zzbgk = zzag.zza.zzd(this.zzbgl);
                this.zzbgl = null;
            } catch (zzbyi e) {
                throw new IllegalStateException(e);
            }
        }
        zzzT();
    }

    private boolean zzzS() {
        return this.zzbgk != null;
    }

    private void zzzT() {
        if (this.zzbgk == null && this.zzbgl != null) {
            return;
        }
        if (this.zzbgk != null && this.zzbgl == null) {
            return;
        }
        if (this.zzbgk != null && this.zzbgl != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        } else if (this.zzbgk == null && this.zzbgl == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        } else {
            throw new IllegalStateException("Impossible");
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzaql.zza(this, parcel, i);
    }

    public byte[] zzGM() {
        return this.zzbgl != null ? this.zzbgl : zzbyj.zzf(this.zzbgk);
    }

    public zzag.zza zzGN() {
        zzzR();
        return this.zzbgk;
    }
}

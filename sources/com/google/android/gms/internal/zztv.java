package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;

public class zztv extends zza {
    public static final Creator<zztv> CREATOR = new zztw();

    /* renamed from: id */
    public final int f3157id;
    final Bundle zzahq;

    zztv(int i, Bundle bundle) {
        this.f3157id = i;
        this.zzahq = bundle;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zztv)) {
            return false;
        }
        zztv zztv = (zztv) obj;
        return zzaa.equal(Integer.valueOf(zztv.f3157id), Integer.valueOf(this.f3157id)) && zzaa.equal(zztv.zzahq, this.zzahq);
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.f3157id), this.zzahq);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zztw.zza(this, parcel, i);
    }
}

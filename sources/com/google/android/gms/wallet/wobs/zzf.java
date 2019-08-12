package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zzf extends zza {
    public static final Creator<zzf> CREATOR = new zzi();
    String label;
    String type;
    zzl zzbQI;
    zzg zzbSB;

    zzf() {
    }

    zzf(String str, zzg zzg, String str2, zzl zzl) {
        this.label = str;
        this.zzbSB = zzg;
        this.type = str2;
        this.zzbQI = zzl;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }
}

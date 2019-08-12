package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zzg extends zza {
    public static final Creator<zzg> CREATOR = new zzh();
    String zzbQm;
    int zzbSC;
    String zzbSD;
    double zzbSE;
    long zzbSF;
    int zzbSG;

    zzg() {
        this.zzbSG = -1;
        this.zzbSC = -1;
        this.zzbSE = -1.0d;
    }

    zzg(int i, String str, double d, String str2, long j, int i2) {
        this.zzbSC = i;
        this.zzbSD = str;
        this.zzbSE = d;
        this.zzbQm = str2;
        this.zzbSF = j;
        this.zzbSG = i2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }
}

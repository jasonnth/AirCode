package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zzp extends zza {
    public static final Creator<zzp> CREATOR = new zzq();
    String body;
    String zzbSH;
    zzl zzbSL;
    zzn zzbSM;
    zzn zzbSN;

    zzp() {
    }

    zzp(String str, String str2, zzl zzl, zzn zzn, zzn zzn2) {
        this.zzbSH = str;
        this.body = str2;
        this.zzbSL = zzl;
        this.zzbSM = zzn;
        this.zzbSN = zzn2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzq.zza(this, parcel, i);
    }
}

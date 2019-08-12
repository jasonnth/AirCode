package com.google.android.gms.wallet.firstparty;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zzh extends zza {
    public static final Creator<zzh> CREATOR = new zzi();
    zzm zzbRJ;
    boolean zzbRK;

    zzh() {
    }

    zzh(zzm zzm, boolean z) {
        this.zzbRJ = zzm;
        this.zzbRK = z;
        if (zzm == null) {
            throw new NullPointerException("WalletCustomTheme is required");
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }
}

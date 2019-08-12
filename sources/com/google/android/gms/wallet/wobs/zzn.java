package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zzn extends zza {
    public static final Creator<zzn> CREATOR = new zzo();
    String description;
    String zzbSK;

    zzn() {
    }

    public zzn(String str, String str2) {
        this.zzbSK = str;
        this.description = str2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzo.zza(this, parcel, i);
    }
}

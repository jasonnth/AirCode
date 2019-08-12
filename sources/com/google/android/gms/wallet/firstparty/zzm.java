package com.google.android.gms.wallet.firstparty;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzm extends zza {
    public static final Creator<zzm> CREATOR = new zzn();
    int zzbRN;
    Bundle zzbRO;
    String zzbRP;

    public zzm() {
        this.zzbRN = 0;
        this.zzbRO = new Bundle();
        this.zzbRP = "";
    }

    zzm(int i, Bundle bundle, String str) {
        this.zzbRO = bundle;
        this.zzbRN = i;
        this.zzbRP = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzn.zza(this, parcel, i);
    }
}

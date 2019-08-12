package com.google.android.gms.wallet.firstparty;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class zza extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Creator<zza> CREATOR = new zzb();
    byte[] zzbRD;
    byte[] zzbRE;
    zzm zzbRF;

    public zza(byte[] bArr, byte[] bArr2, zzm zzm) {
        this.zzbRD = bArr;
        this.zzbRE = bArr2;
        this.zzbRF = zzm;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}

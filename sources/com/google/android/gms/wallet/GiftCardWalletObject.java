package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

public final class GiftCardWalletObject extends zza {
    public static final Creator<GiftCardWalletObject> CREATOR = new zzi();
    String pin;
    CommonWalletObject zzbQi = CommonWalletObject.zzUe().zzUf();
    String zzbQj;
    String zzbQk;
    long zzbQl;
    String zzbQm;
    long zzbQn;
    String zzbQo;

    GiftCardWalletObject() {
    }

    GiftCardWalletObject(CommonWalletObject commonWalletObject, String str, String str2, String str3, long j, String str4, long j2, String str5) {
        this.zzbQi = commonWalletObject;
        this.zzbQj = str;
        this.pin = str2;
        this.zzbQl = j;
        this.zzbQm = str4;
        this.zzbQn = j2;
        this.zzbQo = str5;
        this.zzbQk = str3;
    }

    public String getId() {
        return this.zzbQi.getId();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }
}

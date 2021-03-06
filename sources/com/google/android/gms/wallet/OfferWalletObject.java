package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

public final class OfferWalletObject extends zza {
    public static final Creator<OfferWalletObject> CREATOR = new zzq();
    private final int zzaiI;
    CommonWalletObject zzbQi;
    String zzbRl;
    String zzkl;

    OfferWalletObject() {
        this.zzaiI = 3;
    }

    OfferWalletObject(int i, String str, String str2, CommonWalletObject commonWalletObject) {
        this.zzaiI = i;
        this.zzbRl = str2;
        if (i < 3) {
            this.zzbQi = CommonWalletObject.zzUe().zzim(str).zzUf();
        } else {
            this.zzbQi = commonWalletObject;
        }
    }

    public String getId() {
        return this.zzbQi.getId();
    }

    public String getRedemptionCode() {
        return this.zzbRl;
    }

    public int getVersionCode() {
        return this.zzaiI;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzq.zza(this, parcel, i);
    }
}

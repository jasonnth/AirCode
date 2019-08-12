package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zze extends zza {
    public static final Creator<zze> CREATOR = new zzf();
    LoyaltyWalletObject zzbPS;
    OfferWalletObject zzbPT;
    GiftCardWalletObject zzbPU;

    zze() {
    }

    zze(LoyaltyWalletObject loyaltyWalletObject, OfferWalletObject offerWalletObject, GiftCardWalletObject giftCardWalletObject) {
        this.zzbPS = loyaltyWalletObject;
        this.zzbPT = offerWalletObject;
        this.zzbPU = giftCardWalletObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }
}

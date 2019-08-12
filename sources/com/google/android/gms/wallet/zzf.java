package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzf implements Creator<zze> {
    static void zza(zze zze, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) zze.zzbPS, i, false);
        zzc.zza(parcel, 3, (Parcelable) zze.zzbPT, i, false);
        zzc.zza(parcel, 4, (Parcelable) zze.zzbPU, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzka */
    public zze createFromParcel(Parcel parcel) {
        GiftCardWalletObject giftCardWalletObject;
        OfferWalletObject offerWalletObject;
        LoyaltyWalletObject loyaltyWalletObject;
        GiftCardWalletObject giftCardWalletObject2 = null;
        int zzaY = zzb.zzaY(parcel);
        OfferWalletObject offerWalletObject2 = null;
        LoyaltyWalletObject loyaltyWalletObject2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    GiftCardWalletObject giftCardWalletObject3 = giftCardWalletObject2;
                    offerWalletObject = offerWalletObject2;
                    loyaltyWalletObject = (LoyaltyWalletObject) zzb.zza(parcel, zzaX, LoyaltyWalletObject.CREATOR);
                    giftCardWalletObject = giftCardWalletObject3;
                    break;
                case 3:
                    loyaltyWalletObject = loyaltyWalletObject2;
                    OfferWalletObject offerWalletObject3 = (OfferWalletObject) zzb.zza(parcel, zzaX, OfferWalletObject.CREATOR);
                    giftCardWalletObject = giftCardWalletObject2;
                    offerWalletObject = offerWalletObject3;
                    break;
                case 4:
                    giftCardWalletObject = (GiftCardWalletObject) zzb.zza(parcel, zzaX, GiftCardWalletObject.CREATOR);
                    offerWalletObject = offerWalletObject2;
                    loyaltyWalletObject = loyaltyWalletObject2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    giftCardWalletObject = giftCardWalletObject2;
                    offerWalletObject = offerWalletObject2;
                    loyaltyWalletObject = loyaltyWalletObject2;
                    break;
            }
            loyaltyWalletObject2 = loyaltyWalletObject;
            offerWalletObject2 = offerWalletObject;
            giftCardWalletObject2 = giftCardWalletObject;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zze(loyaltyWalletObject2, offerWalletObject2, giftCardWalletObject2);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzow */
    public zze[] newArray(int i) {
        return new zze[i];
    }
}

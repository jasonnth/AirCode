package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.identity.intents.model.UserAddress;

public class zzn implements Creator<MaskedWallet> {
    static void zza(MaskedWallet maskedWallet, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, maskedWallet.zzbPV, false);
        zzc.zza(parcel, 3, maskedWallet.zzbPW, false);
        zzc.zza(parcel, 4, maskedWallet.zzbQb, false);
        zzc.zza(parcel, 5, maskedWallet.zzbPY, false);
        zzc.zza(parcel, 6, (Parcelable) maskedWallet.zzbPZ, i, false);
        zzc.zza(parcel, 7, (Parcelable) maskedWallet.zzbQa, i, false);
        zzc.zza(parcel, 8, (T[]) maskedWallet.zzbQS, i, false);
        zzc.zza(parcel, 9, (T[]) maskedWallet.zzbQT, i, false);
        zzc.zza(parcel, 10, (Parcelable) maskedWallet.zzbQc, i, false);
        zzc.zza(parcel, 11, (Parcelable) maskedWallet.zzbQd, i, false);
        zzc.zza(parcel, 12, (T[]) maskedWallet.zzbQe, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzki */
    public MaskedWallet createFromParcel(Parcel parcel) {
        InstrumentInfo[] instrumentInfoArr = null;
        int zzaY = zzb.zzaY(parcel);
        UserAddress userAddress = null;
        UserAddress userAddress2 = null;
        OfferWalletObject[] offerWalletObjectArr = null;
        LoyaltyWalletObject[] loyaltyWalletObjectArr = null;
        zza zza = null;
        zza zza2 = null;
        String str = null;
        String[] strArr = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 4:
                    strArr = zzb.zzC(parcel, zzaX);
                    break;
                case 5:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 6:
                    zza2 = (zza) zzb.zza(parcel, zzaX, zza.CREATOR);
                    break;
                case 7:
                    zza = (zza) zzb.zza(parcel, zzaX, zza.CREATOR);
                    break;
                case 8:
                    loyaltyWalletObjectArr = (LoyaltyWalletObject[]) zzb.zzb(parcel, zzaX, LoyaltyWalletObject.CREATOR);
                    break;
                case 9:
                    offerWalletObjectArr = (OfferWalletObject[]) zzb.zzb(parcel, zzaX, OfferWalletObject.CREATOR);
                    break;
                case 10:
                    userAddress2 = (UserAddress) zzb.zza(parcel, zzaX, UserAddress.CREATOR);
                    break;
                case 11:
                    userAddress = (UserAddress) zzb.zza(parcel, zzaX, UserAddress.CREATOR);
                    break;
                case 12:
                    instrumentInfoArr = (InstrumentInfo[]) zzb.zzb(parcel, zzaX, InstrumentInfo.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new MaskedWallet(str3, str2, strArr, str, zza2, zza, loyaltyWalletObjectArr, offerWalletObjectArr, userAddress2, userAddress, instrumentInfoArr);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzoE */
    public MaskedWallet[] newArray(int i) {
        return new MaskedWallet[i];
    }
}

package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public class zza implements Creator<WalletFragmentInitParams> {
    static void zza(WalletFragmentInitParams walletFragmentInitParams, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, walletFragmentInitParams.getAccountName(), false);
        zzc.zza(parcel, 3, (Parcelable) walletFragmentInitParams.getMaskedWalletRequest(), i, false);
        zzc.zzc(parcel, 4, walletFragmentInitParams.getMaskedWalletRequestCode());
        zzc.zza(parcel, 5, (Parcelable) walletFragmentInitParams.getMaskedWallet(), i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzky */
    public WalletFragmentInitParams createFromParcel(Parcel parcel) {
        MaskedWallet maskedWallet;
        int i;
        MaskedWalletRequest maskedWalletRequest;
        String str;
        MaskedWallet maskedWallet2 = null;
        int zzaY = zzb.zzaY(parcel);
        int i2 = -1;
        MaskedWalletRequest maskedWalletRequest2 = null;
        String str2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    MaskedWallet maskedWallet3 = maskedWallet2;
                    i = i2;
                    maskedWalletRequest = maskedWalletRequest2;
                    str = zzb.zzq(parcel, zzaX);
                    maskedWallet = maskedWallet3;
                    break;
                case 3:
                    str = str2;
                    int i3 = i2;
                    maskedWalletRequest = (MaskedWalletRequest) zzb.zza(parcel, zzaX, MaskedWalletRequest.CREATOR);
                    maskedWallet = maskedWallet2;
                    i = i3;
                    break;
                case 4:
                    maskedWalletRequest = maskedWalletRequest2;
                    str = str2;
                    MaskedWallet maskedWallet4 = maskedWallet2;
                    i = zzb.zzg(parcel, zzaX);
                    maskedWallet = maskedWallet4;
                    break;
                case 5:
                    maskedWallet = (MaskedWallet) zzb.zza(parcel, zzaX, MaskedWallet.CREATOR);
                    i = i2;
                    maskedWalletRequest = maskedWalletRequest2;
                    str = str2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    maskedWallet = maskedWallet2;
                    i = i2;
                    maskedWalletRequest = maskedWalletRequest2;
                    str = str2;
                    break;
            }
            str2 = str;
            maskedWalletRequest2 = maskedWalletRequest;
            i2 = i;
            maskedWallet2 = maskedWallet;
        }
        if (parcel.dataPosition() == zzaY) {
            return new WalletFragmentInitParams(str2, maskedWalletRequest2, i2, maskedWallet2);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zzb.zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzoU */
    public WalletFragmentInitParams[] newArray(int i) {
        return new WalletFragmentInitParams[i];
    }
}

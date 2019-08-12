package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzb implements Creator<WalletFragmentOptions> {
    static void zza(WalletFragmentOptions walletFragmentOptions, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 2, walletFragmentOptions.getEnvironment());
        zzc.zzc(parcel, 3, walletFragmentOptions.getTheme());
        zzc.zza(parcel, 4, (Parcelable) walletFragmentOptions.getFragmentStyle(), i, false);
        zzc.zzc(parcel, 5, walletFragmentOptions.getMode());
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzkz */
    public WalletFragmentOptions createFromParcel(Parcel parcel) {
        int zzg;
        WalletFragmentStyle walletFragmentStyle;
        int i;
        int i2;
        int i3 = 1;
        int zzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        int i4 = 0;
        WalletFragmentStyle walletFragmentStyle2 = null;
        int i5 = 1;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzdc(zzaX)) {
                case 2:
                    int i6 = i3;
                    walletFragmentStyle = walletFragmentStyle2;
                    i = i4;
                    i2 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaX);
                    zzg = i6;
                    break;
                case 3:
                    i2 = i5;
                    WalletFragmentStyle walletFragmentStyle3 = walletFragmentStyle2;
                    i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaX);
                    zzg = i3;
                    walletFragmentStyle = walletFragmentStyle3;
                    break;
                case 4:
                    i = i4;
                    i2 = i5;
                    int i7 = i3;
                    walletFragmentStyle = (WalletFragmentStyle) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaX, WalletFragmentStyle.CREATOR);
                    zzg = i7;
                    break;
                case 5:
                    zzg = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaX);
                    walletFragmentStyle = walletFragmentStyle2;
                    i = i4;
                    i2 = i5;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, zzaX);
                    zzg = i3;
                    walletFragmentStyle = walletFragmentStyle2;
                    i = i4;
                    i2 = i5;
                    break;
            }
            i5 = i2;
            i4 = i;
            walletFragmentStyle2 = walletFragmentStyle;
            i3 = zzg;
        }
        if (parcel.dataPosition() == zzaY) {
            return new WalletFragmentOptions(i5, i4, walletFragmentStyle2, i3);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzoV */
    public WalletFragmentOptions[] newArray(int i) {
        return new WalletFragmentOptions[i];
    }
}

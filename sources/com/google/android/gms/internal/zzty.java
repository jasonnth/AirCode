package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zztx.zza;

public class zzty implements Creator<zza> {
    static void zza(zza zza, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, (Parcelable) zza.zzahr, i, false);
        zzc.zza(parcel, 2, zza.zzahs);
        zzc.zza(parcel, 3, zza.zzaht);
        zzc.zza(parcel, 4, zza.zzahu);
        zzc.zza(parcel, 5, zza.zzahv, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzC */
    public zza createFromParcel(Parcel parcel) {
        String str = null;
        boolean z = false;
        int zzaY = zzb.zzaY(parcel);
        boolean z2 = false;
        boolean z3 = false;
        Account account = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    account = (Account) zzb.zza(parcel, zzaX, Account.CREATOR);
                    break;
                case 2:
                    z3 = zzb.zzc(parcel, zzaX);
                    break;
                case 3:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 4:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 5:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zza(account, z3, z2, z, str);
        }
        throw new zzb.zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzaN */
    public zza[] newArray(int i) {
        return new zza[i];
    }
}

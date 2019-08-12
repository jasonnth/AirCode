package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzacs.zza;
import com.google.android.gms.internal.zzacw.zzb;

public class zzacv implements Creator<zzb> {
    static void zza(zzb zzb, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzb.versionCode);
        zzc.zza(parcel, 2, zzb.zzaB, false);
        zzc.zza(parcel, 3, (Parcelable) zzb.zzaHl, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzbf */
    public zzb createFromParcel(Parcel parcel) {
        zza zza = null;
        int zzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzdc(zzaX)) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    str = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    zza = (zza) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaX, (Creator<T>) zza.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzb(i, str, zza);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zzb.zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzdj */
    public zzb[] newArray(int i) {
        return new zzb[i];
    }
}

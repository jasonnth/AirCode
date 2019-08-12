package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzm implements Creator<zzl> {
    static void zza(zzl zzl, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzl.zzbSI);
        zzc.zza(parcel, 3, zzl.zzbSJ);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzkH */
    public zzl createFromParcel(Parcel parcel) {
        long j = 0;
        int zzaY = zzb.zzaY(parcel);
        long j2 = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    j2 = zzb.zzi(parcel, zzaX);
                    break;
                case 3:
                    j = zzb.zzi(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzl(j2, j);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzpg */
    public zzl[] newArray(int i) {
        return new zzl[i];
    }
}

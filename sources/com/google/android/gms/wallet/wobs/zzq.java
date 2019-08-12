package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzq implements Creator<zzp> {
    static void zza(zzp zzp, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzp.zzbSH, false);
        zzc.zza(parcel, 3, zzp.body, false);
        zzc.zza(parcel, 4, (Parcelable) zzp.zzbSL, i, false);
        zzc.zza(parcel, 5, (Parcelable) zzp.zzbSM, i, false);
        zzc.zza(parcel, 6, (Parcelable) zzp.zzbSN, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzkJ */
    public zzp createFromParcel(Parcel parcel) {
        zzn zzn = null;
        int zzaY = zzb.zzaY(parcel);
        zzn zzn2 = null;
        zzl zzl = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 4:
                    zzl = (zzl) zzb.zza(parcel, zzaX, zzl.CREATOR);
                    break;
                case 5:
                    zzn2 = (zzn) zzb.zza(parcel, zzaX, zzn.CREATOR);
                    break;
                case 6:
                    zzn = (zzn) zzb.zza(parcel, zzaX, zzn.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzp(str2, str, zzl, zzn2, zzn);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzpi */
    public zzp[] newArray(int i) {
        return new zzp[i];
    }
}

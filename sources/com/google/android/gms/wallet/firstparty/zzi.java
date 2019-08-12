package com.google.android.gms.wallet.firstparty;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzi implements Creator<zzh> {
    static void zza(zzh zzh, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) zzh.zzbRJ, i, false);
        zzc.zza(parcel, 3, zzh.zzbRK);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzku */
    public zzh createFromParcel(Parcel parcel) {
        boolean zzc;
        zzm zzm;
        int zzaY = zzb.zzaY(parcel);
        zzm zzm2 = null;
        boolean z = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    boolean z2 = z;
                    zzm = (zzm) zzb.zza(parcel, zzaX, zzm.CREATOR);
                    zzc = z2;
                    break;
                case 3:
                    zzc = zzb.zzc(parcel, zzaX);
                    zzm = zzm2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzc = z;
                    zzm = zzm2;
                    break;
            }
            zzm2 = zzm;
            z = zzc;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzh(zzm2, z);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzoQ */
    public zzh[] newArray(int i) {
        return new zzh[i];
    }
}

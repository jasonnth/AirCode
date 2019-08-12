package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzarw;
import java.util.ArrayList;
import java.util.List;

public class zzg implements Creator<zzf> {
    static void zza(zzf zzf, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzf.zzId(), false);
        zzc.zza(parcel, 2, zzf.getTag(), false);
        zzc.zzc(parcel, 3, zzf.zzIe(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzgI */
    public zzf createFromParcel(Parcel parcel) {
        ArrayList arrayList = null;
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        List list = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    list = zzb.zzc(parcel, zzaX, zzd.CREATOR);
                    break;
                case 2:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    arrayList = zzb.zzc(parcel, zzaX, zzarw.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzf(list, str, arrayList);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzkd */
    public zzf[] newArray(int i) {
        return new zzf[i];
    }
}

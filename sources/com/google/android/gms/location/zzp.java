package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzp implements Creator<zzo> {
    static void zza(zzo zzo, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, zzo.zzIg(), false);
        zzc.zza(parcel, 2, zzo.zzIh(), false);
        zzc.zzc(parcel, 3, zzo.zzIi());
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzgO */
    public zzo createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        String str = "";
        String str2 = "";
        int i = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 2:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzo(str, str2, i);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzko */
    public zzo[] newArray(int i) {
        return new zzo[i];
    }
}

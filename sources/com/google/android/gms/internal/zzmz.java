package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzmz implements Creator<zzmy> {
    static void zza(zzmy zzmy, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzmy.zzFV, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaa */
    public zzmy[] newArray(int i) {
        return new zzmy[i];
    }

    /* renamed from: zzs */
    public zzmy createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzmy(str);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }
}

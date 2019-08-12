package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzop implements Creator<zzoo> {
    static void zza(zzoo zzoo, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzoo.type, false);
        zzc.zzc(parcel, 3, zzoo.zzVP);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaf */
    public zzoo[] newArray(int i) {
        return new zzoo[i];
    }

    /* renamed from: zzu */
    public zzoo createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str = zzb.zzq(parcel, zzaX);
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
            return new zzoo(str, i);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }
}

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzts implements Creator<zztr> {
    static void zza(zztr zztr, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, zztr.zzRg, false);
        zzc.zza(parcel, 2, zztr.zzahi, false);
        zzc.zza(parcel, 3, zztr.zzahj, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaK */
    public zztr[] newArray(int i) {
        return new zztr[i];
    }

    /* renamed from: zzz */
    public zztr createFromParcel(Parcel parcel) {
        String str = null;
        int zzaY = zzb.zzaY(parcel);
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 2:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zztr(str3, str2, str);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }
}

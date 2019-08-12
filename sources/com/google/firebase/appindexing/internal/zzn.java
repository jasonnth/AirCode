package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.firebase.appindexing.internal.Thing.zza;

public class zzn implements Creator<zza> {
    static void zza(zza zza, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, zza.zzVA());
        zzc.zzc(parcel, 2, zza.getScore());
        zzc.zza(parcel, 3, zza.zzVB(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzlz */
    public zza createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        boolean z = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 2:
                    i = zzb.zzg(parcel, zzaX);
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
            return new zza(z, i, str);
        }
        throw new zzb.zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzqe */
    public zza[] newArray(int i) {
        return new zza[i];
    }
}

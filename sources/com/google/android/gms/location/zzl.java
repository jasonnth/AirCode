package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzl implements Creator<LocationAvailability> {
    static void zza(LocationAvailability locationAvailability, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, locationAvailability.zzbjP);
        zzc.zzc(parcel, 2, locationAvailability.zzbjQ);
        zzc.zza(parcel, 3, locationAvailability.zzbjR);
        zzc.zzc(parcel, 4, locationAvailability.zzbjS);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzgL */
    public LocationAvailability createFromParcel(Parcel parcel) {
        int i = 1;
        int zzaY = zzb.zzaY(parcel);
        int i2 = 1000;
        long j = 0;
        int i3 = 1;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 3:
                    j = zzb.zzi(parcel, zzaX);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new LocationAvailability(i2, i3, i, j);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzkj */
    public LocationAvailability[] newArray(int i) {
        return new LocationAvailability[i];
    }
}

package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zze implements Creator<LatLngBounds> {
    static void zza(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) latLngBounds.southwest, i, false);
        zzc.zza(parcel, 3, (Parcelable) latLngBounds.northeast, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhB */
    public LatLngBounds createFromParcel(Parcel parcel) {
        LatLng latLng;
        LatLng latLng2;
        LatLng latLng3 = null;
        int zzaY = zzb.zzaY(parcel);
        LatLng latLng4 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    LatLng latLng5 = latLng3;
                    latLng2 = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    latLng = latLng5;
                    break;
                case 3:
                    latLng = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    latLng2 = latLng4;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    latLng = latLng3;
                    latLng2 = latLng4;
                    break;
            }
            latLng4 = latLng2;
            latLng3 = latLng;
        }
        if (parcel.dataPosition() == zzaY) {
            return new LatLngBounds(latLng4, latLng3);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzlj */
    public LatLngBounds[] newArray(int i) {
        return new LatLngBounds[i];
    }
}

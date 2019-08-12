package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzs implements Creator<VisibleRegion> {
    static void zza(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) visibleRegion.nearLeft, i, false);
        zzc.zza(parcel, 3, (Parcelable) visibleRegion.nearRight, i, false);
        zzc.zza(parcel, 4, (Parcelable) visibleRegion.farLeft, i, false);
        zzc.zza(parcel, 5, (Parcelable) visibleRegion.farRight, i, false);
        zzc.zza(parcel, 6, (Parcelable) visibleRegion.latLngBounds, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhP */
    public VisibleRegion createFromParcel(Parcel parcel) {
        LatLngBounds latLngBounds = null;
        int zzaY = zzb.zzaY(parcel);
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    latLng4 = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    break;
                case 3:
                    latLng3 = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    break;
                case 4:
                    latLng2 = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    break;
                case 5:
                    latLng = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) zzb.zza(parcel, zzaX, LatLngBounds.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new VisibleRegion(latLng4, latLng3, latLng2, latLng, latLngBounds);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzlx */
    public VisibleRegion[] newArray(int i) {
        return new VisibleRegion[i];
    }
}

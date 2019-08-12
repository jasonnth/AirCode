package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzj implements Creator<PointOfInterest> {
    static void zza(PointOfInterest pointOfInterest, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) pointOfInterest.latLng, i, false);
        zzc.zza(parcel, 3, pointOfInterest.placeId, false);
        zzc.zza(parcel, 4, pointOfInterest.name, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhG */
    public PointOfInterest createFromParcel(Parcel parcel) {
        String zzq;
        String str;
        LatLng latLng;
        String str2 = null;
        int zzaY = zzb.zzaY(parcel);
        String str3 = null;
        LatLng latLng2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    String str4 = str2;
                    str = str3;
                    latLng = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    zzq = str4;
                    break;
                case 3:
                    latLng = latLng2;
                    String zzq2 = zzb.zzq(parcel, zzaX);
                    zzq = str2;
                    str = zzq2;
                    break;
                case 4:
                    zzq = zzb.zzq(parcel, zzaX);
                    str = str3;
                    latLng = latLng2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzq = str2;
                    str = str3;
                    latLng = latLng2;
                    break;
            }
            latLng2 = latLng;
            str3 = str;
            str2 = zzq;
        }
        if (parcel.dataPosition() == zzaY) {
            return new PointOfInterest(latLng2, str3, str2);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzlo */
    public PointOfInterest[] newArray(int i) {
        return new PointOfInterest[i];
    }
}

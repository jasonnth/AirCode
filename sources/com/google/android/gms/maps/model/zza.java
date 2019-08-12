package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zza implements Creator<CameraPosition> {
    static void zza(CameraPosition cameraPosition, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) cameraPosition.target, i, false);
        zzc.zza(parcel, 3, cameraPosition.zoom);
        zzc.zza(parcel, 4, cameraPosition.tilt);
        zzc.zza(parcel, 5, cameraPosition.bearing);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhx */
    public CameraPosition createFromParcel(Parcel parcel) {
        float zzl;
        float f;
        float f2;
        LatLng latLng;
        float f3 = 0.0f;
        int zzaY = zzb.zzaY(parcel);
        LatLng latLng2 = null;
        float f4 = 0.0f;
        float f5 = 0.0f;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    float f6 = f3;
                    f = f4;
                    f2 = f5;
                    latLng = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    zzl = f6;
                    break;
                case 3:
                    latLng = latLng2;
                    float f7 = f4;
                    f2 = zzb.zzl(parcel, zzaX);
                    zzl = f3;
                    f = f7;
                    break;
                case 4:
                    f2 = f5;
                    latLng = latLng2;
                    float f8 = f3;
                    f = zzb.zzl(parcel, zzaX);
                    zzl = f8;
                    break;
                case 5:
                    zzl = zzb.zzl(parcel, zzaX);
                    f = f4;
                    f2 = f5;
                    latLng = latLng2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzl = f3;
                    f = f4;
                    f2 = f5;
                    latLng = latLng2;
                    break;
            }
            latLng2 = latLng;
            f5 = f2;
            f4 = f;
            f3 = zzl;
        }
        if (parcel.dataPosition() == zzaY) {
            return new CameraPosition(latLng2, f5, f4, f3);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zzb.zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzlf */
    public CameraPosition[] newArray(int i) {
        return new CameraPosition[i];
    }
}

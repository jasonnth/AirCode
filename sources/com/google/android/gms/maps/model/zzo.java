package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzo implements Creator<StreetViewPanoramaLocation> {
    static void zza(StreetViewPanoramaLocation streetViewPanoramaLocation, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (T[]) streetViewPanoramaLocation.links, i, false);
        zzc.zza(parcel, 3, (Parcelable) streetViewPanoramaLocation.position, i, false);
        zzc.zza(parcel, 4, streetViewPanoramaLocation.panoId, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhL */
    public StreetViewPanoramaLocation createFromParcel(Parcel parcel) {
        String zzq;
        LatLng latLng;
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr;
        String str = null;
        int zzaY = zzb.zzaY(parcel);
        LatLng latLng2 = null;
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    String str2 = str;
                    latLng = latLng2;
                    streetViewPanoramaLinkArr = (StreetViewPanoramaLink[]) zzb.zzb(parcel, zzaX, StreetViewPanoramaLink.CREATOR);
                    zzq = str2;
                    break;
                case 3:
                    streetViewPanoramaLinkArr = streetViewPanoramaLinkArr2;
                    LatLng latLng3 = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    zzq = str;
                    latLng = latLng3;
                    break;
                case 4:
                    zzq = zzb.zzq(parcel, zzaX);
                    latLng = latLng2;
                    streetViewPanoramaLinkArr = streetViewPanoramaLinkArr2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzq = str;
                    latLng = latLng2;
                    streetViewPanoramaLinkArr = streetViewPanoramaLinkArr2;
                    break;
            }
            streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
            latLng2 = latLng;
            str = zzq;
        }
        if (parcel.dataPosition() == zzaY) {
            return new StreetViewPanoramaLocation(streetViewPanoramaLinkArr2, latLng2, str);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzlt */
    public StreetViewPanoramaLocation[] newArray(int i) {
        return new StreetViewPanoramaLocation[i];
    }
}

package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzasm;
import java.util.ArrayList;

public class zzi implements Creator<GeofencingRequest> {
    static void zza(GeofencingRequest geofencingRequest, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, geofencingRequest.zzIf(), false);
        zzc.zzc(parcel, 2, geofencingRequest.getInitialTrigger());
        zzc.zza(parcel, 3, geofencingRequest.getTag(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzgK */
    public GeofencingRequest createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        ArrayList arrayList = null;
        int i = 0;
        String str = "";
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    arrayList = zzb.zzc(parcel, zzaX, zzasm.CREATOR);
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
            return new GeofencingRequest(arrayList, i, str);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzki */
    public GeofencingRequest[] newArray(int i) {
        return new GeofencingRequest[i];
    }
}

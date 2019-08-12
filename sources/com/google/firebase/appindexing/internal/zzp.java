package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.firebase.appindexing.internal.Thing.zza;

public class zzp implements Creator<Thing> {
    static void zza(Thing thing, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, thing.zzqK(), false);
        zzc.zza(parcel, 2, (Parcelable) thing.zzVy(), i, false);
        zzc.zza(parcel, 3, thing.getId(), false);
        zzc.zza(parcel, 4, thing.getType(), false);
        zzc.zzc(parcel, 1000, thing.getVersionCode());
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzlB */
    public Thing createFromParcel(Parcel parcel) {
        String str = null;
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        String str2 = null;
        zza zza = null;
        Bundle bundle = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    bundle = zzb.zzs(parcel, zzaX);
                    break;
                case 2:
                    zza = (zza) zzb.zza(parcel, zzaX, zza.CREATOR);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 4:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 1000:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new Thing(i, bundle, zza, str2, str);
        }
        throw new zzb.zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzqg */
    public Thing[] newArray(int i) {
        return new Thing[i];
    }
}

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzada implements Creator<zzacz> {
    static void zza(zzacz zzacz, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzacz.getVersionCode());
        zzc.zza(parcel, 2, zzacz.zzyH(), false);
        zzc.zza(parcel, 3, (Parcelable) zzacz.zzyI(), i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzbi */
    public zzacz createFromParcel(Parcel parcel) {
        zzacw zzacw = null;
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        Parcel parcel2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    parcel2 = zzb.zzF(parcel, zzaX);
                    break;
                case 3:
                    zzacw = (zzacw) zzb.zza(parcel, zzaX, zzacw.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzacz(i, parcel2, zzacw);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzdm */
    public zzacz[] newArray(int i) {
        return new zzacz[i];
    }
}

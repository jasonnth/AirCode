package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzmq implements Creator<zzmp> {
    static void zza(zzmp zzmp, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzmp.zzSL);
        zzc.zzb(parcel, 3, zzmp.zzSM, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzX */
    public zzmp[] newArray(int i) {
        return new zzmp[i];
    }

    /* renamed from: zzp */
    public zzmp createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        boolean z = false;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 3:
                    arrayList = zzb.zzE(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzmp(z, arrayList);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }
}

package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzr implements Creator<zzq> {
    static void zza(zzq zzq, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, zzq.getUrl(), false);
        zzc.zza(parcel, 2, zzq.zzVC(), false);
        zzc.zza(parcel, 3, zzq.zzVD(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzlC */
    public zzq createFromParcel(Parcel parcel) {
        String[] strArr = null;
        int zzaY = zzb.zzaY(parcel);
        String[] strArr2 = null;
        String str = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 2:
                    strArr2 = zzb.zzC(parcel, zzaX);
                    break;
                case 3:
                    strArr = zzb.zzC(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzq(str, strArr2, strArr);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzqh */
    public zzq[] newArray(int i) {
        return new zzq[i];
    }
}

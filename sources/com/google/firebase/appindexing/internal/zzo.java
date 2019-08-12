package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.firebase.appindexing.internal.zza.C7828zza;

public class zzo implements Creator<C7828zza> {
    static void zza(C7828zza zza, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zza.zzVr());
        zzc.zza(parcel, 2, zza.zzVs());
        zzc.zza(parcel, 3, zza.zzVt(), false);
        zzc.zza(parcel, 4, zza.getAccountName(), false);
        zzc.zza(parcel, 5, zza.zzVu(), false);
        zzc.zza(parcel, 6, zza.zzVv());
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzlA */
    public C7828zza createFromParcel(Parcel parcel) {
        byte[] bArr = null;
        boolean z = false;
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        String str2 = null;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 4:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 5:
                    bArr = zzb.zzt(parcel, zzaX);
                    break;
                case 6:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new C7828zza(i, z2, str2, str, bArr, z);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzqf */
    public C7828zza[] newArray(int i) {
        return new C7828zza[i];
    }
}

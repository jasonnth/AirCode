package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzi implements Creator<zzf> {
    static void zza(zzf zzf, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzf.label, false);
        zzc.zza(parcel, 3, (Parcelable) zzf.zzbSB, i, false);
        zzc.zza(parcel, 4, zzf.type, false);
        zzc.zza(parcel, 5, (Parcelable) zzf.zzbQI, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzkF */
    public zzf createFromParcel(Parcel parcel) {
        zzl zzl;
        String str;
        zzg zzg;
        String str2;
        zzl zzl2 = null;
        int zzaY = zzb.zzaY(parcel);
        String str3 = null;
        zzg zzg2 = null;
        String str4 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    zzl zzl3 = zzl2;
                    str = str3;
                    zzg = zzg2;
                    str2 = zzb.zzq(parcel, zzaX);
                    zzl = zzl3;
                    break;
                case 3:
                    str2 = str4;
                    String str5 = str3;
                    zzg = (zzg) zzb.zza(parcel, zzaX, zzg.CREATOR);
                    zzl = zzl2;
                    str = str5;
                    break;
                case 4:
                    zzg = zzg2;
                    str2 = str4;
                    zzl zzl4 = zzl2;
                    str = zzb.zzq(parcel, zzaX);
                    zzl = zzl4;
                    break;
                case 5:
                    zzl = (zzl) zzb.zza(parcel, zzaX, zzl.CREATOR);
                    str = str3;
                    zzg = zzg2;
                    str2 = str4;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzl = zzl2;
                    str = str3;
                    zzg = zzg2;
                    str2 = str4;
                    break;
            }
            str4 = str2;
            zzg2 = zzg;
            str3 = str;
            zzl2 = zzl;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzf(str4, zzg2, str3, zzl2);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzpe */
    public zzf[] newArray(int i) {
        return new zzf[i];
    }
}

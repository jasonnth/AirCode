package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzuc implements Creator<zzub> {
    static void zza(zzub zzub, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, zzub.name, false);
        zzc.zza(parcel, 2, zzub.zzahB, false);
        zzc.zza(parcel, 3, zzub.zzahC);
        zzc.zzc(parcel, 4, zzub.weight);
        zzc.zza(parcel, 5, zzub.zzahD);
        zzc.zza(parcel, 6, zzub.zzahE, false);
        zzc.zza(parcel, 7, (T[]) zzub.zzahF, i, false);
        zzc.zza(parcel, 8, zzub.zzahG, false);
        zzc.zza(parcel, 11, zzub.zzahH, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzE */
    public zzub createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int zzaY = zzb.zzaY(parcel);
        int i = 1;
        int[] iArr = null;
        zztv[] zztvArr = null;
        String str2 = null;
        boolean z2 = false;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    str4 = zzb.zzq(parcel, zzaX);
                    break;
                case 2:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 4:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 5:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 6:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 7:
                    zztvArr = (zztv[]) zzb.zzb(parcel, zzaX, zztv.CREATOR);
                    break;
                case 8:
                    iArr = zzb.zzw(parcel, zzaX);
                    break;
                case 11:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzub(str4, str3, z2, i, z, str2, zztvArr, iArr, str);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzaR */
    public zzub[] newArray(int i) {
        return new zzub[i];
    }
}

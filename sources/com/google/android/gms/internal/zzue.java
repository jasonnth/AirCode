package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzue implements Creator<zzud> {
    static void zza(zzud zzud, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, (Parcelable) zzud.zzahP, i, false);
        zzc.zza(parcel, 2, zzud.zzahQ);
        zzc.zzc(parcel, 3, zzud.zzahR);
        zzc.zza(parcel, 4, zzud.zzAT, false);
        zzc.zza(parcel, 5, (Parcelable) zzud.zzahS, i, false);
        zzc.zza(parcel, 6, zzud.zzahT);
        zzc.zzc(parcel, 7, zzud.zzahU);
        zzc.zzc(parcel, 8, zzud.zzahV);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzF */
    public zzud createFromParcel(Parcel parcel) {
        zztp zztp = null;
        int i = 0;
        int zzaY = zzb.zzaY(parcel);
        long j = 0;
        int i2 = -1;
        boolean z = false;
        String str = null;
        int i3 = 0;
        zztr zztr = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    zztr = (zztr) zzb.zza(parcel, zzaX, zztr.CREATOR);
                    break;
                case 2:
                    j = zzb.zzi(parcel, zzaX);
                    break;
                case 3:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 4:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 5:
                    zztp = (zztp) zzb.zza(parcel, zzaX, zztp.CREATOR);
                    break;
                case 6:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 7:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 8:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzud(zztr, j, i3, str, zztp, z, i2, i);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzaU */
    public zzud[] newArray(int i) {
        return new zzud[i];
    }
}

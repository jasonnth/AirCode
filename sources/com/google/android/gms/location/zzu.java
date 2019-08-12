package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.List;

public class zzu implements Creator<zzt> {
    static void zza(zzt zzt, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzb(parcel, 1, zzt.zzIm(), false);
        zzc.zza(parcel, 2, (Parcelable) zzt.zzvu(), i, false);
        zzc.zza(parcel, 3, zzt.getTag(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzgS */
    public zzt createFromParcel(Parcel parcel) {
        String zzq;
        PendingIntent pendingIntent;
        List list;
        PendingIntent pendingIntent2 = null;
        int zzaY = zzb.zzaY(parcel);
        String str = "";
        List list2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    String str2 = str;
                    pendingIntent = pendingIntent2;
                    list = zzb.zzE(parcel, zzaX);
                    zzq = str2;
                    break;
                case 2:
                    list = list2;
                    PendingIntent pendingIntent3 = (PendingIntent) zzb.zza(parcel, zzaX, PendingIntent.CREATOR);
                    zzq = str;
                    pendingIntent = pendingIntent3;
                    break;
                case 3:
                    zzq = zzb.zzq(parcel, zzaX);
                    pendingIntent = pendingIntent2;
                    list = list2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzq = str;
                    pendingIntent = pendingIntent2;
                    list = list2;
                    break;
            }
            list2 = list;
            pendingIntent2 = pendingIntent;
            str = zzq;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzt(list2, pendingIntent2, str);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzku */
    public zzt[] newArray(int i) {
        return new zzt[i];
    }
}

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zztx.zzb;
import java.util.List;

public class zztz implements Creator<zzb> {
    static void zza(zzb zzb, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, (Parcelable) zzb.zzahw, i, false);
        zzc.zzc(parcel, 2, zzb.zzahx, false);
        zzc.zza(parcel, 3, zzb.zzahy, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzD */
    public zzb createFromParcel(Parcel parcel) {
        String[] zzC;
        List list;
        Status status;
        String[] strArr = null;
        int zzaY = com.google.android.gms.common.internal.safeparcel.zzb.zzaY(parcel);
        List list2 = null;
        Status status2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = com.google.android.gms.common.internal.safeparcel.zzb.zzaX(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zzb.zzdc(zzaX)) {
                case 1:
                    String[] strArr2 = strArr;
                    list = list2;
                    status = (Status) com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, zzaX, Status.CREATOR);
                    zzC = strArr2;
                    break;
                case 2:
                    status = status2;
                    List zzc = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, zzaX, zzud.CREATOR);
                    zzC = strArr;
                    list = zzc;
                    break;
                case 3:
                    zzC = com.google.android.gms.common.internal.safeparcel.zzb.zzC(parcel, zzaX);
                    list = list2;
                    status = status2;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, zzaX);
                    zzC = strArr;
                    list = list2;
                    status = status2;
                    break;
            }
            status2 = status;
            list2 = list;
            strArr = zzC;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzb(status2, list2, strArr);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzaO */
    public zzb[] newArray(int i) {
        return new zzb[i];
    }
}

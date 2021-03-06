package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzl implements Creator<LineItem> {
    static void zza(LineItem lineItem, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, lineItem.description, false);
        zzc.zza(parcel, 3, lineItem.zzbQv, false);
        zzc.zza(parcel, 4, lineItem.zzbQw, false);
        zzc.zza(parcel, 5, lineItem.zzbPO, false);
        zzc.zzc(parcel, 6, lineItem.zzbQx);
        zzc.zza(parcel, 7, lineItem.zzbPP, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzkg */
    public LineItem createFromParcel(Parcel parcel) {
        String str = null;
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str5 = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    str4 = zzb.zzq(parcel, zzaX);
                    break;
                case 4:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 5:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 6:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 7:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new LineItem(str5, str4, str3, str2, i, str);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzoC */
    public LineItem[] newArray(int i) {
        return new LineItem[i];
    }
}

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzt implements Creator<ProxyCard> {
    static void zza(ProxyCard proxyCard, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, proxyCard.zzbRp, false);
        zzc.zza(parcel, 3, proxyCard.zzbRq, false);
        zzc.zzc(parcel, 4, proxyCard.zzbRr);
        zzc.zzc(parcel, 5, proxyCard.zzbRs);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzko */
    public ProxyCard createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int zzaY = zzb.zzaY(parcel);
        int i2 = 0;
        String str2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 5:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new ProxyCard(str2, str, i2, i);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzoK */
    public ProxyCard[] newArray(int i) {
        return new ProxyCard[i];
    }
}

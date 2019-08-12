package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzx implements Creator<zzw> {
    static void zza(zzw zzw, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) zzw.zzbRA, i, false);
        zzc.zza(parcel, 3, zzw.zzbRB, false);
        zzc.zza(parcel, 4, zzw.zzbRC, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzkq */
    public zzw createFromParcel(Parcel parcel) {
        String zzq;
        String str;
        Cart cart;
        String str2 = null;
        int zzaY = zzb.zzaY(parcel);
        String str3 = null;
        Cart cart2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    String str4 = str2;
                    str = str3;
                    cart = (Cart) zzb.zza(parcel, zzaX, Cart.CREATOR);
                    zzq = str4;
                    break;
                case 3:
                    cart = cart2;
                    String zzq2 = zzb.zzq(parcel, zzaX);
                    zzq = str2;
                    str = zzq2;
                    break;
                case 4:
                    zzq = zzb.zzq(parcel, zzaX);
                    str = str3;
                    cart = cart2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzq = str2;
                    str = str3;
                    cart = cart2;
                    break;
            }
            cart2 = cart;
            str3 = str;
            str2 = zzq;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzw(cart2, str3, str2);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzoM */
    public zzw[] newArray(int i) {
        return new zzw[i];
    }
}

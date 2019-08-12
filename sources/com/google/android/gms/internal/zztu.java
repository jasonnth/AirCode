package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zztu implements Creator<zztt> {
    static void zza(zztt zztt, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, zztt.zzahm, false);
        zzc.zza(parcel, 3, (Parcelable) zztt.zzahn, i, false);
        zzc.zzc(parcel, 4, zztt.zzaho);
        zzc.zza(parcel, 5, zztt.zzahp, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzA */
    public zztt createFromParcel(Parcel parcel) {
        byte[] zzt;
        int i;
        zzub zzub;
        String str;
        byte[] bArr = null;
        int zzaY = zzb.zzaY(parcel);
        int i2 = -1;
        zzub zzub2 = null;
        String str2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    byte[] bArr2 = bArr;
                    i = i2;
                    zzub = zzub2;
                    str = zzb.zzq(parcel, zzaX);
                    zzt = bArr2;
                    break;
                case 3:
                    str = str2;
                    int i3 = i2;
                    zzub = (zzub) zzb.zza(parcel, zzaX, zzub.CREATOR);
                    zzt = bArr;
                    i = i3;
                    break;
                case 4:
                    zzub = zzub2;
                    str = str2;
                    byte[] bArr3 = bArr;
                    i = zzb.zzg(parcel, zzaX);
                    zzt = bArr3;
                    break;
                case 5:
                    zzt = zzb.zzt(parcel, zzaX);
                    i = i2;
                    zzub = zzub2;
                    str = str2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzt = bArr;
                    i = i2;
                    zzub = zzub2;
                    str = str2;
                    break;
            }
            str2 = str;
            zzub2 = zzub;
            i2 = i;
            bArr = zzt;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zztt(str2, zzub2, i2, bArr);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzaL */
    public zztt[] newArray(int i) {
        return new zztt[i];
    }
}

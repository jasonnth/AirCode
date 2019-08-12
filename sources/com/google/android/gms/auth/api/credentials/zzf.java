package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;
import java.util.List;

public class zzf implements Creator<PasswordSpecification> {
    static void zza(PasswordSpecification passwordSpecification, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, passwordSpecification.zzajE, false);
        zzc.zzb(parcel, 2, passwordSpecification.zzajF, false);
        zzc.zza(parcel, 3, passwordSpecification.zzajG, false);
        zzc.zzc(parcel, 4, passwordSpecification.zzajH);
        zzc.zzc(parcel, 5, passwordSpecification.zzajI);
        zzc.zzc(parcel, 1000, passwordSpecification.zzaiI);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzP */
    public PasswordSpecification createFromParcel(Parcel parcel) {
        ArrayList arrayList = null;
        int i = 0;
        int zzaY = zzb.zzaY(parcel);
        int i2 = 0;
        List list = null;
        String str = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 2:
                    list = zzb.zzE(parcel, zzaX);
                    break;
                case 3:
                    arrayList = zzb.zzD(parcel, zzaX);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 5:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 1000:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new PasswordSpecification(i3, str, list, arrayList, i2, i);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzbe */
    public PasswordSpecification[] newArray(int i) {
        return new PasswordSpecification[i];
    }
}

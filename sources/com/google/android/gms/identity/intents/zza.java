package com.google.android.gms.identity.intents;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;

public class zza implements Creator<UserAddressRequest> {
    static void zza(UserAddressRequest userAddressRequest, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 2, userAddressRequest.zzbhs, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzgp */
    public UserAddressRequest createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        ArrayList arrayList = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    arrayList = zzb.zzc(parcel, zzaX, CountrySpecification.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new UserAddressRequest(arrayList);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zzb.zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzjH */
    public UserAddressRequest[] newArray(int i) {
        return new UserAddressRequest[i];
    }
}

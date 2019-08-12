package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.zzd;
import com.google.android.gms.wallet.wobs.zzf;
import com.google.android.gms.wallet.wobs.zzj;
import com.google.android.gms.wallet.wobs.zzl;
import com.google.android.gms.wallet.wobs.zzn;
import com.google.android.gms.wallet.wobs.zzp;
import java.util.ArrayList;

public class zzm implements Creator<LoyaltyWalletObject> {
    static void zza(LoyaltyWalletObject loyaltyWalletObject, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, loyaltyWalletObject.zzkl, false);
        zzc.zza(parcel, 3, loyaltyWalletObject.zzbQz, false);
        zzc.zza(parcel, 4, loyaltyWalletObject.zzbQA, false);
        zzc.zza(parcel, 5, loyaltyWalletObject.zzbQB, false);
        zzc.zza(parcel, 6, loyaltyWalletObject.zzaJT, false);
        zzc.zza(parcel, 7, loyaltyWalletObject.zzbQC, false);
        zzc.zza(parcel, 8, loyaltyWalletObject.zzbQD, false);
        zzc.zza(parcel, 9, loyaltyWalletObject.zzbQE, false);
        zzc.zza(parcel, 10, loyaltyWalletObject.zzbQF, false);
        zzc.zza(parcel, 11, loyaltyWalletObject.zzbQG, false);
        zzc.zzc(parcel, 12, loyaltyWalletObject.state);
        zzc.zzc(parcel, 13, loyaltyWalletObject.zzbQH, false);
        zzc.zza(parcel, 14, (Parcelable) loyaltyWalletObject.zzbQI, i, false);
        zzc.zzc(parcel, 15, loyaltyWalletObject.zzbQJ, false);
        zzc.zza(parcel, 16, loyaltyWalletObject.zzbQK, false);
        zzc.zza(parcel, 17, loyaltyWalletObject.zzbQL, false);
        zzc.zzc(parcel, 18, loyaltyWalletObject.zzbQM, false);
        zzc.zza(parcel, 19, loyaltyWalletObject.zzbQN);
        zzc.zzc(parcel, 20, loyaltyWalletObject.zzbQO, false);
        zzc.zzc(parcel, 21, loyaltyWalletObject.zzbQP, false);
        zzc.zzc(parcel, 22, loyaltyWalletObject.zzbQQ, false);
        zzc.zza(parcel, 23, (Parcelable) loyaltyWalletObject.zzbQR, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzkh */
    public LoyaltyWalletObject createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        int i = 0;
        ArrayList zzyY = com.google.android.gms.common.util.zzb.zzyY();
        zzl zzl = null;
        ArrayList zzyY2 = com.google.android.gms.common.util.zzb.zzyY();
        String str11 = null;
        String str12 = null;
        ArrayList zzyY3 = com.google.android.gms.common.util.zzb.zzyY();
        boolean z = false;
        ArrayList zzyY4 = com.google.android.gms.common.util.zzb.zzyY();
        ArrayList zzyY5 = com.google.android.gms.common.util.zzb.zzyY();
        ArrayList zzyY6 = com.google.android.gms.common.util.zzb.zzyY();
        zzf zzf = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 4:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 5:
                    str4 = zzb.zzq(parcel, zzaX);
                    break;
                case 6:
                    str5 = zzb.zzq(parcel, zzaX);
                    break;
                case 7:
                    str6 = zzb.zzq(parcel, zzaX);
                    break;
                case 8:
                    str7 = zzb.zzq(parcel, zzaX);
                    break;
                case 9:
                    str8 = zzb.zzq(parcel, zzaX);
                    break;
                case 10:
                    str9 = zzb.zzq(parcel, zzaX);
                    break;
                case 11:
                    str10 = zzb.zzq(parcel, zzaX);
                    break;
                case 12:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 13:
                    zzyY = zzb.zzc(parcel, zzaX, zzp.CREATOR);
                    break;
                case 14:
                    zzl = (zzl) zzb.zza(parcel, zzaX, zzl.CREATOR);
                    break;
                case 15:
                    zzyY2 = zzb.zzc(parcel, zzaX, LatLng.CREATOR);
                    break;
                case 16:
                    str11 = zzb.zzq(parcel, zzaX);
                    break;
                case 17:
                    str12 = zzb.zzq(parcel, zzaX);
                    break;
                case 18:
                    zzyY3 = zzb.zzc(parcel, zzaX, zzd.CREATOR);
                    break;
                case 19:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 20:
                    zzyY4 = zzb.zzc(parcel, zzaX, zzn.CREATOR);
                    break;
                case 21:
                    zzyY5 = zzb.zzc(parcel, zzaX, zzj.CREATOR);
                    break;
                case 22:
                    zzyY6 = zzb.zzc(parcel, zzaX, zzn.CREATOR);
                    break;
                case 23:
                    zzf = (zzf) zzb.zza(parcel, zzaX, zzf.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new LoyaltyWalletObject(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, i, zzyY, zzl, zzyY2, str11, str12, zzyY3, z, zzyY4, zzyY5, zzyY6, zzf);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzoD */
    public LoyaltyWalletObject[] newArray(int i) {
        return new LoyaltyWalletObject[i];
    }
}

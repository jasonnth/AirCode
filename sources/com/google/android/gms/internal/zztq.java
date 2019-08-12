package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zztq implements Creator<zztp> {
    static void zza(zztp zztp, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, (T[]) zztp.zzahb, i, false);
        zzc.zza(parcel, 2, zztp.zzahc, false);
        zzc.zza(parcel, 3, zztp.zzahd);
        zzc.zza(parcel, 4, (Parcelable) zztp.account, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaJ */
    public zztp[] newArray(int i) {
        return new zztp[i];
    }

    /* renamed from: zzy */
    public zztp createFromParcel(Parcel parcel) {
        Account account;
        boolean z;
        String str;
        zztt[] zzttArr;
        Account account2 = null;
        int zzaY = zzb.zzaY(parcel);
        boolean z2 = false;
        String str2 = null;
        zztt[] zzttArr2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    Account account3 = account2;
                    z = z2;
                    str = str2;
                    zzttArr = (zztt[]) zzb.zzb(parcel, zzaX, zztt.CREATOR);
                    account = account3;
                    break;
                case 2:
                    zzttArr = zzttArr2;
                    boolean z3 = z2;
                    str = zzb.zzq(parcel, zzaX);
                    account = account2;
                    z = z3;
                    break;
                case 3:
                    str = str2;
                    zzttArr = zzttArr2;
                    Account account4 = account2;
                    z = zzb.zzc(parcel, zzaX);
                    account = account4;
                    break;
                case 4:
                    account = (Account) zzb.zza(parcel, zzaX, Account.CREATOR);
                    z = z2;
                    str = str2;
                    zzttArr = zzttArr2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    account = account2;
                    z = z2;
                    str = str2;
                    zzttArr = zzttArr2;
                    break;
            }
            zzttArr2 = zzttArr;
            str2 = str;
            z2 = z;
            account2 = account;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zztp(zzttArr2, str2, z2, account2);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }
}

package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;

public class zzc implements Creator<SignInAccount> {
    static void zza(SignInAccount signInAccount, Parcel parcel, int i) {
        int zzaZ = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(parcel);
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 1, signInAccount.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, signInAccount.zzaka, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 7, (Parcelable) signInAccount.zzro(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 8, signInAccount.zzadi, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzY */
    public SignInAccount createFromParcel(Parcel parcel) {
        String zzq;
        GoogleSignInAccount googleSignInAccount;
        String str;
        int i;
        int zzaY = zzb.zzaY(parcel);
        int i2 = 0;
        String str2 = "";
        GoogleSignInAccount googleSignInAccount2 = null;
        String str3 = "";
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    String str4 = str3;
                    googleSignInAccount = googleSignInAccount2;
                    str = str2;
                    i = zzb.zzg(parcel, zzaX);
                    zzq = str4;
                    break;
                case 4:
                    i = i2;
                    GoogleSignInAccount googleSignInAccount3 = googleSignInAccount2;
                    str = zzb.zzq(parcel, zzaX);
                    zzq = str3;
                    googleSignInAccount = googleSignInAccount3;
                    break;
                case 7:
                    str = str2;
                    i = i2;
                    String str5 = str3;
                    googleSignInAccount = (GoogleSignInAccount) zzb.zza(parcel, zzaX, GoogleSignInAccount.CREATOR);
                    zzq = str5;
                    break;
                case 8:
                    zzq = zzb.zzq(parcel, zzaX);
                    googleSignInAccount = googleSignInAccount2;
                    str = str2;
                    i = i2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzq = str3;
                    googleSignInAccount = googleSignInAccount2;
                    str = str2;
                    i = i2;
                    break;
            }
            i2 = i;
            str2 = str;
            googleSignInAccount2 = googleSignInAccount;
            str3 = zzq;
        }
        if (parcel.dataPosition() == zzaY) {
            return new SignInAccount(i2, str2, googleSignInAccount2, str3);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzbn */
    public SignInAccount[] newArray(int i) {
        return new SignInAccount[i];
    }
}

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;
import java.util.List;

public class zzo implements Creator<MaskedWalletRequest> {
    static void zza(MaskedWalletRequest maskedWalletRequest, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, maskedWalletRequest.zzbPW, false);
        zzc.zza(parcel, 3, maskedWalletRequest.zzbQV);
        zzc.zza(parcel, 4, maskedWalletRequest.zzbQW);
        zzc.zza(parcel, 5, maskedWalletRequest.zzbQX);
        zzc.zza(parcel, 6, maskedWalletRequest.zzbQY, false);
        zzc.zza(parcel, 7, maskedWalletRequest.zzbPP, false);
        zzc.zza(parcel, 8, maskedWalletRequest.zzbQZ, false);
        zzc.zza(parcel, 9, (Parcelable) maskedWalletRequest.zzbQg, i, false);
        zzc.zza(parcel, 10, maskedWalletRequest.zzbRa);
        zzc.zza(parcel, 11, maskedWalletRequest.zzbRb);
        zzc.zza(parcel, 12, (T[]) maskedWalletRequest.zzbRc, i, false);
        zzc.zza(parcel, 13, maskedWalletRequest.zzbRd);
        zzc.zza(parcel, 14, maskedWalletRequest.zzbRe);
        zzc.zzc(parcel, 15, maskedWalletRequest.zzbRf, false);
        zzc.zza(parcel, 16, (Parcelable) maskedWalletRequest.zzbRg, i, false);
        zzc.zza(parcel, 17, (List<Integer>) maskedWalletRequest.zzbRh, false);
        zzc.zza(parcel, 18, maskedWalletRequest.zzUI, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzkj */
    public MaskedWalletRequest createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Cart cart = null;
        boolean z4 = false;
        boolean z5 = false;
        CountrySpecification[] countrySpecificationArr = null;
        boolean z6 = true;
        boolean z7 = true;
        ArrayList arrayList = null;
        PaymentMethodTokenizationParameters paymentMethodTokenizationParameters = null;
        ArrayList arrayList2 = null;
        String str5 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 4:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 5:
                    z3 = zzb.zzc(parcel, zzaX);
                    break;
                case 6:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 7:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 8:
                    str4 = zzb.zzq(parcel, zzaX);
                    break;
                case 9:
                    cart = (Cart) zzb.zza(parcel, zzaX, Cart.CREATOR);
                    break;
                case 10:
                    z4 = zzb.zzc(parcel, zzaX);
                    break;
                case 11:
                    z5 = zzb.zzc(parcel, zzaX);
                    break;
                case 12:
                    countrySpecificationArr = (CountrySpecification[]) zzb.zzb(parcel, zzaX, CountrySpecification.CREATOR);
                    break;
                case 13:
                    z6 = zzb.zzc(parcel, zzaX);
                    break;
                case 14:
                    z7 = zzb.zzc(parcel, zzaX);
                    break;
                case 15:
                    arrayList = zzb.zzc(parcel, zzaX, CountrySpecification.CREATOR);
                    break;
                case 16:
                    paymentMethodTokenizationParameters = (PaymentMethodTokenizationParameters) zzb.zza(parcel, zzaX, PaymentMethodTokenizationParameters.CREATOR);
                    break;
                case 17:
                    arrayList2 = zzb.zzD(parcel, zzaX);
                    break;
                case 18:
                    str5 = zzb.zzq(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new MaskedWalletRequest(str, z, z2, z3, str2, str3, str4, cart, z4, z5, countrySpecificationArr, z6, z7, arrayList, paymentMethodTokenizationParameters, arrayList2, str5);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzoF */
    public MaskedWalletRequest[] newArray(int i) {
        return new MaskedWalletRequest[i];
    }
}

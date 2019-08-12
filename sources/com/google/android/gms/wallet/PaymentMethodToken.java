package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class PaymentMethodToken extends zza {
    public static final Creator<PaymentMethodToken> CREATOR = new zzr();
    int zzbRm;
    String zzbxW;

    private PaymentMethodToken() {
    }

    PaymentMethodToken(int i, String str) {
        this.zzbRm = i;
        this.zzbxW = str;
    }

    public int getPaymentMethodTokenizationType() {
        return this.zzbRm;
    }

    public String getToken() {
        return this.zzbxW;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzr.zza(this, parcel, i);
    }
}

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

@Deprecated
public final class ProxyCard extends zza {
    public static final Creator<ProxyCard> CREATOR = new zzt();
    String zzbRp;
    String zzbRq;
    int zzbRr;
    int zzbRs;

    public ProxyCard(String str, String str2, int i, int i2) {
        this.zzbRp = str;
        this.zzbRq = str2;
        this.zzbRr = i;
        this.zzbRs = i2;
    }

    public String getCvn() {
        return this.zzbRq;
    }

    public int getExpirationMonth() {
        return this.zzbRr;
    }

    public int getExpirationYear() {
        return this.zzbRs;
    }

    public String getPan() {
        return this.zzbRp;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzt.zza(this, parcel, i);
    }
}

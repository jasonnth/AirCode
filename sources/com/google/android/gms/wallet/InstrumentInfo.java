package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class InstrumentInfo extends zza {
    public static final Creator<InstrumentInfo> CREATOR = new zzj();
    private String zzbQp;
    private String zzbQq;

    public InstrumentInfo(String str, String str2) {
        this.zzbQp = str;
        this.zzbQq = str2;
    }

    public String getInstrumentDetails() {
        return this.zzbQq;
    }

    public String getInstrumentType() {
        return this.zzbQp;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzj.zza(this, parcel, i);
    }
}

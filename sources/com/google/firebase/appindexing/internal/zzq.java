package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzq extends zza {
    public static final Creator<zzq> CREATOR = new zzr();
    private final String zzE;
    private final String[] zzbXG;
    private final String[] zzbXH;

    public zzq(String str, String[] strArr, String[] strArr2) {
        this.zzE = str;
        this.zzbXG = strArr;
        this.zzbXH = strArr2;
    }

    public String getUrl() {
        return this.zzE;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzr.zza(this, parcel, i);
    }

    public String[] zzVC() {
        return this.zzbXG;
    }

    public String[] zzVD() {
        return this.zzbXH;
    }
}

package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzg extends zza {
    public static final Creator<zzg> CREATOR = new zzh();
    private final String packageName;
    private final String url;

    public zzg(String str, String str2) {
        this.url = str;
        this.packageName = str2;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getUrl() {
        return this.url;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }
}

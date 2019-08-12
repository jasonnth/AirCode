package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zztr extends zza {
    public static final Creator<zztr> CREATOR = new zzts();
    final String zzRg;
    final String zzahi;
    final String zzahj;

    public zztr(String str, String str2, String str3) {
        this.zzRg = str;
        this.zzahi = str2;
        this.zzahj = str3;
    }

    public String toString() {
        return String.format("DocumentId[packageName=%s, corpusName=%s, uri=%s]", new Object[]{this.zzRg, this.zzahi, this.zzahj});
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzts.zza(this, parcel, i);
    }
}

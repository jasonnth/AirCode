package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzi extends zza implements Result {
    public static final Creator<zzi> CREATOR = new zzj();
    private final Status zzahw;
    private final Thing zzbXF;

    public zzi(Status status, Thing thing) {
        this.zzahw = status;
        this.zzbXF = thing;
    }

    public Status getStatus() {
        return this.zzahw;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzj.zza(this, parcel, i);
    }

    public Thing zzVx() {
        return this.zzbXF;
    }
}

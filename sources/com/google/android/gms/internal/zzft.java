package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.safeparcel.zza;

@zzme
public class zzft extends zza {
    public static final Creator<zzft> CREATOR = new zzfu();
    public final boolean zzAU;

    public zzft(VideoOptions videoOptions) {
        this(videoOptions.getStartMuted());
    }

    public zzft(boolean z) {
        this.zzAU = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzfu.zza(this, parcel, i);
    }
}

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.safeparcel.zza;

@zzme
public final class zzqh extends zza {
    public static final Creator<zzqh> CREATOR = new zzqi();
    public int zzYW;
    public int zzYX;
    public boolean zzYY;
    public boolean zzYZ;
    public String zzba;

    public zzqh(int i, int i2, boolean z) {
        this(i, i2, z, false, false);
    }

    public zzqh(int i, int i2, boolean z, boolean z2) {
        this(i, i2, z, false, z2);
    }

    public zzqh(int i, int i2, boolean z, boolean z2, boolean z3) {
        String valueOf = String.valueOf("afma-sdk-a-v");
        String str = z ? AppEventsConstants.EVENT_PARAM_VALUE_NO : z2 ? "2" : "1";
        String sb = new StringBuilder(String.valueOf(valueOf).length() + 24 + String.valueOf(str).length()).append(valueOf).append(i).append(".").append(i2).append(".").append(str).toString();
        this(sb, i, i2, z, z3);
    }

    zzqh(String str, int i, int i2, boolean z, boolean z2) {
        this.zzba = str;
        this.zzYW = i;
        this.zzYX = i2;
        this.zzYY = z;
        this.zzYZ = z2;
    }

    public static zzqh zzlk() {
        return new zzqh(10260208, 10260208, true);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzqi.zza(this, parcel, i);
    }
}

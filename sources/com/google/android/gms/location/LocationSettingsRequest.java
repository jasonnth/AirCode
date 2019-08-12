package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.Collections;
import java.util.List;

public final class LocationSettingsRequest extends zza {
    public static final Creator<LocationSettingsRequest> CREATOR = new zzq();
    private final List<LocationRequest> zzaWt;
    private final boolean zzbkd;
    private final boolean zzbke;
    private zzo zzbkf;

    LocationSettingsRequest(List<LocationRequest> list, boolean z, boolean z2, zzo zzo) {
        this.zzaWt = list;
        this.zzbkd = z;
        this.zzbke = z2;
        this.zzbkf = zzo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzq.zza(this, parcel, i);
    }

    public List<LocationRequest> zzDf() {
        return Collections.unmodifiableList(this.zzaWt);
    }

    public boolean zzIj() {
        return this.zzbkd;
    }

    public boolean zzIk() {
        return this.zzbke;
    }

    public zzo zzIl() {
        return this.zzbkf;
    }
}

package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class LocationSettingsStates extends zza {
    public static final Creator<LocationSettingsStates> CREATOR = new zzs();
    private final boolean zzbki;
    private final boolean zzbkj;
    private final boolean zzbkk;
    private final boolean zzbkl;
    private final boolean zzbkm;
    private final boolean zzbkn;

    public LocationSettingsStates(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.zzbki = z;
        this.zzbkj = z2;
        this.zzbkk = z3;
        this.zzbkl = z4;
        this.zzbkm = z5;
        this.zzbkn = z6;
    }

    public static LocationSettingsStates fromIntent(Intent intent) {
        return (LocationSettingsStates) zzd.zza(intent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
    }

    public boolean isBlePresent() {
        return this.zzbkn;
    }

    public boolean isBleUsable() {
        return this.zzbkk;
    }

    public boolean isGpsPresent() {
        return this.zzbkl;
    }

    public boolean isGpsUsable() {
        return this.zzbki;
    }

    public boolean isLocationPresent() {
        return this.zzbkl || this.zzbkm;
    }

    public boolean isLocationUsable() {
        return this.zzbki || this.zzbkj;
    }

    public boolean isNetworkLocationPresent() {
        return this.zzbkm;
    }

    public boolean isNetworkLocationUsable() {
        return this.zzbkj;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzs.zza(this, parcel, i);
    }
}

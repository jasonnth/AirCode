package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class zzo extends zza {
    public static final Creator<zzo> CREATOR = new zzp();
    private final String zzbka;
    private final String zzbkb;
    private final int zzbkc;

    zzo(String str, String str2, int i) {
        this.zzbka = str;
        this.zzbkb = str2;
        this.zzbkc = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzp.zza(this, parcel, i);
    }

    public String zzIg() {
        return this.zzbka;
    }

    public String zzIh() {
        return this.zzbkb;
    }

    public int zzIi() {
        return this.zzbkc;
    }
}

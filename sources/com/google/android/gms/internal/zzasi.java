package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public class zzasi extends zza {
    public static final Creator<zzasi> CREATOR = new zzasj();
    static final List<zzarw> zzbkL = Collections.emptyList();
    String mTag;
    LocationRequest zzaWw;
    boolean zzbjk = true;
    List<zzarw> zzbjw;
    boolean zzbkM;
    boolean zzbkN;

    zzasi(LocationRequest locationRequest, List<zzarw> list, String str, boolean z, boolean z2) {
        this.zzaWw = locationRequest;
        this.zzbjw = list;
        this.mTag = str;
        this.zzbkM = z;
        this.zzbkN = z2;
    }

    public static zzasi zza(String str, LocationRequest locationRequest) {
        return new zzasi(locationRequest, zzbkL, str, false, false);
    }

    @Deprecated
    public static zzasi zzb(LocationRequest locationRequest) {
        return zza(null, locationRequest);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzasi)) {
            return false;
        }
        zzasi zzasi = (zzasi) obj;
        return zzaa.equal(this.zzaWw, zzasi.zzaWw) && zzaa.equal(this.zzbjw, zzasi.zzbjw) && zzaa.equal(this.mTag, zzasi.mTag) && this.zzbkM == zzasi.zzbkM && this.zzbkN == zzasi.zzbkN;
    }

    public int hashCode() {
        return this.zzaWw.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.zzaWw.toString());
        if (this.mTag != null) {
            sb.append(" tag=").append(this.mTag);
        }
        sb.append(" hideAppOps=").append(this.zzbkM);
        sb.append(" clients=").append(this.zzbjw);
        sb.append(" forceCoarseLocation=").append(this.zzbkN);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzasj.zza(this, parcel, i);
    }
}

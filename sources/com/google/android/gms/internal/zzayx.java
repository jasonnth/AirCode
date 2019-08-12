package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class zzayx extends zza implements Comparable<zzayx> {
    public static final Creator<zzayx> CREATOR = new zzayy();
    public final int zzbBB;
    public final zzayz[] zzbBC;
    public final String[] zzbBD;
    public final Map<String, zzayz> zzbBE = new TreeMap();

    public zzayx(int i, zzayz[] zzayzArr, String[] strArr) {
        this.zzbBB = i;
        this.zzbBC = zzayzArr;
        for (zzayz zzayz : zzayzArr) {
            this.zzbBE.put(zzayz.name, zzayz);
        }
        this.zzbBD = strArr;
        if (this.zzbBD != null) {
            Arrays.sort(this.zzbBD);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzayx)) {
            return false;
        }
        zzayx zzayx = (zzayx) obj;
        return this.zzbBB == zzayx.zzbBB && zzaa.equal(this.zzbBE, zzayx.zzbBE) && Arrays.equals(this.zzbBD, zzayx.zzbBD);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.zzbBB);
        sb.append(", ");
        sb.append("(");
        for (zzayz append : this.zzbBE.values()) {
            sb.append(append);
            sb.append(", ");
        }
        sb.append(")");
        sb.append(", ");
        sb.append("(");
        if (this.zzbBD != null) {
            for (String append2 : this.zzbBD) {
                sb.append(append2);
                sb.append(", ");
            }
        } else {
            sb.append("null");
        }
        sb.append(")");
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzayy.zza(this, parcel, i);
    }

    /* renamed from: zza */
    public int compareTo(zzayx zzayx) {
        return this.zzbBB - zzayx.zzbBB;
    }
}

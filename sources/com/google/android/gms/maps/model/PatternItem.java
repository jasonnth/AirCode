package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.List;

public class PatternItem extends zza {
    public static final Creator<PatternItem> CREATOR = new zzi();
    private static final String TAG = PatternItem.class.getSimpleName();
    private final int type;
    private final Float zzbpI;

    public PatternItem(int i, Float f) {
        boolean z = true;
        if (i != 1 && (f == null || f.floatValue() < 0.0f)) {
            z = false;
        }
        String valueOf = String.valueOf(f);
        zzac.zzb(z, (Object) new StringBuilder(String.valueOf(valueOf).length() + 45).append("Invalid PatternItem: type=").append(i).append(" length=").append(valueOf).toString());
        this.type = i;
        this.zzbpI = f;
    }

    static List<PatternItem> zzI(List<PatternItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (PatternItem patternItem : list) {
            arrayList.add(patternItem == null ? null : patternItem.zzJO());
        }
        return arrayList;
    }

    private PatternItem zzJO() {
        switch (this.type) {
            case 0:
                return new Dash(this.zzbpI.floatValue());
            case 1:
                return new Dot();
            case 2:
                return new Gap(this.zzbpI.floatValue());
            default:
                Log.w(TAG, "Unknown PatternItem type: " + this.type);
                return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) obj;
        return this.type == patternItem.type && zzaa.equal(this.zzbpI, patternItem.zzbpI);
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.type), this.zzbpI);
    }

    public String toString() {
        int i = this.type;
        String valueOf = String.valueOf(this.zzbpI);
        return new StringBuilder(String.valueOf(valueOf).length() + 39).append("[PatternItem: type=").append(i).append(" length=").append(valueOf).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }

    public Float zzJN() {
        return this.zzbpI;
    }
}

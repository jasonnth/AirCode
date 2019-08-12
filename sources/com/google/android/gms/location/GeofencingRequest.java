package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.zzasm;
import java.util.ArrayList;
import java.util.List;

public class GeofencingRequest extends zza {
    public static final Creator<GeofencingRequest> CREATOR = new zzi();
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    private final String mTag;
    private final List<zzasm> zzbjN;
    private final int zzbjO;

    GeofencingRequest(List<zzasm> list, int i, String str) {
        this.zzbjN = list;
        this.zzbjO = i;
        this.mTag = str;
    }

    public List<Geofence> getGeofences() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zzbjN);
        return arrayList;
    }

    public int getInitialTrigger() {
        return this.zzbjO;
    }

    public String getTag() {
        return this.mTag;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }

    public List<zzasm> zzIf() {
        return this.zzbjN;
    }
}

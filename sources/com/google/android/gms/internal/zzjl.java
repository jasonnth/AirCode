package com.google.android.gms.internal;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

@zzme
public class zzjl implements zzjk {
    private final zzjj zzKl;
    private final HashSet<SimpleEntry<String, zzid>> zzKm = new HashSet<>();

    public zzjl(zzjj zzjj) {
        this.zzKl = zzjj;
    }

    public void zza(String str, zzid zzid) {
        this.zzKl.zza(str, zzid);
        this.zzKm.add(new SimpleEntry(str, zzid));
    }

    public void zza(String str, JSONObject jSONObject) {
        this.zzKl.zza(str, jSONObject);
    }

    public void zzb(String str, zzid zzid) {
        this.zzKl.zzb(str, zzid);
        this.zzKm.remove(new SimpleEntry(str, zzid));
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzKl.zzb(str, jSONObject);
    }

    public void zzgT() {
        Iterator it = this.zzKm.iterator();
        while (it.hasNext()) {
            SimpleEntry simpleEntry = (SimpleEntry) it.next();
            String str = "Unregistering eventhandler: ";
            String valueOf = String.valueOf(((zzid) simpleEntry.getValue()).toString());
            zzpk.m1279v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzKl.zzb((String) simpleEntry.getKey(), (zzid) simpleEntry.getValue());
        }
        this.zzKm.clear();
    }

    public void zzj(String str, String str2) {
        this.zzKl.zzj(str, str2);
    }
}

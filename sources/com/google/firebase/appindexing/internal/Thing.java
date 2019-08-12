package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.firebase.appindexing.Indexable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;

public final class Thing extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable, Indexable {
    public static final Creator<Thing> CREATOR = new zzp();
    private final String zzGV;
    private final String zzUA;
    public final int zzaiI;
    private final Bundle zzaib;
    private final zza zzbXo;

    public static class zza extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final Creator<zza> CREATOR = new zzn();
        private final boolean zzbXm;
        private String zzbXn;
        private int zzxA;

        public zza(boolean z, int i, String str) {
            this.zzbXm = z;
            this.zzxA = i;
            this.zzbXn = str;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return zzaa.equal(Boolean.valueOf(this.zzbXm), Boolean.valueOf(zza.zzbXm)) && zzaa.equal(Integer.valueOf(this.zzxA), Integer.valueOf(zza.zzxA)) && zzaa.equal(this.zzbXn, zza.zzbXn);
        }

        public int getScore() {
            return this.zzxA;
        }

        public int hashCode() {
            return zzaa.hashCode(Boolean.valueOf(this.zzbXm), Integer.valueOf(this.zzxA), this.zzbXn);
        }

        public String toString() {
            String str = "";
            if (!zzVB().isEmpty()) {
                String str2 = ", accountEmail: ";
                String valueOf = String.valueOf(zzVB());
                str = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
            }
            return new StringBuilder(String.valueOf(str).length() + 39).append("worksOffline: ").append(zzVA()).append(", score: ").append(getScore()).append(str).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzn.zza(this, parcel, i);
        }

        public boolean zzVA() {
            return this.zzbXm;
        }

        public String zzVB() {
            return this.zzbXn;
        }
    }

    public Thing(int i, Bundle bundle, zza zza2, String str, String str2) {
        this.zzaiI = i;
        this.zzaib = bundle;
        this.zzbXo = zza2;
        this.zzGV = str;
        this.zzUA = str2;
        this.zzaib.setClassLoader(getClass().getClassLoader());
    }

    public Thing(Bundle bundle, zza zza2, String str, String str2) {
        this.zzaiI = 6;
        this.zzaib = bundle;
        this.zzbXo = zza2;
        this.zzGV = str;
        this.zzUA = str2;
    }

    public String getId() {
        return this.zzGV;
    }

    public String getType() {
        return this.zzUA;
    }

    public int getVersionCode() {
        return this.zzaiI;
    }

    public String toString() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(zzVz()).append(" { ");
        StringBuilder append = sb2.append("{ id: ");
        if (getId() == null) {
            sb = "<null> } ";
        } else {
            String valueOf = String.valueOf(getId());
            sb = new StringBuilder(String.valueOf(valueOf).length() + 5).append("'").append(valueOf).append("' } ").toString();
        }
        append.append(sb);
        sb2.append("Properties { ");
        Set keySet = this.zzaib.keySet();
        String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
        Arrays.sort(strArr);
        for (String str : strArr) {
            sb2.append("{ key: '").append(str).append("' value: ");
            Object obj = this.zzaib.get(str);
            if (obj == null) {
                sb2.append("<null>");
            } else if (obj.getClass().isArray()) {
                sb2.append("[ ");
                for (int i = 0; i < Array.getLength(obj); i++) {
                    sb2.append("'").append(Array.get(obj, i)).append("' ");
                }
                sb2.append("]");
            } else {
                sb2.append(obj.toString());
            }
            sb2.append(" } ");
        }
        sb2.append("} ");
        sb2.append("Metadata { ");
        sb2.append(this.zzbXo.toString());
        sb2.append(" } ");
        sb2.append("}");
        return sb2.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzp.zza(this, parcel, i);
    }

    public zza zzVy() {
        return this.zzbXo;
    }

    public String zzVz() {
        return this.zzUA.equals("Thing") ? "Indexable" : this.zzUA;
    }

    public Bundle zzqK() {
        return this.zzaib;
    }
}

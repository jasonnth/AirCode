package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.firebase.appindexing.Action;

public class zza extends com.google.android.gms.common.internal.safeparcel.zza implements Action {
    public static final Creator<zza> CREATOR = new zzb();
    private final String zzbXa;
    private final String zzbXb;
    private final String zzbXc;
    private final String zzbXd;
    private final C7828zza zzbXe;
    private final String zzbXf;

    /* renamed from: com.google.firebase.appindexing.internal.zza$zza reason: collision with other inner class name */
    public static class C7828zza extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final Creator<C7828zza> CREATOR = new zzo();
        private int zzahV = 0;
        private final String zzaiu;
        private final boolean zzbXh;
        private final boolean zzbXi;
        private final String zzbXp;
        private final byte[] zzbXq;

        C7828zza(int i, boolean z, String str, String str2, byte[] bArr, boolean z2) {
            this.zzahV = i;
            this.zzbXh = z;
            this.zzbXp = str;
            this.zzaiu = str2;
            this.zzbXq = bArr;
            this.zzbXi = z2;
        }

        public C7828zza(boolean z, String str, String str2, byte[] bArr, boolean z2) {
            this.zzbXh = z;
            this.zzbXp = str;
            this.zzaiu = str2;
            this.zzbXq = bArr;
            this.zzbXi = z2;
        }

        public String getAccountName() {
            return this.zzaiu;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MetadataImpl { ");
            sb.append("{ eventStatus: '").append(this.zzahV).append("' } ");
            sb.append("{ uploadable: '").append(this.zzbXh).append("' } ");
            if (this.zzbXp != null) {
                sb.append("{ completionToken: '").append(this.zzbXp).append("' } ");
            }
            if (this.zzaiu != null) {
                sb.append("{ accountName: '").append(this.zzaiu).append("' } ");
            }
            if (this.zzbXq != null) {
                sb.append("{ ssbContext: [ ");
                for (byte hexString : this.zzbXq) {
                    sb.append("0x").append(Integer.toHexString(hexString)).append(" ");
                }
                sb.append("] } ");
            }
            sb.append("{ contextOnly: '").append(this.zzbXi).append("' } ");
            sb.append("}");
            return sb.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzo.zza(this, parcel, i);
        }

        public int zzVr() {
            return this.zzahV;
        }

        public boolean zzVs() {
            return this.zzbXh;
        }

        public String zzVt() {
            return this.zzbXp;
        }

        public byte[] zzVu() {
            return this.zzbXq;
        }

        public boolean zzVv() {
            return this.zzbXi;
        }

        public void zzpY(int i) {
            this.zzahV = i;
        }
    }

    public zza(String str, String str2, String str3, String str4, C7828zza zza, String str5) {
        this.zzbXa = str;
        this.zzbXb = str2;
        this.zzbXc = str3;
        this.zzbXd = str4;
        this.zzbXe = zza;
        this.zzbXf = str5;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ActionImpl { ");
        sb.append("{ actionType: '").append(this.zzbXa).append("' } ");
        sb.append("{ objectName: '").append(this.zzbXb).append("' } ");
        sb.append("{ objectUrl: '").append(this.zzbXc).append("' } ");
        if (this.zzbXd != null) {
            sb.append("{ objectSameAs: '").append(this.zzbXd).append("' } ");
        }
        if (this.zzbXe != null) {
            sb.append("{ metadata: '").append(this.zzbXe.toString()).append("' } ");
        }
        if (this.zzbXf != null) {
            sb.append("{ actionStatus: '").append(this.zzbXf).append("' } ");
        }
        sb.append("}");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public String zzVl() {
        return this.zzbXa;
    }

    public String zzVm() {
        return this.zzbXb;
    }

    public String zzVn() {
        return this.zzbXc;
    }

    public String zzVo() {
        return this.zzbXd;
    }

    public C7828zza zzVp() {
        return this.zzbXe;
    }

    public String zzVq() {
        return this.zzbXf;
    }
}

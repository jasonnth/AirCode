package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@zzme
public class zzpb {
    public final int errorCode;
    public final int orientation;
    public final List<String> zzKF;
    public final List<String> zzKG;
    public final List<String> zzKI;
    public final long zzKL;
    public final zzjq zzLi;
    public final zzkb zzLj;
    public final String zzLk;
    public final zzjt zzLl;
    public final zzqw zzNH;
    public final String zzRB;
    public final zzec zzRy;
    public final zzoo zzSC;
    public final List<String> zzSE;
    public final boolean zzSF;
    public final zzmp zzSG;
    public final String zzSJ;
    public final long zzSm;
    public final boolean zzSn;
    public final long zzSo;
    public final List<String> zzSp;
    public final String zzSs;
    public final JSONObject zzWa;
    public boolean zzWb;
    public final zzjr zzWc;
    public final String zzWd;
    public final zzeg zzWe;
    public final List<String> zzWf;
    public final long zzWg;
    public final long zzWh;
    public final com.google.android.gms.internal.zzha.zza zzWi;
    public boolean zzWj;
    public boolean zzWk;
    public boolean zzWl;

    @zzme
    public static final class zza {
        public final int errorCode;
        public final zzmk zzTi;
        public final JSONObject zzWa;
        public final zzjr zzWc;
        public final long zzWg;
        public final long zzWh;
        public final zzmn zzWm;
        public final zzeg zzvr;

        public zza(zzmk zzmk, zzmn zzmn, zzjr zzjr, zzeg zzeg, int i, long j, long j2, JSONObject jSONObject) {
            this.zzTi = zzmk;
            this.zzWm = zzmn;
            this.zzWc = zzjr;
            this.zzvr = zzeg;
            this.errorCode = i;
            this.zzWg = j;
            this.zzWh = j2;
            this.zzWa = jSONObject;
        }
    }

    public zzpb(zzec zzec, zzqw zzqw, List<String> list, int i, List<String> list2, List<String> list3, int i2, long j, String str, boolean z, zzjq zzjq, zzkb zzkb, String str2, zzjr zzjr, zzjt zzjt, long j2, zzeg zzeg, long j3, long j4, long j5, String str3, JSONObject jSONObject, com.google.android.gms.internal.zzha.zza zza2, zzoo zzoo, List<String> list4, List<String> list5, boolean z2, zzmp zzmp, String str4, List<String> list6, String str5) {
        this.zzWj = false;
        this.zzWk = false;
        this.zzWl = false;
        this.zzRy = zzec;
        this.zzNH = zzqw;
        this.zzKF = zzm(list);
        this.errorCode = i;
        this.zzKG = zzm(list2);
        this.zzSp = zzm(list3);
        this.orientation = i2;
        this.zzKL = j;
        this.zzRB = str;
        this.zzSn = z;
        this.zzLi = zzjq;
        this.zzLj = zzkb;
        this.zzLk = str2;
        this.zzWc = zzjr;
        this.zzLl = zzjt;
        this.zzSo = j2;
        this.zzWe = zzeg;
        this.zzSm = j3;
        this.zzWg = j4;
        this.zzWh = j5;
        this.zzSs = str3;
        this.zzWa = jSONObject;
        this.zzWi = zza2;
        this.zzSC = zzoo;
        this.zzWf = zzm(list4);
        this.zzSE = zzm(list5);
        this.zzSF = z2;
        this.zzSG = zzmp;
        this.zzWd = str4;
        this.zzKI = zzm(list6);
        this.zzSJ = str5;
    }

    public zzpb(zza zza2, zzqw zzqw, zzjq zzjq, zzkb zzkb, String str, zzjt zzjt, com.google.android.gms.internal.zzha.zza zza3, String str2) {
        this(zza2.zzTi.zzRy, zzqw, zza2.zzWm.zzKF, zza2.errorCode, zza2.zzWm.zzKG, zza2.zzWm.zzSp, zza2.zzWm.orientation, zza2.zzWm.zzKL, zza2.zzTi.zzRB, zza2.zzWm.zzSn, zzjq, zzkb, str, zza2.zzWc, zzjt, zza2.zzWm.zzSo, zza2.zzvr, zza2.zzWm.zzSm, zza2.zzWg, zza2.zzWh, zza2.zzWm.zzSs, zza2.zzWa, zza3, zza2.zzWm.zzSC, zza2.zzWm.zzSD, zza2.zzWm.zzSD, zza2.zzWm.zzSF, zza2.zzWm.zzSG, str2, zza2.zzWm.zzKI, zza2.zzWm.zzSJ);
    }

    private static <T> List<T> zzm(List<T> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public boolean zzdD() {
        if (this.zzNH == null || this.zzNH.zzlv() == null) {
            return false;
        }
        return this.zzNH.zzlv().zzdD();
    }
}

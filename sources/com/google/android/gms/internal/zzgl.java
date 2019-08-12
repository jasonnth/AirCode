package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zzme
public class zzgl {
    private final List<zzgj> zzFO = new LinkedList();
    private final Map<String, String> zzFP = new LinkedHashMap();
    private String zzFQ;
    private zzgj zzFR;
    private zzgl zzFS;
    boolean zzFx;
    private final Object zzrJ = new Object();

    public zzgl(boolean z, String str, String str2) {
        this.zzFx = z;
        this.zzFP.put("action", str);
        this.zzFP.put("ad_format", str2);
    }

    public void zzX(String str) {
        if (this.zzFx) {
            synchronized (this.zzrJ) {
                this.zzFQ = str;
            }
        }
    }

    public boolean zza(zzgj zzgj, long j, String... strArr) {
        synchronized (this.zzrJ) {
            for (String zzgj2 : strArr) {
                this.zzFO.add(new zzgj(j, zzgj2, zzgj));
            }
        }
        return true;
    }

    public boolean zza(zzgj zzgj, String... strArr) {
        if (!this.zzFx || zzgj == null) {
            return false;
        }
        return zza(zzgj, zzw.zzcS().elapsedRealtime(), strArr);
    }

    public zzgj zzc(long j) {
        if (!this.zzFx) {
            return null;
        }
        return new zzgj(j, null, null);
    }

    public void zzc(zzgl zzgl) {
        synchronized (this.zzrJ) {
            this.zzFS = zzgl;
        }
    }

    public zzgj zzfB() {
        return zzc(zzw.zzcS().elapsedRealtime());
    }

    public void zzfC() {
        synchronized (this.zzrJ) {
            this.zzFR = zzfB();
        }
    }

    public String zzfD() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        synchronized (this.zzrJ) {
            for (zzgj zzgj : this.zzFO) {
                long time = zzgj.getTime();
                String zzfy = zzgj.zzfy();
                zzgj zzfz = zzgj.zzfz();
                if (zzfz != null && time > 0) {
                    sb2.append(zzfy).append('.').append(time - zzfz.getTime()).append(',');
                }
            }
            this.zzFO.clear();
            if (!TextUtils.isEmpty(this.zzFQ)) {
                sb2.append(this.zzFQ);
            } else if (sb2.length() > 0) {
                sb2.setLength(sb2.length() - 1);
            }
            sb = sb2.toString();
        }
        return sb;
    }

    /* access modifiers changed from: 0000 */
    public Map<String, String> zzfE() {
        Map<String, String> zza;
        synchronized (this.zzrJ) {
            zzgf zzkk = zzw.zzcQ().zzkk();
            zza = (zzkk == null || this.zzFS == null) ? this.zzFP : zzkk.zza(this.zzFP, this.zzFS.zzfE());
        }
        return zza;
    }

    public zzgj zzfF() {
        zzgj zzgj;
        synchronized (this.zzrJ) {
            zzgj = this.zzFR;
        }
        return zzgj;
    }

    public void zzh(String str, String str2) {
        if (this.zzFx && !TextUtils.isEmpty(str2)) {
            zzgf zzkk = zzw.zzcQ().zzkk();
            if (zzkk != null) {
                synchronized (this.zzrJ) {
                    zzkk.zzV(str).zza(this.zzFP, str, str2);
                }
            }
        }
    }
}

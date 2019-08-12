package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.zzw;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzmg extends zzpj implements com.google.android.gms.internal.zzmh.zza {
    private final Context mContext;
    zzjr zzKY;
    private zzmk zzLo;
    zzmn zzPS;
    /* access modifiers changed from: private */
    public Runnable zzPT;
    /* access modifiers changed from: private */
    public final Object zzPU = new Object();
    private final com.google.android.gms.internal.zzmf.zza zzRl;
    /* access modifiers changed from: private */
    public final com.google.android.gms.internal.zzmk.zza zzRm;
    zzpq zzRn;

    @zzme
    static final class zza extends Exception {
        private final int zzPY;

        public zza(String str, int i) {
            super(str);
            this.zzPY = i;
        }

        public int getErrorCode() {
            return this.zzPY;
        }
    }

    public zzmg(Context context, com.google.android.gms.internal.zzmk.zza zza2, com.google.android.gms.internal.zzmf.zza zza3) {
        this.zzRl = zza3;
        this.mContext = context;
        this.zzRm = zza2;
    }

    /* access modifiers changed from: private */
    public void zzd(int i, String str) {
        if (i == 3 || i == -1) {
            zzpk.zzbg(str);
        } else {
            zzpk.zzbh(str);
        }
        if (this.zzPS == null) {
            this.zzPS = new zzmn(i);
        } else {
            this.zzPS = new zzmn(i, this.zzPS.zzKL);
        }
        this.zzRl.zza(new com.google.android.gms.internal.zzpb.zza(this.zzLo != null ? this.zzLo : new zzmk(this.zzRm, -1, null, null, null), this.zzPS, this.zzKY, null, i, -1, this.zzPS.zzSr, null));
    }

    public void onStop() {
        synchronized (this.zzPU) {
            if (this.zzRn != null) {
                this.zzRn.cancel();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public zzpq zza(zzqh zzqh, zzqp<zzmk> zzqp) {
        return zzmh.zza(this.mContext, zzqh, zzqp, this);
    }

    public void zza(zzmn zzmn) {
        JSONObject jSONObject;
        zzpk.zzbf("Received ad response.");
        this.zzPS = zzmn;
        long elapsedRealtime = zzw.zzcS().elapsedRealtime();
        synchronized (this.zzPU) {
            this.zzRn = null;
        }
        zzw.zzcQ().zzd(this.mContext, this.zzPS.zzRV);
        if (((Boolean) zzgd.zzDc.get()).booleanValue()) {
            if (this.zzPS.zzSh) {
                zzw.zzcQ().zzk(this.mContext, this.zzLo.zzvl);
            } else {
                zzw.zzcQ().zzl(this.mContext, this.zzLo.zzvl);
            }
        }
        try {
            if (this.zzPS.errorCode == -2 || this.zzPS.errorCode == -3) {
                zzjm();
                zzeg zzeg = this.zzLo.zzvr.zzzA != null ? zzb(this.zzLo) : null;
                zzw.zzcQ().zzE(this.zzPS.zzSx);
                zzw.zzcQ().zzF(this.zzPS.zzSK);
                if (!TextUtils.isEmpty(this.zzPS.zzSv)) {
                    try {
                        jSONObject = new JSONObject(this.zzPS.zzSv);
                    } catch (Exception e) {
                        zzpk.zzb("Error parsing the JSON for Active View.", e);
                    }
                    this.zzRl.zza(new com.google.android.gms.internal.zzpb.zza(this.zzLo, this.zzPS, this.zzKY, zzeg, -2, elapsedRealtime, this.zzPS.zzSr, jSONObject));
                    zzpo.zzXC.removeCallbacks(this.zzPT);
                    return;
                }
                jSONObject = null;
                this.zzRl.zza(new com.google.android.gms.internal.zzpb.zza(this.zzLo, this.zzPS, this.zzKY, zzeg, -2, elapsedRealtime, this.zzPS.zzSr, jSONObject));
                zzpo.zzXC.removeCallbacks(this.zzPT);
                return;
            }
            throw new zza("There was a problem getting an ad response. ErrorCode: " + this.zzPS.errorCode, this.zzPS.errorCode);
        } catch (zza e2) {
            zzd(e2.getErrorCode(), e2.getMessage());
            zzpo.zzXC.removeCallbacks(this.zzPT);
        }
    }

    /* access modifiers changed from: protected */
    public zzeg zzb(zzmk zzmk) throws zza {
        zzeg[] zzegArr;
        if (this.zzPS.zzzC) {
            for (zzeg zzeg : zzmk.zzvr.zzzA) {
                if (zzeg.zzzC) {
                    return new zzeg(zzeg, zzmk.zzvr.zzzA);
                }
            }
        }
        if (this.zzPS.zzSq == null) {
            throw new zza("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.zzPS.zzSq.split("x");
        if (split.length != 2) {
            String str = "Invalid ad size format from the ad response: ";
            String valueOf = String.valueOf(this.zzPS.zzSq);
            throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            zzeg[] zzegArr2 = zzmk.zzvr.zzzA;
            int length = zzegArr2.length;
            for (int i = 0; i < length; i++) {
                zzeg zzeg2 = zzegArr2[i];
                float f = this.mContext.getResources().getDisplayMetrics().density;
                int i2 = zzeg2.width == -1 ? (int) (((float) zzeg2.widthPixels) / f) : zzeg2.width;
                int i3 = zzeg2.height == -2 ? (int) (((float) zzeg2.heightPixels) / f) : zzeg2.height;
                if (parseInt == i2 && parseInt2 == i3 && !zzeg2.zzzC) {
                    return new zzeg(zzeg2, zzmk.zzvr.zzzA);
                }
            }
            String str2 = "The ad size from the ad response was not one of the requested sizes: ";
            String valueOf2 = String.valueOf(this.zzPS.zzSq);
            throw new zza(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), 0);
        } catch (NumberFormatException e) {
            String str3 = "Invalid ad size number from the ad response: ";
            String valueOf3 = String.valueOf(this.zzPS.zzSq);
            throw new zza(valueOf3.length() != 0 ? str3.concat(valueOf3) : new String(str3), 0);
        }
    }

    public void zzco() {
        zzpk.zzbf("AdLoaderBackgroundTask started.");
        this.zzPT = new Runnable() {
            public void run() {
                synchronized (zzmg.this.zzPU) {
                    if (zzmg.this.zzRn != null) {
                        zzmg.this.onStop();
                        zzmg.this.zzd(2, "Timed out waiting for ad response.");
                    }
                }
            }
        };
        zzpo.zzXC.postDelayed(this.zzPT, ((Long) zzgd.zzDL.get()).longValue());
        long elapsedRealtime = zzw.zzcS().elapsedRealtime();
        if (((Boolean) zzgd.zzDK.get()).booleanValue() && this.zzRm.zzRy.extras != null) {
            String string = this.zzRm.zzRy.extras.getString("_ad");
            if (string != null) {
                this.zzLo = new zzmk(this.zzRm, elapsedRealtime, null, null, null);
                zza(zznd.zza(this.mContext, this.zzLo, string));
                return;
            }
        }
        final zzqq zzqq = new zzqq();
        zzpn.zza((Runnable) new Runnable() {
            public void run() {
                synchronized (zzmg.this.zzPU) {
                    zzmg.this.zzRn = zzmg.this.zza(zzmg.this.zzRm.zzvn, zzqq);
                    if (zzmg.this.zzRn == null) {
                        zzmg.this.zzd(0, "Could not start the ad request service.");
                        zzpo.zzXC.removeCallbacks(zzmg.this.zzPT);
                    }
                }
            }
        });
        String zzD = zzw.zzdl().zzD(this.mContext);
        String zzE = zzw.zzdl().zzE(this.mContext);
        String zzF = zzw.zzdl().zzF(this.mContext);
        zzw.zzdl().zzh(this.mContext, zzF);
        this.zzLo = new zzmk(this.zzRm, elapsedRealtime, zzD, zzE, zzF);
        zzqq.zzg(this.zzLo);
    }

    /* access modifiers changed from: protected */
    public void zzjm() throws zza {
        if (this.zzPS.errorCode != -3) {
            if (TextUtils.isEmpty(this.zzPS.body)) {
                throw new zza("No fill from ad server.", 3);
            }
            zzw.zzcQ().zzc(this.mContext, this.zzPS.zzRG);
            if (this.zzPS.zzSn) {
                try {
                    this.zzKY = new zzjr(this.zzPS.body);
                    zzw.zzcQ().zzG(this.zzKY.zzKJ);
                } catch (JSONException e) {
                    zzpk.zzb("Could not parse mediation config.", e);
                    String str = "Could not parse mediation config: ";
                    String valueOf = String.valueOf(this.zzPS.body);
                    throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
                }
            } else {
                zzw.zzcQ().zzG(this.zzPS.zzKJ);
            }
            if (!TextUtils.isEmpty(this.zzPS.zzRW) && ((Boolean) zzgd.zzFb.get()).booleanValue()) {
                zzpk.zzbf("Received cookie from server. Setting webview cookie in CookieManager.");
                CookieManager zzX = zzw.zzcO().zzX(this.mContext);
                if (zzX != null) {
                    zzX.setCookie("googleads.g.doubleclick.net", this.zzPS.zzRW);
                }
            }
        }
    }
}

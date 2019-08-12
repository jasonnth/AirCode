package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.internal.zzpb.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzok extends zzpj implements zzoj {
    private final Context mContext;
    private final zza zzPR;
    private final ArrayList<Future> zzVG;
    private final ArrayList<String> zzVH;
    private final HashMap<String, zzoe> zzVI;
    private final List<zzof> zzVJ;
    private final HashSet<String> zzVK;
    /* access modifiers changed from: private */
    public final zzns zzVL;
    private final long zzVv;
    private final Object zzrJ;

    public zzok(Context context, zza zza, zzns zzns) {
        this(context, zza, zzns, ((Long) zzgd.zzCO.get()).longValue());
    }

    zzok(Context context, zza zza, zzns zzns, long j) {
        this.zzVG = new ArrayList<>();
        this.zzVH = new ArrayList<>();
        this.zzVI = new HashMap<>();
        this.zzVJ = new ArrayList();
        this.zzVK = new HashSet<>();
        this.zzrJ = new Object();
        this.mContext = context;
        this.zzPR = zza;
        this.zzVL = zzns;
        this.zzVv = j;
    }

    private static int zzT(int i) {
        switch (i) {
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 4;
            case 6:
                return 0;
            case 7:
                return 3;
            default:
                return 6;
        }
    }

    private zzpb zza(int i, String str, zzjq zzjq) {
        return new zzpb(this.zzPR.zzTi.zzRy, null, this.zzPR.zzWm.zzKF, i, this.zzPR.zzWm.zzKG, this.zzPR.zzWm.zzSp, this.zzPR.zzWm.orientation, this.zzPR.zzWm.zzKL, this.zzPR.zzTi.zzRB, this.zzPR.zzWm.zzSn, zzjq, null, str, this.zzPR.zzWc, null, this.zzPR.zzWm.zzSo, this.zzPR.zzvr, this.zzPR.zzWm.zzSm, this.zzPR.zzWg, this.zzPR.zzWm.zzSr, this.zzPR.zzWm.zzSs, this.zzPR.zzWa, null, this.zzPR.zzWm.zzSC, this.zzPR.zzWm.zzSD, this.zzPR.zzWm.zzSE, this.zzPR.zzWm.zzSF, this.zzPR.zzWm.zzSG, zzjM(), this.zzPR.zzWm.zzKI, this.zzPR.zzWm.zzSJ);
    }

    private zzpb zza(String str, zzjq zzjq) {
        return zza(-2, str, zzjq);
    }

    private static String zza(zzof zzof) {
        String str = zzof.zzKq;
        int zzT = zzT(zzof.errorCode);
        return new StringBuilder(String.valueOf(str).length() + 33).append(str).append(".").append(zzT).append(".").append(zzof.zzLn).toString();
    }

    private void zza(String str, String str2, zzjq zzjq) {
        synchronized (this.zzrJ) {
            zzol zzaN = this.zzVL.zzaN(str);
            if (zzaN == null || zzaN.zzjO() == null || zzaN.zzjN() == null) {
                this.zzVJ.add(new zzof.zza().zzaQ(zzjq.zzKq).zzaP(str).zzl(0).zzae(7).zzjK());
                return;
            }
            zzoe zza = zza(str, str2, zzjq, zzaN);
            this.zzVG.add((Future) zza.zziP());
            this.zzVH.add(str);
            this.zzVI.put(str, zza);
        }
    }

    private zzpb zzjL() {
        return zza(3, (String) null, (zzjq) null);
    }

    private String zzjM() {
        StringBuilder sb = new StringBuilder("");
        if (this.zzVJ == null) {
            return sb.toString();
        }
        for (zzof zzof : this.zzVJ) {
            if (zzof != null && !TextUtils.isEmpty(zzof.zzKq)) {
                sb.append(String.valueOf(zza(zzof)).concat("_"));
            }
        }
        return sb.substring(0, Math.max(0, sb.length() - 1));
    }

    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public zzoe zza(String str, String str2, zzjq zzjq, zzol zzol) {
        return new zzoe(this.mContext, str, str2, zzjq, this.zzPR, zzol, this, this.zzVv);
    }

    public void zza(String str, int i) {
    }

    public void zzaO(String str) {
        synchronized (this.zzrJ) {
            this.zzVK.add(str);
        }
    }

    public void zzco() {
        for (zzjq zzjq : this.zzPR.zzWc.zzKD) {
            String str = zzjq.zzKv;
            for (String str2 : zzjq.zzKp) {
                if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str2) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str2)) {
                    try {
                        str2 = new JSONObject(str).getString("class_name");
                    } catch (JSONException e) {
                        zzpk.zzb("Unable to determine custom event class name, skipping...", e);
                    }
                }
                zza(str2, str, zzjq);
            }
        }
        int i = 0;
        while (i < this.zzVG.size()) {
            try {
                ((Future) this.zzVG.get(i)).get();
                synchronized (this.zzrJ) {
                    String str3 = (String) this.zzVH.get(i);
                    if (!TextUtils.isEmpty(str3)) {
                        zzoe zzoe = (zzoe) this.zzVI.get(str3);
                        if (zzoe != null) {
                            this.zzVJ.add(zzoe.zzjH());
                        }
                    }
                }
                synchronized (this.zzrJ) {
                    if (this.zzVK.contains(this.zzVH.get(i))) {
                        String str4 = (String) this.zzVH.get(i);
                        final zzpb zza = zza(str4, this.zzVI.get(str4) != null ? ((zzoe) this.zzVI.get(str4)).zzjI() : null);
                        zzqe.zzYP.post(new Runnable() {
                            public void run() {
                                zzok.this.zzVL.zzb(zza);
                            }
                        });
                        return;
                    }
                }
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                synchronized (this.zzrJ) {
                    String str5 = (String) this.zzVH.get(i);
                    if (!TextUtils.isEmpty(str5)) {
                        zzoe zzoe2 = (zzoe) this.zzVI.get(str5);
                        if (zzoe2 != null) {
                            this.zzVJ.add(zzoe2.zzjH());
                        }
                    }
                }
            } catch (Exception e3) {
                zzpk.zzc("Unable to resolve rewarded adapter.", e3);
                synchronized (this.zzrJ) {
                    String str6 = (String) this.zzVH.get(i);
                    if (!TextUtils.isEmpty(str6)) {
                        zzoe zzoe3 = (zzoe) this.zzVI.get(str6);
                        if (zzoe3 != null) {
                            this.zzVJ.add(zzoe3.zzjH());
                        }
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                synchronized (this.zzrJ) {
                    String str7 = (String) this.zzVH.get(i);
                    if (!TextUtils.isEmpty(str7)) {
                        zzoe zzoe4 = (zzoe) this.zzVI.get(str7);
                        if (zzoe4 != null) {
                            this.zzVJ.add(zzoe4.zzjH());
                        }
                    }
                    throw th2;
                }
            }
        }
        final zzpb zzjL = zzjL();
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                zzok.this.zzVL.zzb(zzjL);
            }
        });
        return;
        i++;
    }
}

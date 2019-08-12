package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

@zzme
public class zzmx extends zzpj {
    /* access modifiers changed from: private */
    public static zzji zzQn = null;
    static final long zzSV = TimeUnit.SECONDS.toMillis(10);
    static boolean zzSW = false;
    private static zzie zzSX = null;
    /* access modifiers changed from: private */
    public static zzii zzSY = null;
    private static zzid zzSZ = null;
    private static final Object zztX = new Object();
    private final Context mContext;
    private final Object zzPU = new Object();
    /* access modifiers changed from: private */
    public final com.google.android.gms.internal.zzmf.zza zzRl;
    private final com.google.android.gms.internal.zzmk.zza zzRm;
    /* access modifiers changed from: private */
    public com.google.android.gms.internal.zzji.zzc zzTa;

    public static class zza implements zzpt<zzjf> {
        /* renamed from: zza */
        public void zzd(zzjf zzjf) {
            zzmx.zzc(zzjf);
        }
    }

    public static class zzb implements zzpt<zzjf> {
        /* renamed from: zza */
        public void zzd(zzjf zzjf) {
            zzmx.zzb(zzjf);
        }
    }

    public static class zzc implements zzid {
        public void zza(zzqw zzqw, Map<String, String> map) {
            String str = (String) map.get("request_id");
            String str2 = "Invalid request: ";
            String valueOf = String.valueOf((String) map.get("errors"));
            zzpk.zzbh(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            zzmx.zzSY.zzac(str);
        }
    }

    public zzmx(Context context, com.google.android.gms.internal.zzmk.zza zza2, com.google.android.gms.internal.zzmf.zza zza3) {
        super(true);
        this.zzRl = zza3;
        this.mContext = context;
        this.zzRm = zza2;
        synchronized (zztX) {
            if (!zzSW) {
                zzSY = new zzii();
                zzSX = new zzie(context.getApplicationContext(), zza2.zzvn);
                zzSZ = new zzc();
                zzQn = new zzji(this.mContext.getApplicationContext(), this.zzRm.zzvn, (String) zzgd.zzBh.get(), new zzb(), new zza());
                zzSW = true;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.json.JSONObject] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject zza(com.google.android.gms.internal.zzmk r7, java.lang.String r8) {
        /*
            r6 = this;
            r1 = 0
            com.google.android.gms.internal.zzec r0 = r7.zzRy
            android.os.Bundle r0 = r0.extras
            java.lang.String r2 = "sdk_less_server_data"
            android.os.Bundle r2 = r0.getBundle(r2)
            if (r2 != 0) goto L_0x000f
        L_0x000e:
            return r1
        L_0x000f:
            com.google.android.gms.internal.zznj r0 = com.google.android.gms.ads.internal.zzw.zzcV()     // Catch:{ Exception -> 0x0077 }
            android.content.Context r3 = r6.mContext     // Catch:{ Exception -> 0x0077 }
            java.util.concurrent.Future r0 = r0.zzA(r3)     // Catch:{ Exception -> 0x0077 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0077 }
            com.google.android.gms.internal.zzni r0 = (com.google.android.gms.internal.zzni) r0     // Catch:{ Exception -> 0x0077 }
        L_0x001f:
            android.content.Context r3 = r6.mContext
            com.google.android.gms.internal.zzna r4 = new com.google.android.gms.internal.zzna
            r4.<init>()
            com.google.android.gms.internal.zzna r4 = r4.zzf(r7)
            com.google.android.gms.internal.zzna r0 = r4.zza(r0)
            org.json.JSONObject r3 = com.google.android.gms.internal.zznd.zza(r3, r0)
            if (r3 == 0) goto L_0x000e
            android.content.Context r0 = r6.mContext     // Catch:{ IOException -> 0x0091, IllegalStateException -> 0x008d, GooglePlayServicesNotAvailableException -> 0x008f, GooglePlayServicesRepairableException -> 0x0080 }
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r0 = com.google.android.gms.ads.identifier.AdvertisingIdClient.getAdvertisingIdInfo(r0)     // Catch:{ IOException -> 0x0091, IllegalStateException -> 0x008d, GooglePlayServicesNotAvailableException -> 0x008f, GooglePlayServicesRepairableException -> 0x0080 }
        L_0x003a:
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.lang.String r5 = "request_id"
            r4.put(r5, r8)
            java.lang.String r5 = "request_param"
            r4.put(r5, r3)
            java.lang.String r3 = "data"
            r4.put(r3, r2)
            if (r0 == 0) goto L_0x006e
            java.lang.String r2 = "adid"
            java.lang.String r3 = r0.getId()
            r4.put(r2, r3)
            java.lang.String r2 = "lat"
            boolean r0 = r0.isLimitAdTrackingEnabled()
            if (r0 == 0) goto L_0x0089
            r0 = 1
        L_0x0067:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r4.put(r2, r0)
        L_0x006e:
            com.google.android.gms.internal.zzpo r0 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ JSONException -> 0x008b }
            org.json.JSONObject r1 = r0.zzQ(r4)     // Catch:{ JSONException -> 0x008b }
            goto L_0x000e
        L_0x0077:
            r0 = move-exception
            java.lang.String r3 = "Error grabbing device info: "
            com.google.android.gms.internal.zzpk.zzc(r3, r0)
            r0 = r1
            goto L_0x001f
        L_0x0080:
            r0 = move-exception
        L_0x0081:
            java.lang.String r4 = "Cannot get advertising id info"
            com.google.android.gms.internal.zzpk.zzc(r4, r0)
            r0 = r1
            goto L_0x003a
        L_0x0089:
            r0 = 0
            goto L_0x0067
        L_0x008b:
            r0 = move-exception
            goto L_0x000e
        L_0x008d:
            r0 = move-exception
            goto L_0x0081
        L_0x008f:
            r0 = move-exception
            goto L_0x0081
        L_0x0091:
            r0 = move-exception
            goto L_0x0081
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmx.zza(com.google.android.gms.internal.zzmk, java.lang.String):org.json.JSONObject");
    }

    protected static void zzb(zzjf zzjf) {
        zzjf.zza("/loadAd", (zzid) zzSY);
        zzjf.zza("/fetchHttpRequest", (zzid) zzSX);
        zzjf.zza("/invalidRequest", zzSZ);
    }

    protected static void zzc(zzjf zzjf) {
        zzjf.zzb("/loadAd", (zzid) zzSY);
        zzjf.zzb("/fetchHttpRequest", (zzid) zzSX);
        zzjf.zzb("/invalidRequest", zzSZ);
    }

    private zzmn zze(zzmk zzmk) {
        final String zzkL = zzw.zzcM().zzkL();
        final JSONObject zza2 = zza(zzmk, zzkL);
        if (zza2 == null) {
            return new zzmn(0);
        }
        long elapsedRealtime = zzw.zzcS().elapsedRealtime();
        Future zzab = zzSY.zzab(zzkL);
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                zzmx.this.zzTa = zzmx.zzQn.zzgO();
                zzmx.this.zzTa.zza(new com.google.android.gms.internal.zzqp.zzc<zzjj>() {
                    /* renamed from: zzb */
                    public void zzd(zzjj zzjj) {
                        try {
                            zzjj.zza("AFMA_getAdapterLessMediationAd", zza2);
                        } catch (Exception e) {
                            zzpk.zzb("Error requesting an ad url", e);
                            zzmx.zzSY.zzac(zzkL);
                        }
                    }
                }, new com.google.android.gms.internal.zzqp.zza() {
                    public void run() {
                        zzmx.zzSY.zzac(zzkL);
                    }
                });
            }
        });
        try {
            JSONObject jSONObject = (JSONObject) zzab.get(zzSV - (zzw.zzcS().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new zzmn(-1);
            }
            zzmn zza3 = zznd.zza(this.mContext, zzmk, jSONObject.toString());
            return (zza3.errorCode == -3 || !TextUtils.isEmpty(zza3.body)) ? zza3 : new zzmn(3);
        } catch (InterruptedException | CancellationException e) {
            return new zzmn(-1);
        } catch (TimeoutException e2) {
            return new zzmn(2);
        } catch (ExecutionException e3) {
            return new zzmn(0);
        }
    }

    public void onStop() {
        synchronized (this.zzPU) {
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    if (zzmx.this.zzTa != null) {
                        zzmx.this.zzTa.release();
                        zzmx.this.zzTa = null;
                    }
                }
            });
        }
    }

    public void zzco() {
        zzpk.zzbf("SdkLessAdLoaderBackgroundTask started.");
        String zzF = zzw.zzdl().zzF(this.mContext);
        zzmk zzmk = new zzmk(this.zzRm, -1, zzw.zzdl().zzD(this.mContext), zzw.zzdl().zzE(this.mContext), zzF);
        zzw.zzdl().zzh(this.mContext, zzF);
        zzmn zze = zze(zzmk);
        zzmk zzmk2 = zzmk;
        final com.google.android.gms.internal.zzpb.zza zza2 = new com.google.android.gms.internal.zzpb.zza(zzmk2, zze, null, null, zze.errorCode, zzw.zzcS().elapsedRealtime(), zze.zzSr, null);
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                zzmx.this.zzRl.zza(zza2);
                if (zzmx.this.zzTa != null) {
                    zzmx.this.zzTa.release();
                    zzmx.this.zzTa = null;
                }
            }
        });
    }
}

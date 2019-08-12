package com.google.android.gms.internal;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zze;
import com.google.android.gms.analytics.zzf;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzac;
import java.util.HashMap;
import java.util.Map.Entry;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

class zzsi extends zzsa {
    private boolean mStarted;
    private final zzsg zzaeA;
    private final zztf zzaeB;
    private final zzte zzaeC;
    private final zzsf zzaeD;
    private long zzaeE = Long.MIN_VALUE;
    private final zzsr zzaeF;
    private final zzsr zzaeG;
    private final zztj zzaeH;
    private long zzaeI;
    private boolean zzaeJ;

    protected zzsi(zzsc zzsc, zzsd zzsd) {
        super(zzsc);
        zzac.zzw(zzsd);
        this.zzaeC = zzsd.zzk(zzsc);
        this.zzaeA = zzsd.zzm(zzsc);
        this.zzaeB = zzsd.zzn(zzsc);
        this.zzaeD = zzsd.zzo(zzsc);
        this.zzaeH = new zztj(zznR());
        this.zzaeF = new zzsr(zzsc) {
            public void run() {
                zzsi.this.zzoB();
            }
        };
        this.zzaeG = new zzsr(zzsc) {
            public void run() {
                zzsi.this.zzoC();
            }
        };
    }

    private void zza(zzse zzse, zzrl zzrl) {
        zzac.zzw(zzse);
        zzac.zzw(zzrl);
        zza zza = new zza(zznQ());
        zza.zzbo(zzse.zzok());
        zza.enableAdvertisingIdCollection(zzse.zzol());
        zze zzmo = zza.zzmo();
        zzrt zzrt = (zzrt) zzmo.zzb(zzrt.class);
        zzrt.zzbE("data");
        zzrt.zzS(true);
        zzmo.zza((zzf) zzrl);
        zzro zzro = (zzro) zzmo.zzb(zzro.class);
        zzrk zzrk = (zzrk) zzmo.zzb(zzrk.class);
        for (Entry entry : zzse.zzfE().entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if ("an".equals(str)) {
                zzrk.setAppName(str2);
            } else if ("av".equals(str)) {
                zzrk.setAppVersion(str2);
            } else if ("aid".equals(str)) {
                zzrk.setAppId(str2);
            } else if ("aiid".equals(str)) {
                zzrk.setAppInstallerId(str2);
            } else if (JPushReportInterface.UID.equals(str)) {
                zzrt.setUserId(str2);
            } else {
                zzro.set(str, str2);
            }
        }
        zzb("Sending installation campaign to", zzse.zzok(), zzrl);
        zzmo.zzq(zznW().zzqe());
        zzmo.zzmG();
    }

    private boolean zzbW(String str) {
        return zzadg.zzbi(getContext()).checkCallingOrSelfPermission(str) == 0;
    }

    /* access modifiers changed from: private */
    public void zzoB() {
        zzb((zzsu) new zzsu() {
            public void zzf(Throwable th) {
                zzsi.this.zzoH();
            }
        });
    }

    /* access modifiers changed from: private */
    public void zzoC() {
        try {
            this.zzaeA.zzot();
            zzoH();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzaeG.zzy(86400000);
    }

    private boolean zzoI() {
        return !this.zzaeJ && zzoO() > 0;
    }

    private void zzoJ() {
        zzst zznV = zznV();
        if (zznV.zzpD() && !zznV.zzcy()) {
            long zzou = zzou();
            if (zzou != 0 && Math.abs(zznR().currentTimeMillis() - zzou) <= zznT().zzpf()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zznT().zzpe()));
                zznV.schedule();
            }
        }
    }

    private void zzoK() {
        long min;
        zzoJ();
        long zzoO = zzoO();
        long zzqg = zznW().zzqg();
        if (zzqg != 0) {
            min = zzoO - Math.abs(zznR().currentTimeMillis() - zzqg);
            if (min <= 0) {
                min = Math.min(zznT().zzpc(), zzoO);
            }
        } else {
            min = Math.min(zznT().zzpc(), zzoO);
        }
        zza("Dispatch scheduled (ms)", Long.valueOf(min));
        if (this.zzaeF.zzcy()) {
            this.zzaeF.zzz(Math.max(1, min + this.zzaeF.zzpA()));
            return;
        }
        this.zzaeF.zzy(min);
    }

    private void zzoL() {
        zzoM();
        zzoN();
    }

    private void zzoM() {
        if (this.zzaeF.zzcy()) {
            zzbP("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzaeF.cancel();
    }

    private void zzoN() {
        zzst zznV = zznV();
        if (zznV.zzcy()) {
            zznV.cancel();
        }
    }

    private void zzoz() {
        zzmR();
        Context context = zznQ().getContext();
        if (!zzth.zzak(context)) {
            zzbS("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzti.zzal(context)) {
            zzbT("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zzak(context)) {
            zzbS("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!CampaignTrackingService.zzal(context)) {
            zzbS("CampaignTrackingService is not registered or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
    }

    /* access modifiers changed from: protected */
    public void onServiceConnected() {
        zzmR();
        zzoE();
    }

    /* access modifiers changed from: 0000 */
    public void start() {
        zzob();
        zzac.zza(!this.mStarted, (Object) "Analytics backend already started");
        this.mStarted = true;
        zznU().zzg(new Runnable() {
            public void run() {
                zzsi.this.zzoA();
            }
        });
    }

    public void zzV(boolean z) {
        zzoH();
    }

    public long zza(zzse zzse, boolean z) {
        zzac.zzw(zzse);
        zzob();
        zzmR();
        try {
            this.zzaeA.beginTransaction();
            this.zzaeA.zza(zzse.zzoj(), zzse.zzmy());
            long zza = this.zzaeA.zza(zzse.zzoj(), zzse.zzmy(), zzse.zzok());
            if (!z) {
                zzse.zzs(zza);
            } else {
                zzse.zzs(1 + zza);
            }
            this.zzaeA.zzb(zzse);
            this.zzaeA.setTransactionSuccessful();
            try {
                return zza;
            } catch (SQLiteException e) {
                zze("Failed to end transaction", e);
                return zza;
            }
        } catch (SQLiteException e2) {
            zze("Failed to update Analytics property", e2);
            try {
            } catch (SQLiteException e3) {
                zze("Failed to end transaction", e3);
            }
            return -1;
        } finally {
            try {
                this.zzaeA.endTransaction();
            } catch (SQLiteException e4) {
                zze("Failed to end transaction", e4);
            }
        }
    }

    public void zza(zzsu zzsu, long j) {
        zzh.zzmR();
        zzob();
        long j2 = -1;
        long zzqg = zznW().zzqg();
        if (zzqg != 0) {
            j2 = Math.abs(zznR().currentTimeMillis() - zzqg);
        }
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(j2));
        zzoD();
        try {
            zzoF();
            zznW().zzqh();
            zzoH();
            if (zzsu != null) {
                zzsu.zzf(null);
            }
            if (this.zzaeI != j) {
                this.zzaeC.zzpZ();
            }
        } catch (Throwable th) {
            zze("Local dispatch failed", th);
            zznW().zzqh();
            zzoH();
            if (zzsu != null) {
                zzsu.zzf(th);
            }
        }
    }

    public void zza(zzsz zzsz) {
        zzac.zzw(zzsz);
        zzh.zzmR();
        zzob();
        if (this.zzaeJ) {
            zzbQ("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", zzsz);
        }
        zzsz zzf = zzf(zzsz);
        zzoD();
        if (this.zzaeD.zzb(zzf)) {
            zzbQ("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        try {
            this.zzaeA.zzc(zzf);
            zzoH();
        } catch (SQLiteException e) {
            zze("Delivery failed to save hit to a database", e);
            zznS().zza(zzf, "deliver: failed to insert hit to database");
        }
    }

    public void zzb(zzsu zzsu) {
        zza(zzsu, this.zzaeI);
    }

    public void zzbX(String str) {
        zzac.zzdr(str);
        zzmR();
        zzrl zza = zztm.zza(zznS(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        String zzqi = zznW().zzqi();
        if (str.equals(zzqi)) {
            zzbS("Ignoring duplicate install campaign");
        } else if (!TextUtils.isEmpty(zzqi)) {
            zzd("Ignoring multiple install campaigns. original, new", zzqi, str);
        } else {
            zznW().zzcb(str);
            if (zznW().zzqf().zzA(zznT().zzpy())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzse zza2 : this.zzaeA.zzw(0)) {
                zza(zza2, zza);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzc(zzse zzse) {
        zzmR();
        zzb("Sending first hit to property", zzse.zzok());
        if (!zznW().zzqf().zzA(zznT().zzpy())) {
            String zzqi = zznW().zzqi();
            if (!TextUtils.isEmpty(zzqi)) {
                zzrl zza = zztm.zza(zznS(), zzqi);
                zzb("Found relevant installation campaign", zza);
                zza(zzse, zza);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public zzsz zzf(zzsz zzsz) {
        if (!TextUtils.isEmpty(zzsz.zzpU())) {
            return zzsz;
        }
        Pair zzqm = zznW().zzqj().zzqm();
        if (zzqm == null) {
            return zzsz;
        }
        Long l = (Long) zzqm.second;
        String str = (String) zzqm.first;
        String valueOf = String.valueOf(l);
        String sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
        HashMap hashMap = new HashMap(zzsz.zzfE());
        hashMap.put("_m", sb);
        return zzsz.zza(this, zzsz, hashMap);
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        this.zzaeA.initialize();
        this.zzaeB.initialize();
        this.zzaeD.initialize();
    }

    public void zznN() {
        zzh.zzmR();
        zzob();
        zzbP("Service disconnected");
    }

    /* access modifiers changed from: 0000 */
    public void zznP() {
        zzmR();
        this.zzaeI = zznR().currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void zzoA() {
        zzob();
        zzoz();
        zznW().zzqe();
        if (!zzbW("android.permission.ACCESS_NETWORK_STATE")) {
            zzbT("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzoP();
        }
        if (!zzbW("android.permission.INTERNET")) {
            zzbT("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzoP();
        }
        if (zzti.zzal(getContext())) {
            zzbP("AnalyticsService registered in the app manifest and enabled");
        } else {
            zzbS("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!this.zzaeJ && !this.zzaeA.isEmpty()) {
            zzoD();
        }
        zzoH();
    }

    /* access modifiers changed from: protected */
    public void zzoD() {
        if (!this.zzaeJ && zznT().zzoX() && !this.zzaeD.isConnected()) {
            if (this.zzaeH.zzA(zznT().zzps())) {
                this.zzaeH.start();
                zzbP("Connecting to service");
                if (this.zzaeD.connect()) {
                    zzbP("Connected to service");
                    this.zzaeH.clear();
                    onServiceConnected();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0063 A[LOOP:1: B:18:0x0063->B:17:?, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0048 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzoE() {
        /*
            r6 = this;
            com.google.android.gms.analytics.zzh.zzmR()
            r6.zzob()
            com.google.android.gms.internal.zzsp r0 = r6.zznT()
            boolean r0 = r0.zzoX()
            if (r0 != 0) goto L_0x0016
            java.lang.String r0 = "Service client disabled. Can't dispatch local hits to device AnalyticsService"
            r6.zzbS(r0)
        L_0x0016:
            com.google.android.gms.internal.zzsf r0 = r6.zzaeD
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x0025
            java.lang.String r0 = "Service not connected"
            r6.zzbP(r0)
        L_0x0024:
            return
        L_0x0025:
            com.google.android.gms.internal.zzsg r0 = r6.zzaeA
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0024
            java.lang.String r0 = "Dispatching local hits to device AnalyticsService"
            r6.zzbP(r0)
        L_0x0033:
            com.google.android.gms.internal.zzsg r0 = r6.zzaeA     // Catch:{ SQLiteException -> 0x004c }
            com.google.android.gms.internal.zzsp r1 = r6.zznT()     // Catch:{ SQLiteException -> 0x004c }
            int r1 = r1.zzpg()     // Catch:{ SQLiteException -> 0x004c }
            long r2 = (long) r1     // Catch:{ SQLiteException -> 0x004c }
            java.util.List r1 = r0.zzu(r2)     // Catch:{ SQLiteException -> 0x004c }
            boolean r0 = r1.isEmpty()     // Catch:{ SQLiteException -> 0x004c }
            if (r0 == 0) goto L_0x0063
            r6.zzoH()     // Catch:{ SQLiteException -> 0x004c }
            goto L_0x0024
        L_0x004c:
            r0 = move-exception
            java.lang.String r1 = "Failed to read hits from store"
            r6.zze(r1, r0)
            r6.zzoL()
            goto L_0x0024
        L_0x0057:
            r1.remove(r0)
            com.google.android.gms.internal.zzsg r2 = r6.zzaeA     // Catch:{ SQLiteException -> 0x007c }
            long r4 = r0.zzpP()     // Catch:{ SQLiteException -> 0x007c }
            r2.zzv(r4)     // Catch:{ SQLiteException -> 0x007c }
        L_0x0063:
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x0033
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.zzsz r0 = (com.google.android.gms.internal.zzsz) r0
            com.google.android.gms.internal.zzsf r2 = r6.zzaeD
            boolean r2 = r2.zzb(r0)
            if (r2 != 0) goto L_0x0057
            r6.zzoH()
            goto L_0x0024
        L_0x007c:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove hit that was send for delivery"
            r6.zze(r1, r0)
            r6.zzoL()
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsi.zzoE():void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f8, code lost:
        if (r12.zzaeD.isConnected() == false) goto L_0x0200;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00fa, code lost:
        zzbP("Service connected, sending hits to the service");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0104, code lost:
        if (r8.isEmpty() != false) goto L_0x0200;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0106, code lost:
        r0 = (com.google.android.gms.internal.zzsz) r8.get(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0113, code lost:
        if (r12.zzaeD.zzb(r0) != false) goto L_0x013e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0115, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011c, code lost:
        if (r12.zzaeB.zzqa() == false) goto L_0x018f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011e, code lost:
        r8 = r12.zzaeB.zzt(r8);
        r9 = r8.iterator();
        r4 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x012d, code lost:
        if (r9.hasNext() == false) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x012f, code lost:
        r4 = java.lang.Math.max(r4, ((java.lang.Long) r9.next()).longValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x013e, code lost:
        r4 = java.lang.Math.max(r4, r0.zzpP());
        r8.remove(r0);
        zzb("Hit sent do device AnalyticsService for delivery", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        r12.zzaeA.zzv(r0.zzpP());
        r3.add(java.lang.Long.valueOf(r0.zzpP()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0164, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        zze("Failed to remove hit that was send for delivery", r0);
        zzoL();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r12.zzaeA.setTransactionSuccessful();
        r12.zzaeA.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x017a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x017b, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzoL();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        r12.zzaeA.zzr(r8);
        r3.addAll(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x018e, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0193, code lost:
        if (r3.isEmpty() == false) goto L_0x01cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01a1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01a2, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzoL();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01ad, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
        zze("Failed to remove successfully uploaded hits", r0);
        zzoL();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
        r12.zzaeA.setTransactionSuccessful();
        r12.zzaeA.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01c3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01c4, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzoL();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        r12.zzaeA.setTransactionSuccessful();
        r12.zzaeA.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01dc, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01dd, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzoL();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0200, code lost:
        r0 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean zzoF() {
        /*
            r12 = this;
            r1 = 1
            r2 = 0
            com.google.android.gms.analytics.zzh.zzmR()
            r12.zzob()
            java.lang.String r0 = "Dispatching a batch of local hits"
            r12.zzbP(r0)
            com.google.android.gms.internal.zzsf r0 = r12.zzaeD
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x002a
            r0 = r1
        L_0x0017:
            com.google.android.gms.internal.zztf r3 = r12.zzaeB
            boolean r3 = r3.zzqa()
            if (r3 != 0) goto L_0x002c
        L_0x001f:
            if (r0 == 0) goto L_0x002e
            if (r1 == 0) goto L_0x002e
            java.lang.String r0 = "No network or service available. Will retry later"
            r12.zzbP(r0)
        L_0x0029:
            return r2
        L_0x002a:
            r0 = r2
            goto L_0x0017
        L_0x002c:
            r1 = r2
            goto L_0x001f
        L_0x002e:
            com.google.android.gms.internal.zzsp r0 = r12.zznT()
            int r0 = r0.zzpg()
            com.google.android.gms.internal.zzsp r1 = r12.zznT()
            int r1 = r1.zzph()
            int r0 = java.lang.Math.max(r0, r1)
            long r6 = (long) r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = 0
        L_0x004a:
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ all -> 0x01e8 }
            r0.beginTransaction()     // Catch:{ all -> 0x01e8 }
            r3.clear()     // Catch:{ all -> 0x01e8 }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x00d0 }
            java.util.List r8 = r0.zzu(r6)     // Catch:{ SQLiteException -> 0x00d0 }
            boolean r0 = r8.isEmpty()     // Catch:{ SQLiteException -> 0x00d0 }
            if (r0 == 0) goto L_0x007d
            java.lang.String r0 = "Store is empty, nothing to dispatch"
            r12.zzbP(r0)     // Catch:{ SQLiteException -> 0x00d0 }
            r12.zzoL()     // Catch:{ SQLiteException -> 0x00d0 }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x0072 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x0072 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x0072 }
            goto L_0x0029
        L_0x0072:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzoL()
            goto L_0x0029
        L_0x007d:
            java.lang.String r0 = "Hits loaded from store. count"
            int r1 = r8.size()     // Catch:{ SQLiteException -> 0x00d0 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x00d0 }
            r12.zza(r0, r1)     // Catch:{ SQLiteException -> 0x00d0 }
            java.util.Iterator r1 = r8.iterator()     // Catch:{ all -> 0x01e8 }
        L_0x008f:
            boolean r0 = r1.hasNext()     // Catch:{ all -> 0x01e8 }
            if (r0 == 0) goto L_0x00f2
            java.lang.Object r0 = r1.next()     // Catch:{ all -> 0x01e8 }
            com.google.android.gms.internal.zzsz r0 = (com.google.android.gms.internal.zzsz) r0     // Catch:{ all -> 0x01e8 }
            long r10 = r0.zzpP()     // Catch:{ all -> 0x01e8 }
            int r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x008f
            java.lang.String r0 = "Database contains successfully uploaded hit"
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x01e8 }
            int r3 = r8.size()     // Catch:{ all -> 0x01e8 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x01e8 }
            r12.zzd(r0, r1, r3)     // Catch:{ all -> 0x01e8 }
            r12.zzoL()     // Catch:{ all -> 0x01e8 }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x00c4 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x00c4 }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x00c4 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x00c4 }
            goto L_0x0029
        L_0x00c4:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzoL()
            goto L_0x0029
        L_0x00d0:
            r0 = move-exception
            java.lang.String r1 = "Failed to read hits from persisted store"
            r12.zzd(r1, r0)     // Catch:{ all -> 0x01e8 }
            r12.zzoL()     // Catch:{ all -> 0x01e8 }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x00e6 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x00e6 }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x00e6 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x00e6 }
            goto L_0x0029
        L_0x00e6:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzoL()
            goto L_0x0029
        L_0x00f2:
            com.google.android.gms.internal.zzsf r0 = r12.zzaeD     // Catch:{ all -> 0x01e8 }
            boolean r0 = r0.isConnected()     // Catch:{ all -> 0x01e8 }
            if (r0 == 0) goto L_0x0200
            java.lang.String r0 = "Service connected, sending hits to the service"
            r12.zzbP(r0)     // Catch:{ all -> 0x01e8 }
        L_0x0100:
            boolean r0 = r8.isEmpty()     // Catch:{ all -> 0x01e8 }
            if (r0 != 0) goto L_0x0200
            r0 = 0
            java.lang.Object r0 = r8.get(r0)     // Catch:{ all -> 0x01e8 }
            com.google.android.gms.internal.zzsz r0 = (com.google.android.gms.internal.zzsz) r0     // Catch:{ all -> 0x01e8 }
            com.google.android.gms.internal.zzsf r1 = r12.zzaeD     // Catch:{ all -> 0x01e8 }
            boolean r1 = r1.zzb(r0)     // Catch:{ all -> 0x01e8 }
            if (r1 != 0) goto L_0x013e
            r0 = r4
        L_0x0116:
            com.google.android.gms.internal.zztf r4 = r12.zzaeB     // Catch:{ all -> 0x01e8 }
            boolean r4 = r4.zzqa()     // Catch:{ all -> 0x01e8 }
            if (r4 == 0) goto L_0x018f
            com.google.android.gms.internal.zztf r4 = r12.zzaeB     // Catch:{ all -> 0x01e8 }
            java.util.List r8 = r4.zzt(r8)     // Catch:{ all -> 0x01e8 }
            java.util.Iterator r9 = r8.iterator()     // Catch:{ all -> 0x01e8 }
            r4 = r0
        L_0x0129:
            boolean r0 = r9.hasNext()     // Catch:{ all -> 0x01e8 }
            if (r0 == 0) goto L_0x0186
            java.lang.Object r0 = r9.next()     // Catch:{ all -> 0x01e8 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ all -> 0x01e8 }
            long r0 = r0.longValue()     // Catch:{ all -> 0x01e8 }
            long r4 = java.lang.Math.max(r4, r0)     // Catch:{ all -> 0x01e8 }
            goto L_0x0129
        L_0x013e:
            long r10 = r0.zzpP()     // Catch:{ all -> 0x01e8 }
            long r4 = java.lang.Math.max(r4, r10)     // Catch:{ all -> 0x01e8 }
            r8.remove(r0)     // Catch:{ all -> 0x01e8 }
            java.lang.String r1 = "Hit sent do device AnalyticsService for delivery"
            r12.zzb(r1, r0)     // Catch:{ all -> 0x01e8 }
            com.google.android.gms.internal.zzsg r1 = r12.zzaeA     // Catch:{ SQLiteException -> 0x0164 }
            long r10 = r0.zzpP()     // Catch:{ SQLiteException -> 0x0164 }
            r1.zzv(r10)     // Catch:{ SQLiteException -> 0x0164 }
            long r0 = r0.zzpP()     // Catch:{ SQLiteException -> 0x0164 }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ SQLiteException -> 0x0164 }
            r3.add(r0)     // Catch:{ SQLiteException -> 0x0164 }
            goto L_0x0100
        L_0x0164:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove hit that was send for delivery"
            r12.zze(r1, r0)     // Catch:{ all -> 0x01e8 }
            r12.zzoL()     // Catch:{ all -> 0x01e8 }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x017a }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x017a }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x017a }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x017a }
            goto L_0x0029
        L_0x017a:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzoL()
            goto L_0x0029
        L_0x0186:
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x01ad }
            r0.zzr(r8)     // Catch:{ SQLiteException -> 0x01ad }
            r3.addAll(r8)     // Catch:{ SQLiteException -> 0x01ad }
            r0 = r4
        L_0x018f:
            boolean r4 = r3.isEmpty()     // Catch:{ all -> 0x01e8 }
            if (r4 == 0) goto L_0x01cf
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x01a1 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01a1 }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x01a1 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x01a1 }
            goto L_0x0029
        L_0x01a1:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzoL()
            goto L_0x0029
        L_0x01ad:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove successfully uploaded hits"
            r12.zze(r1, r0)     // Catch:{ all -> 0x01e8 }
            r12.zzoL()     // Catch:{ all -> 0x01e8 }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x01c3 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01c3 }
            com.google.android.gms.internal.zzsg r0 = r12.zzaeA     // Catch:{ SQLiteException -> 0x01c3 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x01c3 }
            goto L_0x0029
        L_0x01c3:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzoL()
            goto L_0x0029
        L_0x01cf:
            com.google.android.gms.internal.zzsg r4 = r12.zzaeA     // Catch:{ SQLiteException -> 0x01dc }
            r4.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01dc }
            com.google.android.gms.internal.zzsg r4 = r12.zzaeA     // Catch:{ SQLiteException -> 0x01dc }
            r4.endTransaction()     // Catch:{ SQLiteException -> 0x01dc }
            r4 = r0
            goto L_0x004a
        L_0x01dc:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzoL()
            goto L_0x0029
        L_0x01e8:
            r0 = move-exception
            com.google.android.gms.internal.zzsg r1 = r12.zzaeA     // Catch:{ SQLiteException -> 0x01f4 }
            r1.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01f4 }
            com.google.android.gms.internal.zzsg r1 = r12.zzaeA     // Catch:{ SQLiteException -> 0x01f4 }
            r1.endTransaction()     // Catch:{ SQLiteException -> 0x01f4 }
            throw r0
        L_0x01f4:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzoL()
            goto L_0x0029
        L_0x0200:
            r0 = r4
            goto L_0x0116
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsi.zzoF():boolean");
    }

    public void zzoH() {
        boolean z;
        zznQ().zzmR();
        zzob();
        if (!zzoI()) {
            this.zzaeC.unregister();
            zzoL();
        } else if (this.zzaeA.isEmpty()) {
            this.zzaeC.unregister();
            zzoL();
        } else {
            if (!((Boolean) zzsw.zzafS.get()).booleanValue()) {
                this.zzaeC.zzpX();
                z = this.zzaeC.isConnected();
            } else {
                z = true;
            }
            if (z) {
                zzoK();
                return;
            }
            zzoL();
            zzoJ();
        }
    }

    public long zzoO() {
        if (this.zzaeE != Long.MIN_VALUE) {
            return this.zzaeE;
        }
        return zzmB().zzpK() ? ((long) zzmB().zzqB()) * 1000 : zznT().zzpd();
    }

    public void zzoP() {
        zzob();
        zzmR();
        this.zzaeJ = true;
        this.zzaeD.disconnect();
        zzoH();
    }

    public long zzou() {
        zzh.zzmR();
        zzob();
        try {
            return this.zzaeA.zzou();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }
}

package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzac;
import java.util.Map;
import p005cn.jpush.android.JPushConstants;

@zzme
public class zzji {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final String zzJK;
    /* access modifiers changed from: private */
    public zzpt<zzjf> zzJL;
    private zzpt<zzjf> zzJM;
    /* access modifiers changed from: private */
    public zzd zzJN;
    /* access modifiers changed from: private */
    public int zzJO;
    /* access modifiers changed from: private */
    public final Object zzrJ;
    /* access modifiers changed from: private */
    public final zzqh zztt;

    static class zza {
        static int zzJZ = JPushConstants.ONE_MINUTE;
        static int zzKa = 10000;
    }

    public static class zzb<T> implements zzpt<T> {
        public void zzd(T t) {
        }
    }

    public static class zzc extends zzqq<zzjj> {
        /* access modifiers changed from: private */
        public final zzd zzKb;
        private boolean zzKc;
        private final Object zzrJ = new Object();

        public zzc(zzd zzd) {
            this.zzKb = zzd;
        }

        public void release() {
            synchronized (this.zzrJ) {
                if (!this.zzKc) {
                    this.zzKc = true;
                    zza(new com.google.android.gms.internal.zzqp.zzc<zzjj>(this) {
                        /* renamed from: zzb */
                        public void zzd(zzjj zzjj) {
                            zzpk.m1279v("Ending javascript session.");
                            ((zzjk) zzjj).zzgT();
                        }
                    }, new com.google.android.gms.internal.zzqp.zzb());
                    zza(new com.google.android.gms.internal.zzqp.zzc<zzjj>() {
                        /* renamed from: zzb */
                        public void zzd(zzjj zzjj) {
                            zzpk.m1279v("Releasing engine reference.");
                            zzc.this.zzKb.zzgQ();
                        }
                    }, new com.google.android.gms.internal.zzqp.zza() {
                        public void run() {
                            zzc.this.zzKb.zzgQ();
                        }
                    });
                }
            }
        }
    }

    public static class zzd extends zzqq<zzjf> {
        /* access modifiers changed from: private */
        public zzpt<zzjf> zzJM;
        private boolean zzKe;
        private int zzKf;
        private final Object zzrJ = new Object();

        public zzd(zzpt<zzjf> zzpt) {
            this.zzJM = zzpt;
            this.zzKe = false;
            this.zzKf = 0;
        }

        public zzc zzgP() {
            final zzc zzc = new zzc(this);
            synchronized (this.zzrJ) {
                zza(new com.google.android.gms.internal.zzqp.zzc<zzjf>(this) {
                    /* renamed from: zza */
                    public void zzd(zzjf zzjf) {
                        zzpk.m1279v("Getting a new session for JS Engine.");
                        zzc.zzg(zzjf.zzgM());
                    }
                }, new com.google.android.gms.internal.zzqp.zza(this) {
                    public void run() {
                        zzpk.m1279v("Rejecting reference for JS Engine.");
                        zzc.reject();
                    }
                });
                zzac.zzav(this.zzKf >= 0);
                this.zzKf++;
            }
            return zzc;
        }

        /* access modifiers changed from: protected */
        public void zzgQ() {
            boolean z = true;
            synchronized (this.zzrJ) {
                if (this.zzKf < 1) {
                    z = false;
                }
                zzac.zzav(z);
                zzpk.m1279v("Releasing 1 reference for JS Engine");
                this.zzKf--;
                zzgS();
            }
        }

        public void zzgR() {
            boolean z = true;
            synchronized (this.zzrJ) {
                if (this.zzKf < 0) {
                    z = false;
                }
                zzac.zzav(z);
                zzpk.m1279v("Releasing root reference. JS Engine will be destroyed once other references are released.");
                this.zzKe = true;
                zzgS();
            }
        }

        /* access modifiers changed from: protected */
        public void zzgS() {
            synchronized (this.zzrJ) {
                zzac.zzav(this.zzKf >= 0);
                if (!this.zzKe || this.zzKf != 0) {
                    zzpk.m1279v("There are still references to the engine. Not destroying.");
                } else {
                    zzpk.m1279v("No reference is left (including root). Cleaning up engine.");
                    zza(new com.google.android.gms.internal.zzqp.zzc<zzjf>() {
                        /* renamed from: zza */
                        public void zzd(final zzjf zzjf) {
                            zzw.zzcM().runOnUiThread(new Runnable() {
                                public void run() {
                                    zzd.this.zzJM.zzd(zzjf);
                                    zzjf.destroy();
                                }
                            });
                        }
                    }, new com.google.android.gms.internal.zzqp.zzb());
                }
            }
        }
    }

    public static class zze extends zzqq<zzjj> {
        private zzc zzKk;

        public zze(zzc zzc) {
            this.zzKk = zzc;
        }

        public void finalize() {
            this.zzKk.release();
            this.zzKk = null;
        }

        public int getStatus() {
            return this.zzKk.getStatus();
        }

        public void reject() {
            this.zzKk.reject();
        }

        public void zza(com.google.android.gms.internal.zzqp.zzc<zzjj> zzc, com.google.android.gms.internal.zzqp.zza zza) {
            this.zzKk.zza(zzc, zza);
        }

        /* renamed from: zzj */
        public void zzg(zzjj zzjj) {
            this.zzKk.zzg(zzjj);
        }
    }

    public zzji(Context context, zzqh zzqh, String str) {
        this.zzrJ = new Object();
        this.zzJO = 1;
        this.zzJK = str;
        this.mContext = context.getApplicationContext();
        this.zztt = zzqh;
        this.zzJL = new zzb();
        this.zzJM = new zzb();
    }

    public zzji(Context context, zzqh zzqh, String str, zzpt<zzjf> zzpt, zzpt<zzjf> zzpt2) {
        this(context, zzqh, str);
        this.zzJL = zzpt;
        this.zzJM = zzpt2;
    }

    private zzd zza(final zzaw zzaw) {
        final zzd zzd2 = new zzd(this.zzJM);
        zzw.zzcM().runOnUiThread(new Runnable() {
            public void run() {
                final zzjf zza = zzji.this.zza(zzji.this.mContext, zzji.this.zztt, zzaw);
                zza.zza(new com.google.android.gms.internal.zzjf.zza() {
                    public void zzgN() {
                        zzpo.zzXC.postDelayed(new Runnable() {
                            /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
                                return;
                             */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public void run() {
                                /*
                                    r3 = this;
                                    com.google.android.gms.internal.zzji$1$1 r0 = com.google.android.gms.internal.zzji.C12891.C12901.this
                                    com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this
                                    com.google.android.gms.internal.zzji r0 = com.google.android.gms.internal.zzji.this
                                    java.lang.Object r1 = r0.zzrJ
                                    monitor-enter(r1)
                                    com.google.android.gms.internal.zzji$1$1 r0 = com.google.android.gms.internal.zzji.C12891.C12901.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$zzd r0 = r0     // Catch:{ all -> 0x0044 }
                                    int r0 = r0.getStatus()     // Catch:{ all -> 0x0044 }
                                    r2 = -1
                                    if (r0 == r2) goto L_0x0025
                                    com.google.android.gms.internal.zzji$1$1 r0 = com.google.android.gms.internal.zzji.C12891.C12901.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$zzd r0 = r0     // Catch:{ all -> 0x0044 }
                                    int r0 = r0.getStatus()     // Catch:{ all -> 0x0044 }
                                    r2 = 1
                                    if (r0 != r2) goto L_0x0027
                                L_0x0025:
                                    monitor-exit(r1)     // Catch:{ all -> 0x0044 }
                                L_0x0026:
                                    return
                                L_0x0027:
                                    com.google.android.gms.internal.zzji$1$1 r0 = com.google.android.gms.internal.zzji.C12891.C12901.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$zzd r0 = r0     // Catch:{ all -> 0x0044 }
                                    r0.reject()     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzpo r0 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$1$1$1$1 r2 = new com.google.android.gms.internal.zzji$1$1$1$1     // Catch:{ all -> 0x0044 }
                                    r2.<init>()     // Catch:{ all -> 0x0044 }
                                    r0.runOnUiThread(r2)     // Catch:{ all -> 0x0044 }
                                    java.lang.String r0 = "Could not receive loaded message in a timely manner. Rejecting."
                                    com.google.android.gms.internal.zzpk.m1279v(r0)     // Catch:{ all -> 0x0044 }
                                    monitor-exit(r1)     // Catch:{ all -> 0x0044 }
                                    goto L_0x0026
                                L_0x0044:
                                    r0 = move-exception
                                    monitor-exit(r1)     // Catch:{ all -> 0x0044 }
                                    throw r0
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzji.C12891.C12901.C12911.run():void");
                            }
                        }, (long) zza.zzKa);
                    }
                });
                zza.zza("/jsLoaded", (zzid) new zzid() {
                    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
                        return;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void zza(com.google.android.gms.internal.zzqw r4, java.util.Map<java.lang.String, java.lang.String> r5) {
                        /*
                            r3 = this;
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this
                            com.google.android.gms.internal.zzji r0 = com.google.android.gms.internal.zzji.this
                            java.lang.Object r1 = r0.zzrJ
                            monitor-enter(r1)
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$zzd r0 = r0     // Catch:{ all -> 0x0052 }
                            int r0 = r0.getStatus()     // Catch:{ all -> 0x0052 }
                            r2 = -1
                            if (r0 == r2) goto L_0x001f
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$zzd r0 = r0     // Catch:{ all -> 0x0052 }
                            int r0 = r0.getStatus()     // Catch:{ all -> 0x0052 }
                            r2 = 1
                            if (r0 != r2) goto L_0x0021
                        L_0x001f:
                            monitor-exit(r1)     // Catch:{ all -> 0x0052 }
                        L_0x0020:
                            return
                        L_0x0021:
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji r0 = com.google.android.gms.internal.zzji.this     // Catch:{ all -> 0x0052 }
                            r2 = 0
                            r0.zzJO = r2     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji r0 = com.google.android.gms.internal.zzji.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzpt r0 = r0.zzJL     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzjf r2 = r0     // Catch:{ all -> 0x0052 }
                            r0.zzd(r2)     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$zzd r0 = r0     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzjf r2 = r0     // Catch:{ all -> 0x0052 }
                            r0.zzg(r2)     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji r0 = com.google.android.gms.internal.zzji.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$1 r2 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$zzd r2 = r0     // Catch:{ all -> 0x0052 }
                            r0.zzJN = r2     // Catch:{ all -> 0x0052 }
                            java.lang.String r0 = "Successfully loaded JS Engine."
                            com.google.android.gms.internal.zzpk.m1279v(r0)     // Catch:{ all -> 0x0052 }
                            monitor-exit(r1)     // Catch:{ all -> 0x0052 }
                            goto L_0x0020
                        L_0x0052:
                            r0 = move-exception
                            monitor-exit(r1)     // Catch:{ all -> 0x0052 }
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzji.C12891.C12932.zza(com.google.android.gms.internal.zzqw, java.util.Map):void");
                    }
                });
                final zzqa zzqa = new zzqa();
                C12943 r2 = new zzid() {
                    public void zza(zzqw zzqw, Map<String, String> map) {
                        synchronized (zzji.this.zzrJ) {
                            zzpk.zzbg("JS Engine is requesting an update");
                            if (zzji.this.zzJO == 0) {
                                zzpk.zzbg("Starting reload.");
                                zzji.this.zzJO = 2;
                                zzji.this.zzb(zzaw);
                            }
                            zza.zzb("/requestReload", (zzid) zzqa.get());
                        }
                    }
                };
                zzqa.set(r2);
                zza.zza("/requestReload", (zzid) r2);
                if (zzji.this.zzJK.endsWith(".js")) {
                    zza.zzam(zzji.this.zzJK);
                } else if (zzji.this.zzJK.startsWith("<html>")) {
                    zza.zzao(zzji.this.zzJK);
                } else {
                    zza.zzan(zzji.this.zzJK);
                }
                zzpo.zzXC.postDelayed(new Runnable() {
                    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
                        return;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r3 = this;
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this
                            com.google.android.gms.internal.zzji r0 = com.google.android.gms.internal.zzji.this
                            java.lang.Object r1 = r0.zzrJ
                            monitor-enter(r1)
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x003c }
                            com.google.android.gms.internal.zzji$zzd r0 = r0     // Catch:{ all -> 0x003c }
                            int r0 = r0.getStatus()     // Catch:{ all -> 0x003c }
                            r2 = -1
                            if (r0 == r2) goto L_0x001f
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x003c }
                            com.google.android.gms.internal.zzji$zzd r0 = r0     // Catch:{ all -> 0x003c }
                            int r0 = r0.getStatus()     // Catch:{ all -> 0x003c }
                            r2 = 1
                            if (r0 != r2) goto L_0x0021
                        L_0x001f:
                            monitor-exit(r1)     // Catch:{ all -> 0x003c }
                        L_0x0020:
                            return
                        L_0x0021:
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.C12891.this     // Catch:{ all -> 0x003c }
                            com.google.android.gms.internal.zzji$zzd r0 = r0     // Catch:{ all -> 0x003c }
                            r0.reject()     // Catch:{ all -> 0x003c }
                            com.google.android.gms.internal.zzpo r0 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ all -> 0x003c }
                            com.google.android.gms.internal.zzji$1$4$1 r2 = new com.google.android.gms.internal.zzji$1$4$1     // Catch:{ all -> 0x003c }
                            r2.<init>()     // Catch:{ all -> 0x003c }
                            r0.runOnUiThread(r2)     // Catch:{ all -> 0x003c }
                            java.lang.String r0 = "Could not receive loaded message in a timely manner. Rejecting."
                            com.google.android.gms.internal.zzpk.m1279v(r0)     // Catch:{ all -> 0x003c }
                            monitor-exit(r1)     // Catch:{ all -> 0x003c }
                            goto L_0x0020
                        L_0x003c:
                            r0 = move-exception
                            monitor-exit(r1)     // Catch:{ all -> 0x003c }
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzji.C12891.C12954.run():void");
                    }
                }, (long) zza.zzJZ);
            }
        });
        return zzd2;
    }

    /* access modifiers changed from: protected */
    public zzjf zza(Context context, zzqh zzqh, zzaw zzaw) {
        return new zzjh(context, zzqh, zzaw, null);
    }

    /* access modifiers changed from: protected */
    public zzd zzb(zzaw zzaw) {
        final zzd zza2 = zza(zzaw);
        zza2.zza(new com.google.android.gms.internal.zzqp.zzc<zzjf>() {
            /* renamed from: zza */
            public void zzd(zzjf zzjf) {
                synchronized (zzji.this.zzrJ) {
                    zzji.this.zzJO = 0;
                    if (!(zzji.this.zzJN == null || zza2 == zzji.this.zzJN)) {
                        zzpk.m1279v("New JS engine is loaded, marking previous one as destroyable.");
                        zzji.this.zzJN.zzgR();
                    }
                    zzji.this.zzJN = zza2;
                }
            }
        }, new com.google.android.gms.internal.zzqp.zza() {
            public void run() {
                synchronized (zzji.this.zzrJ) {
                    zzji.this.zzJO = 1;
                    zzpk.m1279v("Failed loading new engine. Marking new engine destroyable.");
                    zza2.zzgR();
                }
            }
        });
        return zza2;
    }

    public zzc zzc(zzaw zzaw) {
        zzc zzc2;
        synchronized (this.zzrJ) {
            if (this.zzJN == null || this.zzJN.getStatus() == -1) {
                this.zzJO = 2;
                this.zzJN = zzb(zzaw);
                zzc2 = this.zzJN.zzgP();
            } else if (this.zzJO == 0) {
                zzc2 = this.zzJN.zzgP();
            } else if (this.zzJO == 1) {
                this.zzJO = 2;
                zzb(zzaw);
                zzc2 = this.zzJN.zzgP();
            } else {
                zzc2 = this.zzJO == 2 ? this.zzJN.zzgP() : this.zzJN.zzgP();
            }
        }
        return zzc2;
    }

    public zzc zzgO() {
        return zzc((zzaw) null);
    }
}

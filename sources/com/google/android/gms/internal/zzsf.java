package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzac;
import java.util.Collections;

public class zzsf extends zzsa {
    /* access modifiers changed from: private */
    public final zza zzael = new zza();
    private zzta zzaem;
    private final zzsr zzaen;
    private zztj zzaeo;

    protected class zza implements ServiceConnection {
        private volatile zzta zzaeq;
        private volatile boolean zzaer;

        protected zza() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            r4.zzaep.zzbT("Service connect failed to get IAnalyticsService");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0060, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
            notifyAll();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0064, code lost:
            throw r0;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [B:3:0x0009, B:9:0x0017] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
            /*
                r4 = this;
                java.lang.String r0 = "AnalyticsServiceConnection.onServiceConnected"
                com.google.android.gms.common.internal.zzac.zzdj(r0)
                monitor-enter(r4)
                if (r6 != 0) goto L_0x0016
                com.google.android.gms.internal.zzsf r0 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0060 }
                java.lang.String r1 = "Service connected with null binder"
                r0.zzbT(r1)     // Catch:{ all -> 0x0060 }
                r4.notifyAll()     // Catch:{ all -> 0x004a }
                monitor-exit(r4)     // Catch:{ all -> 0x004a }
            L_0x0015:
                return
            L_0x0016:
                r0 = 0
                java.lang.String r1 = r6.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x0056 }
                java.lang.String r2 = "com.google.android.gms.analytics.internal.IAnalyticsService"
                boolean r2 = r2.equals(r1)     // Catch:{ RemoteException -> 0x0056 }
                if (r2 == 0) goto L_0x004d
                com.google.android.gms.internal.zzta r0 = com.google.android.gms.internal.zzta.zza.zzam(r6)     // Catch:{ RemoteException -> 0x0056 }
                com.google.android.gms.internal.zzsf r1 = com.google.android.gms.internal.zzsf.this     // Catch:{ RemoteException -> 0x0056 }
                java.lang.String r2 = "Bound to IAnalyticsService interface"
                r1.zzbP(r2)     // Catch:{ RemoteException -> 0x0056 }
            L_0x0030:
                if (r0 != 0) goto L_0x0065
                com.google.android.gms.common.stats.zza r0 = com.google.android.gms.common.stats.zza.zzyJ()     // Catch:{ IllegalArgumentException -> 0x0083 }
                com.google.android.gms.internal.zzsf r1 = com.google.android.gms.internal.zzsf.this     // Catch:{ IllegalArgumentException -> 0x0083 }
                android.content.Context r1 = r1.getContext()     // Catch:{ IllegalArgumentException -> 0x0083 }
                com.google.android.gms.internal.zzsf r2 = com.google.android.gms.internal.zzsf.this     // Catch:{ IllegalArgumentException -> 0x0083 }
                com.google.android.gms.internal.zzsf$zza r2 = r2.zzael     // Catch:{ IllegalArgumentException -> 0x0083 }
                r0.zza(r1, r2)     // Catch:{ IllegalArgumentException -> 0x0083 }
            L_0x0045:
                r4.notifyAll()     // Catch:{ all -> 0x004a }
                monitor-exit(r4)     // Catch:{ all -> 0x004a }
                goto L_0x0015
            L_0x004a:
                r0 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x004a }
                throw r0
            L_0x004d:
                com.google.android.gms.internal.zzsf r2 = com.google.android.gms.internal.zzsf.this     // Catch:{ RemoteException -> 0x0056 }
                java.lang.String r3 = "Got binder with a wrong descriptor"
                r2.zze(r3, r1)     // Catch:{ RemoteException -> 0x0056 }
                goto L_0x0030
            L_0x0056:
                r1 = move-exception
                com.google.android.gms.internal.zzsf r1 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0060 }
                java.lang.String r2 = "Service connect failed to get IAnalyticsService"
                r1.zzbT(r2)     // Catch:{ all -> 0x0060 }
                goto L_0x0030
            L_0x0060:
                r0 = move-exception
                r4.notifyAll()     // Catch:{ all -> 0x004a }
                throw r0     // Catch:{ all -> 0x004a }
            L_0x0065:
                boolean r1 = r4.zzaer     // Catch:{ all -> 0x0060 }
                if (r1 != 0) goto L_0x0080
                com.google.android.gms.internal.zzsf r1 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0060 }
                java.lang.String r2 = "onServiceConnected received after the timeout limit"
                r1.zzbS(r2)     // Catch:{ all -> 0x0060 }
                com.google.android.gms.internal.zzsf r1 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0060 }
                com.google.android.gms.analytics.zzh r1 = r1.zznU()     // Catch:{ all -> 0x0060 }
                com.google.android.gms.internal.zzsf$zza$1 r2 = new com.google.android.gms.internal.zzsf$zza$1     // Catch:{ all -> 0x0060 }
                r2.<init>(r0)     // Catch:{ all -> 0x0060 }
                r1.zzg(r2)     // Catch:{ all -> 0x0060 }
                goto L_0x0045
            L_0x0080:
                r4.zzaeq = r0     // Catch:{ all -> 0x0060 }
                goto L_0x0045
            L_0x0083:
                r0 = move-exception
                goto L_0x0045
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsf.zza.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
        }

        public void onServiceDisconnected(final ComponentName componentName) {
            zzac.zzdj("AnalyticsServiceConnection.onServiceDisconnected");
            zzsf.this.zznU().zzg(new Runnable() {
                public void run() {
                    zzsf.this.onServiceDisconnected(componentName);
                }
            });
        }

        public zzta zzoq() {
            zzta zzta = null;
            zzsf.this.zzmR();
            Intent intent = new Intent("com.google.android.gms.analytics.service.START");
            intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
            Context context = zzsf.this.getContext();
            intent.putExtra("app_package_name", context.getPackageName());
            com.google.android.gms.common.stats.zza zzyJ = com.google.android.gms.common.stats.zza.zzyJ();
            synchronized (this) {
                this.zzaeq = null;
                this.zzaer = true;
                boolean zza = zzyJ.zza(context, intent, (ServiceConnection) zzsf.this.zzael, 129);
                zzsf.this.zza("Bind to service requested", Boolean.valueOf(zza));
                if (!zza) {
                    this.zzaer = false;
                } else {
                    try {
                        wait(zzsf.this.zznT().zzpr());
                    } catch (InterruptedException e) {
                        zzsf.this.zzbS("Wait for service connect was interrupted");
                    }
                    this.zzaer = false;
                    zzta = this.zzaeq;
                    this.zzaeq = null;
                    if (zzta == null) {
                        zzsf.this.zzbT("Successfully bound to service but never got onServiceConnected callback");
                    }
                }
            }
            return zzta;
        }
    }

    protected zzsf(zzsc zzsc) {
        super(zzsc);
        this.zzaeo = new zztj(zzsc.zznR());
        this.zzaen = new zzsr(zzsc) {
            public void run() {
                zzsf.this.zzop();
            }
        };
    }

    private void onDisconnect() {
        zzmA().zznN();
    }

    /* access modifiers changed from: private */
    public void onServiceDisconnected(ComponentName componentName) {
        zzmR();
        if (this.zzaem != null) {
            this.zzaem = null;
            zza("Disconnected from device AnalyticsService", componentName);
            onDisconnect();
        }
    }

    /* access modifiers changed from: private */
    public void zza(zzta zzta) {
        zzmR();
        this.zzaem = zzta;
        zzoo();
        zzmA().onServiceConnected();
    }

    private void zzoo() {
        this.zzaeo.start();
        this.zzaen.zzy(zznT().zzpq());
    }

    /* access modifiers changed from: private */
    public void zzop() {
        zzmR();
        if (isConnected()) {
            zzbP("Inactivity, disconnecting from device AnalyticsService");
            disconnect();
        }
    }

    public boolean connect() {
        zzmR();
        zzob();
        if (this.zzaem != null) {
            return true;
        }
        zzta zzoq = this.zzael.zzoq();
        if (zzoq == null) {
            return false;
        }
        this.zzaem = zzoq;
        zzoo();
        return true;
    }

    public void disconnect() {
        zzmR();
        zzob();
        try {
            com.google.android.gms.common.stats.zza.zzyJ().zza(getContext(), this.zzael);
        } catch (IllegalArgumentException | IllegalStateException e) {
        }
        if (this.zzaem != null) {
            this.zzaem = null;
            onDisconnect();
        }
    }

    public boolean isConnected() {
        zzmR();
        zzob();
        return this.zzaem != null;
    }

    public boolean zzb(zzsz zzsz) {
        zzac.zzw(zzsz);
        zzmR();
        zzob();
        zzta zzta = this.zzaem;
        if (zzta == null) {
            return false;
        }
        try {
            zzta.zza(zzsz.zzfE(), zzsz.zzpQ(), zzsz.zzpS() ? zznT().zzpj() : zznT().zzpk(), Collections.emptyList());
            zzoo();
            return true;
        } catch (RemoteException e) {
            zzbP("Failed to send hits to AnalyticsService");
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }
}

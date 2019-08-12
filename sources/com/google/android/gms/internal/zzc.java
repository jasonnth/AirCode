package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.internal.zzb.zza;
import java.util.concurrent.BlockingQueue;

public class zzc extends Thread {
    private static final boolean DEBUG = zzt.DEBUG;
    private final BlockingQueue<zzl<?>> zzg;
    /* access modifiers changed from: private */
    public final BlockingQueue<zzl<?>> zzh;
    private final zzb zzi;
    private final zzo zzj;
    private volatile boolean zzk = false;

    public zzc(BlockingQueue<zzl<?>> blockingQueue, BlockingQueue<zzl<?>> blockingQueue2, zzb zzb, zzo zzo) {
        this.zzg = blockingQueue;
        this.zzh = blockingQueue2;
        this.zzi = zzb;
        this.zzj = zzo;
    }

    public void quit() {
        this.zzk = true;
        interrupt();
    }

    public void run() {
        if (DEBUG) {
            zzt.zza("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzi.initialize();
        while (true) {
            try {
                final zzl zzl = (zzl) this.zzg.take();
                zzl.zzc("cache-queue-take");
                zza zza = this.zzi.zza(zzl.zzg());
                if (zza == null) {
                    zzl.zzc("cache-miss");
                    this.zzh.put(zzl);
                } else if (zza.zza()) {
                    zzl.zzc("cache-hit-expired");
                    zzl.zza(zza);
                    this.zzh.put(zzl);
                } else {
                    zzl.zzc("cache-hit");
                    zzn zza2 = zzl.zza(new zzj(zza.data, zza.zzf));
                    zzl.zzc("cache-hit-parsed");
                    if (!zza.zzb()) {
                        this.zzj.zza(zzl, zza2);
                    } else {
                        zzl.zzc("cache-hit-refresh-needed");
                        zzl.zza(zza);
                        zza2.zzah = true;
                        this.zzj.zza(zzl, zza2, new Runnable() {
                            public void run() {
                                try {
                                    zzc.this.zzh.put(zzl);
                                } catch (InterruptedException e) {
                                }
                            }
                        });
                    }
                }
            } catch (InterruptedException e) {
                if (this.zzk) {
                    return;
                }
            }
        }
    }
}

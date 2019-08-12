package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public class zzh extends Thread {
    private final zzb zzi;
    private final zzo zzj;
    private volatile boolean zzk = false;
    private final BlockingQueue<zzl<?>> zzx;
    private final zzg zzy;

    public zzh(BlockingQueue<zzl<?>> blockingQueue, zzg zzg, zzb zzb, zzo zzo) {
        this.zzx = blockingQueue;
        this.zzy = zzg;
        this.zzi = zzb;
        this.zzj = zzo;
    }

    @TargetApi(14)
    private void zzb(zzl<?> zzl) {
        int i = VERSION.SDK_INT;
        TrafficStats.setThreadStatsTag(zzl.zzf());
    }

    private void zzb(zzl<?> zzl, zzs zzs) {
        this.zzj.zza(zzl, zzl.zzb(zzs));
    }

    public void quit() {
        this.zzk = true;
        interrupt();
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                zzl zzl = (zzl) this.zzx.take();
                try {
                    zzl.zzc("network-queue-take");
                    zzb(zzl);
                    zzj zza = this.zzy.zza(zzl);
                    zzl.zzc("network-http-complete");
                    if (!zza.zzA || !zzl.zzs()) {
                        zzn zza2 = zzl.zza(zza);
                        zzl.zzc("network-parse-complete");
                        if (zzl.zzn() && zza2.zzaf != null) {
                            this.zzi.zza(zzl.zzg(), zza2.zzaf);
                            zzl.zzc("network-cache-written");
                        }
                        zzl.zzr();
                        this.zzj.zza(zzl, zza2);
                    } else {
                        zzl.zzd("not-modified");
                    }
                } catch (zzs e) {
                    e.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    zzb(zzl, e);
                } catch (Exception e2) {
                    zzt.zza(e2, "Unhandled exception %s", e2.toString());
                    zzs zzs = new zzs((Throwable) e2);
                    zzs.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.zzj.zza(zzl, zzs);
                }
            } catch (InterruptedException e3) {
                if (this.zzk) {
                    return;
                }
            }
        }
    }
}

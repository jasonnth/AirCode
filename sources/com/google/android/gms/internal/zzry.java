package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzac;

public class zzry extends zzsa {
    /* access modifiers changed from: private */
    public final zzsi zzadG;

    public zzry(zzsc zzsc, zzsd zzsd) {
        super(zzsc);
        zzac.zzw(zzsd);
        this.zzadG = zzsd.zzj(zzsc);
    }

    /* access modifiers changed from: 0000 */
    public void onServiceConnected() {
        zzmR();
        this.zzadG.onServiceConnected();
    }

    public void start() {
        this.zzadG.start();
    }

    public void zzV(final boolean z) {
        zza("Network connectivity status changed", Boolean.valueOf(z));
        zznU().zzg(new Runnable() {
            public void run() {
                zzry.this.zzadG.zzV(z);
            }
        });
    }

    public long zza(zzse zzse) {
        zzob();
        zzac.zzw(zzse);
        zzmR();
        long zza = this.zzadG.zza(zzse, true);
        if (zza == 0) {
            this.zzadG.zzc(zzse);
        }
        return zza;
    }

    public void zza(final zzsu zzsu) {
        zzob();
        zznU().zzg(new Runnable() {
            public void run() {
                zzry.this.zzadG.zzb(zzsu);
            }
        });
    }

    public void zza(final zzsz zzsz) {
        zzac.zzw(zzsz);
        zzob();
        zzb("Hit delivery requested", zzsz);
        zznU().zzg(new Runnable() {
            public void run() {
                zzry.this.zzadG.zza(zzsz);
            }
        });
    }

    public void zza(final String str, final Runnable runnable) {
        zzac.zzh(str, "campaign param can't be empty");
        zznU().zzg(new Runnable() {
            public void run() {
                zzry.this.zzadG.zzbX(str);
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        this.zzadG.initialize();
    }

    public void zznL() {
        zzob();
        Context context = getContext();
        if (!zzth.zzak(context) || !zzti.zzal(context)) {
            zza((zzsu) null);
            return;
        }
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
        context.startService(intent);
    }

    public void zznN() {
        zzob();
        zzh.zzmR();
        this.zzadG.zznN();
    }

    public void zznO() {
        zzbP("Radio powered up");
        zznL();
    }

    /* access modifiers changed from: 0000 */
    public void zznP() {
        zzmR();
        this.zzadG.zznP();
    }
}

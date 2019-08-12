package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzar;
import com.google.android.gms.internal.zzav;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpn;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zzme
class zzj implements zzar, Runnable {
    private zzx zzss;
    private final List<Object[]> zzth = new Vector();
    private final AtomicReference<zzar> zzti = new AtomicReference<>();
    CountDownLatch zztj = new CountDownLatch(1);

    public zzj(zzx zzx) {
        this.zzss = zzx;
        if (zzel.zzeT().zzlj()) {
            zzpn.zza((Runnable) this);
        } else {
            run();
        }
    }

    private void zzch() {
        if (!this.zzth.isEmpty()) {
            for (Object[] objArr : this.zzth) {
                if (objArr.length == 1) {
                    ((zzar) this.zzti.get()).zza((MotionEvent) objArr[0]);
                } else if (objArr.length == 3) {
                    ((zzar) this.zzti.get()).zza(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
                }
            }
            this.zzth.clear();
        }
    }

    private Context zzg(Context context) {
        if (!((Boolean) zzgd.zzBs.get()).booleanValue()) {
            return context;
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    public void run() {
        try {
            zza(zzd(this.zzss.zzvn.zzba, zzg(this.zzss.zzqn), !((Boolean) zzgd.zzCU.get()).booleanValue() && (this.zzss.zzvn.zzYY || !((Boolean) zzgd.zzBO.get()).booleanValue())));
        } finally {
            this.zztj.countDown();
            this.zzss = null;
        }
    }

    public String zza(Context context, String str, View view) {
        if (zzcg()) {
            zzar zzar = (zzar) this.zzti.get();
            if (zzar != null) {
                zzch();
                return zzar.zza(zzg(context), str, view);
            }
        }
        return "";
    }

    public String zza(Context context, byte[] bArr) {
        if (zzcg()) {
            zzar zzar = (zzar) this.zzti.get();
            if (zzar != null) {
                zzch();
                return zzar.zzb(zzg(context));
            }
        }
        return "";
    }

    public void zza(int i, int i2, int i3) {
        zzar zzar = (zzar) this.zzti.get();
        if (zzar != null) {
            zzch();
            zzar.zza(i, i2, i3);
            return;
        }
        this.zzth.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public void zza(MotionEvent motionEvent) {
        zzar zzar = (zzar) this.zzti.get();
        if (zzar != null) {
            zzch();
            zzar.zza(motionEvent);
            return;
        }
        this.zzth.add(new Object[]{motionEvent});
    }

    /* access modifiers changed from: protected */
    public void zza(zzar zzar) {
        this.zzti.set(zzar);
    }

    public String zzb(Context context) {
        return zza(context, null);
    }

    /* access modifiers changed from: protected */
    public boolean zzcg() {
        try {
            this.zztj.await();
            return true;
        } catch (InterruptedException e) {
            zzpk.zzc("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public zzar zzd(String str, Context context, boolean z) {
        return zzav.zza(str, context, z);
    }
}

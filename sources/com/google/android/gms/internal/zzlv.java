package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.internal.zzlq.zza;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzme
public class zzlv extends zzpj {
    /* access modifiers changed from: private */
    public final zza zzPQ;
    private final zzpb.zza zzPR;
    private final zzmn zzPS;
    private final zzlx zzQi;
    private Future<zzpb> zzQj;
    private final Object zzrJ;

    public zzlv(Context context, zzs zzs, zzpb.zza zza, zzaw zzaw, zza zza2, zzgl zzgl) {
        this(zza, zza2, new zzlx(context, zzs, new zzpv(context), zzaw, zza, zzgl));
    }

    zzlv(zzpb.zza zza, zza zza2, zzlx zzlx) {
        this.zzrJ = new Object();
        this.zzPR = zza;
        this.zzPS = zza.zzWm;
        this.zzPQ = zza2;
        this.zzQi = zzlx;
    }

    private zzpb zzS(int i) {
        return new zzpb(this.zzPR.zzTi.zzRy, null, null, i, null, null, this.zzPS.orientation, this.zzPS.zzKL, this.zzPR.zzTi.zzRB, false, null, null, null, null, null, this.zzPS.zzSo, this.zzPR.zzvr, this.zzPS.zzSm, this.zzPR.zzWg, this.zzPS.zzSr, this.zzPS.zzSs, this.zzPR.zzWa, null, null, null, null, this.zzPR.zzWm.zzSF, this.zzPR.zzWm.zzSG, null, null, this.zzPS.zzSJ);
    }

    public void onStop() {
        synchronized (this.zzrJ) {
            if (this.zzQj != null) {
                this.zzQj.cancel(true);
            }
        }
    }

    public void zzco() {
        final zzpb zzpb;
        int i;
        try {
            synchronized (this.zzrJ) {
                this.zzQj = zzpn.zza((Callable<T>) this.zzQi);
            }
            zzpb = (zzpb) this.zzQj.get(60000, TimeUnit.MILLISECONDS);
            i = -2;
        } catch (TimeoutException e) {
            zzpk.zzbh("Timed out waiting for native ad.");
            this.zzQj.cancel(true);
            i = 2;
            zzpb = null;
        } catch (ExecutionException e2) {
            zzpb = null;
            i = 0;
        } catch (InterruptedException e3) {
            zzpb = null;
            i = 0;
        } catch (CancellationException e4) {
            zzpb = null;
            i = 0;
        }
        if (zzpb == null) {
            zzpb = zzS(i);
        }
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                zzlv.this.zzPQ.zzb(zzpb);
            }
        });
    }
}

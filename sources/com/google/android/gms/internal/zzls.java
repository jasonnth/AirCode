package com.google.android.gms.internal;

import com.google.android.gms.internal.zzlq.zza;

@zzme
public class zzls extends zzpj {
    /* access modifiers changed from: private */
    public final zza zzPQ;
    private final zzpb.zza zzPR;
    private final zzmn zzPS = this.zzPR.zzWm;

    public zzls(zzpb.zza zza, zza zza2) {
        this.zzPR = zza;
        this.zzPQ = zza2;
    }

    private zzpb zzS(int i) {
        return new zzpb(this.zzPR.zzTi.zzRy, null, null, i, null, null, this.zzPS.orientation, this.zzPS.zzKL, this.zzPR.zzTi.zzRB, false, null, null, null, null, null, this.zzPS.zzSo, this.zzPR.zzvr, this.zzPS.zzSm, this.zzPR.zzWg, this.zzPS.zzSr, this.zzPS.zzSs, this.zzPR.zzWa, null, null, null, null, this.zzPR.zzWm.zzSF, this.zzPR.zzWm.zzSG, null, null, null);
    }

    public void onStop() {
    }

    public void zzco() {
        final zzpb zzS = zzS(0);
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                zzls.this.zzPQ.zzb(zzS);
            }
        });
    }
}

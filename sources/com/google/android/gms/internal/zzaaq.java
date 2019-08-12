package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzaad.zza;

public class zzaaq implements zzaau {
    /* access modifiers changed from: private */
    public final zzaav zzaBk;
    private boolean zzaBl = false;

    public zzaaq(zzaav zzaav) {
        this.zzaBk = zzaav;
    }

    /* JADX WARNING: type inference failed for: r0v8, types: [com.google.android.gms.common.api.Api$zzg] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <A extends com.google.android.gms.common.api.Api.zzb> void zze(com.google.android.gms.internal.zzaad.zza<? extends com.google.android.gms.common.api.Result, A> r4) throws android.os.DeadObjectException {
        /*
            r3 = this;
            com.google.android.gms.internal.zzaav r0 = r3.zzaBk
            com.google.android.gms.internal.zzaat r0 = r0.zzaAw
            com.google.android.gms.internal.zzaby r0 = r0.zzaBW
            r0.zzb(r4)
            com.google.android.gms.internal.zzaav r0 = r3.zzaBk
            com.google.android.gms.internal.zzaat r0 = r0.zzaAw
            com.google.android.gms.common.api.Api$zzc r1 = r4.zzvg()
            com.google.android.gms.common.api.Api$zze r0 = r0.zzc(r1)
            boolean r1 = r0.isConnected()
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.internal.zzaav r1 = r3.zzaBk
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.ConnectionResult> r1 = r1.zzaCf
            com.google.android.gms.common.api.Api$zzc r2 = r4.zzvg()
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L_0x0034
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status
            r1 = 17
            r0.<init>(r1)
            r4.zzB(r0)
        L_0x0033:
            return
        L_0x0034:
            boolean r1 = r0 instanceof com.google.android.gms.common.internal.zzal
            if (r1 == 0) goto L_0x003e
            com.google.android.gms.common.internal.zzal r0 = (com.google.android.gms.common.internal.zzal) r0
            com.google.android.gms.common.api.Api$zzg r0 = r0.zzyn()
        L_0x003e:
            r4.zzb(r0)
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaaq.zze(com.google.android.gms.internal.zzaad$zza):void");
    }

    public void begin() {
    }

    public void connect() {
        if (this.zzaBl) {
            this.zzaBl = false;
            this.zzaBk.zza((zza) new zza(this) {
                public void zzwe() {
                    zzaaq.this.zzaBk.zzaCj.zzo(null);
                }
            });
        }
    }

    public boolean disconnect() {
        if (this.zzaBl) {
            return false;
        }
        if (this.zzaBk.zzaAw.zzwq()) {
            this.zzaBl = true;
            for (zzabx zzxb : this.zzaBk.zzaAw.zzaBV) {
                zzxb.zzxb();
            }
            return false;
        }
        this.zzaBk.zzh(null);
        return true;
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
        this.zzaBk.zzh(null);
        this.zzaBk.zzaCj.zzc(i, this.zzaBl);
    }

    public <A extends zzb, R extends Result, T extends zza<R, A>> T zza(T t) {
        return zzb(t);
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T zzb(T t) {
        try {
            zze(t);
        } catch (DeadObjectException e) {
            this.zzaBk.zza((zza) new zza(this) {
                public void zzwe() {
                    zzaaq.this.onConnectionSuspended(1);
                }
            });
        }
        return t;
    }

    /* access modifiers changed from: 0000 */
    public void zzwd() {
        if (this.zzaBl) {
            this.zzaBl = false;
            this.zzaBk.zzaAw.zzaBW.release();
            disconnect();
        }
    }
}

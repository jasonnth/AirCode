package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzf.zzc;

@zzme
public abstract class zzmi implements com.google.android.gms.internal.zzmh.zza, zzpq<Void> {
    private final zzqp<zzmk> zzRq;
    private final com.google.android.gms.internal.zzmh.zza zzRr;
    private final Object zzrJ = new Object();

    @zzme
    public static final class zza extends zzmi {
        private final Context mContext;

        public zza(Context context, zzqp<zzmk> zzqp, com.google.android.gms.internal.zzmh.zza zza) {
            super(zzqp, zza);
            this.mContext = context;
        }

        public void zzjn() {
        }

        public zzmt zzjo() {
            return zznc.zza(this.mContext, new zzfw((String) zzgd.zzBh.get()), zznb.zzju());
        }
    }

    @zzme
    public static class zzb extends zzmi implements com.google.android.gms.common.internal.zzf.zzb, zzc {
        private Context mContext;
        private zzqp<zzmk> zzRq;
        private final com.google.android.gms.internal.zzmh.zza zzRr;
        protected zzmj zzRu;
        private boolean zzRv;
        private final Object zzrJ = new Object();
        private zzqh zztt;

        public zzb(Context context, zzqh zzqh, zzqp<zzmk> zzqp, com.google.android.gms.internal.zzmh.zza zza) {
            Looper mainLooper;
            super(zzqp, zza);
            this.mContext = context;
            this.zztt = zzqh;
            this.zzRq = zzqp;
            this.zzRr = zza;
            if (((Boolean) zzgd.zzBT.get()).booleanValue()) {
                this.zzRv = true;
                mainLooper = zzw.zzdc().zzlb();
            } else {
                mainLooper = context.getMainLooper();
            }
            this.zzRu = new zzmj(context, mainLooper, this, this, this.zztt.zzYX);
            connect();
        }

        /* access modifiers changed from: protected */
        public void connect() {
            this.zzRu.zzxz();
        }

        public void onConnected(Bundle bundle) {
            zziP();
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            zzpk.zzbf("Cannot connect to remote service, fallback to local instance.");
            zzjp().zziP();
            Bundle bundle = new Bundle();
            bundle.putString("action", "gms_connection_failed_fallback_to_local");
            zzw.zzcM().zzb(this.mContext, this.zztt.zzba, "gmob-apps", bundle, true);
        }

        public void onConnectionSuspended(int i) {
            zzpk.zzbf("Disconnected from remote ad request service.");
        }

        public void zzjn() {
            synchronized (this.zzrJ) {
                if (this.zzRu.isConnected() || this.zzRu.isConnecting()) {
                    this.zzRu.disconnect();
                }
                Binder.flushPendingCommands();
                if (this.zzRv) {
                    zzw.zzdc().zzlc();
                    this.zzRv = false;
                }
            }
        }

        public zzmt zzjo() {
            zzmt zzmt;
            synchronized (this.zzrJ) {
                try {
                    zzmt = this.zzRu.zzjq();
                } catch (DeadObjectException | IllegalStateException e) {
                    zzmt = null;
                }
            }
            return zzmt;
        }

        /* access modifiers changed from: 0000 */
        public zzpq zzjp() {
            return new zza(this.mContext, this.zzRq, this.zzRr);
        }
    }

    public zzmi(zzqp<zzmk> zzqp, com.google.android.gms.internal.zzmh.zza zza2) {
        this.zzRq = zzqp;
        this.zzRr = zza2;
    }

    public void cancel() {
        zzjn();
    }

    public void zza(zzmn zzmn) {
        synchronized (this.zzrJ) {
            this.zzRr.zza(zzmn);
            zzjn();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zza(zzmt zzmt, zzmk zzmk) {
        try {
            zzmt.zza(zzmk, new zzmm(this));
            return true;
        } catch (Throwable th) {
            zzpk.zzc("Could not fetch ad response from ad request service due to an Exception.", th);
            zzw.zzcQ().zza(th, "AdRequestClientTask.getAdResponseFromService");
            this.zzRr.zza(new zzmn(0));
            return false;
        }
    }

    /* renamed from: zziN */
    public Void zziP() {
        final zzmt zzjo = zzjo();
        if (zzjo == null) {
            this.zzRr.zza(new zzmn(0));
            zzjn();
        } else {
            this.zzRq.zza(new zzqp.zzc<zzmk>() {
                /* renamed from: zzc */
                public void zzd(zzmk zzmk) {
                    if (!zzmi.this.zza(zzjo, zzmk)) {
                        zzmi.this.zzjn();
                    }
                }
            }, new com.google.android.gms.internal.zzqp.zza() {
                public void run() {
                    zzmi.this.zzjn();
                }
            });
        }
        return null;
    }

    public abstract void zzjn();

    public abstract zzmt zzjo();
}

package com.google.android.gms.ads.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzey.zza;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzod;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpr;
import com.google.android.gms.internal.zzqh;

@zzme
public class zzq extends zza {
    private static final Object zztX = new Object();
    private static zzq zztY;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Object zzrJ = new Object();
    private boolean zztZ;
    private boolean zzua;
    private float zzub = -1.0f;
    private zzqh zzuc;

    zzq(Context context, zzqh zzqh) {
        this.mContext = context;
        this.zzuc = zzqh;
        this.zztZ = false;
    }

    public static zzq zza(Context context, zzqh zzqh) {
        zzq zzq;
        synchronized (zztX) {
            if (zztY == null) {
                zztY = new zzq(context.getApplicationContext(), zzqh);
            }
            zzq = zztY;
        }
        return zzq;
    }

    public static zzq zzcp() {
        zzq zzq;
        synchronized (zztX) {
            zzq = zztY;
        }
        return zzq;
    }

    public void initialize() {
        synchronized (zztX) {
            if (this.zztZ) {
                zzpk.zzbh("Mobile ads is initialized already.");
                return;
            }
            this.zztZ = true;
            zzgd.initialize(this.mContext);
            zzw.zzcQ().zzc(this.mContext, this.zzuc);
            zzw.zzcR().initialize(this.mContext);
        }
    }

    public void setAppMuted(boolean z) {
        synchronized (this.zzrJ) {
            this.zzua = z;
        }
    }

    public void setAppVolume(float f) {
        synchronized (this.zzrJ) {
            this.zzub = f;
        }
    }

    public void zzb(IObjectWrapper iObjectWrapper, String str) {
        if (iObjectWrapper == null) {
            zzpk.m1280e("Wrapped context is null. Failed to open debug menu.");
            return;
        }
        Context context = (Context) zzd.zzF(iObjectWrapper);
        if (context == null) {
            zzpk.m1280e("Context is null. Failed to open debug menu.");
            return;
        }
        zzpr zzh = zzh(context);
        zzh.setAdUnitId(str);
        zzh.zzba(this.zzuc.zzba);
        zzh.showDialog();
    }

    public void zzc(String str, IObjectWrapper iObjectWrapper) {
        Runnable runnable;
        boolean z;
        if (!TextUtils.isEmpty(str)) {
            zzgd.initialize(this.mContext);
            boolean booleanValue = ((Boolean) zzgd.zzEJ.get()).booleanValue() | ((Boolean) zzgd.zzCN.get()).booleanValue();
            if (((Boolean) zzgd.zzCN.get()).booleanValue()) {
                final Runnable runnable2 = (Runnable) zzd.zzF(iObjectWrapper);
                runnable = new Runnable() {
                    public void run() {
                        zzw.zzcM().runOnUiThread(new Runnable() {
                            public void run() {
                                zzod.zza(zzq.this.mContext, runnable2);
                            }
                        });
                    }
                };
                z = true;
            } else {
                runnable = null;
                z = booleanValue;
            }
            if (z) {
                zzw.zzdi().zza(this.mContext, this.zzuc, str, runnable);
            }
        }
    }

    public float zzcq() {
        float f;
        synchronized (this.zzrJ) {
            f = this.zzub;
        }
        return f;
    }

    public boolean zzcr() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzub >= 0.0f;
        }
        return z;
    }

    public boolean zzcs() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzua;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public zzpr zzh(Context context) {
        return new zzpr(context);
    }

    public void zzy(String str) {
        zzgd.initialize(this.mContext);
        if (!TextUtils.isEmpty(str) && ((Boolean) zzgd.zzEJ.get()).booleanValue()) {
            zzw.zzdi().zza(this.mContext, this.zzuc, str, (Runnable) null);
        }
    }
}

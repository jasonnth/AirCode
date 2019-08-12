package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzha.zza;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

@zzme
public class zzgz extends zzhb {
    private zzke zzGH;
    private zzkf zzGI;
    private final zzs zzGJ;
    private zzha zzGK;
    private boolean zzGL;
    private Object zzrJ;

    private zzgz(Context context, zzs zzs, zzaw zzaw, zza zza) {
        super(context, zzs, null, zzaw, null, zza, null, null);
        this.zzGL = false;
        this.zzrJ = new Object();
        this.zzGJ = zzs;
    }

    public zzgz(Context context, zzs zzs, zzaw zzaw, zzke zzke, zza zza) {
        this(context, zzs, zzaw, zza);
        this.zzGH = zzke;
    }

    public zzgz(Context context, zzs zzs, zzaw zzaw, zzkf zzkf, zza zza) {
        this(context, zzs, zzaw, zza);
        this.zzGI = zzkf;
    }

    public View zza(OnClickListener onClickListener, boolean z) {
        IObjectWrapper iObjectWrapper;
        synchronized (this.zzrJ) {
            if (this.zzGK != null) {
                View zza = this.zzGK.zza(onClickListener, z);
                return zza;
            }
            try {
                if (this.zzGH != null) {
                    iObjectWrapper = this.zzGH.zzhh();
                } else {
                    if (this.zzGI != null) {
                        iObjectWrapper = this.zzGI.zzhh();
                    }
                    iObjectWrapper = null;
                }
            } catch (RemoteException e) {
                zzpk.zzc("Failed to call getAdChoicesContent", e);
            }
            if (iObjectWrapper == null) {
                return null;
            }
            View view = (View) zzd.zzF(iObjectWrapper);
            return view;
        }
    }

    public void zza(View view, Map<String, WeakReference<View>> map, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        synchronized (this.zzrJ) {
            this.zzGL = true;
            try {
                if (this.zzGH != null) {
                    this.zzGH.zzm(zzd.zzA(view));
                } else if (this.zzGI != null) {
                    this.zzGI.zzm(zzd.zzA(view));
                }
            } catch (RemoteException e) {
                zzpk.zzc("Failed to call prepareAd", e);
            }
            this.zzGL = false;
        }
    }

    public void zza(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, View view2) {
        zzac.zzdj("performClick must be called on the main UI thread.");
        synchronized (this.zzrJ) {
            if (this.zzGK != null) {
                this.zzGK.zza(view, map, jSONObject, view2);
                this.zzGJ.onAdClicked();
            } else {
                try {
                    if (this.zzGH != null && !this.zzGH.getOverrideClickHandling()) {
                        this.zzGH.zzl(zzd.zzA(view));
                        this.zzGJ.onAdClicked();
                    }
                    if (this.zzGI != null && !this.zzGI.getOverrideClickHandling()) {
                        this.zzGI.zzl(zzd.zzA(view));
                        this.zzGJ.onAdClicked();
                    }
                } catch (RemoteException e) {
                    zzpk.zzc("Failed to call performClick", e);
                }
            }
        }
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
        zzac.zzdj("recordImpression must be called on the main UI thread.");
        synchronized (this.zzrJ) {
            zzp(true);
            if (this.zzGK != null) {
                this.zzGK.zzb(view, map);
                this.zzGJ.recordImpression();
            } else {
                try {
                    if (this.zzGH != null && !this.zzGH.getOverrideImpressionRecording()) {
                        this.zzGH.recordImpression();
                        this.zzGJ.recordImpression();
                    } else if (this.zzGI != null && !this.zzGI.getOverrideImpressionRecording()) {
                        this.zzGI.recordImpression();
                        this.zzGJ.recordImpression();
                    }
                } catch (RemoteException e) {
                    zzpk.zzc("Failed to call recordImpression", e);
                }
            }
        }
    }

    public void zzc(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.zzrJ) {
            try {
                if (this.zzGH != null) {
                    this.zzGH.zzn(zzd.zzA(view));
                } else if (this.zzGI != null) {
                    this.zzGI.zzn(zzd.zzA(view));
                }
            } catch (RemoteException e) {
                zzpk.zzc("Failed to call untrackView", e);
            }
        }
    }

    public void zzc(zzha zzha) {
        synchronized (this.zzrJ) {
            this.zzGK = zzha;
        }
    }

    public boolean zzfY() {
        boolean zzcx;
        synchronized (this.zzrJ) {
            zzcx = this.zzGK != null ? this.zzGK.zzfY() : this.zzGJ.zzcx();
        }
        return zzcx;
    }

    public boolean zzfZ() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzGL;
        }
        return z;
    }

    public zzha zzga() {
        zzha zzha;
        synchronized (this.zzrJ) {
            zzha = this.zzGK;
        }
        return zzha;
    }

    public zzqw zzgb() {
        return null;
    }
}

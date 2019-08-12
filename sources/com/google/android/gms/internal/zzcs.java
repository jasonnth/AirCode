package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import com.google.android.gms.internal.zzct.zza;
import com.google.android.gms.internal.zzct.zzd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@zzme
public class zzcs implements zzcu {
    private final Object zzrJ = new Object();
    private final zzqh zztt;
    private final WeakHashMap<zzpb, zzct> zzwg = new WeakHashMap<>();
    private final ArrayList<zzct> zzwh = new ArrayList<>();
    private final Context zzwi;
    private final zzji zzwj;

    public zzcs(Context context, zzqh zzqh, zzji zzji) {
        this.zzwi = context.getApplicationContext();
        this.zztt = zzqh;
        this.zzwj = zzji;
    }

    public void zza(zzct zzct) {
        synchronized (this.zzrJ) {
            if (!zzct.zzdJ()) {
                this.zzwh.remove(zzct);
                Iterator it = this.zzwg.entrySet().iterator();
                while (it.hasNext()) {
                    if (((Entry) it.next()).getValue() == zzct) {
                        it.remove();
                    }
                }
            }
        }
    }

    public void zza(zzeg zzeg, zzpb zzpb) {
        zza(zzeg, zzpb, zzpb.zzNH.getView());
    }

    public void zza(zzeg zzeg, zzpb zzpb, View view) {
        zza(zzeg, zzpb, (zzda) new zzd(view, zzpb), (zzjj) null);
    }

    public void zza(zzeg zzeg, zzpb zzpb, View view, zzjj zzjj) {
        zza(zzeg, zzpb, (zzda) new zzd(view, zzpb), zzjj);
    }

    public void zza(zzeg zzeg, zzpb zzpb, zzda zzda, zzjj zzjj) {
        zzct zzct;
        synchronized (this.zzrJ) {
            if (zzi(zzpb)) {
                zzct = (zzct) this.zzwg.get(zzpb);
            } else {
                zzct = new zzct(this.zzwi, zzeg, zzpb, this.zztt, zzda);
                zzct.zza((zzcu) this);
                this.zzwg.put(zzpb, zzct);
                this.zzwh.add(zzct);
            }
            if (zzjj != null) {
                zzct.zza((zzcx) new zzcv(zzct, zzjj));
            } else {
                zzct.zza((zzcx) new zzcw(zzct, this.zzwj));
            }
        }
    }

    public void zza(zzeg zzeg, zzpb zzpb, zzha zzha) {
        zza(zzeg, zzpb, (zzda) new zza(zzha), (zzjj) null);
    }

    public boolean zzi(zzpb zzpb) {
        boolean z;
        synchronized (this.zzrJ) {
            zzct zzct = (zzct) this.zzwg.get(zzpb);
            z = zzct != null && zzct.zzdJ();
        }
        return z;
    }

    public void zzj(zzpb zzpb) {
        synchronized (this.zzrJ) {
            zzct zzct = (zzct) this.zzwg.get(zzpb);
            if (zzct != null) {
                zzct.zzdH();
            }
        }
    }

    public void zzk(zzpb zzpb) {
        synchronized (this.zzrJ) {
            zzct zzct = (zzct) this.zzwg.get(zzpb);
            if (zzct != null) {
                zzct.stop();
            }
        }
    }

    public void zzl(zzpb zzpb) {
        synchronized (this.zzrJ) {
            zzct zzct = (zzct) this.zzwg.get(zzpb);
            if (zzct != null) {
                zzct.pause();
            }
        }
    }

    public void zzm(zzpb zzpb) {
        synchronized (this.zzrJ) {
            zzct zzct = (zzct) this.zzwg.get(zzpb);
            if (zzct != null) {
                zzct.resume();
            }
        }
    }
}

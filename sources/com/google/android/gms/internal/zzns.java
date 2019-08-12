package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzpb.zza;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzns extends zzb implements zzoh {
    private static zzns zzVk;
    private static final zzjz zzVl = new zzjz();
    private final Map<String, zzol> zzVm = new HashMap();
    private boolean zzVn;

    public zzns(Context context, zze zze, zzeg zzeg, zzka zzka, zzqh zzqh) {
        super(context, zzeg, null, zzka, zzqh, zze);
        zzVk = this;
    }

    private zza zzd(zza zza) {
        zzpk.m1279v("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            String jSONObject = zznd.zzb(zza.zzWm).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zza.zzTi.zzvl);
            zzjr zzjr = new zzjr(Arrays.asList(new zzjq[]{new zzjq(jSONObject, null, Arrays.asList(new String[]{"com.google.ads.mediation.admob.AdMobAdapter"}), null, null, Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList())}), ((Long) zzgd.zzDM.get()).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1, 0, 1, null, 0, -1, -1, false);
            return new zza(zza.zzTi, zza.zzWm, zzjr, zza.zzvr, zza.errorCode, zza.zzWg, zza.zzWh, zza.zzWa);
        } catch (JSONException e) {
            zzpk.zzb("Unable to generate ad state for non-mediated rewarded video.", e);
            return zze(zza);
        }
    }

    private zza zze(zza zza) {
        return new zza(zza.zzTi, zza.zzWm, null, zza.zzvr, 0, zza.zzWg, zza.zzWh, zza.zzWa);
    }

    public static zzns zzjE() {
        return zzVk;
    }

    public void destroy() {
        zzac.zzdj("destroy must be called on the main UI thread.");
        for (String str : this.zzVm.keySet()) {
            try {
                zzol zzol = (zzol) this.zzVm.get(str);
                if (!(zzol == null || zzol.zzjN() == null)) {
                    zzol.zzjN().destroy();
                }
            } catch (RemoteException e) {
                String str2 = "Fail to destroy adapter: ";
                String valueOf = String.valueOf(str);
                zzpk.zzbh(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
        }
    }

    public boolean isLoaded() {
        zzac.zzdj("isLoaded must be called on the main UI thread.");
        return this.zzss.zzvp == null && this.zzss.zzvq == null && this.zzss.zzvs != null && !this.zzVn;
    }

    public void onContextChanged(Context context) {
        for (zzol zzjN : this.zzVm.values()) {
            try {
                zzjN.zzjN().zzk(zzd.zzA(context));
            } catch (RemoteException e) {
                zzpk.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public void onRewardedVideoAdClosed() {
        zzbG();
    }

    public void onRewardedVideoAdLeftApplication() {
        zzbH();
    }

    public void onRewardedVideoAdOpened() {
        zza(this.zzss.zzvs, false);
        zzbI();
    }

    public void onRewardedVideoStarted() {
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzLi == null)) {
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, this.zzss.zzvs, this.zzss.zzvl, false, this.zzss.zzvs.zzLi.zzKw);
        }
        zzbK();
    }

    public void pause() {
        zzac.zzdj("pause must be called on the main UI thread.");
        for (String str : this.zzVm.keySet()) {
            try {
                zzol zzol = (zzol) this.zzVm.get(str);
                if (!(zzol == null || zzol.zzjN() == null)) {
                    zzol.zzjN().pause();
                }
            } catch (RemoteException e) {
                String str2 = "Fail to pause adapter: ";
                String valueOf = String.valueOf(str);
                zzpk.zzbh(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
        }
    }

    public void resume() {
        zzac.zzdj("resume must be called on the main UI thread.");
        for (String str : this.zzVm.keySet()) {
            try {
                zzol zzol = (zzol) this.zzVm.get(str);
                if (!(zzol == null || zzol.zzjN() == null)) {
                    zzol.zzjN().resume();
                }
            } catch (RemoteException e) {
                String str2 = "Fail to resume adapter: ";
                String valueOf = String.valueOf(str);
                zzpk.zzbh(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
        }
    }

    public void zza(zzoa zzoa) {
        zzac.zzdj("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty(zzoa.zzvl)) {
            zzpk.zzbh("Invalid ad unit id. Aborting.");
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    zzns.this.zzh(1);
                }
            });
            return;
        }
        this.zzVn = false;
        this.zzss.zzvl = zzoa.zzvl;
        super.zzb(zzoa.zzRy);
    }

    public void zza(final zza zza, zzgl zzgl) {
        if (zza.errorCode != -2) {
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    zzns.this.zzb(new zzpb(zza, null, null, null, null, null, null, null));
                }
            });
            return;
        }
        this.zzss.zzvt = zza;
        if (zza.zzWc == null) {
            this.zzss.zzvt = zzd(zza);
        }
        this.zzss.zzvO = 0;
        this.zzss.zzvq = zzw.zzcL().zza(this.zzss.zzqn, this.zzss.zzvt, this);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzec zzec, zzpb zzpb, boolean z) {
        return false;
    }

    public boolean zza(zzpb zzpb, zzpb zzpb2) {
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzol zzaN(java.lang.String r6) {
        /*
            r5 = this;
            java.util.Map<java.lang.String, com.google.android.gms.internal.zzol> r0 = r5.zzVm
            java.lang.Object r0 = r0.get(r6)
            com.google.android.gms.internal.zzol r0 = (com.google.android.gms.internal.zzol) r0
            if (r0 != 0) goto L_0x0027
            com.google.android.gms.internal.zzka r1 = r5.zzsz     // Catch:{ Exception -> 0x0028 }
            java.lang.String r2 = "com.google.ads.mediation.admob.AdMobAdapter"
            boolean r2 = r2.equals(r6)     // Catch:{ Exception -> 0x0028 }
            if (r2 == 0) goto L_0x004a
            com.google.android.gms.internal.zzjz r1 = zzVl     // Catch:{ Exception -> 0x0028 }
            r2 = r1
        L_0x0018:
            com.google.android.gms.internal.zzol r1 = new com.google.android.gms.internal.zzol     // Catch:{ Exception -> 0x0028 }
            com.google.android.gms.internal.zzkb r2 = r2.zzas(r6)     // Catch:{ Exception -> 0x0028 }
            r1.<init>(r2, r5)     // Catch:{ Exception -> 0x0028 }
            java.util.Map<java.lang.String, com.google.android.gms.internal.zzol> r0 = r5.zzVm     // Catch:{ Exception -> 0x0047 }
            r0.put(r6, r1)     // Catch:{ Exception -> 0x0047 }
            r0 = r1
        L_0x0027:
            return r0
        L_0x0028:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L_0x002b:
            java.lang.String r3 = "Fail to instantiate adapter "
            java.lang.String r0 = java.lang.String.valueOf(r6)
            int r4 = r0.length()
            if (r4 == 0) goto L_0x0041
            java.lang.String r0 = r3.concat(r0)
        L_0x003c:
            com.google.android.gms.internal.zzpk.zzc(r0, r2)
            r0 = r1
            goto L_0x0027
        L_0x0041:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
            goto L_0x003c
        L_0x0047:
            r0 = move-exception
            r2 = r0
            goto L_0x002b
        L_0x004a:
            r2 = r1
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzns.zzaN(java.lang.String):com.google.android.gms.internal.zzol");
    }

    /* access modifiers changed from: protected */
    public void zzbG() {
        this.zzss.zzvs = null;
        super.zzbG();
    }

    public void zzc(zzoo zzoo) {
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzLi == null)) {
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, this.zzss.zzvs, this.zzss.zzvl, false, this.zzss.zzvs.zzLi.zzKx);
        }
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzWc == null || TextUtils.isEmpty(this.zzss.zzvs.zzWc.zzKM))) {
            zzoo = new zzoo(this.zzss.zzvs.zzWc.zzKM, this.zzss.zzvs.zzWc.zzKN);
        }
        zza(zzoo);
    }

    public void zzjF() {
        zzac.zzdj("showAd must be called on the main UI thread.");
        if (!isLoaded()) {
            zzpk.zzbh("The reward video has not loaded.");
            return;
        }
        this.zzVn = true;
        zzol zzaN = zzaN(this.zzss.zzvs.zzLk);
        if (zzaN != null && zzaN.zzjN() != null) {
            try {
                zzaN.zzjN().showVideo();
            } catch (RemoteException e) {
                zzpk.zzc("Could not call showVideo.", e);
            }
        }
    }

    public void zzjG() {
        onAdClicked();
    }
}

package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.common.util.zzt;

@zzme
public class zzlq {

    public interface zza {
        void zzb(zzpb zzpb);
    }

    public zzpq zza(Context context, com.google.android.gms.ads.internal.zza zza2, com.google.android.gms.internal.zzpb.zza zza3, zzaw zzaw, zzqw zzqw, zzka zzka, zza zza4, zzgl zzgl) {
        zzmn zzmn = zza3.zzWm;
        zzpq zzls = zzmn.zzSn ? new zzlu(context, zza3, zzka, zza4, zzgl, zzqw) : (zzmn.zzzB || (zza2 instanceof zzs)) ? (!zzmn.zzzB || !(zza2 instanceof zzs)) ? new zzls(zza3, zza4) : new zzlv(context, (zzs) zza2, zza3, zzaw, zza4, zzgl) : (!((Boolean) zzgd.zzCu.get()).booleanValue() || !zzt.zzzl() || zzt.zzzn() || zzqw == null || !zzqw.zzbC().zzzz) ? new zzlr(context, zza3, zzqw, zza4) : new zzlt(context, zza3, zzqw, zza4);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(zzls.getClass().getName());
        zzpk.zzbf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzls.zziP();
        return zzls;
    }

    public zzpq zza(Context context, com.google.android.gms.internal.zzpb.zza zza2, zzns zzns) {
        zzok zzok = new zzok(context, zza2, zzns);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(zzok.getClass().getName());
        zzpk.zzbf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzok.zziP();
        return zzok;
    }
}

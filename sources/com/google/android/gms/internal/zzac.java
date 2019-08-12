package com.google.android.gms.internal;

import com.google.android.gms.internal.zzn.zza;
import com.google.android.gms.internal.zzn.zzb;
import java.io.UnsupportedEncodingException;

public class zzac extends zzl<String> {
    private final zzb<String> zzaG;

    public zzac(int i, String str, zzb<String> zzb, zza zza) {
        super(i, str, zza);
        this.zzaG = zzb;
    }

    /* access modifiers changed from: protected */
    public zzn<String> zza(zzj zzj) {
        String str;
        try {
            str = new String(zzj.data, zzy.zza(zzj.zzz));
        } catch (UnsupportedEncodingException e) {
            str = new String(zzj.data);
        }
        return zzn.zza(str, zzy.zzb(zzj));
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzi */
    public void zza(String str) {
        this.zzaG.zzb(str);
    }
}

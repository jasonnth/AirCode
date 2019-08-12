package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.internal.zzaqn.zzb;
import com.google.android.gms.internal.zzaqn.zzc;
import com.google.android.gms.internal.zzaqn.zzd;
import com.google.android.gms.internal.zzub.zza;
import java.util.ArrayList;

public class zzuk {
    public static zztt zza(String str, zzc zzc) {
        return new zztt(zzbyj.zzf(zzc), new zza(str).zzY(true).zzco(str).zzcn("blob").zzqH());
    }

    public static zzud zza(Action action, long j, String str, int i) {
        int i2;
        boolean z = false;
        Bundle bundle = new Bundle();
        bundle.putAll(action.zzqK());
        Bundle bundle2 = bundle.getBundle("object");
        Uri uri = bundle2.containsKey("id") ? Uri.parse(bundle2.getString("id")) : null;
        String string = bundle2.getString("name");
        String string2 = bundle2.getString("type");
        Intent zza = zzul.zza(str, Uri.parse(bundle2.getString("url")));
        zztp.zza zza2 = zzud.zza(zza, string, uri, string2, null);
        if (bundle.containsKey(".private:ssbContext")) {
            zza2.zza(zztt.zzl(bundle.getByteArray(".private:ssbContext")));
            bundle.remove(".private:ssbContext");
        }
        if (bundle.containsKey(".private:accountName")) {
            zza2.zzb(new Account(bundle.getString(".private:accountName"), "com.google"));
            bundle.remove(".private:accountName");
        }
        if (!bundle.containsKey(".private:isContextOnly") || !bundle.getBoolean(".private:isContextOnly")) {
            i2 = 0;
        } else {
            i2 = 4;
            bundle.remove(".private:isContextOnly");
        }
        if (bundle.containsKey(".private:isDeviceOnly")) {
            z = bundle.getBoolean(".private:isDeviceOnly", false);
            bundle.remove(".private:isDeviceOnly");
        }
        zza2.zza(zza(".private:action", zzj(bundle)));
        return new zzud.zza().zza(zzud.zza(str, zza)).zzB(j).zzaS(i2).zza(zza2.zzqE()).zzaa(z).zzaT(i).zzqI();
    }

    private static zzb zzb(String str, Bundle bundle) {
        zzb zzb = new zzb();
        zzb.name = str;
        zzb.zzbhl = new zzd();
        zzb.zzbhl.zzbhq = zzj(bundle);
        return zzb;
    }

    private static zzb zzh(String str, boolean z) {
        zzb zzb = new zzb();
        zzb.name = str;
        zzb.zzbhl = new zzd();
        zzb.zzbhl.zzbhn = z;
        return zzb;
    }

    public static zzc zzj(Bundle bundle) {
        Bundle[] bundleArr;
        String[] strArr;
        ArrayList arrayList = new ArrayList();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof String) {
                arrayList.add(zzv(str, (String) obj));
            } else if (obj instanceof Bundle) {
                arrayList.add(zzb(str, (Bundle) obj));
            } else if (obj instanceof String[]) {
                for (String str2 : (String[]) obj) {
                    if (str2 != null) {
                        arrayList.add(zzv(str, str2));
                    }
                }
            } else if (obj instanceof Bundle[]) {
                for (Bundle bundle2 : (Bundle[]) obj) {
                    if (bundle2 != null) {
                        arrayList.add(zzb(str, bundle2));
                    }
                }
            } else if (obj instanceof Boolean) {
                arrayList.add(zzh(str, ((Boolean) obj).booleanValue()));
            } else {
                String valueOf = String.valueOf(obj);
                Log.e("SearchIndex", new StringBuilder(String.valueOf(valueOf).length() + 19).append("Unsupported value: ").append(valueOf).toString());
            }
        }
        zzc zzc = new zzc();
        if (bundle.containsKey("type")) {
            zzc.type = bundle.getString("type");
        }
        zzc.zzbhm = (zzb[]) arrayList.toArray(new zzb[arrayList.size()]);
        return zzc;
    }

    private static zzb zzv(String str, String str2) {
        zzb zzb = new zzb();
        zzb.name = str;
        zzb.zzbhl = new zzd();
        zzb.zzbhl.zzaGV = str2;
        return zzb;
    }
}

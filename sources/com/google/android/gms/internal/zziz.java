package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzni.zza;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p005cn.jpush.android.JPushConstants;

@zzme
public class zziz {
    private final LinkedList<zzja> zzJb = new LinkedList<>();
    private zziw zzJc;
    private final Map<zzja, zzjb> zzyE = new HashMap();

    private static void zza(String str, zzja zzja) {
        if (zzpk.zzak(2)) {
            zzpk.m1279v(String.format(str, new Object[]{zzja}));
        }
    }

    private String[] zzai(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), JPushConstants.ENCODING_UTF_8);
            }
            return split;
        } catch (UnsupportedEncodingException e) {
            return new String[0];
        }
    }

    private boolean zzaj(String str) {
        try {
            return Pattern.matches((String) zzgd.zzDj.get(), str);
        } catch (RuntimeException e) {
            zzw.zzcQ().zza((Throwable) e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    static String zzak(String str) {
        try {
            Matcher matcher = Pattern.compile("([^/]+/[0-9]+).*").matcher(str);
            return matcher.matches() ? matcher.group(1) : str;
        } catch (RuntimeException e) {
            return str;
        }
    }

    private static void zzc(Bundle bundle, String str) {
        String[] split = str.split("/", 2);
        if (split.length != 0) {
            String str2 = split[0];
            if (split.length == 1) {
                bundle.remove(str2);
                return;
            }
            Bundle bundle2 = bundle.getBundle(str2);
            if (bundle2 != null) {
                zzc(bundle2, split[1]);
            }
        }
    }

    private static void zzc(zzec zzec, String str) {
        Bundle bundle = zzec.zzzd.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            bundle.putBoolean(str, true);
        }
        zzec.extras.putBoolean(str, true);
    }

    private String zzgw() {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator it = this.zzJb.iterator();
            while (it.hasNext()) {
                sb.append(Base64.encodeToString(((zzja) it.next()).toString().getBytes(JPushConstants.ENCODING_UTF_8), 0));
                if (it.hasNext()) {
                    sb.append("\u0000");
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    static Set<String> zzj(zzec zzec) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(zzec.extras.keySet());
        Bundle bundle = zzec.zzzd.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            hashSet.addAll(bundle.keySet());
        }
        return hashSet;
    }

    static zzec zzk(zzec zzec) {
        zzec zzn = zzn(zzec);
        zzc(zzn, "_skipMediation");
        return zzn;
    }

    static boolean zzl(zzec zzec) {
        return zzj(zzec).contains("_skipMediation");
    }

    static zzec zzm(zzec zzec) {
        String[] split;
        zzec zzn = zzn(zzec);
        for (String str : ((String) zzgd.zzDf.get()).split(",")) {
            zzc(zzn.zzzd, str);
            String str2 = "com.google.ads.mediation.admob.AdMobAdapter/";
            if (str.startsWith(str2)) {
                zzc(zzn.extras, str.replaceFirst(str2, ""));
            }
        }
        return zzn;
    }

    static zzec zzn(zzec zzec) {
        Parcel obtain = Parcel.obtain();
        zzec.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        zzec zzec2 = (zzec) zzec.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        if (((Boolean) zzgd.zzCX.get()).booleanValue()) {
            zzec.zzi(zzec2);
        }
        return zzec2;
    }

    /* access modifiers changed from: 0000 */
    public void flush() {
        while (this.zzJb.size() > 0) {
            zzja zzja = (zzja) this.zzJb.remove();
            zzjb zzjb = (zzjb) this.zzyE.get(zzja);
            zza("Flushing interstitial queue for %s.", zzja);
            while (zzjb.size() > 0) {
                zzjb.zzo(null).zzJh.zzcm();
            }
            this.zzyE.remove(zzja);
        }
    }

    /* access modifiers changed from: 0000 */
    public void restore() {
        if (this.zzJc != null) {
            SharedPreferences sharedPreferences = this.zzJc.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
            flush();
            try {
                HashMap hashMap = new HashMap();
                for (Entry entry : sharedPreferences.getAll().entrySet()) {
                    if (!((String) entry.getKey()).equals("PoolKeys")) {
                        zzje zzal = zzje.zzal((String) entry.getValue());
                        zzja zzja = new zzja(zzal.zzum, zzal.zzts, zzal.zzJf);
                        if (!this.zzyE.containsKey(zzja)) {
                            this.zzyE.put(zzja, new zzjb(zzal.zzum, zzal.zzts, zzal.zzJf));
                            hashMap.put(zzja.toString(), zzja);
                            zza("Restored interstitial queue for %s.", zzja);
                        }
                    }
                }
                for (String str : zzai(sharedPreferences.getString("PoolKeys", ""))) {
                    zzja zzja2 = (zzja) hashMap.get(str);
                    if (this.zzyE.containsKey(zzja2)) {
                        this.zzJb.add(zzja2);
                    }
                }
            } catch (IOException | RuntimeException e) {
                zzw.zzcQ().zza(e, "InterstitialAdPool.restore");
                zzpk.zzc("Malformed preferences value for InterstitialAdPool.", e);
                this.zzyE.clear();
                this.zzJb.clear();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void save() {
        if (this.zzJc != null) {
            Editor edit = this.zzJc.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
            edit.clear();
            for (Entry entry : this.zzyE.entrySet()) {
                zzja zzja = (zzja) entry.getKey();
                zzjb zzjb = (zzjb) entry.getValue();
                if (zzjb.zzgB()) {
                    edit.putString(zzja.toString(), new zzje(zzjb).zzgL());
                    zza("Saved interstitial queue for %s.", zzja);
                }
            }
            edit.putString("PoolKeys", zzgw());
            edit.apply();
        }
    }

    /* access modifiers changed from: 0000 */
    public zza zza(zzec zzec, String str) {
        zzjb zzjb;
        if (zzaj(str)) {
            return null;
        }
        int i = new zza(this.zzJc.getApplicationContext()).zzjC().zzUQ;
        zzec zzm = zzm(zzec);
        String zzak = zzak(str);
        zzja zzja = new zzja(zzm, zzak, i);
        zzjb zzjb2 = (zzjb) this.zzyE.get(zzja);
        if (zzjb2 == null) {
            zza("Interstitial pool created at %s.", zzja);
            zzjb zzjb3 = new zzjb(zzm, zzak, i);
            this.zzyE.put(zzja, zzjb3);
            zzjb = zzjb3;
        } else {
            zzjb = zzjb2;
        }
        this.zzJb.remove(zzja);
        this.zzJb.add(zzja);
        zzjb.zzgA();
        while (this.zzJb.size() > ((Integer) zzgd.zzDg.get()).intValue()) {
            zzja zzja2 = (zzja) this.zzJb.remove();
            zzjb zzjb4 = (zzjb) this.zzyE.get(zzja2);
            zza("Evicting interstitial queue for %s.", zzja2);
            while (zzjb4.size() > 0) {
                zza zzo = zzjb4.zzo(null);
                if (zzo.zzJl) {
                    zzjc.zzgC().zzgE();
                }
                zzo.zzJh.zzcm();
            }
            this.zzyE.remove(zzja2);
        }
        while (zzjb.size() > 0) {
            zza zzo2 = zzjb.zzo(zzm);
            if (!zzo2.zzJl || zzw.zzcS().currentTimeMillis() - zzo2.zzJk <= 1000 * ((long) ((Integer) zzgd.zzDi.get()).intValue())) {
                String str2 = zzo2.zzJi != null ? " (inline) " : " ";
                zza(new StringBuilder(String.valueOf(str2).length() + 34).append("Pooled interstitial").append(str2).append("returned at %s.").toString(), zzja);
                return zzo2;
            }
            zza("Expired interstitial at %s.", zzja);
            zzjc.zzgC().zzgD();
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void zza(zziw zziw) {
        if (this.zzJc == null) {
            this.zzJc = zziw.zzgu();
            restore();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzb(zzec zzec, String str) {
        if (this.zzJc != null) {
            int i = new zza(this.zzJc.getApplicationContext()).zzjC().zzUQ;
            zzec zzm = zzm(zzec);
            String zzak = zzak(str);
            zzja zzja = new zzja(zzm, zzak, i);
            zzjb zzjb = (zzjb) this.zzyE.get(zzja);
            if (zzjb == null) {
                zza("Interstitial pool created at %s.", zzja);
                zzjb = new zzjb(zzm, zzak, i);
                this.zzyE.put(zzja, zzjb);
            }
            zzjb.zza(this.zzJc, zzec);
            zzjb.zzgA();
            zza("Inline entry added to the queue at %s.", zzja);
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzgv() {
        if (this.zzJc != null) {
            for (Entry entry : this.zzyE.entrySet()) {
                zzja zzja = (zzja) entry.getKey();
                zzjb zzjb = (zzjb) entry.getValue();
                if (zzpk.zzak(2)) {
                    int size = zzjb.size();
                    int zzgy = zzjb.zzgy();
                    if (zzgy < size) {
                        zzpk.m1279v(String.format("Loading %s/%s pooled interstitials for %s.", new Object[]{Integer.valueOf(size - zzgy), Integer.valueOf(size), zzja}));
                    }
                }
                int zzgz = zzjb.zzgz() + 0;
                while (zzjb.size() < ((Integer) zzgd.zzDh.get()).intValue()) {
                    zza("Pooling and loading one new interstitial for %s.", zzja);
                    if (zzjb.zzb(this.zzJc)) {
                        zzgz++;
                    }
                }
                zzjc.zzgC().zzE(zzgz);
            }
            save();
        }
    }
}

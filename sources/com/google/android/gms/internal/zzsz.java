package com.google.android.gms.internal;

import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class zzsz {
    private final Map<String, String> zzFP;
    private final List<zzsl> zzagg;
    private final long zzagh;
    private final long zzagi;
    private final int zzagj;
    private final boolean zzagk;
    private final String zzagl;

    public zzsz(zzrz zzrz, Map<String, String> map, long j, boolean z) {
        this(zzrz, map, j, z, 0, 0, null);
    }

    public zzsz(zzrz zzrz, Map<String, String> map, long j, boolean z, long j2, int i) {
        this(zzrz, map, j, z, j2, i, null);
    }

    public zzsz(zzrz zzrz, Map<String, String> map, long j, boolean z, long j2, int i, List<zzsl> list) {
        zzac.zzw(zzrz);
        zzac.zzw(map);
        this.zzagi = j;
        this.zzagk = z;
        this.zzagh = j2;
        this.zzagj = i;
        this.zzagg = list != null ? list : Collections.emptyList();
        this.zzagl = zzs(list);
        HashMap hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            if (zzl(entry.getKey())) {
                String zza = zza(zzrz, entry.getKey());
                if (zza != null) {
                    hashMap.put(zza, zzb(zzrz, entry.getValue()));
                }
            }
        }
        for (Entry entry2 : map.entrySet()) {
            if (!zzl(entry2.getKey())) {
                String zza2 = zza(zzrz, entry2.getKey());
                if (zza2 != null) {
                    hashMap.put(zza2, zzb(zzrz, entry2.getValue()));
                }
            }
        }
        if (!TextUtils.isEmpty(this.zzagl)) {
            zztm.zzc(hashMap, "_v", this.zzagl);
            if (this.zzagl.equals("ma4.0.0") || this.zzagl.equals("ma4.0.1")) {
                hashMap.remove("adid");
            }
        }
        this.zzFP = Collections.unmodifiableMap(hashMap);
    }

    public static zzsz zza(zzrz zzrz, zzsz zzsz, Map<String, String> map) {
        return new zzsz(zzrz, map, zzsz.zzpQ(), zzsz.zzpS(), zzsz.zzpP(), zzsz.zzpO(), zzsz.zzpR());
    }

    private static String zza(zzrz zzrz, Object obj) {
        if (obj == null) {
            return null;
        }
        String obj2 = obj.toString();
        if (obj2.startsWith("&")) {
            obj2 = obj2.substring(1);
        }
        int length = obj2.length();
        if (length > 256) {
            obj2 = obj2.substring(0, 256);
            zzrz.zzc("Hit param name is too long and will be trimmed", Integer.valueOf(length), obj2);
        }
        if (TextUtils.isEmpty(obj2)) {
            return null;
        }
        return obj2;
    }

    private static String zzb(zzrz zzrz, Object obj) {
        String obj2 = obj == null ? "" : obj.toString();
        int length = obj2.length();
        if (length <= 8192) {
            return obj2;
        }
        String substring = obj2.substring(0, 8192);
        zzrz.zzc("Hit param value is too long and will be trimmed", Integer.valueOf(length), substring);
        return substring;
    }

    private static boolean zzl(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.toString().startsWith("&");
    }

    private String zzs(String str, String str2) {
        zzac.zzdr(str);
        zzac.zzb(!str.startsWith("&"), (Object) "Short param name required");
        String str3 = (String) this.zzFP.get(str);
        return str3 != null ? str3 : str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zzs(java.util.List<com.google.android.gms.internal.zzsl> r5) {
        /*
            r1 = 0
            if (r5 == 0) goto L_0x002d
            java.util.Iterator r2 = r5.iterator()
        L_0x0007:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x002d
            java.lang.Object r0 = r2.next()
            com.google.android.gms.internal.zzsl r0 = (com.google.android.gms.internal.zzsl) r0
            java.lang.String r3 = "appendVersion"
            java.lang.String r4 = r0.getId()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0007
            java.lang.String r0 = r0.getValue()
        L_0x0024:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x002b
        L_0x002a:
            return r1
        L_0x002b:
            r1 = r0
            goto L_0x002a
        L_0x002d:
            r0 = r1
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsz.zzs(java.util.List):java.lang.String");
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ht=").append(this.zzagi);
        if (this.zzagh != 0) {
            stringBuffer.append(", dbId=").append(this.zzagh);
        }
        if (this.zzagj != 0) {
            stringBuffer.append(", appUID=").append(this.zzagj);
        }
        ArrayList<String> arrayList = new ArrayList<>(this.zzFP.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            stringBuffer.append(", ");
            stringBuffer.append(str);
            stringBuffer.append("=");
            stringBuffer.append((String) this.zzFP.get(str));
        }
        return stringBuffer.toString();
    }

    public Map<String, String> zzfE() {
        return this.zzFP;
    }

    public int zzpO() {
        return this.zzagj;
    }

    public long zzpP() {
        return this.zzagh;
    }

    public long zzpQ() {
        return this.zzagi;
    }

    public List<zzsl> zzpR() {
        return this.zzagg;
    }

    public boolean zzpS() {
        return this.zzagk;
    }

    public long zzpT() {
        return zztm.zzcf(zzs("_s", AppEventsConstants.EVENT_PARAM_VALUE_NO));
    }

    public String zzpU() {
        return zzs("_m", "");
    }
}

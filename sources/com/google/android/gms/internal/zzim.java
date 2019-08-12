package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

@zzme
public class zzim implements zzid {
    private final Map<String, zza> zzIt = new HashMap();
    private final Object zzrJ = new Object();

    public interface zza {
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(com.google.android.gms.internal.zzqw r6, java.util.Map<java.lang.String, java.lang.String> r7) {
        /*
            r5 = this;
            java.lang.String r0 = "id"
            java.lang.Object r0 = r7.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "fail"
            java.lang.Object r1 = r7.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "fail_reason"
            r7.get(r2)
            java.lang.String r2 = "result"
            java.lang.Object r2 = r7.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r4 = r5.zzrJ
            monitor-enter(r4)
            java.util.Map<java.lang.String, com.google.android.gms.internal.zzim$zza> r3 = r5.zzIt     // Catch:{ all -> 0x004a }
            java.lang.Object r3 = r3.remove(r0)     // Catch:{ all -> 0x004a }
            com.google.android.gms.internal.zzim$zza r3 = (com.google.android.gms.internal.zzim.zza) r3     // Catch:{ all -> 0x004a }
            if (r3 != 0) goto L_0x004d
            java.lang.String r1 = "Received result for unexpected method invocation: "
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x004a }
            int r2 = r0.length()     // Catch:{ all -> 0x004a }
            if (r2 == 0) goto L_0x0044
            java.lang.String r0 = r1.concat(r0)     // Catch:{ all -> 0x004a }
        L_0x003f:
            com.google.android.gms.internal.zzpk.zzbh(r0)     // Catch:{ all -> 0x004a }
            monitor-exit(r4)     // Catch:{ all -> 0x004a }
        L_0x0043:
            return
        L_0x0044:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x004a }
            r0.<init>(r1)     // Catch:{ all -> 0x004a }
            goto L_0x003f
        L_0x004a:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x004a }
            throw r0
        L_0x004d:
            boolean r0 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x004a }
            if (r0 != 0) goto L_0x0055
            monitor-exit(r4)     // Catch:{ all -> 0x004a }
            goto L_0x0043
        L_0x0055:
            if (r2 != 0) goto L_0x0059
            monitor-exit(r4)     // Catch:{ all -> 0x004a }
            goto L_0x0043
        L_0x0059:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0060 }
            r0.<init>(r2)     // Catch:{ JSONException -> 0x0060 }
        L_0x005e:
            monitor-exit(r4)     // Catch:{ all -> 0x004a }
            goto L_0x0043
        L_0x0060:
            r0 = move-exception
            r0.getMessage()     // Catch:{ all -> 0x004a }
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzim.zza(com.google.android.gms.internal.zzqw, java.util.Map):void");
    }
}

package com.google.android.gms.internal;

import com.google.android.gms.internal.zzqe.zza;

@zzme
public class zzqg implements zza {
    private final String zzIA;

    public zzqg() {
        this(null);
    }

    public zzqg(String str) {
        this.zzIA = str;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzu(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r1 = "Pinging URL: "
            java.lang.String r0 = java.lang.String.valueOf(r5)     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
            int r2 = r0.length()     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
            if (r2 == 0) goto L_0x0065
            java.lang.String r0 = r1.concat(r0)     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
        L_0x0011:
            com.google.android.gms.internal.zzqf.zzbf(r0)     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
            java.net.URL r0 = new java.net.URL     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
            r0.<init>(r5)     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
            com.google.android.gms.internal.zzqe r1 = com.google.android.gms.internal.zzel.zzeT()     // Catch:{ all -> 0x00aa }
            r2 = 1
            java.lang.String r3 = r4.zzIA     // Catch:{ all -> 0x00aa }
            r1.zza(r2, r0, r3)     // Catch:{ all -> 0x00aa }
            int r1 = r0.getResponseCode()     // Catch:{ all -> 0x00aa }
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 < r2) goto L_0x0035
            r2 = 300(0x12c, float:4.2E-43)
            if (r1 < r2) goto L_0x0061
        L_0x0035:
            java.lang.String r2 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x00aa }
            int r2 = r2.length()     // Catch:{ all -> 0x00aa }
            int r2 = r2 + 65
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00aa }
            r3.<init>(r2)     // Catch:{ all -> 0x00aa }
            java.lang.String r2 = "Received non-success response code "
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ all -> 0x00aa }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ all -> 0x00aa }
            java.lang.String r2 = " from pinging URL: "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x00aa }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x00aa }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00aa }
            com.google.android.gms.internal.zzqf.zzbh(r1)     // Catch:{ all -> 0x00aa }
        L_0x0061:
            r0.disconnect()     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
        L_0x0064:
            return
        L_0x0065:
            java.lang.String r0 = new java.lang.String     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
            r0.<init>(r1)     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
            goto L_0x0011
        L_0x006b:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = java.lang.String.valueOf(r5)
            int r1 = r1.length()
            int r1 = r1 + 32
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            int r1 = r1 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "Error while parsing ping URL: "
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r2 = ". "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.zzqf.zzbh(r0)
            goto L_0x0064
        L_0x00aa:
            r1 = move-exception
            r0.disconnect()     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
            throw r1     // Catch:{ IndexOutOfBoundsException -> 0x006b, IOException -> 0x00af, RuntimeException -> 0x00ef }
        L_0x00af:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = java.lang.String.valueOf(r5)
            int r1 = r1.length()
            int r1 = r1 + 27
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            int r1 = r1 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "Error while pinging URL: "
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r2 = ". "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.zzqf.zzbh(r0)
            goto L_0x0064
        L_0x00ef:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = java.lang.String.valueOf(r5)
            int r1 = r1.length()
            int r1 = r1 + 27
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            int r1 = r1 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "Error while pinging URL: "
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r2 = ". "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.zzqf.zzbh(r0)
            goto L_0x0064
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqg.zzu(java.lang.String):void");
    }
}

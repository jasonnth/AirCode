package com.threatmetrix.TrustDefender;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/* renamed from: com.threatmetrix.TrustDefender.ao */
class C4779ao implements LocationListener {

    /* renamed from: a */
    private static final String f4246a = C4834w.m2892a(C4779ao.class);

    /* renamed from: b */
    private Location f4247b = null;

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009b, code lost:
        if (r5 != false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a1, code lost:
        if (r3 == false) goto L_0x00a3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLocationChanged(android.location.Location r11) {
        /*
            r10 = this;
            r2 = 0
            r1 = 1
            java.lang.String r0 = f4246a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "onLocationChanged() : "
            r3.<init>(r4)
            java.lang.String r4 = r11.getProvider()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = ":"
            java.lang.StringBuilder r3 = r3.append(r4)
            double r4 = r11.getLatitude()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = ":"
            java.lang.StringBuilder r3 = r3.append(r4)
            double r4 = r11.getLongitude()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = ":"
            java.lang.StringBuilder r3 = r3.append(r4)
            float r4 = r11.getAccuracy()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.threatmetrix.TrustDefender.C4834w.m2901c(r0, r3)
            android.location.Location r7 = r10.f4247b
            if (r7 != 0) goto L_0x0051
        L_0x004c:
            if (r1 == 0) goto L_0x0050
            r10.f4247b = r11
        L_0x0050:
            return
        L_0x0051:
            long r4 = r11.getTime()
            long r8 = r7.getTime()
            long r4 = r4 - r8
            r8 = 120000(0x1d4c0, double:5.9288E-319)
            int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x00a5
            r3 = r1
        L_0x0062:
            r8 = -120000(0xfffffffffffe2b40, double:NaN)
            int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r0 >= 0) goto L_0x00a7
            r0 = r1
        L_0x006a:
            r8 = 0
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 <= 0) goto L_0x00a9
            r6 = r1
        L_0x0071:
            if (r3 != 0) goto L_0x004c
            if (r0 != 0) goto L_0x00a3
            float r0 = r11.getAccuracy()
            float r3 = r7.getAccuracy()
            float r0 = r0 - r3
            int r0 = (int) r0
            if (r0 <= 0) goto L_0x00ab
            r5 = r1
        L_0x0082:
            if (r0 >= 0) goto L_0x00ad
            r4 = r1
        L_0x0085:
            r3 = 200(0xc8, float:2.8E-43)
            if (r0 <= r3) goto L_0x00af
            r0 = r1
        L_0x008a:
            java.lang.String r3 = r11.getProvider()
            java.lang.String r7 = r7.getProvider()
            if (r3 != 0) goto L_0x00b3
            if (r7 != 0) goto L_0x00b1
            r3 = r1
        L_0x0097:
            if (r4 != 0) goto L_0x004c
            if (r6 == 0) goto L_0x009d
            if (r5 == 0) goto L_0x004c
        L_0x009d:
            if (r6 == 0) goto L_0x00a3
            if (r0 != 0) goto L_0x00a3
            if (r3 != 0) goto L_0x004c
        L_0x00a3:
            r1 = r2
            goto L_0x004c
        L_0x00a5:
            r3 = r2
            goto L_0x0062
        L_0x00a7:
            r0 = r2
            goto L_0x006a
        L_0x00a9:
            r6 = r2
            goto L_0x0071
        L_0x00ab:
            r5 = r2
            goto L_0x0082
        L_0x00ad:
            r4 = r2
            goto L_0x0085
        L_0x00af:
            r0 = r2
            goto L_0x008a
        L_0x00b1:
            r3 = r2
            goto L_0x0097
        L_0x00b3:
            boolean r3 = r3.equals(r7)
            goto L_0x0097
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4779ao.onLocationChanged(android.location.Location):void");
    }

    public void onProviderDisabled(String provider) {
        C4834w.m2901c(f4246a, "onProviderDisabled: " + provider);
    }

    public void onProviderEnabled(String provider) {
        C4834w.m2901c(f4246a, "onProviderEnabled: " + provider);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        String str = f4246a;
        StringBuilder append = new StringBuilder("onStatusChanged: ").append(provider).append(" status: ");
        String str2 = status == 2 ? "available " : status == 1 ? "temporarily unavailable" : status == 0 ? "Out of Service" : "unknown";
        C4834w.m2901c(str, append.append(str2).toString());
    }

    /* renamed from: a */
    public final Location mo45987a() {
        if (this.f4247b != null) {
            return new Location(this.f4247b);
        }
        return null;
    }
}

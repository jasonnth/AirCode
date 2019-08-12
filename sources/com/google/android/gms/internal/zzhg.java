package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.ads.formats.NativeAd.Image;

@zzme
public class zzhg extends Image {
    private final Drawable mDrawable;
    private final Uri mUri;
    private final double zzGo;
    private final zzhf zzHq;

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    /* JADX WARNING: type inference failed for: r0v12, types: [android.graphics.drawable.Drawable] */
    /* JADX WARNING: type inference failed for: r0v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzhg(com.google.android.gms.internal.zzhf r5) {
        /*
            r4 = this;
            r1 = 0
            r4.<init>()
            r4.zzHq = r5
            com.google.android.gms.internal.zzhf r0 = r4.zzHq     // Catch:{ RemoteException -> 0x0029 }
            com.google.android.gms.dynamic.IObjectWrapper r0 = r0.zzfP()     // Catch:{ RemoteException -> 0x0029 }
            if (r0 == 0) goto L_0x0030
            java.lang.Object r0 = com.google.android.gms.dynamic.zzd.zzF(r0)     // Catch:{ RemoteException -> 0x0029 }
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0     // Catch:{ RemoteException -> 0x0029 }
        L_0x0014:
            r4.mDrawable = r0
            com.google.android.gms.internal.zzhf r0 = r4.zzHq     // Catch:{ RemoteException -> 0x0032 }
            android.net.Uri r1 = r0.getUri()     // Catch:{ RemoteException -> 0x0032 }
        L_0x001c:
            r4.mUri = r1
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            com.google.android.gms.internal.zzhf r2 = r4.zzHq     // Catch:{ RemoteException -> 0x003a }
            double r0 = r2.getScale()     // Catch:{ RemoteException -> 0x003a }
        L_0x0026:
            r4.zzGo = r0
            return
        L_0x0029:
            r0 = move-exception
            java.lang.String r2 = "Failed to get drawable."
            com.google.android.gms.internal.zzqf.zzb(r2, r0)
        L_0x0030:
            r0 = r1
            goto L_0x0014
        L_0x0032:
            r0 = move-exception
            java.lang.String r2 = "Failed to get uri."
            com.google.android.gms.internal.zzqf.zzb(r2, r0)
            goto L_0x001c
        L_0x003a:
            r2 = move-exception
            java.lang.String r3 = "Failed to get scale."
            com.google.android.gms.internal.zzqf.zzb(r3, r2)
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhg.<init>(com.google.android.gms.internal.zzhf):void");
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public double getScale() {
        return this.zzGo;
    }

    public Uri getUri() {
        return this.mUri;
    }
}

package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzqx.zza;
import p005cn.jpush.android.JPushConstants;

@zzme
public class zzlr extends zzlo implements zza {
    zzlr(Context context, zzpb.zza zza, zzqw zzqw, zzlq.zza zza2) {
        super(context, zza, zzqw, zza2);
    }

    /* access modifiers changed from: protected */
    public void zziO() {
        if (this.zzPS.errorCode == -2) {
            this.zzIs.zzlv().zza((zza) this);
            zziQ();
            zzpk.zzbf("Loading HTML in WebView.");
            this.zzIs.loadDataWithBaseURL(this.zzPS.zzNJ, this.zzPS.body, "text/html", JPushConstants.ENCODING_UTF_8, null);
        }
    }

    /* access modifiers changed from: protected */
    public void zziQ() {
    }
}

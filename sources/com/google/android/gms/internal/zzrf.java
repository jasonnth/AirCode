package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.view.View;
import android.webkit.WebChromeClient.CustomViewCallback;

@zzme
@TargetApi(14)
public final class zzrf extends zzrd {
    public zzrf(zzqw zzqw) {
        super(zzqw);
    }

    public void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
        zza(view, i, customViewCallback);
    }
}

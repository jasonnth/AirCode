package com.google.android.gms.internal;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public class zzbzf extends CustomTabsServiceConnection {
    private WeakReference<zzbzg> zzcyj;

    public zzbzf(zzbzg zzbzg) {
        this.zzcyj = new WeakReference<>(zzbzg);
    }

    public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzbzg zzbzg = (zzbzg) this.zzcyj.get();
        if (zzbzg != null) {
            zzbzg.zza(customTabsClient);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzbzg zzbzg = (zzbzg) this.zzcyj.get();
        if (zzbzg != null) {
            zzbzg.zzfI();
        }
    }
}

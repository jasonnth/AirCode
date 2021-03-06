package com.google.android.gms.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.google.firebase.iid.zzb;

public class InstanceIDListenerService extends zzb {
    static void zza(Context context, zzd zzd) {
        zzd.zzHo();
        Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.putExtra("CMD", "RST");
        intent.setClassName(context, "com.google.android.gms.gcm.GcmReceiver");
        context.sendBroadcast(intent);
    }

    static void zzby(Context context) {
        Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.putExtra("CMD", "SYNC");
        intent.setClassName(context, "com.google.android.gms.gcm.GcmReceiver");
        context.sendBroadcast(intent);
    }

    public void handleIntent(Intent intent) {
        if ("com.google.android.gms.iid.InstanceID".equals(intent.getAction())) {
            Bundle bundle = null;
            String stringExtra = intent.getStringExtra("subtype");
            if (stringExtra != null) {
                bundle = new Bundle();
                bundle.putString("subtype", stringExtra);
            }
            InstanceID zza = InstanceID.zza(this, bundle);
            String stringExtra2 = intent.getStringExtra("CMD");
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", new StringBuilder(String.valueOf(stringExtra).length() + 34 + String.valueOf(stringExtra2).length()).append("Service command. subtype:").append(stringExtra).append(" command:").append(stringExtra2).toString());
            }
            if ("gcm.googleapis.com/refresh".equals(intent.getStringExtra(BaseAnalytics.FROM))) {
                zza.zzHj().zzeK(stringExtra);
                zzaF(false);
            } else if ("RST".equals(stringExtra2)) {
                zza.zzHi();
                zzaF(true);
            } else if ("RST_FULL".equals(stringExtra2)) {
                if (!zza.zzHj().isEmpty()) {
                    zza.zzHj().zzHo();
                    zzaF(true);
                }
            } else if ("SYNC".equals(stringExtra2)) {
                zza.zzHj().zzeK(stringExtra);
                zzaF(false);
            } else {
                "PING".equals(stringExtra2);
            }
        }
    }

    public void onTokenRefresh() {
    }

    public void zzaF(boolean z) {
        onTokenRefresh();
    }
}

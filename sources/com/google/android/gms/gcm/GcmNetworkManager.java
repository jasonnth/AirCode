package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.iid.zzc;
import java.util.Iterator;
import java.util.List;
import org.jmrtd.lds.LDSFile;
import p005cn.jpush.android.JPushConstants.PushService;

public class GcmNetworkManager {
    private static GcmNetworkManager zzbgm;
    private Context mContext;
    private final PendingIntent mPendingIntent = PendingIntent.getBroadcast(this.mContext, 0, new Intent().setPackage("com.google.example.invalidpackage"), 0);

    private GcmNetworkManager(Context context) {
        this.mContext = context;
    }

    public static GcmNetworkManager getInstance(Context context) {
        GcmNetworkManager gcmNetworkManager;
        synchronized (GcmNetworkManager.class) {
            if (zzbgm == null) {
                zzbgm = new GcmNetworkManager(context.getApplicationContext());
            }
            gcmNetworkManager = zzbgm;
        }
        return gcmNetworkManager;
    }

    private Intent zzGO() {
        String zzbA = zzc.zzbA(this.mContext);
        int i = -1;
        if (zzbA != null) {
            i = GoogleCloudMessaging.zzbv(this.mContext);
        }
        if (zzbA == null || i < GoogleCloudMessaging.zzbgC) {
            Log.e("GcmNetworkManager", "Google Play Services is not available, dropping GcmNetworkManager request. code=" + i);
            return null;
        }
        Intent intent = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
        intent.setPackage(zzbA);
        intent.putExtra(PushService.PARAM_APP, this.mPendingIntent);
        intent.putExtra("source", 4);
        intent.putExtra("source_version", 10400000);
        return intent;
    }

    static void zzey(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Must provide a valid tag.");
        } else if (100 < str.length()) {
            throw new IllegalArgumentException("Tag is larger than max permissible tag length (100)");
        }
    }

    private void zzez(String str) {
        boolean z = true;
        zzac.zzb(str, (Object) "GcmTaskService must not be null.");
        Intent intent = new Intent(GcmTaskService.SERVICE_ACTION_EXECUTE_TASK);
        intent.setPackage(this.mContext.getPackageName());
        List queryIntentServices = this.mContext.getPackageManager().queryIntentServices(intent, 0);
        zzac.zzb((queryIntentServices == null || queryIntentServices.size() == 0) ? false : true, (Object) "There is no GcmTaskService component registered within this package. Have you extended GcmTaskService correctly?");
        Iterator it = queryIntentServices.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((ResolveInfo) it.next()).serviceInfo.name.equals(str)) {
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        zzac.zzb(z, (Object) new StringBuilder(String.valueOf(str).length() + LDSFile.EF_SOD_TAG).append("The GcmTaskService class you provided ").append(str).append(" does not seem to support receiving com.google.android.gms.gcm.ACTION_TASK_READY.").toString());
    }

    public void schedule(Task task) {
        zzez(task.getServiceName());
        Intent zzGO = zzGO();
        if (zzGO != null) {
            Bundle extras = zzGO.getExtras();
            extras.putString("scheduler_action", "SCHEDULE_TASK");
            task.toBundle(extras);
            zzGO.putExtras(extras);
            this.mContext.sendBroadcast(zzGO);
        }
    }
}

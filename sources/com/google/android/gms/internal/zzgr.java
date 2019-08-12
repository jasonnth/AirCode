package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import java.util.List;

@zzme
public class zzgr implements zzbzg {
    private CustomTabsSession zzFX;
    private CustomTabsClient zzFY;
    private CustomTabsServiceConnection zzFZ;
    private zza zzGa;

    public interface zza {
        void zzfJ();

        void zzfK();
    }

    public static boolean zzo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (queryIntentActivities == null || resolveActivity == null) {
            return false;
        }
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            if (resolveActivity.activityInfo.name.equals(((ResolveInfo) queryIntentActivities.get(i)).activityInfo.name)) {
                return resolveActivity.activityInfo.packageName.equals(zzbze.zzcI(context));
            }
        }
        return false;
    }

    public boolean mayLaunchUrl(Uri uri, Bundle bundle, List<Bundle> list) {
        if (this.zzFY == null) {
            return false;
        }
        CustomTabsSession zzfH = zzfH();
        if (zzfH != null) {
            return zzfH.mayLaunchUrl(uri, bundle, list);
        }
        return false;
    }

    public void zza(CustomTabsClient customTabsClient) {
        this.zzFY = customTabsClient;
        this.zzFY.warmup(0);
        if (this.zzGa != null) {
            this.zzGa.zzfJ();
        }
    }

    public void zza(zza zza2) {
        this.zzGa = zza2;
    }

    public void zzd(Activity activity) {
        if (this.zzFZ != null) {
            activity.unbindService(this.zzFZ);
            this.zzFY = null;
            this.zzFX = null;
            this.zzFZ = null;
        }
    }

    public void zze(Activity activity) {
        if (this.zzFY == null) {
            String zzcI = zzbze.zzcI(activity);
            if (zzcI != null) {
                this.zzFZ = new zzbzf(this);
                CustomTabsClient.bindCustomTabsService(activity, zzcI, this.zzFZ);
            }
        }
    }

    public CustomTabsSession zzfH() {
        if (this.zzFY == null) {
            this.zzFX = null;
        } else if (this.zzFX == null) {
            this.zzFX = this.zzFY.newSession(null);
        }
        return this.zzFX;
    }

    public void zzfI() {
        this.zzFY = null;
        this.zzFX = null;
        if (this.zzGa != null) {
            this.zzGa.zzfK();
        }
    }
}

package com.airbnb.android.core;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import com.airbnb.android.core.services.JPushBroadcastReceiver;
import com.airbnb.android.core.utils.JPushHelper;
import p005cn.jpush.android.api.JPushInterface;
import p005cn.jpush.android.p006ui.PushActivity;
import p005cn.jpush.android.service.AlarmReceiver;
import p005cn.jpush.android.service.DaemonService;
import p005cn.jpush.android.service.DownloadService;
import p005cn.jpush.android.service.PushReceiver;
import p005cn.jpush.android.service.PushService;

public class JPushInitializer implements PostApplicationCreatedInitializer {
    private static boolean initialized;
    private final Context context;

    public JPushInitializer(Context context2) {
        this.context = context2;
    }

    public void initialize() {
        synchronized (JPushInitializer.class) {
            if (!initialized) {
                PackageManager pm = this.context.getPackageManager();
                Context applicationContext = this.context.getApplicationContext();
                if (JPushHelper.enableJPush(this.context)) {
                    JPushInterface.init(this.context);
                    JPushInterface.setDebugMode(false);
                    if (pm.getComponentEnabledSetting(new ComponentName(applicationContext, PushService.class)) == 2) {
                        setAllJPushServicesAndReceiversEnabled(pm, applicationContext, true);
                    }
                } else if (pm.getComponentEnabledSetting(new ComponentName(applicationContext, PushService.class)) != 2) {
                    setAllJPushServicesAndReceiversEnabled(pm, applicationContext, false);
                }
                initialized = true;
            }
        }
    }

    private static void setComponentEnabled(PackageManager pm, Context applicationContext, Class klass, boolean enabled) {
        pm.setComponentEnabledSetting(new ComponentName(applicationContext, klass), enabled ? 1 : 2, 1);
    }

    private static void setAllJPushServicesAndReceiversEnabled(PackageManager pm, Context applicationContext, boolean enabled) {
        setComponentEnabled(pm, applicationContext, PushReceiver.class, enabled);
        setComponentEnabled(pm, applicationContext, AlarmReceiver.class, enabled);
        setComponentEnabled(pm, applicationContext, JPushBroadcastReceiver.class, enabled);
        setComponentEnabled(pm, applicationContext, PushActivity.class, enabled);
        setComponentEnabled(pm, applicationContext, PushService.class, enabled);
        setComponentEnabled(pm, applicationContext, DownloadService.class, enabled);
        setComponentEnabled(pm, applicationContext, DaemonService.class, enabled);
    }
}

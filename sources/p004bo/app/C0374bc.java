package p004bo.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.airbnb.android.Manifest.permission;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PermissionUtils;
import com.appboy.support.StringUtils;
import p005cn.jpush.android.JPushConstants.PushService;

/* renamed from: bo.app.bc */
public class C0374bc {

    /* renamed from: a */
    private static final String f177a = AppboyLogger.getAppboyLogTag(C0374bc.class);

    /* renamed from: b */
    private final Context f178b;

    /* renamed from: c */
    private final C0380bi f179c;

    public C0374bc(Context context, C0380bi biVar) {
        this.f178b = context;
        this.f179c = biVar;
    }

    @TargetApi(11)
    /* renamed from: a */
    public void mo6800a(String... strArr) {
        if (this.f179c.mo6801a() != null) {
            AppboyLogger.m1739w(f177a, "The device is already registered with the GCM server and is eligible to receive GCM messages.");
            return;
        }
        AppboyLogger.m1733d(f177a, "Registering the application with the GCM server.");
        String join = StringUtils.join(strArr, ",");
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gsf");
        intent.putExtra(PushService.PARAM_APP, PendingIntent.getBroadcast(this.f178b, 0, new Intent(), 0));
        intent.putExtra("sender", join);
        this.f178b.startService(intent);
    }

    /* renamed from: a */
    public static boolean m166a(Context context, AppboyConfigurationProvider appboyConfigurationProvider) {
        return m165a(context) && m167b(context, appboyConfigurationProvider);
    }

    /* renamed from: a */
    private static boolean m165a(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gsf", 0);
            return true;
        } catch (NameNotFoundException e) {
            AppboyLogger.m1735e(f177a, "GCM requires the Google Play store installed.");
            return false;
        } catch (Exception e2) {
            AppboyLogger.m1735e(f177a, String.format("Unexpected exception while checking for %s.", new Object[]{"com.google.android.gsf"}));
            return false;
        }
    }

    @SuppressLint({"WrongConstant"})
    /* renamed from: b */
    private static boolean m167b(Context context, AppboyConfigurationProvider appboyConfigurationProvider) {
        StringBuilder sb = new StringBuilder();
        PackageManager packageManager = context.getPackageManager();
        String str = context.getPackageName() + permission.C2D_MESSAGE;
        try {
            packageManager.getPermissionInfo(str, 4096);
        } catch (NameNotFoundException e) {
            sb.append(String.format("The manifest does not define the %s permission.", new Object[]{str}));
        }
        if (!PermissionUtils.hasPermission(context, "android.permission.INTERNET")) {
            sb.append(String.format("Missing permission. The %s permission must be set so that the Android application can send the registration ID to the 3rd party server.", new Object[]{"android.permission.INTERNET"}));
        } else if (!PermissionUtils.hasPermission(context, "com.google.android.c2dm.permission.RECEIVE")) {
            sb.append(String.format("Missing permission. The %s permission must be set so that the Android application can register and receive messages.", new Object[]{"com.google.android.c2dm.permission.RECEIVE"}));
        } else if (!PermissionUtils.hasPermission(context, str)) {
            sb.append(String.format("Missing permission. The %s permission must be set so that ONLY this Android application can register and receive GCM messages.", new Object[]{str}));
        }
        if (!PermissionUtils.hasPermission(context, "android.permission.GET_ACCOUNTS")) {
            if (VERSION.SDK_INT >= 16) {
                AppboyLogger.m1737i(f177a, String.format("Missing permission. The %s permission is recommended to be set so that pre-Jelly Bean Android devices can register with the GCM server.", new Object[]{"android.permission.GET_ACCOUNTS"}));
            } else {
                sb.append(String.format("Missing permission. The %s permission must be set so that this pre-Jelly Bean Android device can register with the GCM server.", new Object[]{"android.permission.GET_ACCOUNTS"}));
            }
        }
        if (!PermissionUtils.hasPermission(context, "android.permission.WAKE_LOCK")) {
            AppboyLogger.m1737i(f177a, String.format("Missing permission. The %s permission is recommended be set so that the GCM receiver can notify users by waking the phone when a message is received.", new Object[]{"android.permission.WAKE_LOCK"}));
        }
        ComponentName componentName = new ComponentName(context, "com.appboy.AppboyGcmReceiver");
        try {
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(componentName, 2);
            if (receiverInfo == null || !receiverInfo.enabled) {
                sb.append(String.format("The %s broadcast receiver is either not found or is disabled", new Object[]{componentName.getClassName()}));
            }
        } catch (NameNotFoundException e2) {
            sb.append(String.format("No %s broadcast receiver is registered in the manifest.", new Object[]{componentName.getClassName()}));
        }
        if (appboyConfigurationProvider.getGcmSenderId() == null) {
            sb.append(String.format("Cannot find the Google Cloud Messaging sender ID attribute %s in res/values/appboy.xml.", new Object[]{"com.appboy.GCM_SENDER_ID"}));
        }
        if (sb.length() == 0) {
            return true;
        }
        AppboyLogger.m1735e(f177a, sb.toString());
        return false;
    }
}

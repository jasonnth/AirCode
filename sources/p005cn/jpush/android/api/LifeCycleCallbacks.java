package p005cn.jpush.android.api;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.HashMap;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.api.LifeCycleCallbacks */
public class LifeCycleCallbacks {
    private static final String TAG = "LifeCycleCallbacks";
    /* access modifiers changed from: private */
    public static String currentActivity = null;
    /* access modifiers changed from: private */
    public static HashMap<String, Integer> invokeMap = new HashMap<>();
    public static boolean isDebug = false;
    /* access modifiers changed from: private */
    public static boolean isFirstResume = true;
    /* access modifiers changed from: private */
    public static String lastActivity = null;
    /* access modifiers changed from: private */
    public static String mainActivity = null;
    /* access modifiers changed from: private */
    public static String tickerText = "请在您每个Activity的onResume()和onPause()的super()后调用相关统计方法：JPushInterface.onResume() 和 JPushInterface.onPause()";

    public static void registerCallback(Application paramApplication) {
        ActivityLifecycleCallbacks callback = new ActivityLifecycleCallbacks() {
            public void onActivityStopped(Activity activity) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            public void onActivityResumed(Activity activity) {
                LifeCycleCallbacks.currentActivity = activity.getClass().getName();
                if (VERSION.SDK_INT >= 14 && LifeCycleCallbacks.isDebug) {
                    if (LifeCycleCallbacks.isFirstResume) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.setPackage(activity.getPackageName());
                        intent.addCategory("android.intent.category.LAUNCHER");
                        ResolveInfo r = activity.getPackageManager().resolveActivity(intent, 0);
                        if (r == null) {
                            Logger.m1434ww(LifeCycleCallbacks.TAG, "hasnot Intent.ACTION_MAIN and Intent.CATEGORY_LAUNCHER");
                            return;
                        }
                        LifeCycleCallbacks.mainActivity = r.activityInfo.name;
                        LifeCycleCallbacks.isFirstResume = false;
                        return;
                    }
                    if (!JPushSA.isOnPauseInvoke && JPushSA.getInstance().isStatEnable() && LifeCycleCallbacks.lastActivity != null) {
                        if (LifeCycleCallbacks.invokeMap.containsKey(LifeCycleCallbacks.lastActivity)) {
                            LifeCycleCallbacks.invokeMap.put(LifeCycleCallbacks.lastActivity, Integer.valueOf(2));
                            if (!StringUtils.isEmpty(LifeCycleCallbacks.mainActivity) && LifeCycleCallbacks.mainActivity.equals(LifeCycleCallbacks.lastActivity)) {
                                AndroidUtil.showPermanentNotification(activity, LifeCycleCallbacks.tickerText, LifeCycleCallbacks.lastActivity, 2);
                            }
                        } else {
                            LifeCycleCallbacks.invokeMap.put(LifeCycleCallbacks.lastActivity, Integer.valueOf(1));
                            if (!StringUtils.isEmpty(LifeCycleCallbacks.mainActivity) && LifeCycleCallbacks.mainActivity.equals(LifeCycleCallbacks.lastActivity)) {
                                AndroidUtil.showPermanentNotification(activity, LifeCycleCallbacks.tickerText, LifeCycleCallbacks.lastActivity, 1);
                            }
                        }
                    }
                    if (activity instanceof TabActivity) {
                        Logger.m1432w(LifeCycleCallbacks.TAG, "--------------resumed");
                    } else {
                        JPushSA.isOnPauseInvoke = false;
                    }
                }
            }

            public void onActivityPaused(Activity activity) {
                LifeCycleCallbacks.lastActivity = activity.getClass().getName();
                if (StringUtils.isEmpty(LifeCycleCallbacks.currentActivity)) {
                    LifeCycleCallbacks.currentActivity = activity.getClass().getName();
                }
                if (VERSION.SDK_INT >= 14 && LifeCycleCallbacks.isDebug) {
                    if (!JPushSA.isOnResumeInvoke && JPushSA.getInstance().isStatEnable()) {
                        LifeCycleCallbacks.invokeMap.put(LifeCycleCallbacks.currentActivity, Integer.valueOf(0));
                        if (!StringUtils.isEmpty(LifeCycleCallbacks.mainActivity) && LifeCycleCallbacks.mainActivity.equals(LifeCycleCallbacks.currentActivity)) {
                            AndroidUtil.showPermanentNotification(activity, LifeCycleCallbacks.tickerText, LifeCycleCallbacks.currentActivity, 0);
                        }
                    }
                    if (activity instanceof TabActivity) {
                        Logger.m1432w(LifeCycleCallbacks.TAG, "------------paused");
                    } else {
                        JPushSA.isOnResumeInvoke = false;
                    }
                }
            }
        };
        paramApplication.unregisterActivityLifecycleCallbacks(callback);
        paramApplication.registerActivityLifecycleCallbacks(callback);
    }
}

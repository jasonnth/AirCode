package p005cn.jpush.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.api.JPushInterface;
import p005cn.jpush.android.service.ServiceInterface;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.JPushConfig */
public class JPushConfig {
    private static final String JSON_KEY_1_APPKEY = "app_key";
    private static final String JSON_KEY_1_CONFIG = "config";
    private static final String JSON_KEY_1_PACKAGE = "package";
    private static final String JSON_KEY_1_PLATFORM = "platform";
    private static final String JSON_KEY_1_SERVICE = "service";
    private static final String JSON_KEY_1_UID = "uid";
    private static final String JSON_KEY_2_PUSH_SWITCH = "push_switch";
    private static final String JSON_KEY_3_DISABLED_CHANNELS = "disabled_channels";
    private static final String JSON_KEY_3_OVERALL_ENABLED = "overall_enabled";
    private static final String TAG = "JPushConfig";
    private static String configHttpEnd = "/v1/config/";
    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public static String manifestAppKey = "";
    /* access modifiers changed from: private */
    public static String manifestChannel = "";
    /* access modifiers changed from: private */
    public static int pushEnableState = -1;
    /* access modifiers changed from: private */
    public static Object readWriteConfigLock = new Object();
    private static int retryTimes = -1;

    public static int getPushEnabledState(Context context) {
        if (pushEnableState == -1) {
            pushEnableState = Configs.getUpdateConfigEnabled(context);
        }
        return pushEnableState;
    }

    public static void getConfigFromSPF(final Context context) {
        executor.execute(new Runnable() {
            public void run() {
                synchronized (JPushConfig.readWriteConfigLock) {
                    JPushConfig.pushEnableState = Configs.getUpdateConfigEnabled(context);
                }
            }
        });
    }

    public static void updateConfig(final Context context) {
        executor.execute(new Runnable() {
            public void run() {
                synchronized (JPushConfig.readWriteConfigLock) {
                    Logger.m1418dd(JPushConfig.TAG, "JPush try to update config from server");
                    try {
                        ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                        if (ai != null) {
                            Bundle metaData = ai.metaData;
                            JPushConfig.manifestAppKey = metaData.getString(JPush.KEY_APP_KEY);
                            JPushConfig.manifestChannel = metaData.getString(JPush.KEY_JPUSH_CHANNEL);
                            Logger.m1416d(JPushConfig.TAG, "manifestAppKey :" + JPushConfig.manifestAppKey);
                            Logger.m1416d(JPushConfig.TAG, "manifestChannel :" + JPushConfig.manifestChannel);
                        }
                    } catch (NameNotFoundException e) {
                        Logger.m1420e(JPushConfig.TAG, "JPush update config fail:" + e);
                    }
                }
            }
        });
    }

    private static void updateFail(Context context) {
        retryTimes = getRetryTime(context);
        Logger.m1418dd(TAG, "Config update fail, retry:" + retryTimes);
        if (retryTimes > 0) {
            Configs.resetLastUpdateConfigTime();
            int i = retryTimes - 1;
            retryTimes = i;
            Configs.setServerConfigRetrytimes(context, i);
        }
    }

    private static void updateSuccess(Context context, String json) {
        if (!StringUtils.isEmpty(json)) {
            Logger.m1416d(TAG, "JPush update config :" + json);
            try {
                JSONObject newConfig = new JSONObject(json);
                if (!verifyConfigJSON(context, newConfig)) {
                    Logger.m1418dd(TAG, "JPush update config verify failed");
                    return;
                }
                JSONObject push_switch = newConfig.getJSONObject(JSON_KEY_1_CONFIG).getJSONObject(JSON_KEY_2_PUSH_SWITCH);
                if (push_switch.getBoolean(JSON_KEY_3_OVERALL_ENABLED)) {
                    HashSet<String> blockChannels = new HashSet<>();
                    JSONArray disabled_channels = push_switch.getJSONArray(JSON_KEY_3_DISABLED_CHANNELS);
                    int len = disabled_channels.length();
                    for (int i = 0; i < len; i++) {
                        Logger.m1416d(TAG, "loop :" + disabled_channels.getString(i));
                        blockChannels.add(disabled_channels.getString(i));
                    }
                    if (blockChannels.contains(manifestChannel)) {
                        pushEnableState = 0;
                    } else {
                        pushEnableState = 1;
                    }
                } else {
                    pushEnableState = 0;
                }
                handlePushStateChanged(context, pushEnableState);
            } catch (JSONException e) {
                Logger.m1416d(TAG, "parse JPush config failed:" + e);
            }
        }
    }

    private static void handlePushStateChanged(Context context, int newState) {
        Logger.m1418dd(TAG, "handle new JPush config with state：" + (newState == 1 ? "enable" : "disabled"));
        Configs.setUpdateConfigEnabled(context, newState);
        if (newState == 0) {
            int stopFlag = Configs.getServiceStoppedFlag(context);
            if (stopFlag <= 0 || stopFlag == 2) {
                ServiceInterface.stopPush(context, 3);
            }
        } else if (newState == 1) {
            int stopFlag2 = Configs.getServiceStoppedFlag(context);
            if (stopFlag2 == 2) {
                Configs.setServiceStopedFlag(context, 0);
                Logger.m1416d(TAG, "Push enabling to work,wake it up by init action");
                JPushInterface.init(context);
            } else if (stopFlag2 == 3) {
                Logger.m1416d(TAG, "Push enabling to work,wake it up by resume action");
                ServiceInterface.restartPush(context, 3);
            } else {
                Logger.m1418dd(TAG, "Push enabling to work,but we won't wake PushService up because service has been stopped by user setting");
            }
        }
    }

    private static boolean verifyConfigJSON(Context context, JSONObject configContainer) throws JSONException {
        if (configContainer == null) {
            Logger.m1418dd(TAG, "verify new config fail:configContainer == null");
            return false;
        }
        String appKey = configContainer.optString(JSON_KEY_1_APPKEY);
        if (StringUtils.isEmpty(appKey) || !appKey.equals(manifestAppKey)) {
            Logger.m1418dd(TAG, "verify new config fail:wrong Appkey");
            return false;
        }
        String platform = configContainer.optString("platform");
        if (StringUtils.isEmpty(platform) || !platform.equals("a")) {
            Logger.m1418dd(TAG, "verify new config fail:wrong platform");
            return false;
        }
        long uid = configContainer.optLong("uid");
        long jpushUid = Configs.getUid();
        if (jpushUid == 0 || uid == jpushUid) {
            String packageName = configContainer.optString(JSON_KEY_1_PACKAGE);
            if (StringUtils.isEmpty(packageName) || !packageName.equals(context.getPackageName())) {
                Logger.m1418dd(TAG, "verify new config fail:wrong Package Name");
                return false;
            }
            String service = configContainer.optString("service");
            if (!StringUtils.isEmpty(service) && service.equals("push")) {
                return true;
            }
            Logger.m1418dd(TAG, "verify new config fail:wrong service type");
            return false;
        }
        Logger.m1418dd(TAG, "verify new config fail:wrong UID");
        return false;
    }

    private static int getRetryTime(Context context) {
        if (retryTimes == -1) {
            retryTimes = Configs.getServerConfigRetrytimes(context);
            if (retryTimes <= 0) {
                if (System.currentTimeMillis() - Configs.getLastUpdateConfigTime(context) >= 86400000) {
                    retryTimes = 3;
                }
            }
        }
        return retryTimes;
    }
}

package p005cn.jpush.android.service;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.JPushConstants.PushService;
import p005cn.jpush.android.api.BasicPushNotificationBuilder;
import p005cn.jpush.android.api.CallBackParams;
import p005cn.jpush.android.api.JPushInterface.ErrorCode;
import p005cn.jpush.android.api.NotificationHelper;
import p005cn.jpush.android.api.TagAliasCallback;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.service.ServiceInterface */
public class ServiceInterface {
    public static final String ACTION_TAG_ALIAS_CALLBACK = "cn.jpush.android.intent.TAG_ALIAS_CALLBACK";
    public static final String ACTION_TAG_ALIAS_TIMEOUT = "cn.jpush.android.intent.TAG_ALIAS_TIMEOUT";
    public static final String EXTRA_TAGALIAS_CALLBACKCODE = "tagalias_callbackcode";
    public static final String EXTRA_TAGALIAS_SEQID = "tagalias_seqid";
    public static final String TAG = "ServiceInterface";
    private static AtomicBoolean isRegisted = new AtomicBoolean(false);
    private static TagAliasCallbackReceiver receiver = new TagAliasCallbackReceiver();
    public static ConcurrentHashMap<Long, CallBackParams> tagAliasCallbacks = new ConcurrentHashMap<>();
    private static boolean userInteraction = false;

    /* renamed from: cn.jpush.android.service.ServiceInterface$TagAliasCallbackReceiver */
    static class TagAliasCallbackReceiver extends BroadcastReceiver {
        TagAliasCallbackReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Logger.m1416d(ServiceInterface.TAG, "onreceiver tag alias callback");
            long rid = intent.getLongExtra(ServiceInterface.EXTRA_TAGALIAS_SEQID, -1);
            if (rid != 0) {
                Logger.m1428v(ServiceInterface.TAG, "onReceiver rid:" + rid);
                String tagAliasAction = intent.getAction();
                CallBackParams callBack = ServiceInterface.getTagAliasCallback(rid);
                TagAliasCallback tc = null;
                if (callBack != null) {
                    tc = callBack.tagAliasCallBack;
                    ServiceInterface.removeTagAliasCallback(rid);
                } else {
                    Logger.m1432w(ServiceInterface.TAG, "tagalias callback is null; rid=" + rid);
                }
                if (tagAliasAction.equals(ServiceInterface.ACTION_TAG_ALIAS_TIMEOUT)) {
                    if (tc != null) {
                        tc.gotResult(ErrorCode.TIMEOUT, callBack.alias, callBack.tags);
                    }
                } else if (tagAliasAction.equals(ServiceInterface.ACTION_TAG_ALIAS_CALLBACK) && tc != null) {
                    tc.gotResult(intent.getIntExtra(ServiceInterface.EXTRA_TAGALIAS_CALLBACKCODE, -1), callBack.alias, callBack.tags);
                }
                ServiceInterface.unRegisterTagAliasCallback(context);
            }
        }
    }

    public static void putTagAliasCallbacks(Long i, CallBackParams c) {
        tagAliasCallbacks.put(i, c);
    }

    public static void registerPush(Context context) {
        if (!isServiceStoped(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(PushService.ACTION_REGISTER);
            intent.putExtra(PushService.PARAM_APP, context.getPackageName());
            context.startService(intent);
        }
    }

    public static void initPush(Context context) {
        if (!isServiceStoped(context)) {
            registerOnly(context, false);
        }
    }

    private static void registerOnly(Context context, boolean registerOnly) {
        try {
            Intent intent = new Intent(context, PushService.class);
            if (registerOnly) {
                intent.setAction(PushService.ACTION_REGISTER);
                intent.putExtra(PushService.PARAM_APP, context.getPackageName());
            } else {
                intent.setAction(PushService.ACTION_INIT);
                intent.putExtra(PushService.PARAM_APP, context.getPackageName());
            }
            context.startService(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void stopPush(Context context, int stopType) {
        if (stopType > 0) {
            int type = getStopType(context);
            if (stopType == type) {
                Logger.m1418dd(TAG, "service already stop");
            } else if (stopType >= type || type <= 0 || isUserInteraction() || 0 == 0) {
                setPushReceiverEnable(context, false);
                Configs.setServiceStopedFlag(context, 1);
                Intent intent = new Intent(context, PushService.class);
                intent.setAction("cn.jpush.android.intent.STOPPUSH");
                intent.putExtra(PushService.PARAM_APP, context.getPackageName());
                context.startService(intent);
            } else {
                Logger.m1418dd(TAG, "service stop by online config already");
            }
        }
    }

    public static void unRegister(Context context) {
        if (!isServiceStoped(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(PushService.ACTION_UNREGISTER);
            intent.putExtra(PushService.PARAM_APP, context.getPackageName());
            context.startService(intent);
        }
    }

    public static void stopThread(Context context) {
        if (!isServiceStoped(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(PushService.STOP_THREAD);
            intent.putExtra(PushService.PARAM_APP, context.getPackageName());
            context.startService(intent);
        }
    }

    public static void restartPush(Context context, int restartType) {
        if (restartType > 0) {
            int type = getStopType(context);
            if (type == 0) {
                Logger.m1418dd(TAG, "service is running already");
            } else if (restartType >= type || type <= 0 || isUserInteraction() || 0 == 0) {
                setPushReceiverEnable(context, true);
                Configs.setServiceStopedFlag(context, 0);
                Intent intent = new Intent(context, PushService.class);
                intent.setAction("cn.jpush.android.intent.RESTOREPUSH");
                intent.putExtra(PushService.PARAM_APP, context.getPackageName());
                context.startService(intent);
            } else {
                Logger.m1418dd(TAG, "service stop by online config, can't restart by user setting");
            }
        }
    }

    public static void setAliasAndTags(Context context, String alias, String tags, CallBackParams callback) {
        if (!isServiceStoped(context)) {
            if (!(context instanceof Application)) {
                context = context.getApplicationContext();
            }
            if (JPush.init(context)) {
                long rid = Configs.getNextRid();
                if (!(callback == null || callback.tagAliasCallBack == null)) {
                    putTagAliasCallbacks(Long.valueOf(rid), callback);
                }
                Logger.m1424i(TAG, "tag alias rid = " + rid);
                if (!isRegisted.get()) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addCategory(JPush.PKG_NAME);
                    intentFilter.addAction(ACTION_TAG_ALIAS_TIMEOUT);
                    intentFilter.addAction(ACTION_TAG_ALIAS_CALLBACK);
                    context.registerReceiver(receiver, intentFilter);
                    isRegisted.set(true);
                } else {
                    Logger.m1416d(TAG, "tag alias callback register is called");
                }
                Intent intent = new Intent(context, PushService.class);
                intent.setAction(PushService.ACTION_ALIAS_TAGS);
                intent.putExtra(PushService.PARAM_ALIAS, alias);
                intent.putExtra(PushService.PARAM_TAGS, tags);
                intent.putExtra(PushService.PARAM_SEQ_ID, rid);
                context.startService(intent);
            }
        }
    }

    public static synchronized void unRegisterTagAliasCallback(Context context) {
        synchronized (ServiceInterface.class) {
            if (!isRegisted.get() || tagAliasCallbacks == null || !tagAliasCallbacks.isEmpty()) {
                Logger.m1428v(TAG, "tagAliasCallbacks is not empty");
            } else {
                try {
                    context.unregisterReceiver(receiver);
                } catch (IllegalArgumentException e) {
                    Logger.m1435ww(TAG, "Receiver not registered, cannot call unregisterReceiver", e);
                } catch (Exception e2) {
                    Logger.m1435ww(TAG, "other exception", e2);
                }
                isRegisted.set(false);
                Logger.m1428v(TAG, "unRegister tag alias callback");
            }
        }
        return;
    }

    static CallBackParams getTagAliasCallback(long seqId) {
        return (CallBackParams) tagAliasCallbacks.get(Long.valueOf(seqId));
    }

    public static void removeTagAliasCallback(long seqId) {
        tagAliasCallbacks.remove(Long.valueOf(seqId));
    }

    public static void setSilencePushTime(Context context, String silencePushTime) {
        if (context != null && !isServiceStoped(context)) {
            if (serviceIsBinded() || !JPush.isMultiProcess) {
                Configs.setSilencePushTime(context, silencePushTime);
                return;
            }
            Intent intent = new Intent(context, PushService.class);
            Bundle bundle = new Bundle();
            bundle.putInt(PushService.PARAM_MULTI_TYPE, 4);
            bundle.putString(PushService.PARAM_SILENCE_PUSH_TIME, silencePushTime);
            intent.setAction(PushService.ACTION_MULTI_PROCESS);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static void executeDownload(Context context, Entity entity) {
        Logger.m1428v(TAG, "action:executeDownload");
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra("body", entity);
        context.startService(intent);
    }

    public static void rtc(Context context) {
        if (!isServiceStoped(context)) {
            Intent intent1 = new Intent(context, PushService.class);
            intent1.setAction(PushService.ACTION_RTC);
            Bundle bundle = new Bundle();
            bundle.putString("rtc", "rtc");
            intent1.putExtras(bundle);
            context.startService(intent1);
        }
    }

    public static void rtcWithDelayTime(Context context, int times) {
        if (!isServiceStoped(context)) {
            Intent intent1 = new Intent(context, PushService.class);
            intent1.setAction(PushService.ACTION_RTC);
            Bundle bundle = new Bundle();
            bundle.putString("rtc", "rtc");
            bundle.putInt(PushService.DELAY_TIME, times);
            intent1.putExtras(bundle);
            context.startService(intent1);
        }
    }

    public static void reStartRtc(Context context) {
        if (!isServiceStoped(context)) {
            Logger.m1428v(TAG, "reStartRtc");
        }
    }

    public static void setPushStopExecuted(Context context, boolean stopped) {
        if (context != null) {
            if (serviceIsBinded() || !JPush.isMultiProcess) {
                Configs.setStopExecuted(context, stopped);
                return;
            }
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(PushService.ACTION_MULTI_PROCESS);
            Bundle bundle = new Bundle();
            bundle.putInt(PushService.PARAM_MULTI_TYPE, 5);
            bundle.putBoolean(PushService.PARAM_PUSH_STOPPED, stopped);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static void setNofiticationBuilder(Context context, Integer id, BasicPushNotificationBuilder builder) {
        if (context == null) {
            Logger.m1422ee(TAG, "Null context, please init JPush!");
        } else if (serviceIsBinded() || !JPush.isMultiProcess) {
            Configs.setCustomBuilder(context, "" + id, builder.toString());
        } else {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(PushService.ACTION_MULTI_PROCESS);
            Bundle bundle = new Bundle();
            bundle.putInt(PushService.PARAM_MULTI_TYPE, 1);
            bundle.putString(PushService.PARAM_NOTI_BUILDER_id, id + "");
            bundle.putString(PushService.PARAM_NOTI_BUILDER, builder.toString());
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static void setNotifactionNumber(Context context, int num) {
        if (context != null) {
            Logger.m1428v(TAG, "set notification max num : " + num);
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(PushService.ACTION_MULTI_PROCESS);
            Bundle bundle = new Bundle();
            bundle.putInt(PushService.PARAM_MULTI_TYPE, 2);
            bundle.putInt(PushService.PARAM_NOTI_MAXNUM, num);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static void clearAllNotifications(Context context) {
        NotificationHelper.cancelAllNotifications(context);
    }

    public static String getVersion() {
        return JPushConstants.SDK_VERSION;
    }

    public static boolean setSilencePushTime(Context context, int startHour, int startMins, int endHour, int endMins) {
        JSONObject timeJson = new JSONObject();
        try {
            timeJson.put(JPushConstants.START_HOUR, startHour);
            timeJson.put(JPushConstants.START_MINS, startMins);
            timeJson.put(JPushConstants.END_HOUR, endHour);
            timeJson.put(JPushConstants.END_MINS, endMins);
            setSilencePushTime(context, timeJson.toString());
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static boolean isStoped(Context context) {
        return getStopType(context) >= 1;
    }

    public static int getStopType(Context context) {
        int stopType = Configs.getServiceStoppedFlag(context);
        Logger.m1416d(TAG, "pid:" + Process.myPid() + ", stopType:" + stopType);
        return stopType;
    }

    public static boolean isServiceStoped(Context context) {
        boolean flag = isStoped(context);
        if (flag) {
            Logger.m1418dd(TAG, "The service is stopped, it will give up all the actions until you call resumePush method to resume the service.");
        }
        return flag;
    }

    public static void networkConnected(Context context) {
        if (!isServiceStoped(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(PushService.ACTION_CONNECTIVITY_CHANGE);
            Bundle bundle = new Bundle();
            bundle.putString(PushService.PARAM_CONNECTIONSTATE, ConnectionState.connected.name());
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    public static void networkDisconnected(Context context) {
        if (!isServiceStoped(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(PushService.ACTION_CONNECTIVITY_CHANGE);
            Bundle bundle = new Bundle();
            bundle.putString(PushService.PARAM_CONNECTIONSTATE, ConnectionState.disconnected.name());
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    static void setPushReceiverEnable(Context context, boolean enable) {
        PackageManager pm = context.getPackageManager();
        ComponentName compName = new ComponentName(context.getApplicationContext(), PushReceiver.class);
        ComponentName compAlaName = new ComponentName(context.getApplicationContext(), AlarmReceiver.class);
        if (!enable) {
            Logger.m1428v(TAG, "set Push/Alarm Receiver disabled");
            pm.setComponentEnabledSetting(compName, 2, 1);
            pm.setComponentEnabledSetting(compAlaName, 2, 1);
            return;
        }
        Logger.m1428v(TAG, "set Push/Alarm Receiver enabled");
        pm.setComponentEnabledSetting(compName, 1, 1);
        pm.setComponentEnabledSetting(compAlaName, 1, 1);
    }

    public static boolean isUserInteraction() {
        return userInteraction;
    }

    public static void setUserInteraction(boolean userInteraction2) {
        userInteraction = userInteraction2;
    }

    public static boolean serviceIsBinded() {
        return JPush.mRemoteService != null;
    }
}

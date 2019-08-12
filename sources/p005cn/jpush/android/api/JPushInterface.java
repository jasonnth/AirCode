package p005cn.jpush.android.api;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.airbnb.android.airdate.AirDateConstants;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.data.JPushLocalNotification;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.service.ConnectionState;
import p005cn.jpush.android.service.JPushLocalNotificationCenter;
import p005cn.jpush.android.service.PushService;
import p005cn.jpush.android.service.ServiceInterface;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.Patterns;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.api.JPushInterface */
public class JPushInterface {
    public static final String ACTION_ACTIVITY_OPENDED = "cn.jpush.android.intent.ACTION_ACTIVITY_OPENDED";
    public static final String ACTION_CONNECTION_CHANGE = "cn.jpush.android.intent.CONNECTION";
    public static final String ACTION_MESSAGE_RECEIVED = "cn.jpush.android.intent.MESSAGE_RECEIVED";
    public static final String ACTION_NOTIFICATION_OPENED = "cn.jpush.android.intent.NOTIFICATION_OPENED";
    public static final String ACTION_NOTIFICATION_RECEIVED = "cn.jpush.android.intent.NOTIFICATION_RECEIVED";
    public static final String ACTION_NOTIFICATION_RECEIVED_PROXY = "cn.jpush.android.intent.NOTIFICATION_RECEIVED_PROXY";
    public static final String ACTION_REGISTRATION_ID = "cn.jpush.android.intent.REGISTRATION";
    public static final String ACTION_RESTOREPUSH = "cn.jpush.android.intent.RESTOREPUSH";
    public static final String ACTION_RICHPUSH_CALLBACK = "cn.jpush.android.intent.ACTION_RICHPUSH_CALLBACK";
    public static final String ACTION_STATUS = "cn.jpush.android.intent.STATUS";
    public static final String ACTION_STOPPUSH = "cn.jpush.android.intent.STOPPUSH";
    private static final int CFG_SESSION_TIMEOUT = 10000;
    private static final Integer DEFAULT_NOTIFICATION_ID = Integer.valueOf(0);
    private static final int DEFAULT_NOTIFICATION_NUM = 5;
    public static final String EXTRA_ACTIVITY_PARAM = "cn.jpush.android.ACTIVITY_PARAM";
    public static final String EXTRA_ALERT = "cn.jpush.android.ALERT";
    public static final String EXTRA_APP_KEY = "cn.jpush.android.APPKEY";
    public static final String EXTRA_CONNECTION_CHANGE = "cn.jpush.android.CONNECTION_CHANGE";
    public static final String EXTRA_CONTENT_TYPE = "cn.jpush.android.CONTENT_TYPE";
    public static final String EXTRA_EXTRA = "cn.jpush.android.EXTRA";
    public static final String EXTRA_MESSAGE = "cn.jpush.android.MESSAGE";
    public static final String EXTRA_MSG_ID = "cn.jpush.android.MSG_ID";
    public static final String EXTRA_NOTIFICATION_DEVELOPER_ARG0 = "cn.jpush.android.NOTIFICATION_DEVELOPER_ARG0";
    public static final String EXTRA_NOTIFICATION_ID = "cn.jpush.android.NOTIFICATION_ID";
    public static final String EXTRA_NOTIFICATION_TITLE = "cn.jpush.android.NOTIFICATION_CONTENT_TITLE";
    public static final String EXTRA_NOTI_TYPE = "cn.jpush.android.NOTIFICATION_TYPE";
    public static final String EXTRA_PUSH_ID = "cn.jpush.android.PUSH_ID";
    public static final String EXTRA_REGISTRATION_ID = "cn.jpush.android.REGISTRATION_ID";
    public static final String EXTRA_RICHPUSH_FILE_PATH = "cn.jpush.android.FILE_PATH";
    public static final String EXTRA_RICHPUSH_FILE_TYPE = "cn.jpush.android.FILE_TYPE";
    public static final String EXTRA_RICHPUSH_HTML_PATH = "cn.jpush.android.HTML_PATH";
    public static final String EXTRA_RICHPUSH_HTML_RES = "cn.jpush.android.HTML_RES";
    public static final String EXTRA_STATUS = "cn.jpush.android.STATUS";
    public static final String EXTRA_TITLE = "cn.jpush.android.TITLE";
    private static final String PUSH_TIME = "0123456_0^23";
    private static final String TAG = "JPushInterface";
    private static JPushSA jpushSA = JPushSA.getInstance();
    private static final String soName = "jpush210";
    private static ConcurrentLinkedQueue<Long> tagAliasSequence = new ConcurrentLinkedQueue<>();

    /* renamed from: cn.jpush.android.api.JPushInterface$ErrorCode */
    public static class ErrorCode {
        public static int INVALID_ALIAS = 6003;
        public static int INVALID_JSON = 6010;
        public static int INVALID_TAGS = 6005;
        public static int INVOKE_TOO_SOON = 6011;
        public static int NULL_TAGANDALIAS = 6001;
        public static int TIMEOUT = 6002;
        public static int TOO_LONG_ALIAS = 6004;
        public static int TOO_LONG_TAGALIAS = 6008;
        public static int TOO_LONG_TAGS = 6006;
        public static int TOO_MANY_TAGS = 6007;
        public static int UNKNOWN_ERROR = 6009;
    }

    public static void init(Context context) {
        Logger.m1418dd(TAG, "action:init - sdkVersion:" + ServiceInterface.getVersion() + ", buildId:" + 329);
        try {
            System.loadLibrary(soName);
        } catch (Throwable e) {
            Logger.m1420e(TAG, "System.loadLibrary::jpush210" + e);
            e.printStackTrace();
        }
        checkContext(context);
        if (JPush.DEBUG_MODE && !AndroidUtil.isConnected(context)) {
            Logger.m1418dd(TAG, "检测到当前没有网络。此动作将在有网络时自动继续执行。");
        }
        if (JPush.init(context)) {
            setDefaultNotifactionNumber(context);
            ServiceInterface.initPush(context);
        }
    }

    public static void resumePush(Context context) {
        Logger.m1418dd(TAG, "action:resumePush");
        checkContext(context);
        if (Configs.isImLoggedIn()) {
            Logger.m1416d(TAG, "im is login, set push status to resume");
            ServiceInterface.setPushStopExecuted(context, false);
            return;
        }
        ServiceInterface.restartPush(context, 1);
    }

    public static void stopPush(Context context) {
        Logger.m1418dd(TAG, "action:stopPush");
        checkContext(context);
        if (Configs.isImLoggedIn()) {
            Logger.m1416d(TAG, "im is login, set push status to stop");
            ServiceInterface.setPushStopExecuted(context, true);
            return;
        }
        ServiceInterface.stopPush(context, 1);
    }

    public static boolean isPushStopped(Context context) {
        checkContext(context);
        return ServiceInterface.isStoped(context);
    }

    public static void setDebugMode(boolean debug) {
        JPush.DEBUG_MODE = debug;
    }

    public static void setPushTime(Context context, Set<Integer> weekDays, int startHour, int endHour) {
        checkContext(context);
        if (JPush.DEBUG_MODE && !AndroidUtil.isConnected(context)) {
            Logger.m1418dd(TAG, "检测到当前没有网络。此动作将在有网络时自动继续执行。");
        }
        if (weekDays == null) {
            enableNotification(context, true);
        } else if (weekDays.size() == 0 || weekDays.isEmpty()) {
            enableNotification(context, false);
        } else if (startHour > endHour) {
            Logger.m1422ee(TAG, "Invalid time format - startHour should less than endHour");
        } else {
            StringBuilder pushtime = new StringBuilder();
            for (Integer day : weekDays) {
                if (day.intValue() > 6 || day.intValue() < 0) {
                    Logger.m1422ee(TAG, "Invalid day format - " + day);
                    return;
                }
                pushtime.append(day);
            }
            pushtime.append("_");
            pushtime.append(startHour);
            pushtime.append("^");
            pushtime.append(endHour);
            enableNotification(context, true, pushtime.toString());
        }
    }

    public static void setSilenceTime(Context context, int startHour, int startMinute, int endHour, int endMinute) {
        checkContext(context);
        if (startHour < 0 || startMinute < 0 || endHour < 0 || endMinute < 0 || startMinute > 59 || endMinute > 59 || endHour > 23 || startHour > 23) {
            Logger.m1422ee(TAG, "Invalid parameter format, startHour and endHour should between 0 ~ 23, startMins and endMins should between 0 ~ 59. ");
        } else if (startHour == 0 && startMinute == 0 && endHour == 0 && endMinute == 0) {
            ServiceInterface.setSilencePushTime(context, "");
            Logger.m1418dd(TAG, "Remove the silence time!");
        } else if (ServiceInterface.setSilencePushTime(context, startHour, startMinute, endHour, endMinute)) {
            Logger.m1418dd(TAG, "Set Silence PushTime - " + startHour + " : " + startMinute + " -- " + endHour + " : " + endMinute);
        } else {
            Logger.m1422ee(TAG, "Set Silence PushTime Failed");
        }
    }

    public static String getRegistrationID(Context context) {
        checkContext(context);
        return Configs.getRegistrationId();
    }

    private static void checkContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        Configs.init(context);
    }

    public static String getUdid(Context context) {
        checkContext(context);
        return AndroidUtil.getDeviceId(context);
    }

    public static void setLatestNotificationNumber(Context context, int maxNum) {
        checkContext(context);
        Logger.m1418dd(TAG, "action:setLatestNotificationNumber : " + maxNum);
        if (maxNum <= 0) {
            Logger.m1422ee(TAG, "maxNum should > 0, Give up action..");
        } else {
            ServiceInterface.setNotifactionNumber(context, maxNum);
        }
    }

    public static void setDefaultPushNotificationBuilder(BasicPushNotificationBuilder builder) {
        if (builder == null) {
            throw new IllegalArgumentException("NULL notification");
        }
        ServiceInterface.setNofiticationBuilder(JPush.mApplicationContext, DEFAULT_NOTIFICATION_ID, builder);
    }

    public static void clearAllNotifications(Context context) {
        checkContext(context);
        ServiceInterface.clearAllNotifications(context);
    }

    public static void clearNotificationById(Context context, int notificationId) {
        checkContext(context);
        if (notificationId <= 0) {
            Logger.m1422ee(TAG, "Invalid notificationId, Give up action..");
        } else {
            ((NotificationManager) context.getSystemService("notification")).cancel(notificationId);
        }
    }

    public static void setPushNotificationBuilder(Integer id, BasicPushNotificationBuilder builder) {
        Logger.m1430vv(TAG, "action:setPushNotificationBuilder - id:" + id);
        if (builder == null) {
            throw new IllegalArgumentException("NULL pushNotificationBuilder");
        } else if (id.intValue() < 1) {
            Logger.m1422ee(TAG, "id should be larger than 0");
        } else {
            ServiceInterface.setNofiticationBuilder(JPush.mApplicationContext, id, builder);
        }
    }

    static boolean isValidNotificationBuilderId(int id) {
        if (id < 1) {
            return false;
        }
        if (getRecordPushNotificationBuilder(id + "") != null) {
            return true;
        }
        Logger.m1434ww(TAG, "The builder with id:" + id + " has not been set in your app, use default builder!");
        return false;
    }

    static PushNotificationBuilder getPushNotificationBuilder(int id) {
        Logger.m1430vv(TAG, "action:getPushNotificationBuilder : " + id);
        if (id < 1) {
            id = DEFAULT_NOTIFICATION_ID.intValue();
        }
        PushNotificationBuilder builder = null;
        try {
            builder = getRecordPushNotificationBuilder(id + "");
        } catch (Exception e) {
            Logger.m1417d(TAG, "获取记录builder出错!", e);
        }
        if (builder != null) {
            return builder;
        }
        Logger.m1416d(TAG, "No developer customized builder. Use default one.");
        return new DefaultPushNotificationBuilder();
    }

    @Deprecated
    public static void setAliasAndTags(Context context, String alias, Set<String> tags) {
        checkContext(context);
        setAliasAndTagsWithCallBack(context, alias, tags, null, false);
    }

    public static void setAliasAndTags(Context context, String alias, Set<String> tags, TagAliasCallback callback) {
        checkContext(context);
        if (!isSetTagAliasInValidTime(System.currentTimeMillis())) {
            Logger.m1432w(TAG, "set tags/alias too soon,over 10 times in 10s");
            if (callback != null) {
                callback.gotResult(ErrorCode.INVOKE_TOO_SOON, alias, tags);
                return;
            }
            return;
        }
        setAliasAndTagsWithCallBack(context, alias, tags, callback, true);
    }

    public static void setTags(Context context, Set<String> tags, TagAliasCallback callback) {
        checkContext(context);
        setAliasAndTags(context, null, tags, callback);
    }

    public static void setAlias(Context context, String alias, TagAliasCallback callback) {
        checkContext(context);
        setAliasAndTags(context, alias, null, callback);
    }

    public static Set<String> filterValidTags(Set<String> tags) {
        if (tags == null) {
            return null;
        }
        if (tags.isEmpty()) {
            return tags;
        }
        Set<String> retTags = new LinkedHashSet<>();
        int i = 0;
        for (String tag : tags) {
            if (StringUtils.isEmpty(tag) || !Patterns.isValidTagAndAlais(tag)) {
                Logger.m1422ee(TAG, "Invalid tag : " + tag);
            } else {
                retTags.add(tag);
                i++;
                if (i >= 100) {
                    Logger.m1434ww(TAG, "The lenght of tags maybe more than 100.");
                    return retTags;
                }
            }
        }
        return retTags;
    }

    public static void reportNotificationOpened(Context context, String msgId) {
        checkContext(context);
        if (StringUtils.isEmpty(msgId)) {
            Logger.m1422ee(TAG, "The msgId is not valid - " + msgId);
        }
        ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_NOTIFACTION_OPENED, context);
    }

    public static boolean getConnectionState(Context context) {
        checkContext(context);
        return ConnectionState.connected == Configs.getConnectionState(context);
    }

    public static void onResume(Context context) {
        checkContext(context);
        jpushSA.onResume(context);
    }

    public static void onPause(Context context) {
        checkContext(context);
        jpushSA.onPause(context);
    }

    public static void onFragmentResume(Context context, String fragmentName) {
        checkContext(context);
        jpushSA.onFragmentResume(context, fragmentName);
    }

    public static void onFragmentPause(Context context, String fragmentName) {
        checkContext(context);
        jpushSA.onFragmentPause(context, fragmentName);
    }

    public static void onKillProcess(Context context) {
        jpushSA.onKillProcess(context);
    }

    public static void setStatisticsSessionTimeout(long timeout) {
        if (timeout < 10) {
            Logger.m1434ww(TAG, "sesseion timeout less than 10s");
        } else if (timeout > AirDateConstants.SECONDS_PER_DAY) {
            Logger.m1434ww(TAG, "sesseion timeout larger than 1day");
        } else {
            jpushSA.setInterval(timeout);
        }
    }

    public static void setStatisticsEnable(boolean enable) {
        jpushSA.setStatEnable(enable);
    }

    public static void initCrashHandler(Context context) {
        checkContext(context);
        JPushCrashHandler.getInstance().init(context);
    }

    public static void addLocalNotification(Context context, JPushLocalNotification notification) {
        checkContext(context);
        if (!JPush.isMultiProcess) {
            JPushLocalNotificationCenter.getInstance(context).add(context, notification);
            return;
        }
        Intent intent = new Intent(context, PushService.class);
        Bundle bundle = new Bundle();
        bundle.putInt(JPushConstants.PushService.PARAM_MULTI_TYPE, 6);
        bundle.putSerializable(JPushConstants.PushService.PARAM_LOCAL_NOTIFICATION, notification);
        intent.putExtras(bundle);
        intent.setAction(JPushConstants.PushService.ACTION_MULTI_PROCESS);
        context.startService(intent);
    }

    public static void removeLocalNotification(Context context, long notificationId) {
        checkContext(context);
        if (!JPush.isMultiProcess) {
            JPushLocalNotificationCenter.getInstance(context).remove(context, notificationId);
            return;
        }
        Intent intent = new Intent(context, PushService.class);
        Bundle bundle = new Bundle();
        bundle.putInt(JPushConstants.PushService.PARAM_MULTI_TYPE, 7);
        bundle.putLong(JPushConstants.PushService.PARAM_LOCAL_NOTIFICATION_ID, notificationId);
        intent.putExtras(bundle);
        intent.setAction(JPushConstants.PushService.ACTION_MULTI_PROCESS);
        context.startService(intent);
    }

    public static void clearLocalNotifications(Context context) {
        checkContext(context);
        if (!JPush.isMultiProcess) {
            JPushLocalNotificationCenter.getInstance(context).clear(context);
            return;
        }
        Intent intent = new Intent(context, PushService.class);
        intent.setAction(JPushConstants.PushService.ACTION_MULTI_PROCESS);
        context.startService(intent);
    }

    private static boolean checkLengthOfTags(String tags) {
        int length = 0;
        if (!StringUtils.isEmpty(tags)) {
            length = 0 + tags.getBytes().length;
        }
        return length <= 1000;
    }

    private static void enableNotification(Context context, boolean enabled, String pushTimes) {
        Logger.m1416d(TAG, "Action:enableNotification");
        Configs.setNotificationEnabled(context, enabled);
        if (!enabled) {
            Logger.m1418dd(TAG, "action:setPushTime - closed");
            return;
        }
        String timeSliceReg = "([0-9]|1[0-9]|2[0-3])\\^([0-9]|1[0-9]|2[0-3])";
        if (Pattern.compile("([0-6]{0,7})_((" + timeSliceReg + ")|(" + timeSliceReg + "-)+(" + timeSliceReg + "))").matcher(pushTimes).matches()) {
            String preTimes = Configs.getPushTime(context);
            if (pushTimes.equals(preTimes)) {
                Logger.m1418dd(TAG, "Already SetPushTime, give up - " + preTimes);
                return;
            }
            Logger.m1418dd(TAG, "action:setPushTime - enabled:" + enabled + ", pushTime:" + pushTimes);
            if (ServiceInterface.serviceIsBinded() || !JPush.isMultiProcess) {
                Configs.setPushTime(context, pushTimes);
                return;
            }
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(JPushConstants.PushService.ACTION_MULTI_PROCESS);
            Bundle bundle = new Bundle();
            bundle.putInt(JPushConstants.PushService.PARAM_MULTI_TYPE, 3);
            bundle.putString(JPushConstants.PushService.PARAM_PUSH_TIME, pushTimes);
            intent.putExtras(bundle);
            context.startService(intent);
            return;
        }
        Logger.m1422ee(TAG, "Invalid time format - " + pushTimes);
    }

    private static void enableNotification(Context context, boolean isEnable) {
        enableNotification(context, isEnable, PUSH_TIME);
    }

    private static String getStringTags(Set<String> tags) {
        if (tags == null) {
            return null;
        }
        if (tags.isEmpty()) {
            return "";
        }
        String stags = null;
        int i = 0;
        for (String tag : tags) {
            if (StringUtils.isEmpty(tag) || !Patterns.isValidTagAndAlais(tag)) {
                Logger.m1422ee(TAG, "Invalid tag: " + tag);
            } else {
                if (stags == null) {
                    stags = tag;
                } else {
                    stags = stags + "," + tag;
                }
                i++;
                if (i >= 100) {
                    return stags;
                }
            }
        }
        return stags;
    }

    private static void setDefaultNotifactionNumber(Context context) {
        if (Configs.getNotificationMaxNum(context) == -1) {
            setLatestNotificationNumber(context, 5);
        }
    }

    private static PushNotificationBuilder getRecordPushNotificationBuilder(String id) throws NumberFormatException {
        Logger.m1428v(TAG, "getRecordPushNotificationBuilder - " + id);
        String customBuilderStr = Configs.getCustomBuilder(JPush.mApplicationContext, id);
        if (StringUtils.isEmpty(customBuilderStr)) {
            return null;
        }
        Logger.m1428v(TAG, "Customized builder from saved preference - " + customBuilderStr);
        return BasicPushNotificationBuilder.parseFromPreference(customBuilderStr);
    }

    private static boolean isSetTagAliasInValidTime(long time) {
        if (tagAliasSequence.size() < 10) {
            tagAliasSequence.offer(Long.valueOf(time));
            return true;
        } else if (time - ((Long) tagAliasSequence.element()).longValue() <= 10000) {
            return false;
        } else {
            while (tagAliasSequence.size() >= 10) {
                tagAliasSequence.poll();
            }
            tagAliasSequence.offer(Long.valueOf(time));
            return true;
        }
    }

    private static void setAliasAndTagsWithCallBack(Context context, String alias, Set<String> tags, TagAliasCallback callback, boolean isNew) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        if (JPush.DEBUG_MODE && !AndroidUtil.isConnected(context)) {
            Logger.m1418dd(TAG, "检测到当前没有网络。此动作将在有网络时自动继续执行。");
        }
        if (alias != null) {
            int aliasCode = Patterns.checkAlias(alias);
            if (aliasCode != 0) {
                Logger.m1418dd(TAG, "Invalid alias: " + alias + ", will not set alias this time.");
                if (callback != null) {
                    callback.gotResult(aliasCode, alias, tags);
                    return;
                }
                return;
            }
        }
        if (callback == null && !isNew) {
            tags = filterValidTags(tags);
        }
        if (tags != null) {
            int tagsCode = Patterns.checkTags(tags);
            if (tagsCode != 0) {
                Logger.m1418dd(TAG, "Invalid tags, will not set tags this time.");
                if (callback != null) {
                    callback.gotResult(tagsCode, alias, tags);
                    return;
                }
                return;
            }
        }
        String stags = getStringTags(tags);
        if (alias == null && stags == null) {
            Logger.m1422ee(TAG, "NULL alias and tags. Give up action.");
            if (callback != null) {
                callback.gotResult(ErrorCode.NULL_TAGANDALIAS, alias, tags);
            }
        } else if (stags == null || checkLengthOfTags(stags.replaceAll(",", ""))) {
            Logger.m1418dd(TAG, "action:setAliasAndTags - alias:" + alias + ", tags:" + stags);
            ServiceInterface.setAliasAndTags(context, alias, stags, new CallBackParams(alias, tags, callback));
        } else {
            if (callback != null) {
                callback.gotResult(ErrorCode.TOO_LONG_TAGALIAS, alias, tags);
            }
            Logger.m1422ee(TAG, "The length of tags should be less than 1000 bytes.");
        }
    }

    private static void setAliasAndTagsInBackground(Context context, String alias, Set<String> tags) {
        if (!isSetTagAliasInValidTime(System.currentTimeMillis())) {
            Logger.m1432w(TAG, "set tags/alias too soon,over 10 times in 10s");
        } else if (context == null) {
            throw new IllegalArgumentException("NULL context");
        } else {
            if (JPush.DEBUG_MODE && !AndroidUtil.isConnected(context)) {
                Logger.m1418dd(TAG, "检测到当前没有网络。setAliasAndTagsInBackground此动作将在有网络时自动继续执行。");
            }
            if (alias == null || Patterns.checkAlias(alias) == 0) {
                Set<String> tags2 = filterValidTags(tags);
                if (tags2 == null || Patterns.checkTags(tags2) == 0) {
                    String stags = getStringTags(tags2);
                    if (alias == null && stags == null) {
                        Logger.m1422ee(TAG, "NULL alias and tags. Give up action.");
                    } else if (stags == null || checkLengthOfTags(stags.replaceAll(",", ""))) {
                        Logger.m1418dd(TAG, "action:setAliasAndTagsInBackground - alias:" + alias + ", tags:" + stags);
                        ServiceInterface.setAliasAndTags(context, alias, stags, null);
                    } else {
                        Logger.m1422ee(TAG, "The length of tags should be less than 1000 bytes.");
                    }
                } else {
                    Logger.m1418dd(TAG, "Invalid tags, will not set tags this time.");
                }
            } else {
                Logger.m1418dd(TAG, "Invalid alias: " + alias + ", will not set alias this time.");
            }
        }
    }

    private static void setTagsInBackground(Context context, Set<String> tags) {
        setAliasAndTagsInBackground(context, null, tags);
    }

    private static void setAliasInBackground(Context context, String alias) {
        setAliasAndTagsInBackground(context, alias, null);
    }

    public static void requestPermission(Context context) {
        if (VERSION.SDK_INT < 23 || !(context instanceof Activity)) {
            Logger.m1418dd(TAG, "android.os.Build.VERSION.SDK_INT<23");
            return;
        }
        String[] permissions2 = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE"};
        boolean sdBool = AndroidUtil.hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
        boolean phBool = AndroidUtil.hasPermission(context, "android.permission.READ_PHONE_STATE");
        if (!sdBool || !phBool) {
            try {
                Class.forName("android.app.Activity").getDeclaredMethod("requestPermissions", new Class[]{String[].class, Integer.TYPE}).invoke(context, new Object[]{permissions2, Integer.valueOf(1)});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

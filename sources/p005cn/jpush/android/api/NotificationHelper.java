package p005cn.jpush.android.api;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.zip.Adler32;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.data.ShowEntity;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.service.PushReceiver;
import p005cn.jpush.android.service.PushService;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.DirectoryUtils;
import p005cn.jpush.android.util.FileUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.api.NotificationHelper */
public class NotificationHelper {
    public static final String RES_TYPE_DRAWABLE = "R$drawable";
    public static final String RES_TYPE_ID = "R$id";
    public static final String RES_TYPE_LAYOUT = "R$layout";
    private static final String TAG = "NotificationHelper";
    public static final int TYPE_MESSAGE_DOWNLOADED = 1;
    public static final int TYPE_MESSAGE_SHOW = 0;
    private static Queue<Integer> mNotifactionIdQueue = new LinkedList();

    /* renamed from: cn.jpush.android.api.NotificationHelper$MyHandler */
    static class MyHandler extends Handler {

        /* renamed from: nm */
        private NotificationManager f964nm;
        private Notification notification;

        public MyHandler(Looper looper, Notification notification2, NotificationManager nm) {
            super(looper);
            this.notification = notification2;
            this.f964nm = nm;
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            boolean isSucc = bundle.getBoolean("isSucc");
            String filePath = bundle.getString("filePath");
            if (!isSucc || TextUtils.isEmpty(filePath)) {
                Logger.m1428v(NotificationHelper.TAG, "No customized icon");
            } else {
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                    if (bitmap != null) {
                        this.notification.contentView.setImageViewBitmap(16908294, bitmap);
                    }
                } catch (Exception e) {
                    Logger.m1421e(NotificationHelper.TAG, "", e);
                }
            }
            String msgId = bundle.getString("messageId");
            if (!StringUtils.isEmpty(msgId)) {
                ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_NOTIFACTION_SHOW, JPush.mApplicationContext);
            }
            if (this.notification != null) {
                this.f964nm.notify(msg.what, this.notification);
            }
        }
    }

    public static void cancelAllNotifications(Context context) {
        while (true) {
            Integer notifId = (Integer) mNotifactionIdQueue.poll();
            if (notifId != null) {
                cancelNotification(context, notifId.intValue());
            } else {
                return;
            }
        }
    }

    public static void decreaseNotificationQueue(Context context, int num) {
        int numToDes = mNotifactionIdQueue.size() - num;
        if (numToDes > 0) {
            for (int i = 0; i < numToDes; i++) {
                Integer notifId = (Integer) mNotifactionIdQueue.poll();
                if (notifId != null) {
                    cancelNotification(context, notifId.intValue());
                }
            }
        }
    }

    public static void cancelNotification(Context context, int notifiId) {
        Logger.m1416d(TAG, "action:cleanNotification - notificationId:" + notifiId);
        if (context == null) {
            context = JPush.mApplicationContext;
        }
        ((NotificationManager) context.getSystemService("notification")).cancel(notifiId);
    }

    public static void cancelNotification(Context context, Entity entity, int type) {
        Logger.m1416d(TAG, "action:cleanNotification - messageId:" + entity.messageId);
        if (context == null) {
            context = JPush.mApplicationContext;
        }
        ((NotificationManager) context.getSystemService("notification")).cancel(getNofiticationID(entity, type));
    }

    public static void cancelAllNotification(Context context, String msgId) {
        Logger.m1416d(TAG, "action:cleanAllNotification - messageId:" + msgId);
        if (context == null) {
            context = JPush.mApplicationContext;
        }
        NotificationManager nm = (NotificationManager) context.getSystemService("notification");
        nm.cancel(getNofiticationID(msgId, 0));
        nm.cancel(getNofiticationID(msgId, 1));
    }

    public static Notification getDefaultNotification(Context context, int notifyId, Intent intent, Entity entity, boolean needOriginalPakcageIcon) {
        return getDefaultNotification(context, notifyId, intent, entity, needOriginalPakcageIcon, false);
    }

    public static Notification getBrocastNotification(Context context, int notifyId, Intent intent, Entity entity, boolean needOriginalPakcageIcon) {
        return getDefaultNotification(context, notifyId, intent, entity, needOriginalPakcageIcon, true);
    }

    public static Notification getDefaultNotification(Context context, int notifyId, Intent intent, Entity entity, boolean needOriginalPakcageIcon, boolean needBroadcast) {
        PendingIntent pendingIntent;
        int appIconId = -1;
        if (needOriginalPakcageIcon) {
            Logger.m1416d(TAG, "Need to use original app icon.");
            try {
                appIconId = context.getPackageManager().getPackageInfo(context.getPackageName(), 256).applicationInfo.icon;
            } catch (NameNotFoundException e) {
                Logger.m1417d(TAG, "", e);
            }
        }
        if (appIconId < 0) {
            Logger.m1432w(TAG, "No valid notification icon.");
            return null;
        }
        if (needBroadcast) {
            pendingIntent = PendingIntent.getBroadcast(context, notifyId, intent, 134217728);
        } else {
            pendingIntent = PendingIntent.getActivity(context, notifyId, intent, 134217728);
        }
        if (VERSION.SDK_INT >= 11) {
            Builder notificationBuidler = new Builder(context.getApplicationContext()).setWhen(System.currentTimeMillis()).setSmallIcon(appIconId).setTicker(entity.notificationContent);
            if (entity.isDeveloperMessage) {
                notificationBuidler.setDefaults(3);
                if (AndroidUtil.isInSilencePushTime(context)) {
                    notificationBuidler.setDefaults(0);
                }
            }
            if (!StringUtils.isEmpty(entity._fullImagePath)) {
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(entity._fullImagePath);
                    if (bitmap != null) {
                        Integer imageId = (Integer) getResourceId(RES_TYPE_ID, new String[]{"state_bar_image_view"}).get("state_bar_image_view");
                        Integer layoutId = (Integer) getResourceId(RES_TYPE_LAYOUT, new String[]{"message_image_state_bar_layout"}).get("message_image_state_bar_layout");
                        if (imageId == null || layoutId == null || imageId.intValue() <= 0 || layoutId.intValue() <= 0) {
                            Logger.m1432w(TAG, "get res id failed");
                            return null;
                        }
                        RemoteViews imageRemoteView = new RemoteViews(context.getPackageName(), layoutId.intValue());
                        imageRemoteView.setImageViewBitmap(imageId.intValue(), bitmap);
                        notificationBuidler.setContent(imageRemoteView);
                        notificationBuidler.setContentIntent(pendingIntent);
                    } else {
                        Logger.m1432w(TAG, "get status bar photo error");
                        return null;
                    }
                } catch (Exception e2) {
                    Logger.m1433w(TAG, "init status bar photo error", e2);
                    return null;
                }
            } else {
                notificationBuidler.setContentTitle(entity.notificationTitle).setContentText(entity.notificationContent).setContentIntent(pendingIntent);
            }
            Notification notification = notificationBuidler.getNotification();
            notification.flags = getNotifiFlag(entity.notificationRemoveMode);
            return notification;
        }
        Notification notification2 = new Notification();
        notification2.when = System.currentTimeMillis();
        notification2.icon = appIconId;
        notification2.tickerText = entity.notificationContent;
        notification2.flags = getNotifiFlag(entity.notificationRemoveMode);
        if (entity.isDeveloperMessage) {
            notification2.defaults = 3;
            if (AndroidUtil.isInSilencePushTime(context)) {
                notification2.defaults = 0;
            }
        }
        if (!StringUtils.isEmpty(entity._fullImagePath)) {
            try {
                Bitmap bitmap2 = BitmapFactory.decodeFile(entity._fullImagePath);
                if (bitmap2 != null) {
                    Integer imageId2 = (Integer) getResourceId(RES_TYPE_ID, new String[]{"state_bar_image_view"}).get("state_bar_image_view");
                    Integer layoutId2 = (Integer) getResourceId(RES_TYPE_LAYOUT, new String[]{"message_image_state_bar_layout"}).get("message_image_state_bar_layout");
                    if (imageId2 == null || layoutId2 == null || imageId2.intValue() <= 0 || layoutId2.intValue() <= 0) {
                        Logger.m1432w(TAG, "get res id failed");
                        return null;
                    }
                    RemoteViews imageRemoteView2 = new RemoteViews(context.getPackageName(), layoutId2.intValue());
                    imageRemoteView2.setImageViewBitmap(imageId2.intValue(), bitmap2);
                    notification2.contentView = imageRemoteView2;
                    notification2.contentIntent = pendingIntent;
                    return notification2;
                }
                Logger.m1432w(TAG, "get status bar photo error");
                return null;
            } catch (Exception e3) {
                Logger.m1433w(TAG, "init status bar photo error", e3);
                return null;
            }
        } else {
            methodInvokeNoti(notification2, context, entity.notificationTitle, entity.notificationContent, pendingIntent);
            return notification2;
        }
    }

    public static void showNotification(final Context context, final Entity entity) {
        if (Thread.currentThread().getId() == PushService.mainThreadId) {
            Logger.m1424i(TAG, "start new thread");
            new Thread(new Runnable() {
                public void run() {
                    NotificationHelper.showNotification(context, entity, false, false);
                }
            }).start();
            return;
        }
        showNotification(context, entity, false, false);
    }

    public static void showNotification(Context context, Entity entity, boolean needOriginalPakcageIcon, boolean isUpdateVersion) {
        Logger.m1428v(TAG, "action:showNotification");
        int notifiId = getNofiticationID(entity, 0);
        if (entity.isDeveloperMessage && entity.notificationOnly) {
            deliveryDeveloperPushNotification(context, notifiId, entity);
        }
    }

    public static int getNofiticationID(Entity entity, int type) {
        String notId = entity.messageId;
        if (!StringUtils.isEmpty(entity.overrideMessageId)) {
            notId = entity.overrideMessageId;
        }
        return getNofiticationID(notId, type);
    }

    private static void deliveryDeveloperPushNotification(Context context, int notifiId, Entity entity) {
        String toAppPackage;
        Intent intent;
        PendingIntent pi;
        Intent intent2;
        NotificationManager nm = (NotificationManager) context.getSystemService("notification");
        if (entity instanceof ShowEntity) {
            String notificationContent = entity.notificationContent;
            String notificationTitle = entity.notificationTitle;
            String extraJson = entity.extras;
            if (StringUtils.isEmpty(entity.appId)) {
                toAppPackage = context.getPackageName();
            } else {
                toAppPackage = entity.appId;
            }
            Map<String, String> extras = new HashMap<>();
            extras.put(JPushInterface.EXTRA_MSG_ID, entity.messageId);
            extras.put(JPushInterface.EXTRA_ALERT, notificationContent);
            if (!TextUtils.isEmpty(notificationTitle)) {
                extras.put(JPushInterface.EXTRA_NOTIFICATION_TITLE, notificationTitle);
            }
            if (!StringUtils.isEmpty(extraJson)) {
                extras.put(JPushInterface.EXTRA_EXTRA, extraJson);
            }
            if (StringUtils.isEmpty(notificationContent)) {
                sendNotificationReceivedBroadcast(context, extras, 0, "", toAppPackage, entity);
                return;
            }
            PushNotificationBuilder builder = JPushInterface.getPushNotificationBuilder(entity.notificationBuilderId);
            String developerArg0 = builder.getDeveloperArg0();
            Notification notification = builder.buildNotification(notificationContent, extras);
            if (notification == null || StringUtils.isEmpty(notificationContent)) {
                Logger.m1434ww(TAG, "Got NULL notification. Give up to show.");
                return;
            }
            if (!entity.isRichPush()) {
                if (AndroidUtil.hasReceiver(context, PushReceiver.class.getCanonicalName())) {
                    intent2 = new Intent("cn.jpush.android.intent.NOTIFICATION_OPENED_PROXY." + UUID.randomUUID().toString());
                    intent2.setClass(context, PushReceiver.class);
                    intent2.putExtra(JPushInterface.EXTRA_NOTI_TYPE, "" + entity.notificationType);
                } else {
                    Logger.m1424i(TAG, "the PushReceiver in manifest is deleted by some other apps,we should send the broadcast directly.");
                    intent2 = new Intent(JPushInterface.ACTION_NOTIFICATION_OPENED);
                    intent2.addCategory(toAppPackage);
                }
                putExtras(intent2, extras, notifiId);
                intent2.putExtra(JPushInterface.EXTRA_ALERT, notificationContent);
                intent2.putExtra(JPushConstants.PushService.PARAM_APP, toAppPackage);
                if (!StringUtils.isEmpty(developerArg0)) {
                    intent2.putExtra(JPushInterface.EXTRA_NOTIFICATION_DEVELOPER_ARG0, developerArg0);
                }
                pi = PendingIntent.getBroadcast(context, 0, intent2, 1073741824);
            } else {
                Logger.m1424i(TAG, "delivery rich push type: " + ((ShowEntity) entity).richType);
                if (3 == ((ShowEntity) entity).richType || 4 == ((ShowEntity) entity).richType || ((ShowEntity) entity).richType == 0) {
                    intent = AndroidUtil.getIntentForStartPushActivity(context, entity, false);
                } else if (2 == ((ShowEntity) entity).richType) {
                    intent = AndroidUtil.getIntentForStartPopWin(context, entity);
                } else {
                    intent = AndroidUtil.getIntentForStartPushActivity(context, entity, false);
                }
                pi = PendingIntent.getActivity(context, notifiId, intent, 134217728);
            }
            notification.contentIntent = pi;
            if (!JPushInterface.isValidNotificationBuilderId(entity.notificationBuilderId)) {
                if (1 == entity.notificationType) {
                    entity.notificationRemoveMode = 1;
                }
                notification.flags = getNotifiFlag(entity.notificationRemoveMode);
                if (notification.defaults == 0) {
                    notification.defaults = 3;
                }
            }
            if (AndroidUtil.isInSilencePushTime(context)) {
                notification.defaults = 0;
            }
            if (notification != null) {
                nm.notify(notifiId, notification);
            }
            if (!mNotifactionIdQueue.contains(Integer.valueOf(notifiId)) && entity.notificationType != 1) {
                mNotifactionIdQueue.offer(Integer.valueOf(notifiId));
            }
            if (mNotifactionIdQueue.size() > Configs.getNotificationMaxNum(context)) {
                try {
                    int idCancel = ((Integer) mNotifactionIdQueue.poll()).intValue();
                    if (idCancel != 0) {
                        nm.cancel(idCancel);
                    }
                } catch (Exception e) {
                    Logger.m1420e(TAG, "Cancel notifaction error");
                }
            }
            if (1 != entity.notificationType) {
                ReportHelper.reportMsgResult(entity.messageId, StatusCode.RESULT_TYPE_NOTIFACTION_SHOW, context);
            }
            sendNotificationReceivedBroadcast(context, extras, notifiId, developerArg0, toAppPackage, entity);
        }
    }

    private static void sendNotificationReceivedBroadcast(Context context, Map<String, String> extras, int notifiId, String developerArg0, String toAppPackage, Entity entity) {
        Logger.m1418dd(TAG, "Send push received broadcast to developer defined receiver");
        Intent intent = new Intent(JPushInterface.ACTION_NOTIFICATION_RECEIVED);
        putExtras(intent, extras, notifiId);
        if (!StringUtils.isEmpty(developerArg0)) {
            intent.putExtra(JPushInterface.EXTRA_NOTIFICATION_DEVELOPER_ARG0, developerArg0);
        }
        if (entity.isRichPush() && (entity instanceof ShowEntity)) {
            ShowEntity se = (ShowEntity) entity;
            if (!(se.richType == 0 || se.richType == 4)) {
                if (se._webPagePath != null && se._webPagePath.startsWith("file://")) {
                    se._webPagePath = se._webPagePath.replaceFirst("file://", "");
                    intent.putExtra(JPushInterface.EXTRA_RICHPUSH_HTML_PATH, se._webPagePath);
                }
                if (se.showResourceList != null && se.showResourceList.size() > 0) {
                    StringBuilder htmlRes = new StringBuilder();
                    String resPath = DirectoryUtils.getDirectoryRichPush(context, entity.messageId);
                    Iterator it = se.showResourceList.iterator();
                    while (it.hasNext()) {
                        String s = (String) it.next();
                        if (s.startsWith(JPushConstants.HTTP_PRE)) {
                            s = FileUtil.getFileNameFromUrl(s);
                        }
                        if (StringUtils.isEmpty(htmlRes.toString())) {
                            htmlRes.append(resPath).append(s);
                        } else {
                            htmlRes.append(",").append(resPath).append(s);
                        }
                    }
                    intent.putExtra(JPushInterface.EXTRA_RICHPUSH_HTML_RES, htmlRes.toString());
                }
            }
        }
        intent.addCategory(toAppPackage);
        context.sendBroadcast(intent, toAppPackage + JPushConstants.PUSH_MESSAGE_PERMISSION_POSTFIX);
    }

    private static void putExtras(Intent intent, Map<String, String> extras, int notifiId) {
        for (String key : extras.keySet()) {
            intent.putExtra(key, (String) extras.get(key));
        }
        if (notifiId != 0) {
            intent.putExtra(JPushInterface.EXTRA_NOTIFICATION_ID, notifiId);
        }
    }

    private static int getNofiticationID(String msgId, int idType) {
        if (TextUtils.isEmpty(msgId)) {
            Logger.m1416d(TAG, "action:getNofiticationID - empty messageId");
            return 0;
        }
        try {
            return Integer.valueOf(msgId).intValue();
        } catch (Exception e) {
            Logger.m1432w(TAG, "Ths msgId is not a integer");
            Adler32 adler32 = new Adler32();
            adler32.update(msgId.getBytes());
            int nId = (int) adler32.getValue();
            if (nId < 0) {
                nId = Math.abs(nId);
            }
            int nId2 = nId + (13889152 * idType);
            if (nId2 < 0) {
                return Math.abs(nId2);
            }
            return nId2;
        }
    }

    public static int getNotificationRingmode(int code) {
        switch (code) {
            case 0:
                return -1;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            default:
                return 4;
        }
    }

    public static int getNotifiIcon(int code) {
        switch (code) {
            case -1:
                int id = 0;
                try {
                    id = ((Integer) getResourceId(RES_TYPE_DRAWABLE, new String[]{JPushConstants.FIXED_MESSAGE_ICON_NAME}).get(JPushConstants.FIXED_MESSAGE_ICON_NAME)).intValue();
                } catch (Exception e) {
                    Logger.m1420e(TAG, "Can not load resource: jpush_notification_icon");
                }
                if (id > 0) {
                    return id;
                }
                return 17301618;
            case 0:
                return 17301647;
            case 1:
                return 17301586;
            case 2:
                return 17301618;
            case 3:
                return 17301567;
            default:
                return 17301586;
        }
    }

    public static HashMap<String, Integer> getResourceId(String resType, String[] fieldNames) {
        if (StringUtils.isEmpty(resType) || fieldNames == null || fieldNames.length == 0) {
            throw new NullPointerException("parameter resType or fieldNames error.");
        }
        HashMap<String, Integer> resIds = new HashMap<>();
        try {
            Class<?>[] classInners = Class.forName(JPush.mApplicationContext.getPackageName() + ".R").getDeclaredClasses();
            int length = classInners.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Class<?> c = classInners[i];
                if (c.getName().contains(resType)) {
                    for (String fieldName : fieldNames) {
                        resIds.put(fieldName, Integer.valueOf(c.getDeclaredField(fieldName).getInt(fieldName)));
                    }
                } else {
                    i++;
                }
            }
        } catch (Exception e) {
            Logger.m1433w(TAG, "Failed to get res id for name.", e);
        }
        return resIds;
    }

    public static int getNotifiFlag(int code) {
        switch (code) {
            case 0:
                return 1;
            case 1:
                return 16;
            case 2:
                return 32;
            default:
                return 1;
        }
    }

    public static void methodInvokeNoti(Notification notification, Context context, String title, String content, PendingIntent pintent) {
        try {
            Class.forName("android.app.Notification").getDeclaredMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{context, title, content, pintent});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

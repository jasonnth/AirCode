package p005cn.jpush.android.api;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.os.Build.VERSION;
import android.widget.RemoteViews;
import java.util.Map;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.api.DefaultPushNotificationBuilder */
public class DefaultPushNotificationBuilder implements PushNotificationBuilder {
    private static final String TAG = "DefaultPushNotificationBuilder";
    protected String notificationTitle;

    public String getDeveloperArg0() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void preBuild(String alert, Map<String, String> intentExtras) {
        if (intentExtras.containsKey(JPushInterface.EXTRA_NOTIFICATION_TITLE)) {
            this.notificationTitle = (String) intentExtras.get(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        }
        if (this.notificationTitle == null) {
            this.notificationTitle = JPush.mApplicationName;
        }
    }

    /* access modifiers changed from: 0000 */
    public RemoteViews buildContentView(String alert) {
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void resetNotificationParams(Notification notification) {
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(16)
    public Notification getNotification(Builder builder) {
        return builder.build();
    }

    public Notification buildNotification(String alert, Map<String, String> intentExtras) {
        if (StringUtils.isEmpty(alert)) {
            Logger.m1434ww(TAG, "No notification content to show. Give up.");
            return null;
        }
        preBuild(alert, intentExtras);
        if (VERSION.SDK_INT < 16) {
            Notification notification = new Notification(JPush.mPackageIconId, alert, System.currentTimeMillis());
            resetNotificationParams(notification);
            if (this.notificationTitle == null) {
                this.notificationTitle = JPush.mApplicationName;
            }
            RemoteViews remoteViews = buildContentView(alert);
            if (remoteViews != null) {
                notification.contentView = remoteViews;
                return notification;
            }
            NotificationHelper.methodInvokeNoti(notification, JPush.mApplicationContext, this.notificationTitle, alert, null);
            return notification;
        } else if (JPush.mApplicationContext != null) {
            int iconRes = JPush.mApplicationContext.getResources().getIdentifier(JPushConstants.FIXED_MESSAGE_ICON_NAME, "drawable", JPush.mApplicationContext.getPackageName());
            if (iconRes == 0) {
                if (JPush.mPackageIconId != 0) {
                    iconRes = JPush.mPackageIconId;
                } else {
                    Logger.m1432w(TAG, "Can't find valid icon when build notification. : " + iconRes);
                    return null;
                }
            }
            Builder notificationBuilder = new Builder(JPush.mApplicationContext).setContentTitle(this.notificationTitle).setContentText(alert).setTicker(alert).setSmallIcon(iconRes);
            RemoteViews remoteViews2 = buildContentView(alert);
            if (remoteViews2 != null) {
                notificationBuilder.setContent(remoteViews2);
            } else {
                Logger.m1424i(TAG, " Use default notification view! ");
            }
            return getNotification(notificationBuilder);
        } else {
            Logger.m1432w(TAG, "Can't find valid context when build notification.");
            return null;
        }
    }
}

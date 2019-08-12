package p005cn.jpush.android.api;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.content.Context;
import p005cn.jpush.android.JPush;

/* renamed from: cn.jpush.android.api.BasicPushNotificationBuilder */
public class BasicPushNotificationBuilder extends DefaultPushNotificationBuilder {
    protected static final String PARAM_INTERVAL = "_____";
    public String developerArg0 = "developerArg0";
    protected Context mContext;
    public int notificationDefaults = -1;
    public int notificationFlags = 16;
    public int statusBarDrawable = JPush.mPackageIconId;

    public BasicPushNotificationBuilder(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        this.mContext = context;
    }

    public String getDeveloperArg0() {
        return this.developerArg0;
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(16)
    public Notification getNotification(Builder builder) {
        builder.setDefaults(this.notificationDefaults);
        builder.setSmallIcon(this.statusBarDrawable);
        Notification notification = builder.build();
        notification.flags = this.notificationFlags;
        return notification;
    }

    /* access modifiers changed from: 0000 */
    public void resetNotificationParams(Notification notification) {
        notification.defaults = this.notificationDefaults;
        notification.flags = this.notificationFlags;
        notification.icon = this.statusBarDrawable;
    }

    public String toString() {
        return "basic_____" + toPreferenceString();
    }

    /* access modifiers changed from: 0000 */
    public String toPreferenceString() {
        return this.notificationDefaults + PARAM_INTERVAL + this.notificationFlags + PARAM_INTERVAL + this.statusBarDrawable + PARAM_INTERVAL + this.developerArg0;
    }

    /* access modifiers changed from: 0000 */
    public void fromPreferenceParams(String[] params) throws NumberFormatException {
        this.notificationDefaults = Integer.parseInt(params[1]);
        this.notificationFlags = Integer.parseInt(params[2]);
        this.statusBarDrawable = Integer.parseInt(params[3]);
        this.developerArg0 = params[4];
    }

    static PushNotificationBuilder parseFromPreference(String preferenceBuilderString) {
        BasicPushNotificationBuilder builder;
        String[] params = preferenceBuilderString.split(PARAM_INTERVAL);
        String type = params[0];
        if ("basic".equals(type)) {
            builder = new BasicPushNotificationBuilder(JPush.mApplicationContext);
        } else if ("custom".equals(type)) {
            builder = new CustomPushNotificationBuilder(JPush.mApplicationContext);
        } else {
            builder = new BasicPushNotificationBuilder(JPush.mApplicationContext);
        }
        builder.fromPreferenceParams(params);
        return builder;
    }
}

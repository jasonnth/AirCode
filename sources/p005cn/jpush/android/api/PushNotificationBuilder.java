package p005cn.jpush.android.api;

import android.app.Notification;
import java.util.Map;

/* renamed from: cn.jpush.android.api.PushNotificationBuilder */
public interface PushNotificationBuilder {
    Notification buildNotification(String str, Map<String, String> map);

    String getDeveloperArg0();
}

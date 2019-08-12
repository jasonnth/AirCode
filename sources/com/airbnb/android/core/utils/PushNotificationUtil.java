package com.airbnb.android.core.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.airbnb.android.core.PushNotificationType;
import com.airbnb.android.core.localpushnotifications.LocalPushAnalytics;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import com.airbnb.android.core.requests.PushNotificationConversionRequest;

public final class PushNotificationUtil {
    private PushNotificationUtil() {
    }

    public static void dismissMessageThreadNotification(Context context, long threadId) {
        dismissPushNotification(context, PushNotificationType.MessageWithTextOnly, threadId);
        dismissPushNotification(context, PushNotificationType.MessageWithImageAttachment, threadId);
    }

    private static void dismissPushNotification(Context context, PushNotificationType type, long serverPushId) {
        ((NotificationManager) context.getSystemService("notification")).cancel(getClientTag(type), getClientPushId(type, serverPushId));
    }

    public static int getClientPushId(PushNotificationType typeEnum, long serverNotificationId) {
        if (serverNotificationId < -2147483648L || serverNotificationId > 2147483647L) {
            return typeEnum.ordinal();
        }
        return (int) serverNotificationId;
    }

    public static void ackPushNotificationOpened(Intent intent) {
        String pushNotificationId = intent.getStringExtra(PushNotificationConversionRequest.PUSH_NOTIFICATION_ID_KEY);
        String secret = intent.getStringExtra("secret");
        String pushType = intent.getStringExtra(PushNotificationConversionRequest.PUSH_TYPE);
        if (!TextUtils.isEmpty(pushNotificationId) && !TextUtils.isEmpty(secret)) {
            PushNotificationConversionRequest.sendPushNotificationOpenedEvent(pushNotificationId, secret, pushType);
        } else if (intent.getSerializableExtra(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE) != null) {
            LocalPushAnalytics.trackLocalPushOpen(intent);
        }
    }

    public static String getClientTag(PushNotificationType type) {
        switch (type) {
            case ReservationGuestAccepted:
            case ReservationHostAccepted:
            case ReservationGuestCancelled:
            case ReservationGuestDeclined:
            case ReservationGuestExpired:
            case ReservationHostCancelled:
            case ReservationHostRequest:
                return "reservation";
            case Unknown:
                return "";
            default:
                return type.type;
        }
    }
}

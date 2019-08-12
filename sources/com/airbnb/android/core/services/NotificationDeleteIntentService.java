package com.airbnb.android.core.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.PushAnalytics;
import com.airbnb.android.core.requests.PushNotificationConversionRequest;

public class NotificationDeleteIntentService extends IntentService {
    private static final String TAG = NotificationDeleteIntentService.class.getSimpleName();

    public static Intent intentForNotification(Context context, String notificationType, String pushId) {
        Intent intent = new Intent(context, NotificationDeleteIntentService.class);
        intent.putExtra(PushIntentServiceConstants.EXTRA_PUSH_TYPE, notificationType);
        intent.putExtra(PushNotificationConversionRequest.PUSH_NOTIFICATION_ID_KEY, pushId);
        return intent;
    }

    public NotificationDeleteIntentService() {
        super(TAG);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent == null) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException(TAG + " called with null intent"));
        } else {
            PushAnalytics.trackOperation(intent.getStringExtra(PushIntentServiceConstants.EXTRA_PUSH_TYPE), intent.getStringExtra(PushNotificationConversionRequest.PUSH_NOTIFICATION_ID_KEY), "push_dismissed");
        }
    }
}

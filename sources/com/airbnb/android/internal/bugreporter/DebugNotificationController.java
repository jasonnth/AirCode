package com.airbnb.android.internal.bugreporter;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.p000v4.app.NotificationCompat.Builder;
import com.airbnb.android.core.AppForegroundDetector;
import com.airbnb.android.core.AppForegroundDetector.AppForegroundListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.PostApplicationCreatedInitializer;
import com.airbnb.android.core.intents.DebugMenuIntents;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.internal.C6574R;

public class DebugNotificationController implements PostApplicationCreatedInitializer {
    private static final String TAG = "DebugNotificationController";
    private final Context context;
    private final AppForegroundDetector foregroundDetector;

    public DebugNotificationController(Context context2, AppForegroundDetector foregroundDetector2) {
        this.context = context2;
        this.foregroundDetector = foregroundDetector2;
    }

    public void initialize() {
        if (InternalBugReportFragment.enabled()) {
            this.foregroundDetector.registerAppForegroundListener(new AppForegroundListener() {
                public void onAppForegrounded(Activity entryActivity) {
                    DebugNotificationController.this.showNotification();
                }

                public void onAppBackgrounded() {
                    DebugNotificationController.this.hideNotification();
                }
            });
            if (this.foregroundDetector.isAppInForeground()) {
                showNotification();
            }
        }
    }

    /* access modifiers changed from: private */
    public void showNotification() {
        PendingIntent reportPendingIntent = PendingIntent.getActivity(this.context, 0, InternalBugReportFragment.createIntent(this.context), 0);
        getNotificationManager().notify(TAG, 0, new Builder(this.context).setContentTitle(this.context.getString(C6574R.string.internal_bug_report_notification_title, new Object[]{BuildHelper.buildType()})).setContentText(this.context.getString(C6574R.string.internal_bug_report_notification_text)).setSmallIcon(C0716R.C0717drawable.ic_stat_notify).setOngoing(true).setContentIntent(reportPendingIntent).addAction(0, this.context.getString(C6574R.string.internal_bug_report_notification_action_internal_settings), PendingIntent.getActivity(this.context, 0, DebugMenuIntents.create(this.context), 0)).build());
    }

    /* access modifiers changed from: private */
    public void hideNotification() {
        getNotificationManager().cancel(TAG, 0);
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) this.context.getSystemService("notification");
    }
}

package com.airbnb.android.aireventlogger;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.p000v4.app.NotificationCompat;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

final class AlarmManagerUtils {
    private static final int REQUEST_CODE_REGULAR = 1;

    AlarmManagerUtils() {
    }

    @TargetApi(19)
    static void scheduleUploadJob(Context context, long alarmTimeStartMills, long alarmWindowMillis) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent pendingIntent = createPendingIntent(context, true);
        if (VERSION.SDK_INT >= 19) {
            alarmManager.setWindow(0, System.currentTimeMillis() + alarmTimeStartMills, alarmWindowMillis, pendingIntent);
        } else {
            alarmManager.set(0, System.currentTimeMillis() + alarmTimeStartMills, pendingIntent);
        }
    }

    static void cancelNextUploadJob(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent intent = createPendingIntent(context, false);
        if (intent != null) {
            alarmManager.cancel(intent);
        }
    }

    private static PendingIntent createPendingIntent(Context context, boolean createIntent) {
        return PendingIntent.getService(context, 1, AirEventUploadService.createIntent(context), createIntent ? 268435456 : PKIFailureInfo.duplicateCertReq);
    }
}

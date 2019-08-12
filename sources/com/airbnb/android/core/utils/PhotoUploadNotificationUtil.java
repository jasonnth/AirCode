package com.airbnb.android.core.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.p000v4.app.NotificationCompat.Builder;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.requests.photos.PhotoUpload;

public class PhotoUploadNotificationUtil {
    private static final int NOTIFICATION_IMAGE_SIZE = 256;

    public static Notification forUploading(Context context, String path) {
        Builder notifBuilder = new Builder(context).setContentTitle(context.getString(C0716R.string.ml_uploading_photo)).setContentText(context.getString(C0716R.string.ml_upload_in_progress)).setSmallIcon(C0716R.C0717drawable.ic_stat_notify).setProgress(0, 0, true).setOngoing(true);
        notifBuilder.setContentIntent(PendingIntent.getActivity(context, 0, new Intent(), 134217728));
        tryAddThumbnail(notifBuilder, path);
        return notifBuilder.build();
    }

    public static Notification forFailedUpload(Context context, PhotoUpload photoUpload, int failedCount, AirRequestNetworkException exception, PendingIntent retryIntent) {
        Builder notifBuilder = new Builder(context).setContentTitle(context.getResources().getQuantityString(C0716R.plurals.failed_to_upload_x_photos, failedCount, new Object[]{Integer.valueOf(failedCount)})).setContentText(NetworkUtil.getErrorMessage(context, exception, C0716R.string.manage_listing_photo_upload_failed_notification_description)).setSmallIcon(C0716R.C0717drawable.ic_stat_notify).setContentIntent(PendingIntent.getActivity(context, 0, photoUpload.notificationIntent(), 134217728)).setAutoCancel(true).addAction(0, context.getString(C0716R.string.manage_listing_photo_upload_retry), retryIntent);
        ensureNoticationPops(notifBuilder);
        if (failedCount == 1) {
            tryAddThumbnail(notifBuilder, photoUpload.path());
        } else {
            notifBuilder.setLargeIcon(null);
        }
        return notifBuilder.build();
    }

    private static void ensureNoticationPops(Builder notifBuilder) {
        notifBuilder.setPriority(2).setDefaults(2);
    }

    private static void tryAddThumbnail(Builder notifBuilder, String path) {
        Bitmap scaledBitmap = ImageUtils.scaleBitmap(path, 256, 256);
        if (scaledBitmap != null) {
            notifBuilder.setLargeIcon(scaledBitmap);
        }
    }
}

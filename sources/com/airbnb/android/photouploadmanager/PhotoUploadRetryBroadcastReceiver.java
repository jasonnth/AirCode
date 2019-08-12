package com.airbnb.android.photouploadmanager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.requests.photos.PhotoUpload;
import com.airbnb.android.core.utils.Check;

public class PhotoUploadRetryBroadcastReceiver extends BroadcastReceiver {
    private static final String ACTION_NAME = "retry_photo_upload";
    private static final String INTENT_EXTRA_PHOTO_UPLOAD = "photo_upload_target";
    PhotoUploadManager photoUploadManager;

    static PendingIntent createPendingIntent(Context context, PhotoUpload photoUpload) {
        Intent intent = new Intent(context, PhotoUploadRetryBroadcastReceiver.class);
        intent.setAction(ACTION_NAME);
        intent.putExtra(INTENT_EXTRA_PHOTO_UPLOAD, photoUpload);
        return PendingIntent.getBroadcast(context, 0, intent, 134217728);
    }

    public void onReceive(Context context, Intent intent) {
        Check.state(ACTION_NAME.equals(intent.getAction()));
        ((PhotoUploadManagerGraph) CoreApplication.instance(context).component()).inject(this);
        this.photoUploadManager.retryAllFailedUploadsForPhotoUploadTarget((PhotoUpload) intent.getExtras().getParcelable(INTENT_EXTRA_PHOTO_UPLOAD));
    }
}

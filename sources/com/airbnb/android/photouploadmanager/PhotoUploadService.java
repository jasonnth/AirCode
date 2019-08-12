package com.airbnb.android.photouploadmanager;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.requests.photos.PhotoUploadRequest;
import com.airbnb.android.core.responses.PhotoUploadResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.PhotoUploadNotificationUtil;
import p032rx.Observer;

public class PhotoUploadService extends IntentService {
    private static final int UPLOAD_NOTIFICATION_ID = 43;
    private final Object lock = new Object();
    PhotoUploadManager photoUploadManager;
    private final NonResubscribableRequestListener<PhotoUploadResponse> uploadListener = new C0699RL().onResponse(PhotoUploadService$$Lambda$1.lambdaFactory$(this)).onError(PhotoUploadService$$Lambda$4.lambdaFactory$(this)).onComplete(PhotoUploadService$$Lambda$5.lambdaFactory$(this)).buildWithoutResubscription();

    static void startService(Context context) {
        context.startService(new Intent(context, PhotoUploadService.class));
    }

    public PhotoUploadService() {
        super("PhotoUploadService");
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        ((PhotoUploadManagerGraph) CoreApplication.instance(this).component()).inject(this);
        while (this.photoUploadManager.moveNext()) {
            PhotoUploadTransaction photoUpload = this.photoUploadManager.getCurrent();
            startForeground(43, PhotoUploadNotificationUtil.forUploading(this, photoUpload.getPath()));
            uploadImage(photoUpload);
            waitForUploadComplete();
        }
        stopForeground(true);
    }

    private void uploadImage(PhotoUploadTransaction photoUpload) {
        PhotoUploadRequest.createRequest(photoUpload.offlineId, photoUpload.getPhotoUpload()).withListener((Observer) this.uploadListener).execute(NetworkUtil.singleFireExecutor());
    }

    private void waitForUploadComplete() {
        try {
            synchronized (this.lock) {
                this.lock.wait();
            }
        } catch (InterruptedException e) {
        }
    }

    /* access modifiers changed from: private */
    public void notifyUploadComplete() {
        synchronized (this.lock) {
            this.lock.notify();
        }
    }
}

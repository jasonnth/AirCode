package com.airbnb.android.photouploadmanager;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.requests.photos.PhotoUpload;
import com.airbnb.android.core.requests.photos.PhotoUploadRequest;
import com.airbnb.android.core.requests.photos.PhotoUploadTarget;
import com.airbnb.android.core.responses.PhotoUploadResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.PhotoUploadNotificationUtil;
import com.airbnb.android.photouploadmanager.PhotoUploadTransaction.State;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PhotoUploadManager {
    private static final String TAG = "PhotoUploadManager";
    private final Context context;
    private PhotoUploadTransaction currentItem;
    private final List<PhotoUploadTransaction> failedUploads = new ArrayList();
    private final PhotoUploadListenerManager listenerManager = new PhotoUploadListenerManager();
    private long nextOfflineId = Long.MIN_VALUE;
    private final Queue<PhotoUploadTransaction> pendingUploadQueue = new LinkedList();

    public PhotoUploadManager(Context context2) {
        this.context = context2;
    }

    public void addListenerForPhotoUploadTarget(long galleryId, PhotoUploadTarget target, PhotoUploadListener listener) {
        this.listenerManager.addListener(galleryId, target, listener);
    }

    public void removeListenerForPhotoUploadTarget(long galleryId, PhotoUploadTarget target, PhotoUploadListener listener) {
        this.listenerManager.removeListener(galleryId, target, listener);
    }

    public synchronized long uploadImage(PhotoUpload photoUpload) {
        long offlineId;
        offlineId = getNextOfflineId();
        enqueuePhotoAndEnsureUploading(new PhotoUploadTransaction(offlineId, photoUpload));
        return offlineId;
    }

    public synchronized ImmutableList<PhotoUploadTransaction> getOrderedOutgoingItems(long galleryId, PhotoUploadTarget target) {
        return FluentIterable.from((Iterable<E>) this.failedUploads).append((E[]) new PhotoUploadTransaction[]{this.currentItem}).append((Iterable<? extends E>) this.pendingUploadQueue).filter(PhotoUploadManager$$Lambda$1.lambdaFactory$(galleryId, target)).toList();
    }

    static /* synthetic */ boolean lambda$getOrderedOutgoingItems$0(long galleryId, PhotoUploadTarget target, PhotoUploadTransaction photoUpload) {
        return photoUpload != null && photoUpload.isSameUniqueGalleryTarget(galleryId, target);
    }

    public synchronized void cancelFailedPhotoUpload(long uploadRequestId, PhotoUploadTarget target) {
        Long offlineId = getFailedUploadOfflineId(uploadRequestId, target);
        if (offlineId != null) {
            cancelFailedPhotoUpload(offlineId.longValue());
        }
    }

    public synchronized void cancelFailedPhotoUpload(long offlineId) {
        PhotoUploadTransaction uploadToRemove = removeFailedUpload(offlineId);
        if (uploadToRemove != null) {
            deleteFile(uploadToRemove.getPath());
        }
    }

    public synchronized void retryFailedUpload(long uploadRequestId, PhotoUploadTarget target) {
        Long offlineId = getFailedUploadOfflineId(uploadRequestId, target);
        if (offlineId != null) {
            retryFailedUpload(offlineId.longValue());
        }
    }

    public synchronized void retryFailedUpload(long offlineId) {
        PhotoUploadTransaction uploadToRemove = removeFailedUpload(offlineId);
        if (uploadToRemove != null) {
            enqueuePhotoAndEnsureUploading(uploadToRemove);
        }
    }

    public synchronized void retryAllFailedUploadsForPhotoUploadTarget(PhotoUpload photoUpload) {
        getNotificationManager(this.context).cancel(PhotoUploadUtils.getUniqueGalleryTargetId(photoUpload), 0);
        ImmutableList<PhotoUploadTransaction> uploadsForPhotoUploadTarget = FluentIterable.from((Iterable<E>) this.failedUploads).filter(PhotoUploadManager$$Lambda$2.lambdaFactory$(photoUpload)).toList();
        this.failedUploads.removeAll(uploadsForPhotoUploadTarget);
        UnmodifiableIterator it = uploadsForPhotoUploadTarget.iterator();
        while (it.hasNext()) {
            PhotoUploadTransaction photoUploadTransaction = (PhotoUploadTransaction) it.next();
            photoUploadTransaction.setState(State.Pending);
            this.pendingUploadQueue.add(photoUploadTransaction);
        }
        this.listenerManager.notifyAllChanged(photoUpload.galleryId(), photoUpload.uploadTarget());
        ensureUploadServiceActive();
    }

    static /* synthetic */ boolean lambda$retryAllFailedUploadsForPhotoUploadTarget$1(PhotoUpload photoUpload, PhotoUploadTransaction upload) {
        return upload != null && upload.isSameUniqueGalleryTarget(photoUpload.galleryId(), photoUpload.uploadTarget());
    }

    /* access modifiers changed from: 0000 */
    public synchronized boolean moveNext() {
        if (this.currentItem != null && this.currentItem.getState() == State.Failed) {
            this.failedUploads.add(this.currentItem);
        }
        if (!this.pendingUploadQueue.isEmpty()) {
            this.currentItem = (PhotoUploadTransaction) this.pendingUploadQueue.remove();
        } else {
            this.currentItem = null;
        }
        return this.currentItem != null;
    }

    /* access modifiers changed from: 0000 */
    public synchronized PhotoUploadTransaction getCurrent() {
        return this.currentItem;
    }

    public Queue<PhotoUploadTransaction> getPendingUploadQueue() {
        return this.pendingUploadQueue;
    }

    /* access modifiers changed from: 0000 */
    public void setPhotoUploadSuccessful(PhotoUploadResponse response) {
        PhotoUploadRequest request = (PhotoUploadRequest) response.metadata.request();
        setCurrentPhotoUploadState(request.offlineId, State.Succeeded);
        if (request.photoUpload.shouldDeleteFileOnComplete()) {
            deleteFile(request.photoUpload.path());
        }
        this.listenerManager.notifySuccess(request.offlineId, request.photoUpload, response);
    }

    /* access modifiers changed from: 0000 */
    public void setPhotoUploadFailed(PhotoUploadRequest request, AirRequestNetworkException exception) {
        setCurrentPhotoUploadState(request.offlineId, State.Failed);
        PhotoUpload photoUpload = request.photoUpload;
        getNotificationManager(this.context).notify(PhotoUploadUtils.getUniqueGalleryTargetId(photoUpload), 0, PhotoUploadNotificationUtil.forFailedUpload(this.context, photoUpload, FluentIterable.from((Iterable<E>) this.failedUploads).filter(PhotoUploadManager$$Lambda$3.lambdaFactory$(photoUpload)).size() + 1, exception, PhotoUploadRetryBroadcastReceiver.createPendingIntent(this.context, photoUpload)));
        this.listenerManager.notifyFailure(request.offlineId, photoUpload);
    }

    static /* synthetic */ boolean lambda$setPhotoUploadFailed$2(PhotoUpload photoUpload, PhotoUploadTransaction upload) {
        return upload != null && upload.isSameUniqueGalleryTarget(photoUpload.galleryId(), photoUpload.uploadTarget());
    }

    private void setPhotoUploadPending(PhotoUploadTransaction item) {
        item.setState(State.Pending);
        this.listenerManager.notifyPending(item.offlineId, item.getPhotoUpload());
    }

    private synchronized void enqueuePhotoAndEnsureUploading(PhotoUploadTransaction item) {
        this.pendingUploadQueue.add(item);
        setPhotoUploadPending(item);
        ensureUploadServiceActive();
    }

    private synchronized void ensureUploadServiceActive() {
        if (!(this.currentItem != null)) {
            PhotoUploadService.startService(this.context);
        }
    }

    static /* synthetic */ boolean lambda$removeFailedUpload$3(long offlineId, PhotoUploadTransaction upload) {
        return upload != null && upload.offlineId == offlineId;
    }

    private synchronized PhotoUploadTransaction removeFailedUpload(long offlineId) {
        PhotoUploadTransaction photoUploadTransaction;
        photoUploadTransaction = (PhotoUploadTransaction) FluentIterable.from((Iterable<E>) this.failedUploads).firstMatch(PhotoUploadManager$$Lambda$4.lambdaFactory$(offlineId)).orNull();
        if (photoUploadTransaction != null) {
            PhotoUpload photoUpload = photoUploadTransaction.getPhotoUpload();
            this.failedUploads.remove(photoUploadTransaction);
            if (!FluentIterable.from((Iterable<E>) this.failedUploads).anyMatch(PhotoUploadManager$$Lambda$5.lambdaFactory$(photoUpload))) {
                getNotificationManager(this.context).cancel(PhotoUploadUtils.getUniqueGalleryTargetId(photoUpload), 0);
            }
            this.listenerManager.notifyRemoved(offlineId, photoUpload);
        }
        return photoUploadTransaction;
    }

    static /* synthetic */ boolean lambda$removeFailedUpload$4(PhotoUpload photoUpload, PhotoUploadTransaction upload) {
        return upload != null && upload.isSameUniqueGalleryTarget(photoUpload.galleryId(), photoUpload.uploadTarget());
    }

    private synchronized void setCurrentPhotoUploadState(long offlineId, State state) {
        Check.notNull(this.currentItem, "No current item to set the state on");
        Check.state(this.currentItem.offlineId == offlineId, "Attempting to set state on non-current item");
        this.currentItem.setState(state);
    }

    private synchronized long getNextOfflineId() {
        long offlineId;
        offlineId = this.nextOfflineId;
        this.nextOfflineId++;
        Check.state(this.nextOfflineId < 0, "Out of bounds of offline IDs, also probably heat death of the universe");
        return offlineId;
    }

    private Long getFailedUploadOfflineId(long uploadRequestId, PhotoUploadTarget target) {
        return (Long) FluentIterable.from((Iterable<E>) this.failedUploads).firstMatch(PhotoUploadManager$$Lambda$6.lambdaFactory$(uploadRequestId, target)).transform(PhotoUploadManager$$Lambda$7.lambdaFactory$()).orNull();
    }

    static /* synthetic */ boolean lambda$getFailedUploadOfflineId$5(long uploadRequestId, PhotoUploadTarget target, PhotoUploadTransaction upload) {
        return upload.getUploadRequestId() == uploadRequestId && upload.getPhotoUploadTarget() == target;
    }

    private static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists() && !file.delete()) {
            Log.d(TAG, "Attempt to delete file failed");
        }
    }

    private static NotificationManager getNotificationManager(Context context2) {
        return (NotificationManager) context2.getSystemService("notification");
    }
}

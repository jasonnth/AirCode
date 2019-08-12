package com.airbnb.android.photouploadmanager;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.android.core.requests.photos.PhotoUpload;
import com.airbnb.android.core.requests.photos.PhotoUploadTarget;
import com.airbnb.android.core.responses.PhotoUploadResponse;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import p032rx.functions.Action1;

public class PhotoUploadListenerManager {
    private final Map<String, List<PhotoUploadListener>> listeners = Maps.newHashMap();
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    /* access modifiers changed from: 0000 */
    public synchronized void addListener(long galleryId, PhotoUploadTarget target, PhotoUploadListener listener) {
        String identifier = PhotoUploadUtils.getUniqueGalleryTargetId(galleryId, target);
        if (!this.listeners.containsKey(identifier)) {
            this.listeners.put(identifier, Lists.newCopyOnWriteArrayList());
        }
        ((List) this.listeners.get(identifier)).add(listener);
    }

    /* access modifiers changed from: 0000 */
    public synchronized void removeListener(long galleryId, PhotoUploadTarget target, PhotoUploadListener listener) {
        String identifier = PhotoUploadUtils.getUniqueGalleryTargetId(galleryId, target);
        if (this.listeners.containsKey(identifier)) {
            List<PhotoUploadListener> listingListeners = (List) this.listeners.get(identifier);
            listingListeners.remove(listener);
            if (listingListeners.isEmpty()) {
                this.listeners.remove(identifier);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void notifyPending(long offlineId, PhotoUpload photoUpload) {
        notifyListeners(PhotoUploadUtils.getUniqueGalleryTargetId(photoUpload), PhotoUploadListenerManager$$Lambda$1.lambdaFactory$(offlineId, photoUpload));
    }

    /* access modifiers changed from: 0000 */
    public void notifySuccess(long offlineId, PhotoUpload photoUpload, PhotoUploadResponse response) {
        notifyListeners(PhotoUploadUtils.getUniqueGalleryTargetId(photoUpload), PhotoUploadListenerManager$$Lambda$2.lambdaFactory$(offlineId, photoUpload, response));
    }

    /* access modifiers changed from: 0000 */
    public void notifyFailure(long offlineId, PhotoUpload photoUpload) {
        notifyListeners(PhotoUploadUtils.getUniqueGalleryTargetId(photoUpload), PhotoUploadListenerManager$$Lambda$3.lambdaFactory$(offlineId, photoUpload));
    }

    /* access modifiers changed from: 0000 */
    public void notifyRemoved(long offlineId, PhotoUpload photoUpload) {
        notifyListeners(PhotoUploadUtils.getUniqueGalleryTargetId(photoUpload), PhotoUploadListenerManager$$Lambda$4.lambdaFactory$(offlineId, photoUpload));
    }

    /* access modifiers changed from: 0000 */
    public void notifyAllChanged(long galleryId, PhotoUploadTarget target) {
        notifyListeners(PhotoUploadUtils.getUniqueGalleryTargetId(galleryId, target), PhotoUploadListenerManager$$Lambda$5.lambdaFactory$(galleryId));
    }

    private void notifyListeners(String identifier, Action1<PhotoUploadListener> runnable) {
        this.mainThreadHandler.post(PhotoUploadListenerManager$$Lambda$6.lambdaFactory$(this, identifier, runnable));
    }

    /* access modifiers changed from: private */
    public synchronized void notifyTargetListenersOnMainThread(String identifier, Action1<PhotoUploadListener> runnable) {
        if (this.listeners.containsKey(identifier)) {
            for (PhotoUploadListener listener : (List) this.listeners.get(identifier)) {
                runnable.call(listener);
            }
        }
    }
}

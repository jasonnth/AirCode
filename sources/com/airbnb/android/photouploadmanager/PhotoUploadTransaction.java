package com.airbnb.android.photouploadmanager;

import com.airbnb.android.core.requests.photos.PhotoUpload;
import com.airbnb.android.core.requests.photos.PhotoUploadTarget;

public class PhotoUploadTransaction {
    public final long offlineId;
    private final PhotoUpload photoUpload;
    private State state = State.Pending;

    public enum State {
        Pending,
        Succeeded,
        Failed
    }

    public PhotoUploadTransaction(long offlineId2, PhotoUpload photoUpload2) {
        this.offlineId = offlineId2;
        this.photoUpload = photoUpload2;
    }

    public State getState() {
        return this.state;
    }

    /* access modifiers changed from: 0000 */
    public void setState(State state2) {
        this.state = state2;
    }

    public String getPath() {
        return this.photoUpload.path();
    }

    public PhotoUpload getPhotoUpload() {
        return this.photoUpload;
    }

    public long getUploadRequestId() {
        return this.photoUpload.uploadRequestId();
    }

    public PhotoUploadTarget getPhotoUploadTarget() {
        return this.photoUpload.uploadTarget();
    }

    public boolean isSameUniqueGalleryTarget(long galleryId, PhotoUploadTarget target) {
        return this.photoUpload.isSameUniqueGalleryTarget(galleryId, target);
    }
}

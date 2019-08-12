package com.airbnb.android.core.requests.photos;

import android.content.Intent;
import android.os.Parcelable;

public abstract class PhotoUpload implements Parcelable {

    public static abstract class Builder {
        public abstract PhotoUpload build();

        public abstract Builder galleryId(long j);

        public abstract Builder notificationIntent(Intent intent);

        public abstract Builder path(String str);

        public abstract Builder shouldDeleteFileOnComplete(boolean z);

        public abstract Builder uploadRequestId(long j);

        public abstract Builder uploadTarget(PhotoUploadTarget photoUploadTarget);
    }

    public abstract long galleryId();

    public abstract Intent notificationIntent();

    public abstract String path();

    public abstract boolean shouldDeleteFileOnComplete();

    public abstract long uploadRequestId();

    public abstract PhotoUploadTarget uploadTarget();

    public boolean isSameUniqueGalleryTarget(long galleryId, PhotoUploadTarget target) {
        return galleryId() == galleryId && uploadTarget() == target;
    }

    public static Builder builder(long uploadRequestId, String path) {
        return new Builder().uploadRequestId(uploadRequestId).path(path).shouldDeleteFileOnComplete(true);
    }
}

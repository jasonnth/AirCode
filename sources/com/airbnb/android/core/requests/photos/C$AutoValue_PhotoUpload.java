package com.airbnb.android.core.requests.photos;

import android.content.Intent;
import com.airbnb.android.core.enums.HelpCenterArticle;

/* renamed from: com.airbnb.android.core.requests.photos.$AutoValue_PhotoUpload reason: invalid class name */
abstract class C$AutoValue_PhotoUpload extends PhotoUpload {
    private final long galleryId;
    private final Intent notificationIntent;
    private final String path;
    private final boolean shouldDeleteFileOnComplete;
    private final long uploadRequestId;
    private final PhotoUploadTarget uploadTarget;

    /* renamed from: com.airbnb.android.core.requests.photos.$AutoValue_PhotoUpload$Builder */
    static final class Builder extends com.airbnb.android.core.requests.photos.PhotoUpload.Builder {
        private Long galleryId;
        private Intent notificationIntent;
        private String path;
        private Boolean shouldDeleteFileOnComplete;
        private Long uploadRequestId;
        private PhotoUploadTarget uploadTarget;

        Builder() {
        }

        public com.airbnb.android.core.requests.photos.PhotoUpload.Builder galleryId(long galleryId2) {
            this.galleryId = Long.valueOf(galleryId2);
            return this;
        }

        public com.airbnb.android.core.requests.photos.PhotoUpload.Builder uploadRequestId(long uploadRequestId2) {
            this.uploadRequestId = Long.valueOf(uploadRequestId2);
            return this;
        }

        public com.airbnb.android.core.requests.photos.PhotoUpload.Builder uploadTarget(PhotoUploadTarget uploadTarget2) {
            if (uploadTarget2 == null) {
                throw new NullPointerException("Null uploadTarget");
            }
            this.uploadTarget = uploadTarget2;
            return this;
        }

        public com.airbnb.android.core.requests.photos.PhotoUpload.Builder path(String path2) {
            if (path2 == null) {
                throw new NullPointerException("Null path");
            }
            this.path = path2;
            return this;
        }

        public com.airbnb.android.core.requests.photos.PhotoUpload.Builder shouldDeleteFileOnComplete(boolean shouldDeleteFileOnComplete2) {
            this.shouldDeleteFileOnComplete = Boolean.valueOf(shouldDeleteFileOnComplete2);
            return this;
        }

        public com.airbnb.android.core.requests.photos.PhotoUpload.Builder notificationIntent(Intent notificationIntent2) {
            if (notificationIntent2 == null) {
                throw new NullPointerException("Null notificationIntent");
            }
            this.notificationIntent = notificationIntent2;
            return this;
        }

        public PhotoUpload build() {
            String missing = "";
            if (this.galleryId == null) {
                missing = missing + " galleryId";
            }
            if (this.uploadRequestId == null) {
                missing = missing + " uploadRequestId";
            }
            if (this.uploadTarget == null) {
                missing = missing + " uploadTarget";
            }
            if (this.path == null) {
                missing = missing + " path";
            }
            if (this.shouldDeleteFileOnComplete == null) {
                missing = missing + " shouldDeleteFileOnComplete";
            }
            if (this.notificationIntent == null) {
                missing = missing + " notificationIntent";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PhotoUpload(this.galleryId.longValue(), this.uploadRequestId.longValue(), this.uploadTarget, this.path, this.shouldDeleteFileOnComplete.booleanValue(), this.notificationIntent);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_PhotoUpload(long galleryId2, long uploadRequestId2, PhotoUploadTarget uploadTarget2, String path2, boolean shouldDeleteFileOnComplete2, Intent notificationIntent2) {
        this.galleryId = galleryId2;
        this.uploadRequestId = uploadRequestId2;
        if (uploadTarget2 == null) {
            throw new NullPointerException("Null uploadTarget");
        }
        this.uploadTarget = uploadTarget2;
        if (path2 == null) {
            throw new NullPointerException("Null path");
        }
        this.path = path2;
        this.shouldDeleteFileOnComplete = shouldDeleteFileOnComplete2;
        if (notificationIntent2 == null) {
            throw new NullPointerException("Null notificationIntent");
        }
        this.notificationIntent = notificationIntent2;
    }

    public long galleryId() {
        return this.galleryId;
    }

    public long uploadRequestId() {
        return this.uploadRequestId;
    }

    public PhotoUploadTarget uploadTarget() {
        return this.uploadTarget;
    }

    public String path() {
        return this.path;
    }

    public boolean shouldDeleteFileOnComplete() {
        return this.shouldDeleteFileOnComplete;
    }

    public Intent notificationIntent() {
        return this.notificationIntent;
    }

    public String toString() {
        return "PhotoUpload{galleryId=" + this.galleryId + ", uploadRequestId=" + this.uploadRequestId + ", uploadTarget=" + this.uploadTarget + ", path=" + this.path + ", shouldDeleteFileOnComplete=" + this.shouldDeleteFileOnComplete + ", notificationIntent=" + this.notificationIntent + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PhotoUpload)) {
            return false;
        }
        PhotoUpload that = (PhotoUpload) o;
        if (this.galleryId != that.galleryId() || this.uploadRequestId != that.uploadRequestId() || !this.uploadTarget.equals(that.uploadTarget()) || !this.path.equals(that.path()) || this.shouldDeleteFileOnComplete != that.shouldDeleteFileOnComplete() || !this.notificationIntent.equals(that.notificationIntent())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((int) (((long) (((int) (((long) (1 * 1000003)) ^ ((this.galleryId >>> 32) ^ this.galleryId))) * 1000003)) ^ ((this.uploadRequestId >>> 32) ^ this.uploadRequestId))) * 1000003) ^ this.uploadTarget.hashCode()) * 1000003) ^ this.path.hashCode()) * 1000003) ^ (this.shouldDeleteFileOnComplete ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE)) * 1000003) ^ this.notificationIntent.hashCode();
    }
}

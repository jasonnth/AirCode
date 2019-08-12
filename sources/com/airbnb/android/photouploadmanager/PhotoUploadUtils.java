package com.airbnb.android.photouploadmanager;

import com.airbnb.android.core.requests.photos.PhotoUpload;
import com.airbnb.android.core.requests.photos.PhotoUploadTarget;

class PhotoUploadUtils {
    PhotoUploadUtils() {
    }

    static String getUniqueGalleryTargetId(long galleryId, PhotoUploadTarget target) {
        return target.name() + galleryId;
    }

    static String getUniqueGalleryTargetId(PhotoUpload photoUpload) {
        return getUniqueGalleryTargetId(photoUpload.galleryId(), photoUpload.uploadTarget());
    }
}

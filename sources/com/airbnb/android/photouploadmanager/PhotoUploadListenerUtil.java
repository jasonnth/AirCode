package com.airbnb.android.photouploadmanager;

import com.airbnb.android.core.requests.photos.PhotoUpload;
import com.airbnb.android.core.responses.PhotoUploadResponse;

public class PhotoUploadListenerUtil {

    public interface CatchAllListener {
        void uploadEvent();
    }

    public interface SuccessListener {
        void uploadSuccess(PhotoUploadResponse photoUploadResponse);
    }

    public static PhotoUploadListener createCatchAllListener(final CatchAllListener listener) {
        return new PhotoUploadListener() {
            public void uploadPending(long offlineId, PhotoUpload photoUpload) {
                listener.uploadEvent();
            }

            public void uploadFailed(long offlineId, PhotoUpload photoUpload) {
                listener.uploadEvent();
            }

            public void uploadSucceded(long offlineId, PhotoUpload photoUpload, PhotoUploadResponse response) {
                listener.uploadEvent();
            }

            public void uploadRemoved(long offlineId, PhotoUpload photoUpload) {
                listener.uploadEvent();
            }

            public void retryAllUploads(long galleryId) {
                listener.uploadEvent();
            }
        };
    }

    public static PhotoUploadListener createSuccessListener(final SuccessListener listener) {
        return new PhotoUploadListener() {
            public void uploadPending(long offlineId, PhotoUpload photoUpload) {
            }

            public void uploadFailed(long offlineId, PhotoUpload photoUpload) {
            }

            public void uploadSucceded(long offlineId, PhotoUpload photoUpload, PhotoUploadResponse response) {
                listener.uploadSuccess(response);
            }

            public void uploadRemoved(long offlineId, PhotoUpload photoUpload) {
            }

            public void retryAllUploads(long galleryId) {
            }
        };
    }
}

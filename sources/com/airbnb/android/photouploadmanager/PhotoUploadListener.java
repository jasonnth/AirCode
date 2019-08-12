package com.airbnb.android.photouploadmanager;

import com.airbnb.android.core.requests.photos.PhotoUpload;
import com.airbnb.android.core.responses.PhotoUploadResponse;

public interface PhotoUploadListener {
    void retryAllUploads(long j);

    void uploadFailed(long j, PhotoUpload photoUpload);

    void uploadPending(long j, PhotoUpload photoUpload);

    void uploadRemoved(long j, PhotoUpload photoUpload);

    void uploadSucceded(long j, PhotoUpload photoUpload, PhotoUploadResponse photoUploadResponse);
}

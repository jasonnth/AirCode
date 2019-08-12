package com.jumio.commons.camera;

import android.hardware.Camera.PreviewCallback;
import com.jumio.commons.camera.CameraManager.PreviewProperties;

public interface ICameraCallback extends PreviewCallback {
    void onCameraAvailable(boolean z);

    void onCameraError(Throwable th);

    void onManualRefocus(int i, int i2);

    void onPreviewAvailable(PreviewProperties previewProperties);

    void onStopPreview();
}

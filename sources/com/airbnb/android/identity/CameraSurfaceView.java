package com.airbnb.android.identity;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.utils.CameraHelper;
import com.airbnb.android.core.utils.ErrorUtils;
import java.io.IOException;
import java.util.List;

public class CameraSurfaceView extends SurfaceView implements Callback {
    private Camera camera;
    private Size previewSize;
    private SurfaceHolder surfaceHolder = getHolder();

    public CameraSurfaceView(Context context, Camera camera2) {
        super(context);
        this.camera = camera2;
        this.surfaceHolder.addCallback(this);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            if (this.camera != null) {
                this.camera.setPreviewDisplay(holder);
                this.camera.startPreview();
            }
        } catch (Exception e) {
            BugsnagWrapper.throwOrNotify(new RuntimeException(e));
            ErrorUtils.showErrorUsingSnackbar((View) this, C6533R.string.account_verification_selfie_camera_preview_error);
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (this.camera != null && this.surfaceHolder.getSurface() != null) {
            try {
                this.camera.stopPreview();
            } catch (Exception e) {
            }
            try {
                Parameters parameters = this.camera.getParameters();
                List<Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
                if (supportedPreviewSizes != null) {
                    this.previewSize = CameraHelper.getOptimalPreviewSize(supportedPreviewSizes, width, height);
                    parameters.setPreviewSize(this.previewSize.width, this.previewSize.height);
                    this.camera.setParameters(parameters);
                    this.camera.startPreview();
                }
            } catch (RuntimeException e2) {
                BugsnagWrapper.throwOrNotify(e2);
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (this.camera != null) {
            try {
                this.camera.stopPreview();
                this.camera.setPreviewDisplay(null);
                this.camera.release();
            } catch (RuntimeException e) {
                BugsnagWrapper.throwOrNotify(e);
            } catch (IOException e2) {
                BugsnagWrapper.throwOrNotify(new RuntimeException("IOException thrown while calling setPreviewDisplay(null)."));
            }
        }
        this.camera = null;
    }
}

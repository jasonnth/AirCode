package com.airbnb.android.core.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.airbnb.android.utils.AndroidVersion;
import com.miteksystems.facialcapture.science.api.params.FacialCaptureApiConstants;
import java.util.List;

public class CameraHelper {
    static final double ASPECT_TOLERANCE = 0.1d;

    @SuppressLint({"NewApi"})
    public static boolean isCameraAvailable(Context context) {
        boolean handlesIntent;
        PackageManager pm = context.getPackageManager();
        if (!pm.queryIntentActivities(new Intent("android.media.action.IMAGE_CAPTURE"), 65536).isEmpty()) {
            handlesIntent = true;
        } else {
            handlesIntent = false;
        }
        if (!AndroidVersion.isAtLeastJellyBeanMR1()) {
            return handlesIntent;
        }
        if (!pm.hasSystemFeature("android.hardware.camera.any") || !handlesIntent) {
            return false;
        }
        return true;
    }

    public static Camera getCameraInstance(Context context) throws Exception {
        if (!isBackCameraAvailable(context)) {
            return null;
        }
        return Camera.open();
    }

    public static boolean isBackCameraAvailable(Context context) {
        Check.notNull(context, "Context can't be null");
        return context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public static boolean cameraSupportsRequiredResolution(Camera camera, int width, int height) {
        Check.notNull(camera, "Camera can't be null");
        Parameters camParams = camera.getParameters();
        if (camParams == null) {
            return false;
        }
        List<Size> previewSizes = camParams.getSupportedPreviewSizes();
        if (previewSizes == null || previewSizes.isEmpty()) {
            return false;
        }
        for (Size previewSize : previewSizes) {
            if (previewSize.width == width && previewSize.height == height) {
                return true;
            }
        }
        return false;
    }

    public static boolean cameraSupportsAutoFocusMode(Context context, Camera camera) {
        Check.notNull(camera, "Camera can't be null");
        Check.notNull(context, "Context can't be null");
        if (context.getPackageManager().hasSystemFeature("android.hardware.camera.autofocus")) {
            return true;
        }
        Parameters camParams = camera.getParameters();
        if (camParams == null) {
            return false;
        }
        List<String> modes = camParams.getSupportedFocusModes();
        if (modes == null || modes.isEmpty()) {
            return false;
        }
        if (modes.contains("continuous-video") || modes.contains("continuous-picture")) {
            return true;
        }
        return false;
    }

    private static int getCameraID(CameraInfo cameraInfo, int cameraDirection) {
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == cameraDirection) {
                return i;
            }
        }
        return -1;
    }

    public static int getFrontCameraID() {
        return getCameraID(new CameraInfo(), 1);
    }

    public static int getBackCameraID() {
        return getCameraID(new CameraInfo(), 0);
    }

    public static int getOrientationOffset(Activity activity, int cameraID) {
        CameraInfo info = new CameraInfo();
        Camera.getCameraInfo(cameraID, info);
        int degrees = 0;
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 0:
                degrees = 0;
                break;
            case 1:
                degrees = 90;
                break;
            case 2:
                degrees = 180;
                break;
            case 3:
                degrees = MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS;
                break;
        }
        if (info.facing == 1) {
            return (360 - ((info.orientation + degrees) % FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT)) % FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT;
        }
        return ((info.orientation - degrees) + FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT) % FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT;
    }

    public static Size getOptimalPreviewSize(List<Size> supportedSizes, int screenWidth, int screenHeight) {
        if (supportedSizes == null) {
            return null;
        }
        double targetRatio = ((double) screenHeight) / ((double) screenWidth);
        Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;
        int targetHeight = screenHeight;
        for (Size size : supportedSizes) {
            if (Math.abs((((double) size.width) / ((double) size.height)) - targetRatio) <= ASPECT_TOLERANCE && ((double) Math.abs(size.height - targetHeight)) < minDiff) {
                optimalSize = size;
                minDiff = (double) Math.abs(size.height - targetHeight);
            }
        }
        if (optimalSize != null) {
            return optimalSize;
        }
        double minDiff2 = Double.MAX_VALUE;
        for (Size size2 : supportedSizes) {
            if (((double) Math.abs(size2.height - targetHeight)) < minDiff2) {
                optimalSize = size2;
                minDiff2 = (double) Math.abs(size2.height - targetHeight);
            }
        }
        return optimalSize;
    }
}

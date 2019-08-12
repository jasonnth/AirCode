package com.airbnb.android.misnap;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import com.airbnb.android.core.utils.CameraHelper;
import com.airbnb.android.core.utils.Check;
import com.miteksystems.misnap.params.MiSnapAPI;
import com.miteksystems.misnap.params.MiSnapApiConstants;
import com.miteksystems.misnap.params.MiSnapIntentCheck;
import org.json.JSONException;
import org.json.JSONObject;

public final class MiSnapUtils {
    private MiSnapUtils() {
    }

    public static boolean setupMiSnapCameraForAutoCaptureIfPossible(Context context, Intent miSnapIntent) throws RuntimeException {
        boolean supportsAcceptableRes;
        boolean canAutoCapture;
        boolean z = true;
        Check.notNull(context, "Context can't be null");
        Check.notNull(miSnapIntent, "Intent can't be null");
        try {
            Camera camera = CameraHelper.getCameraInstance(context);
            if (camera == null) {
                throw new RuntimeException("Camera is not available");
            }
            boolean supportsAutoFocus = CameraHelper.cameraSupportsAutoFocusMode(context, camera);
            if (CameraHelper.cameraSupportsRequiredResolution(camera, 1920, 1080) || CameraHelper.cameraSupportsRequiredResolution(camera, 1280, 720)) {
                supportsAcceptableRes = true;
            } else {
                supportsAcceptableRes = false;
            }
            camera.release();
            if (!supportsAutoFocus || !supportsAcceptableRes) {
                canAutoCapture = false;
            } else {
                canAutoCapture = true;
            }
            if (canAutoCapture) {
                z = false;
            }
            setCaptureMode(miSnapIntent, z);
            return canAutoCapture;
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize MiSnap properly.", e);
        }
    }

    private static void setCaptureMode(Intent intent, boolean isManual) throws JSONException {
        JSONObject param = new JSONObject(intent.getStringExtra(MiSnapAPI.JOB_SETTINGS));
        if (param.has(MiSnapAPI.MiSnapCaptureMode)) {
            param.remove(MiSnapAPI.MiSnapCaptureMode);
        }
        param.put(MiSnapAPI.MiSnapCaptureMode, isManual ? 1 : 2);
        intent.putExtra(MiSnapAPI.JOB_SETTINGS, param.toString());
    }

    public static boolean isBarcodeRequest(Intent intent) {
        try {
            String docType = new JSONObject(getJobSettings(intent)).getString(MiSnapAPI.MiSnapDocumentType);
            if ("PDF417".equals(docType) || MiSnapApiConstants.PARAMETER_DOCTYPE_BARCODES.equals(docType)) {
                return true;
            }
            return false;
        } catch (JSONException e) {
            return false;
        }
    }

    private static String getJobSettings(Intent intent) {
        if (MiSnapIntentCheck.isDangerous(intent)) {
            return "";
        }
        String jobSettings = intent.getStringExtra(MiSnapAPI.JOB_SETTINGS);
        return jobSettings == null ? "" : jobSettings;
    }
}

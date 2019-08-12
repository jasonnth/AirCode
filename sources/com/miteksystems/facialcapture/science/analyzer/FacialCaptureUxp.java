package com.miteksystems.facialcapture.science.analyzer;

import android.content.Context;
import com.miteksystems.facialcapture.science.C4531R;
import com.miteksystems.misnap.utils.Utils;

public class FacialCaptureUxp {
    private Context mAppContext;
    private boolean mFirstUxp = true;
    private boolean mUxpBlinking;
    private boolean mUxpDeviceUpright;
    private boolean mUxpFaceDistanceGood;
    private boolean mUxpFaceFound;

    public FacialCaptureUxp(Context appContext) {
        this.mAppContext = appContext.getApplicationContext();
    }

    public void addPerFrameUxpData(FacialCaptureFrameResult result) {
        int uxpId;
        int i = 0;
        if (this.mFirstUxp) {
            this.mFirstUxp = false;
            Context context = this.mAppContext;
            int i2 = C4531R.string.uxp_facialcapture_device_upright;
            if (result.isDeviceUpright) {
                i = 1;
            }
            Utils.uxpEvent(context, i2, i);
            this.mUxpDeviceUpright = result.isDeviceUpright;
        } else if (addUxpIfChanged(this.mUxpDeviceUpright, result.isDeviceUpright, C4531R.string.uxp_facialcapture_device_upright)) {
            this.mUxpDeviceUpright = result.isDeviceUpright;
        }
        boolean faceWasJustFound = false;
        if (addUxpIfChanged(this.mUxpFaceFound, result.isFaceFound, C4531R.string.uxp_facialcapture_face_found)) {
            this.mUxpFaceFound = result.isFaceFound;
            faceWasJustFound = true;
        }
        if (result.isFaceFound) {
            if (this.mUxpFaceDistanceGood != result.isFaceDistanceGood || faceWasJustFound) {
                this.mUxpFaceDistanceGood = result.isFaceDistanceGood;
                if (result.isFaceTooClose) {
                    uxpId = C4531R.string.uxp_facialcapture_face_too_close;
                } else if (result.isFaceTooFarAway) {
                    uxpId = C4531R.string.uxp_facialcapture_face_too_far_away;
                } else {
                    uxpId = C4531R.string.uxp_facialcapture_face_distance_good;
                }
                Utils.uxpEvent(this.mAppContext, uxpId, result.eyeDistanceFromScreen);
            }
            addUxpIfFailed(result.isQualityGood, C4531R.string.uxp_facialcapture_quality_fail, result.qualityScore);
            addUxpIfFailed(result.isSharpnessGood, C4531R.string.uxp_facialcapture_sharpness_fail, result.sharpnessScore);
            addUxpIfFailed(result.isLightingUniform, C4531R.string.uxp_facialcapture_uniform_lighting_fail, result.uniformLightingScore);
            if (addUxpIfChanged(this.mUxpBlinking, result.isBlinkDetected, C4531R.string.uxp_facialcapture_blink)) {
                this.mUxpBlinking = result.isBlinkDetected;
            }
        }
    }

    public void addFinalFrameUxpData(FacialCaptureFrameResult result) {
        Utils.uxpEvent(this.mAppContext, C4531R.string.uxp_facialcapture_final_eye_distance, result.eyeDistanceFromScreen);
        Utils.uxpEvent(this.mAppContext, C4531R.string.uxp_facialcapture_final_uniform_lighting, result.uniformLightingScore);
        Utils.uxpEvent(this.mAppContext, C4531R.string.uxp_facialcapture_final_global_quality, result.qualityScore);
        Utils.uxpEvent(this.mAppContext, C4531R.string.uxp_facialcapture_final_sharpness, result.sharpnessScore);
    }

    private boolean addUxpIfChanged(boolean oldState, boolean newState, int uxpId) {
        int i = 0;
        if (oldState == newState) {
            return false;
        }
        Context context = this.mAppContext;
        if (newState) {
            i = 1;
        }
        Utils.uxpEvent(context, uxpId, i);
        return true;
    }

    private boolean addUxpIfFailed(boolean valueWasGoodEnough, int uxpId, int measurement) {
        if (valueWasGoodEnough || measurement == 0) {
            return false;
        }
        Utils.uxpEvent(this.mAppContext, uxpId, measurement);
        return true;
    }
}

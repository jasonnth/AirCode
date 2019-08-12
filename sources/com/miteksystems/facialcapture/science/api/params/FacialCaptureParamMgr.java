package com.miteksystems.facialcapture.science.api.params;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class FacialCaptureParamMgr {
    private static final String TAG = FacialCaptureParamMgr.class.getSimpleName();
    private JSONObject mChangedParams = new JSONObject();
    private JSONObject mJobSettings;

    public FacialCaptureParamMgr(JSONObject jobSettings) {
        this.mJobSettings = jobSettings;
    }

    public int getBlinkThreshold() {
        return getParameterValue(FacialCaptureApi.BlinkThreshold, 0, 1000, 400);
    }

    public int getEyeMinDistance() {
        return getParameterValue(FacialCaptureApi.EyeMinDistance, 0, 1000, 200);
    }

    public int getEyeMaxDistance() {
        return getParameterValue(FacialCaptureApi.EyeMaxDistance, 0, 1000, FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT);
    }

    public int getLivenessThreshold() {
        return getParameterValue(FacialCaptureApi.LivenessThreshold, 0, 1000, 500);
    }

    public int getLightingMinThreshold() {
        return getParameterValue(FacialCaptureApi.LightingMinThreshold, 0, 1000, 500);
    }

    public int getSharpnessMinThreshold() {
        return getParameterValue(FacialCaptureApi.SharpnessMinThreshold, 0, 1000, 200);
    }

    public int getCaptureEyesOpen() {
        return getParameterValue(FacialCaptureApi.CaptureEyesOpen, 0, 1, 1);
    }

    public String getChangedParameters() {
        return this.mChangedParams.toString();
    }

    private int getParameterValue(String param, int minValue, int maxValue, int defaultValue) {
        int value = defaultValue;
        if (parameterHasBeenSet(param)) {
            try {
                value = readParameter(param);
            } catch (JSONException e) {
                Log.d(TAG, "Using default value of " + defaultValue + " for parameter " + param);
                addChangedParameter(param, defaultValue);
            }
            int oldValue = value;
            value = cropToRange(value, minValue, maxValue);
            if (oldValue != value) {
                addChangedParameter(param, value);
            }
        }
        return value;
    }

    private boolean parameterHasBeenSet(String param) {
        return this.mJobSettings.has(param);
    }

    private int readParameter(String param) throws JSONException {
        return this.mJobSettings.getInt(param);
    }

    private static final int cropToRange(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    private void addChangedParameter(String param, int value) {
        try {
            this.mChangedParams.put(param, value);
        } catch (JSONException e) {
        }
    }
}

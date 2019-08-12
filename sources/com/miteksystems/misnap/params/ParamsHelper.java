package com.miteksystems.misnap.params;

import android.content.Intent;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class ParamsHelper {
    private static final String TAG = ParamsHelper.class.getSimpleName();

    public static String getJobSettings(Intent miSnapIntent) {
        if (MiSnapIntentCheck.isDangerous(miSnapIntent)) {
            return "";
        }
        String jobSettings = miSnapIntent.getStringExtra(MiSnapAPI.JOB_SETTINGS);
        return jobSettings == null ? "" : jobSettings;
    }

    public static String getDocType(Intent miSnapIntent) {
        try {
            return new JSONObject(getJobSettings(miSnapIntent)).getString(MiSnapAPI.MiSnapDocumentType);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getAppVersion(Intent miSnapIntent) {
        try {
            String version = new JSONObject(getJobSettings(miSnapIntent)).getString(MiSnapAPI.AppVersion);
            if (version.length() > 20) {
                return version.substring(0, 20);
            }
            return version;
        } catch (JSONException e) {
            Log.e(TAG, "AppVersion was not set. " + e.getMessage());
            return "";
        }
    }

    public static boolean isHandledByCardIo(Intent miSnapIntent) {
        return getDocType(miSnapIntent).equals(MiSnapApiConstants.PARAMETER_DOCTYPE_CREDIT_CARD);
    }

    public static boolean isHandledByManatee(Intent miSnapIntent) {
        String docType = getDocType(miSnapIntent);
        return docType.equals("PDF417") || docType.equals(MiSnapApiConstants.PARAMETER_DOCTYPE_BARCODES);
    }

    public static boolean isCheck(Intent miSnapIntent) {
        return isCheckFront(miSnapIntent) || isCheckBack(miSnapIntent);
    }

    public static boolean isCheckFront(Intent miSnapIntent) {
        return isCheckFront(getDocType(miSnapIntent));
    }

    public static boolean isCheckFront(String docType) {
        return docType.equals(MiSnapApiConstants.PARAMETER_DOCTYPE_CHECK_FRONT) || docType.startsWith(MiSnapApiConstants.PARAMETER_DOCTYPE_AUTOMATIC_CHECK_HANDLING);
    }

    public static boolean isCheckBack(Intent miSnapIntent) {
        return isCheckBack(getDocType(miSnapIntent));
    }

    public static boolean isCheckBack(String docType) {
        return docType.equals(MiSnapApiConstants.PARAMETER_DOCTYPE_CHECK_BACK);
    }

    public static boolean isAutoCaptureMode(Intent miSnapIntent) {
        return getCaptureMode(miSnapIntent) != 1;
    }

    public static int getCaptureMode(Intent miSnapIntent) {
        int captureMode = 2;
        try {
            return new JSONObject(getJobSettings(miSnapIntent)).getInt(MiSnapAPI.MiSnapCaptureMode);
        } catch (JSONException e) {
            return captureMode;
        }
    }

    public static boolean areScreenshotsAllowed(Intent miSnapIntent) {
        int allowScreenshots = 0;
        try {
            allowScreenshots = new JSONObject(getJobSettings(miSnapIntent)).getInt(MiSnapAPI.MiSnapAllowScreenshots);
        } catch (JSONException e) {
        }
        if (allowScreenshots == 1) {
            return true;
        }
        return false;
    }

    public static Intent setCaptureMode(Intent miSnapIntent, int parameterCaptureModeManual) {
        JSONObject jobJson = null;
        try {
            JSONObject jobJson2 = new JSONObject(getJobSettings(miSnapIntent));
            try {
                jobJson2.put(MiSnapAPI.MiSnapCaptureMode, String.valueOf(parameterCaptureModeManual));
                jobJson = jobJson2;
            } catch (JSONException e) {
                e = e;
                jobJson = jobJson2;
                e.printStackTrace();
                miSnapIntent.removeExtra(MiSnapAPI.JOB_SETTINGS);
                miSnapIntent.putExtra(MiSnapAPI.JOB_SETTINGS, jobJson.toString());
                return miSnapIntent;
            }
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            miSnapIntent.removeExtra(MiSnapAPI.JOB_SETTINGS);
            miSnapIntent.putExtra(MiSnapAPI.JOB_SETTINGS, jobJson.toString());
            return miSnapIntent;
        }
        miSnapIntent.removeExtra(MiSnapAPI.JOB_SETTINGS);
        miSnapIntent.putExtra(MiSnapAPI.JOB_SETTINGS, jobJson.toString());
        return miSnapIntent;
    }

    public static Intent setTorchStartingState(Intent miSnapIntent, boolean newTorchState) {
        JSONObject jobJson = null;
        try {
            JSONObject jobJson2 = new JSONObject(getJobSettings(miSnapIntent));
            try {
                jobJson2.put(MiSnapAPI.MiSnapTorchMode, newTorchState ? "2" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
                jobJson = jobJson2;
            } catch (JSONException e) {
                e = e;
                jobJson = jobJson2;
                e.printStackTrace();
                miSnapIntent.removeExtra(MiSnapAPI.JOB_SETTINGS);
                miSnapIntent.putExtra(MiSnapAPI.JOB_SETTINGS, jobJson.toString());
                return miSnapIntent;
            }
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            miSnapIntent.removeExtra(MiSnapAPI.JOB_SETTINGS);
            miSnapIntent.putExtra(MiSnapAPI.JOB_SETTINGS, jobJson.toString());
            return miSnapIntent;
        }
        miSnapIntent.removeExtra(MiSnapAPI.JOB_SETTINGS);
        miSnapIntent.putExtra(MiSnapAPI.JOB_SETTINGS, jobJson.toString());
        return miSnapIntent;
    }
}

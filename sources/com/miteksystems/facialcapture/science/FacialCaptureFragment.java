package com.miteksystems.facialcapture.science;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.miteksystems.facialcapture.science.analyzer.FacialCaptureAnalyzer;
import com.miteksystems.facialcapture.science.api.params.FacialCaptureParamMgr;
import com.miteksystems.misnap.MiSnapCameraFragment;
import com.miteksystems.misnap.analyzer.IAnalyzer;
import com.miteksystems.misnap.params.MiSnapAPI;
import com.miteksystems.misnap.params.MiSnapApiConstants;
import com.miteksystems.misnap.params.ParameterManager;
import com.miteksystems.misnap.utils.MibiData;
import com.miteksystems.misnap.utils.UXPManager;
import org.json.JSONObject;

public class FacialCaptureFragment extends MiSnapCameraFragment {
    private static final int BAD_DAON_FRAMES = 6;
    private static final String TAG = FacialCaptureFragment.class.getSimpleName();
    FacialCaptureParamMgr mFacialCaptureParams;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        UXPManager.getInstance();
        try {
            Intent intent = getActivity().getIntent();
            JSONObject jobJson = new JSONObject(intent.getStringExtra(MiSnapAPI.JOB_SETTINGS));
            jobJson.put(MiSnapAPI.MiSnapDocumentType, MiSnapApiConstants.PARAMETER_DOCTYPE_CAMERA_ONLY);
            jobJson.put(MiSnapAPI.MiSnapUseFrontCamera, 1);
            jobJson.put(MiSnapAPI.MiSnapUsePortraitOrientation, 1);
            intent.removeExtra(MiSnapAPI.JOB_SETTINGS);
            intent.putExtra(MiSnapAPI.JOB_SETTINGS, jobJson.toString());
            getActivity().setIntent(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public IAnalyzer createAnalyzer(ParameterManager parameterManager) {
        JSONObject jobSettings;
        try {
            jobSettings = new JSONObject(parameterManager.mMiSnapParameters);
        } catch (Exception e) {
            jobSettings = new JSONObject();
        }
        this.mFacialCaptureParams = new FacialCaptureParamMgr(jobSettings);
        FacialCaptureAnalyzer facialCaptureAnalyzer = new FacialCaptureAnalyzer(getActivity(), parameterManager, this.mFacialCaptureParams);
        facialCaptureAnalyzer.setNumFramesToIgnore(6);
        return facialCaptureAnalyzer;
    }

    /* access modifiers changed from: protected */
    public String buildMibiData(Context context, String resultCode) {
        MibiData mibiBuilder = new MibiData(super.buildMibiData(context, resultCode));
        try {
            mibiBuilder.put("SDKVersion", getString(C4531R.string.sdk_versionName));
            mibiBuilder.appendChangedParams(this.mFacialCaptureParams.getChangedParameters());
        } catch (Exception e) {
            Log.e(TAG, "Unable to add MIBI data");
        }
        return mibiBuilder.getMibiData();
    }
}

package com.miteksystems.misnap;

import android.content.Context;
import android.support.p000v4.app.FragmentActivity;
import android.util.Log;
import com.miteksystems.misnap.analyzer.C4566a;
import com.miteksystems.misnap.analyzer.C4568b;
import com.miteksystems.misnap.analyzer.C4569c;
import com.miteksystems.misnap.analyzer.IAnalyzer;
import com.miteksystems.misnap.analyzer.MiSnapAnalyzer;
import com.miteksystems.misnap.analyzer.NoAnalyzer;
import com.miteksystems.misnap.params.MiSnapApiConstants;
import com.miteksystems.misnap.params.ParameterManager;

public class MiSnapFragment extends MiSnapCameraFragment {
    /* access modifiers changed from: protected */
    public IAnalyzer createAnalyzer(ParameterManager parameterManager) {
        char c;
        if (parameterManager.isTestScienceCaptureMode()) {
            Log.e("Analyzer", "Creating TEST_SCIENCE_CAPTURE_ANALYZER");
            c = 'b';
        } else if (parameterManager.isTestScienceReplayMode()) {
            Log.e("Analyzer", "Creating TEST_SCIENCE_REPLAY_ANALYZER");
            c = 'c';
        } else if (parameterManager.isTestScienceOneDrawableMode()) {
            Log.e("Analyzer", "Creating TEST_SCIENCE_ONE_DRAWABLE_ANALYZER");
            c = 'd';
        } else if (parameterManager.getmJobName().equals(MiSnapApiConstants.PARAMETER_DOCTYPE_CAMERA_ONLY)) {
            c = 0;
        } else {
            Log.e("Analyzer", "Creating MISNAP_ANALYZER");
            c = 1;
        }
        FragmentActivity activity = getActivity();
        switch (c) {
            case 1:
                return new MiSnapAnalyzer(activity, parameterManager, true);
            case 'b':
                return new C4566a(activity, parameterManager);
            case 'c':
                return new C4569c(activity, parameterManager);
            case 'd':
                return new C4568b(activity, parameterManager);
            default:
                return new NoAnalyzer();
        }
    }

    /* access modifiers changed from: protected */
    public String buildMibiData(Context context, String str) {
        return super.buildMibiData(context, str);
    }
}

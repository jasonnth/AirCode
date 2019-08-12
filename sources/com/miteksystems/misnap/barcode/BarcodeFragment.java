package com.miteksystems.misnap.barcode;

import android.content.Context;
import com.miteksystems.misnap.MiSnapCameraFragment;
import com.miteksystems.misnap.analyzer.IAnalyzer;
import com.miteksystems.misnap.barcode.analyzer.BarcodeAnalyzer;
import com.miteksystems.misnap.params.ParameterManager;

public class BarcodeFragment extends MiSnapCameraFragment {
    /* access modifiers changed from: protected */
    public IAnalyzer createAnalyzer(ParameterManager paramMgr) {
        return new BarcodeAnalyzer(getActivity(), paramMgr);
    }

    /* access modifiers changed from: protected */
    public String buildMibiData(Context context, String resultCode) {
        return super.buildMibiData(context, resultCode);
    }
}

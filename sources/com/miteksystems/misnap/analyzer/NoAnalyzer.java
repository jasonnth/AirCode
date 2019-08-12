package com.miteksystems.misnap.analyzer;

import android.util.Log;

public class NoAnalyzer implements IAnalyzer {
    public static final String TAG = NoAnalyzer.class.getSimpleName();

    public boolean init() {
        Log.d(TAG, "Initializing NoAnalyzer");
        return true;
    }

    public void deinit() {
        Log.d(TAG, "Deinit NoAnalyzer");
    }

    public void analyze(IAnalyzeResponse callbackToCall, byte[] imageBytes, int previewWidth, int previewHeight, int previewImageType) {
        if (callbackToCall != null) {
            callbackToCall.onAnalyzeFail(1, null);
        }
    }
}

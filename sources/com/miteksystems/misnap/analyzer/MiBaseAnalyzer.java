package com.miteksystems.misnap.analyzer;

import com.miteksystems.misnap.analyzer.IAnalyzeResponse.ExtraInfo;

public abstract class MiBaseAnalyzer implements IAnalyzer, IAnalyzerPicTaken {
    public void onManualPictureTaken(IAnalyzeResponse callback, byte[] jpegImage) {
        callback.onAnalyzeSuccess(4, new ExtraInfo());
    }
}

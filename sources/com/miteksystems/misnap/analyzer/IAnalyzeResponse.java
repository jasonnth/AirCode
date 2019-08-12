package com.miteksystems.misnap.analyzer;

import java.io.Serializable;

public interface IAnalyzeResponse {
    public static final int ANALYZER_FRAME_IS_GOOD = 0;
    public static final int ANALYZER_FRAME_IS_NOT_GOOD_ENOUGH = 1;
    public static final int ANALYZER_IS_ALREADY_ANALYZING = 3;
    public static final int ANALYZER_IS_DISABLED = 4;
    public static final int ANALYZER_IS_NOT_INITIALIZED = 2;

    public static class ExtraInfo implements Serializable {
        public byte[] yuvImage;

        public ExtraInfo() {
        }

        public ExtraInfo(byte[] yuvImage2) {
            this.yuvImage = yuvImage2;
        }
    }

    void onAnalyzeFail(int i, ExtraInfo extraInfo);

    void onAnalyzeSuccess(int i, ExtraInfo extraInfo);
}

package com.jumio.p311nv.ocr.environment;

import android.content.Context;
import com.jumio.core.environment.Environment;
import java.io.File;

/* renamed from: com.jumio.nv.ocr.environment.NVOcrEnvironment */
public class NVOcrEnvironment extends Environment {
    public static final String BUILD_VERSION = "JMSDK 2.4.0 (0-55)";
    public static final String OCR_VERSION = "1.99.0";

    /* renamed from: a */
    private static boolean f3454a;

    public static String getCardDetectionSettingsPath(Context context) {
        return new File(getDataDirectory(context), "card_detector/card_detection_engine.xml").getAbsolutePath();
    }

    public static synchronized boolean loadLivenessDetectorAndTemplateMatcherLib() {
        synchronized (NVOcrEnvironment.class) {
            if (!f3454a) {
                System.loadLibrary("jniLivenessAndTM");
                f3454a = true;
            }
        }
        return true;
    }
}

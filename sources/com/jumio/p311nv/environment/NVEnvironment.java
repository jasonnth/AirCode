package com.jumio.p311nv.environment;

import android.content.Context;
import com.jumio.core.environment.Environment;
import java.io.File;

/* renamed from: com.jumio.nv.environment.NVEnvironment */
public class NVEnvironment extends Environment {
    public static final String BUILD_VERSION = "JMSDK 2.4.0 (0-55)";
    public static final String CLIENTLIB_VERSION = "1.91.10";
    public static final String OCR_VERSION = "1.99.0";

    /* renamed from: a */
    private static boolean f3362a = false;

    /* renamed from: b */
    private static boolean f3363b = false;

    public static String getLivenessEngineSettingsPath(Context context) {
        extractFile(context, NVEnvironment.class, "livenessDetector/cascade", "4a979bbeae0ef4316e97832f282eef536aeabae0ac80dd2090b08a45f8d237a9", ".pb");
        extractFile(context, NVEnvironment.class, "livenessDetector/smile", "0d4a24f4fdee26d40b318654fc123af50ac2404571b27d22b2a736051ae64f2b", ".dat");
        extractFile(context, NVEnvironment.class, "livenessDetector/sp", "69a694249414e8b5cbda17e65a6d6fc2863a9ec90e1d7567d60228ee7a3b19bf", ".dat");
        return new File(getDataDirectory(context), "livenessDetector").getAbsolutePath();
    }

    public static synchronized boolean loadLivenessDetectorAndTemplateMatcherLib() {
        synchronized (NVEnvironment.class) {
            if (!f3362a) {
                System.loadLibrary("jniLivenessAndTM");
                f3362a = true;
            }
        }
        return true;
    }

    public static synchronized boolean loadBenchmarkLib() {
        synchronized (NVEnvironment.class) {
            if (!f3363b) {
                System.loadLibrary("nativeBenchmark");
                f3363b = true;
            }
        }
        return true;
    }

    public static String getCardDetectionSettingsPath(Context context) {
        extractFile(context, NVEnvironment.class, "card_detector/card_detection_engine-by_jumio", "66715acf23836e35c1ccd770b1ccc4d77c9cbc811d766c1680c04120de1495d4", ".json");
        extractFile(context, NVEnvironment.class, "card_detector/card_detection_engine", "9ef87da4d5737222a284baa8fdbe72c2805334b85a779fb3a5f68cc18420bc3f", ".xml");
        extractFile(context, NVEnvironment.class, "card_detector/fast_findcard_config", "839c941a7446898b3f2e655850e65d60d8e70d72ac394c36b4b4bf9093042682", ".xml");
        return new File(getDataDirectory(context), "card_detector/card_detection_engine.xml").getAbsolutePath();
    }
}

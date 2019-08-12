package com.jumio.p311nv.barcode.environment;

import com.jumio.core.environment.Environment;

/* renamed from: com.jumio.nv.barcode.environment.BarcodeEnvironment */
public class BarcodeEnvironment extends Environment {
    public static final String BUILD_VERSION = "JMSDK 2.4.0 (0-55)";
    public static final String PHOTOPAY_VERSION = "5.4.0";

    /* renamed from: a */
    private static boolean f3328a = false;

    public static synchronized boolean loadPhotopayNativeAPILib() {
        synchronized (BarcodeEnvironment.class) {
            if (!f3328a) {
                System.loadLibrary("photopaynativeapi");
                f3328a = true;
            }
        }
        return true;
    }
}

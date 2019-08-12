package com.paypal.android.sdk.onetouch.core.metadata;

import android.os.Build;
import android.os.Environment;
import java.io.File;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.o */
public final class C4689o {
    /* renamed from: a */
    public static boolean m2475a() {
        if (!(Build.MANUFACTURER.equals("unknown") || Build.MANUFACTURER.equals("Genymotion") || Build.MANUFACTURER.contains("AndyOS"))) {
            if (!(Build.BRAND.equals("generic") || Build.BRAND.equals("generic_x86") || Build.BRAND.equals(InternalLogger.EVENT_PARAM_SDK_ANDROID) || Build.BRAND.equals("AndyOS"))) {
                if (!(Build.DEVICE.equals("AndyOSX") || Build.DEVICE.equals("Droid4X") || Build.DEVICE.equals("generic") || Build.DEVICE.equals("generic_x86") || Build.DEVICE.equals("vbox86p"))) {
                    if (!(Build.HARDWARE.equals("goldfish") || Build.HARDWARE.equals("vbox86") || Build.HARDWARE.equals("andy"))) {
                        if (!(Build.MODEL.equals("sdk") || Build.MODEL.equals("google_sdk") || Build.MODEL.equals("Android SDK built for x86")) && !Build.FINGERPRINT.startsWith("generic")) {
                            if (!(Build.PRODUCT.matches(".*_?sdk_?.*") || Build.PRODUCT.equals("vbox86p") || Build.PRODUCT.equals("Genymotion") || Build.PRODUCT.equals("Driod4X") || Build.PRODUCT.equals("AndyOSX"))) {
                                if (!(new File(new StringBuilder().append(Environment.getExternalStorageDirectory().toString()).append(File.separatorChar).append("windows").append(File.separatorChar).append("BstSharedFolder").toString()).exists())) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}

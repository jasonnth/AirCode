package com.paypal.android.sdk.onetouch.core.metadata;

import android.os.Build;
import java.io.File;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.p */
public final class C4690p {

    /* renamed from: a */
    private static C4680e f4026a = new C4680e();

    /* renamed from: a */
    public static boolean m2476a() {
        return (Build.TAGS != null && Build.TAGS.contains("test-keys")) || m2477c() || m2478d();
    }

    /* renamed from: c */
    private static boolean m2477c() {
        try {
            return new File(C4680e.m2431a("suFileName")).exists();
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: d */
    private static boolean m2478d() {
        try {
            return new File(C4680e.m2431a("superUserApk")).exists();
        } catch (Exception e) {
            return false;
        }
    }
}

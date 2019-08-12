package com.paypal.android.sdk.onetouch.core.metadata;

import java.io.Closeable;
import java.io.IOException;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.af */
public final class C4674af {
    /* renamed from: a */
    public static void m2376a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}

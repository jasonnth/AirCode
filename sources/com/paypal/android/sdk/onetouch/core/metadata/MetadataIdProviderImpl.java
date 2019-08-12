package com.paypal.android.sdk.onetouch.core.metadata;

import android.content.Context;
import java.util.Map;

public class MetadataIdProviderImpl implements MetadataIdProvider {

    /* renamed from: a */
    private C4682h f3820a = C4682h.m2450h();

    public void flush() {
        this.f3820a.mo45423b();
    }

    public String generatePairingId(String str) {
        return this.f3820a.mo45422b(str);
    }

    public String init(Context context, String str, Map<String, Object> map) {
        return this.f3820a.mo45419a(context, str, C4687m.MSDK, "3.1.4", map);
    }
}

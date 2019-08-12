package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.app.admin.DevicePolicyManager;
import android.content.Context;

/* renamed from: com.threatmetrix.TrustDefender.q */
class C4829q {

    /* renamed from: a */
    private static final String f4645a = C4834w.m2892a(C4829q.class);

    C4829q() {
    }

    @TargetApi(11)
    /* renamed from: a */
    static int m2881a(Context context) {
        if (C4802c.f4381a == null) {
            return 16;
        }
        if (C4800a.f4363c < C4801b.f4368d) {
            return 1;
        }
        try {
            Object policyService = context.getSystemService("device_policy");
            if (policyService == null || !(policyService instanceof DevicePolicyManager)) {
                return 16;
            }
            return ((DevicePolicyManager) policyService).getStorageEncryptionStatus();
        } catch (SecurityException e) {
            String str = f4645a;
            return 16;
        } catch (Exception e2) {
            C4834w.m2901c(f4645a, e2.getMessage());
            return 16;
        }
    }
}

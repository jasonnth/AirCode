package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.PowerManager;

/* renamed from: com.threatmetrix.TrustDefender.r */
class C4830r {

    /* renamed from: a */
    private static final String f4646a = C4834w.m2892a(C4830r.class);

    C4830r() {
    }

    @TargetApi(20)
    /* renamed from: a */
    public static boolean m2882a(Context context) {
        if (!C4809j.m2799a() || C4800a.f4363c < C4801b.f4377m) {
            return true;
        }
        try {
            Object powerService = context.getSystemService("power");
            if (powerService == null || !(powerService instanceof PowerManager)) {
                return true;
            }
            return ((PowerManager) powerService).isInteractive();
        } catch (SecurityException e) {
            String str = f4646a;
            return true;
        } catch (Exception e2) {
            C4834w.m2901c(f4646a, e2.getMessage());
            return true;
        }
    }
}

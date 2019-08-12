package com.paypal.android.sdk.onetouch.core.metadata;

import java.util.TimerTask;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.i */
final class C4683i extends TimerTask {

    /* renamed from: a */
    private /* synthetic */ C4682h f4014a;

    C4683i(C4682h hVar) {
        this.f4014a = hVar;
    }

    public final void run() {
        this.f4014a.f4009v = this.f4014a.f4009v + 1;
        C4677ai.m2390a(C4682h.f3992h, "****** LogRiskMetadataTask #" + this.f4014a.f4009v);
        if (C4682h.m2444c(this.f4014a)) {
            C4677ai.m2390a(C4682h.f3992h, "No host activity in the last " + (this.f4014a.f4008u / 1000) + " seconds. Stopping update interval.");
            this.f4014a.f3998F.cancel();
            return;
        }
        try {
            C4682h.m2447f(this.f4014a);
        } catch (Exception e) {
            C4677ai.m2391a(C4682h.f3992h, "Error in logRiskMetadataTask: ", (Throwable) e);
        }
    }
}

package com.paypal.android.sdk.onetouch.core.metadata;

import java.util.TimerTask;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.j */
final class C4684j extends TimerTask {

    /* renamed from: a */
    private /* synthetic */ C4682h f4015a;

    C4684j(C4682h hVar) {
        this.f4015a = hVar;
    }

    public final void run() {
        this.f4015a.f4010w = this.f4015a.f4010w + 1;
        C4677ai.m2390a(C4682h.f3992h, "****** LoadConfigurationTask #" + this.f4015a.f4010w);
        C4671ab.m2370a().mo45394a(new C4694v(this.f4015a.f4005r, this.f4015a.f4012y, this.f4015a.f3999G));
    }
}

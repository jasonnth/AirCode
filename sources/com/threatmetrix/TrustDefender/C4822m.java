package com.threatmetrix.TrustDefender;

import java.io.IOException;
import java.util.Map;

/* renamed from: com.threatmetrix.TrustDefender.m */
class C4822m extends C4824o {

    /* renamed from: f */
    private static final String f4614f = C4834w.m2892a(C4822m.class);

    /* renamed from: a */
    public C4771aj f4615a = null;

    /* renamed from: e */
    private C4794e f4616e = null;

    public C4822m(C4777am client, String url, C4823n params, Map<String, String> headers, C4751TrustDefender sdk, C4794e state) {
        super(client, C4825a.GET, url, params, headers, sdk, null, state);
        this.f4616e = state;
    }

    public void run() {
        this.f4615a = null;
        try {
            C4834w.m2901c(f4614f, "starting retrieval: " + this.f4623c + "?" + this.f4624d.mo46086b());
            super.run();
            if (mo46087b() == 200) {
                this.f4615a = new C4771aj();
                try {
                    this.f4615a.mo45969a(this.f4622b.mo45940d());
                } catch (IOException e) {
                    if (this.f4616e == null || !this.f4616e.mo45949a()) {
                        C4834w.m2895a(f4614f, "IO Error", (Throwable) e);
                    } else {
                        C4834w.m2901c(f4614f, "IO Error, probably due to cancel");
                    }
                } finally {
                    this.f4622b.mo45941e();
                }
            }
        } catch (InterruptedException e2) {
            if (this.f4616e == null || !this.f4616e.mo45949a()) {
                C4834w.m2895a(f4614f, "starting retrieval: " + this.f4623c + " but interrupted", (Throwable) e2);
            } else {
                C4834w.m2901c(f4614f, "starting retrieval: " + this.f4623c + " but interrupted by cancel");
            }
        }
    }

    /* renamed from: a */
    public final THMStatusCode mo46078a() {
        if (this.f4622b.mo45942f() == THMStatusCode.THM_OK) {
            return (this.f4615a == null || !this.f4615a.mo45970a()) ? THMStatusCode.THM_ConfigurationError : THMStatusCode.THM_OK;
        }
        return super.mo46078a();
    }
}

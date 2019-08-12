package com.threatmetrix.TrustDefender;

import android.content.Context;
import java.util.Map;

/* renamed from: com.threatmetrix.TrustDefender.o */
class C4824o implements Runnable {

    /* renamed from: h */
    private static final String f4620h = C4834w.m2892a(C4824o.class);

    /* renamed from: a */
    private final C4825a f4621a;

    /* renamed from: b */
    final C4784aq f4622b;

    /* renamed from: c */
    final String f4623c;

    /* renamed from: d */
    final C4823n f4624d;

    /* renamed from: e */
    private Context f4625e = null;

    /* renamed from: f */
    private final C4751TrustDefender f4626f;

    /* renamed from: g */
    private C4794e f4627g = null;

    /* renamed from: com.threatmetrix.TrustDefender.o$a */
    enum C4825a {
        GET,
        GET_CONSUME,
        POST,
        POST_CONSUME
    }

    public C4824o(C4777am client, C4825a type, String url, C4823n params, Map<String, String> headers, C4751TrustDefender sdk, Context context, C4794e state) {
        this.f4627g = state;
        this.f4622b = client.mo45945a(state);
        this.f4622b.mo45937a(headers);
        this.f4621a = type;
        this.f4623c = url;
        this.f4624d = params;
        this.f4626f = sdk;
        this.f4625e = context;
    }

    public void run() {
        long startTime = System.nanoTime();
        C4834w.m2901c(f4620h, "starting retrieval: " + this.f4623c);
        long result = -1;
        if (this.f4621a == C4825a.GET || this.f4621a == C4825a.GET_CONSUME) {
            try {
                result = this.f4622b.mo45934a(this.f4624d == null ? this.f4623c : this.f4623c + "?" + this.f4624d.mo46086b());
            } catch (InterruptedException e) {
                if (this.f4627g == null || !this.f4627g.mo45949a()) {
                    C4834w.m2895a(f4620h, "interrupted, aborting connection", (Throwable) e);
                } else {
                    C4834w.m2901c(f4620h, "interrupted due to cancel");
                }
                if (this.f4626f != null) {
                    this.f4626f.mo45921a(THMStatusCode.THM_Interrupted_Error);
                    return;
                }
                return;
            }
        } else if (this.f4621a == C4825a.POST || this.f4621a == C4825a.POST_CONSUME) {
            result = this.f4622b.mo45935a(this.f4623c, this.f4624d);
        }
        long duration = (System.nanoTime() - startTime) / 1000000;
        if (result < 0) {
            C4834w.m2899b(f4620h, "failed to retrieve from " + this.f4622b.mo45938b() + " with " + this.f4622b.mo45942f().toString() + " in " + duration + "ms");
            if (this.f4626f != null) {
                this.f4626f.mo45921a(this.f4622b.mo45942f());
                return;
            }
            return;
        }
        C4834w.m2901c(f4620h, "retrieved: " + this.f4622b.mo45936a() + " in " + duration + "ms");
        if (result != 200) {
            C4834w.m2899b(f4620h, "error (" + result + ") status on request to " + this.f4622b.mo45938b());
        } else if (this.f4621a == C4825a.GET_CONSUME || this.f4621a == C4825a.POST_CONSUME) {
            C4834w.m2901c(f4620h, "consuming content");
            this.f4622b.mo45941e();
        }
    }

    /* renamed from: a */
    public THMStatusCode mo46078a() {
        return this.f4622b.mo45942f();
    }

    /* renamed from: b */
    public final int mo46087b() {
        return this.f4622b.mo45943g();
    }

    /* renamed from: c */
    public final void mo46088c() {
        this.f4622b.mo45939c();
    }
}

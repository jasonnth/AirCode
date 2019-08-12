package com.paypal.android.sdk.onetouch.core.metadata;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.v */
public class C4694v extends C4670aa {

    /* renamed from: a */
    private static final String f4033a = C4694v.class.getSimpleName();

    /* renamed from: e */
    private Context f4034e;

    /* renamed from: f */
    private String f4035f;

    /* renamed from: g */
    private Handler f4036g;

    public C4694v(Context context, String str, Handler handler) {
        this.f4034e = context;
        this.f4035f = str;
        this.f4036g = handler;
    }

    public void run() {
        C4677ai.m2390a(f4033a, "entering LoadConfigurationRequest.");
        if (this.f4036g != null) {
            try {
                this.f4036g.sendMessage(Message.obtain(this.f4036g, 10, this.f4035f));
                this.f4036g.sendMessage(Message.obtain(this.f4036g, 12, new C4678c(this.f4034e, this.f4035f)));
            } catch (Exception e) {
                C4677ai.m2391a(f4033a, "LoadConfigurationRequest loading remote config failed.", (Throwable) e);
                this.f4036g.sendMessage(Message.obtain(this.f4036g, 11, e));
            } finally {
                C4671ab.m2370a().mo45395b(this);
            }
            C4677ai.m2390a(f4033a, "leaving LoadConfigurationRequest.");
        }
    }
}

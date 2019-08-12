package com.paypal.android.sdk.onetouch.core.metadata;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.k */
final class C4685k extends Handler {

    /* renamed from: a */
    private final WeakReference<C4682h> f4016a;

    public C4685k(C4682h hVar) {
        this.f4016a = new WeakReference<>(hVar);
    }

    public final void handleMessage(Message message) {
        C4682h hVar = (C4682h) this.f4016a.get();
        if (hVar != null) {
            hVar.mo45421a(message);
        }
    }
}

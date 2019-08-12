package com.threatmetrix.TrustDefender;

import android.location.Location;
import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.v */
final class C4833v extends C4787at {

    /* renamed from: a */
    static final Method f4661a = m2743a(Location.class, "isFromMockProvider", new Class[0]);

    /* renamed from: b */
    private boolean f4662b = false;

    C4833v(Location location) {
        if (C4800a.f4363c >= C4801b.f4375k) {
            Boolean b = (Boolean) m2741a((Object) location, f4661a, new Object[0]);
            if (b != null) {
                this.f4662b = b.booleanValue();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final boolean mo46095a() {
        return this.f4662b;
    }
}

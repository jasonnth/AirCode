package com.paypal.android.sdk.onetouch.core.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.ab */
public class C4671ab {

    /* renamed from: c */
    private List<C4670aa> f3823c;

    /* renamed from: d */
    private List<C4670aa> f3824d;

    static {
        C4671ab.class.getSimpleName();
    }

    private C4671ab() {
        this.f3823c = Collections.synchronizedList(new ArrayList());
        this.f3824d = Collections.synchronizedList(new ArrayList());
    }

    /* synthetic */ C4671ab(byte b) {
        this();
    }

    /* renamed from: a */
    public static C4671ab m2370a() {
        return C4672ad.f3825a;
    }

    /* renamed from: b */
    private void m2371b() {
        if (!this.f3824d.isEmpty()) {
            synchronized (this) {
                if (!this.f3824d.isEmpty()) {
                    C4670aa aaVar = (C4670aa) this.f3824d.get(0);
                    this.f3824d.remove(0);
                    this.f3823c.add(aaVar);
                    new Thread(aaVar).start();
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo45394a(C4670aa aaVar) {
        this.f3824d.add(aaVar);
        if (this.f3823c.size() < 3) {
            m2371b();
        }
    }

    /* renamed from: b */
    public final void mo45395b(C4670aa aaVar) {
        this.f3823c.remove(aaVar);
        m2371b();
    }
}

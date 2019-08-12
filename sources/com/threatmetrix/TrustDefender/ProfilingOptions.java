package com.threatmetrix.TrustDefender;

import android.location.Location;
import java.util.List;

public class ProfilingOptions {

    /* renamed from: a */
    private String f4091a;

    /* renamed from: b */
    private List<String> f4092b;

    /* renamed from: c */
    private Location f4093c;

    /* renamed from: d */
    private EndNotifierBase f4094d = null;

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final String mo45903a() {
        return this.f4091a;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final List<String> mo45904b() {
        return this.f4092b;
    }

    public ProfilingOptions setCustomAttributes(List<String> customAttributes) {
        this.f4092b = customAttributes;
        return this;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final Location mo45905c() {
        return this.f4093c;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final EndNotifierBase mo45906d() {
        return this.f4094d;
    }

    public ProfilingOptions setEndNotifier(EndNotifierBase notifier) {
        this.f4094d = notifier;
        return this;
    }
}

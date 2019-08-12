package p004bo.app;

/* renamed from: bo.app.ht */
public enum C0606ht {
    STRICT(false, true),
    LENIENT(true, false),
    NON_EXTENSIBLE(false, false),
    STRICT_ORDER(true, true);
    

    /* renamed from: e */
    private final boolean f815e;

    /* renamed from: f */
    private final boolean f816f;

    private C0606ht(boolean z, boolean z2) {
        this.f815e = z;
        this.f816f = z2;
    }

    /* renamed from: a */
    public boolean mo7279a() {
        return this.f815e;
    }

    /* renamed from: b */
    public boolean mo7280b() {
        return this.f816f;
    }
}

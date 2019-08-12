package p004bo.app;

/* renamed from: bo.app.gm */
public class C0559gm {

    /* renamed from: a */
    private final C0560a f728a;

    /* renamed from: b */
    private final Throwable f729b;

    /* renamed from: bo.app.gm$a */
    public enum C0560a {
        IO_ERROR,
        DECODING_ERROR,
        NETWORK_DENIED,
        OUT_OF_MEMORY,
        UNKNOWN
    }

    public C0559gm(C0560a aVar, Throwable th) {
        this.f728a = aVar;
        this.f729b = th;
    }
}

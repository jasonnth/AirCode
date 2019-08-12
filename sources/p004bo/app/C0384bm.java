package p004bo.app;

import com.appboy.support.AppboyLogger;

/* renamed from: bo.app.bm */
public class C0384bm<T> {

    /* renamed from: a */
    private static final String f185a = AppboyLogger.getAppboyLogTag(C0384bm.class);

    /* renamed from: b */
    private T f186b;

    /* renamed from: c */
    private volatile boolean f187c = false;

    /* renamed from: d */
    private volatile boolean f188d = false;

    /* renamed from: e */
    private final Object f189e = new Object();

    public C0384bm(T t, boolean z) {
        this.f188d = z;
        this.f186b = t;
    }

    /* renamed from: a */
    public synchronized T mo6807a() {
        return this.f186b;
    }

    /* renamed from: a */
    public synchronized void mo6808a(T t) {
        this.f186b = t;
    }

    /* renamed from: b */
    public boolean mo6809b() {
        return this.f187c;
    }

    /* renamed from: c */
    public boolean mo6810c() {
        return this.f188d;
    }

    /* renamed from: d */
    public void mo6811d() {
        synchronized (this.f189e) {
            if (mo6809b()) {
                AppboyLogger.m1735e(f185a, "Warning: called dispatch() on field already in dispatch. Please report this to Appboy.");
            }
            this.f187c = true;
        }
    }

    /* renamed from: e */
    public void mo6812e() {
        synchronized (this.f189e) {
            this.f187c = false;
            if (this.f188d) {
                AppboyLogger.m1735e(f185a, "Erroneously got processSuccessfulDispatch call in DispatchOnceField with mHasSentSuccessfully already true. Please report this to Appboy.");
            }
            this.f188d = true;
        }
    }

    /* renamed from: f */
    public void mo6813f() {
        synchronized (this.f189e) {
            this.f187c = false;
            if (this.f188d) {
                AppboyLogger.m1735e(f185a, "Erroneously got processFailedDispatch call in DispatchOnceField with mHasSentSuccessfully already true. Please report this to Appboy.");
            }
        }
    }
}

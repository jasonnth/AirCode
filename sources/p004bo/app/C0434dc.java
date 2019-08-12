package p004bo.app;

import com.appboy.support.AppboyLogger;

/* renamed from: bo.app.dc */
public abstract class C0434dc<T> {

    /* renamed from: b */
    private static final String f330b = AppboyLogger.getAppboyLogTag(C0434dc.class);

    /* renamed from: a */
    private final Object f331a = new Object();

    /* renamed from: c */
    private boolean f332c = false;

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract T mo6929a();

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract void mo6930a(T t);

    /* renamed from: b */
    public T mo6931b() {
        T a;
        synchronized (this.f331a) {
            if (this.f332c) {
                AppboyLogger.m1737i(f330b, "Received call to export dirty object, but the cache was already locked.");
                a = null;
            } else {
                this.f332c = true;
                a = mo6929a();
            }
        }
        return a;
    }

    /* renamed from: b */
    public boolean mo6932b(T t) {
        synchronized (this.f331a) {
            if (!this.f332c) {
                AppboyLogger.m1739w(f330b, String.format("Tried to confirm [%s], but the cache wasn't locked, so not doing anything.", new Object[]{String.valueOf(t)}));
                return false;
            }
            mo6930a(t);
            this.f332c = false;
            return true;
        }
    }
}

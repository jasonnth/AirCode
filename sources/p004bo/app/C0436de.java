package p004bo.app;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: bo.app.de */
public class C0436de implements C0446di {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final C0446di f335a;

    /* renamed from: b */
    private final C0360at f336b;

    public C0436de(C0446di diVar, C0360at atVar) {
        this.f335a = diVar;
        this.f336b = atVar;
    }

    /* renamed from: a */
    public synchronized void mo6938a(final C0391bt btVar) {
        this.f336b.execute(new Runnable() {
            public void run() {
                C0436de.this.f335a.mo6938a(btVar);
            }
        });
    }

    /* renamed from: a */
    public synchronized C0391bt mo6937a() {
        try {
        } catch (Exception e) {
            throw new RuntimeException("Error while trying to asynchronously get stored open sessions.", e);
        }
        return (C0391bt) this.f336b.submit(new Callable<C0391bt>() {
            /* renamed from: a */
            public C0391bt call() {
                return C0436de.this.f335a.mo6937a();
            }
        }).get(5000, TimeUnit.MILLISECONDS);
    }

    /* renamed from: b */
    public synchronized void mo6941b(C0391bt btVar) {
        mo6942c(btVar);
    }

    /* renamed from: c */
    public synchronized Future<?> mo6942c(final C0391bt btVar) {
        return this.f336b.submit(new Runnable() {
            public void run() {
                C0436de.this.f335a.mo6941b(btVar);
            }
        });
    }

    /* renamed from: b */
    public synchronized Collection<C0391bt> mo6940b() {
        try {
        } catch (Exception e) {
            throw new RuntimeException("Error while trying to asynchronously get sealed sessions.", e);
        }
        return (Collection) this.f336b.submit(new Callable<Collection<C0391bt>>() {
            /* renamed from: a */
            public Collection<C0391bt> call() {
                return C0436de.this.f335a.mo6940b();
            }
        }).get(5000, TimeUnit.MILLISECONDS);
    }

    /* renamed from: a */
    public void mo6939a(final C0391bt btVar, final C0386bo boVar) {
        this.f336b.execute(new Runnable() {
            public void run() {
                C0436de.this.f335a.mo6939a(btVar, boVar);
            }
        });
    }
}

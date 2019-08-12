package p004bo.app;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: bo.app.gh */
class C0549gh {

    /* renamed from: a */
    final C0544gg f677a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Executor f678b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public Executor f679c;

    /* renamed from: d */
    private Executor f680d;

    /* renamed from: e */
    private final Map<Integer, String> f681e = Collections.synchronizedMap(new HashMap());

    /* renamed from: f */
    private final Map<String, ReentrantLock> f682f = new WeakHashMap();

    /* renamed from: g */
    private final AtomicBoolean f683g = new AtomicBoolean(false);

    /* renamed from: h */
    private final AtomicBoolean f684h = new AtomicBoolean(false);

    /* renamed from: i */
    private final AtomicBoolean f685i = new AtomicBoolean(false);

    /* renamed from: j */
    private final Object f686j = new Object();

    C0549gh(C0544gg ggVar) {
        this.f677a = ggVar;
        this.f678b = ggVar.f635g;
        this.f679c = ggVar.f636h;
        this.f680d = C0537gc.m784a();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7156a(final C0552gj gjVar) {
        this.f680d.execute(new Runnable() {
            public void run() {
                File a = C0549gh.this.f677a.f643o.mo7073a(gjVar.mo7166a());
                boolean z = a != null && a.exists();
                C0549gh.this.m904e();
                if (z) {
                    C0549gh.this.f679c.execute(gjVar);
                } else {
                    C0549gh.this.f678b.execute(gjVar);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7157a(C0557gk gkVar) {
        m904e();
        this.f679c.execute(gkVar);
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m904e() {
        if (!this.f677a.f637i && ((ExecutorService) this.f678b).isShutdown()) {
            this.f678b = m905f();
        }
        if (!this.f677a.f638j && ((ExecutorService) this.f679c).isShutdown()) {
            this.f679c = m905f();
        }
    }

    /* renamed from: f */
    private Executor m905f() {
        return C0537gc.m785a(this.f677a.f639k, this.f677a.f640l, this.f677a.f641m);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public String mo7153a(C0588he heVar) {
        return (String) this.f681e.get(Integer.valueOf(heVar.mo7266f()));
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7158a(C0588he heVar, String str) {
        this.f681e.put(Integer.valueOf(heVar.mo7266f()), str);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public void mo7162b(C0588he heVar) {
        this.f681e.remove(Integer.valueOf(heVar.mo7266f()));
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7160a(boolean z) {
        this.f684h.set(z);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7159a(Runnable runnable) {
        this.f680d.execute(runnable);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public ReentrantLock mo7155a(String str) {
        ReentrantLock reentrantLock = (ReentrantLock) this.f682f.get(str);
        if (reentrantLock != null) {
            return reentrantLock;
        }
        ReentrantLock reentrantLock2 = new ReentrantLock();
        this.f682f.put(str, reentrantLock2);
        return reentrantLock2;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public AtomicBoolean mo7154a() {
        return this.f683g;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public Object mo7161b() {
        return this.f686j;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public boolean mo7163c() {
        return this.f684h.get();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public boolean mo7164d() {
        return this.f685i.get();
    }
}

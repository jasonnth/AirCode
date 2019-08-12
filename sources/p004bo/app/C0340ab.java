package p004bo.app;

import android.app.Activity;
import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

/* renamed from: bo.app.ab */
public final class C0340ab implements C0343ac {

    /* renamed from: a */
    private static final String f74a = AppboyLogger.getAppboyLogTag(C0340ab.class);

    /* renamed from: b */
    private final ConcurrentMap<Activity, ConcurrentMap<Class, CopyOnWriteArraySet<IEventSubscriber>>> f75b = new ConcurrentHashMap();

    /* renamed from: c */
    private final ConcurrentMap<Class, CopyOnWriteArraySet<IEventSubscriber>> f76c = new ConcurrentHashMap();

    /* renamed from: d */
    private final ConcurrentMap<Class, CopyOnWriteArraySet<IEventSubscriber>> f77d = new ConcurrentHashMap();

    /* renamed from: e */
    private final Executor f78e;

    /* renamed from: f */
    private final Object f79f = new Object();

    /* renamed from: g */
    private final Object f80g = new Object();

    /* renamed from: h */
    private final Object f81h = new Object();

    public C0340ab(Executor executor) {
        this.f78e = executor;
    }

    /* renamed from: a */
    public <T> boolean mo6737a(IEventSubscriber<T> iEventSubscriber, Class<T> cls) {
        boolean a;
        synchronized (this.f80g) {
            a = m55a(iEventSubscriber, cls, this.f76c);
        }
        return a;
    }

    /* renamed from: a */
    private <T> boolean m55a(IEventSubscriber<T> iEventSubscriber, Class<T> cls, ConcurrentMap<Class, CopyOnWriteArraySet<IEventSubscriber>> concurrentMap) {
        String name;
        if (iEventSubscriber == null) {
            if (cls == null) {
                name = "null eventClass";
            } else {
                name = cls.getName();
            }
            AppboyLogger.m1735e(f74a, String.format("Error: Attempted to add a null subscriber for eventClass %s. This subscriber is being ignored. Please check your calling code to ensure that all potential subscriptions are valid.", new Object[]{name}));
            return false;
        }
        CopyOnWriteArraySet copyOnWriteArraySet = (CopyOnWriteArraySet) concurrentMap.get(cls);
        if (copyOnWriteArraySet == null) {
            CopyOnWriteArraySet copyOnWriteArraySet2 = new CopyOnWriteArraySet();
            copyOnWriteArraySet = (CopyOnWriteArraySet) concurrentMap.putIfAbsent(cls, copyOnWriteArraySet2);
            if (copyOnWriteArraySet == null) {
                copyOnWriteArraySet = copyOnWriteArraySet2;
            }
        }
        return copyOnWriteArraySet.add(iEventSubscriber);
    }

    /* renamed from: b */
    public <T> boolean mo6738b(IEventSubscriber<T> iEventSubscriber, Class<T> cls) {
        boolean a;
        synchronized (this.f80g) {
            a = m56a((CopyOnWriteArraySet) this.f76c.get(cls), iEventSubscriber);
        }
        return a;
    }

    /* renamed from: a */
    private <T> boolean m56a(CopyOnWriteArraySet<IEventSubscriber> copyOnWriteArraySet, IEventSubscriber<T> iEventSubscriber) {
        return (copyOnWriteArraySet == null || iEventSubscriber == null || !copyOnWriteArraySet.remove(iEventSubscriber)) ? false : true;
    }

    /* renamed from: a */
    public <T> void mo6736a(final T t, final Class<T> cls) {
        AppboyLogger.m1733d(f74a, cls.getName() + " fired: " + t.toString());
        for (Entry entry : this.f75b.entrySet()) {
            final CopyOnWriteArraySet copyOnWriteArraySet = (CopyOnWriteArraySet) ((ConcurrentMap) entry.getValue()).get(cls);
            if (copyOnWriteArraySet != null && !copyOnWriteArraySet.isEmpty()) {
                ((Activity) entry.getKey()).runOnUiThread(new Runnable() {
                    public void run() {
                        Iterator it = C0340ab.this.m54a(cls, copyOnWriteArraySet).iterator();
                        while (it.hasNext()) {
                            ((IEventSubscriber) it.next()).trigger(t);
                        }
                    }
                });
            }
        }
        CopyOnWriteArraySet copyOnWriteArraySet2 = (CopyOnWriteArraySet) this.f76c.get(cls);
        if (copyOnWriteArraySet2 != null) {
            Iterator it = m54a(cls, copyOnWriteArraySet2).iterator();
            while (it.hasNext()) {
                final IEventSubscriber iEventSubscriber = (IEventSubscriber) it.next();
                this.f78e.execute(new Runnable() {
                    public void run() {
                        iEventSubscriber.trigger(t);
                    }
                });
            }
        }
        CopyOnWriteArraySet copyOnWriteArraySet3 = (CopyOnWriteArraySet) this.f77d.get(cls);
        if (copyOnWriteArraySet3 != null) {
            Iterator it2 = m54a(cls, copyOnWriteArraySet3).iterator();
            while (it2.hasNext()) {
                ((IEventSubscriber) it2.next()).trigger(t);
            }
        }
    }

    /* renamed from: a */
    public void mo6735a() {
        synchronized (this.f80g) {
            this.f76c.clear();
        }
        synchronized (this.f81h) {
            this.f77d.clear();
        }
        synchronized (this.f79f) {
            this.f75b.clear();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public <T> CopyOnWriteArraySet<IEventSubscriber<T>> m54a(Class<T> cls, CopyOnWriteArraySet<IEventSubscriber> copyOnWriteArraySet) {
        CopyOnWriteArraySet<IEventSubscriber<T>> copyOnWriteArraySet2 = copyOnWriteArraySet;
        AppboyLogger.m1733d(f74a, "Triggering " + cls.getName() + " on " + copyOnWriteArraySet.size() + " subscribers.");
        return copyOnWriteArraySet2;
    }
}

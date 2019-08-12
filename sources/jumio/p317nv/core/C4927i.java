package jumio.p317nv.core;

import android.content.Context;
import com.jumio.commons.log.Log;
import com.jumio.core.mvp.model.InvokeOnUiThread;
import com.jumio.core.mvp.model.Publisher;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ApiCall;
import com.jumio.core.util.MultiHashMap;
import com.jumio.p311nv.models.BackendModel;
import com.jumio.persistence.DataAccess;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: jumio.nv.core.i */
/* compiled from: QueueProcessor */
public class C4927i<T extends Callable> implements Subscriber, C4926a {

    /* renamed from: a */
    private MultiHashMap<Class<T>, Subscriber> f4806a = new MultiHashMap<>();

    /* renamed from: b */
    private Object f4807b = new Object();

    /* renamed from: c */
    private AtomicBoolean f4808c = new AtomicBoolean(false);

    /* renamed from: d */
    private C4925h<T> f4809d;

    /* renamed from: e */
    private ExecutorService f4810e;

    /* renamed from: f */
    private Future<T> f4811f;

    /* renamed from: g */
    private Context f4812g;

    /* renamed from: jumio.nv.core.i$a */
    /* compiled from: QueueProcessor */
    static class C4929a extends Publisher<Object> {
        private C4929a() {
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public void mo46870a(Subscriber subscriber, Object obj) {
            add(subscriber);
            publishResult(obj);
            remove(subscriber);
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public void mo46871a(Subscriber subscriber, Throwable th) {
            add(subscriber);
            publishError(th);
            remove(subscriber);
        }
    }

    public C4927i(C4925h<T> hVar, ExecutorService executorService) {
        this.f4809d = hVar;
        this.f4809d.mo46860a(this);
        this.f4810e = executorService;
    }

    /* renamed from: a */
    public synchronized void mo46863a() {
        if (!this.f4809d.isEmpty() && !this.f4808c.get()) {
            this.f4808c.set(true);
            Callable callable = (Callable) this.f4809d.peek();
            Log.m1919i("QueueProcessor", "proceed() starting " + callable.getClass().getSimpleName());
            if (callable instanceof Publisher) {
                ((Publisher) callable).add(this);
                this.f4811f = this.f4810e.submit(callable);
            } else {
                throw new RuntimeException("all objects submitted to the QueueProcessor must extend Publisher!");
            }
        }
    }

    /* renamed from: a */
    public void mo46862a(Object obj) {
        Log.m1919i("QueueProcessor", "  item added! " + obj.getClass().getSimpleName());
        if (!this.f4808c.get()) {
            mo46863a();
        } else {
            Log.m1919i("QueueProcessor", "  don't proceed, a call is executing");
        }
    }

    @InvokeOnUiThread(false)
    public void onResult(Object obj) {
        this.f4808c.set(false);
        Callable callable = (Callable) this.f4809d.poll();
        this.f4811f = null;
        if (callable != null) {
            synchronized (this.f4807b) {
                List<Subscriber> remove = this.f4806a.remove((Object) callable.getClass());
                Log.m1919i("QueueProcessor", "onResult() call succeeded: " + callable.getClass().getSimpleName());
                if (remove != null && remove.size() != 0) {
                    for (Subscriber subscriber : remove) {
                        Log.m1919i("QueueProcessor", "  notifying " + subscriber.getClass().getSimpleName());
                        new C4929a().mo46870a(subscriber, obj);
                    }
                    BackendModel backendModel = (BackendModel) DataAccess.load(this.f4812g, BackendModel.class);
                    if (backendModel != null) {
                        backendModel.remove(callable.getClass());
                        DataAccess.update(this.f4812g, BackendModel.class, backendModel);
                    }
                } else if ((obj instanceof Serializable) || obj == null) {
                    BackendModel backendModel2 = (BackendModel) DataAccess.load(this.f4812g, BackendModel.class);
                    if (backendModel2 == null) {
                        backendModel2 = new BackendModel();
                    }
                    backendModel2.add(callable.getClass(), (Serializable) obj);
                    DataAccess.update(this.f4812g, BackendModel.class, backendModel2);
                }
            }
            mo46863a();
        }
    }

    /* renamed from: a */
    public void mo46864a(Context context, Class<?> cls, Subscriber subscriber) {
        this.f4812g = context;
        BackendModel backendModel = (BackendModel) DataAccess.load(context, BackendModel.class);
        if (backendModel != null && backendModel.has(cls)) {
            Object obj = backendModel.get(cls);
            if (obj instanceof Throwable) {
                new C4929a().mo46871a(subscriber, (Throwable) obj);
            } else {
                new C4929a().mo46870a(subscriber, obj);
            }
            backendModel.remove(cls);
            DataAccess.update(context, BackendModel.class, backendModel);
        }
    }

    @InvokeOnUiThread(false)
    public void onError(Throwable th) {
        this.f4808c.set(false);
        Callable callable = (Callable) this.f4809d.peek();
        this.f4811f = null;
        if (callable != null) {
            synchronized (this.f4807b) {
                List<Subscriber> list = (List) this.f4806a.get(callable.getClass());
                Log.m1919i("QueueProcessor", "onError() call failed: " + callable.getClass().getSimpleName());
                if (list == null || list.size() == 0) {
                    BackendModel backendModel = (BackendModel) DataAccess.load(this.f4812g, BackendModel.class);
                    if (backendModel == null) {
                        backendModel = new BackendModel();
                    }
                    backendModel.add(callable.getClass(), th);
                    DataAccess.update(this.f4812g, BackendModel.class, backendModel);
                } else {
                    for (Subscriber subscriber : list) {
                        Log.m1919i("QueueProcessor", "  notifying " + subscriber.getClass().getSimpleName());
                        new C4929a().mo46871a(subscriber, th);
                    }
                    BackendModel backendModel2 = (BackendModel) DataAccess.load(this.f4812g, BackendModel.class);
                    if (backendModel2 != null) {
                        backendModel2.remove(callable.getClass());
                        DataAccess.update(this.f4812g, BackendModel.class, backendModel2);
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public void mo46868b(Context context, Class<T> cls, Subscriber subscriber) {
        if (cls == null || subscriber == null) {
            Log.m1919i("QueueProcessor", "register(): not registering, null-constraint not satisfied");
            return;
        }
        mo46864a(context, cls, subscriber);
        synchronized (this.f4807b) {
            this.f4806a.putOne(cls, subscriber);
        }
        Log.m1919i("QueueProcessor", "register() registering " + subscriber.getClass().getSimpleName() + " for " + cls.getSimpleName() + ". new count = " + this.f4806a.count(cls) + ", overall count = " + this.f4806a.size());
    }

    /* renamed from: a */
    public void mo46866a(T t) {
        this.f4809d.add(t);
    }

    /* renamed from: b */
    public int mo46867b() {
        return this.f4809d.size();
    }

    /* renamed from: c */
    public void mo46869c() {
        try {
            if (this.f4811f != null) {
                this.f4811f.cancel(true);
            }
        } catch (Exception e) {
        }
        this.f4809d.clear();
        this.f4810e.shutdownNow();
        synchronized (this.f4807b) {
            this.f4806a.clear();
        }
        this.f4808c.set(false);
    }

    /* renamed from: a */
    public void mo46865a(Class<? extends ApiCall> cls, Subscriber subscriber) {
        synchronized (this.f4807b) {
            if (this.f4806a.containsKey(cls)) {
                Log.m1919i("QueueProcessor", String.format("unregister(): removing %s (previously registered for %s)", new Object[]{subscriber.getClass().getSimpleName(), cls.getSimpleName()}));
                ((List) this.f4806a.get(cls)).remove(subscriber);
            }
        }
    }
}

package jumio.p317nv.core;

import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: jumio.nv.core.h */
/* compiled from: NotifyingQueue */
public class C4925h<T> extends ConcurrentLinkedQueue<T> {

    /* renamed from: a */
    private C4926a<T> f4805a;

    /* renamed from: jumio.nv.core.h$a */
    /* compiled from: NotifyingQueue */
    interface C4926a<T> {
        /* renamed from: a */
        void mo46862a(T t);
    }

    /* renamed from: a */
    public void mo46860a(C4926a<T> aVar) {
        this.f4805a = aVar;
    }

    public boolean add(T t) {
        boolean add = super.add(t);
        if (this.f4805a != null) {
            this.f4805a.mo46862a(t);
        }
        return add;
    }
}

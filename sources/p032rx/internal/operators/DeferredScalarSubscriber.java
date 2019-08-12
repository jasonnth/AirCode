package p032rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import p032rx.Observable;
import p032rx.Producer;
import p032rx.Subscriber;

/* renamed from: rx.internal.operators.DeferredScalarSubscriber */
public abstract class DeferredScalarSubscriber<T, R> extends Subscriber<T> {
    protected final Subscriber<? super R> actual;
    protected boolean hasValue;
    final AtomicInteger state = new AtomicInteger();
    protected R value;

    /* renamed from: rx.internal.operators.DeferredScalarSubscriber$InnerProducer */
    static final class InnerProducer implements Producer {
        final DeferredScalarSubscriber<?, ?> parent;

        public InnerProducer(DeferredScalarSubscriber<?, ?> parent2) {
            this.parent = parent2;
        }

        public void request(long n) {
            this.parent.downstreamRequest(n);
        }
    }

    public DeferredScalarSubscriber(Subscriber<? super R> actual2) {
        this.actual = actual2;
    }

    public void onError(Throwable ex) {
        this.value = null;
        this.actual.onError(ex);
    }

    public void onCompleted() {
        if (this.hasValue) {
            complete(this.value);
        } else {
            complete();
        }
    }

    /* access modifiers changed from: protected */
    public final void complete() {
        this.actual.onCompleted();
    }

    /* access modifiers changed from: protected */
    public final void complete(R value2) {
        Subscriber<? super R> a = this.actual;
        do {
            int s = this.state.get();
            if (s != 2 && s != 3 && !a.isUnsubscribed()) {
                if (s == 1) {
                    a.onNext(value2);
                    if (!a.isUnsubscribed()) {
                        a.onCompleted();
                    }
                    this.state.lazySet(3);
                    return;
                }
                this.value = value2;
            } else {
                return;
            }
        } while (!this.state.compareAndSet(0, 2));
    }

    /* access modifiers changed from: 0000 */
    public final void downstreamRequest(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + n);
        } else if (n != 0) {
            Subscriber<? super R> a = this.actual;
            do {
                int s = this.state.get();
                if (s != 1 && s != 3 && !a.isUnsubscribed()) {
                    if (s == 2) {
                        if (this.state.compareAndSet(2, 3)) {
                            a.onNext(this.value);
                            if (!a.isUnsubscribed()) {
                                a.onCompleted();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.state.compareAndSet(0, 1));
        }
    }

    public final void setProducer(Producer p) {
        p.request(Long.MAX_VALUE);
    }

    public final void subscribeTo(Observable<? extends T> source) {
        setupDownstream();
        source.unsafeSubscribe(this);
    }

    /* access modifiers changed from: 0000 */
    public final void setupDownstream() {
        Subscriber<? super R> a = this.actual;
        a.add(this);
        a.setProducer(new InnerProducer(this));
    }
}

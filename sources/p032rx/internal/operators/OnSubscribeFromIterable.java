package p032rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import p032rx.Observable.OnSubscribe;
import p032rx.Observer;
import p032rx.Producer;
import p032rx.Subscriber;
import p032rx.exceptions.Exceptions;

/* renamed from: rx.internal.operators.OnSubscribeFromIterable */
public final class OnSubscribeFromIterable<T> implements OnSubscribe<T> {

    /* renamed from: is */
    final Iterable<? extends T> f7248is;

    /* renamed from: rx.internal.operators.OnSubscribeFromIterable$IterableProducer */
    static final class IterableProducer<T> extends AtomicLong implements Producer {

        /* renamed from: it */
        private final Iterator<? extends T> f7249it;

        /* renamed from: o */
        private final Subscriber<? super T> f7250o;

        IterableProducer(Subscriber<? super T> o, Iterator<? extends T> it) {
            this.f7250o = o;
            this.f7249it = it;
        }

        public void request(long n) {
            if (get() != Long.MAX_VALUE) {
                if (n == Long.MAX_VALUE && compareAndSet(0, Long.MAX_VALUE)) {
                    fastPath();
                } else if (n > 0 && BackpressureUtils.getAndAddRequest(this, n) == 0) {
                    slowPath(n);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void slowPath(long n) {
            Subscriber<? super T> o = this.f7250o;
            Iterator<? extends T> it = this.f7249it;
            long r = n;
            long e = 0;
            while (true) {
                if (e == r) {
                    r = get();
                    if (e == r) {
                        r = BackpressureUtils.produced(this, e);
                        if (r != 0) {
                            e = 0;
                        } else {
                            return;
                        }
                    } else {
                        continue;
                    }
                } else if (!o.isUnsubscribed()) {
                    try {
                        o.onNext(it.next());
                        if (!o.isUnsubscribed()) {
                            try {
                                if (it.hasNext()) {
                                    e++;
                                } else if (!o.isUnsubscribed()) {
                                    o.onCompleted();
                                    return;
                                } else {
                                    return;
                                }
                            } catch (Throwable ex) {
                                Exceptions.throwOrReport(ex, (Observer<?>) o);
                                return;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable ex2) {
                        Exceptions.throwOrReport(ex2, (Observer<?>) o);
                        return;
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void fastPath() {
            Subscriber<? super T> o = this.f7250o;
            Iterator<? extends T> it = this.f7249it;
            while (!o.isUnsubscribed()) {
                try {
                    o.onNext(it.next());
                    if (!o.isUnsubscribed()) {
                        try {
                            if (!it.hasNext()) {
                                if (!o.isUnsubscribed()) {
                                    o.onCompleted();
                                    return;
                                }
                                return;
                            }
                        } catch (Throwable ex) {
                            Exceptions.throwOrReport(ex, (Observer<?>) o);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable ex2) {
                    Exceptions.throwOrReport(ex2, (Observer<?>) o);
                    return;
                }
            }
        }
    }

    public OnSubscribeFromIterable(Iterable<? extends T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("iterable must not be null");
        }
        this.f7248is = iterable;
    }

    public void call(Subscriber<? super T> o) {
        try {
            Iterator<? extends T> it = this.f7248is.iterator();
            boolean b = it.hasNext();
            if (o.isUnsubscribed()) {
                return;
            }
            if (!b) {
                o.onCompleted();
            } else {
                o.setProducer(new IterableProducer(o, it));
            }
        } catch (Throwable ex) {
            Exceptions.throwOrReport(ex, (Observer<?>) o);
        }
    }
}

package p032rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p032rx.Observable;
import p032rx.Observable.OnSubscribe;
import p032rx.Producer;
import p032rx.Subscriber;
import p032rx.exceptions.Exceptions;
import p032rx.exceptions.MissingBackpressureException;
import p032rx.functions.Func1;
import p032rx.internal.producers.ProducerArbiter;
import p032rx.internal.util.ExceptionsUtils;
import p032rx.internal.util.ScalarSynchronousObservable;
import p032rx.internal.util.atomic.SpscAtomicArrayQueue;
import p032rx.internal.util.unsafe.SpscArrayQueue;
import p032rx.internal.util.unsafe.UnsafeAccess;
import p032rx.observers.SerializedSubscriber;
import p032rx.plugins.RxJavaHooks;
import p032rx.subscriptions.SerialSubscription;

/* renamed from: rx.internal.operators.OnSubscribeConcatMap */
public final class OnSubscribeConcatMap<T, R> implements OnSubscribe<R> {
    final int delayErrorMode;
    final Func1<? super T, ? extends Observable<? extends R>> mapper;
    final int prefetch;
    final Observable<? extends T> source;

    /* renamed from: rx.internal.operators.OnSubscribeConcatMap$ConcatMapInnerScalarProducer */
    static final class ConcatMapInnerScalarProducer<T, R> implements Producer {
        boolean once;
        final ConcatMapSubscriber<T, R> parent;
        final R value;

        public ConcatMapInnerScalarProducer(R value2, ConcatMapSubscriber<T, R> parent2) {
            this.value = value2;
            this.parent = parent2;
        }

        public void request(long n) {
            if (!this.once && n > 0) {
                this.once = true;
                ConcatMapSubscriber<T, R> p = this.parent;
                p.innerNext(this.value);
                p.innerCompleted(1);
            }
        }
    }

    /* renamed from: rx.internal.operators.OnSubscribeConcatMap$ConcatMapInnerSubscriber */
    static final class ConcatMapInnerSubscriber<T, R> extends Subscriber<R> {
        final ConcatMapSubscriber<T, R> parent;
        long produced;

        public ConcatMapInnerSubscriber(ConcatMapSubscriber<T, R> parent2) {
            this.parent = parent2;
        }

        public void setProducer(Producer p) {
            this.parent.arbiter.setProducer(p);
        }

        public void onNext(R t) {
            this.produced++;
            this.parent.innerNext(t);
        }

        public void onError(Throwable e) {
            this.parent.innerError(e, this.produced);
        }

        public void onCompleted() {
            this.parent.innerCompleted(this.produced);
        }
    }

    /* renamed from: rx.internal.operators.OnSubscribeConcatMap$ConcatMapSubscriber */
    static final class ConcatMapSubscriber<T, R> extends Subscriber<T> {
        volatile boolean active;
        final Subscriber<? super R> actual;
        final ProducerArbiter arbiter = new ProducerArbiter();
        final int delayErrorMode;
        volatile boolean done;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final SerialSubscription inner;
        final Func1<? super T, ? extends Observable<? extends R>> mapper;
        final Queue<Object> queue;
        final AtomicInteger wip = new AtomicInteger();

        public ConcatMapSubscriber(Subscriber<? super R> actual2, Func1<? super T, ? extends Observable<? extends R>> mapper2, int prefetch, int delayErrorMode2) {
            Queue<Object> q;
            this.actual = actual2;
            this.mapper = mapper2;
            this.delayErrorMode = delayErrorMode2;
            if (UnsafeAccess.isUnsafeAvailable()) {
                q = new SpscArrayQueue<>(prefetch);
            } else {
                q = new SpscAtomicArrayQueue<>(prefetch);
            }
            this.queue = q;
            this.inner = new SerialSubscription();
            request((long) prefetch);
        }

        public void onNext(T t) {
            if (!this.queue.offer(NotificationLite.next(t))) {
                unsubscribe();
                onError(new MissingBackpressureException());
                return;
            }
            drain();
        }

        public void onError(Throwable mainError) {
            if (ExceptionsUtils.addThrowable(this.error, mainError)) {
                this.done = true;
                if (this.delayErrorMode == 0) {
                    Throwable ex = ExceptionsUtils.terminate(this.error);
                    if (!ExceptionsUtils.isTerminated(ex)) {
                        this.actual.onError(ex);
                    }
                    this.inner.unsubscribe();
                    return;
                }
                drain();
                return;
            }
            pluginError(mainError);
        }

        public void onCompleted() {
            this.done = true;
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void requestMore(long n) {
            if (n > 0) {
                this.arbiter.request(n);
            } else if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerNext(R value) {
            this.actual.onNext(value);
        }

        /* access modifiers changed from: 0000 */
        public void innerError(Throwable innerError, long produced) {
            if (!ExceptionsUtils.addThrowable(this.error, innerError)) {
                pluginError(innerError);
            } else if (this.delayErrorMode == 0) {
                Throwable ex = ExceptionsUtils.terminate(this.error);
                if (!ExceptionsUtils.isTerminated(ex)) {
                    this.actual.onError(ex);
                }
                unsubscribe();
            } else {
                if (produced != 0) {
                    this.arbiter.produced(produced);
                }
                this.active = false;
                drain();
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerCompleted(long produced) {
            if (produced != 0) {
                this.arbiter.produced(produced);
            }
            this.active = false;
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void pluginError(Throwable e) {
            RxJavaHooks.onError(e);
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (this.wip.getAndIncrement() == 0) {
                int delayErrorMode2 = this.delayErrorMode;
                while (!this.actual.isUnsubscribed()) {
                    if (!this.active) {
                        if (delayErrorMode2 != 1 || this.error.get() == null) {
                            boolean mainDone = this.done;
                            Object v = this.queue.poll();
                            boolean empty = v == null;
                            if (mainDone && empty) {
                                Throwable ex = ExceptionsUtils.terminate(this.error);
                                if (ex == null) {
                                    this.actual.onCompleted();
                                    return;
                                } else if (!ExceptionsUtils.isTerminated(ex)) {
                                    this.actual.onError(ex);
                                    return;
                                } else {
                                    return;
                                }
                            } else if (!empty) {
                                try {
                                    Observable<? extends R> source = (Observable) this.mapper.call(NotificationLite.getValue(v));
                                    if (source == null) {
                                        drainError(new NullPointerException("The source returned by the mapper was null"));
                                        return;
                                    } else if (source != Observable.empty()) {
                                        if (source instanceof ScalarSynchronousObservable) {
                                            ScalarSynchronousObservable<? extends R> scalarSource = (ScalarSynchronousObservable) source;
                                            this.active = true;
                                            this.arbiter.setProducer(new ConcatMapInnerScalarProducer(scalarSource.get(), this));
                                        } else {
                                            ConcatMapInnerSubscriber<T, R> innerSubscriber = new ConcatMapInnerSubscriber<>(this);
                                            this.inner.set(innerSubscriber);
                                            if (!innerSubscriber.isUnsubscribed()) {
                                                this.active = true;
                                                source.unsafeSubscribe(innerSubscriber);
                                            } else {
                                                return;
                                            }
                                        }
                                        request(1);
                                    } else {
                                        request(1);
                                    }
                                } catch (Throwable mapperError) {
                                    Exceptions.throwIfFatal(mapperError);
                                    drainError(mapperError);
                                    return;
                                }
                            }
                        } else {
                            Throwable ex2 = ExceptionsUtils.terminate(this.error);
                            if (!ExceptionsUtils.isTerminated(ex2)) {
                                this.actual.onError(ex2);
                                return;
                            }
                            return;
                        }
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainError(Throwable mapperError) {
            unsubscribe();
            if (ExceptionsUtils.addThrowable(this.error, mapperError)) {
                Throwable ex = ExceptionsUtils.terminate(this.error);
                if (!ExceptionsUtils.isTerminated(ex)) {
                    this.actual.onError(ex);
                    return;
                }
                return;
            }
            pluginError(mapperError);
        }
    }

    public OnSubscribeConcatMap(Observable<? extends T> source2, Func1<? super T, ? extends Observable<? extends R>> mapper2, int prefetch2, int delayErrorMode2) {
        this.source = source2;
        this.mapper = mapper2;
        this.prefetch = prefetch2;
        this.delayErrorMode = delayErrorMode2;
    }

    public void call(Subscriber<? super R> child) {
        Subscriber<? super R> s;
        if (this.delayErrorMode == 0) {
            s = new SerializedSubscriber<>(child);
        } else {
            s = child;
        }
        final ConcatMapSubscriber<T, R> parent = new ConcatMapSubscriber<>(s, this.mapper, this.prefetch, this.delayErrorMode);
        child.add(parent);
        child.add(parent.inner);
        child.setProducer(new Producer() {
            public void request(long n) {
                parent.requestMore(n);
            }
        });
        if (!child.isUnsubscribed()) {
            this.source.unsafeSubscribe(parent);
        }
    }
}

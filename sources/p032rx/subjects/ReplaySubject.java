package p032rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p032rx.Observable.OnSubscribe;
import p032rx.Observer;
import p032rx.Producer;
import p032rx.Subscriber;
import p032rx.Subscription;
import p032rx.exceptions.Exceptions;
import p032rx.internal.operators.BackpressureUtils;
import p032rx.plugins.RxJavaHooks;

/* renamed from: rx.subjects.ReplaySubject */
public final class ReplaySubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    final ReplayState<T> state;

    /* renamed from: rx.subjects.ReplaySubject$ReplayBuffer */
    interface ReplayBuffer<T> {
        void complete();

        void drain(ReplayProducer<T> replayProducer);

        void error(Throwable th);

        void next(T t);
    }

    /* renamed from: rx.subjects.ReplaySubject$ReplayProducer */
    static final class ReplayProducer<T> extends AtomicInteger implements Producer, Subscription {
        final Subscriber<? super T> actual;
        int index;
        Object node;
        final AtomicLong requested = new AtomicLong();
        final ReplayState<T> state;
        int tailIndex;

        public ReplayProducer(Subscriber<? super T> actual2, ReplayState<T> state2) {
            this.actual = actual2;
            this.state = state2;
        }

        public void unsubscribe() {
            this.state.remove(this);
        }

        public boolean isUnsubscribed() {
            return this.actual.isUnsubscribed();
        }

        public void request(long n) {
            if (n > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                this.state.buffer.drain(this);
            } else if (n < 0) {
                throw new IllegalArgumentException("n >= required but it was " + n);
            }
        }
    }

    /* renamed from: rx.subjects.ReplaySubject$ReplayState */
    static final class ReplayState<T> extends AtomicReference<ReplayProducer<T>[]> implements OnSubscribe<T>, Observer<T> {
        static final ReplayProducer[] EMPTY = new ReplayProducer[0];
        static final ReplayProducer[] TERMINATED = new ReplayProducer[0];
        final ReplayBuffer<T> buffer;

        public ReplayState(ReplayBuffer<T> buffer2) {
            this.buffer = buffer2;
            lazySet(EMPTY);
        }

        public void call(Subscriber<? super T> t) {
            ReplayProducer<T> rp = new ReplayProducer<>(t, this);
            t.add(rp);
            t.setProducer(rp);
            if (!add(rp) || !rp.isUnsubscribed()) {
                this.buffer.drain(rp);
            } else {
                remove(rp);
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean add(ReplayProducer<T> rp) {
            ReplayProducer<T>[] a;
            ReplayProducer<T>[] b;
            do {
                a = (ReplayProducer[]) get();
                if (a == TERMINATED) {
                    return false;
                }
                int n = a.length;
                b = new ReplayProducer[(n + 1)];
                System.arraycopy(a, 0, b, 0, n);
                b[n] = rp;
            } while (!compareAndSet(a, b));
            return true;
        }

        /* access modifiers changed from: 0000 */
        public void remove(ReplayProducer<T> rp) {
            ReplayProducer<T>[] a;
            ReplayProducer<T>[] b;
            do {
                a = (ReplayProducer[]) get();
                if (a != TERMINATED && a != EMPTY) {
                    int n = a.length;
                    int j = -1;
                    int i = 0;
                    while (true) {
                        if (i >= n) {
                            break;
                        } else if (a[i] == rp) {
                            j = i;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (j < 0) {
                        return;
                    }
                    if (n == 1) {
                        b = EMPTY;
                    } else {
                        b = new ReplayProducer[(n - 1)];
                        System.arraycopy(a, 0, b, 0, j);
                        System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(a, b));
        }

        public void onNext(T t) {
            ReplayBuffer<T> b = this.buffer;
            b.next(t);
            for (ReplayProducer<T> rp : (ReplayProducer[]) get()) {
                b.drain(rp);
            }
        }

        public void onError(Throwable e) {
            ReplayBuffer<T> b = this.buffer;
            b.error(e);
            List<Throwable> errors = null;
            for (ReplayProducer<T> rp : (ReplayProducer[]) getAndSet(TERMINATED)) {
                try {
                    b.drain(rp);
                } catch (Throwable ex) {
                    if (errors == null) {
                        errors = new ArrayList<>();
                    }
                    errors.add(ex);
                }
            }
            Exceptions.throwIfAny(errors);
        }

        public void onCompleted() {
            ReplayBuffer<T> b = this.buffer;
            b.complete();
            for (ReplayProducer<T> rp : (ReplayProducer[]) getAndSet(TERMINATED)) {
                b.drain(rp);
            }
        }
    }

    /* renamed from: rx.subjects.ReplaySubject$ReplayUnboundedBuffer */
    static final class ReplayUnboundedBuffer<T> implements ReplayBuffer<T> {
        final int capacity;
        volatile boolean done;
        Throwable error;
        final Object[] head;
        volatile int size;
        Object[] tail;
        int tailIndex;

        public ReplayUnboundedBuffer(int capacity2) {
            this.capacity = capacity2;
            Object[] objArr = new Object[(capacity2 + 1)];
            this.head = objArr;
            this.tail = objArr;
        }

        public void next(T t) {
            if (!this.done) {
                int i = this.tailIndex;
                Object[] a = this.tail;
                if (i == a.length - 1) {
                    Object[] b = new Object[a.length];
                    b[0] = t;
                    this.tailIndex = 1;
                    a[i] = b;
                    this.tail = b;
                } else {
                    a[i] = t;
                    this.tailIndex = i + 1;
                }
                this.size++;
            }
        }

        public void error(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
                return;
            }
            this.error = e;
            this.done = true;
        }

        public void complete() {
            this.done = true;
        }

        public void drain(ReplayProducer<T> rp) {
            if (rp.getAndIncrement() == 0) {
                int missed = 1;
                Subscriber<? super T> a = rp.actual;
                int n = this.capacity;
                do {
                    long r = rp.requested.get();
                    long e = 0;
                    Object[] node = (Object[]) rp.node;
                    if (node == null) {
                        node = this.head;
                    }
                    int tailIndex2 = rp.tailIndex;
                    int index = rp.index;
                    while (e != r) {
                        if (a.isUnsubscribed()) {
                            rp.node = null;
                            return;
                        }
                        boolean d = this.done;
                        boolean empty = index == this.size;
                        if (d && empty) {
                            rp.node = null;
                            Throwable ex = this.error;
                            if (ex != null) {
                                a.onError(ex);
                                return;
                            } else {
                                a.onCompleted();
                                return;
                            }
                        } else if (empty) {
                            break;
                        } else {
                            if (tailIndex2 == n) {
                                node = (Object[]) node[tailIndex2];
                                tailIndex2 = 0;
                            }
                            a.onNext(node[tailIndex2]);
                            e++;
                            tailIndex2++;
                            index++;
                        }
                    }
                    if (e == r) {
                        if (a.isUnsubscribed()) {
                            rp.node = null;
                            return;
                        }
                        boolean d2 = this.done;
                        boolean empty2 = index == this.size;
                        if (d2 && empty2) {
                            rp.node = null;
                            Throwable ex2 = this.error;
                            if (ex2 != null) {
                                a.onError(ex2);
                                return;
                            } else {
                                a.onCompleted();
                                return;
                            }
                        }
                    }
                    if (!(e == 0 || r == Long.MAX_VALUE)) {
                        BackpressureUtils.produced(rp.requested, e);
                    }
                    rp.index = index;
                    rp.tailIndex = tailIndex2;
                    rp.node = node;
                    missed = rp.addAndGet(-missed);
                } while (missed != 0);
            }
        }
    }

    public static <T> ReplaySubject<T> create() {
        return create(16);
    }

    public static <T> ReplaySubject<T> create(int capacity) {
        if (capacity > 0) {
            return new ReplaySubject<>(new ReplayState<>(new ReplayUnboundedBuffer<>(capacity)));
        }
        throw new IllegalArgumentException("capacity > 0 required but it was " + capacity);
    }

    ReplaySubject(ReplayState<T> state2) {
        super(state2);
        this.state = state2;
    }

    public void onNext(T t) {
        this.state.onNext(t);
    }

    public void onError(Throwable e) {
        this.state.onError(e);
    }

    public void onCompleted() {
        this.state.onCompleted();
    }
}

package p032rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p032rx.Observable.OnSubscribe;
import p032rx.Observer;
import p032rx.Producer;
import p032rx.Subscriber;
import p032rx.Subscription;
import p032rx.exceptions.Exceptions;
import p032rx.exceptions.MissingBackpressureException;
import p032rx.internal.operators.BackpressureUtils;

/* renamed from: rx.subjects.PublishSubject */
public final class PublishSubject<T> extends Subject<T, T> {
    final PublishSubjectState<T> state;

    /* renamed from: rx.subjects.PublishSubject$PublishSubjectProducer */
    static final class PublishSubjectProducer<T> extends AtomicLong implements Observer<T>, Producer, Subscription {
        final Subscriber<? super T> actual;
        final PublishSubjectState<T> parent;
        long produced;

        public PublishSubjectProducer(PublishSubjectState<T> parent2, Subscriber<? super T> actual2) {
            this.parent = parent2;
            this.actual = actual2;
        }

        public void request(long n) {
            long r;
            if (BackpressureUtils.validate(n)) {
                do {
                    r = get();
                    if (r == Long.MIN_VALUE) {
                        return;
                    }
                } while (!compareAndSet(r, BackpressureUtils.addCap(r, n)));
            }
        }

        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        public void unsubscribe() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
            }
        }

        public void onNext(T t) {
            long r = get();
            if (r != Long.MIN_VALUE) {
                long p = this.produced;
                if (r != p) {
                    this.produced = 1 + p;
                    this.actual.onNext(t);
                    return;
                }
                unsubscribe();
                this.actual.onError(new MissingBackpressureException("PublishSubject: could not emit value due to lack of requests"));
            }
        }

        public void onError(Throwable e) {
            if (get() != Long.MIN_VALUE) {
                this.actual.onError(e);
            }
        }

        public void onCompleted() {
            if (get() != Long.MIN_VALUE) {
                this.actual.onCompleted();
            }
        }
    }

    /* renamed from: rx.subjects.PublishSubject$PublishSubjectState */
    static final class PublishSubjectState<T> extends AtomicReference<PublishSubjectProducer<T>[]> implements OnSubscribe<T>, Observer<T> {
        static final PublishSubjectProducer[] EMPTY = new PublishSubjectProducer[0];
        static final PublishSubjectProducer[] TERMINATED = new PublishSubjectProducer[0];
        Throwable error;

        public PublishSubjectState() {
            lazySet(EMPTY);
        }

        public void call(Subscriber<? super T> t) {
            PublishSubjectProducer<T> pp = new PublishSubjectProducer<>(this, t);
            t.add(pp);
            t.setProducer(pp);
            if (!add(pp)) {
                Throwable ex = this.error;
                if (ex != null) {
                    t.onError(ex);
                } else {
                    t.onCompleted();
                }
            } else if (pp.isUnsubscribed()) {
                remove(pp);
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean add(PublishSubjectProducer<T> inner) {
            PublishSubjectProducer<T>[] curr;
            PublishSubjectProducer<T>[] next;
            do {
                curr = (PublishSubjectProducer[]) get();
                if (curr == TERMINATED) {
                    return false;
                }
                int n = curr.length;
                next = new PublishSubjectProducer[(n + 1)];
                System.arraycopy(curr, 0, next, 0, n);
                next[n] = inner;
            } while (!compareAndSet(curr, next));
            return true;
        }

        /* access modifiers changed from: 0000 */
        public void remove(PublishSubjectProducer<T> inner) {
            PublishSubjectProducer<T>[] curr;
            PublishSubjectProducer<T>[] next;
            do {
                curr = (PublishSubjectProducer[]) get();
                if (curr != TERMINATED && curr != EMPTY) {
                    int n = curr.length;
                    int j = -1;
                    int i = 0;
                    while (true) {
                        if (i >= n) {
                            break;
                        } else if (curr[i] == inner) {
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
                        next = EMPTY;
                    } else {
                        next = new PublishSubjectProducer[(n - 1)];
                        System.arraycopy(curr, 0, next, 0, j);
                        System.arraycopy(curr, j + 1, next, j, (n - j) - 1);
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(curr, next));
        }

        public void onNext(T t) {
            for (PublishSubjectProducer<T> pp : (PublishSubjectProducer[]) get()) {
                pp.onNext(t);
            }
        }

        public void onError(Throwable e) {
            this.error = e;
            List<Throwable> errors = null;
            for (PublishSubjectProducer<T> pp : (PublishSubjectProducer[]) getAndSet(TERMINATED)) {
                try {
                    pp.onError(e);
                } catch (Throwable ex) {
                    if (errors == null) {
                        errors = new ArrayList<>(1);
                    }
                    errors.add(ex);
                }
            }
            Exceptions.throwIfAny(errors);
        }

        public void onCompleted() {
            for (PublishSubjectProducer<T> pp : (PublishSubjectProducer[]) getAndSet(TERMINATED)) {
                pp.onCompleted();
            }
        }
    }

    public static <T> PublishSubject<T> create() {
        return new PublishSubject<>(new PublishSubjectState());
    }

    protected PublishSubject(PublishSubjectState<T> state2) {
        super(state2);
        this.state = state2;
    }

    public void onNext(T v) {
        this.state.onNext(v);
    }

    public void onError(Throwable e) {
        this.state.onError(e);
    }

    public void onCompleted() {
        this.state.onCompleted();
    }
}

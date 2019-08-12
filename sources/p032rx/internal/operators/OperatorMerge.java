package p032rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import p032rx.Observable;
import p032rx.Observable.Operator;
import p032rx.Producer;
import p032rx.Subscriber;
import p032rx.exceptions.CompositeException;
import p032rx.exceptions.MissingBackpressureException;
import p032rx.exceptions.OnErrorThrowable;
import p032rx.internal.util.RxRingBuffer;
import p032rx.internal.util.ScalarSynchronousObservable;
import p032rx.internal.util.atomic.SpscAtomicArrayQueue;
import p032rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import p032rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import p032rx.internal.util.unsafe.Pow2;
import p032rx.internal.util.unsafe.SpscArrayQueue;
import p032rx.internal.util.unsafe.UnsafeAccess;
import p032rx.subscriptions.CompositeSubscription;

/* renamed from: rx.internal.operators.OperatorMerge */
public final class OperatorMerge<T> implements Operator<T, Observable<? extends T>> {
    final boolean delayErrors;
    final int maxConcurrent;

    /* renamed from: rx.internal.operators.OperatorMerge$HolderDelayErrors */
    static final class HolderDelayErrors {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(true, Integer.MAX_VALUE);
    }

    /* renamed from: rx.internal.operators.OperatorMerge$HolderNoDelay */
    static final class HolderNoDelay {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(false, Integer.MAX_VALUE);
    }

    /* renamed from: rx.internal.operators.OperatorMerge$InnerSubscriber */
    static final class InnerSubscriber<T> extends Subscriber<T> {
        static final int LIMIT = (RxRingBuffer.SIZE / 4);
        volatile boolean done;

        /* renamed from: id */
        final long f7251id;
        int outstanding;
        final MergeSubscriber<T> parent;
        volatile RxRingBuffer queue;

        public InnerSubscriber(MergeSubscriber<T> parent2, long id) {
            this.parent = parent2;
            this.f7251id = id;
        }

        public void onStart() {
            this.outstanding = RxRingBuffer.SIZE;
            request((long) RxRingBuffer.SIZE);
        }

        public void onNext(T t) {
            this.parent.tryEmit(this, t);
        }

        public void onError(Throwable e) {
            this.done = true;
            this.parent.getOrCreateErrorQueue().offer(e);
            this.parent.emit();
        }

        public void onCompleted() {
            this.done = true;
            this.parent.emit();
        }

        public void requestMore(long n) {
            int r = this.outstanding - ((int) n);
            if (r > LIMIT) {
                this.outstanding = r;
                return;
            }
            this.outstanding = RxRingBuffer.SIZE;
            int k = RxRingBuffer.SIZE - r;
            if (k > 0) {
                request((long) k);
            }
        }
    }

    /* renamed from: rx.internal.operators.OperatorMerge$MergeProducer */
    static final class MergeProducer<T> extends AtomicLong implements Producer {
        final MergeSubscriber<T> subscriber;

        public MergeProducer(MergeSubscriber<T> subscriber2) {
            this.subscriber = subscriber2;
        }

        public void request(long n) {
            if (n > 0) {
                if (get() != Long.MAX_VALUE) {
                    BackpressureUtils.getAndAddRequest(this, n);
                    this.subscriber.emit();
                }
            } else if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
        }

        public long produced(int n) {
            return addAndGet((long) (-n));
        }
    }

    /* renamed from: rx.internal.operators.OperatorMerge$MergeSubscriber */
    static final class MergeSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final InnerSubscriber<?>[] EMPTY = new InnerSubscriber[0];
        final Subscriber<? super T> child;
        final boolean delayErrors;
        volatile boolean done;
        boolean emitting;
        volatile ConcurrentLinkedQueue<Throwable> errors;
        final Object innerGuard = new Object();
        volatile InnerSubscriber<?>[] innerSubscribers = EMPTY;
        long lastId;
        int lastIndex;
        final int maxConcurrent;
        boolean missed;
        MergeProducer<T> producer;
        volatile Queue<Object> queue;
        int scalarEmissionCount;
        final int scalarEmissionLimit;
        volatile CompositeSubscription subscriptions;
        long uniqueId;

        public MergeSubscriber(Subscriber<? super T> child2, boolean delayErrors2, int maxConcurrent2) {
            this.child = child2;
            this.delayErrors = delayErrors2;
            this.maxConcurrent = maxConcurrent2;
            if (maxConcurrent2 == Integer.MAX_VALUE) {
                this.scalarEmissionLimit = Integer.MAX_VALUE;
                request(Long.MAX_VALUE);
                return;
            }
            this.scalarEmissionLimit = Math.max(1, maxConcurrent2 >> 1);
            request((long) maxConcurrent2);
        }

        /* access modifiers changed from: 0000 */
        public Queue<Throwable> getOrCreateErrorQueue() {
            ConcurrentLinkedQueue<Throwable> q = this.errors;
            if (q == null) {
                synchronized (this) {
                    try {
                        q = this.errors;
                        if (q == null) {
                            ConcurrentLinkedQueue<Throwable> q2 = new ConcurrentLinkedQueue<>();
                            try {
                                this.errors = q2;
                                q = q2;
                            } catch (Throwable th) {
                                th = th;
                                ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = q2;
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
            }
            return q;
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0014, code lost:
            if (r2 == false) goto L_0x0019;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0016, code lost:
            add(r0);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public p032rx.subscriptions.CompositeSubscription getOrCreateComposite() {
            /*
                r4 = this;
                rx.subscriptions.CompositeSubscription r0 = r4.subscriptions
                if (r0 != 0) goto L_0x0019
                r2 = 0
                monitor-enter(r4)
                rx.subscriptions.CompositeSubscription r0 = r4.subscriptions     // Catch:{ all -> 0x001a }
                if (r0 != 0) goto L_0x0013
                rx.subscriptions.CompositeSubscription r1 = new rx.subscriptions.CompositeSubscription     // Catch:{ all -> 0x001a }
                r1.<init>()     // Catch:{ all -> 0x001a }
                r4.subscriptions = r1     // Catch:{ all -> 0x001d }
                r2 = 1
                r0 = r1
            L_0x0013:
                monitor-exit(r4)     // Catch:{ all -> 0x001a }
                if (r2 == 0) goto L_0x0019
                r4.add(r0)
            L_0x0019:
                return r0
            L_0x001a:
                r3 = move-exception
            L_0x001b:
                monitor-exit(r4)     // Catch:{ all -> 0x001a }
                throw r3
            L_0x001d:
                r3 = move-exception
                r0 = r1
                goto L_0x001b
            */
            throw new UnsupportedOperationException("Method not decompiled: p032rx.internal.operators.OperatorMerge.MergeSubscriber.getOrCreateComposite():rx.subscriptions.CompositeSubscription");
        }

        public void onNext(Observable<? extends T> t) {
            if (t != null) {
                if (t == Observable.empty()) {
                    emitEmpty();
                } else if (t instanceof ScalarSynchronousObservable) {
                    tryEmit(((ScalarSynchronousObservable) t).get());
                } else {
                    long j = this.uniqueId;
                    this.uniqueId = 1 + j;
                    InnerSubscriber<T> inner = new InnerSubscriber<>(this, j);
                    addInner(inner);
                    t.unsafeSubscribe(inner);
                    emit();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void emitEmpty() {
            int produced = this.scalarEmissionCount + 1;
            if (produced == this.scalarEmissionLimit) {
                this.scalarEmissionCount = 0;
                requestMore((long) produced);
                return;
            }
            this.scalarEmissionCount = produced;
        }

        private void reportError() {
            List<Throwable> list = new ArrayList<>(this.errors);
            if (list.size() == 1) {
                this.child.onError((Throwable) list.get(0));
            } else {
                this.child.onError(new CompositeException((Collection<? extends Throwable>) list));
            }
        }

        public void onError(Throwable e) {
            getOrCreateErrorQueue().offer(e);
            this.done = true;
            emit();
        }

        public void onCompleted() {
            this.done = true;
            emit();
        }

        /* access modifiers changed from: 0000 */
        public void addInner(InnerSubscriber<T> inner) {
            getOrCreateComposite().add(inner);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] a = this.innerSubscribers;
                int n = a.length;
                InnerSubscriber<?>[] b = new InnerSubscriber[(n + 1)];
                System.arraycopy(a, 0, b, 0, n);
                b[n] = inner;
                this.innerSubscribers = b;
            }
        }

        /* access modifiers changed from: 0000 */
        public void removeInner(InnerSubscriber<T> inner) {
            RxRingBuffer q = inner.queue;
            if (q != null) {
                q.release();
            }
            this.subscriptions.remove(inner);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] a = this.innerSubscribers;
                int n = a.length;
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    } else if (inner.equals(a[i])) {
                        j = i;
                        break;
                    } else {
                        i++;
                    }
                }
                if (j >= 0) {
                    if (n == 1) {
                        this.innerSubscribers = EMPTY;
                        return;
                    }
                    InnerSubscriber<?>[] b = new InnerSubscriber[(n - 1)];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                    this.innerSubscribers = b;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void tryEmit(InnerSubscriber<T> subscriber, T value) {
            boolean success = false;
            long r = this.producer.get();
            if (r != 0) {
                synchronized (this) {
                    r = this.producer.get();
                    if (!this.emitting && r != 0) {
                        this.emitting = true;
                        success = true;
                    }
                }
            }
            if (success) {
                RxRingBuffer subscriberQueue = subscriber.queue;
                if (subscriberQueue == null || subscriberQueue.isEmpty()) {
                    emitScalar(subscriber, value, r);
                    return;
                }
                queueScalar(subscriber, value);
                emitLoop();
                return;
            }
            queueScalar(subscriber, value);
            emit();
        }

        /* access modifiers changed from: protected */
        public void queueScalar(InnerSubscriber<T> subscriber, T value) {
            RxRingBuffer q = subscriber.queue;
            if (q == null) {
                q = RxRingBuffer.getSpscInstance();
                subscriber.add(q);
                subscriber.queue = q;
            }
            try {
                q.onNext(NotificationLite.next(value));
            } catch (MissingBackpressureException ex) {
                subscriber.unsubscribe();
                subscriber.onError(ex);
            } catch (IllegalStateException ex2) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.unsubscribe();
                    subscriber.onError(ex2);
                }
            }
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
            if (1 != 0) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
            monitor-enter(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            r5.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x002a, code lost:
            monitor-exit(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x005e, code lost:
            if (1 != 0) goto L_0x0065;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0060, code lost:
            monitor-enter(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
            r5.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x0064, code lost:
            monitor-exit(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x0065, code lost:
            emitLoop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void emitScalar(p032rx.internal.operators.OperatorMerge.InnerSubscriber<T> r6, T r7, long r8) {
            /*
                r5 = this;
                r0 = 0
                rx.Subscriber<? super T> r2 = r5.child     // Catch:{ Throwable -> 0x002c }
                r2.onNext(r7)     // Catch:{ Throwable -> 0x002c }
            L_0x0006:
                r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                if (r2 == 0) goto L_0x0015
                rx.internal.operators.OperatorMerge$MergeProducer<T> r2 = r5.producer     // Catch:{ all -> 0x004e }
                r3 = 1
                r2.produced(r3)     // Catch:{ all -> 0x004e }
            L_0x0015:
                r2 = 1
                r6.requestMore(r2)     // Catch:{ all -> 0x004e }
                monitor-enter(r5)     // Catch:{ all -> 0x004e }
                r0 = 1
                boolean r2 = r5.missed     // Catch:{ all -> 0x0069 }
                if (r2 != 0) goto L_0x005a
                r2 = 0
                r5.emitting = r2     // Catch:{ all -> 0x0069 }
                monitor-exit(r5)     // Catch:{ all -> 0x0069 }
                if (r0 != 0) goto L_0x002b
                monitor-enter(r5)
                r2 = 0
                r5.emitting = r2     // Catch:{ all -> 0x0057 }
                monitor-exit(r5)     // Catch:{ all -> 0x0057 }
            L_0x002b:
                return
            L_0x002c:
                r1 = move-exception
                boolean r2 = r5.delayErrors     // Catch:{ all -> 0x004e }
                if (r2 != 0) goto L_0x0046
                p032rx.exceptions.Exceptions.throwIfFatal(r1)     // Catch:{ all -> 0x004e }
                r0 = 1
                r6.unsubscribe()     // Catch:{ all -> 0x004e }
                r6.onError(r1)     // Catch:{ all -> 0x004e }
                if (r0 != 0) goto L_0x002b
                monitor-enter(r5)
                r2 = 0
                r5.emitting = r2     // Catch:{ all -> 0x0043 }
                monitor-exit(r5)     // Catch:{ all -> 0x0043 }
                goto L_0x002b
            L_0x0043:
                r2 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x0043 }
                throw r2
            L_0x0046:
                java.util.Queue r2 = r5.getOrCreateErrorQueue()     // Catch:{ all -> 0x004e }
                r2.offer(r1)     // Catch:{ all -> 0x004e }
                goto L_0x0006
            L_0x004e:
                r2 = move-exception
                if (r0 != 0) goto L_0x0056
                monitor-enter(r5)
                r3 = 0
                r5.emitting = r3     // Catch:{ all -> 0x006f }
                monitor-exit(r5)     // Catch:{ all -> 0x006f }
            L_0x0056:
                throw r2
            L_0x0057:
                r2 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x0057 }
                throw r2
            L_0x005a:
                r2 = 0
                r5.missed = r2     // Catch:{ all -> 0x0069 }
                monitor-exit(r5)     // Catch:{ all -> 0x0069 }
                if (r0 != 0) goto L_0x0065
                monitor-enter(r5)
                r2 = 0
                r5.emitting = r2     // Catch:{ all -> 0x006c }
                monitor-exit(r5)     // Catch:{ all -> 0x006c }
            L_0x0065:
                r5.emitLoop()
                goto L_0x002b
            L_0x0069:
                r2 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x0069 }
                throw r2     // Catch:{ all -> 0x004e }
            L_0x006c:
                r2 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x006c }
                throw r2
            L_0x006f:
                r2 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x006f }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: p032rx.internal.operators.OperatorMerge.MergeSubscriber.emitScalar(rx.internal.operators.OperatorMerge$InnerSubscriber, java.lang.Object, long):void");
        }

        public void requestMore(long n) {
            request(n);
        }

        /* access modifiers changed from: 0000 */
        public void tryEmit(T value) {
            boolean success = false;
            long r = this.producer.get();
            if (r != 0) {
                synchronized (this) {
                    r = this.producer.get();
                    if (!this.emitting && r != 0) {
                        this.emitting = true;
                        success = true;
                    }
                }
            }
            if (success) {
                Queue<Object> mainQueue = this.queue;
                if (mainQueue == null || mainQueue.isEmpty()) {
                    emitScalar(value, r);
                    return;
                }
                queueScalar(value);
                emitLoop();
                return;
            }
            queueScalar(value);
            emit();
        }

        /* access modifiers changed from: protected */
        public void queueScalar(T value) {
            Queue<Object> q = this.queue;
            if (q == null) {
                int mc = this.maxConcurrent;
                if (mc == Integer.MAX_VALUE) {
                    q = new SpscUnboundedAtomicArrayQueue<>(RxRingBuffer.SIZE);
                } else if (!Pow2.isPowerOfTwo(mc)) {
                    q = new SpscExactAtomicArrayQueue<>(mc);
                } else if (UnsafeAccess.isUnsafeAvailable()) {
                    q = new SpscArrayQueue<>(mc);
                } else {
                    q = new SpscAtomicArrayQueue<>(mc);
                }
                this.queue = q;
            }
            if (!q.offer(NotificationLite.next(value))) {
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), value));
            }
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
            if (1 != 0) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
            monitor-enter(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            r6.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0034, code lost:
            monitor-exit(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x006b, code lost:
            if (1 != 0) goto L_0x0072;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x006d, code lost:
            monitor-enter(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
            r6.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x0071, code lost:
            monitor-exit(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x0072, code lost:
            emitLoop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:91:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void emitScalar(T r7, long r8) {
            /*
                r6 = this;
                r1 = 0
                rx.Subscriber<? super T> r3 = r6.child     // Catch:{ Throwable -> 0x0036 }
                r3.onNext(r7)     // Catch:{ Throwable -> 0x0036 }
            L_0x0006:
                r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r3 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
                if (r3 == 0) goto L_0x0015
                rx.internal.operators.OperatorMerge$MergeProducer<T> r3 = r6.producer     // Catch:{ all -> 0x0058 }
                r4 = 1
                r3.produced(r4)     // Catch:{ all -> 0x0058 }
            L_0x0015:
                int r3 = r6.scalarEmissionCount     // Catch:{ all -> 0x0058 }
                int r0 = r3 + 1
                int r3 = r6.scalarEmissionLimit     // Catch:{ all -> 0x0058 }
                if (r0 != r3) goto L_0x0061
                r3 = 0
                r6.scalarEmissionCount = r3     // Catch:{ all -> 0x0058 }
                long r4 = (long) r0     // Catch:{ all -> 0x0058 }
                r6.requestMore(r4)     // Catch:{ all -> 0x0058 }
            L_0x0024:
                monitor-enter(r6)     // Catch:{ all -> 0x0058 }
                r1 = 1
                boolean r3 = r6.missed     // Catch:{ all -> 0x0076 }
                if (r3 != 0) goto L_0x0067
                r3 = 0
                r6.emitting = r3     // Catch:{ all -> 0x0076 }
                monitor-exit(r6)     // Catch:{ all -> 0x0076 }
                if (r1 != 0) goto L_0x0035
                monitor-enter(r6)
                r3 = 0
                r6.emitting = r3     // Catch:{ all -> 0x0064 }
                monitor-exit(r6)     // Catch:{ all -> 0x0064 }
            L_0x0035:
                return
            L_0x0036:
                r2 = move-exception
                boolean r3 = r6.delayErrors     // Catch:{ all -> 0x0058 }
                if (r3 != 0) goto L_0x0050
                p032rx.exceptions.Exceptions.throwIfFatal(r2)     // Catch:{ all -> 0x0058 }
                r1 = 1
                r6.unsubscribe()     // Catch:{ all -> 0x0058 }
                r6.onError(r2)     // Catch:{ all -> 0x0058 }
                if (r1 != 0) goto L_0x0035
                monitor-enter(r6)
                r3 = 0
                r6.emitting = r3     // Catch:{ all -> 0x004d }
                monitor-exit(r6)     // Catch:{ all -> 0x004d }
                goto L_0x0035
            L_0x004d:
                r3 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x004d }
                throw r3
            L_0x0050:
                java.util.Queue r3 = r6.getOrCreateErrorQueue()     // Catch:{ all -> 0x0058 }
                r3.offer(r2)     // Catch:{ all -> 0x0058 }
                goto L_0x0006
            L_0x0058:
                r3 = move-exception
                if (r1 != 0) goto L_0x0060
                monitor-enter(r6)
                r4 = 0
                r6.emitting = r4     // Catch:{ all -> 0x007c }
                monitor-exit(r6)     // Catch:{ all -> 0x007c }
            L_0x0060:
                throw r3
            L_0x0061:
                r6.scalarEmissionCount = r0     // Catch:{ all -> 0x0058 }
                goto L_0x0024
            L_0x0064:
                r3 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x0064 }
                throw r3
            L_0x0067:
                r3 = 0
                r6.missed = r3     // Catch:{ all -> 0x0076 }
                monitor-exit(r6)     // Catch:{ all -> 0x0076 }
                if (r1 != 0) goto L_0x0072
                monitor-enter(r6)
                r3 = 0
                r6.emitting = r3     // Catch:{ all -> 0x0079 }
                monitor-exit(r6)     // Catch:{ all -> 0x0079 }
            L_0x0072:
                r6.emitLoop()
                goto L_0x0035
            L_0x0076:
                r3 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x0076 }
                throw r3     // Catch:{ all -> 0x0058 }
            L_0x0079:
                r3 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x0079 }
                throw r3
            L_0x007c:
                r3 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x007c }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: p032rx.internal.operators.OperatorMerge.MergeSubscriber.emitScalar(java.lang.Object, long):void");
        }

        /* access modifiers changed from: 0000 */
        public void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                emitLoop();
            }
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:230:0x027f, code lost:
            if (1 != 0) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:231:0x0281, code lost:
            monitor-enter(r32);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:234:?, code lost:
            r32.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:235:0x028a, code lost:
            monitor-exit(r32);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:306:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:307:?, code lost:
            return;
         */
        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void emitLoop() {
            /*
                r32 = this;
                r23 = 0
                r0 = r32
                rx.Subscriber<? super T> r4 = r0.child     // Catch:{ all -> 0x00fc }
            L_0x0006:
                boolean r30 = r32.checkTerminate()     // Catch:{ all -> 0x00fc }
                if (r30 == 0) goto L_0x001e
                r23 = 1
                if (r23 != 0) goto L_0x001a
                monitor-enter(r32)
                r30 = 0
                r0 = r30
                r1 = r32
                r1.emitting = r0     // Catch:{ all -> 0x001b }
                monitor-exit(r32)     // Catch:{ all -> 0x001b }
            L_0x001a:
                return
            L_0x001b:
                r30 = move-exception
                monitor-exit(r32)     // Catch:{ all -> 0x001b }
                throw r30
            L_0x001e:
                r0 = r32
                java.util.Queue<java.lang.Object> r0 = r0.queue     // Catch:{ all -> 0x00fc }
                r26 = r0
                r0 = r32
                rx.internal.operators.OperatorMerge$MergeProducer<T> r0 = r0.producer     // Catch:{ all -> 0x00fc }
                r30 = r0
                long r20 = r30.get()     // Catch:{ all -> 0x00fc }
                r30 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r30 = (r20 > r30 ? 1 : (r20 == r30 ? 0 : -1))
                if (r30 != 0) goto L_0x0063
                r28 = 1
            L_0x0039:
                r19 = 0
                if (r26 == 0) goto L_0x0079
            L_0x003d:
                r22 = 0
                r16 = 0
            L_0x0041:
                r30 = 0
                int r30 = (r20 > r30 ? 1 : (r20 == r30 ? 0 : -1))
                if (r30 <= 0) goto L_0x0068
                java.lang.Object r16 = r26.poll()     // Catch:{ all -> 0x00fc }
                boolean r30 = r32.checkTerminate()     // Catch:{ all -> 0x00fc }
                if (r30 == 0) goto L_0x0066
                r23 = 1
                if (r23 != 0) goto L_0x001a
                monitor-enter(r32)
                r30 = 0
                r0 = r30
                r1 = r32
                r1.emitting = r0     // Catch:{ all -> 0x0060 }
                monitor-exit(r32)     // Catch:{ all -> 0x0060 }
                goto L_0x001a
            L_0x0060:
                r30 = move-exception
                monitor-exit(r32)     // Catch:{ all -> 0x0060 }
                throw r30
            L_0x0063:
                r28 = 0
                goto L_0x0039
            L_0x0066:
                if (r16 != 0) goto L_0x00b6
            L_0x0068:
                if (r22 <= 0) goto L_0x0071
                if (r28 == 0) goto L_0x010a
                r20 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            L_0x0071:
                r30 = 0
                int r30 = (r20 > r30 ? 1 : (r20 == r30 ? 0 : -1))
                if (r30 == 0) goto L_0x0079
                if (r16 != 0) goto L_0x003d
            L_0x0079:
                r0 = r32
                boolean r5 = r0.done     // Catch:{ all -> 0x00fc }
                r0 = r32
                java.util.Queue<java.lang.Object> r0 = r0.queue     // Catch:{ all -> 0x00fc }
                r26 = r0
                r0 = r32
                rx.internal.operators.OperatorMerge$InnerSubscriber<?>[] r9 = r0.innerSubscribers     // Catch:{ all -> 0x00fc }
                int r15 = r9.length     // Catch:{ all -> 0x00fc }
                if (r5 == 0) goto L_0x011e
                if (r26 == 0) goto L_0x0092
                boolean r30 = r26.isEmpty()     // Catch:{ all -> 0x00fc }
                if (r30 == 0) goto L_0x011e
            L_0x0092:
                if (r15 != 0) goto L_0x011e
                r0 = r32
                java.util.concurrent.ConcurrentLinkedQueue<java.lang.Throwable> r6 = r0.errors     // Catch:{ all -> 0x00fc }
                if (r6 == 0) goto L_0x00a0
                boolean r30 = r6.isEmpty()     // Catch:{ all -> 0x00fc }
                if (r30 == 0) goto L_0x011a
            L_0x00a0:
                r4.onCompleted()     // Catch:{ all -> 0x00fc }
            L_0x00a3:
                r23 = 1
                if (r23 != 0) goto L_0x001a
                monitor-enter(r32)
                r30 = 0
                r0 = r30
                r1 = r32
                r1.emitting = r0     // Catch:{ all -> 0x00b3 }
                monitor-exit(r32)     // Catch:{ all -> 0x00b3 }
                goto L_0x001a
            L_0x00b3:
                r30 = move-exception
                monitor-exit(r32)     // Catch:{ all -> 0x00b3 }
                throw r30
            L_0x00b6:
                java.lang.Object r29 = p032rx.internal.operators.NotificationLite.getValue(r16)     // Catch:{ all -> 0x00fc }
                r0 = r29
                r4.onNext(r0)     // Catch:{ Throwable -> 0x00c9 }
            L_0x00bf:
                int r19 = r19 + 1
                int r22 = r22 + 1
                r30 = 1
                long r20 = r20 - r30
                goto L_0x0041
            L_0x00c9:
                r27 = move-exception
                r0 = r32
                boolean r0 = r0.delayErrors     // Catch:{ all -> 0x00fc }
                r30 = r0
                if (r30 != 0) goto L_0x00f0
                p032rx.exceptions.Exceptions.throwIfFatal(r27)     // Catch:{ all -> 0x00fc }
                r23 = 1
                r32.unsubscribe()     // Catch:{ all -> 0x00fc }
                r0 = r27
                r4.onError(r0)     // Catch:{ all -> 0x00fc }
                if (r23 != 0) goto L_0x001a
                monitor-enter(r32)
                r30 = 0
                r0 = r30
                r1 = r32
                r1.emitting = r0     // Catch:{ all -> 0x00ed }
                monitor-exit(r32)     // Catch:{ all -> 0x00ed }
                goto L_0x001a
            L_0x00ed:
                r30 = move-exception
                monitor-exit(r32)     // Catch:{ all -> 0x00ed }
                throw r30
            L_0x00f0:
                java.util.Queue r30 = r32.getOrCreateErrorQueue()     // Catch:{ all -> 0x00fc }
                r0 = r30
                r1 = r27
                r0.offer(r1)     // Catch:{ all -> 0x00fc }
                goto L_0x00bf
            L_0x00fc:
                r30 = move-exception
                if (r23 != 0) goto L_0x0109
                monitor-enter(r32)
                r31 = 0
                r0 = r31
                r1 = r32
                r1.emitting = r0     // Catch:{ all -> 0x02a7 }
                monitor-exit(r32)     // Catch:{ all -> 0x02a7 }
            L_0x0109:
                throw r30
            L_0x010a:
                r0 = r32
                rx.internal.operators.OperatorMerge$MergeProducer<T> r0 = r0.producer     // Catch:{ all -> 0x00fc }
                r30 = r0
                r0 = r30
                r1 = r22
                long r20 = r0.produced(r1)     // Catch:{ all -> 0x00fc }
                goto L_0x0071
            L_0x011a:
                r32.reportError()     // Catch:{ all -> 0x00fc }
                goto L_0x00a3
            L_0x011e:
                r10 = 0
                if (r15 <= 0) goto L_0x025b
                r0 = r32
                long r0 = r0.lastId     // Catch:{ all -> 0x00fc }
                r24 = r0
                r0 = r32
                int r8 = r0.lastIndex     // Catch:{ all -> 0x00fc }
                if (r15 <= r8) goto L_0x0139
                r30 = r9[r8]     // Catch:{ all -> 0x00fc }
                r0 = r30
                long r0 = r0.f7251id     // Catch:{ all -> 0x00fc }
                r30 = r0
                int r30 = (r30 > r24 ? 1 : (r30 == r24 ? 0 : -1))
                if (r30 == 0) goto L_0x015f
            L_0x0139:
                if (r15 > r8) goto L_0x013c
                r8 = 0
            L_0x013c:
                r14 = r8
                r7 = 0
            L_0x013e:
                if (r7 >= r15) goto L_0x014c
                r30 = r9[r14]     // Catch:{ all -> 0x00fc }
                r0 = r30
                long r0 = r0.f7251id     // Catch:{ all -> 0x00fc }
                r30 = r0
                int r30 = (r30 > r24 ? 1 : (r30 == r24 ? 0 : -1))
                if (r30 != 0) goto L_0x017c
            L_0x014c:
                r8 = r14
                r0 = r32
                r0.lastIndex = r14     // Catch:{ all -> 0x00fc }
                r30 = r9[r14]     // Catch:{ all -> 0x00fc }
                r0 = r30
                long r0 = r0.f7251id     // Catch:{ all -> 0x00fc }
                r30 = r0
                r0 = r30
                r2 = r32
                r2.lastId = r0     // Catch:{ all -> 0x00fc }
            L_0x015f:
                r14 = r8
                r7 = 0
            L_0x0161:
                if (r7 >= r15) goto L_0x0249
                boolean r30 = r32.checkTerminate()     // Catch:{ all -> 0x00fc }
                if (r30 == 0) goto L_0x0184
                r23 = 1
                if (r23 != 0) goto L_0x001a
                monitor-enter(r32)
                r30 = 0
                r0 = r30
                r1 = r32
                r1.emitting = r0     // Catch:{ all -> 0x0179 }
                monitor-exit(r32)     // Catch:{ all -> 0x0179 }
                goto L_0x001a
            L_0x0179:
                r30 = move-exception
                monitor-exit(r32)     // Catch:{ all -> 0x0179 }
                throw r30
            L_0x017c:
                int r14 = r14 + 1
                if (r14 != r15) goto L_0x0181
                r14 = 0
            L_0x0181:
                int r7 = r7 + 1
                goto L_0x013e
            L_0x0184:
                r13 = r9[r14]     // Catch:{ all -> 0x00fc }
                r16 = 0
            L_0x0188:
                r17 = 0
            L_0x018a:
                r30 = 0
                int r30 = (r20 > r30 ? 1 : (r20 == r30 ? 0 : -1))
                if (r30 <= 0) goto L_0x01af
                boolean r30 = r32.checkTerminate()     // Catch:{ all -> 0x00fc }
                if (r30 == 0) goto L_0x01a9
                r23 = 1
                if (r23 != 0) goto L_0x001a
                monitor-enter(r32)
                r30 = 0
                r0 = r30
                r1 = r32
                r1.emitting = r0     // Catch:{ all -> 0x01a6 }
                monitor-exit(r32)     // Catch:{ all -> 0x01a6 }
                goto L_0x001a
            L_0x01a6:
                r30 = move-exception
                monitor-exit(r32)     // Catch:{ all -> 0x01a6 }
                throw r30
            L_0x01a9:
                rx.internal.util.RxRingBuffer r0 = r13.queue     // Catch:{ all -> 0x00fc }
                r18 = r0
                if (r18 != 0) goto L_0x01ff
            L_0x01af:
                if (r17 <= 0) goto L_0x01cb
                if (r28 != 0) goto L_0x023a
                r0 = r32
                rx.internal.operators.OperatorMerge$MergeProducer<T> r0 = r0.producer     // Catch:{ all -> 0x00fc }
                r30 = r0
                r0 = r30
                r1 = r17
                long r20 = r0.produced(r1)     // Catch:{ all -> 0x00fc }
            L_0x01c1:
                r0 = r17
                long r0 = (long) r0     // Catch:{ all -> 0x00fc }
                r30 = r0
                r0 = r30
                r13.requestMore(r0)     // Catch:{ all -> 0x00fc }
            L_0x01cb:
                r30 = 0
                int r30 = (r20 > r30 ? 1 : (r20 == r30 ? 0 : -1))
                if (r30 == 0) goto L_0x01d3
                if (r16 != 0) goto L_0x0188
            L_0x01d3:
                boolean r11 = r13.done     // Catch:{ all -> 0x00fc }
                rx.internal.util.RxRingBuffer r12 = r13.queue     // Catch:{ all -> 0x00fc }
                if (r11 == 0) goto L_0x0243
                if (r12 == 0) goto L_0x01e1
                boolean r30 = r12.isEmpty()     // Catch:{ all -> 0x00fc }
                if (r30 == 0) goto L_0x0243
            L_0x01e1:
                r0 = r32
                r0.removeInner(r13)     // Catch:{ all -> 0x00fc }
                boolean r30 = r32.checkTerminate()     // Catch:{ all -> 0x00fc }
                if (r30 == 0) goto L_0x0240
                r23 = 1
                if (r23 != 0) goto L_0x001a
                monitor-enter(r32)
                r30 = 0
                r0 = r30
                r1 = r32
                r1.emitting = r0     // Catch:{ all -> 0x01fc }
                monitor-exit(r32)     // Catch:{ all -> 0x01fc }
                goto L_0x001a
            L_0x01fc:
                r30 = move-exception
                monitor-exit(r32)     // Catch:{ all -> 0x01fc }
                throw r30
            L_0x01ff:
                java.lang.Object r16 = r18.poll()     // Catch:{ all -> 0x00fc }
                if (r16 == 0) goto L_0x01af
                java.lang.Object r29 = p032rx.internal.operators.NotificationLite.getValue(r16)     // Catch:{ all -> 0x00fc }
                r0 = r29
                r4.onNext(r0)     // Catch:{ Throwable -> 0x0216 }
                r30 = 1
                long r20 = r20 - r30
                int r17 = r17 + 1
                goto L_0x018a
            L_0x0216:
                r27 = move-exception
                r23 = 1
                p032rx.exceptions.Exceptions.throwIfFatal(r27)     // Catch:{ all -> 0x00fc }
                r0 = r27
                r4.onError(r0)     // Catch:{ all -> 0x0235 }
                r32.unsubscribe()     // Catch:{ all -> 0x00fc }
                if (r23 != 0) goto L_0x001a
                monitor-enter(r32)
                r30 = 0
                r0 = r30
                r1 = r32
                r1.emitting = r0     // Catch:{ all -> 0x0232 }
                monitor-exit(r32)     // Catch:{ all -> 0x0232 }
                goto L_0x001a
            L_0x0232:
                r30 = move-exception
                monitor-exit(r32)     // Catch:{ all -> 0x0232 }
                throw r30
            L_0x0235:
                r30 = move-exception
                r32.unsubscribe()     // Catch:{ all -> 0x00fc }
                throw r30     // Catch:{ all -> 0x00fc }
            L_0x023a:
                r20 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                goto L_0x01c1
            L_0x0240:
                int r19 = r19 + 1
                r10 = 1
            L_0x0243:
                r30 = 0
                int r30 = (r20 > r30 ? 1 : (r20 == r30 ? 0 : -1))
                if (r30 != 0) goto L_0x0290
            L_0x0249:
                r0 = r32
                r0.lastIndex = r14     // Catch:{ all -> 0x00fc }
                r30 = r9[r14]     // Catch:{ all -> 0x00fc }
                r0 = r30
                long r0 = r0.f7251id     // Catch:{ all -> 0x00fc }
                r30 = r0
                r0 = r30
                r2 = r32
                r2.lastId = r0     // Catch:{ all -> 0x00fc }
            L_0x025b:
                if (r19 <= 0) goto L_0x0269
                r0 = r19
                long r0 = (long) r0     // Catch:{ all -> 0x00fc }
                r30 = r0
                r0 = r32
                r1 = r30
                r0.request(r1)     // Catch:{ all -> 0x00fc }
            L_0x0269:
                if (r10 != 0) goto L_0x0006
                monitor-enter(r32)     // Catch:{ all -> 0x00fc }
                r0 = r32
                boolean r0 = r0.missed     // Catch:{ all -> 0x02a4 }
                r30 = r0
                if (r30 != 0) goto L_0x0299
                r23 = 1
                r30 = 0
                r0 = r30
                r1 = r32
                r1.emitting = r0     // Catch:{ all -> 0x02a4 }
                monitor-exit(r32)     // Catch:{ all -> 0x02a4 }
                if (r23 != 0) goto L_0x001a
                monitor-enter(r32)
                r30 = 0
                r0 = r30
                r1 = r32
                r1.emitting = r0     // Catch:{ all -> 0x028d }
                monitor-exit(r32)     // Catch:{ all -> 0x028d }
                goto L_0x001a
            L_0x028d:
                r30 = move-exception
                monitor-exit(r32)     // Catch:{ all -> 0x028d }
                throw r30
            L_0x0290:
                int r14 = r14 + 1
                if (r14 != r15) goto L_0x0295
                r14 = 0
            L_0x0295:
                int r7 = r7 + 1
                goto L_0x0161
            L_0x0299:
                r30 = 0
                r0 = r30
                r1 = r32
                r1.missed = r0     // Catch:{ all -> 0x02a4 }
                monitor-exit(r32)     // Catch:{ all -> 0x02a4 }
                goto L_0x0006
            L_0x02a4:
                r30 = move-exception
                monitor-exit(r32)     // Catch:{ all -> 0x02a4 }
                throw r30     // Catch:{ all -> 0x00fc }
            L_0x02a7:
                r30 = move-exception
                monitor-exit(r32)     // Catch:{ all -> 0x02a7 }
                throw r30
            */
            throw new UnsupportedOperationException("Method not decompiled: p032rx.internal.operators.OperatorMerge.MergeSubscriber.emitLoop():void");
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminate() {
            if (this.child.isUnsubscribed()) {
                return true;
            }
            Queue<Throwable> e = this.errors;
            if (this.delayErrors || e == null || e.isEmpty()) {
                return false;
            }
            try {
                reportError();
                return true;
            } finally {
                unsubscribe();
            }
        }
    }

    public static <T> OperatorMerge<T> instance(boolean delayErrors2) {
        if (delayErrors2) {
            return HolderDelayErrors.INSTANCE;
        }
        return HolderNoDelay.INSTANCE;
    }

    OperatorMerge(boolean delayErrors2, int maxConcurrent2) {
        this.delayErrors = delayErrors2;
        this.maxConcurrent = maxConcurrent2;
    }

    public Subscriber<Observable<? extends T>> call(Subscriber<? super T> child) {
        MergeSubscriber<T> subscriber = new MergeSubscriber<>(child, this.delayErrors, this.maxConcurrent);
        MergeProducer<T> producer = new MergeProducer<>(subscriber);
        subscriber.producer = producer;
        child.add(subscriber);
        child.setProducer(producer);
        return subscriber;
    }
}

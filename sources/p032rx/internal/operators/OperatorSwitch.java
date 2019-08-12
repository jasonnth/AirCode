package p032rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import p032rx.Observable;
import p032rx.Observable.Operator;
import p032rx.Producer;
import p032rx.Subscriber;
import p032rx.Subscription;
import p032rx.exceptions.CompositeException;
import p032rx.functions.Action0;
import p032rx.internal.util.RxRingBuffer;
import p032rx.internal.util.atomic.SpscLinkedArrayQueue;
import p032rx.plugins.RxJavaHooks;
import p032rx.subscriptions.SerialSubscription;
import p032rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.operators.OperatorSwitch */
public final class OperatorSwitch<T> implements Operator<T, Observable<? extends T>> {
    final boolean delayError;

    /* renamed from: rx.internal.operators.OperatorSwitch$Holder */
    static final class Holder {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(false);
    }

    /* renamed from: rx.internal.operators.OperatorSwitch$HolderDelayError */
    static final class HolderDelayError {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(true);
    }

    /* renamed from: rx.internal.operators.OperatorSwitch$InnerSubscriber */
    static final class InnerSubscriber<T> extends Subscriber<T> {
        /* access modifiers changed from: private */

        /* renamed from: id */
        public final long f7253id;
        private final SwitchSubscriber<T> parent;

        InnerSubscriber(long id, SwitchSubscriber<T> parent2) {
            this.f7253id = id;
            this.parent = parent2;
        }

        public void setProducer(Producer p) {
            this.parent.innerProducer(p, this.f7253id);
        }

        public void onNext(T t) {
            this.parent.emit(t, this);
        }

        public void onError(Throwable e) {
            this.parent.error(e, this.f7253id);
        }

        public void onCompleted() {
            this.parent.complete(this.f7253id);
        }
    }

    /* renamed from: rx.internal.operators.OperatorSwitch$SwitchSubscriber */
    static final class SwitchSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final Throwable TERMINAL_ERROR = new Throwable("Terminal error");
        final Subscriber<? super T> child;
        final boolean delayError;
        boolean emitting;
        Throwable error;
        final AtomicLong index;
        boolean innerActive;
        volatile boolean mainDone;
        boolean missed;
        Producer producer;
        final SpscLinkedArrayQueue<Object> queue;
        long requested;
        final SerialSubscription serial = new SerialSubscription();

        SwitchSubscriber(Subscriber<? super T> child2, boolean delayError2) {
            this.child = child2;
            this.delayError = delayError2;
            this.index = new AtomicLong();
            this.queue = new SpscLinkedArrayQueue<>(RxRingBuffer.SIZE);
        }

        /* access modifiers changed from: 0000 */
        public void init() {
            this.child.add(this.serial);
            this.child.add(Subscriptions.create(new Action0() {
                public void call() {
                    SwitchSubscriber.this.clearProducer();
                }
            }));
            this.child.setProducer(new Producer() {
                public void request(long n) {
                    if (n > 0) {
                        SwitchSubscriber.this.childRequested(n);
                    } else if (n < 0) {
                        throw new IllegalArgumentException("n >= 0 expected but it was " + n);
                    }
                }
            });
        }

        /* access modifiers changed from: 0000 */
        public void clearProducer() {
            synchronized (this) {
                this.producer = null;
            }
        }

        public void onNext(Observable<? extends T> t) {
            InnerSubscriber<T> inner;
            long id = this.index.incrementAndGet();
            Subscription s = this.serial.get();
            if (s != null) {
                s.unsubscribe();
            }
            synchronized (this) {
                inner = new InnerSubscriber<>(id, this);
                this.innerActive = true;
                this.producer = null;
            }
            this.serial.set(inner);
            t.unsafeSubscribe(inner);
        }

        public void onError(Throwable e) {
            boolean success;
            synchronized (this) {
                success = updateError(e);
            }
            if (success) {
                this.mainDone = true;
                drain();
                return;
            }
            pluginError(e);
        }

        /* access modifiers changed from: 0000 */
        public boolean updateError(Throwable next) {
            Throwable e = this.error;
            if (e == TERMINAL_ERROR) {
                return false;
            }
            if (e == null) {
                this.error = next;
            } else if (e instanceof CompositeException) {
                List<Throwable> list = new ArrayList<>(((CompositeException) e).getExceptions());
                list.add(next);
                this.error = new CompositeException((Collection<? extends Throwable>) list);
            } else {
                this.error = new CompositeException(e, next);
            }
            return true;
        }

        public void onCompleted() {
            this.mainDone = true;
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void emit(T value, InnerSubscriber<T> inner) {
            synchronized (this) {
                if (this.index.get() == inner.f7253id) {
                    this.queue.offer(inner, NotificationLite.next(value));
                    drain();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void error(Throwable e, long id) {
            boolean success;
            synchronized (this) {
                if (this.index.get() == id) {
                    success = updateError(e);
                    this.innerActive = false;
                    this.producer = null;
                } else {
                    success = true;
                }
            }
            if (success) {
                drain();
            } else {
                pluginError(e);
            }
        }

        /* access modifiers changed from: 0000 */
        public void complete(long id) {
            synchronized (this) {
                if (this.index.get() == id) {
                    this.innerActive = false;
                    this.producer = null;
                    drain();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void pluginError(Throwable e) {
            RxJavaHooks.onError(e);
        }

        /* access modifiers changed from: 0000 */
        public void innerProducer(Producer p, long id) {
            synchronized (this) {
                if (this.index.get() == id) {
                    long n = this.requested;
                    this.producer = p;
                    p.request(n);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void childRequested(long n) {
            Producer p;
            synchronized (this) {
                p = this.producer;
                this.requested = BackpressureUtils.addCap(this.requested, n);
            }
            if (p != null) {
                p.request(n);
            }
            drain();
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
            r8 = r25.queue;
            r19 = r25.index;
            r9 = r25.child;
            r5 = r25.mainDone;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
            r20 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
            if (r20 == r22) goto L_0x0060;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
            if (r9.isUnsubscribed() != false) goto L_0x000d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0052, code lost:
            r10 = r8.isEmpty();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x005c, code lost:
            if (checkTerminated(r5, r6, r7, r8, r9, r10) != false) goto L_0x000d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x005e, code lost:
            if (r10 == false) goto L_0x00ab;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0062, code lost:
            if (r20 != r22) goto L_0x007f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0068, code lost:
            if (r9.isUnsubscribed() != false) goto L_0x000d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x007d, code lost:
            if (checkTerminated(r25.mainDone, r6, r7, r8, r9, r8.isEmpty()) != false) goto L_0x000d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x007f, code lost:
            monitor-enter(r25);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            r22 = r25.requested;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x008d, code lost:
            if (r22 == Long.MAX_VALUE) goto L_0x0097;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x008f, code lost:
            r22 = r22 - r20;
            r25.requested = r22;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x009b, code lost:
            if (r25.missed != false) goto L_0x00d0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x009d, code lost:
            r25.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a2, code lost:
            monitor-exit(r25);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ab, code lost:
            r18 = (p032rx.internal.operators.OperatorSwitch.InnerSubscriber) r8.poll();
            r24 = p032rx.internal.operators.NotificationLite.getValue(r8.poll());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c3, code lost:
            if (r19.get() != p032rx.internal.operators.OperatorSwitch.InnerSubscriber.access$000(r18)) goto L_0x0048;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c5, code lost:
            r9.onNext(r24);
            r20 = r20 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
            r25.missed = false;
            r5 = r25.mainDone;
            r6 = r25.innerActive;
            r7 = r25.error;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e1, code lost:
            if (r7 == null) goto L_0x00f3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e5, code lost:
            if (r7 == TERMINAL_ERROR) goto L_0x00f3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x00eb, code lost:
            if (r25.delayError != false) goto L_0x00f3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ed, code lost:
            r25.error = TERMINAL_ERROR;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f3, code lost:
            monitor-exit(r25);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drain() {
            /*
                r25 = this;
                monitor-enter(r25)
                r0 = r25
                boolean r4 = r0.emitting     // Catch:{ all -> 0x00a8 }
                if (r4 == 0) goto L_0x000e
                r4 = 1
                r0 = r25
                r0.missed = r4     // Catch:{ all -> 0x00a8 }
                monitor-exit(r25)     // Catch:{ all -> 0x00a8 }
            L_0x000d:
                return
            L_0x000e:
                r4 = 1
                r0 = r25
                r0.emitting = r4     // Catch:{ all -> 0x00a8 }
                r0 = r25
                boolean r6 = r0.innerActive     // Catch:{ all -> 0x00a8 }
                r0 = r25
                long r0 = r0.requested     // Catch:{ all -> 0x00a8 }
                r22 = r0
                r0 = r25
                java.lang.Throwable r7 = r0.error     // Catch:{ all -> 0x00a8 }
                if (r7 == 0) goto L_0x0033
                java.lang.Throwable r4 = TERMINAL_ERROR     // Catch:{ all -> 0x00a8 }
                if (r7 == r4) goto L_0x0033
                r0 = r25
                boolean r4 = r0.delayError     // Catch:{ all -> 0x00a8 }
                if (r4 != 0) goto L_0x0033
                java.lang.Throwable r4 = TERMINAL_ERROR     // Catch:{ all -> 0x00a8 }
                r0 = r25
                r0.error = r4     // Catch:{ all -> 0x00a8 }
            L_0x0033:
                monitor-exit(r25)     // Catch:{ all -> 0x00a8 }
                r0 = r25
                rx.internal.util.atomic.SpscLinkedArrayQueue<java.lang.Object> r8 = r0.queue
                r0 = r25
                java.util.concurrent.atomic.AtomicLong r0 = r0.index
                r19 = r0
                r0 = r25
                rx.Subscriber<? super T> r9 = r0.child
                r0 = r25
                boolean r5 = r0.mainDone
            L_0x0046:
                r20 = 0
            L_0x0048:
                int r4 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1))
                if (r4 == 0) goto L_0x0060
                boolean r4 = r9.isUnsubscribed()
                if (r4 != 0) goto L_0x000d
                boolean r10 = r8.isEmpty()
                r4 = r25
                boolean r4 = r4.checkTerminated(r5, r6, r7, r8, r9, r10)
                if (r4 != 0) goto L_0x000d
                if (r10 == 0) goto L_0x00ab
            L_0x0060:
                int r4 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1))
                if (r4 != 0) goto L_0x007f
                boolean r4 = r9.isUnsubscribed()
                if (r4 != 0) goto L_0x000d
                r0 = r25
                boolean r12 = r0.mainDone
                boolean r17 = r8.isEmpty()
                r11 = r25
                r13 = r6
                r14 = r7
                r15 = r8
                r16 = r9
                boolean r4 = r11.checkTerminated(r12, r13, r14, r15, r16, r17)
                if (r4 != 0) goto L_0x000d
            L_0x007f:
                monitor-enter(r25)
                r0 = r25
                long r0 = r0.requested     // Catch:{ all -> 0x00a5 }
                r22 = r0
                r12 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r4 = (r22 > r12 ? 1 : (r22 == r12 ? 0 : -1))
                if (r4 == 0) goto L_0x0097
                long r22 = r22 - r20
                r0 = r22
                r2 = r25
                r2.requested = r0     // Catch:{ all -> 0x00a5 }
            L_0x0097:
                r0 = r25
                boolean r4 = r0.missed     // Catch:{ all -> 0x00a5 }
                if (r4 != 0) goto L_0x00d0
                r4 = 0
                r0 = r25
                r0.emitting = r4     // Catch:{ all -> 0x00a5 }
                monitor-exit(r25)     // Catch:{ all -> 0x00a5 }
                goto L_0x000d
            L_0x00a5:
                r4 = move-exception
                monitor-exit(r25)     // Catch:{ all -> 0x00a5 }
                throw r4
            L_0x00a8:
                r4 = move-exception
                monitor-exit(r25)     // Catch:{ all -> 0x00a8 }
                throw r4
            L_0x00ab:
                java.lang.Object r18 = r8.poll()
                rx.internal.operators.OperatorSwitch$InnerSubscriber r18 = (p032rx.internal.operators.OperatorSwitch.InnerSubscriber) r18
                java.lang.Object r4 = r8.poll()
                java.lang.Object r24 = p032rx.internal.operators.NotificationLite.getValue(r4)
                long r12 = r19.get()
                long r14 = r18.f7253id
                int r4 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
                if (r4 != 0) goto L_0x0048
                r0 = r24
                r9.onNext(r0)
                r12 = 1
                long r20 = r20 + r12
                goto L_0x0048
            L_0x00d0:
                r4 = 0
                r0 = r25
                r0.missed = r4     // Catch:{ all -> 0x00a5 }
                r0 = r25
                boolean r5 = r0.mainDone     // Catch:{ all -> 0x00a5 }
                r0 = r25
                boolean r6 = r0.innerActive     // Catch:{ all -> 0x00a5 }
                r0 = r25
                java.lang.Throwable r7 = r0.error     // Catch:{ all -> 0x00a5 }
                if (r7 == 0) goto L_0x00f3
                java.lang.Throwable r4 = TERMINAL_ERROR     // Catch:{ all -> 0x00a5 }
                if (r7 == r4) goto L_0x00f3
                r0 = r25
                boolean r4 = r0.delayError     // Catch:{ all -> 0x00a5 }
                if (r4 != 0) goto L_0x00f3
                java.lang.Throwable r4 = TERMINAL_ERROR     // Catch:{ all -> 0x00a5 }
                r0 = r25
                r0.error = r4     // Catch:{ all -> 0x00a5 }
            L_0x00f3:
                monitor-exit(r25)     // Catch:{ all -> 0x00a5 }
                goto L_0x0046
            */
            throw new UnsupportedOperationException("Method not decompiled: p032rx.internal.operators.OperatorSwitch.SwitchSubscriber.drain():void");
        }

        /* access modifiers changed from: protected */
        public boolean checkTerminated(boolean localMainDone, boolean localInnerActive, Throwable localError, SpscLinkedArrayQueue<Object> localQueue, Subscriber<? super T> localChild, boolean empty) {
            if (this.delayError) {
                if (localMainDone && !localInnerActive && empty) {
                    if (localError != null) {
                        localChild.onError(localError);
                        return true;
                    }
                    localChild.onCompleted();
                    return true;
                }
            } else if (localError != null) {
                localQueue.clear();
                localChild.onError(localError);
                return true;
            } else if (localMainDone && !localInnerActive && empty) {
                localChild.onCompleted();
                return true;
            }
            return false;
        }
    }

    public static <T> OperatorSwitch<T> instance(boolean delayError2) {
        if (delayError2) {
            return HolderDelayError.INSTANCE;
        }
        return Holder.INSTANCE;
    }

    OperatorSwitch(boolean delayError2) {
        this.delayError = delayError2;
    }

    public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> child) {
        SwitchSubscriber<T> sws = new SwitchSubscriber<>(child, this.delayError);
        child.add(sws);
        sws.init();
        return sws;
    }
}

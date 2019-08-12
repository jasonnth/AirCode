package p032rx.observers;

import p032rx.Observer;
import p032rx.exceptions.Exceptions;
import p032rx.internal.operators.NotificationLite;

/* renamed from: rx.observers.SerializedObserver */
public class SerializedObserver<T> implements Observer<T> {
    private final Observer<? super T> actual;
    private boolean emitting;
    private FastList queue;
    private volatile boolean terminated;

    /* renamed from: rx.observers.SerializedObserver$FastList */
    static final class FastList {
        Object[] array;
        int size;

        FastList() {
        }

        public void add(Object o) {
            int s = this.size;
            Object[] a = this.array;
            if (a == null) {
                a = new Object[16];
                this.array = a;
            } else if (s == a.length) {
                Object[] array2 = new Object[((s >> 2) + s)];
                System.arraycopy(a, 0, array2, 0, s);
                a = array2;
                this.array = a;
            }
            a[s] = o;
            this.size = s + 1;
        }
    }

    public SerializedObserver(Observer<? super T> s) {
        this.actual = s;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r8.actual.onNext(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0031, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r4 = r8.queue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0034, code lost:
        if (r4 != null) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0036, code lost:
        r8.emitting = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0039, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x003e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x003f, code lost:
        r8.terminated = true;
        p032rx.exceptions.Exceptions.throwOrReport(r1, r8.actual, (java.lang.Object) r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r8.queue = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x004a, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x004b, code lost:
        r0 = r4.array;
        r3 = r0.length;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x004f, code lost:
        if (r2 >= r3) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0051, code lost:
        r5 = r0[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0053, code lost:
        if (r5 == null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x005b, code lost:
        if (p032rx.internal.operators.NotificationLite.accept(r8.actual, r5) == false) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x005d, code lost:
        r8.terminated = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0061, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0062, code lost:
        r8.terminated = true;
        p032rx.exceptions.Exceptions.throwIfFatal(r1);
        r8.actual.onError(p032rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0071, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onNext(T r9) {
        /*
            r8 = this;
            r7 = 1
            boolean r6 = r8.terminated
            if (r6 == 0) goto L_0x0006
        L_0x0005:
            return
        L_0x0006:
            monitor-enter(r8)
            boolean r6 = r8.terminated     // Catch:{ all -> 0x000d }
            if (r6 == 0) goto L_0x0010
            monitor-exit(r8)     // Catch:{ all -> 0x000d }
            goto L_0x0005
        L_0x000d:
            r6 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x000d }
            throw r6
        L_0x0010:
            boolean r6 = r8.emitting     // Catch:{ all -> 0x000d }
            if (r6 == 0) goto L_0x0028
            rx.observers.SerializedObserver$FastList r4 = r8.queue     // Catch:{ all -> 0x000d }
            if (r4 != 0) goto L_0x001f
            rx.observers.SerializedObserver$FastList r4 = new rx.observers.SerializedObserver$FastList     // Catch:{ all -> 0x000d }
            r4.<init>()     // Catch:{ all -> 0x000d }
            r8.queue = r4     // Catch:{ all -> 0x000d }
        L_0x001f:
            java.lang.Object r6 = p032rx.internal.operators.NotificationLite.next(r9)     // Catch:{ all -> 0x000d }
            r4.add(r6)     // Catch:{ all -> 0x000d }
            monitor-exit(r8)     // Catch:{ all -> 0x000d }
            goto L_0x0005
        L_0x0028:
            r6 = 1
            r8.emitting = r6     // Catch:{ all -> 0x000d }
            monitor-exit(r8)     // Catch:{ all -> 0x000d }
            rx.Observer<? super T> r6 = r8.actual     // Catch:{ Throwable -> 0x003e }
            r6.onNext(r9)     // Catch:{ Throwable -> 0x003e }
        L_0x0031:
            monitor-enter(r8)
            rx.observers.SerializedObserver$FastList r4 = r8.queue     // Catch:{ all -> 0x003b }
            if (r4 != 0) goto L_0x0047
            r6 = 0
            r8.emitting = r6     // Catch:{ all -> 0x003b }
            monitor-exit(r8)     // Catch:{ all -> 0x003b }
            goto L_0x0005
        L_0x003b:
            r6 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x003b }
            throw r6
        L_0x003e:
            r1 = move-exception
            r8.terminated = r7
            rx.Observer<? super T> r6 = r8.actual
            p032rx.exceptions.Exceptions.throwOrReport(r1, r6, r9)
            goto L_0x0005
        L_0x0047:
            r6 = 0
            r8.queue = r6     // Catch:{ all -> 0x003b }
            monitor-exit(r8)     // Catch:{ all -> 0x003b }
            java.lang.Object[] r0 = r4.array
            int r3 = r0.length
            r2 = 0
        L_0x004f:
            if (r2 >= r3) goto L_0x0031
            r5 = r0[r2]
            if (r5 == 0) goto L_0x0031
            rx.Observer<? super T> r6 = r8.actual     // Catch:{ Throwable -> 0x0061 }
            boolean r6 = p032rx.internal.operators.NotificationLite.accept(r6, r5)     // Catch:{ Throwable -> 0x0061 }
            if (r6 == 0) goto L_0x0071
            r6 = 1
            r8.terminated = r6     // Catch:{ Throwable -> 0x0061 }
            goto L_0x0005
        L_0x0061:
            r1 = move-exception
            r8.terminated = r7
            p032rx.exceptions.Exceptions.throwIfFatal(r1)
            rx.Observer<? super T> r6 = r8.actual
            java.lang.Throwable r7 = p032rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r9)
            r6.onError(r7)
            goto L_0x0005
        L_0x0071:
            int r2 = r2 + 1
            goto L_0x004f
        */
        throw new UnsupportedOperationException("Method not decompiled: p032rx.observers.SerializedObserver.onNext(java.lang.Object):void");
    }

    public void onError(Throwable e) {
        Exceptions.throwIfFatal(e);
        if (!this.terminated) {
            synchronized (this) {
                if (!this.terminated) {
                    this.terminated = true;
                    if (this.emitting) {
                        FastList list = this.queue;
                        if (list == null) {
                            list = new FastList();
                            this.queue = list;
                        }
                        list.add(NotificationLite.error(e));
                        return;
                    }
                    this.emitting = true;
                    this.actual.onError(e);
                }
            }
        }
    }

    public void onCompleted() {
        if (!this.terminated) {
            synchronized (this) {
                if (!this.terminated) {
                    this.terminated = true;
                    if (this.emitting) {
                        FastList list = this.queue;
                        if (list == null) {
                            list = new FastList();
                            this.queue = list;
                        }
                        list.add(NotificationLite.completed());
                        return;
                    }
                    this.emitting = true;
                    this.actual.onCompleted();
                }
            }
        }
    }
}

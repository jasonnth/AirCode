package p032rx.internal.util;

import java.util.Queue;
import p032rx.Subscription;
import p032rx.exceptions.MissingBackpressureException;
import p032rx.internal.operators.NotificationLite;
import p032rx.internal.util.atomic.SpscAtomicArrayQueue;
import p032rx.internal.util.unsafe.SpmcArrayQueue;
import p032rx.internal.util.unsafe.SpscArrayQueue;
import p032rx.internal.util.unsafe.UnsafeAccess;

/* renamed from: rx.internal.util.RxRingBuffer */
public class RxRingBuffer implements Subscription {
    public static final int SIZE;
    private Queue<Object> queue;
    private final int size;
    public volatile Object terminalState;

    static {
        int defaultSize = 128;
        if (PlatformDependent.isAndroid()) {
            defaultSize = 16;
        }
        String sizeFromProperty = System.getProperty("rx.ring-buffer.size");
        if (sizeFromProperty != null) {
            try {
                defaultSize = Integer.parseInt(sizeFromProperty);
            } catch (NumberFormatException e) {
                System.err.println("Failed to set 'rx.buffer.size' with value " + sizeFromProperty + " => " + e.getMessage());
            }
        }
        SIZE = defaultSize;
    }

    public static RxRingBuffer getSpscInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(false, SIZE);
        }
        return new RxRingBuffer();
    }

    private RxRingBuffer(Queue<Object> queue2, int size2) {
        this.queue = queue2;
        this.size = size2;
    }

    private RxRingBuffer(boolean spmc, int size2) {
        this.queue = spmc ? new SpmcArrayQueue<>(size2) : new SpscArrayQueue<>(size2);
        this.size = size2;
    }

    public synchronized void release() {
    }

    public void unsubscribe() {
        release();
    }

    RxRingBuffer() {
        this((Queue<Object>) new SpscAtomicArrayQueue<Object>(SIZE), SIZE);
    }

    public void onNext(Object o) throws MissingBackpressureException {
        boolean iae = false;
        boolean mbe = false;
        synchronized (this) {
            Queue<Object> q = this.queue;
            if (q != null) {
                mbe = !q.offer(NotificationLite.next(o));
            } else {
                iae = true;
            }
        }
        if (iae) {
            throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        } else if (mbe) {
            throw new MissingBackpressureException();
        }
    }

    public boolean isEmpty() {
        Queue<Object> q = this.queue;
        return q == null || q.isEmpty();
    }

    public Object poll() {
        Object o = null;
        synchronized (this) {
            Queue<Object> q = this.queue;
            if (q != null) {
                o = q.poll();
                Object ts = this.terminalState;
                if (o == null && ts != null && q.peek() == null) {
                    o = ts;
                    this.terminalState = null;
                }
            }
        }
        return o;
    }

    public boolean isUnsubscribed() {
        return this.queue == null;
    }
}

package p032rx.internal.util.unsafe;

/* renamed from: rx.internal.util.unsafe.SpmcArrayQueueL1Pad */
/* compiled from: SpmcArrayQueue */
abstract class SpmcArrayQueueL1Pad<E> extends ConcurrentCircularArrayQueue<E> {
    public SpmcArrayQueueL1Pad(int capacity) {
        super(capacity);
    }
}

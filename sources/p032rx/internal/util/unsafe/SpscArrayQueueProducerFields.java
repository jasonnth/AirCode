package p032rx.internal.util.unsafe;

/* renamed from: rx.internal.util.unsafe.SpscArrayQueueProducerFields */
/* compiled from: SpscArrayQueue */
abstract class SpscArrayQueueProducerFields<E> extends SpscArrayQueueL1Pad<E> {
    protected static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueProducerFields.class, "producerIndex");
    protected long producerIndex;

    public SpscArrayQueueProducerFields(int capacity) {
        super(capacity);
    }
}

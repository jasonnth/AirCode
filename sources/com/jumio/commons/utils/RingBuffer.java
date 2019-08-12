package com.jumio.commons.utils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

public class RingBuffer<T extends Serializable> implements Serializable, Iterable<T> {
    /* access modifiers changed from: private */
    public Object[] mArray;
    /* access modifiers changed from: private */
    public int mElements = 0;
    /* access modifiers changed from: private */
    public int mIndex = 0;
    /* access modifiers changed from: private */
    public int mReadIndex = 0;

    private class RingBufferIterator implements Iterator<T> {
        private int mLocalIndex = 0;

        public RingBufferIterator() {
        }

        public boolean hasNext() {
            return true;
        }

        public T next() {
            T elem = (Serializable) RingBuffer.this.mArray[this.mLocalIndex];
            this.mLocalIndex++;
            if (RingBuffer.this.mIndex >= RingBuffer.this.mArray.length) {
                this.mLocalIndex = 0;
            }
            return elem;
        }

        public void remove() {
            if (RingBuffer.this.isEmpty()) {
                throw new IllegalStateException("cannot call remove on an empty ringbuffer!");
            } else if (!RingBuffer.this.isEmpty()) {
                RingBuffer.this.mArray[this.mLocalIndex] = null;
                for (int i = this.mLocalIndex; i < RingBuffer.this.mArray.length - 1; i++) {
                    RingBuffer.this.mArray[i] = RingBuffer.this.mArray[i + 1];
                }
                RingBuffer.this.mElements = RingBuffer.this.mElements - 1;
            }
        }
    }

    private class StatefulRingBufferIterator implements Iterator<T> {
        private StatefulRingBufferIterator() {
        }

        public boolean hasNext() {
            return true;
        }

        public T next() {
            T elem = (Serializable) RingBuffer.this.mArray[RingBuffer.this.mReadIndex];
            RingBuffer.this.mReadIndex = RingBuffer.this.mReadIndex + 1;
            if (RingBuffer.this.mReadIndex >= RingBuffer.this.mArray.length) {
                RingBuffer.this.mReadIndex = 0;
            }
            return elem;
        }

        public void remove() {
            throw new IllegalStateException("Removing not supported!");
        }
    }

    public RingBuffer(int size) {
        this.mArray = new Object[size];
        clear();
    }

    @SafeVarargs
    public static <T extends Serializable> RingBuffer<T> wrap(T... objects) {
        RingBuffer<T> newBuffer = new RingBuffer<>(objects.length);
        for (T obj : objects) {
            newBuffer.add(obj);
        }
        return newBuffer;
    }

    public void add(T element) {
        this.mArray[this.mIndex] = element;
        this.mIndex++;
        if (this.mIndex >= this.mArray.length) {
            this.mIndex = 0;
        }
        if (this.mElements < this.mArray.length) {
            this.mElements++;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T current() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot call current() upon an empty RingBuffer!");
        } else if (this.mIndex == 0) {
            return (Serializable) this.mArray[this.mArray.length - 1];
        } else {
            return (Serializable) this.mArray[this.mIndex - 1];
        }
    }

    public Iterator<T> iterator() {
        return new RingBufferIterator();
    }

    public Iterator<T> statefulIterator() {
        return new StatefulRingBufferIterator();
    }

    public int size() {
        return this.mElements;
    }

    public void clear() {
        Arrays.fill(this.mArray, null);
        this.mElements = 0;
        this.mIndex = 0;
        this.mReadIndex = 0;
    }

    public void resetStatefulIterator() {
        this.mReadIndex = 0;
    }
}

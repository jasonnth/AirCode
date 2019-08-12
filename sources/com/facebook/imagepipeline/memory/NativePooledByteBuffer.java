package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.memory.PooledByteBuffer.ClosedException;

public class NativePooledByteBuffer implements PooledByteBuffer {
    @VisibleForTesting
    CloseableReference<NativeMemoryChunk> mBufRef;
    private final int mSize;

    public NativePooledByteBuffer(CloseableReference<NativeMemoryChunk> bufRef, int size) {
        Preconditions.checkNotNull(bufRef);
        Preconditions.checkArgument(size >= 0 && size <= ((NativeMemoryChunk) bufRef.get()).getSize());
        this.mBufRef = bufRef.clone();
        this.mSize = size;
    }

    public synchronized int size() {
        ensureValid();
        return this.mSize;
    }

    public synchronized byte read(int offset) {
        boolean z;
        byte read;
        boolean z2 = true;
        synchronized (this) {
            ensureValid();
            if (offset >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z);
            if (offset >= this.mSize) {
                z2 = false;
            }
            Preconditions.checkArgument(z2);
            read = ((NativeMemoryChunk) this.mBufRef.get()).read(offset);
        }
        return read;
    }

    public synchronized void read(int offset, byte[] buffer, int bufferOffset, int length) {
        ensureValid();
        Preconditions.checkArgument(offset + length <= this.mSize);
        ((NativeMemoryChunk) this.mBufRef.get()).read(offset, buffer, bufferOffset, length);
    }

    public synchronized long getNativePtr() {
        ensureValid();
        return ((NativeMemoryChunk) this.mBufRef.get()).getNativePtr();
    }

    public synchronized boolean isClosed() {
        return !CloseableReference.isValid(this.mBufRef);
    }

    public synchronized void close() {
        CloseableReference.closeSafely(this.mBufRef);
        this.mBufRef = null;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void ensureValid() {
        if (isClosed()) {
            throw new ClosedException();
        }
    }
}

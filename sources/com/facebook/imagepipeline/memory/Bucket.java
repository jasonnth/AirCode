package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.util.LinkedList;
import java.util.Queue;

@VisibleForTesting
class Bucket<V> {
    final Queue mFreeList;
    private int mInUseLength;
    public final int mItemSize;
    public final int mMaxLength;

    public Bucket(int itemSize, int maxLength, int inUseLength) {
        boolean z;
        boolean z2 = true;
        Preconditions.checkState(itemSize > 0);
        if (maxLength >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z);
        if (inUseLength < 0) {
            z2 = false;
        }
        Preconditions.checkState(z2);
        this.mItemSize = itemSize;
        this.mMaxLength = maxLength;
        this.mFreeList = new LinkedList();
        this.mInUseLength = inUseLength;
    }

    public boolean isMaxLengthExceeded() {
        return this.mInUseLength + getFreeListSize() > this.mMaxLength;
    }

    /* access modifiers changed from: 0000 */
    public int getFreeListSize() {
        return this.mFreeList.size();
    }

    public V get() {
        V value = pop();
        if (value != null) {
            this.mInUseLength++;
        }
        return value;
    }

    public V pop() {
        return this.mFreeList.poll();
    }

    public void incrementInUseCount() {
        this.mInUseLength++;
    }

    public void release(V value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkState(this.mInUseLength > 0);
        this.mInUseLength--;
        addToFreeList(value);
    }

    /* access modifiers changed from: 0000 */
    public void addToFreeList(V value) {
        this.mFreeList.add(value);
    }

    public void decrementInUseCount() {
        Preconditions.checkState(this.mInUseLength > 0);
        this.mInUseLength--;
    }

    public int getInUseCount() {
        return this.mInUseLength;
    }
}

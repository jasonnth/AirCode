package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.Throwables;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BasePool<V> implements Pool<V> {
    private final Class<?> TAG = getClass();
    private boolean mAllowNewBuckets;
    @VisibleForTesting
    final SparseArray<Bucket<V>> mBuckets;
    @VisibleForTesting
    final Counter mFree;
    @VisibleForTesting
    final Set<V> mInUseValues;
    final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;
    @VisibleForTesting
    final Counter mUsed;

    @VisibleForTesting
    static class Counter {
        private static final String TAG = "com.facebook.imagepipeline.memory.BasePool.Counter";
        int mCount;
        int mNumBytes;

        Counter() {
        }

        public void increment(int numBytes) {
            this.mCount++;
            this.mNumBytes += numBytes;
        }

        public void decrement(int numBytes) {
            if (this.mNumBytes < numBytes || this.mCount <= 0) {
                FLog.wtf(TAG, "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(numBytes), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
                return;
            }
            this.mCount--;
            this.mNumBytes -= numBytes;
        }

        public void reset() {
            this.mCount = 0;
            this.mNumBytes = 0;
        }
    }

    public static class InvalidSizeException extends RuntimeException {
        public InvalidSizeException(Object size) {
            super("Invalid size: " + size.toString());
        }
    }

    public static class InvalidValueException extends RuntimeException {
        public InvalidValueException(Object value) {
            super("Invalid value: " + value.toString());
        }
    }

    public static class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int hardCap, int usedBytes, int freeBytes, int allocSize) {
            super("Pool hard cap violation? Hard cap = " + hardCap + " Used size = " + usedBytes + " Free size = " + freeBytes + " Request size = " + allocSize);
        }
    }

    public static class SizeTooLargeException extends InvalidSizeException {
        public SizeTooLargeException(Object size) {
            super(size);
        }
    }

    /* access modifiers changed from: protected */
    public abstract V alloc(int i);

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public abstract void free(V v);

    /* access modifiers changed from: protected */
    public abstract int getBucketedSize(int i);

    /* access modifiers changed from: protected */
    public abstract int getBucketedSizeForValue(V v);

    /* access modifiers changed from: protected */
    public abstract int getSizeInBytes(int i);

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        this.mMemoryTrimmableRegistry = (MemoryTrimmableRegistry) Preconditions.checkNotNull(memoryTrimmableRegistry);
        this.mPoolParams = (PoolParams) Preconditions.checkNotNull(poolParams);
        this.mPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
        this.mBuckets = new SparseArray<>();
        initBuckets(new SparseIntArray(0));
        this.mInUseValues = Sets.newIdentityHashSet();
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    public V get(int size) {
        V value;
        ensurePoolSizeInvariant();
        int bucketedSize = getBucketedSize(size);
        synchronized (this) {
            Bucket<V> bucket = getBucket(bucketedSize);
            if (bucket != null) {
                value = bucket.get();
                if (value != null) {
                    Preconditions.checkState(this.mInUseValues.add(value));
                    int bucketedSize2 = getBucketedSizeForValue(value);
                    int sizeInBytes = getSizeInBytes(bucketedSize2);
                    this.mUsed.increment(sizeInBytes);
                    this.mFree.decrement(sizeInBytes);
                    this.mPoolStatsTracker.onValueReuse(sizeInBytes);
                    logStats();
                    if (FLog.isLoggable(2)) {
                        FLog.m1829v(this.TAG, "get (reuse) (object, size) = (%x, %s)", (Object) Integer.valueOf(System.identityHashCode(value)), (Object) Integer.valueOf(bucketedSize2));
                    }
                }
            }
            int sizeInBytes2 = getSizeInBytes(bucketedSize);
            if (!canAllocate(sizeInBytes2)) {
                throw new PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, sizeInBytes2);
            }
            this.mUsed.increment(sizeInBytes2);
            if (bucket != null) {
                bucket.incrementInUseCount();
            }
            value = null;
            try {
                value = alloc(bucketedSize);
            } catch (Throwable e) {
                synchronized (this) {
                    this.mUsed.decrement(sizeInBytes2);
                    Bucket<V> bucket2 = getBucket(bucketedSize);
                    if (bucket2 != null) {
                        bucket2.decrementInUseCount();
                    }
                    Throwables.propagateIfPossible(e);
                }
                return value;
            }
            synchronized (this) {
                Preconditions.checkState(this.mInUseValues.add(value));
                trimToSoftCap();
                this.mPoolStatsTracker.onAlloc(sizeInBytes2);
                logStats();
                if (FLog.isLoggable(2)) {
                    FLog.m1829v(this.TAG, "get (alloc) (object, size) = (%x, %s)", (Object) Integer.valueOf(System.identityHashCode(value)), (Object) Integer.valueOf(bucketedSize));
                }
            }
        }
        return value;
    }

    public void release(V value) {
        Preconditions.checkNotNull(value);
        int bucketedSize = getBucketedSizeForValue(value);
        int sizeInBytes = getSizeInBytes(bucketedSize);
        synchronized (this) {
            Bucket<V> bucket = getBucket(bucketedSize);
            if (!this.mInUseValues.remove(value)) {
                FLog.m1805e(this.TAG, "release (free, value unrecognized) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                free(value);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else if (bucket == null || bucket.isMaxLengthExceeded() || isMaxSizeSoftCapExceeded() || !isReusable(value)) {
                if (bucket != null) {
                    bucket.decrementInUseCount();
                }
                if (FLog.isLoggable(2)) {
                    FLog.m1829v(this.TAG, "release (free) (object, size) = (%x, %s)", (Object) Integer.valueOf(System.identityHashCode(value)), (Object) Integer.valueOf(bucketedSize));
                }
                free(value);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else {
                bucket.release(value);
                this.mFree.increment(sizeInBytes);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueRelease(sizeInBytes);
                if (FLog.isLoggable(2)) {
                    FLog.m1829v(this.TAG, "release (reuse) (object, size) = (%x, %s)", (Object) Integer.valueOf(System.identityHashCode(value)), (Object) Integer.valueOf(bucketedSize));
                }
            }
            logStats();
        }
    }

    public void trim(MemoryTrimType memoryTrimType) {
        trimToNothing();
    }

    /* access modifiers changed from: protected */
    public void onParamsChanged() {
    }

    /* access modifiers changed from: protected */
    public boolean isReusable(V value) {
        Preconditions.checkNotNull(value);
        return true;
    }

    private synchronized void ensurePoolSizeInvariant() {
        Preconditions.checkState(!isMaxSizeSoftCapExceeded() || this.mFree.mNumBytes == 0);
    }

    private synchronized void initBuckets(SparseIntArray inUseCounts) {
        Preconditions.checkNotNull(inUseCounts);
        this.mBuckets.clear();
        SparseIntArray bucketSizes = this.mPoolParams.bucketSizes;
        if (bucketSizes != null) {
            for (int i = 0; i < bucketSizes.size(); i++) {
                int bucketSize = bucketSizes.keyAt(i);
                this.mBuckets.put(bucketSize, new Bucket(getSizeInBytes(bucketSize), bucketSizes.valueAt(i), inUseCounts.get(bucketSize, 0)));
            }
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void trimToNothing() {
        List<Bucket<V>> bucketsToTrim = new ArrayList<>(this.mBuckets.size());
        SparseIntArray inUseCounts = new SparseIntArray();
        synchronized (this) {
            for (int i = 0; i < this.mBuckets.size(); i++) {
                Bucket<V> bucket = (Bucket) this.mBuckets.valueAt(i);
                if (bucket.getFreeListSize() > 0) {
                    bucketsToTrim.add(bucket);
                }
                inUseCounts.put(this.mBuckets.keyAt(i), bucket.getInUseCount());
            }
            initBuckets(inUseCounts);
            this.mFree.reset();
            logStats();
        }
        onParamsChanged();
        for (int i2 = 0; i2 < bucketsToTrim.size(); i2++) {
            Bucket<V> bucket2 = (Bucket) bucketsToTrim.get(i2);
            while (true) {
                V item = bucket2.pop();
                if (item == null) {
                    break;
                }
                free(item);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized void trimToSoftCap() {
        if (isMaxSizeSoftCapExceeded()) {
            trimToSize(this.mPoolParams.maxSizeSoftCap);
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized void trimToSize(int targetSize) {
        int bytesToFree = Math.min((this.mUsed.mNumBytes + this.mFree.mNumBytes) - targetSize, this.mFree.mNumBytes);
        if (bytesToFree > 0) {
            if (FLog.isLoggable(2)) {
                FLog.m1830v(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", (Object) Integer.valueOf(targetSize), (Object) Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes), (Object) Integer.valueOf(bytesToFree));
            }
            logStats();
            for (int i = 0; i < this.mBuckets.size() && bytesToFree > 0; i++) {
                Bucket<V> bucket = (Bucket) this.mBuckets.valueAt(i);
                while (bytesToFree > 0) {
                    V value = bucket.pop();
                    if (value == null) {
                        break;
                    }
                    free(value);
                    bytesToFree -= bucket.mItemSize;
                    this.mFree.decrement(bucket.mItemSize);
                }
            }
            logStats();
            if (FLog.isLoggable(2)) {
                FLog.m1829v(this.TAG, "trimToSize: TargetSize = %d; Final Size = %d", (Object) Integer.valueOf(targetSize), (Object) Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized Bucket<V> getBucket(int bucketedSize) {
        Bucket<V> newBucket;
        Bucket<V> bucket = (Bucket) this.mBuckets.get(bucketedSize);
        if (bucket != null || !this.mAllowNewBuckets) {
            newBucket = bucket;
        } else {
            if (FLog.isLoggable(2)) {
                FLog.m1828v(this.TAG, "creating new bucket %s", (Object) Integer.valueOf(bucketedSize));
            }
            newBucket = newBucket(bucketedSize);
            this.mBuckets.put(bucketedSize, newBucket);
        }
        return newBucket;
    }

    /* access modifiers changed from: 0000 */
    public Bucket<V> newBucket(int bucketedSize) {
        return new Bucket<>(getSizeInBytes(bucketedSize), Integer.MAX_VALUE, 0);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean isMaxSizeSoftCapExceeded;
        isMaxSizeSoftCapExceeded = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (isMaxSizeSoftCapExceeded) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return isMaxSizeSoftCapExceeded;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized boolean canAllocate(int sizeInBytes) {
        boolean z = false;
        synchronized (this) {
            int hardCap = this.mPoolParams.maxSizeHardCap;
            if (sizeInBytes > hardCap - this.mUsed.mNumBytes) {
                this.mPoolStatsTracker.onHardCapReached();
            } else {
                int softCap = this.mPoolParams.maxSizeSoftCap;
                if (sizeInBytes > softCap - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
                    trimToSize(softCap - sizeInBytes);
                }
                if (sizeInBytes > hardCap - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
                    this.mPoolStatsTracker.onHardCapReached();
                } else {
                    z = true;
                }
            }
        }
        return z;
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    private void logStats() {
        if (FLog.isLoggable(2)) {
            FLog.m1831v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", (Object) Integer.valueOf(this.mUsed.mCount), (Object) Integer.valueOf(this.mUsed.mNumBytes), (Object) Integer.valueOf(this.mFree.mCount), (Object) Integer.valueOf(this.mFree.mNumBytes));
        }
    }

    public synchronized Map<String, Integer> getStats() {
        Map<String, Integer> stats;
        stats = new HashMap<>();
        for (int i = 0; i < this.mBuckets.size(); i++) {
            stats.put(PoolStatsTracker.BUCKETS_USED_PREFIX + getSizeInBytes(this.mBuckets.keyAt(i)), Integer.valueOf(((Bucket) this.mBuckets.valueAt(i)).getInUseCount()));
        }
        stats.put(PoolStatsTracker.SOFT_CAP, Integer.valueOf(this.mPoolParams.maxSizeSoftCap));
        stats.put(PoolStatsTracker.HARD_CAP, Integer.valueOf(this.mPoolParams.maxSizeHardCap));
        stats.put(PoolStatsTracker.USED_COUNT, Integer.valueOf(this.mUsed.mCount));
        stats.put(PoolStatsTracker.USED_BYTES, Integer.valueOf(this.mUsed.mNumBytes));
        stats.put(PoolStatsTracker.FREE_COUNT, Integer.valueOf(this.mFree.mCount));
        stats.put(PoolStatsTracker.FREE_BYTES, Integer.valueOf(this.mFree.mNumBytes));
        return stats;
    }
}

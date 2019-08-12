package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;

public class PoolConfig {
    private final PoolParams mBitmapPoolParams;
    private final PoolStatsTracker mBitmapPoolStatsTracker;
    private final PoolParams mFlexByteArrayPoolParams;
    private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private final PoolParams mNativeMemoryChunkPoolParams;
    private final PoolStatsTracker mNativeMemoryChunkPoolStatsTracker;
    private final PoolParams mSmallByteArrayPoolParams;
    private final PoolStatsTracker mSmallByteArrayPoolStatsTracker;

    public static class Builder {
        /* access modifiers changed from: private */
        public PoolParams mBitmapPoolParams;
        /* access modifiers changed from: private */
        public PoolStatsTracker mBitmapPoolStatsTracker;
        /* access modifiers changed from: private */
        public PoolParams mFlexByteArrayPoolParams;
        /* access modifiers changed from: private */
        public MemoryTrimmableRegistry mMemoryTrimmableRegistry;
        /* access modifiers changed from: private */
        public PoolParams mNativeMemoryChunkPoolParams;
        /* access modifiers changed from: private */
        public PoolStatsTracker mNativeMemoryChunkPoolStatsTracker;
        /* access modifiers changed from: private */
        public PoolParams mSmallByteArrayPoolParams;
        /* access modifiers changed from: private */
        public PoolStatsTracker mSmallByteArrayPoolStatsTracker;

        private Builder() {
        }

        public Builder setBitmapPoolParams(PoolParams bitmapPoolParams) {
            this.mBitmapPoolParams = (PoolParams) Preconditions.checkNotNull(bitmapPoolParams);
            return this;
        }

        public Builder setBitmapPoolStatsTracker(PoolStatsTracker bitmapPoolStatsTracker) {
            this.mBitmapPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(bitmapPoolStatsTracker);
            return this;
        }

        public Builder setFlexByteArrayPoolParams(PoolParams flexByteArrayPoolParams) {
            this.mFlexByteArrayPoolParams = flexByteArrayPoolParams;
            return this;
        }

        public Builder setMemoryTrimmableRegistry(MemoryTrimmableRegistry memoryTrimmableRegistry) {
            this.mMemoryTrimmableRegistry = memoryTrimmableRegistry;
            return this;
        }

        public Builder setNativeMemoryChunkPoolParams(PoolParams nativeMemoryChunkPoolParams) {
            this.mNativeMemoryChunkPoolParams = (PoolParams) Preconditions.checkNotNull(nativeMemoryChunkPoolParams);
            return this;
        }

        public Builder setNativeMemoryChunkPoolStatsTracker(PoolStatsTracker nativeMemoryChunkPoolStatsTracker) {
            this.mNativeMemoryChunkPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(nativeMemoryChunkPoolStatsTracker);
            return this;
        }

        public Builder setSmallByteArrayPoolParams(PoolParams commonByteArrayPoolParams) {
            this.mSmallByteArrayPoolParams = (PoolParams) Preconditions.checkNotNull(commonByteArrayPoolParams);
            return this;
        }

        public Builder setSmallByteArrayPoolStatsTracker(PoolStatsTracker smallByteArrayPoolStatsTracker) {
            this.mSmallByteArrayPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(smallByteArrayPoolStatsTracker);
            return this;
        }

        public PoolConfig build() {
            return new PoolConfig(this);
        }
    }

    private PoolConfig(Builder builder) {
        PoolParams access$000;
        PoolStatsTracker access$100;
        PoolParams access$200;
        MemoryTrimmableRegistry access$300;
        PoolParams access$400;
        PoolStatsTracker access$500;
        PoolParams access$600;
        PoolStatsTracker access$700;
        if (builder.mBitmapPoolParams == null) {
            access$000 = DefaultBitmapPoolParams.get();
        } else {
            access$000 = builder.mBitmapPoolParams;
        }
        this.mBitmapPoolParams = access$000;
        if (builder.mBitmapPoolStatsTracker == null) {
            access$100 = NoOpPoolStatsTracker.getInstance();
        } else {
            access$100 = builder.mBitmapPoolStatsTracker;
        }
        this.mBitmapPoolStatsTracker = access$100;
        if (builder.mFlexByteArrayPoolParams == null) {
            access$200 = DefaultFlexByteArrayPoolParams.get();
        } else {
            access$200 = builder.mFlexByteArrayPoolParams;
        }
        this.mFlexByteArrayPoolParams = access$200;
        if (builder.mMemoryTrimmableRegistry == null) {
            access$300 = NoOpMemoryTrimmableRegistry.getInstance();
        } else {
            access$300 = builder.mMemoryTrimmableRegistry;
        }
        this.mMemoryTrimmableRegistry = access$300;
        if (builder.mNativeMemoryChunkPoolParams == null) {
            access$400 = DefaultNativeMemoryChunkPoolParams.get();
        } else {
            access$400 = builder.mNativeMemoryChunkPoolParams;
        }
        this.mNativeMemoryChunkPoolParams = access$400;
        if (builder.mNativeMemoryChunkPoolStatsTracker == null) {
            access$500 = NoOpPoolStatsTracker.getInstance();
        } else {
            access$500 = builder.mNativeMemoryChunkPoolStatsTracker;
        }
        this.mNativeMemoryChunkPoolStatsTracker = access$500;
        if (builder.mSmallByteArrayPoolParams == null) {
            access$600 = DefaultByteArrayPoolParams.get();
        } else {
            access$600 = builder.mSmallByteArrayPoolParams;
        }
        this.mSmallByteArrayPoolParams = access$600;
        if (builder.mSmallByteArrayPoolStatsTracker == null) {
            access$700 = NoOpPoolStatsTracker.getInstance();
        } else {
            access$700 = builder.mSmallByteArrayPoolStatsTracker;
        }
        this.mSmallByteArrayPoolStatsTracker = access$700;
    }

    public PoolParams getBitmapPoolParams() {
        return this.mBitmapPoolParams;
    }

    public PoolStatsTracker getBitmapPoolStatsTracker() {
        return this.mBitmapPoolStatsTracker;
    }

    public MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        return this.mMemoryTrimmableRegistry;
    }

    public PoolParams getNativeMemoryChunkPoolParams() {
        return this.mNativeMemoryChunkPoolParams;
    }

    public PoolStatsTracker getNativeMemoryChunkPoolStatsTracker() {
        return this.mNativeMemoryChunkPoolStatsTracker;
    }

    public PoolParams getFlexByteArrayPoolParams() {
        return this.mFlexByteArrayPoolParams;
    }

    public PoolParams getSmallByteArrayPoolParams() {
        return this.mSmallByteArrayPoolParams;
    }

    public PoolStatsTracker getSmallByteArrayPoolStatsTracker() {
        return this.mSmallByteArrayPoolStatsTracker;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}

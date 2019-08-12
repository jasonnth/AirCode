package com.apollographql.apollo.cache.normalized.lru;

import com.apollographql.apollo.api.internal.Optional;
import java.util.concurrent.TimeUnit;

public final class EvictionPolicy {
    public static final EvictionPolicy NO_EVICTION = builder().build();
    private final Optional<Long> expireAfterAccess;
    private final Optional<TimeUnit> expireAfterAccessTimeUnit;
    private final Optional<Long> expireAfterWrite;
    private final Optional<TimeUnit> expireAfterWriteTimeUnit;
    private final Optional<Long> maxEntries;
    private final Optional<Long> maxSizeBytes;

    public static class Builder {
        private Optional<Long> expireAfterAccess;
        private Optional<TimeUnit> expireAfterAccessTimeUnit;
        private Optional<Long> expireAfterWrite;
        private Optional<TimeUnit> expireAfterWriteTimeUnit;
        private Optional<Long> maxEntries;
        private Optional<Long> maxSizeBytes;

        private Builder() {
            this.maxSizeBytes = Optional.absent();
            this.maxEntries = Optional.absent();
            this.expireAfterAccess = Optional.absent();
            this.expireAfterAccessTimeUnit = Optional.absent();
            this.expireAfterWrite = Optional.absent();
            this.expireAfterWriteTimeUnit = Optional.absent();
        }

        public EvictionPolicy build() {
            return new EvictionPolicy(this.maxSizeBytes, this.maxEntries, this.expireAfterAccess, this.expireAfterAccessTimeUnit, this.expireAfterWrite, this.expireAfterWriteTimeUnit);
        }
    }

    /* access modifiers changed from: 0000 */
    public Optional<Long> maxSizeBytes() {
        return this.maxSizeBytes;
    }

    /* access modifiers changed from: 0000 */
    public Optional<Long> maxEntries() {
        return this.maxEntries;
    }

    /* access modifiers changed from: 0000 */
    public Optional<Long> expireAfterAccess() {
        return this.expireAfterAccess;
    }

    /* access modifiers changed from: 0000 */
    public Optional<TimeUnit> expireAfterAccessTimeUnit() {
        return this.expireAfterAccessTimeUnit;
    }

    /* access modifiers changed from: 0000 */
    public Optional<Long> expireAfterWrite() {
        return this.expireAfterWrite;
    }

    /* access modifiers changed from: 0000 */
    public Optional<TimeUnit> expireAfterWriteTimeUnit() {
        return this.expireAfterWriteTimeUnit;
    }

    public static Builder builder() {
        return new Builder();
    }

    private EvictionPolicy(Optional<Long> maxSizeBytes2, Optional<Long> maxEntries2, Optional<Long> expireAfterAccess2, Optional<TimeUnit> expireAfterAccessTimeUnit2, Optional<Long> expireAfterWrite2, Optional<TimeUnit> expireAfterWriteTimeUnit2) {
        this.maxSizeBytes = maxSizeBytes2;
        this.maxEntries = maxEntries2;
        this.expireAfterAccess = expireAfterAccess2;
        this.expireAfterAccessTimeUnit = expireAfterAccessTimeUnit2;
        this.expireAfterWrite = expireAfterWrite2;
        this.expireAfterWriteTimeUnit = expireAfterWriteTimeUnit2;
    }
}

package com.apollographql.apollo.cache.http;

import com.apollographql.apollo.internal.cache.http.HttpCacheFetchStrategy;
import java.util.concurrent.TimeUnit;

public final class HttpCachePolicy {
    public static final ExpirePolicy CACHE_FIRST = new ExpirePolicy(HttpCacheFetchStrategy.CACHE_FIRST);
    public static final ExpirePolicy CACHE_ONLY = new ExpirePolicy(HttpCacheFetchStrategy.CACHE_ONLY);
    public static final ExpirePolicy NETWORK_FIRST = new ExpirePolicy(HttpCacheFetchStrategy.NETWORK_FIRST);
    public static final Policy NETWORK_ONLY = new Policy(HttpCacheFetchStrategy.NETWORK_ONLY, 0, null, false);

    public static final class ExpirePolicy extends Policy {
        ExpirePolicy(HttpCacheFetchStrategy fetchStrategy) {
            super(fetchStrategy, 0, null, false);
        }
    }

    public static class Policy {
        public final boolean expireAfterRead;
        public final TimeUnit expireTimeUnit;
        public final long expireTimeout;
        public final HttpCacheFetchStrategy fetchStrategy;

        Policy(HttpCacheFetchStrategy fetchStrategy2, long expireTimeout2, TimeUnit expireTimeUnit2, boolean expireAfterRead2) {
            this.fetchStrategy = fetchStrategy2;
            this.expireTimeout = expireTimeout2;
            this.expireTimeUnit = expireTimeUnit2;
            this.expireAfterRead = expireAfterRead2;
        }

        public long expireTimeoutMs() {
            if (this.expireTimeUnit == null) {
                return 0;
            }
            return this.expireTimeUnit.toMillis(this.expireTimeout);
        }
    }
}

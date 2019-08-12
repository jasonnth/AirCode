package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.api.internal.Utils;

public final class CacheKey {
    public static final CacheKey NO_KEY = new CacheKey("");
    private final String key;

    public static CacheKey from(String key2) {
        return new CacheKey((String) Utils.checkNotNull(key2, "key == null"));
    }

    private CacheKey(String key2) {
        this.key = key2;
    }

    public String key() {
        return this.key;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CacheKey)) {
            return false;
        }
        return this.key.equals(((CacheKey) o).key);
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public String toString() {
        return this.key;
    }
}

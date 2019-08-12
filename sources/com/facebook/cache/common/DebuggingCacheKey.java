package com.facebook.cache.common;

public class DebuggingCacheKey extends SimpleCacheKey {
    private final Object mCallerContext;

    public DebuggingCacheKey(String key, Object callerContext) {
        super(key);
        this.mCallerContext = callerContext;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }
}

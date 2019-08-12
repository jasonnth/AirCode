package com.nytimes.android.external.cache;

final class Platform {
    static long systemNanoTime() {
        return System.nanoTime();
    }
}

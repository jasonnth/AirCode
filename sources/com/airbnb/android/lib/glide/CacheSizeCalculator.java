package com.airbnb.android.lib.glide;

import com.airbnb.android.core.utils.MemoryUtils;

public class CacheSizeCalculator {
    public static final int MAX_DISK_CACHE_SIZE = 83886080;

    public static int getMemoryCacheSize() {
        int memoryAvailableInBytes = MemoryUtils.getMemoryClass() * 1024 * 1024;
        return MemoryUtils.isLowMemoryDevice() ? memoryAvailableInBytes / 15 : memoryAvailableInBytes / 12;
    }

    public static int getBitmapPoolSize() {
        int memoryAvailableInBytes = MemoryUtils.getMemoryClass() * 1024 * 1024;
        return MemoryUtils.isLowMemoryDevice() ? memoryAvailableInBytes / 26 : memoryAvailableInBytes / 22;
    }
}

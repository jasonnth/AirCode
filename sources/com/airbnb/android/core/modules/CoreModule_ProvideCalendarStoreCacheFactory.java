package com.airbnb.android.core.modules;

import com.airbnb.android.core.calendar.CalendarStoreCache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoreModule_ProvideCalendarStoreCacheFactory implements Factory<CalendarStoreCache> {
    private static final CoreModule_ProvideCalendarStoreCacheFactory INSTANCE = new CoreModule_ProvideCalendarStoreCacheFactory();

    public CalendarStoreCache get() {
        return (CalendarStoreCache) Preconditions.checkNotNull(CoreModule.provideCalendarStoreCache(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CalendarStoreCache> create() {
        return INSTANCE;
    }

    public static CalendarStoreCache proxyProvideCalendarStoreCache() {
        return CoreModule.provideCalendarStoreCache();
    }
}

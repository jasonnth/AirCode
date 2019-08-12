package com.airbnb.android.core.modules;

import com.airbnb.android.core.calendar.CalendarStoreConfig;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoreModule_ProvideCalendarStoreConfigFactory implements Factory<CalendarStoreConfig> {
    private static final CoreModule_ProvideCalendarStoreConfigFactory INSTANCE = new CoreModule_ProvideCalendarStoreConfigFactory();

    public CalendarStoreConfig get() {
        return (CalendarStoreConfig) Preconditions.checkNotNull(CoreModule.provideCalendarStoreConfig(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CalendarStoreConfig> create() {
        return INSTANCE;
    }

    public static CalendarStoreConfig proxyProvideCalendarStoreConfig() {
        return CoreModule.provideCalendarStoreConfig();
    }
}

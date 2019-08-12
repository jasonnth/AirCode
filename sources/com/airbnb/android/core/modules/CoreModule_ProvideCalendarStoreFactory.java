package com.airbnb.android.core.modules;

import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.calendar.CalendarStoreCache;
import com.airbnb.android.core.calendar.CalendarStoreConfig;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideCalendarStoreFactory implements Factory<CalendarStore> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideCalendarStoreFactory.class.desiredAssertionStatus());
    private final Provider<Bus> busProvider;
    private final Provider<CalendarStoreCache> calendarStoreCacheProvider;
    private final Provider<CalendarStoreConfig> configProvider;

    public CoreModule_ProvideCalendarStoreFactory(Provider<CalendarStoreCache> calendarStoreCacheProvider2, Provider<CalendarStoreConfig> configProvider2, Provider<Bus> busProvider2) {
        if ($assertionsDisabled || calendarStoreCacheProvider2 != null) {
            this.calendarStoreCacheProvider = calendarStoreCacheProvider2;
            if ($assertionsDisabled || configProvider2 != null) {
                this.configProvider = configProvider2;
                if ($assertionsDisabled || busProvider2 != null) {
                    this.busProvider = busProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public CalendarStore get() {
        return (CalendarStore) Preconditions.checkNotNull(CoreModule.provideCalendarStore((CalendarStoreCache) this.calendarStoreCacheProvider.get(), (CalendarStoreConfig) this.configProvider.get(), (Bus) this.busProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CalendarStore> create(Provider<CalendarStoreCache> calendarStoreCacheProvider2, Provider<CalendarStoreConfig> configProvider2, Provider<Bus> busProvider2) {
        return new CoreModule_ProvideCalendarStoreFactory(calendarStoreCacheProvider2, configProvider2, busProvider2);
    }

    public static CalendarStore proxyProvideCalendarStore(CalendarStoreCache calendarStoreCache, CalendarStoreConfig config, Bus bus) {
        return CoreModule.provideCalendarStore(calendarStoreCache, config, bus);
    }
}

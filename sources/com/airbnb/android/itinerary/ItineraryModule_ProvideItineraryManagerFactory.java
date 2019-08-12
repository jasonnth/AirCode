package com.airbnb.android.itinerary;

import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.itinerary.ItineraryManager;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.itinerary.data.ItineraryTableOpenHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ItineraryModule_ProvideItineraryManagerFactory implements Factory<ItineraryManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ItineraryModule_ProvideItineraryManagerFactory.class.desiredAssertionStatus());
    private final ItineraryModule module;
    private final Provider<ItineraryTableOpenHelper> openHelperProvider;
    private final Provider<PerformanceLogger> performanceLoggerProvider;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;

    public ItineraryModule_ProvideItineraryManagerFactory(ItineraryModule module2, Provider<ItineraryTableOpenHelper> openHelperProvider2, Provider<PerformanceLogger> performanceLoggerProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || openHelperProvider2 != null) {
                this.openHelperProvider = openHelperProvider2;
                if ($assertionsDisabled || performanceLoggerProvider2 != null) {
                    this.performanceLoggerProvider = performanceLoggerProvider2;
                    if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
                        this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ItineraryManager get() {
        return (ItineraryManager) Preconditions.checkNotNull(this.module.provideItineraryManager((ItineraryTableOpenHelper) this.openHelperProvider.get(), (PerformanceLogger) this.performanceLoggerProvider.get(), (SharedPrefsHelper) this.sharedPrefsHelperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ItineraryManager> create(ItineraryModule module2, Provider<ItineraryTableOpenHelper> openHelperProvider2, Provider<PerformanceLogger> performanceLoggerProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        return new ItineraryModule_ProvideItineraryManagerFactory(module2, openHelperProvider2, performanceLoggerProvider2, sharedPrefsHelperProvider2);
    }
}

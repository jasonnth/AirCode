package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.LogAirInitializer;
import com.airbnb.android.core.data.ConverterFactory;
import com.airbnb.android.core.utils.MemoryUtils;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnalyticsModule_ProvideLogAirInitializerFactory implements Factory<LogAirInitializer> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnalyticsModule_ProvideLogAirInitializerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbApi> airbnbApiProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ConverterFactory> converterFactoryProvider;
    private final Provider<MemoryUtils> memoryUtilsProvider;
    private final AnalyticsModule module;

    public AnalyticsModule_ProvideLogAirInitializerFactory(AnalyticsModule module2, Provider<Context> contextProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<MemoryUtils> memoryUtilsProvider2, Provider<ConverterFactory> converterFactoryProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || airbnbApiProvider2 != null) {
                    this.airbnbApiProvider = airbnbApiProvider2;
                    if ($assertionsDisabled || memoryUtilsProvider2 != null) {
                        this.memoryUtilsProvider = memoryUtilsProvider2;
                        if ($assertionsDisabled || converterFactoryProvider2 != null) {
                            this.converterFactoryProvider = converterFactoryProvider2;
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
        throw new AssertionError();
    }

    public LogAirInitializer get() {
        return (LogAirInitializer) Preconditions.checkNotNull(this.module.provideLogAirInitializer((Context) this.contextProvider.get(), DoubleCheck.lazy(this.airbnbApiProvider), (MemoryUtils) this.memoryUtilsProvider.get(), (ConverterFactory) this.converterFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<LogAirInitializer> create(AnalyticsModule module2, Provider<Context> contextProvider2, Provider<AirbnbApi> airbnbApiProvider2, Provider<MemoryUtils> memoryUtilsProvider2, Provider<ConverterFactory> converterFactoryProvider2) {
        return new AnalyticsModule_ProvideLogAirInitializerFactory(module2, contextProvider2, airbnbApiProvider2, memoryUtilsProvider2, converterFactoryProvider2);
    }

    public static LogAirInitializer proxyProvideLogAirInitializer(AnalyticsModule instance, Context context, Lazy<AirbnbApi> airbnbApi, MemoryUtils memoryUtils, ConverterFactory converterFactory) {
        return instance.provideLogAirInitializer(context, airbnbApi, memoryUtils, converterFactory);
    }
}

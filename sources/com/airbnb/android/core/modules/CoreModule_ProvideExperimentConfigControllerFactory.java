package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.controllers.ExperimentConfigController;
import com.airbnb.android.core.controllers.TrebuchetController;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideExperimentConfigControllerFactory implements Factory<ExperimentConfigController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideExperimentConfigControllerFactory.class.desiredAssertionStatus());
    private final Provider<Bus> busProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ExperimentsProvider> experimentsProvider;
    private final Provider<AirRequestInitializer> initializerProvider;
    private final Provider<PerformanceLogger> performanceLoggerProvider;
    private final Provider<TrebuchetController> trebuchetControllerProvider;

    public CoreModule_ProvideExperimentConfigControllerFactory(Provider<Context> contextProvider2, Provider<AirRequestInitializer> initializerProvider2, Provider<ExperimentsProvider> experimentsProvider2, Provider<Bus> busProvider2, Provider<PerformanceLogger> performanceLoggerProvider2, Provider<TrebuchetController> trebuchetControllerProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            if ($assertionsDisabled || initializerProvider2 != null) {
                this.initializerProvider = initializerProvider2;
                if ($assertionsDisabled || experimentsProvider2 != null) {
                    this.experimentsProvider = experimentsProvider2;
                    if ($assertionsDisabled || busProvider2 != null) {
                        this.busProvider = busProvider2;
                        if ($assertionsDisabled || performanceLoggerProvider2 != null) {
                            this.performanceLoggerProvider = performanceLoggerProvider2;
                            if ($assertionsDisabled || trebuchetControllerProvider2 != null) {
                                this.trebuchetControllerProvider = trebuchetControllerProvider2;
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
        throw new AssertionError();
    }

    public ExperimentConfigController get() {
        return (ExperimentConfigController) Preconditions.checkNotNull(CoreModule.provideExperimentConfigController((Context) this.contextProvider.get(), (AirRequestInitializer) this.initializerProvider.get(), (ExperimentsProvider) this.experimentsProvider.get(), (Bus) this.busProvider.get(), (PerformanceLogger) this.performanceLoggerProvider.get(), (TrebuchetController) this.trebuchetControllerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ExperimentConfigController> create(Provider<Context> contextProvider2, Provider<AirRequestInitializer> initializerProvider2, Provider<ExperimentsProvider> experimentsProvider2, Provider<Bus> busProvider2, Provider<PerformanceLogger> performanceLoggerProvider2, Provider<TrebuchetController> trebuchetControllerProvider2) {
        return new CoreModule_ProvideExperimentConfigControllerFactory(contextProvider2, initializerProvider2, experimentsProvider2, busProvider2, performanceLoggerProvider2, trebuchetControllerProvider2);
    }

    public static ExperimentConfigController proxyProvideExperimentConfigController(Context context, AirRequestInitializer initializer, ExperimentsProvider experimentsProvider2, Bus bus, PerformanceLogger performanceLogger, TrebuchetController trebuchetController) {
        return CoreModule.provideExperimentConfigController(context, initializer, experimentsProvider2, bus, performanceLogger, trebuchetController);
    }
}

package com.airbnb.android.core.modules;

import com.airbnb.android.core.erf.ErfCallbacks;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.erf.Erf;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideErfFactory implements Factory<Erf> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideErfFactory.class.desiredAssertionStatus());
    private final Provider<ErfCallbacks> callbacksProvider;
    private final Provider<ExperimentsProvider> experimentsProvider;

    public CoreModule_ProvideErfFactory(Provider<ExperimentsProvider> experimentsProvider2, Provider<ErfCallbacks> callbacksProvider2) {
        if ($assertionsDisabled || experimentsProvider2 != null) {
            this.experimentsProvider = experimentsProvider2;
            if ($assertionsDisabled || callbacksProvider2 != null) {
                this.callbacksProvider = callbacksProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public Erf get() {
        return (Erf) Preconditions.checkNotNull(CoreModule.provideErf((ExperimentsProvider) this.experimentsProvider.get(), (ErfCallbacks) this.callbacksProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Erf> create(Provider<ExperimentsProvider> experimentsProvider2, Provider<ErfCallbacks> callbacksProvider2) {
        return new CoreModule_ProvideErfFactory(experimentsProvider2, callbacksProvider2);
    }

    public static Erf proxyProvideErf(ExperimentsProvider experimentsProvider2, ErfCallbacks callbacks) {
        return CoreModule.provideErf(experimentsProvider2, callbacks);
    }
}

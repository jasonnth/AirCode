package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.erf.ErfExperimentsTableOpenHelper;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideExperimentsProviderFactory implements Factory<ExperimentsProvider> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideExperimentsProviderFactory.class.desiredAssertionStatus());
    private final Provider<Bus> busProvider;
    private final Provider<Context> contextProvider;
    private final CoreModule module;
    private final Provider<ErfExperimentsTableOpenHelper> tableOpenHelperProvider;

    public CoreModule_ProvideExperimentsProviderFactory(CoreModule module2, Provider<Context> contextProvider2, Provider<Bus> busProvider2, Provider<ErfExperimentsTableOpenHelper> tableOpenHelperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || busProvider2 != null) {
                    this.busProvider = busProvider2;
                    if ($assertionsDisabled || tableOpenHelperProvider2 != null) {
                        this.tableOpenHelperProvider = tableOpenHelperProvider2;
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

    public ExperimentsProvider get() {
        return (ExperimentsProvider) Preconditions.checkNotNull(this.module.provideExperimentsProvider((Context) this.contextProvider.get(), (Bus) this.busProvider.get(), (ErfExperimentsTableOpenHelper) this.tableOpenHelperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ExperimentsProvider> create(CoreModule module2, Provider<Context> contextProvider2, Provider<Bus> busProvider2, Provider<ErfExperimentsTableOpenHelper> tableOpenHelperProvider2) {
        return new CoreModule_ProvideExperimentsProviderFactory(module2, contextProvider2, busProvider2, tableOpenHelperProvider2);
    }

    public static ExperimentsProvider proxyProvideExperimentsProvider(CoreModule instance, Context context, Bus bus, ErfExperimentsTableOpenHelper tableOpenHelper) {
        return instance.provideExperimentsProvider(context, bus, tableOpenHelper);
    }
}

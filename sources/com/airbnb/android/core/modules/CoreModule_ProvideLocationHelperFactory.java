package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.location.LocationClientFacade;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideLocationHelperFactory implements Factory<LocationClientFacade> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideLocationHelperFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final CoreModule module;

    public CoreModule_ProvideLocationHelperFactory(CoreModule module2, Provider<Context> contextProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public LocationClientFacade get() {
        return (LocationClientFacade) Preconditions.checkNotNull(this.module.provideLocationHelper((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<LocationClientFacade> create(CoreModule module2, Provider<Context> contextProvider2) {
        return new CoreModule_ProvideLocationHelperFactory(module2, contextProvider2);
    }

    public static LocationClientFacade proxyProvideLocationHelper(CoreModule instance, Context context) {
        return instance.provideLocationHelper(context);
    }
}

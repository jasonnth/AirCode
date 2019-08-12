package com.airbnb.android.superhero;

import android.content.Context;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.superhero.SuperHeroManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SuperHeroModule_ProvideSuperHeroManagerFactory implements Factory<SuperHeroManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!SuperHeroModule_ProvideSuperHeroManagerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final SuperHeroModule module;
    private final Provider<SuperHeroTableOpenHelper> openHelperProvider;

    public SuperHeroModule_ProvideSuperHeroManagerFactory(SuperHeroModule module2, Provider<Context> contextProvider2, Provider<SuperHeroTableOpenHelper> openHelperProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || openHelperProvider2 != null) {
                    this.openHelperProvider = openHelperProvider2;
                    if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                        this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
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

    public SuperHeroManager get() {
        return (SuperHeroManager) Preconditions.checkNotNull(this.module.provideSuperHeroManager((Context) this.contextProvider.get(), (SuperHeroTableOpenHelper) this.openHelperProvider.get(), (LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SuperHeroManager> create(SuperHeroModule module2, Provider<Context> contextProvider2, Provider<SuperHeroTableOpenHelper> openHelperProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new SuperHeroModule_ProvideSuperHeroManagerFactory(module2, contextProvider2, openHelperProvider2, loggingContextFactoryProvider2);
    }
}

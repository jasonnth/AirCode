package com.airbnb.android.superhero;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SuperHeroModule_ProvideSuperHeroTableOpenHelperFactory implements Factory<SuperHeroTableOpenHelper> {
    static final /* synthetic */ boolean $assertionsDisabled = (!SuperHeroModule_ProvideSuperHeroTableOpenHelperFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final SuperHeroModule module;

    public SuperHeroModule_ProvideSuperHeroTableOpenHelperFactory(SuperHeroModule module2, Provider<Context> contextProvider2) {
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

    public SuperHeroTableOpenHelper get() {
        return (SuperHeroTableOpenHelper) Preconditions.checkNotNull(this.module.provideSuperHeroTableOpenHelper((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SuperHeroTableOpenHelper> create(SuperHeroModule module2, Provider<Context> contextProvider2) {
        return new SuperHeroModule_ProvideSuperHeroTableOpenHelperFactory(module2, contextProvider2);
    }
}

package com.airbnb.android.explore;

import com.airbnb.android.explore.controllers.ExploreDataRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ExploreModule_ProvideExploreDataRepositoryFactory implements Factory<ExploreDataRepository> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ExploreModule_ProvideExploreDataRepositoryFactory.class.desiredAssertionStatus());
    private final ExploreModule module;

    public ExploreModule_ProvideExploreDataRepositoryFactory(ExploreModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public ExploreDataRepository get() {
        return (ExploreDataRepository) Preconditions.checkNotNull(this.module.provideExploreDataRepository(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ExploreDataRepository> create(ExploreModule module2) {
        return new ExploreModule_ProvideExploreDataRepositoryFactory(module2);
    }
}

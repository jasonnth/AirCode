package com.airbnb.p027n2;

import com.airbnb.p027n2.N2Component.Builder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.airbnb.n2.N2Module_ProvideN2ContextFactory */
public final class N2Module_ProvideN2ContextFactory implements Factory<N2Context> {
    static final /* synthetic */ boolean $assertionsDisabled = (!N2Module_ProvideN2ContextFactory.class.desiredAssertionStatus());
    private final N2Module module;
    private final Provider<Builder> n2ComponentBuilderProvider;

    public N2Module_ProvideN2ContextFactory(N2Module module2, Provider<Builder> n2ComponentBuilderProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || n2ComponentBuilderProvider2 != null) {
                this.n2ComponentBuilderProvider = n2ComponentBuilderProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public N2Context get() {
        return (N2Context) Preconditions.checkNotNull(this.module.provideN2Context((Builder) this.n2ComponentBuilderProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<N2Context> create(N2Module module2, Provider<Builder> n2ComponentBuilderProvider2) {
        return new N2Module_ProvideN2ContextFactory(module2, n2ComponentBuilderProvider2);
    }
}

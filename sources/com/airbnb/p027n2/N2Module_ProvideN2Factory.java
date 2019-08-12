package com.airbnb.p027n2;

import com.airbnb.p027n2.C0977N2.Callbacks;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.airbnb.n2.N2Module_ProvideN2Factory */
public final class N2Module_ProvideN2Factory implements Factory<C0977N2> {
    static final /* synthetic */ boolean $assertionsDisabled = (!N2Module_ProvideN2Factory.class.desiredAssertionStatus());
    private final Provider<Callbacks> callbacksProvider;
    private final N2Module module;

    public N2Module_ProvideN2Factory(N2Module module2, Provider<Callbacks> callbacksProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || callbacksProvider2 != null) {
                this.callbacksProvider = callbacksProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public C0977N2 get() {
        return (C0977N2) Preconditions.checkNotNull(this.module.provideN2((Callbacks) this.callbacksProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<C0977N2> create(N2Module module2, Provider<Callbacks> callbacksProvider2) {
        return new N2Module_ProvideN2Factory(module2, callbacksProvider2);
    }
}

package com.airbnb.android.react;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ReactModule_ProvideReactDeepLinkRegistryFactory implements Factory<ReactDeepLinkRegistry> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ReactModule_ProvideReactDeepLinkRegistryFactory.class.desiredAssertionStatus());
    private final Provider<ReactDeepLinkParser> deepLinkParserProvider;
    private final ReactModule module;

    public ReactModule_ProvideReactDeepLinkRegistryFactory(ReactModule module2, Provider<ReactDeepLinkParser> deepLinkParserProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || deepLinkParserProvider2 != null) {
                this.deepLinkParserProvider = deepLinkParserProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ReactDeepLinkRegistry get() {
        return (ReactDeepLinkRegistry) Preconditions.checkNotNull(this.module.provideReactDeepLinkRegistry((ReactDeepLinkParser) this.deepLinkParserProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ReactDeepLinkRegistry> create(ReactModule module2, Provider<ReactDeepLinkParser> deepLinkParserProvider2) {
        return new ReactModule_ProvideReactDeepLinkRegistryFactory(module2, deepLinkParserProvider2);
    }
}

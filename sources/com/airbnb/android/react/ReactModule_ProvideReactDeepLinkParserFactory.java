package com.airbnb.android.react;

import android.content.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ReactModule_ProvideReactDeepLinkParserFactory implements Factory<ReactDeepLinkParser> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ReactModule_ProvideReactDeepLinkParserFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final ReactModule module;
    private final Provider<ObjectMapper> objectMapperProvider;

    public ReactModule_ProvideReactDeepLinkParserFactory(ReactModule module2, Provider<Context> contextProvider2, Provider<ObjectMapper> objectMapperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || objectMapperProvider2 != null) {
                    this.objectMapperProvider = objectMapperProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ReactDeepLinkParser get() {
        return (ReactDeepLinkParser) Preconditions.checkNotNull(this.module.provideReactDeepLinkParser((Context) this.contextProvider.get(), (ObjectMapper) this.objectMapperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ReactDeepLinkParser> create(ReactModule module2, Provider<Context> contextProvider2, Provider<ObjectMapper> objectMapperProvider2) {
        return new ReactModule_ProvideReactDeepLinkParserFactory(module2, contextProvider2, objectMapperProvider2);
    }
}
